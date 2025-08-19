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
							String sqlUserName = "SELECT TOP 1 * FROM [train_pro].[dbo].[view_info_dist_lending] " +
				"WHERE EmploymentStatus IN (6,2) AND LOWER(RTRIM(LTRIM([UserName]))) = LOWER(RTRIM(LTRIM(?)))";
		List<Map<String, Object>> rows = mssqlJdbcTemplate.queryForList(sqlUserName, username);
		if (!rows.isEmpty()) {
			return Optional.of(rows.get(0));
		}

		// Fallback to alternate column name if present in view
		String sqlUsernameAlt = "SELECT TOP 1 * FROM [train_pro].[dbo].[view_info_dist_lending] " +
				"WHERE EmploymentStatus IN (6,2) AND LOWER(RTRIM(LTRIM([Username]))) = LOWER(RTRIM(LTRIM(?)))";
		List<Map<String, Object>> rowsAlt = mssqlJdbcTemplate.queryForList(sqlUsernameAlt, username);
		return rowsAlt.isEmpty() ? Optional.empty() : Optional.of(rowsAlt.get(0));
	}

	public long countActiveUsers() {
		String sql = "SELECT COUNT(1) FROM [train_pro].[dbo].[view_info_dist_lending] " +
				"WHERE (EmploymentStatus = '6' OR EmploymentStatus = '2')";
		Long count = mssqlJdbcTemplate.queryForObject(sql, Long.class);
		return count == null ? 0L : count;
	}
}


