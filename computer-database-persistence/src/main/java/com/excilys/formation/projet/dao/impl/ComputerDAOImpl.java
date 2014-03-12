package com.excilys.formation.projet.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.projet.dao.ComputerDAO;
import com.excilys.formation.projet.dao.ConstantSQL;
import com.excilys.formation.projet.om.Computer;
import com.excilys.formation.projet.util.Constant;
import com.excilys.formation.projet.wrapper.PageWrapper;
import com.jolbox.bonecp.BoneCPDataSource;

@Repository
public class ComputerDAOImpl implements ComputerDAO {
	static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAOImpl.class);

	@Autowired
	private BoneCPDataSource dataSource;

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.projet.dao.impl.ComputerDAO#addComputer(com.excilys
	 * .formation.projet.om.Computer)
	 */
	@Override
	public Computer add(Computer c) {
		LOGGER.debug("Computer before : " + c);
		entityManager.persist(c);
		LOGGER.debug("Computer after : " + c);
		return c;
		/*
		 * long idCompany;
		 * 
		 * MapSqlParameterSource paramMap = new MapSqlParameterSource();
		 * paramMap.addValue("name", c.getName()); if (c.getIntroduced() !=
		 * null) paramMap.addValue("introduced", new Timestamp(c.getIntroduced()
		 * .getMillis())); else paramMap.addValue("introduced", null); if
		 * (c.getDiscontinued() != null) paramMap.addValue("discontinued", new
		 * Timestamp(c.getDiscontinued() .getMillis())); else
		 * paramMap.addValue("discontinued", null); idCompany =
		 * c.getCompany().getId(); if (idCompany != 0)
		 * paramMap.addValue("companyId", idCompany); else
		 * paramMap.addValue("companyId", null); NamedParameterJdbcTemplate npjt
		 * = new NamedParameterJdbcTemplate( dataSource); KeyHolder keyHolder =
		 * new GeneratedKeyHolder(); npjt.update(ConstantSQL.COMPUTER_CREATE,
		 * paramMap, keyHolder, new String[] { "id" });
		 * 
		 * id = keyHolder.getKey().longValue();
		 * LOGGER.debug("Generated computer id : " + id);
		 * LOGGER.info("Computer added");
		 */
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
		computer = (Computer) entityManager.find(Computer.class, id);
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
		Computer c = getById(id);
		entityManager.remove(c);
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
		entityManager.merge(c);
	}

	public long getAmount() {
		long number = 0;
		number = (long) entityManager.createQuery(ConstantSQL.COMPUTER_AMOUNT)
				.getSingleResult();
		LOGGER.debug("Amount retrieved");
		return number;
	}

	public long getAmount(String search) {

		long number = 0;
		number = (long) entityManager
				.createQuery(ConstantSQL.COMPUTER_AMOUNT_SEARCH)
				.setParameter("search", "%" + search + "%").getSingleResult();

		LOGGER.debug("---------------------SEARCH-------------------- : "
				+ search);
		return number;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageWrapper<Computer> getComputers(int limit, int offset,
			String orderBy, String orderDirection) {
		PageWrapper<Computer> pw = new PageWrapper<Computer>();
		List<Computer> li = null;
		long resultCount = getAmount();
		li = entityManager.createQuery(ConstantSQL.COMPUTERS)
				.setFirstResult(offset).setMaxResults(limit).getResultList();
		pw.setCurrPage((offset / limit) + 1);
		pw.setResultsPerPage(limit);
		pw.setResultCount(resultCount);
		if (resultCount % limit != 0)
			pw.setPageCount(((int) resultCount / limit) + 1);
		else
			pw.setPageCount((int) resultCount / limit);
		pw.setElementList(li);
		/*---------------------------------------------------------------------*/
		/*-------------- Using order by NOT IMPLEMENTED WITH JPA --------------*/
		/*---------------------------------------------------------------------*/

		switch (orderBy) {
		case Constant.COMPANY:
			if (orderDirection.equals(Constant.DESC))
				LOGGER.debug("Order by company name desc");
			else
				LOGGER.debug("Order by company name asc");
			break;

		case Constant.NAME:
			if (orderDirection.equals(Constant.DESC))
				LOGGER.debug("Order by name desc");
			else
				LOGGER.debug("Order by name asc");
			break;

		case Constant.INTRODUCED:
			if (orderDirection.equals(Constant.DESC))
				LOGGER.debug("Order by introduced desc");
			else
				LOGGER.debug("Order by introduced asc");
			break;

		case Constant.DISCONTINUED:
			if (orderDirection.equals(Constant.DESC))
				LOGGER.debug("Order by discontinued desc");
			else
				LOGGER.debug("Order by discontinued asc");
			break;

		default:

			if (orderDirection.equals(Constant.DESC))
				LOGGER.debug("Order by name desc");
			else
				LOGGER.debug("Order by name asc");
			break;
		}
		/*---------------------------------------------------------------------*/
		/*-------------------------------- END --------------------------------*/
		/*---------------------------------------------------------------------*/

		return pw;
	}

	@Override
	public PageWrapper<Computer> getComputers(int limit, int offset,
			String orderBy, String orderDirection, String search) {
		PageWrapper<Computer> pw = new PageWrapper<Computer>();
		List<Computer> li = null;
		long resultCount = getAmount(search);
		li = entityManager.createQuery(ConstantSQL.COMPUTERS_SEARCH)
				.setFirstResult(offset).setMaxResults(limit)
				.setParameter("search", "%" + search + "%").getResultList();
		pw.setCurrPage((offset / limit) + 1);
		pw.setResultsPerPage(limit);
		pw.setResultCount(resultCount);
		if (resultCount % limit != 0)
			pw.setPageCount(((int) resultCount / limit) + 1);
		else
			pw.setPageCount((int) resultCount / limit);
		pw.setElementList(li);
		/*---------------------------------------------------------------------*/
		/*-------------- Using order by NOT IMPLEMENTED WITH JPA --------------*/
		/*---------------------------------------------------------------------*/

		switch (orderBy) {
		case Constant.COMPANY:
			if (orderDirection.equals(Constant.DESC))
				LOGGER.debug("Order by company name desc");
			else
				LOGGER.debug("Order by company name asc");
			break;

		case Constant.NAME:
			if (orderDirection.equals(Constant.DESC))
				LOGGER.debug("Order by name desc");
			else
				LOGGER.debug("Order by name asc");
			break;

		case Constant.INTRODUCED:
			if (orderDirection.equals(Constant.DESC))
				LOGGER.debug("Order by introduced desc");
			else
				LOGGER.debug("Order by introduced asc");
			break;

		case Constant.DISCONTINUED:
			if (orderDirection.equals(Constant.DESC))
				LOGGER.debug("Order by discontinued desc");
			else
				LOGGER.debug("Order by discontinued asc");
			break;

		default:

			if (orderDirection.equals(Constant.DESC))
				LOGGER.debug("Order by name desc");
			else
				LOGGER.debug("Order by name asc");
			break;
		}
		/*---------------------------------------------------------------------*/
		/*-------------------------------- END --------------------------------*/
		/*---------------------------------------------------------------------*/

		return pw;
	}

}
