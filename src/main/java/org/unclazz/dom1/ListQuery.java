package org.unclazz.dom1;

import java.util.List;

/**
 * {@link Query}のヴァリアント.
 * <p>{@link List}を返すクエリの型パラメータ指定を簡便化するためのインターフェース。</p>
 *
 * @param <R> クエリの戻り値であるリストの型
 */
public interface ListQuery<R> extends Query<List<R>> {
	@Override
	List<R> queryFrom(NodeKind n);
}
