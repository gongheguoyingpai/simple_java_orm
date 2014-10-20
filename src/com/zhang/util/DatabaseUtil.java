package com.zhang.util;

public class DatabaseUtil {
	
	public String getConnectUrl(String path) {
		String connectUrl = "jdbc:";
		try {
			String configPath = this.getClass().getClassLoader().getResource("").getPath() + 
					            path;
		    DataSourceConfigUtil dscs = new DataSourceConfigUtil(configPath);
		    String  url    = dscs.getUrl();
		    String  dbName = dscs.getDBName();
		    Integer port   = dscs.getPort();
		    String  type   = dscs.getType();
		    if (type != null && type.equals(ConstantUtil.SQLType.mysql)) {
		    	connectUrl += "mysql://" + url + ":" + port + "/" + dbName;
		    }
		    else if (type != null && type.equals(ConstantUtil.SQLType.oracle)) {
		    	connectUrl += "oracle:thin:@//" + url + ":" + port + "/" + dbName;
		    }
		    else if (type != null && type.equals(ConstantUtil.SQLType.sqlServer)) {
		    	connectUrl += "sqlserver://" + url + ":" + port + "; DatabaseName=" + dbName;
		    }
		    else {
		    	return ConstantUtil.UNKNOWNSQLType;
		    }
		} catch (Exception e) {
			connectUrl = null;
		}
		return connectUrl;
	}
}
