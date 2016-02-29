package org.unclazz.dom1;

import org.w3c.dom.CDATASection;

/**
 * {@link CDATASection}のためのラッパー.
 */
public interface CDATASectionNode extends NodeKind {
	/**
	 * {@link CDATASection#getNodeValue()}を呼び出す.
	 * @return CDATAセクションの内容
	 */
	String getValue();
}
