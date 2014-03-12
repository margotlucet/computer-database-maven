package com.excilys.formation.projet.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.projet.dao.CompanyDAO;
import com.excilys.formation.projet.dao.ConstantSQL;
import com.excilys.formation.projet.om.Company;
import com.jolbox.bonecp.BoneCPDataSource;

/**
 * DAO for Company
 * 
 * @author margot
 * 
 */
@Repository
public class CompanyDAOImpl implements CompanyDAO {
	static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImpl.class);
	@Autowired
	private BoneCPDataSource dataSource;

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.formation.projet.dao.impl.CompanyDAO#getListeCompany()
	 */
	@Override
	public List<Company> getCompanies() {
		List<Company> li = new ArrayList<Company>();
		li = (List<Company>) entityManager.createQuery(ConstantSQL.COMPANIES)
				.getResultList();
		LOGGER.debug("List of companies " + li);
		return li;
	}
}
