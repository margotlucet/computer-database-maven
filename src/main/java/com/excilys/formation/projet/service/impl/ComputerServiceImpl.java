package com.excilys.formation.projet.service.impl;



import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.projet.dao.ComputerDAO;
import com.excilys.formation.projet.dao.DAOFactory;
import com.excilys.formation.projet.dao.LogDAO;
import com.excilys.formation.projet.om.Computer;
import com.excilys.formation.projet.om.Log;
import com.excilys.formation.projet.service.ComputerService;
import com.excilys.formation.projet.wrapper.PageWrapper;


public class ComputerServiceImpl implements ComputerService {
	static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class);

	private ComputerDAO computerDAO;
	private LogDAO logDAO;
	public ComputerServiceImpl() {
		super();
		this.computerDAO = DAOFactory.INSTANCE_DAO.getComputerDAO();
		this.logDAO = DAOFactory.INSTANCE_DAO.getLogDAO();
	}


	/* (non-Javadoc)
	 * @see com.excilys.formation.projet.service.ComputerService#getComputerById(long)
	 */
	@Override
	public Computer getById(long id){
		Computer c = this.computerDAO.getById(id);
		return c;
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.projet.service.ComputerService#addComputer(com.excilys.formation.projet.om.Computer)
	 */
	@Override
	public void add(Computer c){	

		long idComputer = 0;
		Log l = new Log();
		try {
			DAOFactory.INSTANCE_DAO.startTransaction();
			idComputer = computerDAO.add(c);
			c.setId(idComputer);
			l.setComputer(c);
			l.setDescription("New computer Id : "+c.toString());
			LOGGER.debug("Log to add : "+l);
			LOGGER.debug("My LogDAO : "+logDAO);
			logDAO.add(l);
			DAOFactory.INSTANCE_DAO.endTransaction();
		} catch (SQLException e) {
			LOGGER.error("Error occured while executing request");
			try {
				DAOFactory.INSTANCE_DAO.rollback();
			} catch (SQLException e1) {
				LOGGER.error("Error occured while trying to rollback");
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		LOGGER.debug("Exiting add");


	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.projet.service.ComputerService#editComputer(com.excilys.formation.projet.om.Computer)
	 */
	@Override
	public void update(Computer c){
		Log l = new Log();
		try {
			DAOFactory.INSTANCE_DAO.startTransaction();
			computerDAO.update(c);
			l.setComputer(c);
			l.setDescription("Computer updated : "+c.toString());
			logDAO.add(l);
			DAOFactory.INSTANCE_DAO.endTransaction();
		} catch (SQLException e) {
			LOGGER.error("Error occured while executing request");
			try {
				DAOFactory.INSTANCE_DAO.rollback();
			} catch (SQLException e1) {
				LOGGER.error("Error occured while trying to rollback");
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		LOGGER.debug("Exiting update");
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.projet.service.ComputerService#deleteComputer(java.lang.String)
	 */
	@Override
	public void delete(long id){
		Log l = new Log();
		try{
			DAOFactory.INSTANCE_DAO.startTransaction();
			computerDAO.delete(id);
			Computer c = new Computer.Builder().id(id).build();
			l.setComputer(c);
			l.setDescription("Computer with id "+id+" deleted");
			logDAO.add(l);
			DAOFactory.INSTANCE_DAO.endTransaction();
		}
		catch (SQLException e) {
			LOGGER.error("Error occured while executing request");
			try {
				DAOFactory.INSTANCE_DAO.rollback();
			} catch (SQLException e1) {
				LOGGER.error("Error occured while trying to rollback");
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		LOGGER.debug("Exiting delete");;
	}
	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}

	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}


	@Override
	public PageWrapper<Computer> getPage(int page, int nbResult, String orderBy, String orderDirection) {	
		return this.computerDAO.getComputers(nbResult, (page-1)*nbResult, orderBy, orderDirection);
	}

	@Override
	public PageWrapper<Computer> getPage(int page, int nbResult, String orderBy, String orderDirection, String search) {
		return this.computerDAO.getComputers(nbResult, (page-1)*nbResult,  orderBy, orderDirection, search);
	}


}
