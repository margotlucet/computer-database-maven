package com.excilys.formation.projet.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;

import com.excilys.formation.projet.om.Company;
import com.excilys.formation.projet.om.Computer;

public class ComputerRowMapper implements RowMapper<Computer> {

	@Override
	public Computer mapRow(ResultSet rs, int line) throws SQLException {
		Computer c = new Computer.Builder()
				.id(rs.getLong(1))
				.name(rs.getString(2))
				.introduced(new DateTime(rs.getTimestamp(3)))
				.discontinued(new DateTime(rs.getTimestamp(4)))
				.company(
						new Company.Builder().id(rs.getLong(5))
								.name(rs.getString(6)).build()).build();
		return c;
	}

}
