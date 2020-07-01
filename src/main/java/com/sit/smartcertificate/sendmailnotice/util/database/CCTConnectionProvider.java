package com.sit.smartcertificate.sendmailnotice.util.database;

import com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain.ParameterConfig;

import util.database.connection.BGCommonConnectionProvider;

public class CCTConnectionProvider extends BGCommonConnectionProvider {

	public CCTConnectionProvider() throws Exception {
		super(ParameterConfig.getDatabaseConfig().getDatabase());
	}
}
