package core.exceptions;

public class LoginException extends CouponSystemException {

	private static final long serialVersionUID = 1L; // Version

	// A constructor
	public LoginException() {
		super();
	}

	// A constructor
	public LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	// A constructor
	public LoginException(String message, Throwable cause) {
		super(message, cause);
	}

	// A constructor
	public LoginException(String message) {
		super(message);
	}

	// A constructor
	public LoginException(Throwable cause) {
		super(cause);
	}

}
