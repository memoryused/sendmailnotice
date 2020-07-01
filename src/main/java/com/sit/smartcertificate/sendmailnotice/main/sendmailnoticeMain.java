package com.sit.smartcertificate.sendmailnotice.main;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain.DBLookup;
import com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain.GlobalVariable;
import com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain.ParameterConfig;
import com.sit.smartcertificate.sendmailnotice.core.config.parameter.service.ParameterManager;
import com.sit.smartcertificate.sendmailnotice.core.sendmailnotice.service.SendMailNoticeManager;
import com.sit.smartcertificate.sendmailnotice.util.database.CCTConnectionProvider;


import util.database.connection.CCTConnection;
import util.database.connection.CCTConnectionUtil;
import util.file.FileManagerUtil;
import util.log4j2.DefaultLogUtil;

public class sendmailnoticeMain {
	private static File f;
	private static FileChannel channel;
	private static FileLock lock;

	private static Logger logger = null;
	
	@SuppressWarnings({ "resource" })
	public static void main(String[] args) {
		
		Configurator.initialize(null, GlobalVariable.CONFIG_LOG4J_FILE);
		
		logger = LogManager.getLogger(DefaultLogUtil.INITIAL);
		
		getLogger().info("start");
		long start = Calendar.getInstance(Locale.ENGLISH).getTimeInMillis();
		try {
			f = new File(sendmailnoticeMain.class.getName() + ".lock");
			// Check if the lock exist
			/**
			 * sittipol.m comment if (f.exists()) { // if exist try to delete it f.delete(); }
			 */
			// Try to get the lock
			channel = new RandomAccessFile(f, "rw").getChannel();
			lock = channel.tryLock();
			if (lock == null) {
				// File is lock by other application
				channel.close();
				throw new RuntimeException("Only 1 instance of " + sendmailnoticeMain.class.getName() + " can run.");
			}
			// Add shutdown hook to release lock when application shutdown
			ShutdownHook shutdownHook = new ShutdownHook();
			Runtime.getRuntime().addShutdownHook(shutdownHook);

			try {
				
				ParameterConfig.initial();
				
				// สร้างไฟล์ Version
				String versionPathWithFilename = ParameterConfig.getMonitorApplication().getPathFileVersion() 
						+ ParameterConfig.getMonitorApplication().getFileNameVersion();
				FileManagerUtil.writeData(
						versionPathWithFilename, 
						ParameterConfig.getMonitorApplication().getAppVersion(), 
						false);
				
				ParameterManager parameterManager = new ParameterManager(getLogger());
				try {
					getLogger().info("Database test...");
					parameterManager.testDBConnectionBG(ParameterConfig.getDatabaseConfig().getDatabase());
					getLogger().info("Database test...ok");
				} catch (Exception e) {
					getLogger().error("Database test...error.", e);
				}
				
				getLogger().info("############### Start check doc expire ###################");
				CCTConnection conn = null;
				try {
					conn = new CCTConnectionProvider().getConnection(conn, DBLookup.sendmailnotice.getLookup());
					
					SendMailNoticeManager manager = new SendMailNoticeManager(getLogger());
					manager.checkDocExpire(conn);
					
				} catch (Exception e) {
					getLogger().error("", e);
				} finally {
					CCTConnectionUtil.close(conn);
				}

			} catch (Exception e) {
				getLogger().catching(e);
			}
		} catch (Exception e) {
			getLogger().error("Could not start process.", e);
		}
		getLogger().info("############### Finish check doc expire ###################");
		long end = Calendar.getInstance(Locale.ENGLISH).getTimeInMillis();
		getLogger().info("ProcessTime: " + convertTime(end - start));
		getLogger().info("end");
	}
	
	public static Logger getLogger() {
		return logger;
	}

	public static String convertTime(long elapsed) {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+0"));
		return formatter.format(new Date(elapsed));
	}

	public static void unlockFile() {
		// release and delete file lock
		try {
			if (lock != null) {
				lock.release();
				channel.close();
				f.delete();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static class ShutdownHook extends Thread {
		public void run() {
			unlockFile();
		}
	}
}
