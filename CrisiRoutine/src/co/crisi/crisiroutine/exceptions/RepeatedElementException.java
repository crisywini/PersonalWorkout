package co.crisi.crisiroutine.exceptions;

import java.io.Serializable;

public class RepeatedElementException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RepeatedElementException(String errorMessage) {
		super(errorMessage);
	}

}
