package com.zhang.orm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zhang.dao.BasicDao;
import com.zhang.dao.impl.BasicDaoImpl;
import com.zhang.domain.Country;
import com.zhang.util.ConstantUtil;

public class SimpleORM {
	
	private static final BasicDao dao = new BasicDaoImpl();
	
	private static List<String> splitSelectCols(String sql) {
		int startPos = sql.indexOf("select") + "select".length();
		int endPos = sql.indexOf("from");
		if (startPos == -1 || endPos == -1) {
			return null;
		}
		String cols = sql.substring(startPos, endPos).trim();
		if (ConstantUtil.DEBUG) {
			System.err.println(cols);
		}
		String[] colList = cols.split(ConstantUtil.SPLITREGX);
		List<String> selectRecords = new ArrayList<String>();
		for (int i = 0; i < colList.length; ++i) {
			selectRecords.add(colList[i].trim());
			if (ConstantUtil.DEBUG) {
				System.err.println(colList[i].trim());
			}
		}
		return selectRecords;
	}
	
	public List<Object>  select(String sql, List<Object> params, Class classType) throws Exception {
		List<Object> result = new ArrayList<Object>();
		List<String> colList = splitSelectCols(sql);
		if (colList.size() == 1 && colList.get(0) == ConstantUtil.SELECTALL) {
			colList.clear();
			Field[] fields = classType.getFields();
			for (int i = 0; i < fields.length; ++i) {
				colList.add(fields[i].getName());
			}
		}
		List<Map<String, Object>> records = dao.select(sql, params, colList);
		if (records == null) {
			return null;
		}
		Iterator<Map<String, Object>> iter = records.iterator();
		while (iter.hasNext()) {
			Map<String, Object> record = iter.next();
			Object obj = classType.newInstance();
			for (Iterator<String> pos = colList.iterator(); pos.hasNext();) {
				String colName = pos.next();
				String methodColName = colName.substring(0,1).toUpperCase() + colName.substring(1);
				String methodName = ConstantUtil.METHODPREFIX + methodColName;
				Method[] methods = classType.getMethods();
				for (int i = 0; i < methods.length; ++i) {
			        if (methods[i].getName().equals(methodName)) {
			        	Class<?>[] parameterTypes = methods[i].getParameterTypes();
			        	Method method = classType.getMethod(methodName, parameterTypes);
			        	Constructor cons = null;
			            if (parameterTypes[0].isAssignableFrom(Timestamp.class)) {
			            	cons = parameterTypes[0].getConstructor(new Class[]{long.class});
			            	method.invoke(obj, new Object[]{cons.newInstance(((Timestamp)record.get(colName)).getTime())});
			            } else {
			            	cons = parameterTypes[0].getConstructor(new Class[]{String.class});
			            	method.invoke(obj, new Object[]{cons.newInstance(record.get(colName).toString())});
			            }
			        }
				}
			}
			result.add(obj);
		}
		return result;
	}
}
