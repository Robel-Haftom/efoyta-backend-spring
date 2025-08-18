package com.wegagen.efoyta.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class MssqlReadOnlyConfig {

	@Bean
	@ConfigurationProperties("spring.datasource.secondary")
	public DataSourceProperties mssqlDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "mssqlDataSource")
	@ConfigurationProperties("spring.datasource.secondary.hikari")
	public DataSource mssqlDataSource() {
		return mssqlDataSourceProperties()
				.initializeDataSourceBuilder()
				.type(HikariDataSource.class)
				.build();
	}

	@Bean(name = "mssqlJdbcTemplate")
	public JdbcTemplate mssqlJdbcTemplate(@Qualifier("mssqlDataSource") DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}
}


