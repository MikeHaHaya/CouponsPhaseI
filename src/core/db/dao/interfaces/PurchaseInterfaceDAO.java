package core.db.dao.interfaces;

import java.util.ArrayList;

import core.beans.Purchase;
import core.exceptions.CouponSystemException;

public interface PurchaseInterfaceDAO {

	
	public void add(Purchase purchase) throws CouponSystemException;
	
	public void delete(int customerID, int couponID) throws CouponSystemException;
	
	public void deleteByCutomer(int customerID) throws CouponSystemException;
	
	public void deleteByCoupon(int couponID) throws CouponSystemException;
	
	public ArrayList<Purchase> getAll() throws CouponSystemException;
	
}
