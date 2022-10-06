package core.db.dao.bodies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import core.beans.Purchase;
import core.db.dao.interfaces.PurchaseInterfaceDAO;
import core.exceptions.CouponSystemException;
import core.exceptions.DAOException;

/**
 * This class gives the Purchase object the ability to connect with the database. */
public class PurchaseBodyDAO extends BodyDAO<Purchase> implements PurchaseInterfaceDAO {

	/**
	 * A no parameter constructor for PurchaseBodyDAO. */
	public PurchaseBodyDAO() throws CouponSystemException {
		super();
	}

	
	/**
	 * Adds a purchase to the database. */
	@Override
	public void add(Purchase purchase) throws CouponSystemException {
		
		try {
			addType(purchase);
		} catch (DAOException e) {
			throw new DAOException("Failed to add the purchase to the database.", e);
		}

	}

	/**
	 * Deletes a purchase from the database. */
	@Override
	public void delete(int customerID, int couponID) throws CouponSystemException {
		
		Connection connection = pool.getConnection();
		
		try {
			String sql = "DELETE FROM `purchases` WHERE `CustomerID` = " + customerID + " AND `CouponID` = " + couponID;
			PreparedStatement preState = connection.prepareStatement(sql);
			preState.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException("Failed to delete the purchase from the database.", e);
			
		} finally {
			if (connection != null)
				pool.restoreConnection(connection);
		}
	}

	/**
	 * Deletes all purchases from the database with the customerID entered. */
	@Override
	public void deleteByCutomer(int customerID) throws CouponSystemException {

		Connection connection = pool.getConnection();
		
		try {
			String sql = "DELETE FROM `purchases` WHERE `CustomerID` = " + customerID;
			PreparedStatement preState = connection.prepareStatement(sql);
			preState.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException("Failed to delete the purchase from the database.", e);
			
		} finally {
			if (connection != null)
				pool.restoreConnection(connection);
		}
	}

	/**
	 * Deletes all purchases from the database with the couponID entered. */
	@Override
	public void deleteByCoupon(int couponID) throws CouponSystemException {
		
		Connection connection = pool.getConnection();
		
		try {
			String sql = "DELETE FROM `purchases` WHERE `CouponID` = " + couponID;
			PreparedStatement preState = connection.prepareStatement(sql);
			preState.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException("Failed to delete the purchase from the database.", e);
			
		} finally {
			if (connection != null)
				pool.restoreConnection(connection);
		}

	}

	/**
	 * Gets an ArrayList representation of purchases from the database. */
	@Override
	public ArrayList<Purchase> getAll() throws CouponSystemException {
		
		try {
			return getAllType();
			
		} catch (DAOException e) {
			throw new DAOException("Failed to get all purchases from the database.", e);
		}

	}

}
