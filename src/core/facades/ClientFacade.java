package core.facades;

import java.util.ArrayList;
import java.util.Iterator;

import core.beans.Category;
import core.beans.Coupon;
import core.beans.Purchase;
import core.db.dao.bodies.CompanyBodyDAO;
import core.db.dao.bodies.CouponBodyDAO;
import core.db.dao.bodies.CustomerBodyDAO;
import core.db.dao.bodies.PurchaseBodyDAO;
import core.exceptions.CouponSystemException;

public abstract class ClientFacade {

	protected CompanyBodyDAO companyDAO;
	protected CustomerBodyDAO customerDAO;
	protected CouponBodyDAO couponDAO;
	protected PurchaseBodyDAO purchaseDAO;
	
	protected ClientFacade() throws CouponSystemException {
		
		companyDAO = new CompanyBodyDAO();
		customerDAO = new CustomerBodyDAO();
		couponDAO = new CouponBodyDAO();
		purchaseDAO = new PurchaseBodyDAO();
	}
	
	// Tries to log in into the system and return if succeeded
	public abstract boolean login(String email, String password) throws CouponSystemException;
	
	/**
	 * Gets coupons by a company using companyID. */
	protected ArrayList<Coupon> getCouponsByCompany(int companyID) throws CouponSystemException {
		
		ArrayList<Coupon> coupons = couponDAO.getAll();
		for (Coupon coupon : coupons) {
			if (coupon.getCompanyID() != companyID)
				coupons.remove(coupon);
		}
		
		return coupons;
	}
	
	/**
	 * Gets coupons by a customer using customerID. */
	protected ArrayList<Coupon> getCouponsByCustomer(int customerID) throws CouponSystemException {
		
		ArrayList<Purchase> purchases = purchaseDAO.getAll();
		return purchasesToCoupons(purchases);
	}
	
	/**
	 * Filters coupons to only include those with category same as entered. */
	protected ArrayList<Coupon> filterCouponsByCategory(ArrayList<Coupon> coupons, Category category) {
	
		Iterator<Coupon> it = coupons.iterator();
		
		while (it.hasNext()) {
			if (!(it.next().getCategory().equals(category))) 
				it.remove();
		}
		return coupons;
	}
	
	/**
	 * Filters coupons to only include those with price lower or equal from the price entered entered. */
	protected ArrayList<Coupon> filterCouponsByMaxPrice(ArrayList<Coupon> coupons, double maxPrice) {
		
		Iterator<Coupon> it = coupons.iterator();
		
		while (it.hasNext()) {
			if ((it.next().getPrice() > maxPrice)) 
				it.remove();
		}
		return coupons;
	}
	
	/**
	 * Filters purchases to only include those with customerID same as entered. */
	protected ArrayList<Purchase> getPurchasesByCustomer(int customerID) throws CouponSystemException {
		
		ArrayList<Purchase> purchases = purchaseDAO.getAll();
		Iterator<Purchase> it = purchases.iterator();
		
		while (it.hasNext()) {
			if (it.next().getCustomerID() != customerID)
				it.remove();
		}
		return purchases;
	}
	
	/**
	 * Turns a purchases ArrayList to a coupons ArrayList using couponID. */
	protected ArrayList<Coupon> purchasesToCoupons(ArrayList<Purchase> purchases) throws CouponSystemException {
		
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		for (Purchase purchase : purchases) {
			int id =purchase.getCouponID();
			coupons.add(couponDAO.getOne(id));
		}
		
		return coupons;
	}
}
