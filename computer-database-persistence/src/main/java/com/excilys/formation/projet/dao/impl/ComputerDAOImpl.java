package com.excilys.formation.projet.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.projet.dao.ComputerDAO;
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
		computer = entityManager.find(Computer.class, id);
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
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<Computer> c = query.from(Computer.class);
		query.select(cb.count(c));
		number = (long) entityManager.createQuery(query).getSingleResult();
		LOGGER.debug("Amount retrieved");
		return number;
	}

	public long getAmount(String search) {

		long number = 0;
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<Computer> c = query.from(Computer.class);
		Expression<String> pathName = c.get("name");
		Join<Company, Long> company = c.join("company", JoinType.LEFT);
		Expression<String> pathCompanyName = company.get("name");
		query.select(cb.count(c)).where(
				cb.or(cb.like(pathName, "%" + search + "%"),
						cb.like(pathCompanyName, "%" + search + "%")));

		number = (long) entityManager.createQuery(query).getSingleResult();

		LOGGER.debug("---------------------SEARCH-------------------- : "
				+ search);
		return number;
	}

	@Override
	public PageWrapper<Computer> getComputers(int limit, int offset,
			String orderBy, String orderDirection) {
		PageWrapper<Computer> pw = new PageWrapper<Computer>();
		List<Computer> li = null;
		long resultCount = getAmount();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Computer> query = cb.createQuery(Computer.class);
		Root<Computer> c = query.from(Computer.class);

		switch (orderBy) {
		/*---------------------------------------------------*/
		/*------------------NOT IMPLEMENTED------------------*/
		/*---------------------------------------------------*/
		/*
		 * case Constant.COMPANY: if (orderDirection.equals(Constant.DESC))
		 * query.select(c).orderBy(cb.desc(c.get("company.name"))); else
		 * query.select(c).orderBy(cb.asc(c.get("company.name"))); break;
		 */
		case Constant.NAME:
			if (orderDirection.equals(Constant.DESC))
				query.select(c).orderBy(cb.desc(c.get("name")));
			else
				query.select(c).orderBy(cb.asc(c.get("name")));
			break;

		case Constant.INTRODUCED:
			if (orderDirection.equals(Constant.DESC))
				query.select(c).orderBy(cb.desc(c.get("introduced")));
			else
				query.select(c).orderBy(cb.asc(c.get("introduced")));
			break;

		case Constant.DISCONTINUED:
			if (orderDirection.equals(Constant.DESC))
				query.select(c).orderBy(cb.desc(c.get("discontinued")));
			else
				query.select(c).orderBy(cb.asc(c.get("discontinued")));
			break;

		default:

			if (orderDirection.equals(Constant.DESC))
				query.select(c).orderBy(cb.desc(c.get("name")));
			else
				query.select(c).orderBy(cb.asc(c.get("name")));
			break;
		}
		li = entityManager.createQuery(query).setFirstResult(offset)
				.setMaxResults(limit).getResultList();
		pw.setCurrPage((offset / limit) + 1);
		pw.setResultsPerPage(limit);
		pw.setResultCount(resultCount);
		if (resultCount % limit != 0)
			pw.setPageCount(((int) resultCount / limit) + 1);
		else
			pw.setPageCount((int) resultCount / limit);
		pw.setElementList(li);

		return pw;
	}

	@Override
	public PageWrapper<Computer> getComputers(int limit, int offset,
			String orderBy, String orderDirection, String search) {
		PageWrapper<Computer> pw = new PageWrapper<Computer>();
		List<Computer> li = null;
		long resultCount = getAmount(search);
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Computer> query = cb.createQuery(Computer.class);
		Root<Computer> c = query.from(Computer.class);
		Expression<String> pathName = c.get("name");
		Join<Company, Computer> company = c.join("company", JoinType.LEFT);
		Expression<String> pathCompanyName = company.get("name");

		switch (orderBy) {
		/*---------------------------------------------------*/
		/*------------------NOT IMPLEMENTED------------------*/
		/*---------------------------------------------------*/
		/*
		 * case Constant.COMPANY: if (orderDirection.equals(Constant.DESC))
		 * query.select(c) .where(cb.like(pathName, "%" + search + "%"),
		 * cb.like(pathCompanyName, "%" + search + "%"))
		 * .orderBy(cb.desc(c.get("company.name"))); else query.select(c)
		 * .where(cb.like(pathName, "%" + search + "%"),
		 * cb.like(pathCompanyName, "%" + search + "%"))
		 * .orderBy(cb.desc(c.get("company.name"))); break;
		 */
		case Constant.NAME:
			if (orderDirection.equals(Constant.DESC))
				query.select(c)
						.where(cb.or(cb.like(pathName, "%" + search + "%"),
								cb.like(pathCompanyName, "%" + search + "%")))
						.orderBy(cb.desc(c.get("name")));
			else
				query.select(c)
						.where(cb.or(cb.like(pathName, "%" + search + "%"),
								cb.like(pathCompanyName, "%" + search + "%")))
						.orderBy(cb.asc(c.get("name")));
			break;

		case Constant.INTRODUCED:
			if (orderDirection.equals(Constant.DESC))
				query.select(c)
						.where(cb.or(cb.like(pathName, "%" + search + "%"),
								cb.like(pathCompanyName, "%" + search + "%")))
						.orderBy(cb.desc(c.get("introduced")));
			else
				query.select(c)
						.where(cb.or(cb.like(pathName, "%" + search + "%"),
								cb.like(pathCompanyName, "%" + search + "%")))
						.orderBy(cb.asc(c.get("introduced")));
			break;

		case Constant.DISCONTINUED:
			if (orderDirection.equals(Constant.DESC))
				query.select(c)
						.where(cb.or(cb.like(pathName, "%" + search + "%"),
								cb.like(pathCompanyName, "%" + search + "%")))
						.orderBy(cb.desc(c.get("discontinued")));
			else
				query.select(c)
						.where(cb.or(cb.like(pathName, "%" + search + "%"),
								cb.like(pathCompanyName, "%" + search + "%")))
						.orderBy(cb.asc(c.get("discontinued")));
			break;

		default:

			if (orderDirection.equals(Constant.DESC))
				query.select(c)
						.where(cb.or(cb.like(pathName, "%" + search + "%"),
								cb.like(pathCompanyName, "%" + search + "%")))
						.orderBy(cb.desc(c.get("name")));
			else
				query.select(c)
						.where(cb.or(cb.like(pathName, "%" + search + "%"),
								cb.like(pathCompanyName, "%" + search + "%")))
						.orderBy(cb.asc(c.get("name")));
			break;
		}

		li = entityManager.createQuery(query).setFirstResult(offset)
				.setMaxResults(limit).getResultList();
		pw.setCurrPage((offset / limit) + 1);
		pw.setResultsPerPage(limit);
		pw.setResultCount(resultCount);
		if (resultCount % limit != 0)
			pw.setPageCount(((int) resultCount / limit) + 1);
		else
			pw.setPageCount((int) resultCount / limit);
		pw.setElementList(li);
		return pw;
	}
}
