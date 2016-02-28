package org.unclazz.dom1;

public abstract class UniFunction<A,B> implements Function<A,B> {
	@Override
	public abstract B apply(A target);
	
	public<C> BiFunction<A,B,C> and(Function<B,C> other) {
		return BiFunction.synthesize(this, other);
	}
}
