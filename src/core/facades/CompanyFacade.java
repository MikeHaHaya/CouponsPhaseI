package core.facades;

import java.util.ArrayList;

import core.beans.Category;
import core.beans.Company;
import core.beans.Coupon;
import core.exceptions.CouponSystemException;
import core.exceptions.DAOException;
import core.exceptions.FacadeException;

public class CompanyFacade extends ClientFacade {

	private int companyID;
	
	/**
	 * A constructor. */
	public CompanyFacade() throws CouponSystemException {
		super();
	}
	
	/**
	 * Logs in the user. */
	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		
		if (companyDAO.isExists(email, password)) {
			this.companyID = companyDAO.getOne(email, password).getId();
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Adds a coupon to the database. */
	public void addCoupon(Coupon newCoupon) throws CouponSystemException {
		
		ArrayList<Coupon> coupons = getCouponsByCompany(companyID);
		
		for (Coupon oldCoupon : coupons) {
			if (oldCoupon.getTitle().equalsIgnoreCase(newCoupon.getTitle()))
				throw new FacadeException("Failed to add the coupon to the database, a company cannot have two coupons with same title. ");
		}
		couponDAO.add(newCoupon);
	}
	
	/**
	 * Updates a coupon from the database. */
	public void updateCoupon(Coupon newCoupon) throws CouponSystemException {
		
		Coupon coupon = null;
		
		try {
			coupon = couponDAO.getOne(newCoupon.getId());
		} catch (DAOException e) {
			throw new FacadeException("Failed to update the coupon from the database, coupon ID: " + newCoupon.getId()
			+ " does not exist in the database (notice that ID cannot be changed since it is what distinguishes coupons from each other).", e);
		}
		
		if (this.companyID != coupon.getCompanyID())
			throw new FacadeException("Failed to update the coupon from the database, companyID cannot be changed.");
		
		couponDAO.update(coupon);
	}
	
	/**
	 * Deletes a coupon from the database. */
	public void deleteCoupon(int couponID) throws CouponSystemException {
		
		purchaseDAO.deleteByCoupon(couponID);
		couponDAO.delete(couponID);
	}
	
	/**
	 * Gets all coupons created by this company from the database. */
	public ArrayList<Coupon> getCompanyCoupons() throws CouponSystemException {
		
		return getCouponsByCompany(companyID);
	}
	
	/**
	 * Gets all coupons created by this company with category filter from the database. */
	public ArrayList<Coupon> getCompanyCoupons(Category category) throws CouponSystemException {
		
		ArrayList<Coupon> coupons = getCouponsByCompany(companyID);
		return filterCouponsByCategory(coupons, category);
	}

	/**
	 * Gets all coupons created by this company with maximum price filter from the database. */
	public ArrayList<Coupon> getCompanyCoupons(double maxPrice) throws CouponSystemException {
		
		ArrayList<Coupon> coupons = getCouponsByCompany(companyID);
		return filterCouponsByMaxPrice(coupons, maxPrice);
	}
	
	/**
	 * Gets all details about this company from the database. */
	public String getCompanyDetails() throws CouponSystemException {
		
		Company company = companyDAO.getOne(companyID);
		
		String details = "ID: " + companyID + "\n"
				            + "Name: " + company.getName() + "\n"
				            + "Email: " + company.getEmail() + "\n"
				            + "Coupons owned: ";
		
		ArrayList<Coupon> coupons = getCouponsByCompany(companyID);
		details += coupons.size();
		
		return details;
	}
}
