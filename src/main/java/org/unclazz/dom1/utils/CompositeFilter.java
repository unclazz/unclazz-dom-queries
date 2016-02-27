package org.unclazz.dom1.utils;

public class CompositeFilter<T> implements Filter<T> {
	private final Filter<T> left;
	private final Filter<T> right;
	
	CompositeFilter(Filter<T> left, Filter<T> right) {
		this.left = left;
		this.right = right;
	}
	CompositeFilter(Filter<T> left) {
		this.left = left;
		this.right = null;
	}
	
	public CompositeFilter<T> and(Filter<T> other) {
		return new CompositeFilter<T>(this, other);
	}
	
	@Override
	public boolean test(T target) {
		return left.test(target) && (right == null || right.test(target));
	}
}
