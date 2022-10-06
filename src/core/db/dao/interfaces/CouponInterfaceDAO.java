package core.db.dao.interfaces;

import java.util.ArrayList;

import core.beans.Coupon;
import core.exceptions.CouponSystemException;

public interface CouponInterfaceDAO {
	
	
	public void add(Coupon coupon) throws CouponSystemException;
	
	public void update(Coupon coupon) throws CouponSystemException;
	
	public void delete(int id) throws CouponSystemException;
	
	public ArrayList<Coupon> getAll() throws CouponSystemException;
	
	public Coupon getOne(int id) throws CouponSystemException;
	
}
