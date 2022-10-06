package core.db.dao.bodies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import core.beans.Bean;
import core.beans.Category;
import core.beans.Company;
import core.beans.Coupon;
import core.beans.Customer;
import core.beans.Purchase;
import core.db.ConnectionPool;
import core.db.dao.interfaces.CompanyInterfaceDAO;
import core.db.dao.interfaces.CouponInterfaceDAO;
import core.db.dao.interfaces.CustomerInterfaceDAO;
import core.db.dao.interfaces.PurchaseInterfaceDAO;
import core.exceptions.DAOException;

public abstract class BodyDAO<T extends Bean> {

	protected ConnectionPool pool;
	protected String table;
	
	/** A no parameter constructor for BodyDAO.
	 * @throws DAOException if an unknown Interface uses this method
	 * (Interface has to be CompanyInterfaceDAO/CustomerInterfaceDAO/CouponInterfaceDAO). */
	protected BodyDAO() throws DAOException {
		
		this.pool = ConnectionPool.getInstance();
		table = tableType();
	}
	
	
	// TO USE-methods
	
	/**
	 * Generic method to retrieve whether a type <T> exist in the database.
	 * All DAO methods that return boolean should use this method.
	 * The 'action' String is there for the option to check existence by id, or by email and password.
	 * Enter 'id' for id check or 'log' for email and password check
	 * @throws DAOException if the type <T> object is not Company/Customer/Coupon */
	protected boolean isTypeExist(String email, String password) throws DAOException {
		
		Connection connection = pool.getConnection();
		
		try {
			
			String sql = "SELECT * FROM " + table + " WHERE `Email` = '" + email + "' AND `Password` = '" + password + "'";
			PreparedStatement preState = connection.prepareStatement(sql);
			ResultSet rs = preState.executeQuery();
			
			try {
				RsToArrayList(rs).get(0);
				return true;
			} catch (IndexOutOfBoundsException e) {
				return false;
			}
			
		} catch (SQLException e) {
			throw new DAOException("Failed to check if the type <T> exists in the database.", e);
			
		} finally { // To make sure we return the connection to the ConnectionPool
			if (connection != null) {
				pool.restoreConnection(connection);
			}
			
		}
	}
	
	/**
	 * Generic body to add a type to the database.
	 * Type has to be a Company/Customer/Coupon/Purchase or the method will throw an exception. */
	protected void addType(T type) throws DAOException {
		
		Connection connection = pool.getConnection();
		
		try {
			
			// Sets the sql statement
			String sql = "INSERT INTO " + table + " VALUES ";
			if (type instanceof Company) {
				sql += "(?, ?, ?, ?)";
			} else if (type instanceof Customer) {
				sql += "(?, ?, ?, ?, ?)";
			} else if (type instanceof Coupon) {
				sql += "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			} else if (type instanceof Purchase) {
				sql += "(?, ?)";
			} else {
				throw new DAOException("Unkown type <T>, type has to be either a Company, a Customer or a Coupon.");
			}
			
			PreparedStatement preState = connection.prepareStatement(sql);
			preState = preStateType(type, preState, "add");
			preState.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException("Failed to add values to the database.", e);
			
		} finally { // To make sure we return the connection to the ConnectionPool
			if (connection != null) 
				pool.restoreConnection(connection);
		}
		
	}
	
	/**
	 * Generic body to update a type from the database.
	 * Type has to be either a Company a Customer or a Coupon or else the method will throw an exception. */
	protected void updateType(T type) throws DAOException {
		
		Connection connection = pool.getConnection();
		
		try {
			
			String sql = "UPDATE " + table + " SET ";
			
			if (type instanceof Company) {
				sql += "`Name` = ?, `Email` = ?, `Password` = ? WHERE `ID` = ?";
			} else if (type instanceof Customer) {
				sql += "`FirstName` = ?, `LastName` = ?, `Email` = ?, `Password` = ? WHERE `ID` = ?";
			} else if (type instanceof Coupon) {
				sql += "`CompanyID` = ?, `CategoryID` = ?, `Title` = ?, `Description` = ?, `StartDate` = ?, `EndDate` = ?, `Amount` = ?, `Price` = ?, `Image` = ? WHERE `ID` = ?";
			} else {
				throw new DAOException("Unkown type <T>, type has to be either a Company, a Customer or a Coupon.");
			}
			
			PreparedStatement preState = connection.prepareStatement(sql);
			preState = preStateType(type, preState, "update");
			preState.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException("Failed to update values from the database.", e);
			
		} finally { // To make sure we return the connection to the ConnectionPool
			if (connection != null) 
				pool.restoreConnection(connection);
		}
		
	}
	
	/**
	 * Generic body to delete a type from the database.
	 * Type has to be a Company a Customer or a Coupon or the method will throw an exception. */
	protected void deleteType(int id) throws DAOException {
		
		Connection connection = pool.getConnection();
		
		try {
			
			String sql = "DELETE FROM " + table + " WHERE `ID` = " + id;
			PreparedStatement preState = connection.prepareStatement(sql);
			preState.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException("Failed to delete values from the database.", e);
			
		} finally { // To make sure we return the connection to the ConnectionPool
			if (connection != null) 
				pool.restoreConnection(connection);
		}
	}
	
	/**
	 * Generic body to receive an ArrayList of type from the database.
	 * @throws DAOException if type <T> is not Company/Customer/Coupon. */
	protected ArrayList<T> getAllType() throws DAOException {
		
		Connection connection = pool.getConnection();
		
		try {
			
			String sql = "SELECT * FROM " + table;
			PreparedStatement preState = connection.prepareStatement(sql);
			ResultSet rs = preState.executeQuery();
			
			return RsToArrayList(rs);
			
		} catch (SQLException e) {
			throw new DAOException("Failed to get a type <T> list from the database.", e);
			
		} finally { // To make sure we return the connection to the ConnectionPool
			if (connection != null) 
				pool.restoreConnection(connection);
		}
	}
	
	/**
	 * Generic body to receive a single Type from the database.
	 * @throws DAOException if type <T> is not Company/Customer/Coupon. */
	protected T getOneType(int id) throws DAOException {
		
		Connection connection = pool.getConnection();
		
		try {
			
			String sql = "SELECT * FROM " + table + " WHERE `ID` = " + id;
			PreparedStatement preState = connection.prepareStatement(sql);
			ResultSet rs = preState.executeQuery();
			
			return RsToArrayList(rs).get(0);
			
		} catch (SQLException e) {
			throw new DAOException("Failed to get a single type <T> from the database.", e);
			
		} catch (IndexOutOfBoundsException e) {
			throw new DAOException("Failed to get a single type <T> from the database.", e);
			
		} finally { // To make sure we return the connection to the ConnectionPool
			if (connection != null) 
				pool.restoreConnection(connection);
		}
	}

	/**
	 * Generic body to receive a single Type from the database.
	 * @throws DAOException if type <T> is not Company/Customer. */
	protected T getOneType(String email, String password) throws DAOException {
		
		Connection connection = pool.getConnection();
		
		try {
			
			String sql = "SELECT * FROM " + table + " WHERE `Email` = '" + email + "' AND `Password` = '" + password + "'";
			PreparedStatement preState = connection.prepareStatement(sql);
			ResultSet rs = preState.executeQuery();
			
			return RsToArrayList(rs).get(0);
			
		} catch (SQLException e) {
			throw new DAOException("Failed to get a single type <T> from the database.", e);
			
		} finally { // To make sure we return the connection to the ConnectionPool
			if (connection != null) 
				pool.restoreConnection(connection);
		}
	}
	
	
	// Sub-methods
	
	/**
	 * Updates the PreparedStatement for execution,
	 * currently written to work with add and update methods and only exists to give them a cleaner look. */
	protected PreparedStatement preStateType(T type, PreparedStatement preState, String action) throws DAOException {
		
		try {
			
			// In case we need to add something
			if (action.equalsIgnoreCase("add")) {
				
				// PreparedStatement for Company
				if (type instanceof Company) {
					preState.setInt(1, ((Company) type).getId());
					preState.setString(2, ((Company) type).getName());
					preState.setString(3, ((Company) type).getEmail());
					preState.setString(4, ((Company) type).getPassword());
					
				// PreparedStatement for Customer
				} else if (type instanceof Customer) {
					preState.setInt(1, ((Customer) type).getId());
					preState.setString(2, ((Customer) type).getFirstName());
					preState.setString(3, ((Customer) type).getLastName());
					preState.setString(4, ((Customer) type).getEmail());
					preState.setString(5, ((Customer) type).getPassword());
					
				// PreparedStatement for Coupon
				} else if (type instanceof Coupon) {
					preState.setInt(1, ((Coupon) type).getId());
					preState.setInt(2, ((Coupon) type).getCompanyID());
					preState.setInt(3, ((Coupon) type).getCategory().getCategorySQLIndex());
					preState.setString(4, ((Coupon) type).getTitle());
					preState.setString(5, ((Coupon) type).getDescription());
					preState.setDate(6, ((Coupon) type).getStartDate());
					preState.setDate(7, ((Coupon) type).getEndDate());
					preState.setInt(8, ((Coupon) type).getAmount());
					preState.setDouble(9, ((Coupon) type).getPrice());
					preState.setString(10, ((Coupon) type).getImage());
					
				// PreparedStatement for Purchase
				} else if (type instanceof Purchase) {
					preState.setInt(1, ((Purchase) type).getCustomerID());
					preState.setInt(2, ((Purchase) type).getCouponID());
				}
				
			// In case we need to update something
			} else if (action.equalsIgnoreCase("update")) {
				
				// PreparedStatement for Company
				if (type instanceof Company) {
					preState.setString(1, ((Company) type).getName());
					preState.setString(2, ((Company) type).getEmail());
					preState.setString(3, ((Company) type).getPassword());
					preState.setInt(4, ((Company) type).getId());
					
				// PreparedStatement for Customer
				} else if (type instanceof Customer) {
					preState.setString(1, ((Customer) type).getFirstName());
					preState.setString(2, ((Customer) type).getLastName());
					preState.setString(3, ((Customer) type).getEmail());
					preState.setString(4, ((Customer) type).getPassword());
					preState.setInt(5, ((Customer) type).getId());
					
				// PreparedStatement for Coupon
				} else if (type instanceof Coupon) {
					preState.setInt(1, ((Coupon) type).getCompanyID());
					preState.setInt(2, ((Coupon) type).getCategory().getCategorySQLIndex());
					preState.setString(3, ((Coupon) type).getTitle());
					preState.setString(4, ((Coupon) type).getDescription());
					preState.setDate(5, ((Coupon) type).getStartDate());
					preState.setDate(6, ((Coupon) type).getEndDate());
					preState.setInt(7, ((Coupon) type).getAmount());
					preState.setDouble(8, ((Coupon) type).getPrice());
					preState.setString(9, ((Coupon) type).getImage());
					preState.setInt(10, ((Coupon) type).getId());
				}
				
			} else {
				throw new DAOException("Failed to initialize a PreparedStatement, 'action' String has to be either 'add' or 'update'.");
			}
			
		} catch (SQLException e) {
			throw new DAOException("Failed to initialize a PreparedStatement.", e);
		}
		
		return preState;
	}
	
	/**
	 * Converts a ResultSet from SQL to a Java ArrayList. 
	 * @throws DAOException if an unknown Interface uses this method
	 * (Interface has to be CompanyInterfaceDAO/CustomerInterfaceDAO/CouponInterfaceDAO/PurchaseInterfaceDAO). */
	protected ArrayList<T> RsToArrayList(ResultSet rs) throws DAOException {
		
		try {
			
			ArrayList<Bean> beans = new ArrayList<Bean>();
			
			if (this instanceof CompanyInterfaceDAO) {
				
				while (rs.next()) {
					Company company = new Company();
					company.setId(rs.getInt(1));
					company.setName(rs.getString(2));
					company.setEmail(rs.getString(3));
					company.setPassword(rs.getString(4));
					beans.add(company);
				}
				
			} else if (this instanceof CustomerInterfaceDAO) {
				
				while(rs.next()) {
					Customer customer = new Customer();
					customer.setId(rs.getInt(1));
					customer.setFirstName(rs.getString(2));
					customer.setLastName(rs.getString(3));
					customer.setEmail(rs.getString(4));
					customer.setPassword(rs.getString(5));
					beans.add(customer);
				}
				
			} else if (this instanceof CouponInterfaceDAO) {
				
				while(rs.next()) {
					Coupon coupon = new Coupon();
					coupon.setId(rs.getInt(1));
					coupon.setCompanyID(rs.getInt(2));
					coupon.setCategory(Category.turnIndexToCategory(rs.getInt(3)));
					coupon.setTitle(rs.getString(4));
					coupon.setDescription(rs.getString(5));
					coupon.setStartDate(rs.getDate(6));
					coupon.setEndDate(rs.getDate(7));
					coupon.setAmount(rs.getInt(8));
					coupon.setPrice(rs.getDouble(9));
					coupon.setImage(rs.getString(10));
					beans.add(coupon);
				}
				
			} else if (this instanceof PurchaseInterfaceDAO) {
				
				while(rs.next()) {
					Purchase purchase = new Purchase();
					purchase.setCustomerID(rs.getInt(1));
					purchase.setCouponID(rs.getInt(2));
					beans.add(purchase);
				}
				
				
			} else {
				throw new DAOException("Unknown DAO Interface uses this method, Interface has to be "
						+ "CompanyInterfaceDAO/CustomerInterfaceDAO/CouponInterfaceDAO/PurchaseInterfaceDAO.");
			}
			
			@SuppressWarnings("unchecked")
			ArrayList<T> types = (ArrayList<T>) beans; // This cast is a checked cast --- type <T> has to be an extension of Bean.
			return types;
			
		} catch (SQLException e) {
			throw new DAOException("Failed to turn ResultSet to ArrayList.", e);
		}
	}
			
	/**
	 * Sets the 'table' String to the table we need to access in SQL.
	 * @throws DAOException if an unknown Interface uses this method
	 * (Interface has to be CompanyInterfaceDAO/CustomerInterfaceDAO/CouponInterfaceDAO/PurchaseInterfaceDAO). */
	protected String tableType() throws DAOException {
		
		String table = null;
		if (this instanceof CompanyInterfaceDAO) {
			table = "`companies`";
		} else if (this instanceof CustomerInterfaceDAO) {
			table = "`customers`";
		} else if (this instanceof CouponInterfaceDAO) {
			table = "`coupons`";
		} else if (this instanceof PurchaseInterfaceDAO) {
			table = "`purchases`";
		} else
			throw new DAOException("Unknown DAO Interface, Interface has to be "
					+ "CompanyInterfaceDAO/CustomerInterfaceDAO/CouponInterfaceDAO/PurchaseInterfaceDAO.");
		
		return table;
	}
	
}


