package com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Parameter implements Serializable {

	private static final long serialVersionUID = -578325849007499211L;

	// @XmlTransient
	// @XmlAttribute
	// @XmlElement
	private Application application;
	private MonitorApplication monitorApplication;
	
	public Application getApplication() {
		return application;
	}

	@XmlElement
	public void setApplication(Application application) {
		this.application = application;
	}

	public MonitorApplication getMonitorApplication() {
		return monitorApplication;
	}

	public void setMonitorApplication(MonitorApplication monitorApplication) {
		this.monitorApplication = monitorApplication;
	}
}
