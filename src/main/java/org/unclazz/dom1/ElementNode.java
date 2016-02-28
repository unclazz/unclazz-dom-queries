package org.unclazz.dom1;

import java.util.List;
import java.util.Map;

import org.unclazz.dom1.TreeStructuredNode.BranchNode;

/**
 * DOMのElementをあらわすインターフェース.
 */
public interface ElementNode extends NodeKind, BranchNode {
	String getTagName();
	boolean hasAttribute(String name);
	String getAttribute(String name);
	void setAttribute(String name, String value);
	AttributeNode getAttributeNode(String name);
	void setAttributeNode(AttributeNode newAttr);
	AttributeNode removeAttributeNode(AttributeNode oldAttr);
	void normalize();
	Map<String, AttributeNode> getAttributes();
	/**
	 * このノードの子孫要素のなかから名前の合致するすべての要素を取得する.
	 * この要素の並びは要素を検索した時点におけるDOMの木構造において各要素が登場する順序に依存する。
	 * @param tagName タグ名。特殊値{@code "*"}はすべてのタグに合致する。
	 * @return 要素リスト
	 */
	List<ElementNode> getElementsByTagName(String tagName);
}
