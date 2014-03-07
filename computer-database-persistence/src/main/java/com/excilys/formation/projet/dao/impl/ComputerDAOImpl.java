package com.excilys.formation.projet.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.formation.projet.dao.ComputerDAO;
import com.excilys.formation.projet.dao.ConstantSQL;
import com.excilys.formation.projet.jdbc.ComputerRowMapper;
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
		long idCompany;
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("name", c.getName());
		if (c.getIntroduced() != null)
			paramMap.addValue("introduced", new Timestamp(c.getIntroduced()
					.getMillis()));
		else
			paramMap.addValue("introduced", null);
		if (c.getDiscontinued() != null)
			paramMap.addValue("discontinued", new Timestamp(c.getDiscontinued()
					.getMillis()));
		else
			paramMap.addValue("discontinued", null);
		idCompany = c.getCompany().getId();
		if (idCompany != 0)
			paramMap.addValue("companyId", idCompany);
		else
			paramMap.addValue("companyId", null);
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(
				dataSource);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		npjt.update(ConstantSQL.CREATE, paramMap, keyHolder,
				new String[] { "id" });

		id = keyHolder.getKey().longValue();
		LOGGER.debug("Generated computer id : " + id);
		LOGGER.info("Computer added");
		return id;

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
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("id", id);
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(
				dataSource);
		computer = (Computer) npjt.queryForObject(ConstantSQL.SELECT_BY_ID,
				paramMap, new ComputerRowMapper());
		LOGGER.debug("Computer retrieved");
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
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("id", id);
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(
				dataSource);
		npjt.update(ConstantSQL.DELETE, paramMap);
		LOGGER.debug("Computer deleted with id : " + id);
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
		long idCompany = 0;

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("name", c.getName());
		paramMap.addValue("id", c.getId());
		if (c.getIntroduced() != null)
			paramMap.addValue("introduced", new Timestamp(c.getIntroduced()
					.getMillis()));
		else
			paramMap.addValue("introduced", null);
		if (c.getDiscontinued() != null)
			paramMap.addValue("discontinued", new Timestamp(c.getDiscontinued()
					.getMillis()));
		else
			paramMap.addValue("discontinued", null);
		idCompany = c.getCompany().getId();
		if (idCompany != 0)
			paramMap.addValue("companyId", idCompany);
		else
			paramMap.addValue("companyId", null);

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(
				dataSource);
		npjt.update(ConstantSQL.UPDATE, paramMap);

		LOGGER.debug("Computer updated");

	}

	public int getAmount() {
		int number = 0;
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(
				dataSource);
		number = npjt.queryForObject(ConstantSQL.AMOUNT, paramMap,
				Integer.class);
		LOGGER.debug("Amount retrieved");
		return number;
	}

	public int getAmount(String search) {

		int number = 0;
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("search", "%" + search + "%");
		LOGGER.debug("---------------------SEARCH-------------------- : "
				+ search);
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(
				dataSource);
		number = npjt.queryForObject(ConstantSQL.AMOUNT_SEARCH, paramMap,
				Integer.class);
		return number;
	}

	@Override
	public PageWrapper<Computer> getComputers(int limit, int offset,
			String orderBy, String orderDirection) {
		PageWrapper<Computer> pw = new PageWrapper<Computer>();
		List<Computer> li = null;
		int resultCount = 0;

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("limit", limit);
		paramMap.addValue("offset", offset);

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(
				dataSource);

		resultCount = this.getAmount();
		switch (orderBy) {
		case Constant.COMPANY:
			if (orderDirection.equals(Constant.DESC))
				li = (List<Computer>) npjt.query(ConstantSQL.COMPUTERS_CD,
						paramMap, new ComputerRowMapper());
			else
				li = (List<Computer>) npjt.query(ConstantSQL.COMPUTERS_CA,
						paramMap, new ComputerRowMapper());
			break;

		case Constant.NAME:
			if (orderDirection.equals(Constant.DESC))
				li = (List<Computer>) npjt.query(ConstantSQL.COMPUTERS_ND,
						paramMap, new ComputerRowMapper());
			else
				li = (List<Computer>) npjt.query(ConstantSQL.COMPUTERS_NA,
						paramMap, new ComputerRowMapper());
			break;

		case Constant.INTRODUCED:
			if (orderDirection.equals(Constant.DESC))
				li = (List<Computer>) npjt.query(ConstantSQL.COMPUTERS_ID,
						paramMap, new ComputerRowMapper());
			else
				li = (List<Computer>) npjt.query(ConstantSQL.COMPUTERS_IA,
						paramMap, new ComputerRowMapper());
			break;

		case Constant.DISCONTINUED:
			if (orderDirection.equals(Constant.DESC))
				li = (List<Computer>) npjt.query(ConstantSQL.COMPUTERS_DD,
						paramMap, new ComputerRowMapper());
			else
				li = (List<Computer>) npjt.query(ConstantSQL.COMPUTERS_DA,
						paramMap, new ComputerRowMapper());
			break;

		default:

			if (orderDirection.equals(Constant.DESC))
				li = (List<Computer>) npjt.query(ConstantSQL.COMPUTERS_ND,
						paramMap, new ComputerRowMapper());
			else
				li = (List<Computer>) npjt.query(ConstantSQL.COMPUTERS_NA,
						paramMap, new ComputerRowMapper());
			break;

		}
		pw.setCurrPage((offset / limit) + 1);
		pw.setResultsPerPage(limit);
		pw.setResultCount(resultCount);
		pw.setElementList(li);
		pw.setCurrentResultCount(li.size());
		if (resultCount % limit != 0)
			pw.setPageCount((resultCount / limit) + 1);
		else
			pw.setPageCount(resultCount / limit);
		return pw;
	}

	@Override
	public PageWrapper<Computer> getComputers(int limit, int offset,
			String orderBy, String orderDirection, String search) {
		PageWrapper<Computer> pw = new PageWrapper<Computer>();
		List<Computer> li = null;
		int resultCount = 0;

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("limit", limit);
		paramMap.addValue("offset", offset);
		paramMap.addValue("search", "%" + search + "%");

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(
				dataSource);

		resultCount = this.getAmount(search);
		LOGGER.debug("Result count : " + resultCount);
		switch (orderBy) {
		case Constant.COMPANY:
			if (orderDirection.equals(Constant.DESC))
				li = (List<Computer>) npjt.query(
						ConstantSQL.COMPUTERS_CD_SEARCH, paramMap,
						new ComputerRowMapper());
			else
				li = (List<Computer>) npjt.query(
						ConstantSQL.COMPUTERS_CA_SEARCH, paramMap,
						new ComputerRowMapper());
			break;

		case Constant.NAME:
			if (orderDirection.equals(Constant.DESC))
				li = (List<Computer>) npjt.query(
						ConstantSQL.COMPUTERS_ND_SEARCH, paramMap,
						new ComputerRowMapper());
			else
				li = (List<Computer>) npjt.query(
						ConstantSQL.COMPUTERS_NA_SEARCH, paramMap,
						new ComputerRowMapper());
			break;

		case Constant.INTRODUCED:
			if (orderDirection.equals(Constant.DESC))
				li = (List<Computer>) npjt.query(
						ConstantSQL.COMPUTERS_ID_SEARCH, paramMap,
						new ComputerRowMapper());
			else
				li = (List<Computer>) npjt.query(
						ConstantSQL.COMPUTERS_IA_SEARCH, paramMap,
						new ComputerRowMapper());
			break;

		case Constant.DISCONTINUED:
			if (orderDirection.equals(Constant.DESC))
				li = (List<Computer>) npjt.query(
						ConstantSQL.COMPUTERS_DD_SEARCH, paramMap,
						new ComputerRowMapper());
			else
				li = (List<Computer>) npjt.query(
						ConstantSQL.COMPUTERS_DA_SEARCH, paramMap,
						new ComputerRowMapper());
			break;

		default:

			if (orderDirection.equals(Constant.DESC))
				li = (List<Computer>) npjt.query(
						ConstantSQL.COMPUTERS_ND_SEARCH, paramMap,
						new ComputerRowMapper());
			else
				li = (List<Computer>) npjt.query(
						ConstantSQL.COMPUTERS_NA_SEARCH, paramMap,
						new ComputerRowMapper());
			break;

		}
		pw.setCurrPage((offset / limit) + 1);
		pw.setResultsPerPage(limit);
		pw.setResultCount(resultCount);
		pw.setElementList(li);
		if (resultCount % limit != 0)
			pw.setPageCount((resultCount / limit) + 1);
		else
			pw.setPageCount(resultCount / limit);

		return pw;
	}

}
