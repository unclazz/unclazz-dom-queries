package org.unclazz.dom.queries;

import org.w3c.dom.Node;

/**
 * {@link Node}のインスタンスを内包するオブジェクトのインターフェース.
 *
 * @param <T> 内包するオブジェクトの型（{@link Node}もしくはそのサブ・インターフェース）
 */
public interface NodeWrapper<T extends Node> {
	/**
	 * 内包するオブジェクトを返す.
	 * @return 内包するオブジェクト
	 */
	T getWrappedNode();
}
