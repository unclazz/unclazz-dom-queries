package org.unclazz.dom1.utils;

public interface QueryOperator<R> extends ListQuery<R> {
	QueryOperator<R> and(ListQuery<R> q);
	QueryOperator<R> or(ListQuery<R> q);
}
