package core.exceptions;

public class DAOException extends CouponSystemException {

	private static final long serialVersionUID = 1L; // Version

	// A constructor
	public DAOException() {
		super();
	}

	// A constructor
	public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	// A constructor
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	// A constructor
	public DAOException(String message) {
		super(message);
	}

	// A constructor
	public DAOException(Throwable cause) {
		super(cause);
	}

	
}
