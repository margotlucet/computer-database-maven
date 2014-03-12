package com.excilys.formation.projet.service;

import java.util.List;

import com.excilys.formation.projet.om.Company;

public interface CompanyService {
	/**
	 * Gets all the companies
	 * 
	 * @return
	 */
	public abstract List<Company> getListeCompany();

}