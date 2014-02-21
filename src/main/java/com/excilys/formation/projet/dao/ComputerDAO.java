package com.excilys.formation.projet.dao;



import java.sql.SQLException;

import com.excilys.formation.projet.om.Computer;
import com.excilys.formation.projet.wrapper.PageWrapper;
/**
 * 
 * @author excilys
 *
 */
public interface ComputerDAO {
	/**
	 * Adds a computer in the database
	 * @param c computer to add
	 * @return
	 * @throws SQLException 
	 */
	public abstract long add(Computer c) throws SQLException;
	/**
	 * Gets a computer in the database by its id
	 * @param id id of the computer
	 * @return the id of the generated computer
	 */
	public abstract Computer getById(long id);
	/**
	 * Removes a computer in the database 
	 * @param id id of the computer
	 */
	public abstract void delete(long id);
	/**
	 * Updates a computer in the database
	 * @param c
	 */
	public abstract void update(Computer c);

	/**
	 * Gets a list of computers in the database in a particular order with a limit and an offset
	 * @param limit
	 * @param offset
	 * @param orderBy
	 * @param orderDirection
	 * @return
	 * @throws SQLException 
	 */
	public abstract PageWrapper<Computer> getComputers(int limit, int offset, String orderBy, String orderDirection) ;
	/**
	 * Gets a a list of computers in the database in a particular order with a limit and an offset containing a particular String
	 * @param limit
	 * @param offset
	 * @param orderBy
	 * @param orderDirection
	 * @param search
	 * @return
	 */
	public abstract PageWrapper<Computer> getComputers(int limit, int offset, String orderBy, String orderDirection, String search);

}