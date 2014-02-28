package com.excilys.formation.projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// On fait un enum pour avoir une seule instance de DAOFactor
/**
 * Factory of DAO
 * @author excilys
 *
 */
public enum DAOFactory {
	INSTANCE_DAO;
	private ThreadLocalImpl<Connection> tl;
	private BasicDataSource ds;
	private final String URL = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private final String USR = "jee-cdb";
	private final String PW = "password";
	private final Logger LOGGER = LoggerFactory.getLogger(DAOFactory.class);

	private DAOFactory(){
		tl = new ThreadLocalImpl<Connection>();
		LOGGER.debug("Creating DAOFactory");
		LOGGER.debug("TL : "+tl.toString());
		ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl(URL);
		ds.setUsername(USR);
		ds.setPassword(PW);
	}
	/**
	 * Gets a connection to the database
	 * @return a new connection
	 */
	public Connection getConnexion(){
		return tl.get();
	}

	public BasicDataSource getDs() {
		return ds;
	}
	public void setDs(BasicDataSource ds) {
		this.ds = ds;
	}
	public void close(ResultSet... rs){
		for(ResultSet r : rs){
			if(r!=null)
				try {
					r.close();
				} catch (SQLException e) {
					LOGGER.error("Error occured while trying to close a ResultSet");
					e.printStackTrace();
				}
		}
	}
	public void close(Connection... cn) {
		for(Connection c : cn){
			if(c!=null)
				try {
					if(!c.isClosed())
						c.close();
					tl.remove();
				} catch (SQLException e) {
					LOGGER.error("Error occured while trying to close a Connection");
					try {
						c.rollback();
					} catch (SQLException e1) {
						LOGGER.error("An error occured while trying to rollback a connection");
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
		}
	}

	public void close(PreparedStatement... stmt){
		for(PreparedStatement ps : stmt){
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					LOGGER.error("Error occured while trying to close a PreparedStatement");
					e.printStackTrace();
				}
		}
	}

	public ThreadLocalImpl<Connection> getTl() {
		return tl;
	}
	public void setTl(ThreadLocalImpl<Connection> tl) {
		this.tl = tl;
	}

	public void startTransaction() throws SQLException{
		Connection cn = this.getConnexion();
		cn.setAutoCommit(false);
	}

	public void endTransaction() throws SQLException{
		Connection cn = null;
		try {
			cn = this.tl.get();
			cn.commit();
			cn.setAutoCommit(true);
		}
		finally{
			close(cn);
		}
	}

	public void rollback() throws SQLException{
		Connection cn = null;
		cn = this.tl.get();
		cn.rollback();
	}
	public class ThreadLocalImpl<T> extends ThreadLocal<Connection>{
		@Override
		public Connection initialValue(){
			Connection cn = null;

			try {
				cn = ds.getConnection();
				this.set(cn);
				LOGGER.info("[BONECP] returning connection");

			} catch (SQLException e) {
				LOGGER.error("Error while trying to get a connection");
				e.printStackTrace();
			}
			return cn;

		}
	}

}
