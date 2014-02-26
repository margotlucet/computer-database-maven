package com.excilys.formation.projet.dao;

import java.util.List;


import com.excilys.formation.projet.om.Company;


public interface CompanyDAO {
/**
 * Gets a company in the database by its id
 * @param id of the company
 * @return
 */
	public abstract Company getById(long id);
/**
 * Gets all the companies in the database
 * @return
 */
	public abstract List<Company> getCompanies();

}