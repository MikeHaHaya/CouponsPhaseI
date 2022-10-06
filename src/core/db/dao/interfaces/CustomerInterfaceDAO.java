package core.db.dao.interfaces;

import java.util.ArrayList;

import core.beans.Customer;
import core.exceptions.CouponSystemException;

public interface CustomerInterfaceDAO {

	
	public boolean isExists(String email, String password) throws CouponSystemException;
	
	public void add(Customer customer) throws CouponSystemException;
	
	public void update(Customer customer) throws CouponSystemException;
	
	public void delete(int id) throws CouponSystemException;
	
	public ArrayList<Customer> getAll() throws CouponSystemException;
	
	public Customer getOne(int id) throws CouponSystemException;
	
	public Customer getOne(String email, String password) throws CouponSystemException;
}
