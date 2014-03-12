package com.excilys.formation.projet.dao;

import java.util.List;

import com.excilys.formation.projet.om.Company;

public interface CompanyDAO {

	/**
	 * Gets all the companies in the database
	 * 
	 * @return
	 */
	public abstract List<Company> getCompanies();

}