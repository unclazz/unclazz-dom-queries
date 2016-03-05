package org.unclazz.dom1;

import org.w3c.dom.CDATASection;

/**
 * {@link CDATASection}のためのラッパー.
 */
public interface CDATASectionNode extends TreeStructure.Leaf, NodeWrapper<CDATASection> {
	/**
	 * {@link CDATASection#getNodeValue()}を呼び出す.
	 * @return CDATAセクションの内容
	 */
	String getValue();
}
