package com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain;

import java.io.Serializable;

public class MonitorApplication implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String appVersion;
	private String pathFileVersion;
	private String fileNameVersion;
	
	public String getAppVersion() {
		return appVersion;
	}
	public String getPathFileVersion() {
		return pathFileVersion;
	}
	public String getFileNameVersion() {
		return fileNameVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public void setPathFileVersion(String pathFileVersion) {
		this.pathFileVersion = pathFileVersion;
	}
	public void setFileNameVersion(String fileNameVersion) {
		this.fileNameVersion = fileNameVersion;
	}
	
}
