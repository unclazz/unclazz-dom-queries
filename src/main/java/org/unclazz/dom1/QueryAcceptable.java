package org.unclazz.dom1;

public interface QueryAcceptable<T> {
	<R> R query(Query<R> q);
}
