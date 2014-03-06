package com.excilys.formation.projet.service;


import com.excilys.formation.projet.om.Computer;
import com.excilys.formation.projet.wrapper.PageWrapper;

public interface ComputerService {
	/**
	 * Gets a computer by its id
	 * @param id id of the computer
	 * @return
	 */
	public abstract Computer getById(long id);
	/**
	 * Adds a computer
	 * @param c
	 */
	public abstract void add(Computer c);
	/**
	 * Updates a computer
	 * @param c
	 */
	public abstract void update(Computer c);
	/**
	 * Delete a computer
	 * @param id
	 */
	public abstract void delete(long id);

	/**
	 * Gets a particular page of computers existing in the database
	 * @param page number of the page
	 * @param nbResult number of results
	 * @return
	 */
	public abstract PageWrapper<Computer> getPage(int page, int nbResult, String orderBy, String orderDirection);
	/**
	 * Gets a particular page of computers existing in the database containing a particular string 
	 * @param page number of the page
	 * @param nbResult number of results
	 * @param search
	 * @return
	 */
	public abstract PageWrapper<Computer> getPage(int page, int nbResult, String orderBy, String orderDirection, String search);
}