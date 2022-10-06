package core.beans;

import java.util.ArrayList;

public class Customer implements Bean {

	private static final long serialVersionUID = 1L; // Version
	
	private int id;
	private String firstName, lastName, email, password;
	private ArrayList<Coupon> coupons;
	
	/**
	 * An empty constructor. */
	public Customer() {
	}

	/**
	 * Gets the customer's ID. */
	public int getId() {
		return id;
	}

	/**
	 * Sets the customer's ID. */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the customer's first name. */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the customer's first name. */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the customer's last name. */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the customer's last name. */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the customer's email. */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the customer's email. */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the customer's password. */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the customer's password. */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the coupons owned by the customer. */
	public ArrayList<Coupon> getCoupons() {
		return coupons;
	}

	/**
	 * Sets the coupons owned by the customer. */
	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}

	/**
	 * Gets the company's string representation. */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", coupons=" + coupons + "]";
	}
	
	
	
	/**
	 * Gets the serial version of the Customer class. */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
