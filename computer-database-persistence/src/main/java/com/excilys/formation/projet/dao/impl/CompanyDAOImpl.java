package com.excilys.formation.projet.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.excilys.formation.projet.dao.CompanyDAO;
import com.excilys.formation.projet.om.Company;
import com.excilys.formation.projet.om.QCompany;
import com.mysema.query.jpa.impl.JPAQuery;

/**
 * DAO for Company
 * 
 * @author margot
 * 
 */
@Repository
public class CompanyDAOImpl implements CompanyDAO {
	static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImpl.class);
	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.formation.projet.dao.impl.CompanyDAO#getListeCompany()
	 */
	@Override
	public List<Company> getCompanies() {
		JPAQuery query = new JPAQuery(entityManager);
		QCompany company = new QCompany("company");
		List<Company> li = query.from(company).list(company);
		return li;
	}
}
