package com.wegagen.efoyta.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MssqlConnectivityVerifier implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(MssqlConnectivityVerifier.class);

	private final JdbcTemplate mssqlJdbcTemplate;

	public MssqlConnectivityVerifier(@Qualifier("mssqlJdbcTemplate") JdbcTemplate mssqlJdbcTemplate) {
		this.mssqlJdbcTemplate = mssqlJdbcTemplate;
	}

	@Override
	public void run(ApplicationArguments args) {
		try {
			Integer one = mssqlJdbcTemplate.queryForObject("SELECT 1", Integer.class);
			log.info("MSSQL connectivity check passed: {}", one);
		} catch (Exception ex) {
			log.error("MSSQL connectivity check failed: {}", ex.getMessage());
		}
	}
}


