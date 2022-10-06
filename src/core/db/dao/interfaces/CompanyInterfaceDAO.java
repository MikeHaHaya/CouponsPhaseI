package core.db.dao.interfaces;

import java.util.ArrayList;

import core.beans.Company;
import core.exceptions.CouponSystemException;

public interface CompanyInterfaceDAO {
	
	
	public boolean isExists(String email, String password) throws CouponSystemException;
	
	public void add(Company company) throws CouponSystemException;
	
	public void update(Company company) throws CouponSystemException;
	
	public void delete(int id) throws CouponSystemException;
	
	public ArrayList<Company> getAll() throws CouponSystemException;
	
	public Company getOne(int id) throws CouponSystemException;
	
	public Company getOne(String email, String password) throws CouponSystemException;
	
}
