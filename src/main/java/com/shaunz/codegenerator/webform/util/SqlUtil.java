package com.shaunz.codegenerator.webform.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


public class SqlUtil {
	private static Logger logger = Logger.getLogger(SqlUtil.class);
	public static List<Map<String, Object>> formatResultSet(ResultSet resultSet){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			while (resultSet.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
				int colCount = resultSet.getMetaData().getColumnCount();
				for (int i = 1; i <= colCount; i++) {
					String colNm = resultSet.getMetaData().getColumnName(i);
					row.put(colNm, resultSet.getObject(i));
				}
				list.add(row);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
}
