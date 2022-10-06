package core.start.tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import core.clients.ClientType;
import core.clients.LoginManager;
import core.db.ConnectionPool;
import core.exceptions.CouponSystemException;
import core.facades.AdminFacade;
import core.facades.CompanyFacade;
import core.facades.CustomerFacade;
import core.threads.CouponExpirationDailyJob;

public class Test {

	private static final Scanner scan = new Scanner(System.in);
	
	/**
	 * Gives a developer test to test the entire system functioning */
	public static void testAll() {
		
		CouponExpirationDailyJob job = null;
		try {
			job = new CouponExpirationDailyJob();	
			mainMenu();
			
		} catch (CouponSystemException e) {
			System.out.println("Something went wrong, oouldn't start the program.");
			e.printStackTrace();
		} finally {
			end(job);
		}
	}
	
	/**
	 * The start menu that interacts with the developer. */
	public static void startMenu() {
		
		System.out.println("Welcome to CouponsSystem!");
		System.out.println("================================");
		System.out.println();
		System.out.println("This is a test made for developers.");
		System.out.println("The test will run over every aspect in the program automatically,");
		System.out.println("the only request from the developer is to press enter to continue.");
		System.out.println("There are small sleeps of half a second for interface purposes only (more readable and doesn't look like random text).");
		System.out.println();
		System.out.println("At the beginning of the test, you will be shown a menu.");
		System.out.println("It is important to test everything by order (AdminFacade, CompanyFacade, CustomerFacade),");
		System.out.println("otherwise unwanted exceptions will be thrown and the test will be damaged.");
		System.out.println();
		System.out.println("When you are ready, press enter to start the test: ");
		scan.nextLine();
		space();
		
	}
	
	/**
	 * Ends the program appropriately. */
	public static void end(CouponExpirationDailyJob job) {
		
		job.stop();
		scan.close();
		AdminTest.scan.close();
		CompanyTest.scan.close();
		CustomerTest.scan.close();
		
		try {
			ConnectionPool.getInstance().closeAllConnections();
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		System.out.println("Goodbye :)");
	}
	
	/**
	 * Interact with the user to get the ClientType. */
	public static void mainMenu() {
		
		startMenu();
		boolean quit = false;
		
		while (!quit) {
			
		System.out.println();
		System.out.println("To test AdminFacade.................................................enter 1");
		System.out.println("To test CompanyFacade..........................................enter 2");
		System.out.println("To test CustomerFacade.........................................enter 3");
		System.out.println("To clear test and reset database........................enter 4");
		System.out.println("To quit.................................................................................enter 5");
		
		String key = scan.nextLine();
		
		switch (key) {
		
		case "1":
			space();
			adminMenu();
			break;
		case "2":
			space();
			companyMenu();
			break;
		case "3":
			space();
			customerMenu();
			break;
		case "4":
			space();
			clearAll();
			break;
		case "5":
			quit = true;
			break;
		default:
			System.out.println("Please enter one of the following keys: ");
		}
		
		}
		
		
	}

	/**
	 * Interacts with an administrator type user. */
	public static void adminMenu() {
		
		AdminFacade facade = null;
		
		try {
			System.out.println("Logs in as an admin");
			System.out.println("Email: admin@admin.com");
			System.out.println("Password: admin");
			facade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin", ClientType.Administrator);
			System.out.println("Logged in successfully");
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		space();
		
		AdminTest.addCompanies(facade);
		AdminTest.updateCompanies(facade);
		AdminTest.deleteCompanies(facade);
		AdminTest.getAllCompanies(facade);
		AdminTest.getOneCompany(facade);
		AdminTest.addCustomers(facade);
		AdminTest.updateCustomers(facade);
		AdminTest.deleteCustomers(facade);
		AdminTest.getAllCustomers(facade);
		AdminTest.getOneCustomer(facade);
		
		System.out.println("AdminFacade test is over, returning to main menu. ");
		
		space();
	}
	
	/**
	 * Interacts with a company type user. */
	public static void companyMenu() {
		
		CompanyFacade facade = null;
		
		try {
			System.out.println("Logs in as Company1");
			System.out.println("Email: company1@updated-company.com");
			System.out.println("Password: 0000");
			facade = (CompanyFacade) LoginManager.getInstance().login("company1@updated-company.com", "0000", ClientType.Company);
			System.out.println("Logged in successfully");
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		space();
		
		CompanyTest.addCoupons(facade);
		CompanyTest.updateCoupons(facade);
		CompanyTest.deleteCoupons(facade);
		CompanyTest.getAllCoupons(facade);
		CompanyTest.getAllCouponsCat(facade);
		CompanyTest.getAllCouponsMax(facade);
		CompanyTest.getCompanyDetails(facade);
		
		System.out.println("CompanyFacade test is over, returning to main menu. ");
		
		space();
	}
	
	/**
	 * Interacts with a customer type user. */
	public static void customerMenu() {
		
		CustomerFacade facade = null;
		
		try {
			System.out.println("Logs in as Customer1");
			System.out.println("Email: customer1@updated-customer.com");
			System.out.println("Password: 0000");
			facade = (CustomerFacade) LoginManager.getInstance().login("customer1@updated-customer.com", "0000", ClientType.Customer);
			System.out.println("Logged in successfully");
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
		space();
		
		CustomerTest.purchaseCoupons(facade);
		CustomerTest.getCustomerCoupons(facade);
		CustomerTest.getCustomerCouponsCat(facade);
		CustomerTest.getCustomerCouponsMax(facade);
		CustomerTest.getCustomerDetails(facade);
		
		System.out.println("CustomerFacade test is over, returning to main menu. ");
		
		space();
	}
	
	/**
	 * Interacts with the developer to clear database appropriately. */
	public static void clearAll() {
		
		ConnectionPool pool = null;
		Connection connection = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			execute("purchases", connection);
			execute("coupons", connection);
			execute("customers", connection);
			execute("companies", connection);
			System.out.println("Database cleared successfully");
			
		} catch (CouponSystemException e) {
			System.out.println("Failed to clear database.");
			e.printStackTrace();
			
		} catch (SQLException e) {
			System.out.println("Failed to clear database.");
			e.printStackTrace();
			
		} finally { // To make sure we return the connection to the ConnectionPool
			if (connection != null) 
				pool.restoreConnection(connection);
		}
		
		space();
	}
	
	/**
	 * Makes the program sleep for half a second. */
	public static void sleep() {
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Space and sleep. */
	public static void space() {
		
		sleep();
		System.out.println();
		System.out.println("===================================================");
	}
	
	/**
	 * Executes delete statements. */
	public static void execute(String column, Connection connection) throws SQLException {
		
		String sql = "DELETE FROM `" + column + "`";
		PreparedStatement preState = connection.prepareStatement(sql);
		preState.executeUpdate();
	}
	
	
}
