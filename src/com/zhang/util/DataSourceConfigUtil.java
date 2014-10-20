package com.zhang.util;

import org.dom4j.DocumentException;

public class DataSourceConfigUtil {
	
    private String  url    = null;
    private String  dbName = null;
    private Integer port   = null;
    private String  type   = null;
    private String  username = null;
    private String  password = null;
    private String  driver = null;
    
    public DataSourceConfigUtil(String path) throws DocumentException {
        XMLUtil xml = new XMLUtil(path);
        this.url     = xml.getElementText("url");
        this.dbName  = xml.getElementText("dbname");
        String port  = xml.getElementText("port");
        this.port = Integer.parseInt(port);
        this.type = xml.getElementText("type");
        this.username = xml.getElementText("username");
        this.password = xml.getElementText("password");
        this.driver = xml.getElementText("driver");
    }
    
    public String getUrl() {
    	return url;
    }
    
    public String getDBName() {
    	return dbName;
    }
    
    public Integer getPort() {
    	return port;
    }
    
    public String getType() {
    	return type;
    }
    
    public String getUsername() {
    	return username;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public String getDriver() {
    	return driver;
    }
}
