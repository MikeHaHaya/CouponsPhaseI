package core.beans;

import java.io.Serializable;

public enum Category implements Serializable {

	Food, Electricity, Restaurant, Vacation; // Coupon area categories
	
	private static final long serialVersionUID = 1L; // Version
	
	/**
	 * Turns a Category enum to an SQL `categories` index. */
	public int getCategorySQLIndex() {
		
		switch (this) {
		
		case Food:
			return 1;
		case Electricity:
			return 2;
		case Restaurant:
			return 3;
		case Vacation:
			return 4;
		default:
			return 0;
		}
	}
	
	/**
	 * Turns an SQL `categories` index to Category enum. */
	public static Category turnIndexToCategory(int index) {
		
		switch (index) {
		
		case 1:
			return Food;
		case 2:
			return Electricity;
		case 3:
			return Restaurant;
		case 4:
			return Vacation;
		default:
			return null;
		}
	}

	/**
	 * Gets the serial version of the Category class. */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
