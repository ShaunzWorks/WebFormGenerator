package com.shaunz.codegenerator.webform.generator.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.shaunz.codegenerator.webform.util.SqlUtil;

public class IMybatisPostgreGenerator extends IMybatisGenerator{

	@Override
	protected List<String> listAllTables(Connection dbConnection) {
		List<String> tableLst = new ArrayList<String>();
		try {
			PreparedStatement statement = dbConnection.prepareStatement("select table_name from information_schema.tables where table_schema = 'public'");
			ResultSet resultSet = statement.executeQuery();
			List<Map<String, Object>> queryResult = SqlUtil.formatResultSet(resultSet);
			for (int i = 0; i < queryResult.size(); i++) {
				tableLst.add(queryResult.get(i).get("table_name").toString());
				logger.info(queryResult.get(i).get("table_name").toString());
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return tableLst;
	}

}
