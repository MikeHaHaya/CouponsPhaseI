package core.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import core.exceptions.DAOException;
// Singleton
public class ConnectionPool {

	private String url = "jdbc:mysql://localhost:3306/couponsdb?serverTimezone=Israel";
	private String user = "root";
	private String password = "1234";
	private Set<Connection> connections = new HashSet<Connection>();
	public static final int MAX = 5;
	
	// A private constructor
	private ConnectionPool() throws SQLException {
		for (int i = 0; i < MAX; i++) {
			connections.add(DriverManager.getConnection(url, user, password));
		}
	}
	
	// To be called by getInstance
	private static ConnectionPool instance;
	
	// Calls the instance method and gets its value
	public static ConnectionPool getInstance() throws DAOException {
		if (instance == null)
			try {
				instance = new ConnectionPool();
			} catch (SQLException e) {
				throw new DAOException("Cannot connect to the database.", e);
			}
		return instance;
	}

	// Gets a single connection from the connections set
	public synchronized Connection getConnection() throws DAOException {
		
		while(connections.isEmpty())
			try {
				wait();
			} catch (InterruptedException e) {
				throw new DAOException("Cannot connect to database, `getConnection` failed.", e);
			}
		Iterator<Connection> iterator = connections.iterator();
		Connection connection = iterator.next();
		iterator.remove();
		return connection;
	}

	// Restores a connection
	public synchronized void restoreConnection(Connection connection) {
		
		connections.add(connection);
		notify();
	}
	
	// Closes all connections
	public synchronized void closeAllConnections() {
		
		Iterator<Connection> iterator = connections.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
	}
	
}
