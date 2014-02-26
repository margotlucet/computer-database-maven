package com.excilys.formation.projet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.projet.dao.CompanyDAO;
import com.excilys.formation.projet.om.Company;
import com.excilys.formation.projet.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDAO companyDAO;

	/* (non-Javadoc)
	 * @see com.excilys.formation.projet.service.CompanyService#getListeCompany()
	 */
	@Override
	public List<Company> getListeCompany(){
		return companyDAO.getCompanies();
	}
	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.projet.service.CompanyService#getCompanyNameById(long)
	 */
	@Override
	public String getCompanyNameById(long id){
		return this.companyDAO.getById(id).getName();
	}

}
