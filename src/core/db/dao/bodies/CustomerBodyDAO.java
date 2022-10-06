package core.db.dao.bodies;

import java.util.ArrayList;

import core.beans.Customer;
import core.db.dao.interfaces.CustomerInterfaceDAO;
import core.exceptions.CouponSystemException;
import core.exceptions.DAOException;

/**
 * This class gives the Customer object the ability to connect with the database. */
public class CustomerBodyDAO extends BodyDAO<Customer> implements CustomerInterfaceDAO {
	
	
	/**
	 * An empty constructor with a 'getInstance' for the ConnectionPool Object. */
	public CustomerBodyDAO() throws CouponSystemException {
		super();
	}

	
	
	/**
	 * Checks if the customer exist in the database. */
	@Override
	public boolean isExists(String email, String password) throws CouponSystemException {
		
		try {
			return isTypeExist(email, password);
			
		} catch (DAOException e) {
			throw new DAOException("Failed to check if the customer exist in the database.", e);
		}
		
	}

	/**
	 * Adds a customer to the database. */
	@Override
	public void add(Customer customer) throws CouponSystemException {
		
		try {
			addType(customer);
			
		} catch (DAOException e) {
			throw new DAOException("Failed to add the customer to the database.", e);
		}
		
	}

	/** 
	 * Updates a customer from the database. */
	@Override
	public void update(Customer customer) throws CouponSystemException {
		
		try {
			
			updateType(customer);
			
		} catch (DAOException e) {
			throw new DAOException("Failed to update the customer from the database.", e);
		}
		
	}

	/**
	 * Deletes a customer from the database. */
	@Override
	public void delete(int id) throws CouponSystemException {
		
		try {
			deleteType(id);
			
		} catch (DAOException e) {
			throw new DAOException("Failed to delete the customer from the database.", e);
		}
		
	}

	/**
	 * Gets an ArrayList representation of customers from the database. */
	@Override
	public ArrayList<Customer> getAll() throws CouponSystemException {
		
		try {
			return getAllType();
			
		} catch (DAOException e) {
			throw new DAOException("Failed to get all customers from the database.", e);
		}
		
	}

	/**
	 * Gets a single customer object from the database using it's ID. */
	@Override
	public Customer getOne(int id) throws CouponSystemException {
		
		try {
			return getOneType(id);
			
		} catch (DAOException e) {
			throw new DAOException("Failed to get the customer from the database.", e);
		}
	}

	/**
	 * Gets a single customer object from the database using it's email and password. */
	@Override
	public Customer getOne(String email, String password) throws CouponSystemException {

		try {
			return getOneType(email, password);
			
		} catch (DAOException e) {
			throw new DAOException("Failed to get the customer from the database.", e);
		}
	}
	
}
