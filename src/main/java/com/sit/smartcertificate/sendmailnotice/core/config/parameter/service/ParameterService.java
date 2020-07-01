package com.sit.smartcertificate.sendmailnotice.core.config.parameter.service;

import org.apache.logging.log4j.Logger;

import com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain.SQLPath;
import com.sit.core.common.service.CommonService;

import util.database.connection.CCTConnection;
import util.xml.XMLUtil;

public class ParameterService extends CommonService {

	private ParameterDAO dao = null;
	
	public ParameterService(Logger logger) {
		super(logger);
		this.dao = new ParameterDAO(getLogger(), SQLPath.TEST_SQL.getSqlPath());
	}

	protected Object load(String pathWithFileName, Object object) throws Exception {
		Object objectOutput = null;
		try {
			getLogger().debug("pathWithFileName :- " + pathWithFileName);
			objectOutput = XMLUtil.xmlToObject(pathWithFileName, object);
		} catch (Exception e) {
			throw e;
		}
		return objectOutput;
	}

	protected String testSQL(CCTConnection conn) throws Exception {
//		getDao().searchLogError(conn);
//		getDao().insertLogError(conn);
//		getDao().updateLogError(conn);
		return getDao().testSQL(conn);
	}
	
	public ParameterDAO getDao() {
		return dao;
	}
}
