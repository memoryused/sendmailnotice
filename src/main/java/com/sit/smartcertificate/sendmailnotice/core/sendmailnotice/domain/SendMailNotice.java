package com.sit.smartcertificate.sendmailnotice.core.sendmailnotice.domain;

import java.io.Serializable;

public class SendMailNotice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2834324689337765681L;

	private String statusSend;
	private String receiver;
	private String certName;
	private String docName;
	private String expireDate;
	
	public String getStatusSend() {
		return statusSend;
	}
	public void setStatusSend(String statusSend) {
		this.statusSend = statusSend;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getCertName() {
		return certName;
	}
	public void setCertName(String certName) {
		this.certName = certName;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
}
