package com.excilys.formation.projet.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.projet.dao.LogDAO;
import com.excilys.formation.projet.om.Log;
import com.jolbox.bonecp.BoneCPDataSource;

@Repository
public class LogDAOImpl implements LogDAO {
	@Autowired
	private BoneCPDataSource dataSource;

	static final Logger LOGGER = LoggerFactory.getLogger(LogDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(Log l) {
		entityManager.persist(l);
		LOGGER.info("Log inserted ");
	}
}
