package com.excilys.formation.projet.service;

import java.util.List;

import com.excilys.formation.projet.om.Company;

public interface CompanyService {
/**
 * Gets all the companies
 * @return
 */
	public abstract List<Company> getListeCompany();
/**
 * Gets the name of a company with to its id
 * @param id id of the company
 * @return
 */
	public abstract String getCompanyNameById(long id);

}