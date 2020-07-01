package com.sit.smartcertificate.sendmailnotice.core.sendmailnotice.service;

import java.util.List;

import org.apache.logging.log4j.Logger;

import com.sit.core.common.service.CommonService;
import com.sit.smartcertificate.sendmailnotice.core.config.parameter.domain.SQLPath;
import com.sit.smartcertificate.sendmailnotice.core.sendmailnotice.domain.MailConf;
import com.sit.smartcertificate.sendmailnotice.core.sendmailnotice.domain.SendMailNotice;

import util.database.connection.CCTConnection;

public class SendMailNoticeService extends CommonService{

	private SendMailNoticeDAO dao = null;
	
	public SendMailNoticeService(Logger logger) {
		super(logger);
		this.dao = new SendMailNoticeDAO(getLogger(), SQLPath.TEST_SQL.getSqlPath());
	}
	
	protected MailConf searchMailConf(CCTConnection conn) throws Exception {
		return dao.searchMailConf(conn);
	}
	
	protected List<SendMailNotice> searchDocExpire(CCTConnection conn, long dateWithIn) throws Exception {
		return dao.searchDocExpire(conn, dateWithIn);
	}
}
