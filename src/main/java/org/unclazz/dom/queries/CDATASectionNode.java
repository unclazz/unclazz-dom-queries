package org.unclazz.dom.queries;

import org.w3c.dom.CDATASection;

/**
 * {@link CDATASection}を内包するオブジェクト.
 */
public interface CDATASectionNode extends Leaf, NodeWrapper<CDATASection> {
	/**
	 * {@link CDATASection#getNodeValue()}を呼び出す.
	 * @return CDATAセクションの内容
	 */
	String getValue();
}
