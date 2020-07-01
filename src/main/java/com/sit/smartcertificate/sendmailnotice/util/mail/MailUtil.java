package com.sit.smartcertificate.sendmailnotice.util.mail;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.sit.smartcertificate.sendmailnotice.util.mail.domain.MailConfig;
import com.sit.smartcertificate.sendmailnotice.util.mail.domain.MailDetail;
import com.sit.smartcertificate.sendmailnotice.util.mail.domain.MailErrorType;
import com.sit.smartcertificate.sendmailnotice.util.mail.domain.MailSecurityType;
import com.sit.smartcertificate.sendmailnotice.util.mail.exception.MailConnectErrorException;

public class MailUtil {
	static boolean debug = true;
	static String encoding = "UTF-8";
	static String contenttype = "text/html;charset=UTF-8";
	static String multipart = "related";
	static String transport = "smtp";
	
	public enum MailConfigKey{
		DEBUG("debug"),	ENCODING("encoding"), CONTENT_TYPE("content-type"), MULTIPART("multi-part"), TRANSPORT("smtp");
		
		private String value;

		private MailConfigKey(String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}
	
	
	public static boolean checkConnect(String protocal,String mailServer,String emailAddress
			,String password,int port, MailSecurityType.SecType secType) throws Exception{
		boolean result = false;
		
		try{
			Properties properties = System.getProperties();
			if(secType == null){
				properties.put("mail.smtp.port", port);
				properties.put("mail.smtp.auth", true);
			}else{
				properties.put("mail.smtp.port", port);
				properties.put("mail.smtp.auth", true);
				properties.put("mail.smtp.ssl.trust", "*");
				properties.put("mail.smtp.ssl.enable",true);
				if(secType.equals(MailSecurityType.SecType.TLS_SSL)){
					properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				}
			}
			properties.put("mail.host", mailServer);
			properties.put("mail.smtp.timeout", "6000");
			properties.put("mail.smtp.connectiontimeout", "6000");

			Session session = null;
			Authenticator authenticator = new SMTPAuthenticator(emailAddress, password);
			session = Session.getInstance(properties, authenticator);
			session.setDebug(true);

			if(secType != null && secType.equals(MailSecurityType.SecType.STARTTLS)){
				properties.put("mail.smtp.starttls.enable", true);
			}
			
			Transport t = session.getTransport(protocal);
			t.connect(mailServer, emailAddress, password);
			System.out.println("Connect Success");
			
			result = true;
			
			//t.sendMessage(mimemessage, mimemessage.getAllRecipients());
			t.close();
		}catch(AuthenticationFailedException e){
			System.out.println(e);
			throw new MailConnectErrorException(MailErrorType.Message.AUTH_FAIL.getValue());		
		}catch(MessagingException e){
			System.out.println(e);
			throw new MailConnectErrorException(MailErrorType.Message.CONNECT_FAIL.getValue());
		}catch(Exception e){ 
			throw e;
		}
		
		return result;
		
	}
	
	private static class SMTPAuthenticator extends javax.mail.Authenticator {
		String username;
		String password;

		private SMTPAuthenticator(String authenticationUser, String authenticationPassword) {
			username = authenticationUser;
			password = authenticationPassword;
		}

		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	}
	
	
	public static void sendMail(MailConfig mailConfig, MailDetail detail) throws Exception{
		try{
			InternetAddress[] iAddressReceiver = null;
			if(detail.getMailReciver() != null && detail.getMailReciver().length > 0){
				iAddressReceiver = new InternetAddress[detail.getMailReciver().length];
				for (int i = 0; i < detail.getMailReciver().length; i++) {
					iAddressReceiver[i] = new InternetAddress(detail.getMailReciver()[i]);
				}
			}else{
				return;
			}
			
			InternetAddress[] iAddressReceiverCC = null;
			if(detail.getMailReciverCC()!= null && detail.getMailReciverCC().length > 0){
				iAddressReceiverCC = new InternetAddress[detail.getMailReciverCC().length];
				for (int i = 0; i < detail.getMailReciverCC().length; i++) {
					iAddressReceiverCC[i] = new InternetAddress(detail.getMailReciverCC()[i]);
				}
			}
			
			InternetAddress[] iAddressReceiverBCC = null;
			if(detail.getMailReciverBCC()!= null &&  detail.getMailReciverBCC().length > 0){
				iAddressReceiverBCC = new InternetAddress[detail.getMailReciverBCC().length];
				for (int i = 0; i < detail.getMailReciverBCC().length; i++) {
					iAddressReceiverBCC[i] = new InternetAddress(detail.getMailReciverBCC()[i]);
				}
			}
			
			Properties properties = System.getProperties();
			//Properties properties = new Properties();
			for (Map.Entry<String, String> entry : mailConfig.getMapProperties().entrySet()) {
	            System.out.println("Properties : " + entry.getKey() + " Properties Value : " + entry.getValue());
	            if(entry.getValue().equalsIgnoreCase("true")){
	            	properties.put(entry.getKey(),true);
	            }else if(entry.getValue().equalsIgnoreCase("false")){
	            	properties.put(entry.getKey(),false);
	            }else{
	            	properties.put(entry.getKey(),entry.getValue());
	            }
	            //properties.put(entry.getKey(),entry.getValue());
	            //properties.setProperty(entry.getKey(),entry.getValue());
	            //properties.put(entry.getKey(),entry.getValue());
	        }
			//Properties properties = MailConfig.getMapPropertiesMail();
			
//			Properties propertiesSystem = System.getProperties();
//			Properties propertiesConfig = MailConfig.getMapPropertiesMail();
//			for (Object pKey : propertiesConfig.keySet()) {
//				Object pValue = propertiesConfig.get(pKey);
//				if (pValue instanceof Boolean) {
//					System.out.println("Boolean " + pKey + " " + pValue);
//					propertiesSystem.put(pKey, Boolean.valueOf(pValue.toString()));
//				} else if (pValue instanceof Long) {
//					System.out.println("Long " + pKey + " " + pValue);
//					propertiesSystem.put(pKey, Long.valueOf(pValue.toString()));
//				} else {
//					System.out.println("Else " + pKey + " " + pValue);
//					propertiesSystem.put(pKey, pValue);
//				}
//			}
			
			
			Session session = null;
			Authenticator authenticator = new SMTPAuthenticator(mailConfig.getEmail(), mailConfig.getPassword());
			session = Session.getInstance(properties, authenticator);
		
			//session = Session.getInstance(properties);
			session.setDebug(mailConfig.isDebug());
			
			MimeMessage mimemessage = new MimeMessage(session);
			mimemessage.setSubject(detail.getSubject(), mailConfig.getEncoding());
			mimemessage.setFrom(new InternetAddress(mailConfig.getEmail()));
			mimemessage.addRecipients(Message.RecipientType.TO, iAddressReceiver);

			if (detail.getMailReciverCC()!=null && detail.getMailReciverCC().length > 0) {
				mimemessage.addRecipients(Message.RecipientType.CC, iAddressReceiverCC);
			}
			
			if (detail.getMailReciverBCC()!=null && detail.getMailReciverBCC().length > 0) {
				mimemessage.addRecipients(Message.RecipientType.BCC, iAddressReceiverBCC);
			}
			
			String html = detail.getMessage();
			BodyPart bodypart = new MimeBodyPart();
			bodypart.setContent(html, contenttype);
			
			MimeMultipart mimeMultipart = new MimeMultipart(multipart);
			mimeMultipart.addBodyPart(bodypart);
			
			if(detail.getLstFile()!=null){
				for (File file : detail.getLstFile()) {
					FileDataSource source = new FileDataSource(file);
					MimeBodyPart mimebodypart = new MimeBodyPart();
					mimebodypart.setDataHandler(new DataHandler(source));
					mimebodypart.setFileName(MimeUtility.encodeText(source.getName()));
					mimeMultipart.addBodyPart(mimebodypart);
				}
			}
			
			mimemessage.setContent(mimeMultipart);
			mimemessage.setSentDate(new java.util.Date());
			mimemessage.saveChanges();
			
			Transport t = session.getTransport();
			t.connect(mailConfig.getEmail(), mailConfig.getPassword());
			
			t.sendMessage(mimemessage, mimemessage.getAllRecipients());
			t.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}

}
