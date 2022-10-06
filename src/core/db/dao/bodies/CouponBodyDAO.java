package core.db.dao.bodies;

import java.util.ArrayList;

import core.beans.Coupon;
import core.db.dao.interfaces.CouponInterfaceDAO;
import core.exceptions.CouponSystemException;
import core.exceptions.DAOException;

/**
 * This class gives the Coupon object the ability to connect with the database. */
public class CouponBodyDAO extends BodyDAO<Coupon> implements CouponInterfaceDAO {
	
	
	/**
	 * An empty constructor with a 'getInstance' for the ConnectionPool Object. */
	public CouponBodyDAO() throws CouponSystemException {
		super();
	}
	
	

	/**
	 * Adds a coupon to the database. */
	@Override
	public void add(Coupon coupon) throws CouponSystemException {
		
		try {
			addType(coupon);
			
		} catch (DAOException e) {
			throw new DAOException("Failed to add the coupon to the database.", e);
		}
		
	}

	/** 
	 * Updates a coupon from the database. */
	@Override
	public void update(Coupon coupon) throws CouponSystemException {
		
		try {
			updateType(coupon);
			
		} catch (DAOException e) {
			throw new DAOException("Failed to update the coupon from the database.", e);
		}
		
	}

	/**
	 * Deletes a coupon from the database. */
	@Override
	public void delete(int id) throws CouponSystemException {
		
		try {
			deleteType(id);
			
		} catch (DAOException e) {
			throw new DAOException("Failed to delete the coupon from the database.", e);
		}
		
	}

	/**
	 * Gets an ArrayList representation of coupons from the database. */
	@Override
	public ArrayList<Coupon> getAll() throws CouponSystemException {
		
		try {
			return getAllType();
			
		} catch (DAOException e) {
			throw new DAOException("Failed to get all coupons from the database.", e);
		}
		
	}

	/**
	 * Gets a single company object from the database using it's ID. */
	@Override
	public Coupon getOne(int id) throws CouponSystemException {
		
		try {
			return getOneType(id);
			
		} catch (DAOException e) {
			throw new DAOException("Failed to get the coupon from the database.", e);
		}
	}
}
