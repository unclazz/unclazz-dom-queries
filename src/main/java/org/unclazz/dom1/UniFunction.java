package org.unclazz.dom1;

/**
 * 任意の型の引数をとり任意の別の型の戻り値を返す関数.
 *
 * @param <A> 引数の型
 * @param <B> 戻り値の型
 */
public abstract class UniFunction<A,B> implements Function<A,B> {
	@Override
	public abstract B apply(A target);
	
	/**
	 * レシーバの関数オブジェクトと引数の関数オブジェクトを合成した新しい関数オブジェクトを返す.
	 * @param other 別の関数
	 * @return 合成された関数
	 */
	public<C> BiFunction<A,B,C> and(Function<B,C> other) {
		return BiFunction.synthesize(this, other);
	}
}
