package org.unclazz.dom1.utils;

import java.util.ArrayList;
import java.util.List;

class TypedFilterListQuery<R> implements ListQuery<R> {
	private final ListQuery<?> source;
	private final Filter<R> filter;
	private final TypeHandler<R> handler;
	
	TypedFilterListQuery(final ListQuery<?> source, final TypeHandler<R> handler, final Filter<R> filter) {
		this.source = source;
		this.filter = filter;
		this.handler = handler;
	}
	
	@Override
	public List<R> queryFrom(UZNode n) {
		final ArrayList<R> result = new ArrayList<R>();
		for (final Object o : source.queryFrom(n)) {
			final R r = handler.cast(o);
			if (r != null && filter.test(r)) {
				result.add(r);
			}
		}
		result.trimToSize();
		return result;
	}
}
