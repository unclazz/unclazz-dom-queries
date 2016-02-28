package org.unclazz.dom1;

/**
 * 2つの異なる関数を合成してつくられた関数.
 *
 * @param <A> 1つ目の関数の引数の型
 * @param <B> 1つ目の関数の戻り値の型 かつ 2つ目の関数の引数の型
 * @param <C> 2つ目の関数の戻り値の型
 */
public class BiFunction<A,B,C> implements Function<A,C> {
	/**
	 * 2つの関数を合成してつくられた新しい関数を返す.
	 * 
	 * @param left 1つ目の関数
	 * @param right 2つ目の関数
	 * @return 新しい関数
	 * @param <A> 1つ目の関数の引数の型
	 * @param <B> 1つ目の関数の戻り値の型 かつ 2つ目の関数の引数の型
	 * @param <C> 2つ目の関数の戻り値の型
	 */
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
	
	/**
	 * レシーバの関数オブジェクトと引数の関数オブジェクトを合成した新しい関数オブジェクトを返す.
	 * @param other 別の関数
	 * @return 合成された関数
	 * @param <A> レシーバの関数の引数の型
	 * @param <C> レシーバの関数の戻り値の型 かつ 引数で指定された関数の引数の型
	 * @param <D> 引数で指定された関数の戻り値の型
	 */
	public<D> BiFunction<A,C,D> and(Function<C,D> other) {
		return new BiFunction<A,C,D>(this, other);
	}
	
	/**
	 * 1つ目の関数の結果値が{@code null}の場合、このメソッドは即座に{@code null}を返す.
	 * そして2つ目の関数は実行されない。
	 */
	@Override
	public C apply(A target) {
		final B leftResult = left.apply(target);
		if (leftResult == null) {
			return null;
		}
		return right.apply(leftResult);
	}
}
