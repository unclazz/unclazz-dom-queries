package org.unclazz.dom.queries;

import org.w3c.dom.Attr;

/**
 * {@link Attr}を内包するオブジェクト.
 */
public interface AttributeNode extends Branch, NodeWrapper<Attr> {
	/**
	 * {@link Attr#getName()}を呼び出す.
	 * @return 属性名
	 */
	String getName();
	/**
	 * {@link Attr#setValue(String)}を呼び出す.
	 * @param value 属性値
	 */
	void setValue(String value);
	/**
	 * {@link Attr#getSpecified()}を呼び出す.
	 * @return 明示的に指定されてる場合{@code true}
	 */
	boolean isSpecified();
	public String getValue();
}
