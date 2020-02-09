package co.crisi.crisiroutine.exceptions;

import java.io.Serializable;

public class NonExistentElementException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NonExistentElementException(String errorMessage) {
		super(errorMessage);
	}

}
