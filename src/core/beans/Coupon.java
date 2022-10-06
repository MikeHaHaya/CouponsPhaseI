package core.beans;

import java.sql.Date;

public class Coupon implements Bean {

	private static final long serialVersionUID = 1L; // Version
	
	private int id, companyID;
	private Category category;
	private String title, description, image;
	private Date startDate, endDate;
	private int amount;
	private double price;
	
	/**
	 * An empty constructor. */
	public Coupon() {
	}

	/**
	 * Gets the coupon's ID. */
	public int getId() {
		return id;
	}

	/**
	 * Sets the coupon's ID. */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the company's id that owns the coupon. */
	public int getCompanyID() {
		return companyID;
	}

	/**
	 * Sets the company's id that owns the coupon. */
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	/**
	 * Gets the coupon's category. */
	public Category getCategory() {
		return category;
	}

	/**
	 * Sets the coupon's category. */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Gets the coupon's title. */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the coupon's title. */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the coupon's description. */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the coupon's description. */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the coupon's image. */
	public String getImage() {
		return image;
	}

	/**
	 * Sets the coupon's image. */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Gets the date the coupon is available from. */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the date the coupon is available from. */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the date the coupon expires. */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Gets the date the coupon expires. */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the amount of coupons of this kind. */
	public int getAmount() {
		return amount;
	}

	/**
	 * Sets the amount of coupons of this kind. */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * Gets the coupon's price. */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the coupon's price. */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Gets the coupon's string representation. */
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", companyID=" + companyID + ", category=" + category + ", title=" + title
				+ ", description=" + description + ", image=" + image + ", startDate=" + startDate + ", endDate="
				+ endDate + ", amount=" + amount + ", price=" + price + "]";
	}

	
	
	/**
	 * Gets the serial version of the Coupon class. */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
