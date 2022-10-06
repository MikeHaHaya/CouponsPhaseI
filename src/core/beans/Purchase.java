package core.beans;

public class Purchase implements Bean {

	private static final long serialVersionUID = 1L; // Version
	
	private int customerID, couponID;

	/** An empty purchase constructor*/
	public Purchase() {
		
	}

	/**
	 * Gets the the ID of the customer who purchased. */
	public int getCustomerID() {
		return customerID;
	}

	/** 
	 * Sets the the ID of the customer who purchased. */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	/** 
	 * Gets the ID of the purchased coupon. */
	public int getCouponID() {
		return couponID;
	}

	/** 
	 * Sets the ID of the purchased coupon.*/
	public void setCouponID(int couponID) {
		this.couponID = couponID;
	}

	/**
	 * Gets the purchase's String representation */
	@Override
	public String toString() {
		return "Purchase [customerID=" + customerID + ", couponID=" + couponID + "]";
	}
	
	
	/**
	 * Gets the serial version of the Purchase class. */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
