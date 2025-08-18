package com.wegagen.efoyta.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MssqlViewRepository {

	private final JdbcTemplate mssqlJdbcTemplate;

	public MssqlViewRepository(@Qualifier("mssqlJdbcTemplate") JdbcTemplate mssqlJdbcTemplate) {
		this.mssqlJdbcTemplate = mssqlJdbcTemplate;
	}

	public Optional<Map<String, Object>> findActiveUserByUsername(String username) {
		String sql = "SELECT TOP 1 * FROM [train_pro].[dbo].[View_DashboardLogin] " +
				"WHERE Username = ? AND (EmploymentStatus = '6' OR EmploymentStatus = '2')";
		List<Map<String, Object>> rows = mssqlJdbcTemplate.queryForList(sql, username);
		return rows.isEmpty() ? Optional.empty() : Optional.of(rows.get(0));
	}

	public long countActiveUsers() {
		String sql = "SELECT COUNT(1) FROM [train_pro].[dbo].[View_DashboardLogin] " +
				"WHERE (EmploymentStatus = '6' OR EmploymentStatus = '2')";
		Long count = mssqlJdbcTemplate.queryForObject(sql, Long.class);
		return count == null ? 0L : count;
	}
}


