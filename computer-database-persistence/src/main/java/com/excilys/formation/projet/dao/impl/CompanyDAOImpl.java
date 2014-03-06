package com.excilys.formation.projet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.excilys.formation.projet.dao.CompanyDAO;
import com.excilys.formation.projet.dao.DAOFactory;
import com.excilys.formation.projet.om.Company;
import com.jolbox.bonecp.BoneCPDataSource;

/**
 * DAO for Company
 * 
 * @author margot
 * 
 */
@Repository
public class CompanyDAOImpl implements CompanyDAO {
	static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImpl.class);
	@Autowired
	private BoneCPDataSource dataSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.projet.dao.impl.CompanyDAO#getCompanyById(long)
	 */

	@Override
	public Company getById(long id) {
		Company c = new Company.Builder().build();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection cn = null;
		try {

			cn = DataSourceUtils.getConnection(dataSource);
			stmt = cn.prepareStatement("SELECT name FROM company WHERE id=?;");
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			LOGGER.info("Query executed " + stmt);
			while (rs.next()) {
				c.setId(id);
				c.setName(rs.getString(1));
			}
			LOGGER.info("Company obtained", c);

		} catch (SQLException e) {
			LOGGER.error("SQL exception raised while trying to get a company by id");
			throw new RuntimeException();
		}

		finally {
			DAOFactory.INSTANCE_DAO.close(stmt);
			DAOFactory.INSTANCE_DAO.close(rs);
		}
		return c;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.formation.projet.dao.impl.CompanyDAO#getListeCompany()
	 */
	@Override
	public List<Company> getCompanies() {
		List<Company> li = new ArrayList<Company>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection cn = null;
		try {

			cn = DataSourceUtils.getConnection(dataSource);
			stmt = cn.prepareStatement("SELECT id, name FROM company;");
			rs = stmt.executeQuery();
			LOGGER.info("Query executed " + stmt);
			li = getResultList(rs);
			LOGGER.info("Companies obtained");
		} catch (SQLException e) {
			LOGGER.error("SQL exception raised while trying to get companies");
			throw new RuntimeException();
		}

		finally {
			DAOFactory.INSTANCE_DAO.close(stmt);
			DAOFactory.INSTANCE_DAO.close(rs);
		}

		return li;
	}

	public List<Company> getResultList(ResultSet rs) {
		List<Company> list = new ArrayList<Company>();
		Company companyCourant = null;
		try {
			while (rs.next()) {
				companyCourant = new Company.Builder().id(rs.getLong(1))
						.name(rs.getString(2)).build();
				list.add(companyCourant);
			}
			LOGGER.info("List of results obtained");
		} catch (SQLException e) {
			LOGGER.error("SQL exception raised while trying to get a list of results");
			throw new RuntimeException();
		}

		finally {
			DAOFactory.INSTANCE_DAO.close(rs);
		}
		return list;
	}
}
