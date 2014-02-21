package com.excilys.formation.projet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.projet.dao.DAOFactory;
import com.excilys.formation.projet.dao.LogDAO;
import com.excilys.formation.projet.om.Log;

public class LogDAOImpl implements LogDAO {
	static final Logger LOGGER = LoggerFactory.getLogger(LogDAOImpl.class);

	@Override
	public void add(Log l) throws SQLException {
		PreparedStatement stmt = null;
		Connection cn = null;
		LOGGER.debug("Preparing to add the log");
		try {
			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.prepareStatement("INSERT INTO log (description,computer_id) VALUES (?,?);");
			LOGGER.debug("Log description : "+l.getDescription());
			stmt.setString(1,l.getDescription());
			LOGGER.debug("Log idComputer : "+l.getComputer().getId()); 	
			stmt.setLong(2, l.getComputer().getId());	
			stmt.executeUpdate();
			LOGGER.info("Log inserted : "+stmt.toString());
		} 
		finally{
			DAOFactory.INSTANCE_DAO.close(stmt);
		}
	}

}
