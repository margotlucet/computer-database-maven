package com.excilys.formation.projet.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// On fait un enum pour avoir une seule instance de DAOFactor
/**
 * Factory of DAO
 * 
 * @author excilys
 * 
 */
public enum DAOFactory {
	INSTANCE_DAO;
	private final Logger LOGGER = LoggerFactory.getLogger(DAOFactory.class);

	private DAOFactory() {
		LOGGER.debug("CREATING A DAO FACTORY");
	}

	public void close(ResultSet... rs) {
		for (ResultSet r : rs) {
			if (r != null)
				try {
					r.close();
				} catch (SQLException e) {
					LOGGER.error("Error occured while trying to close a ResultSet");
					e.printStackTrace();
				}
		}
	}

	public void close(PreparedStatement... stmt) {
		for (PreparedStatement ps : stmt) {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					LOGGER.error("Error occured while trying to close a PreparedStatement");
					e.printStackTrace();
				}
		}
	}

}
