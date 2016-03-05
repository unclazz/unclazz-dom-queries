package org.unclazz.dom.queries;

public class QueryException extends RuntimeException {
	private static final long serialVersionUID = 153592748231562388L;
	
	public QueryException(final String message) {
		super(message);
	}
	
	public QueryException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	public QueryException(final Throwable cause) {
		super(cause);
	}
}
