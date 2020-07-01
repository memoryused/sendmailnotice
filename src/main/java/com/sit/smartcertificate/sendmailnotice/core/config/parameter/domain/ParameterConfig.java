package com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain;

import java.io.Serializable;

import com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain.Application;
import com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain.DatabaseConfig;
import com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain.GlobalVariable;
import com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain.MonitorApplication;
import com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain.Parameter;
import com.sit.smartcertificate.sendmailnotice.core.config.parameter.service.ParameterManager;

import util.log4j2.DefaultLogUtil;

public class ParameterConfig implements Serializable {

	private static final long serialVersionUID = -2187894195556282622L;
	
	private static Parameter parameter;
	private static DatabaseConfig databaseConfig;

	public static void initial() {
		initial(null);
	}
	
	public static void initial(String path) {
		if ((path != null)
				&& !path.trim().isEmpty()) {
			path += "/";
		} else {
			path = "";
		}
		
		ParameterManager parameterManager = new ParameterManager(DefaultLogUtil.INITIAL);
		try {
			String parameterFile = path + GlobalVariable.CONFIG_PARAMETER_FILE;
			DefaultLogUtil.INITIAL.debug("Parameter path :- " + parameterFile);
			parameter = (Parameter) parameterManager.get(parameterFile, new Parameter());
		} catch (Exception e) {
			DefaultLogUtil.INITIAL.error("Can't load Parameter!!!", e);
		}
		
		try {
			String parameterFile = parameter.getApplication().getDatabaseConfigPath();
			DefaultLogUtil.INITIAL.debug("Database path :- " + parameterFile);
			databaseConfig = (DatabaseConfig) parameterManager.get(parameterFile, new DatabaseConfig());
		} catch (Exception e) {
			DefaultLogUtil.INITIAL.error("Can't load Database!!!", e);
		}
	}

	public static Application getApplication() {
		return parameter.getApplication();
	}

	public static MonitorApplication getMonitorApplication(){
		return parameter.getMonitorApplication();
	}
	
	public static Parameter getParameter() {
		return parameter;
	}
	
	public static DatabaseConfig getDatabaseConfig() {
		return databaseConfig;
	}
}
