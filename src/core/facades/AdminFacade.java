package core.facades;

import java.util.ArrayList;

import core.beans.Company;
import core.beans.Coupon;
import core.beans.Customer;
import core.exceptions.CouponSystemException;
import core.exceptions.DAOException;
import core.exceptions.FacadeException;

public class AdminFacade extends ClientFacade {

	
	/**
	 * A constructor. */
	public AdminFacade() throws CouponSystemException {
		super();
	}

	/**
	 * Logs in the user. */
	@Override
	public boolean login(String email, String password) {
		
		if (email.equalsIgnoreCase("admin@admin.com") &&
			password.equals("admin")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Adds a company to the database. */
	public void addCompany(Company newCompany) throws CouponSystemException {
		
		ArrayList<Company> companies = companyDAO.getAll();
		for (Company oldCompany : companies) {
			
			if (oldCompany.getName().equalsIgnoreCase(newCompany.getName()) ||
				oldCompany.getEmail().equalsIgnoreCase(newCompany.getEmail())) 
				throw new FacadeException("Failed to add the company to the database, A company with a similar name or email already exists in the database. ");
		}
		companyDAO.add(newCompany);
	}
	
	/**
	 * Updates a company from the database. */
	public void updateCompany(Company newCompany) throws CouponSystemException {
		
		Company oldCompany = null;
		
		try {
			oldCompany = companyDAO.getOne(newCompany.getId());
		} catch (DAOException e) {
			throw new FacadeException("Failed to update the company from the database, company ID: " + newCompany.getId()
			+ " does not exist in the database (notice that ID cannot be changed since it is what distinguishes companies from each other).", e);
		}
		
		if (!(oldCompany.getName().equalsIgnoreCase(newCompany.getName())))
			throw new FacadeException("Failed to update the company from the database, the company's name cannot be changed.");
		
		companyDAO.update(newCompany);
		
		
	}
	
	/**
	 * Deletes a company from the database. */
	public void deleteCompany(int companyID) throws CouponSystemException {
		
		ArrayList<Coupon> coupons = getCouponsByCompany(companyID);
		
		for (Coupon coupon : coupons) {
			purchaseDAO.deleteByCoupon(coupon.getId());
			couponDAO.delete(coupon.getId());
		}
		
		companyDAO.delete(companyID);
	}
	
	/**
	 * Gets all companies that exists in the database. */
	public ArrayList<Company> getAllCompanies() throws CouponSystemException {
		
		return companyDAO.getAll();
	}
	
	/**
	 * Gets a single company from the database. */
	public Company getOneCompany(int companyID) throws CouponSystemException {
		
		return companyDAO.getOne(companyID);
	}
	
	/**
	 * Adds a customer to the database. */
	public void addCustomer(Customer newCustomer) throws CouponSystemException {
		
		ArrayList<Customer> customers = customerDAO.getAll();
		for (Customer oldCustomer : customers) {
			if ((oldCustomer.getEmail().equalsIgnoreCase(newCustomer.getEmail())))
				throw new FacadeException("Failed to create a customer, a different customer already has that email.");
		}
		customerDAO.add(newCustomer);
	}
	
	/**
	 * Updates a customer from the database. */
	public void updateCustomer(Customer newCustomer) throws CouponSystemException {
		
		try {
			customerDAO.getOne(newCustomer.getId());
		} catch (DAOException e) {
			throw new FacadeException("Failed to update the customer from the database, customer ID: " + newCustomer.getId()
			+ " does not exist in the database (notice that ID cannot be changed since it is what distinguishes customers from each other).", e);
		}
		
		customerDAO.update(newCustomer);
	}
	
	/**
	 * Deletes a customer from the database. */
	public void deleteCustomer(int customerID) throws CouponSystemException {
		
		purchaseDAO.deleteByCutomer(customerID);
		customerDAO.delete(customerID);
	}
	
	/**
	 * Gets all customers that exists in the database. */
	public ArrayList<Customer> getAllCustomers() throws CouponSystemException {
		
		return customerDAO.getAll();
	}
	
	/**
	 * Gets a single customer from the database. */
	public Customer getOneCustomer(int customerID) throws CouponSystemException {
		
		return customerDAO.getOne(customerID);
	}
	
}
