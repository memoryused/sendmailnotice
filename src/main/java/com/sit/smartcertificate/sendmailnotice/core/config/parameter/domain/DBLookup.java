package com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain;

public enum DBLookup {
	sendmailnotice("sendmailnotice");

	private String lookup;

	private DBLookup(String lookup) {
		this.lookup = lookup;
	}

	public String getLookup() {
		return lookup;
	}
}