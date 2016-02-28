package org.unclazz.dom1;

public class BiFunction<A,B,C> implements Function<A,C> {
	public static<A,B,C> BiFunction<A,B,C> synthesize(Function<A,B> left, Function<B,C> right) {
		return new BiFunction<A, B, C>(left, right);
	}
	
	private final Function<A,B> left;
	private final Function<B,C> right;
	
	private BiFunction(Function<A,B> left, Function<B,C> right) {
		if (left == null || right == null) {
			throw new NullPointerException();
		}
		this.left = left;
		this.right = right;
	}
	
	public<D> BiFunction<A,C,D> and(Function<C,D> other) {
		return new BiFunction<A,C,D>(this, other);
	}
	
	@Override
	public C apply(A target) {
		final B leftResult = left.apply(target);
		if (leftResult == null) {
			return null;
		}
		return right.apply(leftResult);
	}
}
