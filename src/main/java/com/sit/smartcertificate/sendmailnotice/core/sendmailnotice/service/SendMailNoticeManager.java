package com.sit.smartcertificate.sendmailnotice.core.sendmailnotice.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

import com.sit.core.common.service.CommonManager;
import com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain.ParameterConfig;
import com.sit.smartcertificate.sendmailnotice.core.sendmailnotice.domain.MailConf;
import com.sit.smartcertificate.sendmailnotice.core.sendmailnotice.domain.SendMailNotice;
import com.sit.smartcertificate.sendmailnotice.util.mail.MailUtil;
import com.sit.smartcertificate.sendmailnotice.util.mail.domain.MailConfig;
import com.sit.smartcertificate.sendmailnotice.util.mail.domain.MailDetail;

import util.cryptography.DecryptionUtil;
import util.cryptography.enums.DecType;
import util.database.connection.CCTConnection;

public class SendMailNoticeManager extends CommonManager{
	
	private SendMailNoticeService service = null;
	
	public SendMailNoticeManager(Logger logger) {
		super(logger);
		service = new SendMailNoticeService(logger);
	}

	public void checkDocExpire(CCTConnection conn) throws Exception{
		getLogger().info("checkDocExpire");
		
		try {
			getLogger().debug("Search mail configure...");
			MailConf mailConf = service.searchMailConf(conn);
			MailConfig conf = setMailConfig(mailConf);
			
			getLogger().debug("Search doc expired...");
			List<SendMailNotice> listResult = service.searchDocExpire(conn, mailConf.getDateWithIn());  //FIXME
			
			if (listResult != null) {
				for (SendMailNotice sendMailNotice : listResult) {
					if (sendMailNotice.getStatusSend().equals("Y")) {
						//SEND MAIL
						new Thread(() -> getLogger().debug(sendMail(conf, mailConf, sendMailNotice))).start();
					}
				}
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			
		}
	}
	
	private MailConfig setMailConfig(MailConf conf) throws Exception {
		MailConfig mailConfig = new MailConfig();
		
		try {
			mailConfig.initMailConfig(ParameterConfig.getApplication().getMailConfigurePath());
			mailConfig.setEmail(conf.getSender());
            //mailConfig.setPassword(DecryptionUtil.doDecrypt(conf.getPassSender(), DecType.AES128));
			mailConfig.setPassword(conf.getPassSender());
		} catch (Exception e) {
			throw e;
		}
		return mailConfig;
	}
	
	private boolean sendMail(MailConfig mailConf, MailConf tableConf, SendMailNotice sendMailNotice){
		boolean canSend = false;
		try {
			String message = String.format(tableConf.getTemplate(), sendMailNotice.getDocName()
					, sendMailNotice.getCertName()
					, sendMailNotice.getExpireDate()
					);
			
			List<String> listCc = new ArrayList<String>();
			if(!tableConf.getCc1().isEmpty()) {
				listCc.add(tableConf.getCc1());
			}
			
			if(!tableConf.getCc2().isEmpty()) {
				listCc.add(tableConf.getCc2());
			}
			
			String[] receiver = sendMailNotice.getReceiver().split(",", -1);

            MailDetail mailDetail = new MailDetail();
            mailDetail.setMailReciver(receiver);
            if(listCc.size()>0) {
            	mailDetail.setMailReciverCC(listCc.stream().toArray(String[]::new));
            }
            mailDetail.setMessage(message);
            mailDetail.setSubject("[Warning] Certificate will expire");
            
            MailUtil.sendMail(mailConf, mailDetail);
            canSend = true;
		} catch (Exception e) {
			canSend = false;
			getLogger().error("", e);
		}
		return canSend;
	}
}
