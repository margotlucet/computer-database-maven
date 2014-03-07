package com.excilys.formation.projet.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.formation.projet.om.Company;

public class CompanyRowMapper implements RowMapper<Company> {

	@Override
	public Company mapRow(ResultSet rs, int line) throws SQLException {
		Company c = new Company.Builder().id(rs.getLong(1))
				.name(rs.getString(2)).build();
		return c;
	}

}
