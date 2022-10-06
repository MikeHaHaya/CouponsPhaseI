package core.exceptions;

public class CouponSystemException extends Exception {

	private static final long serialVersionUID = 1L; // Version

	// A constructor
	public CouponSystemException() {
		super();
	}

	// A constructor
	public CouponSystemException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	// A constructor
	public CouponSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	// Main use constructor
	public CouponSystemException(String message) {
		super(message);
	}

	// A constructor
	public CouponSystemException(Throwable cause) {
		super(cause);
	}

	
}
