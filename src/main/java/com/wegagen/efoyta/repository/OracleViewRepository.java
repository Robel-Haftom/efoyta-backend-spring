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
			"select ci.Cust_Name, scp.sex Gender, st.BRANCH_CODE, ci.branch_name , ci.MOBILE_NUMBER, st.cust_ac_no, st.cust_no " +
			"from sttms_cust_account st " +
			"inner join customer_information ci on st.CUST_NO = ci.CUST_NO " +
			"left join STTMS_CUST_PERSONAL scp on st.Cust_No = scp.customer_no";

	public List<OracleCustomerAccountInfoDto> findCustomerAccountInfos() {
		return oracleJdbcTemplate.query(CUSTOMER_ACCOUNT_INFO_SQL, new OracleCustomerAccountInfoRowMapper());
	}

	public Optional<OracleCustomerAccountInfoDto> findCustomerAccountInfoByAccountNumber(String accountNumber) {
		String sql = CUSTOMER_ACCOUNT_INFO_SQL + " where st.cust_ac_no = ?";
		List<OracleCustomerAccountInfoDto> rows = oracleJdbcTemplate.query(sql, new OracleCustomerAccountInfoRowMapper(), accountNumber);
		return rows.isEmpty() ? Optional.empty() : Optional.of(rows.get(0));
	}

	private static class OracleCustomerAccountInfoRowMapper implements RowMapper<OracleCustomerAccountInfoDto> {
		@Override
		public OracleCustomerAccountInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			return OracleCustomerAccountInfoDto.builder()
					.customerName(rs.getString("CUST_NAME"))
					.gender(rs.getString("GENDER"))
					.branchCode(rs.getString("BRANCH_CODE"))
					.branchName(rs.getString("BRANCH_NAME"))
					.mobileNumber(rs.getString("MOBILE_NUMBER"))
					.customerAccountNumber(rs.getString("CUST_AC_NO"))
					.customerNumber(rs.getString("CUST_NO"))
					.build();
		}
	}
}


