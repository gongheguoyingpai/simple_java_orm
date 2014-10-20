package com.zhang.util;

public class ConstantUtil {
	
	public static final boolean DEBUG = true;
	
	public static final String  SPLITREGX = ",\\s*";
	
	public static final String  SELECTALL = "*";
	
	public static final String  METHODPREFIX = "set";
	
	public static final String  datasourceXMLPath = "config/datasource.xml";
	
	public static final String  UNKNOWNSQLType = "UNKNOWN SQL TYPE";
	
	public class SQLType {
		public static final String mysql  = "MYSQL";
		public static final String oracle = "ORACLE";
		public static final String sqlServer = "SQLServer";
	}
    
	public class SQLDriverType {
		public final static String mysql     = "com.mysql.jdbc.Driver";
		public final static String sqlServer = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		public final static String oracle    = "oracle.jdbc.driver.OracleDriver";
	}
}
