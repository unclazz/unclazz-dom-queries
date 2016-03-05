package org.unclazz.dom.queries;

/**
 * {@link NodeKind}オブジェクトに対して問合せを行うためのインターフェース.
 *
 * @param <R> クエリが返すオブジェクトの型
 */
public interface Query<R> {
	/**
	 * {@link NodeKind}オブジェクトに対して問合せを行いその結果を返す.
	 * @param n 問合せ対象ノード
	 * @return 問合せ結果
	 */
	R queryFrom(NodeKind n);
}
