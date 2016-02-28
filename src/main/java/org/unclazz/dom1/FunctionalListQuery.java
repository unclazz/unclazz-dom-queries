package org.unclazz.dom1;

import java.util.ArrayList;
import java.util.List;

abstract class FunctionalListQuery<A,B> implements ListQuery<B> {
	public abstract Iterable<A> source(UZNode n);
	public abstract Function<A,B> function();
	
	@Override
	public List<B> queryFrom(UZNode n) {
		final ArrayList<B> result = new ArrayList<B>();
		final Function<A,B> func = function();
		for (final A a : source(n)) {
			final B b = func.apply(a);
			if (b != null) {
				result.add(b);
			}
		}
		result.trimToSize();
		return result;
	}
	
	public<C> FunctionalListQuery<A,C> and(final Function<B,C> g) {
		final FunctionalListQuery<A,B> base = this;
		return new FunctionalListQuery<A, C>() {
			@Override
			public Iterable<A> source(UZNode n) {
				return base.source(n);
			}

			@Override
			public Function<A, C> function() {
				return BiFunction.synthesize(base.function(), g);
			}
		};
	}
}
