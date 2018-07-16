package com.shaunz.codegenerator.webform.generator.mybatis;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;

import com.shaunz.codegenerator.webform.generator.common.DBDriver;
import com.shaunz.codegenerator.webform.main.Generator;
import com.shaunz.codegenerator.webform.util.FileUtil;

public abstract class IMybatisGenerator implements Generator{
	Logger logger = Logger.getLogger(IMybatisGenerator.class);
	private Properties jdbcProperties;
	private JDBCConnectionConfiguration jdbcConnectionConfiguration;
	
	public IMybatisGenerator(){
		init();
	}
	
	private void init(){
		try(InputStream io = FileUtil.readFile("jdbc.properties");) {
			jdbcProperties = new Properties();
			jdbcProperties.load(io);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public String getDbType() throws NullPointerException{
		return jdbcProperties.getProperty("dbtype");
	}
	
	public String getDbDirverNm() throws NullPointerException{
		return jdbcProperties.getProperty("driver");
	}
	
	public String getDbUrl() throws NullPointerException{
		return jdbcProperties.getProperty("url");
	}
	
	public String getDbUserName() throws NullPointerException{
		return jdbcProperties.getProperty("username");
	}
	
	public String getDbPassword() throws NullPointerException{
		return jdbcProperties.getProperty("password");
	}
	
	public Connection getConnection(DBDriver driver){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(driver.getUrl(), driver.getUserName(), driver.getPassword());
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return connection;
	}

	@Override
	public boolean generate() throws Exception {
		Connection dbConnection = null;
		DBDriver driver = null;
		List<String> tableLst = new ArrayList<String>();
		try {
			driver = new DBDriver(this);
			dbConnection = getConnection(driver);
			tableLst = listAllTables(dbConnection);
			
			Configuration mybatisConfiguration = generateMybatisConfiguration(tableLst);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(mybatisConfiguration, null, null);
			myBatisGenerator.generate(null);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if(dbConnection != null){
				dbConnection.close();
			}
			driver = null;
		}
		return tableLst.size() > 0;
	}
	
	protected abstract List<String> listAllTables(Connection dbConnection);
	
	private Configuration generateMybatisConfiguration(List<String> tableLst){
		Configuration configuration = new Configuration();
		configuration.addClasspathEntry("/home/shaun/mybatis generator/postgresql-9.4-1200-jdbc41.jar");
		for (int i = 0; i < tableLst.size(); i++) {
			configuration.addContext(generateMybatisContext(tableLst.get(i)));
		}
		return configuration;
	}
	
	private JDBCConnectionConfiguration generateMybatisJDBCConnectionConfiguration(DBDriver driver){
		jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
		jdbcConnectionConfiguration.addProperty("driverClass", driver.getDirverNm());
		jdbcConnectionConfiguration.addProperty("connectionURL", driver.getUrl()+"?characterEncoding=utf8");
		jdbcConnectionConfiguration.addProperty("userId", driver.getUserName());
		jdbcConnectionConfiguration.addProperty("password", driver.getPassword());
		return jdbcConnectionConfiguration;
	}
	
	private Context generateMybatisContext(String tableNm){
		Context context = new Context(ModelType.FLAT);
		context.setId(tableNm);
		context.setTargetRuntime("MyBatis3");
		
		CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
		commentGeneratorConfiguration.addProperty("suppressAllComments", "true");
		commentGeneratorConfiguration.addProperty("suppressDate", "true");
		context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);
		
		context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
		
		JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
		javaTypeResolverConfiguration.addProperty("forceBigDecimals", "false");
		context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
		
		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
		javaModelGeneratorConfiguration.setTargetPackage("com.shaunz.webform.web.blogmap.entity");
		javaModelGeneratorConfiguration.setTargetProject("webForm");
		javaModelGeneratorConfiguration.addProperty("enableSubPackages", "true");
		javaModelGeneratorConfiguration.addProperty("trimStrings", "true");
		context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
		
		SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
		sqlMapGeneratorConfiguration.setTargetPackage("com.shaunz.webform.web.blogmap.dao.postgresql");
		sqlMapGeneratorConfiguration.setTargetProject("webForm");
		sqlMapGeneratorConfiguration.addProperty("enableSubPackages", "true");
		context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
		
		JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
		javaClientGeneratorConfiguration.setTargetPackage("com.shaunz.webform.web.blogmap.dao");
		javaClientGeneratorConfiguration.setTargetProject("webForm");
		javaClientGeneratorConfiguration.addProperty("enableSubPackages", "true");
		context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
		
		TableConfiguration tableConfiguration = new TableConfiguration(context);
		
		return context;
	}

}
