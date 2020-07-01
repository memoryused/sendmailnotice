package com.sit.smartcertificate.sendmailnotice.core.config.parameter.service;

import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.logging.log4j.Logger;

import com.sit.core.common.domain.CommonSQLPath;
import com.sit.core.common.service.CommonDAO;

import util.database.connection.CCTConnection;
import util.database.connection.CCTConnectionUtil;
import util.sql.SQLUtil;

public class ParameterDAO extends CommonDAO {

	public ParameterDAO(Logger logger, CommonSQLPath sqlPath) {
		super(logger, sqlPath);
	}
	
	/**
	 * ทดสอบ SQL
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	protected String testSQL(CCTConnection conn) throws Exception {
		
		String result = null;
		
		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "testSQL");
		
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = createStatement(conn);
			rst = executeQuery(stmt, sql);
			if (rst.next()) {
				result = rst.getString("dd");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		return result;
	}
	
	/**
	 * ทดสอบ SQL
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	protected String searchLogError(CCTConnection conn) throws Exception {
		getLogger().debug("Test Search");
		String result = null;
		
		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchLogError");
		
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = createStatement(conn);
			rst = executeQuery(stmt, sql);
			if (rst.next()) {
				getLogger().debug("ERROR_ID: " + rst.getString("ERROR_ID"));
				getLogger().debug("ERROR_CLASS: " + rst.getString("ERROR_CLASS"));
				getLogger().debug("ERROR_METHOD: " + rst.getString("ERROR_METHOD"));
				getLogger().debug("SITE_ID: " + rst.getString("SITE_ID"));
			}
			getLogger().debug("Test Search...OK");
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		return result;
	}
	
	protected void insertLogError(CCTConnection conn) throws Exception {
		getLogger().debug("Test Insert");
		int paramIndex = 0;
		Object[] params = new Object[3];
		params[paramIndex++] = "ParameterDAO";
		params[paramIndex++] = "insertLogError";
		params[paramIndex] = 1;
		
		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "insertLogError"
				, params);
	
		executeInsert(conn, sql);
		getLogger().debug("Test Insert...OK");
	}
	
	protected void updateLogError(CCTConnection conn) throws Exception {
		getLogger().debug("Test Update");
		int paramIndex = 0;
		Object[] params = new Object[3];
		params[paramIndex++] = "ParameterDAO";
		params[paramIndex++] = "insertLogError";
		params[paramIndex] = 1;
		
		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "updateLogError"
				, params);
	
		executeUpdate(conn, sql);
		getLogger().debug("Test Update...OK");
	}
}
