package com.skmproject.chatapp.exception;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-07
 */
public class RoomAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 6523847556558216831L;

	/**
	 * 
	 */
	public RoomAlreadyExistsException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public RoomAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RoomAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public RoomAlreadyExistsException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public RoomAlreadyExistsException(Throwable cause) {
		super(cause);
	}

}
