package com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import util.database.Database;

@XmlRootElement
public class DatabaseConfig {

	private Database[] database;

	@XmlElement
	public Database[] getDatabase() {
		return database;
	}

	public void setDatabase(Database[] database) {
		this.database = database;
	}
}
