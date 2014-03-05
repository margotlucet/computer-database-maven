package com.excilys.formation.projet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.excilys.formation.projet.dao.ComputerDAO;
import com.excilys.formation.projet.dao.DAOFactory;
import com.excilys.formation.projet.om.Company;
import com.excilys.formation.projet.om.Computer;
import com.excilys.formation.projet.util.Constant;
import com.excilys.formation.projet.wrapper.PageWrapper;
import com.jolbox.bonecp.BoneCPDataSource;

@Repository
public class ComputerDAOImpl implements ComputerDAO {
	static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAOImpl.class);
	@Autowired
	private BoneCPDataSource dataSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.projet.dao.impl.ComputerDAO#addComputer(com.excilys
	 * .formation.projet.om.Computer)
	 */
	@Override
	public long add(Computer c) {
		long id = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection cn = null;
		long idCompany;
		try {
			cn = DataSourceUtils.getConnection(dataSource);
			stmt = cn.prepareStatement(
					"INSERT INTO computer (name, introduced, "
							+ "discontinued, company_id) VALUES (?, ?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, c.getName());

			if (c.getIntroduced() != null)
				stmt.setTimestamp(2, new Timestamp(c.getIntroduced()
						.getMillis()));
			else
				stmt.setTimestamp(2, null);
			if (c.getDiscontinued() != null)
				stmt.setTimestamp(3, new Timestamp(c.getDiscontinued()
						.getMillis()));
			else
				stmt.setTimestamp(3, null);
			idCompany = c.getCompany().getId();
			if (idCompany != 0)
				stmt.setLong(4, idCompany);
			else
				stmt.setNull(4, Types.BIGINT);
			System.out.println(stmt);
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			while (rs.next()) {
				id = rs.getLong(1);
			}
			LOGGER.debug("Generated computer id : " + id);
			LOGGER.info("Insert executed" + stmt);
			LOGGER.info("Computer added");
		} catch (SQLException e) {
			LOGGER.error("SQL Exception raised while trying to add a computer");
			throw new RuntimeException();
		} finally {
			DAOFactory.INSTANCE_DAO.close(stmt);
			DAOFactory.INSTANCE_DAO.close(rs);
		}
		return id;

	}

	public List<Computer> getResultList(ResultSet rs) {
		List<Computer> list = new ArrayList<Computer>();
		Computer computerCourant = null;
		long id = 0;
		try {
			while (rs.next()) {
				computerCourant = new Computer.Builder().id(rs.getLong(1))
						.name(rs.getString(2))
						.introduced(new DateTime(rs.getTimestamp(3)))
						.discontinued(new DateTime(rs.getTimestamp(4))).build();
				id = rs.getLong(5);
				if (id != 0) {
					computerCourant.setCompany(new Company.Builder().id(id)
							.name(rs.getString(6)).build());
				} else {
					computerCourant.setCompany(new Company.Builder().build());
				}
				list.add(computerCourant);
				LOGGER.info("List of results obtained");
			}
		} catch (SQLException e) {
			LOGGER.error("SQL Exception raised while trying to get a result list");
			throw new RuntimeException();
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.projet.dao.impl.ComputerDAO#getComputerById(long)
	 */
	@Override
	public Computer getById(long id) {
		Computer computer = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection cn = null;
		List<Computer> li = null;
		try {
			cn = DataSourceUtils.getConnection(dataSource);
			LOGGER.debug("My connection : " + cn);
			stmt = cn
					.prepareStatement("SELECT computer.id, computer.name, introduced, discontinued, company_id, "
							+ "company.name FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id = ? ;");
			LOGGER.debug("Statement Prepared " + stmt);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			LOGGER.debug("Query executed " + stmt);
			li = this.getResultList(rs);
			computer = li.get(0);
		} catch (SQLException e) {
			LOGGER.error("SQL Exception raised while trying to get a computer by id");
			throw new RuntimeException();
		} finally {
			DAOFactory.INSTANCE_DAO.close(stmt);
		}

		return computer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.projet.dao.impl.ComputerDAO#deleteComputer(long)
	 */
	@Override
	public void delete(long id) {
		PreparedStatement stmt = null;
		Connection cn = null;
		try {
			cn = DataSourceUtils.getConnection(dataSource);
			stmt = cn.prepareStatement("DELETE FROM computer WHERE id=?");
			stmt.setLong(1, id);
			stmt.executeUpdate();
			LOGGER.info("Update executed" + stmt);
		} catch (SQLException e) {
			LOGGER.error("SQL Exception raised while trying to delete a computer");
			throw new RuntimeException();
			/*
			 * try { cn.rollback(); } catch (SQLException e1) {
			 * LOGGER.error("SQL exception raised while trying to rollback");
			 * e1.printStackTrace(); }
			 * LOGGER.error("SQL exception raised while trying to delete a computer"
			 * ); e.printStackTrace();
			 */
		}

		finally {
			DAOFactory.INSTANCE_DAO.close(stmt);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.projet.dao.impl.ComputerDAO#editComputer(com.excilys
	 * .formation.projet.om.Computer)
	 */
	@Override
	public void update(Computer c) {
		PreparedStatement stmt = null;
		Connection cn = null;
		long idCompany = 0;

		try {
			cn = DataSourceUtils.getConnection(dataSource);
			stmt = cn
					.prepareStatement("UPDATE computer SET name=?, introduced=?, discontinued=?, "
							+ "company_id=? WHERE id=?;");
			stmt.setString(1, c.getName());
			if (c.getIntroduced() != null)
				stmt.setTimestamp(2, new Timestamp(c.getIntroduced()
						.getMillis()));
			else
				stmt.setTimestamp(2, null);
			if (c.getDiscontinued() != null)
				stmt.setTimestamp(3, new Timestamp(c.getDiscontinued()
						.getMillis()));
			else
				stmt.setTimestamp(3, null);
			idCompany = c.getCompany().getId();
			if (idCompany != 0)
				stmt.setLong(4, idCompany);
			else
				stmt.setNull(4, Types.BIGINT);
			stmt.setLong(5, c.getId());
			stmt.executeUpdate();
			LOGGER.info("Update executed" + stmt);
		} catch (SQLException e) {
			LOGGER.error("SQL Exception raised while trying to update a computer");
			throw new RuntimeException();
			/*
			 * try { cn.rollback(); } catch (SQLException e1) {
			 * LOGGER.error("SQL exception raised while trying to rollback");
			 * e1.printStackTrace(); }
			 * LOGGER.error("SQL exception raised while trying to update a computer"
			 * ); e.printStackTrace();
			 */
		}

		finally {
			DAOFactory.INSTANCE_DAO.close(stmt);

		}

	}

	public int getAmount() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection cn = null;
		int number = 0;
		try {
			cn = DataSourceUtils.getConnection(dataSource);
			stmt = cn.prepareStatement("SELECT COUNT(*) FROM computer;");
			rs = stmt.executeQuery();
			LOGGER.debug("Query executed" + stmt);
			while (rs.next()) {
				number = rs.getInt(1);
			}

		} catch (SQLException e) {
			LOGGER.error("SQL exception raised while trying to get the amount of computers");
			throw new RuntimeException();
		} finally {
			DAOFactory.INSTANCE_DAO.close(rs);
			DAOFactory.INSTANCE_DAO.close(stmt);
		}
		return number;
	}

	public int getAmount(String search) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection cn = null;
		int number = 0;
		try {
			cn = DataSourceUtils.getConnection(dataSource);
			stmt = cn
					.prepareStatement("SELECT COUNT(*) FROM computer c LEFT JOIN company b ON c.company_id = b.id WHERE "
							+ "c.name LIKE ? OR b.name LIKE ?;");
			stmt.setString(1, "%" + search + "%");
			stmt.setString(2, "%" + search + "%");
			rs = stmt.executeQuery();
			LOGGER.debug("Query executed" + stmt);
			while (rs.next()) {
				number = rs.getInt(1);
			}

		} catch (SQLException e) {
			LOGGER.error("SQL exception raised while trying to get the amount of computers");
			throw new RuntimeException();
		} finally {
			DAOFactory.INSTANCE_DAO.close(stmt);
			DAOFactory.INSTANCE_DAO.close(rs);
		}
		return number;
	}

	@Override
	public PageWrapper<Computer> getComputers(int limit, int offset,
			String orderBy, String orderDirection) {
		PageWrapper<Computer> pw = new PageWrapper<Computer>();
		PreparedStatement stmt = null;
		Connection cn = null;
		ResultSet rs = null;
		List<Computer> li = null;
		int resultCount = 0;
		try {
			cn = DataSourceUtils.getConnection(dataSource);
			resultCount = this.getAmount();
			switch (orderBy) {
			case Constant.COMPANY:
				if (orderDirection.equals(Constant.DESC))
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, introduced, discontinued, company_id, "
									+ "company.name FROM computer  LEFT JOIN company ON computer.company_id = company.id "
									+ "ORDER BY company.name DESC LIMIT ? OFFSET ?;");
				else
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, introduced, discontinued, company_id, "
									+ "company.name FROM computer  LEFT JOIN company ON computer.company_id = company.id "
									+ "ORDER BY company.name ASC LIMIT ? OFFSET ?;");
				break;

			case Constant.NAME:
				if (orderDirection.equals(Constant.DESC))
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, introduced, discontinued, company_id, "
									+ "company.name FROM computer  LEFT JOIN company ON computer.company_id = company.id "
									+ "ORDER BY computer.name DESC LIMIT ? OFFSET ?;");
				else
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, introduced, discontinued, company_id, "
									+ "company.name FROM computer  LEFT JOIN company ON computer.company_id = company.id "
									+ "ORDER BY computer.name ASC LIMIT ? OFFSET ?;");
				break;

			case Constant.INTRODUCED:
				if (orderDirection.equals(Constant.DESC))
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, introduced, discontinued, company_id, "
									+ "company.name FROM computer  LEFT JOIN company ON computer.company_id = company.id "
									+ "ORDER BY computer.introduced DESC LIMIT ? OFFSET ?;");
				else
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, introduced, discontinued, company_id, "
									+ "company.name FROM computer  LEFT JOIN company ON computer.company_id = company.id "
									+ "ORDER BY computer.introduced ASC LIMIT ? OFFSET ?;");
				break;

			case Constant.DISCONTINUED:
				if (orderDirection.equals(Constant.DESC))
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, introduced, discontinued, company_id, "
									+ "company.name FROM computer  LEFT JOIN company ON computer.company_id = company.id "
									+ "ORDER BY computer.discontinued DESC LIMIT ? OFFSET ?;");
				else
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, introduced, discontinued, company_id, "
									+ "company.name FROM computer  LEFT JOIN company ON computer.company_id = company.id "
									+ "ORDER BY computer.discontinued ASC LIMIT ? OFFSET ?;");
				break;

			default:

				if (orderDirection.equals(Constant.DESC))
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, introduced, discontinued, company_id, "
									+ "company.name FROM computer  LEFT JOIN company ON computer.company_id = company.id "
									+ "ORDER BY computer.name DESC LIMIT ? OFFSET ?;");
				else
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, introduced, discontinued, company_id, "
									+ "company.name FROM computer  LEFT JOIN company ON computer.company_id = company.id "
									+ "ORDER BY computer.discontinued ASC LIMIT ? OFFSET ?;");
				break;

			}
			stmt.setInt(1, limit);
			stmt.setInt(2, offset);
			rs = stmt.executeQuery();
			LOGGER.debug("Query executed" + stmt);
			li = this.getResultList(rs);
			pw.setCurrPage((offset / limit) + 1);
			pw.setResultsPerPage(limit);
			pw.setResultCount(resultCount);
			pw.setElementList(li);
			pw.setCurrentResultCount(li.size());
			if (resultCount % limit != 0)
				pw.setPageCount((resultCount / limit) + 1);
			else
				pw.setPageCount(resultCount / limit);

		} catch (SQLException e) {
			LOGGER.error("SQL exception raised while trying to get a list of computers");
			throw new RuntimeException();
		} finally {
			DAOFactory.INSTANCE_DAO.close(rs);
			DAOFactory.INSTANCE_DAO.close(stmt);
		}
		return pw;
	}

	@Override
	public PageWrapper<Computer> getComputers(int limit, int offset,
			String orderBy, String orderDirection, String search) {
		PageWrapper<Computer> pw = new PageWrapper<Computer>();
		List<Computer> li = null;
		PreparedStatement stmt = null;
		Connection cn = null;
		ResultSet rs = null;
		int resultCount = 0;
		try {
			cn = DataSourceUtils.getConnection(dataSource);
			resultCount = this.getAmount(search);
			LOGGER.debug("Result count : " + resultCount);
			switch (orderBy) {
			case Constant.COMPANY:
				if (orderDirection.equals(Constant.DESC))
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, computer.introduced, "
									+ "computer.discontinued, company.id, company.name  FROM computer LEFT JOIN "
									+ "company ON computer.company_id = company.id WHERE computer.name LIKE ? "
									+ "OR company.name LIKE ? ORDER BY company.name DESC LIMIT ? OFFSET ? ;");
				else
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, computer.introduced, "
									+ "computer.discontinued, company.id, company.name  FROM computer LEFT JOIN "
									+ "company ON computer.company_id = company.id WHERE computer.name LEFT LIKE ? "
									+ "OR company.name LIKE ? ORDER BY company.name ASC LIMIT ? OFFSET ? ;");
				break;

			case Constant.NAME:
				if (orderDirection.equals(Constant.DESC))
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, computer.introduced, "
									+ "computer.discontinued, company.id, company.name  FROM computer LEFT JOIN "
									+ "company ON computer.company_id = company.id WHERE computer.name LIKE ? "
									+ "OR company.name LIKE ? ORDER BY computer.name DESC LIMIT ? OFFSET ? ;");
				else
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, computer.introduced, "
									+ "computer.discontinued, company.id, company.name  FROM computer LEFT JOIN "
									+ "company ON computer.company_id = company.id WHERE computer.name LIKE ? "
									+ "OR company.name LIKE ? ORDER BY computer.name ASC LIMIT ? OFFSET ? ;");
				break;

			case Constant.INTRODUCED:
				if (orderDirection.equals(Constant.DESC))
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, computer.introduced, "
									+ "computer.discontinued, company.id, company.name  FROM computer LEFT JOIN "
									+ "company ON computer.company_id = company.id WHERE computer.name LIKE ? "
									+ "OR company.name LIKE ? ORDER BY computer.introduced DESC LIMIT ? OFFSET ? ;");
				else
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, computer.introduced, "
									+ "computer.discontinued, company.id, company.name  FROM computer LEFT JOIN "
									+ "company ON computer.company_id = company.id WHERE computer.name LIKE ? "
									+ "OR company.name LIKE ? ORDER BY computer.introduced ASC LIMIT ? OFFSET ? ;");
				break;

			case Constant.DISCONTINUED:
				if (orderDirection.equals(Constant.DESC))
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, computer.introduced, "
									+ "computer.discontinued, company.id, company.name  FROM computer LEFT JOIN "
									+ "company ON computer.company_id = company.id WHERE computer.name LIKE ? "
									+ "OR company.name LIKE ? ORDER BY computer.discontinued DESC LIMIT ? OFFSET ? ;");
				else
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, computer.introduced, "
									+ "computer.discontinued, company.id, company.name  FROM computer LEFT JOIN "
									+ "company ON computer.company_id = company.id WHERE computer.name LIKE ? "
									+ "OR company.name LIKE ? ORDER BY computer.discontinued ASC LIMIT ? OFFSET ? ;");
				break;

			default:

				if (orderDirection.equals(Constant.DESC))
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, computer.introduced, "
									+ "computer.discontinued, company.id, company.name  FROM computer LEFT JOIN "
									+ "company ON computer.company_id = company.id WHERE computer.name LIKE ? "
									+ "OR company.name LIKE ? ORDER BY computer.name DESC LIMIT ? OFFSET ? ;");
				else
					stmt = cn
							.prepareStatement("SELECT computer.id, computer.name, computer.introduced, "
									+ "computer.discontinued, company.id, company.name  FROM computer LEFT JOIN "
									+ "company ON computer.company_id = company.id WHERE computer.name LIKE ? "
									+ "OR company.name LIKE ? ORDER BY computer.name ASC LIMIT ? OFFSET ? ;");
				break;

			}
			stmt.setString(1, "%" + search + "%");
			stmt.setString(2, "%" + search + "%");
			stmt.setInt(3, limit);
			stmt.setInt(4, offset);

			rs = stmt.executeQuery();
			LOGGER.debug("Query executed" + stmt);
			li = this.getResultList(rs);
			pw.setCurrPage((offset / limit) + 1);
			pw.setResultsPerPage(limit);
			pw.setResultCount(resultCount);
			pw.setElementList(li);
			if (resultCount % limit != 0)
				pw.setPageCount((resultCount / limit) + 1);
			else
				pw.setPageCount(resultCount / limit);
		} catch (SQLException e) {
			LOGGER.error("SQL exception raised while trying to get a list of computers");
			throw new RuntimeException();
		} finally {
			DAOFactory.INSTANCE_DAO.close(stmt);
		}
		return pw;
	}

}
