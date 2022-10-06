package core.threads;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import core.beans.Coupon;
import core.db.dao.bodies.CouponBodyDAO;
import core.db.dao.bodies.PurchaseBodyDAO;
import core.exceptions.CouponSystemException;

public class CouponExpirationDailyJob implements Runnable {

	private Thread t;
	private CouponBodyDAO couponDAO;
	private PurchaseBodyDAO purchaseDAO;
	private boolean quit = false;
	
	/**
	 * This constructor initializes the DAO connection and starts the thread. */
	public CouponExpirationDailyJob() throws CouponSystemException {
		
		couponDAO = new CouponBodyDAO();
		purchaseDAO = new PurchaseBodyDAO();
		t = new Thread(this);
		t.setDaemon(true);
		t.start();
	}

	/**
	 * Runs the thread, runs in loop until the stop method is activated. */
	@Override
	public void run() {
		
		while (!quit) {
			
			ArrayList<Coupon> coupons;
			Date now = new Date();
			
			try {
				coupons = couponDAO.getAll();
				for (Coupon coupon : coupons) {
					if (coupon.getEndDate().before(now)) {
						purchaseDAO.deleteByCoupon(coupon.getId());
						couponDAO.delete(coupon.getId());
					}
				}
				long delay = delayUntilMidnight();
				Thread.sleep(delay);
				
			} catch (CouponSystemException e) {
				System.out.println(e.getMessage());
			} catch (InterruptedException e) {
				
			}
			
		}
		
	}
	
	/**
	 * Interrupts the thread variable. */
	public void stop() {
		
		quit = true;
		t.interrupt();
	}
	
	/**
	 * Gets how many milliseconds left until next midnight. */
	public static long delayUntilMidnight() {
		
		// Delay to work again at midnight
		Calendar now = Calendar.getInstance();
		Calendar midnight = Calendar.getInstance();
		midnight.set(Calendar.HOUR_OF_DAY, 0);
		midnight.set(Calendar.MINUTE, 0);
		midnight.set(Calendar.SECOND, 0);
		
		if (now.after(midnight)) {
			midnight.add(Calendar.DATE, 1);
		}
		
		long delay = midnight.getTimeInMillis() - now.getTimeInMillis();
		return delay;
	}
}
