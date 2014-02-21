package com.excilys.formation.projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.projet.dao.impl.CompanyDAOImpl;
import com.excilys.formation.projet.dao.impl.ComputerDAOImpl;
import com.excilys.formation.projet.dao.impl.LogDAOImpl;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
// On fait un enum pour avoir une seule instance de DAOFactor
/**
 * Factory of DAO
 * @author excilys
 *
 */
public enum DAOFactory {
	INSTANCE_DAO;
	private ComputerDAO computerDAO;
	private CompanyDAO companyDAO;
	private LogDAO logDAO;
	private BoneCP connectionPool;
	private ThreadLocalImpl<Connection> tl;
	private final String URL = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private final String USR = "jee-cdb";
	private final String PW = "password";
	private final Logger LOGGER = LoggerFactory.getLogger(DAOFactory.class);

	private DAOFactory(){
		computerDAO = new ComputerDAOImpl();
		companyDAO = new CompanyDAOImpl();
		logDAO = new LogDAOImpl();
		tl = new ThreadLocalImpl<Connection>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		BoneCPConfig config = new BoneCPConfig();

		config.setJdbcUrl(URL);
		config.setUsername(USR);
		config.setPassword(PW);

		config.setMinConnectionsPerPartition(5);
		config.setMaxConnectionsPerPartition(10);
		config.setPartitionCount(1);
		try {
			setConnectionPool(new BoneCP(config));
		} catch (SQLException e) {
			LOGGER.error("[SQLEXCEPTION]");
			e.printStackTrace();
		}
	}
	/**
	 * Gets a connection to the database
	 * @return a new connection
	 */
	public Connection getConnexion(){
		return tl.get();
	}

	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}


	public CompanyDAO getCompanyDAO() {
		return companyDAO;
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

	public BoneCP getConnectionPool() {
		return connectionPool;
	}
	public void setConnectionPool(BoneCP connectionPool) {
		this.connectionPool = connectionPool;
	}
	public LogDAO getLogDAO() {
		return logDAO;
	}
	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
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
				cn = connectionPool.getConnection();
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
