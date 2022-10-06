package core.facades;

import java.util.ArrayList;
import java.util.Date;

import core.beans.Coupon;
import core.beans.Customer;
import core.beans.Purchase;
import core.exceptions.CouponSystemException;
import core.exceptions.FacadeException;
import core.beans.Category;

public class CustomerFacade extends ClientFacade {

	private int customerID;
	
	/**
	 * A constructor. */
	public CustomerFacade() throws CouponSystemException {
		super();
	}

	/**
	 * Logs in the user. */
	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		
		if (customerDAO.isExists(email, password)) {
			this.customerID = customerDAO.getOne(email, password).getId();
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Purchases a coupon. */
	public void purchaseCoupon(Coupon newCoupon) throws CouponSystemException {
		
		ArrayList<Coupon> coupons = getCouponsByCustomer(customerID);
		
		for (Coupon oldCoupon: coupons) {
			if (oldCoupon.getId() == newCoupon.getId())
				throw new FacadeException("Failed to purchase the coupon, a coupon cannot be purchased twice by the same customer. ");
		}
		
		if (newCoupon.getAmount() < 1)
			throw new FacadeException("Failed to purchase the coupon, this coupon has been sold out. ");
		
		Date now = new Date();
		if (newCoupon.getStartDate().after(now) || newCoupon.getEndDate().before(now))
			throw new FacadeException("Failed to purchase the coupon, this coupon hasn't started yet or it's out of date. ");
		
		Purchase purchase = new Purchase();
		purchase.setCouponID(newCoupon.getId());
		purchase.setCustomerID(customerID);
		purchaseDAO.add(purchase);
		newCoupon.setAmount(newCoupon.getAmount() - 1);
	}
	
	/**
	 * Gets all coupons purchased by this customer from the database. */
	public ArrayList<Coupon> getCustomerCoupons() throws CouponSystemException {
		
		return getCouponsByCustomer(customerID);
	}
	
	/**
	 * Gets all coupons purchased by this customer with category filter from the database. */
	public ArrayList<Coupon> getCustomerCoupons(Category category) throws CouponSystemException {
		
		ArrayList<Coupon> coupons = getCouponsByCustomer(customerID);
		return filterCouponsByCategory(coupons, category);
	}
	
	/**
	 * Gets all coupons purchased by this customer with maximum price filter from the database. */
	public ArrayList<Coupon> getCustomerCoupons(double maxPrice) throws CouponSystemException {
		
		ArrayList<Coupon> coupons = getCouponsByCustomer(customerID);
		return filterCouponsByMaxPrice(coupons, maxPrice);
	}
	
	/**
	 * Gets all details about this customer from the database. */
	public String getCustomerDetails() throws CouponSystemException {
		
		Customer customer = customerDAO.getOne(customerID);
		
		
		String details = "ID: " + customerID + "\n"
				            + "Full Name: " + customer.getFirstName() + " " + customer.getLastName() +"\n"
				            + "Email: " + customer.getEmail() + "\n"
				            + "Coupons purchased: ";
		
		ArrayList<Coupon> coupons = getCouponsByCustomer(customerID);
		details += coupons.size();
		
		return details;

	}

}
