package org.unclazz.dom1;

import org.w3c.dom.Text;

/**
 * {@link Text}のためのラッパー.
 */
public interface TextNode extends TreeStructure.Leaf, NodeWrapper<Text> {
	/**
	 * {@link Text#splitText(int)}を呼び出す.
	 * @param offset オフセット
	 * @return {@link Text}のラッパー・オブジェクト
	 */
	TextNode splitText(int offset);
	/**
	 * {@link Text#getNodeValue()}を呼び出す.
	 * @return 文字列
	 */
	String getValue();
}
