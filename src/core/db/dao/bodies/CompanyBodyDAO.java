package core.db.dao.bodies;

import java.util.ArrayList;

import core.beans.Company;
import core.db.dao.interfaces.CompanyInterfaceDAO;
import core.exceptions.CouponSystemException;
import core.exceptions.DAOException;

/**
 * This class gives the Company object the ability to connect with the database. */
public class CompanyBodyDAO extends BodyDAO<Company> implements CompanyInterfaceDAO {
	
	
	/**
	 * A no parameter constructor for CompanyBodyDAO. */
	public CompanyBodyDAO() throws CouponSystemException {
		super();
	}
	
	
	
	/**
	 * Checks if the company exist in the database. */
	@Override
	public boolean isExists(String email, String password) throws CouponSystemException {
		
		try {
			return isTypeExist(email, password);
			
		} catch (DAOException e) {
			throw new DAOException("Failed to check if the company exist in the database.", e);
		}
	}

	/**
	 * Adds a company to the database. */
	@Override
	public void add(Company company) throws CouponSystemException {
		
		try {
			addType(company);
			
		} catch (DAOException e) {
			throw new DAOException("Failed to add the company to the database.", e);
		}

	}

	/** 
	 * Updates a company from the database. */
	@Override
	public void update(Company company) throws CouponSystemException {
		
		try {
			updateType(company);
			
		} catch (DAOException e) {
			throw new DAOException("Failed to update the company from the database.", e);
		}
		
	}

	/**
	 * Deletes a company from the database. */
	@Override
	public void delete(int id) throws CouponSystemException {
		
		try {
			deleteType(id);
			
		} catch (DAOException e) {
			throw new DAOException("Failed to delete the company from the database.", e);
		}

	}

	/**
	 * Gets an ArrayList representation of companies from the database. */
	@Override
	public ArrayList<Company> getAll() throws CouponSystemException {
		
		try {
			return getAllType();
			
		} catch (DAOException e) {
			throw new DAOException("Failed to get all companies from the database.", e);
		}
		
	}

	/**
	 * Gets a single company object from the database using it's ID. */
	@Override
	public Company getOne(int id) throws CouponSystemException {
		
		try {
			return getOneType(id);
			
		} catch (DAOException e) {
			throw new DAOException("Failed to get the company from the database.", e);
		}
	}
	
	/**
	 * Gets a single company object from the database using it's email and password. */
	@Override
	public Company getOne(String email, String password) throws CouponSystemException {
		
		try {
			return getOneType(email, password);
			
		} catch (DAOException e) {
			throw new DAOException("Failed to get the company from the database.", e);
		}
	}

}
