package com.wegagen.efoyta.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class OracleConnectivityVerifier implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(OracleConnectivityVerifier.class);

	private final JdbcTemplate oracleJdbcTemplate;

	public OracleConnectivityVerifier(@Qualifier("oracleJdbcTemplate") JdbcTemplate oracleJdbcTemplate) {
		this.oracleJdbcTemplate = oracleJdbcTemplate;
	}

	@Override
	public void run(ApplicationArguments args) {
		try {
			Integer one = oracleJdbcTemplate.queryForObject("SELECT 1 FROM dual", Integer.class);
			log.info("Oracle connectivity check passed: {}", one);
		} catch (Exception ex) {
			log.error("Oracle connectivity check failed: {}", ex.getMessage());
		}
	}
}


