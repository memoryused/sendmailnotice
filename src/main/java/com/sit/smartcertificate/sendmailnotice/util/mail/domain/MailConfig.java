package com.sit.smartcertificate.sendmailnotice.util.mail.domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class MailConfig {
	
	private Map<String,String> mapProperties = new HashMap<String, String>();
	
	private Properties mapPropertiesMail = new Properties();
	
	private String email;
	private String password;

	private  boolean debug=true; 
	private  String encoding="UTF-8";
	private  String contenttype="text/html;charset=UTF-8";
	private  String multipart="related";
	
	
	public MailConfig(){
		debug = true;
		encoding = "UTF-8";
		contenttype = "text/html;charset=UTF-8";
		multipart = "related";
	}
	
	public void initMailConfig(String path) throws Exception{
//    	List<String> data  = FileManagerUtil.readDatas(path);
//    	for(String d : data){
//    		System.out.println(d);
//    		if(d!=null && !d.equals("")){
//	    		String[] tr  = d.split("=");
//	    		if(tr.length ==2){
//	    			if(tr[0] != null){
//			    		System.out.println(tr[0] + "||" + tr[1]);
//			    		mapProperties.put(tr[0], tr[1]);
//	    			}
//	    		}
//    		}
//    	}
    	
    	
    	Properties prop = new Properties();
    	InputStream input = null;
		try {
			input = new FileInputStream(path);
			prop.load(input);
			for (Object propKey : prop.keySet()) {
				mapProperties.put(propKey.toString(), prop.get(propKey).toString());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
    	
	}
	
	public void initMailConfigProp(String path) throws Exception{
		Properties prop = new Properties();
		InputStream input = null;
		try {

			input = new FileInputStream(path);
			
			prop = new Properties();

			// load a properties file
			mapPropertiesMail.load(input);
//			prop.
//			for (Object propKey : prop.keySet()) {
//				mapProperties.put(propKey.toString(), prop.get(propKey));
//			}
//			
			// mapProperties.put
			// mapProperties.putAll(prop);


		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Map<String, String> getMapProperties() {
		return mapProperties;
	}

	public void setMapProperties(Map<String, String> mapProperties) {
		this.mapProperties = mapProperties;
	}

	public Properties getMapPropertiesMail() {
		return mapPropertiesMail;
	}

	public void setMapPropertiesMail(Properties mapPropertiesMail) {
		this.mapPropertiesMail = mapPropertiesMail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getContenttype() {
		return contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public String getMultipart() {
		return multipart;
	}

	public void setMultipart(String multipart) {
		this.multipart = multipart;
	}

}
