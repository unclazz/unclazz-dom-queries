package org.unclazz.dom.queries;

import java.util.List;

/**
 * {@link Query}のヴァリアント.
 * <p>{@link List}を返すクエリの型パラメータ指定を簡便化するためのインターフェース。</p>
 * <p>{@link #queryFrom(NodeKind)}を実装する際には、
 * 問合せの結果として妥当な値が1つも存在しないという場合に、
 * {@code null}ではなく空のリストを返すようにしなくてはならない。</p>
 *
 * @param <R> クエリの戻り値であるリストの型
 */
public interface ListQuery<R> extends Query<List<R>> {
	/**
	 * XMLノードに対して問合せを行いその結果を返す.
	 * <p>妥当な結果値が1つも存在しない場合は空のリストを返す。</p>
	 */
	@Override
	List<R> queryFrom(NodeKind n);
}
