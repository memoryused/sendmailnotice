package com.sit.smartcertificate.sendmailnotice.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class sendmailnoticeCommonWS {
	
	private Logger logger = LogManager.getLogger(this.getClass().getSimpleName());
	
	public Logger getLogger() {
		return logger;
	}
	
}