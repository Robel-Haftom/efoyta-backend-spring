package com.wegagen.efoyta.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class OracleReadOnlyConfig {

	@Bean
	@ConfigurationProperties("spring.datasource.third")
	public DataSourceProperties oracleDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "oracleDataSource")
	@ConfigurationProperties("spring.datasource.third.hikari")
	public DataSource oracleDataSource() {
		return oracleDataSourceProperties()
				.initializeDataSourceBuilder()
				.type(HikariDataSource.class)
				.build();
	}

	@Bean(name = "oracleJdbcTemplate")
	public JdbcTemplate oracleJdbcTemplate(@Qualifier("oracleDataSource") DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}
}


