package core.start.tests;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import core.beans.Category;
import core.beans.Coupon;
import core.exceptions.CouponSystemException;
import core.facades.CompanyFacade;

public class CompanyTest {

	protected static final Scanner scan = new Scanner(System.in);
	
	/**
	 * Adds coupons from CompanyFacade. */
	public static void addCoupons(CompanyFacade facade) {
		
		Test.sleep();
		System.out.println("Auto-adding 3 coupons - Should run smoothly (press enter to continue): ");
		scan.nextLine();
		System.out.println();
		
		// Trying to add coupon1
		Calendar startCal1 = Calendar.getInstance();
		Calendar endCal1 = Calendar.getInstance();
		startCal1.set(2021, 01, 14);
		endCal1.set(2022, 02, 14);
		long startLong1 = startCal1.getTimeInMillis();
		long endLong1 = endCal1.getTimeInMillis();
		
		Coupon coupon1 = new Coupon();
		coupon1.setId(101);
		coupon1.setCompanyID(101);
		coupon1.setCategory(Category.Electricity);
		coupon1.setTitle("Coupon1");
		coupon1.setDescription("Description1");
		coupon1.setImage("Image1");
		coupon1.setStartDate(new Date(startLong1));
		coupon1.setEndDate(new Date(endLong1));
		coupon1.setAmount(10);
		coupon1.setPrice(60.0);
		
		System.out.println("Trying to add Coupon1: ");
		System.out.println(coupon1);
		
		try {
			facade.addCoupon(coupon1);
			System.out.println("Coupon1 was added succesfully");
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		Test.sleep();
		System.out.println();
		
		
		// Trying to add coupon2
		Calendar startCal2 = Calendar.getInstance();
		Calendar endCal2 = Calendar.getInstance();
		startCal2.set(2021, 00, 01);
		endCal2.set(2023, 02, 14);
		long startLong2 = startCal2.getTimeInMillis();
		long endLong2 = endCal2.getTimeInMillis();
		
		Coupon coupon2 = new Coupon();
		coupon2.setId(102);
		coupon2.setCompanyID(101);
		coupon2.setCategory(Category.Food);
		coupon2.setTitle("Coupon2");
		coupon2.setDescription("Description2");
		coupon2.setImage("Image2");
		coupon2.setStartDate(new Date(startLong2));
		coupon2.setEndDate(new Date(endLong2));
		coupon2.setAmount(15);
		coupon2.setPrice(85.0);
		
		System.out.println("Trying to add Coupon2: ");
		System.out.println(coupon2);
		
		try {
			facade.addCoupon(coupon2);
			System.out.println("Coupon2 was added succesfully");
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		Test.sleep();
		System.out.println();
		
		// Trying to add coupon3
		Calendar startCal3 = Calendar.getInstance();
		Calendar endCal3 = Calendar.getInstance();
		startCal3.set(2020, 01, 12);
		endCal3.set(2022, 11, 14);
		long startLong3 = startCal3.getTimeInMillis();
		long endLong3 = endCal3.getTimeInMillis();
		
		Coupon coupon3 = new Coupon();
		coupon3.setId(103);
		coupon3.setCompanyID(101);
		coupon3.setCategory(Category.Restaurant);
		coupon3.setTitle("Coupon3");
		coupon3.setDescription("Description3");
		coupon3.setImage("Image3");
		coupon3.setStartDate(new Date(startLong3));
		coupon3.setEndDate(new Date(endLong3));
		coupon3.setAmount(10);
		coupon3.setPrice(30.0);
		
		System.out.println("Trying to add Coupon3: ");
		System.out.println(coupon3);
		
		try {
			facade.addCoupon(coupon3);
			System.out.println("Coupon3 was added succesfully");
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		
		Test.sleep();
		System.out.println();
		System.out.println("Auto-adding a single coupon with an id duplicate - Should throw an exception (press enter to continue):");
		scan.nextLine();
		System.out.println();
		
		
		// Trying to add coupon3
		Calendar startCal4 = Calendar.getInstance();
		Calendar endCal4 = Calendar.getInstance();
		startCal4.set(2020, 01, 12);
		endCal4.set(2022, 11, 14);
		long startLong4 = startCal4.getTimeInMillis();
		long endLong4 = endCal4.getTimeInMillis();
		
		Coupon coupon4 = new Coupon();
		coupon4.setId(101);
		coupon4.setCompanyID(101);
		coupon4.setCategory(Category.Vacation);
		coupon4.setTitle("Coupon4");
		coupon4.setDescription("Description4");
		coupon4.setImage("Image4");
		coupon4.setStartDate(new Date(startLong4));
		coupon4.setEndDate(new Date(endLong4));
		coupon4.setAmount(10);
		coupon4.setPrice(40.0);
		
		System.out.println("Trying to add Coupon4: ");
		System.out.println(coupon4);
		
		try {
			facade.addCoupon(coupon4);
			System.out.println("Coupon4 was added succesfully");
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		
		Test.space();
	}
	
	/**
	 * Updates coupons from CompanyFacade. */
	public static void updateCoupons(CompanyFacade facade) {
		
		Test.sleep();
		System.out.println("Auto-updating a single coupon with some title and description changes - Should run smoothly (press enter to continue): ");
		scan.nextLine();
		System.out.println();
		
		
		// Trying to update coupon1
		Coupon coupon1 = null;
		
		try {
			coupon1 = facade.getCompanyCoupons().get(0);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		coupon1.setTitle("UpdatedCoupon1");
		coupon1.setDescription("UpdatedDescirption1");
		System.out.println("Trying to update Coupon1");
		System.out.println(coupon1);
		
		try {
			facade.updateCoupon(coupon1);
			System.out.println("Coupon1 was updated successfully");
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		Test.sleep();
		System.out.println();
		System.out.println("Auto-updating a single coupon with id and companyID changes - Should throw an exception (press enter to continue): ");
		scan.nextLine();
		System.out.println();
		
		// Trying to update coupon2
		Coupon coupon2 = null;
		
		try {
			coupon2 = facade.getCompanyCoupons().get(1);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		coupon2.setId(400);
		coupon2.setCompanyID(50);
		System.out.println("Trying to update Coupon2");
		System.out.println(coupon2);
		
		try {
			facade.updateCoupon(coupon2);
			System.out.println("Coupon2 was updated successfully");
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		Test.space();
	}
	
	/** 
	 * Deletes coupons from CompanyFacade. */
	public static void deleteCoupons(CompanyFacade facade) {
		
		Test.sleep();
		System.out.println("Auto-deleting a single company - Should run smoothly (press enter to continue): ");
		scan.nextLine();
		System.out.println();
		
		// Trying to delete coupon2
		Coupon coupon = null;
		
		try {
			coupon = facade.getCompanyCoupons().get(2);
			System.out.println("Trying to delete Coupon3");
			facade.deleteCoupon(coupon.getId());
			System.out.println("Coupon3 was deleted successfully");
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		Test.space();
	}
	
	/**
	 * Gets all coupons by this company from CompanyFacade. */
	public static void getAllCoupons(CompanyFacade facade) {
		
		Test.sleep();
		System.out.println("Auto-getting all coupons by this company - Should run smoothly (press enter to continue): ");
		scan.nextLine();
		System.out.println();
		
		try {
			ArrayList<Coupon> coupons = facade.getCompanyCoupons();
			System.out.println("Coupons received successfully");
			for (Coupon coupon : coupons) {
				System.out.println(coupon);
			}
			
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		Test.space();
	}
	
	/**
	 * Gets all coupons by this company in the electricity category CompanyFacade. */
	public static void getAllCouponsCat(CompanyFacade facade) {
		
		Test.sleep();
		System.out.println("Auto-getting all coupons by this company in the electricity category - Should run smoothly (press enter to continue): ");
		scan.nextLine();
		System.out.println();
		
		try {
			ArrayList<Coupon> coupons = facade.getCompanyCoupons(Category.Electricity);
			System.out.println("Coupons received successfully");
			for (Coupon coupon : coupons) {
				System.out.println(coupon);
			}
			
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		Test.space();
	}
	
	/**
	 * Gets all coupons by this company with a max price of 100 from CompanyFacade. */
	public static void getAllCouponsMax(CompanyFacade facade) {
		
		Test.sleep();
		System.out.println("Auto-getting all coupons by this company with a max price of 100 - Should run smoothly (press enter to continue): ");
		scan.nextLine();
		System.out.println();
		
		try {
			ArrayList<Coupon> coupons = facade.getCompanyCoupons(100);
			System.out.println("Coupons received successfully");
			for (Coupon coupon : coupons) {
				System.out.println(coupon);
			}
			
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		Test.space();
	}
	
	/** 
	 * Gets more details about this company. */
	public static void getCompanyDetails(CompanyFacade facade) {
		
		Test.sleep();
		System.out.println("Auto-getting details about this company - Should run smoothly (press enter to continue): ");
		scan.nextLine();
		System.out.println();
		
		try {
			String details = facade.getCompanyDetails();
			System.out.println("Details received successfully");
			System.out.println(details);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		Test.space();
	}
	
}
