package com.sit.smartcertificate.sendmailnotice.util.mail.domain;

import java.io.File;
import java.util.List;


public class MailDetail {
	
	private  String[] mailReciver;
	private  String[] mailReciverCC;
	private  String[] mailReciverBCC;
	
	private  List<File> lstFile;

	private  String subject;
	private  String message;


	public String[] getMailReciver() {
		return mailReciver;
	}

	public void setMailReciver(String[] mailReciver) {
		this.mailReciver = mailReciver;
	}

	public String[] getMailReciverCC() {
		return mailReciverCC;
	}

	public void setMailReciverCC(String[] mailReciverCC) {
		this.mailReciverCC = mailReciverCC;
	}

	public String[] getMailReciverBCC() {
		return mailReciverBCC;
	}

	public void setMailReciverBCC(String[] mailReciverBCC) {
		this.mailReciverBCC = mailReciverBCC;
	}

	public List<File> getLstFile() {
		return lstFile;
	}

	public void setLstFile(List<File> lstFile) {
		this.lstFile = lstFile;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	

	
	

}
