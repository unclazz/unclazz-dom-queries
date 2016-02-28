package org.unclazz.dom1;

/**
 * 任意の型の引数をとり任意の別の型の戻り値を返す関数.
 *
 * @param <A> 引数の型
 * @param <B> 戻り値の型
 */
public abstract class SyntheticFunction<A,B> implements Function<A,B> {
	@Override
	public abstract B apply(A target);
	
	/**
	 * レシーバの関数オブジェクトと引数の関数オブジェクトを合成した新しい関数オブジェクトを返す.
	 * @param other 別の関数
	 * @return 合成された関数
	 * @param <C> 別の関数の戻り値の型
	 */
	public<C> SyntheticFunction<A,C> and(Function<B,C> other) {
		return new SynthesizedFunction<A, B, C>(this, other);
	}
	
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
	public static<A,B,C> SyntheticFunction<A,C> synthesize(Function<A,B> left, Function<B,C> right) {
		return new SynthesizedFunction<A, B, C>(left, right);
	}
	
	private static class SynthesizedFunction<A,B,C> extends SyntheticFunction<A,C> {
		private final Function<A,B> left;
		private final Function<B,C> right;
		
		SynthesizedFunction(Function<A,B> left, Function<B,C> right) {
			if (left == null || right == null) {
				throw new NullPointerException();
			}
			this.left = left;
			this.right = right;
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
}
