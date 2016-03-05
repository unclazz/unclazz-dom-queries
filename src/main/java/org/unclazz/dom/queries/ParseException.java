package org.unclazz.dom.queries;

public class ParseException extends Exception {
	private static final long serialVersionUID = -1202541016708105478L;
	
	public ParseException(final String message) {
		super(message);
	}
	public ParseException(final String message, final Throwable cause) {
		super(message, cause);
	}
	public ParseException(final Throwable cause) {
		super(cause);
	}
}
