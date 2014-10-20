package com.zhang.util;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

public class PrepareParamsUtil {
	
	public static void finish(PreparedStatement ps, List<Object> params) throws SQLException {
		if (params == null || params.size() == 0) {
			return;
		}
		Iterator<Object> iter = params.iterator();
		int  pos = 1;
		while (iter.hasNext()) {
			Object obj = iter.next();
			if (obj instanceof Integer) {
				ps.setInt(pos, (Integer)obj);
			}
			else if (obj instanceof Long) {
				ps.setLong(pos, (Long)obj);
			}
			else if (obj instanceof Float) {
				ps.setFloat(pos, (Float)obj);
			}
			else if (obj instanceof Double) {
				ps.setDouble(pos, (Double)obj);
			}
			else if (obj instanceof String) {
				ps.setString(pos, (String)obj);
			}
			else if (obj instanceof Boolean) {
				ps.setBoolean(pos, (Boolean)obj);
			}
			else if (obj instanceof Byte) {
				ps.setByte(pos, (Byte)obj);
			}
			else if (obj instanceof Timestamp) {
				ps.setTimestamp(pos, (Timestamp)obj);
			}
			else if (obj instanceof Date) {
				ps.setDate(pos, (Date)obj);
			}
			else if (obj instanceof Time) {
				ps.setTime(pos, (Time)obj);
			}
			else {
				ps.setString(pos, obj.toString());
			}
			++pos;
		}
	}

}
