package org.unclazz.dom1;

public abstract class FunctionalQuery<A,B> implements Query<B> {
	protected abstract A source(NodeKind n);
	protected abstract Function<A,B> function();
	
	@Override
	public B queryFrom(NodeKind n) {
		final Function<A,B> func = function();
		final A a = source(n);
		if (a == null) {
			return null;
		}
		return func.apply(a);
	}
	
	public<C> FunctionalQuery<A,C> and(final Function<B,C> g) {
		final FunctionalQuery<A,B> base = this;
		return new FunctionalQuery<A, C>() {
			@Override
			public A source(NodeKind n) {
				return base.source(n);
			}
			@Override
			public Function<A, C> function() {
				return SyntheticFunction.synthesize(base.function(), g);
			}
		};
	}
}
