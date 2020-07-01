package com.sit.smartcertificate.sendmailnotice.core.sendmailnotice.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

import com.sit.core.common.domain.CommonSQLPath;
import com.sit.core.common.service.CommonDAO;
import com.sit.smartcertificate.sendmailnotice.core.sendmailnotice.domain.MailConf;
import com.sit.smartcertificate.sendmailnotice.core.sendmailnotice.domain.SendMailNotice;

import util.database.connection.CCTConnection;
import util.database.connection.CCTConnectionUtil;
import util.sql.SQLUtil;
import util.string.StringUtil;

public class SendMailNoticeDAO extends CommonDAO{

	public SendMailNoticeDAO(Logger logger, CommonSQLPath sqlPath) {
		super(logger, sqlPath);
	}

	protected MailConf searchMailConf(CCTConnection conn) throws Exception {
		MailConf conf = null;
		
		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchMailConf"
				);
		
		getLogger().debug(sql);

		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			
			if(rst.next()) {
				conf = new MailConf();
				conf.setCc1(StringUtil.nullToString(rst.getString("CC_1")));
				conf.setCc2(StringUtil.nullToString(rst.getString("CC_2")));
				conf.setDateWithIn(rst.getInt("M_Email_Controlcol"));
				conf.setPassSender(StringUtil.nullToString(rst.getString("password_sender")));
				conf.setSender(StringUtil.nullToString(rst.getString("Sender_1")));
				conf.setSendNotif(StringUtil.nullToString(rst.getString("Send_notif")));
				conf.setTemplate(StringUtil.nullToString(rst.getString("template")));
				
			}
		} catch (Exception e) {
			throw e;
			
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		return conf;
	}
	
	protected List<SendMailNotice> searchDocExpire(CCTConnection conn, long dateWithIn) throws Exception {
		List<SendMailNotice> listResult = new ArrayList<SendMailNotice>();
		
		/*
		int paramIndex = 0;
		
		Object[] params = new Object[2];
		params[paramIndex++] = dateWithIn;
		params[paramIndex++] = dateWithIn;
		*/
		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchDocExpire"
				);
		
		getLogger().debug(sql);

		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			
			while (rst.next()) {
				SendMailNotice obj = new SendMailNotice();
				obj.setStatusSend(StringUtil.nullToString(rst.getString("isSendmail")));
				obj.setReceiver(StringUtil.nullToString(rst.getString("contract_email")));
				obj.setCertName(StringUtil.nullToString(rst.getString("CERTIFICATE_NAME")));
				obj.setDocName(StringUtil.nullToString(rst.getString("DOCUMENT_NAME")));
				obj.setExpireDate(StringUtil.nullToString(rst.getString("exp_date")));
				
				listResult.add(obj);
			}
		} catch (Exception e) {
			throw e;
			
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		return listResult;
	}
}
