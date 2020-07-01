package com.sit.smartcertificate.sendmailnotice.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sit.smartcertificate.sendmailnotice.util.mail.MailUtil;
import com.sit.smartcertificate.sendmailnotice.util.mail.domain.MailConfig;
import com.sit.smartcertificate.sendmailnotice.util.mail.domain.MailDetail;

public class TestMail {
	public static MailConfig mailConfig;
	public static void main(String[] args) {
		try{
			String path = "D:\\SomapaIT\\SIT\\ws_certification\\sendmailnotice\\mail_google.properties"; 	
            
            mailConfig = new MailConfig();
            mailConfig.initMailConfig(path);
            
            
            MailDetail mailDetail = new MailDetail();
            
            //mailConfig.setEmail("perapol.p@somapait.com");
           // mailConfig.setPassword("sss@1");
            
            
            /**************************************************/
            
            
            
            /************test server mail gmail************/
            mailConfig.setEmail("admin.cert@carrierportal.info");
            mailConfig.setPassword("w,jwfh8y[2");
            
            /**************************************************/
            
            
            /*************test server mail apps**************/
            //mailConfig.setEmail("appsservice@thai-apps.com");
            //mailConfig.setPassword("BlueberryCheesecake2015");
            
            /******************************************************/
            
            //List<File> lstFile = new ArrayList<File>();
            
            //lstFile.add(new File("d:/test.txt"));
            //lstFile.add(new File("d:/test2.txt"));
            //mailDetail.setLstFile(lstFile);
            
          
            
            String[] receiver = new String[1];
            receiver[0] = "anusorn.l@somapait.com";
            
            //String[] receiverCC = new String[1];
            //receiverCC[0] = "perapol111@gmail.com";
            
            mailDetail.setMailReciver(receiver);
            
            mailDetail.setMessage("<HTML><header><title>h</title></header><font color='red'><b>HELLO Anusorn.l</b></font></HTML>");
            mailDetail.setSubject("test data");


            
        	MailUtil.sendMail(mailConfig, mailDetail);

        }catch(Exception ex){
        	ex.printStackTrace();
        }

	}

}
