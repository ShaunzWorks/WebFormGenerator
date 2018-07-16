package com.shaunz.codegenerator.webform.generator.common;

import org.apache.log4j.Logger;

import com.shaunz.codegenerator.webform.generator.mybatis.IMybatisGenerator;

public class DBDriver{
	private Logger logger = Logger.getLogger(DBDriver.class);
	private Class<?> driver;
	private String dirverNm;
	private String dbtype;
	private String url;
	private String userName;
	private String password;
	private int initialSize;
	private int maxActive;
	private int maxIdle;
	private int minIdle;
	private int maxWait;
	
	public DBDriver(IMybatisGenerator generator){
		try {
			this.driver = Class.forName(generator.getDbDirverNm());
			dirverNm = generator.getDbDirverNm();
			this.dbtype = generator.getDbType();
			this.url = generator.getDbUrl();
			this.userName = generator.getDbUserName();
			this.password = generator.getDbPassword();
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());;
		}
	}

	public Class<?> getDriver() {
		return driver;
	}

	/**
	 * @return the logger
	 */
	public Logger getLogger() {
		return logger;
	}

	/**
	 * @param logger the logger to set
	 */
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	/**
	 * @return the dbtype
	 */
	public String getDbtype() {
		return dbtype;
	}

	/**
	 * @param dbtype the dbtype to set
	 */
	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the initialSize
	 */
	public int getInitialSize() {
		return initialSize;
	}

	/**
	 * @param initialSize the initialSize to set
	 */
	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}

	/**
	 * @return the maxActive
	 */
	public int getMaxActive() {
		return maxActive;
	}

	/**
	 * @param maxActive the maxActive to set
	 */
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	/**
	 * @return the maxIdle
	 */
	public int getMaxIdle() {
		return maxIdle;
	}

	/**
	 * @param maxIdle the maxIdle to set
	 */
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	/**
	 * @return the minIdle
	 */
	public int getMinIdle() {
		return minIdle;
	}

	/**
	 * @param minIdle the minIdle to set
	 */
	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	/**
	 * @return the maxWait
	 */
	public int getMaxWait() {
		return maxWait;
	}

	/**
	 * @param maxWait the maxWait to set
	 */
	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}

	/**
	 * @return the dirverNm
	 */
	public String getDirverNm() {
		return dirverNm;
	}

	/**
	 * @param dirverNm the dirverNm to set
	 */
	public void setDirverNm(String dirverNm) {
		this.dirverNm = dirverNm;
	}
	
}
