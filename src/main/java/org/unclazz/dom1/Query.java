package org.unclazz.dom1;

/**
 * {@link Nodal}オブジェクトに対して問合せを行うためのインターフェース.
 *
 * @param <R> クエリが返すオブジェクトの型
 */
public interface Query<R> {
	/**
	 * {@link Nodal}オブジェクトに対して問合せを行いその結果を返す.
	 * @param n 問合せ対象ノード
	 * @return 問合せ結果
	 */
	R queryFrom(Nodal n);
}
