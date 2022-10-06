package core.exceptions;

public class FacadeException extends CouponSystemException {

	private static final long serialVersionUID = 1L; // Version

	// A constructor
	public FacadeException() {
		super();
	}

	// A constructor
	public FacadeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	// A constructor
	public FacadeException(String message, Throwable cause) {
		super(message, cause);
	}

	// A constructor
	public FacadeException(String message) {
		super(message);
	}

	// A constructor
	public FacadeException(Throwable cause) {
		super(cause);
	}
	
	

}
