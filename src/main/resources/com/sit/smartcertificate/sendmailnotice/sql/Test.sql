testSQL{
	SELECT sysdate() as dd FROM DUAL
}

searchMailConf{
	SELECT Email_id, Sender_1, CC_1, CC_2, Send_notif, M_Email_Controlcol, password_sender, template 
	FROM [sendmailnotice].m_email_control
}

searchDocExpire{
	select date_format(td.Doc_expire_date, '%d/%m/%Y') as exp_date,
		DATEDIFF(td.Doc_expire_date, sysdate()) dateDiff,
		CASE WHEN DATEDIFF(td.Doc_expire_date, sysdate()) = (select send_1st from [sendmailnotice].m_email_control) THEN 'Y'
		WHEN DATEDIFF(td.Doc_expire_date, sysdate()) = (select send_2nd from [sendmailnotice].m_email_control) THEN 'Y'
		WHEN DATEDIFF(td.Doc_expire_date, sysdate()) = (select send_3rd from [sendmailnotice].m_email_control) THEN 'Y'
		WHEN DATEDIFF(td.Doc_expire_date, sysdate()) = (select send_4th from [sendmailnotice].m_email_control) THEN 'Y'
		WHEN DATEDIFF(td.Doc_expire_date, sysdate()) = (select send_5th from [sendmailnotice].m_email_control) THEN 'Y'
		ELSE 'N'
		END as isSendmail,
		CONCAT_WS(', ', td.Doc_mail_1, td.Doc_mail_2, td.Doc_mail_3) as contract_email
		, cs.CERTIFICATE_NAME, ds.DOCUMENT_NAME
	from [sendmailnotice].doc_transaction_d td
	inner join [sendmailnotice].m_certificate_std cs on cs.CERTIFICATE_ID = td.CERTIFICATE_ID AND cs.DOCUMENT_ID = td.DOCUMENT_ID
	inner join [sendmailnotice].m_document_std ds on ds.DOCUMENT_ID = cs.DOCUMENT_ID
	where td.deleted = 'N'
	and td.DOCUMENT_ID IN (1,3)
	and DATEDIFF(td.Doc_expire_date, sysdate()) in (30,23,16,9,2)	
}