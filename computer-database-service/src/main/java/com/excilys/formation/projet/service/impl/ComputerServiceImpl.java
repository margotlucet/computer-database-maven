package com.excilys.formation.projet.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.projet.dao.ComputerDAO;
import com.excilys.formation.projet.dao.LogDAO;
import com.excilys.formation.projet.om.Computer;
import com.excilys.formation.projet.om.Log;
import com.excilys.formation.projet.service.ComputerService;
import com.excilys.formation.projet.wrapper.PageWrapper;

@Service
public class ComputerServiceImpl implements ComputerService {
	static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerServiceImpl.class);
	@Autowired
	private ComputerDAO computerDAO;
	@Autowired
	private LogDAO logDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.projet.service.ComputerService#getComputerById(
	 * long)
	 */
	@Override
	@Transactional(readOnly = true)
	public Computer getById(long id) {
		Computer c = this.computerDAO.getById(id);
		return c;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.projet.service.ComputerService#addComputer(com.
	 * excilys.formation.projet.om.Computer)
	 */
	@Override
	@Transactional
	public void add(Computer c) {

		long idComputer = 0;
		Log l = new Log();
		idComputer = computerDAO.add(c);
		c.setId(idComputer);
		l.setComputer(c);
		l.setDescription("New computer Id : " + c.toString());
		LOGGER.debug("Log to add : " + l);
		LOGGER.debug("My LogDAO : " + logDAO);
		logDAO.add(l);
		LOGGER.debug("Exiting add");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.projet.service.ComputerService#editComputer(com
	 * .excilys.formation.projet.om.Computer)
	 */
	@Override
	@Transactional
	public void update(Computer c) {
		Log l = new Log();
		computerDAO.update(c);
		l.setComputer(c);
		l.setDescription("Computer updated : " + c.toString());
		logDAO.add(l);
		LOGGER.debug("Exiting update");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.projet.service.ComputerService#deleteComputer(java
	 * .lang.String)
	 */
	@Override
	@Transactional
	public void delete(long id) {
		Log l = new Log();

		// DAOFactory.INSTANCE_DAO.startTransaction();
		computerDAO.delete(id);
		Computer c = new Computer.Builder().id(id).build();
		l.setComputer(c);
		l.setDescription("Computer with id " + id + " deleted");
		logDAO.add(l);
		// DAOFactory.INSTANCE_DAO.endTransaction();

		LOGGER.debug("Exiting delete");
	}

	@Override
	@Transactional(readOnly = true)
	public PageWrapper<Computer> getPage(int page, int nbResult,
			String orderBy, String orderDirection) {
		return this.computerDAO.getComputers(nbResult, (page - 1) * nbResult,
				orderBy, orderDirection);
	}

	@Override
	@Transactional(readOnly = true)
	public PageWrapper<Computer> getPage(int page, int nbResult,
			String orderBy, String orderDirection, String search) {
		return this.computerDAO.getComputers(nbResult, (page - 1) * nbResult,
				orderBy, orderDirection, search);
	}

	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}

	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}

	public LogDAO getLogDAO() {
		return logDAO;
	}

	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}

}
