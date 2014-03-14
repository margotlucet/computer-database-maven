package com.excilys.formation.projet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.projet.om.Company;
import com.excilys.formation.projet.repository.CompanyRepository;
import com.excilys.formation.projet.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.projet.service.CompanyService#getListeCompany()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Company> getListeCompany() {
		return companyRepository.findAll();
	}

}
