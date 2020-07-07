package com.skmproject.chatapp.exception;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-07
 */
public class RoomNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 5373451350679471363L;

	/**
	 * 
	 */
	public RoomNotFoundException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public RoomNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RoomNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public RoomNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public RoomNotFoundException(Throwable cause) {
		super(cause);
	}
	
	

}
