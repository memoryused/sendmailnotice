package com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain;

import java.io.Serializable;

public class Application implements Serializable {

	private static final long serialVersionUID = -6791131125526807670L;

	private String databaseConfigPath;
	private String mailConfigurePath;

	public String getDatabaseConfigPath() {
		return databaseConfigPath;
	}

	public void setDatabaseConfigPath(String databaseConfigPath) {
		this.databaseConfigPath = databaseConfigPath;
	}

	public String getMailConfigurePath() {
		return mailConfigurePath;
	}

	public void setMailConfigurePath(String mailConfigurePath) {
		this.mailConfigurePath = mailConfigurePath;
	}
}
