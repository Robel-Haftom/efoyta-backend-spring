package com.wegagen.efoyta.repository;

import com.wegagen.efoyta.dto.response.OracleCustomerAccountInfoDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class OracleViewRepository {

	private final JdbcTemplate oracleJdbcTemplate;

	public OracleViewRepository(@Qualifier("oracleJdbcTemplate") JdbcTemplate oracleJdbcTemplate) {
		this.oracleJdbcTemplate = oracleJdbcTemplate;
	}

	private static final String CUSTOMER_ACCOUNT_INFO_SQL =
			"SELECT a.branch_code, b.branch_name, a.ac_desc, a.cust_no, a.cust_ac_no, c.mobile_number, c.sex " +
			"FROM sttms_cust_account a " +
			"INNER JOIN stvws_brn_dist_regn b ON a.branch_code = b.branch_code " +
			"INNER JOIN sttms_cust_personal c ON a.cust_no = c.customer_no";

//			"SELECT a.branch_code, b.branch_name, a.ac_desc, a.cust_no, a.cust_ac_no, c.mobile_number, " +
//			"c.sex FROM sttms_cust_account a" +
//			"INNER JOIN stvws_brn_dist_regn b ON a.branch_code = b.branch_code" +
//			"INNER JOIN sttms_cust_personal c ON a.cust_no = c.customer_no";

	public List<OracleCustomerAccountInfoDto> findCustomerAccountInfos() {
		return oracleJdbcTemplate.query(CUSTOMER_ACCOUNT_INFO_SQL, new OracleCustomerAccountInfoRowMapper());
	}

	public Optional<OracleCustomerAccountInfoDto> findCustomerAccountInfoByAccountNumber(String accountNumber) {
		String sql = CUSTOMER_ACCOUNT_INFO_SQL + " where a.cust_ac_no = ?";
		List<OracleCustomerAccountInfoDto> rows = oracleJdbcTemplate.query(sql, new OracleCustomerAccountInfoRowMapper(), accountNumber);
		return rows.isEmpty() ? Optional.empty() : Optional.of(rows.get(0));
	}

	private static class OracleCustomerAccountInfoRowMapper implements RowMapper<OracleCustomerAccountInfoDto> {
		@Override
		public OracleCustomerAccountInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			return OracleCustomerAccountInfoDto.builder()
					.customerName(rs.getString("AC_DESC"))
					.gender(rs.getString("SEX"))
					.branchCode(rs.getString("BRANCH_CODE"))
					.branchName(rs.getString("BRANCH_NAME"))
					.mobileNumber(rs.getString("MOBILE_NUMBER"))
					.customerAccountNumber(rs.getString("CUST_AC_NO"))
					.customerNumber(rs.getString("CUST_NO"))
					.build();
		}
	}
}


