package core.start.tests;

import java.util.ArrayList;
import java.util.Scanner;

import core.beans.Category;
import core.beans.Coupon;
import core.db.dao.bodies.CouponBodyDAO;
import core.exceptions.CouponSystemException;
import core.facades.CustomerFacade;

public class CustomerTest {

	protected static final Scanner scan = new Scanner(System.in);
	
	/**
	 * Purchases coupons from CustomerFacade. */
	public static void purchaseCoupons(CustomerFacade facade) {
		
		Test.sleep();
		System.out.println("Auto-adding 2 purchases - Should run smoothly (press enter to continue): ");
		scan.nextLine();
		System.out.println();
		
		
		// Trying to buy coupon1
		CouponBodyDAO couponDAO = null;
		Coupon coupon1 = null;
		try {
			couponDAO = new CouponBodyDAO();
			coupon1 = couponDAO.getOne(101);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		System.out.println("Trying to buy Coupon1: ");
		System.out.println(coupon1);
		
		try {
			facade.purchaseCoupon(coupon1);
			System.out.println("Coupon1 was purchased successfully");
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		Test.sleep();
		System.out.println();
		
		
		// Trying to buy coupon1
		Coupon coupon2 = null;
		try {
			coupon2 = couponDAO.getOne(102);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		System.out.println("Trying to buy Coupon2: ");
		System.out.println(coupon2);
		
		try {
			facade.purchaseCoupon(coupon2);
			System.out.println("Coupon2 was purchased successfully");
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		Test.sleep();
		System.out.println();
		System.out.println("Auto-adding a coupon that was already bought - Should throw an exception (press enter to continue):");
		scan.nextLine();
		System.out.println();
		
		
		// Trying to buy coupon1 again
		System.out.println("Trying to buy Coupon1 again: ");
		System.out.println(coupon1);
		
		try {
			facade.purchaseCoupon(coupon1);
			System.out.println("Coupon1 was purchased successfully");
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		
		Test.space();
	}
	
	/**
	 * Gets all coupons bought by this customer from CustomerFacade. */
	public static void getCustomerCoupons(CustomerFacade facade) {
		
		Test.sleep();
		System.out.println("Auto-getting all coupons bought by this customer - Should run smoothly (press enter to continue): ");
		scan.nextLine();
		System.out.println();
		
		try {
			ArrayList<Coupon> coupons = new ArrayList<Coupon>();
			coupons = facade.getCustomerCoupons();
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
	 * Gets all coupons bought by this customer in the electricity category from CustomerFacade. */
	public static void getCustomerCouponsCat(CustomerFacade facade) {
		
		Test.sleep();
		System.out.println("Auto-getting all coupons bought by this customer in the electricity category - Should run smoothly (press enter to continue): ");
		scan.nextLine();
		System.out.println();
		
		try {
			ArrayList<Coupon> coupons = new ArrayList<Coupon>();
			coupons = facade.getCustomerCoupons(Category.Electricity);
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
	 * Gets all coupons bought by this customer with a max price of 100 from CustomerFacade. */
	public static void getCustomerCouponsMax(CustomerFacade facade) {
		
		Test.sleep();
		System.out.println("Auto-getting all coupons bought by this customer with a max price of 100 - Should run smoothly (press enter to continue): ");
		scan.nextLine();
		System.out.println();
		
		try {
			ArrayList<Coupon> coupons = new ArrayList<Coupon>();
			coupons = facade.getCustomerCoupons(100);
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
	 * Gets more details about this customer. */
	public static void getCustomerDetails(CustomerFacade facade) {
		
		Test.sleep();
		System.out.println("Auto-getting details about this customer - Should run smoothly (press enter to continue): ");
		scan.nextLine();
		System.out.println();
		
		try {
			String details = facade.getCustomerDetails();
			System.out.println("Details received successfully");
			System.out.println(details);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		Test.space();
	}
	
}
