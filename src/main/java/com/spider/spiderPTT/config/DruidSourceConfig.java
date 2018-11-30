package com.spider.spiderPTT.config;


import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * druid 连接池配置

 */
@Configuration
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "jdbc")
public class DruidSourceConfig {
    private String url;
	
    private String username;
	
    private String password;
	
    private String driverClassName;
	
    private String filters;
	
    private int maxActive;
	
    private int initialSize;

    private int maxWait;
	
    private int minIdle;
	
    private int timeBetweenEvictionRunsMillis;

    private int minEvictableIdleTimeMillis;

    private boolean testWhileIdle;

    private String validationQuery;
	
    private boolean testOnBorrow;

    private boolean testOnReturn;

    private boolean poolPreparedStatements;
	
    private int maxOpenPreparedStatements;
    
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}

	public int getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public int getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public int getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public String getValidationQuery() {
		return validationQuery;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public boolean isTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public boolean isPoolPreparedStatements() {
		return poolPreparedStatements;
	}

	public void setPoolPreparedStatements(boolean poolPreparedStatements) {
		this.poolPreparedStatements = poolPreparedStatements;
	}

	public int getMaxOpenPreparedStatements() {
		return maxOpenPreparedStatements;
	}

	public void setMaxOpenPreparedStatements(int maxOpenPreparedStatements) {
		this.maxOpenPreparedStatements = maxOpenPreparedStatements;
	}

	@Bean
    public DruidDataSource dataSource() throws SQLException{
    	DruidDataSource dataSource=new DruidDataSource();
    	dataSource.setUrl(url);
    	dataSource.setUsername(username);
    	dataSource.setPassword(password);
    	dataSource.setDriverClassName(driverClassName);
    	dataSource.setFilters(filters);
    	dataSource.setMaxActive(maxActive);
    	dataSource.setInitialSize(initialSize);
    	dataSource.setMaxWait(maxWait);
    	dataSource.setMinIdle(minIdle);
    	dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
    	dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
    	dataSource.setValidationQuery(validationQuery);
    	dataSource.setTestWhileIdle(testWhileIdle);
    	dataSource.setTestOnBorrow(testOnBorrow);
    	dataSource.setTestOnReturn(testOnReturn);
    	dataSource.setPoolPreparedStatements(poolPreparedStatements);
    	dataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
    	return dataSource;
    }
}
