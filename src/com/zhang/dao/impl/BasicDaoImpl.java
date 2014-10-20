package com.zhang.dao.impl;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;

import com.zhang.dao.BasicDao;
import com.zhang.util.ConstantUtil;
import com.zhang.util.DataSourceConfigUtil;
import com.zhang.util.DatabaseUtil;
import com.zhang.util.PrepareParamsUtil;

public class BasicDaoImpl implements BasicDao {
	
	private Connection getConnection() throws DocumentException, ClassNotFoundException, SQLException {
		Connection conn = null;
	    String classPath = this.getClass().getClassLoader().getResource("").getPath();
		DataSourceConfigUtil dscs = new DataSourceConfigUtil(classPath + ConstantUtil.datasourceXMLPath);
		Class.forName(dscs.getDriver());
	    String userName = dscs.getUsername();
	    String passWord = dscs.getPassword();
	    DatabaseUtil dbu = new DatabaseUtil();
	    String connectUrl = dbu.getConnectUrl(ConstantUtil.datasourceXMLPath);
		conn = DriverManager.getConnection(connectUrl, userName, passWord);
		return conn;
	}
	
	public List<Map<String, Object>> select(String sql, List<Object> params, List<String> colList) throws Exception {
	    Connection        conn = null;
	    PreparedStatement ps   = null;
		ResultSet         rs   = null;
		List<Map<String, Object>> records = null;
		try {
			conn = getConnection();
			if (conn != null) {
				ps = conn.prepareStatement(sql);
				PrepareParamsUtil.finish(ps, params);
				rs = ps.executeQuery();
				if (rs != null) {
					records = new ArrayList<Map<String, Object>>();
				}
				else {
					return null;
				}
				while (rs.next()) {
				    Map<String, Object> record = new HashMap<String, Object>();
				    Iterator<String> iter = colList.iterator();
				    while (iter.hasNext()) {
					    String colName = iter.next();
					    record.put(colName, rs.getObject(colName));
					}
				    records.add(record);
				} 
			}
		} catch (ClassNotFoundException err) {
			throw err;
		} catch (SQLException err) {
			throw err;
		} catch (Exception err) {
			throw err;	
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return records;
	}
}
