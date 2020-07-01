package com.sit.smartcertificate.sendmailnotice.core.sendmailnotice.domain;

import java.io.Serializable;

public class MailConf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5591111796980825154L;

	private String sender;
	private String cc1;
	private String cc2;
	private String sendNotif;
	private int dateWithIn;
	private String passSender;
	private String template;
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getCc1() {
		return cc1;
	}
	public void setCc1(String cc1) {
		this.cc1 = cc1;
	}
	public String getCc2() {
		return cc2;
	}
	public void setCc2(String cc2) {
		this.cc2 = cc2;
	}
	public String getSendNotif() {
		return sendNotif;
	}
	public void setSendNotif(String sendNotif) {
		this.sendNotif = sendNotif;
	}
	public int getDateWithIn() {
		return dateWithIn;
	}
	public void setDateWithIn(int dateWithIn) {
		this.dateWithIn = dateWithIn;
	}
	public String getPassSender() {
		return passSender;
	}
	public void setPassSender(String passSender) {
		this.passSender = passSender;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
}
