package core.beans;

import java.util.ArrayList;

public class Company implements Bean {

	private static final long serialVersionUID = 1L; // Version
	
	private int id;
	private String name, email, password;
	private ArrayList<Coupon> coupons;
	
	/**
	 * An empty constructor. */
	public Company() {
		
	}
	
	/**
	 * Gets the company's ID. */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the company's ID. */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the company's name. */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the company's name. */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the company's email. */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the company's email. */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the company's password. */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the company's password. */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the company's coupons. */
	public ArrayList<Coupon> getCoupons() {
		return coupons;
	}
	
	/**
	 * Sets the company's coupons. */
	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}
	
	/**
	 * Gets the company's string representation. */
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", coupons="
				+ coupons + "]";
	}

	/**
	 * This equals method has been overwritten to compare an Object to a Company. */
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Object)) 
			return false;
		
		
		Company other = (Company) obj;
		if (this.getId() != other.getId())
			return false;
		else if (!(this.getName().equalsIgnoreCase(other.getName())))
			return false;
		else if (!(this.getEmail().equalsIgnoreCase(other.getEmail())))
			return false;
		else if (!(this.getPassword().equalsIgnoreCase(other.getPassword())))
			return false;
		else if (!(this.getCoupons().equals(other.getCoupons())))
			return false;
		else
			return true;
		
	}
	
	
	/**
	 * Gets the serial version of the Company class. */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
