package org.unclazz.dom1;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * {@link Function}によりフィルタと変換を行いその結果を返すクエリ.
 * <p>具象クラスは{@link #source(NodeKind)}により入力ソースを規定し、
 * {@link #function()}により入力ソースから得られたオブジェクトの変換とフィルタリングを規定する。</p>
 * <p>このクエリは問合せ対象XMLノードを引数にて{@link #source(NodeKind)}を呼び出す。
 * そしてそこから返された{@link Iterable}の要素のそれぞれを引数として
 * {@link #function()}が返す{@link Function}を実行する。
 * {@link Function#apply(Object)}を呼び出した結果が{@code null}以外であれば
 * そのオブジェクトは問合せ結果のリストの要素となる。
 * 一方結果が{@code null}であった場合、その値は問合せ結果の{@link List}には含まれない。</p>
 *
 * @param <A> 関数の引数の型
 * @param <B> 関数の戻り値の型
 */
public abstract class FunctionalListQuery<A,B> implements ListQuery<B> {
	/**
	 * 関数の適用対象となるオブジェクトを内包した{@link Iterable}を返す.
	 * <p>{@link Iterable}インスタンスから取得された要素は{@link #finalize()}が返す関数の引数として利用される。
	 * この要素は{@code null}であってはならない。</p>
	 * @param n XMLノード
	 * @return {@link Iterable}インスタンス
	 */
	protected abstract Iterable<A> source(NodeKind n);
	/**
	 * XMLノードから抽出されたオブジェクトをフィルタし変換するための関数を返す.
	 * <p>{@link #source(NodeKind)}から返された
	 * 当該オブジェクトに対して適用された関数が{@code null}を返した場合、
	 * 当該オブジェクトに対応する値（つまり{@code null}）はクエリの問合せ結果から除外される。</p>
	 * @return 関数オブジェクト
	 */
	protected abstract Function<A,B> function();
	
	/**
	 * 問合せ結果の最初の1件だけを返すクエリを返す.
	 * @return クエリ
	 * @throws NoSuchElementException 問合せ結果が0件である場合
	 */
	public Query<B> one() {
		final ListQuery<B> base = this;
		return new Query<B>() {
			@Override
			public B queryFrom(NodeKind n) {
				final List<B> one = base.queryFrom(n);
				if (one.isEmpty()) {
					throw new NoSuchElementException("No item exsists in query result.");
				}
				return one.get(0);
			}
		};
	}
	
	@Override
	public List<B> queryFrom(NodeKind n) {
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
	 * @param <C> 別の関数の戻り値の型
	 */
	public<C> FunctionalListQuery<A,C> and(final Function<B,C> g) {
		final FunctionalListQuery<A,B> base = this;
		return new FunctionalListQuery<A, C>() {
			@Override
			public Iterable<A> source(NodeKind n) {
				return base.source(n);
			}
			@Override
			public Function<A, C> function() {
				return SyntheticFunction.synthesize(base.function(), g);
			}
		};
	}
}
