package org.unclazz.dom1;

import org.w3c.dom.CDATASection;

/**
 * {@link CDATASection}を内包するオブジェクト.
 */
public interface CDATASectionNode extends TreeStructure.Leaf, NodeWrapper<CDATASection> {
	/**
	 * {@link CDATASection#getNodeValue()}を呼び出す.
	 * @return CDATAセクションの内容
	 */
	String getValue();
}
