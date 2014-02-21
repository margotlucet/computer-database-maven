package com.excilys.formation.projet.dao;

import java.sql.SQLException;

import com.excilys.formation.projet.om.Log;

public interface LogDAO {
	
	public abstract void add(Log l) throws SQLException;
	
	
}
