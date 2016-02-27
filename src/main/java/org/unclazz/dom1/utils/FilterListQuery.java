package org.unclazz.dom1.utils;

import java.util.ArrayList;
import java.util.List;

class FilterListQuery<R> implements ListQuery<R> {
	private final ListQuery<R> source;
	private final Filter<R> filter;
	
	FilterListQuery(final ListQuery<R> source, final Filter<R> filter) {
		this.source = source;
		this.filter = filter;
	}
	
	public List<R> queryFrom(UZNode n) {
		final ArrayList<R> result = new ArrayList<R>();
		for (final R tsn : source.queryFrom(n)) {
			if (filter.test(tsn)) {
				result.add(tsn);
			}
		}
		result.trimToSize();
		return result;
	}
	
	public FilterListQuery<R> and(Filter<R> other) {
		return new FilterListQuery<R>(source, new CompositeFilter<R>(filter, other));
	}
}
