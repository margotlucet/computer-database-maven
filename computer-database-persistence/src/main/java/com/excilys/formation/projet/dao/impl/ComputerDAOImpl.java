package com.excilys.formation.projet.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.excilys.formation.projet.dao.ComputerDAO;
import com.excilys.formation.projet.om.Computer;
import com.excilys.formation.projet.om.QCompany;
import com.excilys.formation.projet.om.QComputer;
import com.excilys.formation.projet.util.Constant;
import com.excilys.formation.projet.wrapper.PageWrapper;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class ComputerDAOImpl implements ComputerDAO {
	static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAOImpl.class);

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
		JPAQuery query = new JPAQuery(entityManager);
		QComputer computer = new QComputer("computer");
		number = query.from(computer).count();
		LOGGER.debug("Amount retrieved");
		return number;
	}

	public long getAmount(String search) {

		long number = 0;
		JPAQuery query = new JPAQuery(entityManager);
		QComputer computer = new QComputer("computer");
		QCompany company = new QCompany("company");
		BooleanBuilder likePredicat = new BooleanBuilder();
		likePredicat.or(computer.name.like("%" + search + "%"));
		likePredicat.or(company.name.like("%" + search + "%"));
		number = query.from(computer).leftJoin(computer.company, company)
				.where(likePredicat).count();

		LOGGER.debug("---------------------SEARCH-------------------- : "
				+ search);
		return number;
	}

	@Override
	public PageWrapper<Computer> getComputers(int limit, int offset,
			String orderBy, String orderDirection) {

		PageWrapper<Computer> pw = new PageWrapper<Computer>();
		List<Computer> li = null;

		JPAQuery query = new JPAQuery(entityManager);
		QComputer computer = new QComputer("computer");

		long resultCount = getAmount();

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
				li = query.from(computer).limit(limit).offset(offset)
						.orderBy(computer.name.desc()).list(computer);
			else
				li = query.from(computer).limit(limit).offset(offset)
						.orderBy(computer.name.asc()).list(computer);
			break;

		case Constant.INTRODUCED:
			if (orderDirection.equals(Constant.DESC))
				li = query.from(computer).limit(limit).offset(offset)
						.orderBy(computer.introduced.desc()).list(computer);
			else
				li = query.from(computer).limit(limit).offset(offset)
						.orderBy(computer.introduced.asc()).list(computer);
			break;

		case Constant.DISCONTINUED:
			if (orderDirection.equals(Constant.DESC))
				li = query.from(computer).limit(limit).offset(offset)
						.orderBy(computer.discontinued.desc()).list(computer);
			else
				li = query.from(computer).limit(limit).offset(offset)
						.orderBy(computer.discontinued.asc()).list(computer);
			break;

		default:

			if (orderDirection.equals(Constant.DESC))
				li = query.from(computer).limit(limit).offset(offset)
						.orderBy(computer.name.desc()).list(computer);
			else
				li = query.from(computer).limit(limit).offset(offset)
						.orderBy(computer.name.asc()).list(computer);
			break;
		}
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
		JPAQuery query = new JPAQuery(entityManager);
		QComputer computer = new QComputer("computer");
		QCompany company = new QCompany("company");
		BooleanBuilder likePredicat = new BooleanBuilder();
		likePredicat.or(computer.name.like("%" + search + "%"));
		likePredicat.or(company.name.like("%" + search + "%"));

		switch (orderBy) {
		/*---------------------------------------------------*/
		/*------------------NOT IMPLEMENTED------------------*/
		/*---------------------------------------------------*/

		case Constant.NAME:
			if (orderDirection.equals(Constant.DESC))
				li = query.from(computer).leftJoin(computer.company, company)
						.where(likePredicat).limit(limit).offset(offset)
						.orderBy(computer.name.desc()).list(computer);
			else
				li = query.from(computer).leftJoin(computer.company, company)
						.where(likePredicat).limit(limit).offset(offset)
						.orderBy(computer.name.asc()).list(computer);
			break;

		case Constant.INTRODUCED:
			if (orderDirection.equals(Constant.DESC))
				li = query.from(computer).leftJoin(computer.company, company)
						.where(likePredicat).limit(limit).offset(offset)
						.orderBy(computer.introduced.desc()).list(computer);
			else
				li = query.from(computer).leftJoin(computer.company, company)
						.where(likePredicat).limit(limit).offset(offset)
						.orderBy(computer.introduced.asc()).list(computer);
			break;

		case Constant.DISCONTINUED:
			if (orderDirection.equals(Constant.DESC))
				li = query.from(computer).leftJoin(computer.company, company)
						.where(likePredicat).limit(limit).offset(offset)
						.orderBy(computer.discontinued.desc()).list(computer);
			else
				li = query.from(computer).leftJoin(computer.company, company)
						.where(likePredicat).limit(limit).offset(offset)
						.orderBy(computer.discontinued.asc()).list(computer);
			break;

		default:

			if (orderDirection.equals(Constant.DESC))
				li = query.from(computer).leftJoin(computer.company, company)
						.where(likePredicat).limit(limit).offset(offset)
						.orderBy(computer.name.desc()).list(computer);
			else
				li = query.from(computer).leftJoin(computer.company, company)
						.where(likePredicat).limit(limit).offset(offset)
						.orderBy(computer.name.asc()).list(computer);
			break;
		}
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
