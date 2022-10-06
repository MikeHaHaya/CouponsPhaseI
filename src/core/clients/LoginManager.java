package core.clients;
// Singleton

import core.exceptions.CouponSystemException;
import core.exceptions.LoginException;
import core.facades.AdminFacade;
import core.facades.ClientFacade;
import core.facades.CompanyFacade;
import core.facades.CustomerFacade;

public class LoginManager {

	/**
	 * A private constructor. */
	private LoginManager() {
		
	}
	
	/**
	 * Calls the constructor and creates a new LoginManager if not exist. */
	private static LoginManager instance = new LoginManager();
	
	/**
	 * Calls the instance method and gets it's value. */
	public static LoginManager getInstance() {
		return instance;
	}
	
	/**
	 * Logs in the user. */
	public ClientFacade login (String email, String password, ClientType clientType) throws CouponSystemException {
		
		ClientFacade facade = null;
		
		switch (clientType) {
		
		case Administrator:
			facade = new AdminFacade();
			break;
		case Company:
			facade = new CompanyFacade();
			break;
		case Customer:
			facade = new CustomerFacade();
			break;
		default:
			throw new LoginException("Failed to log in, client has to be either an administrator, a company or a customer.");
		}
		
		if (facade.login(email, password))
			return facade;
		else
			throw new LoginException("Failed to log in, the email or password you entered is incorrect. ");
		
	}
}
