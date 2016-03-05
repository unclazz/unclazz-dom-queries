package org.unclazz.dom.queries;

/**
 * 関数オブジェクトのためのインターフェース.
 * <p>このオブジェクトは{@link FunctionalListQuery}などで利用される。</p>
 *
 * @param <A> 引数の型
 * @param <B> 戻り値の型
 */
public interface Function<A,B> {
	/**
	 * 関数を実行する.
	 * @param target 引数
	 * @return 戻り値
	 */
	B apply(A target);
}
