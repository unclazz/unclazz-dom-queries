package org.unclazz.dom1;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link Function}によりフィルタと変換を行いその結果を返すクエリ.
 * <p>{@link Function#apply(Object)}が{@code null}を返した場合、
 * その{@code null}値は問合せ結果の{@link List}には含まれない。</p>
 *
 * @param <A> 関数の引数の型
 * @param <B> 関数の戻り値の型
 */
public abstract class FunctionalListQuery<A,B> implements ListQuery<B> {
	/**
	 * 関数の適用対象となるオブジェクトを内包した{@link Iterable}を返す.
	 * @param n XMLノード
	 * @return {@link Iterable}インスタンス
	 */
	protected abstract Iterable<A> source(Nodal n);
	/**
	 * XMLノードから抽出されたオブジェクトをフィルタし変換するための関数を返す.
	 * <p>当該オブジェクトに対して適用された関数が{@code null}を返した場合、
	 * 当該オブジェクトに対応する値（つまり{@code null}）はクエリの問合せ結果から除外される。</p>
	 * @return 関数オブジェクト
	 */
	protected abstract Function<A,B> function();
	
	/**
	 * 問合せ結果の最初の1件だけを返すクエリを返す.
	 * もとになる問合せ結果が0件である場合、このクエリは{@code null}を返す。
	 * @return クエリ
	 */
	public Query<B> one() {
		final ListQuery<B> base = this;
		return new Query<B>() {
			@Override
			public B queryFrom(Nodal n) {
				final List<B> one = base.queryFrom(n);
				return one.isEmpty() ? null : one.get(0);
			}
		};
	}
	
	@Override
	public List<B> queryFrom(Nodal n) {
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
	
	/**
	 * レシーバのクエリが内包する関数に対して引数で指定された関数を合成して新しいクエリをつくる.
	 * @param g 別の関数
	 * @return 新しいクエリ
	 */
	public<C> FunctionalListQuery<A,C> and(final Function<B,C> g) {
		final FunctionalListQuery<A,B> base = this;
		return new FunctionalListQuery<A, C>() {
			@Override
			public Iterable<A> source(Nodal n) {
				return base.source(n);
			}

			@Override
			public Function<A, C> function() {
				return BiFunction.synthesize(base.function(), g);
			}
		};
	}
}
