package com.sit.smartcertificate.sendmailnotice.core.config.parameter.service;

import org.apache.logging.log4j.Logger;

import com.sit.core.common.service.CommonManager;
import com.sit.smartcertificate.sendmailnotice.util.database.CCTConnectionProvider;

import util.database.Database;
import util.database.connection.CCTConnection;
import util.database.connection.CCTConnectionUtil;


public class ParameterManager extends CommonManager {

	public static final String XML_PATH = System.getProperty("user.dir") + "/parameter.xml";
	
	private ParameterService service = null;
	
	public ParameterManager(Logger logger) {
		super(logger);
		this.service = new ParameterService(getLogger());
	}

	public Object get(String pathWithFileName, Object object) throws Exception {
		return getService().load(pathWithFileName, object);
	}
	
	public void testDBConnectionBG(Database[] dbConfig) {
		getLogger().debug("DB Connection test...");
		for (Database database : dbConfig) {
			CCTConnection conn = null;
			try {
				conn = new CCTConnectionProvider().getConnection(conn, database.getKey());
				getLogger().debug(database.getKey() + " > " + database.getLookup() + " > is ok.");
			} catch (Exception e) {
				getLogger().error(database.getKey() + " > " + database.getLookup() + " > is error.", e);
			} finally {
				CCTConnectionUtil.close(conn);
			}
		}
	}
	
	public String testSQL(CCTConnection conn) throws Exception {
		return getService().testSQL(conn);
		
	}
	
	public static void main(String[] args) {
//		ParameterManager m = new ParameterManager(DefaultLogUtil.INITIAL);
//		try {
//			Parameter parameter = m.get(XML_PATH);
//			m.testDBConnection(parameter.getDatabase());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	public ParameterService getService() {
		return service;
	}
}
