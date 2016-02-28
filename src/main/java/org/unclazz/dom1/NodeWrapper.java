package org.unclazz.dom1;

import org.w3c.dom.Node;

/**
 * {@link Node}オブジェクトを内包するオブジェクトのためのインターフェース.
 * @param <T> 内包する要素の型
 */
public interface NodeWrapper<T extends Node> {
	/**
	 * 内包する要素を返す.
	 * @return {@link Node}オブジェクト
	 */
	T getWrappedNode();
}
