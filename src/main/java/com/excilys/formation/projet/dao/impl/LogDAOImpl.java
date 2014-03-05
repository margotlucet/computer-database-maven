package com.excilys.formation.projet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.excilys.formation.projet.dao.DAOFactory;
import com.excilys.formation.projet.dao.LogDAO;
import com.excilys.formation.projet.om.Log;
import com.jolbox.bonecp.BoneCPDataSource;

@Repository
public class LogDAOImpl implements LogDAO {
	@Autowired
	private BoneCPDataSource dataSource;

	static final Logger LOGGER = LoggerFactory.getLogger(LogDAOImpl.class);

	@Override
	public void add(Log l) {
		PreparedStatement stmt = null;
		Connection cn = null;
		LOGGER.debug("Preparing to add the log");
		try {
			cn = DataSourceUtils.getConnection(dataSource);
			stmt = cn
					.prepareStatement("INSERT INTO log (description,computer_id) VALUES (?,?);");
			LOGGER.debug("Log description : " + l.getDescription());
			stmt.setString(1, l.getDescription());
			LOGGER.debug("Log idComputer : " + l.getComputer().getId());
			stmt.setLong(2, l.getComputer().getId());
			stmt.executeUpdate();
			LOGGER.info("Log inserted : " + stmt.toString());
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			LOGGER.error("SQLException raised while trying to add a log");
			throw new RuntimeException();
		} finally {
			DAOFactory.INSTANCE_DAO.close(stmt);
		}
	}

}
