package org.unclazz.dom1;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

/**
 * {@link Element}を内包するオブジェクト.
 */
public interface ElementNode extends TreeStructure.Branch, NodeWrapper<Element> {
	/**
	 * {@link Element#getTagName()}を呼び出す.
	 * @return タグ名
	 */
	String getTagName();
	/**
	 * {@link Element#hasAttribute(String)}を呼び出す.
	 * @param name 属性名
	 * @return {@code true}の場合 属性は存在する
	 */
	boolean hasAttribute(String name);
	/**
	 * {@link Element#getAttribute(String)}を呼び出す.
	 * @param name 属性名
	 * @return 属性値
	 */
	String getAttribute(String name);
	/**
	 * {@link Element#setAttribute(String, String)}を呼び出す.
	 * @param name 属性名
	 * @param value 属性値
	 */
	void setAttribute(String name, String value);
	/**
	 * {@link Element#getAttributeNode(String)}を呼び出す.
	 * @param name 属性名
	 * @return {@link Attr}のラッパー・オブジェクト
	 */
	AttributeNode getAttributeNode(String name);
	/**
	 * {@link Element#setAttributeNode(Attr)}を呼び出す.
	 * @param newAttr 新しい属性
	 */
	void setAttributeNode(AttributeNode newAttr);
	/**
	 * {@link Element#removeAttributeNode(Attr)}を呼び出す.
	 * @param oldAttr 削除対象の属性
	 * @return {@link Attr}のラッパー・オブジェクト
	 */
	AttributeNode removeAttributeNode(AttributeNode oldAttr);
	/**
	 * {@link Element#normalize()}を呼び出す.
	 */
	void normalize();
	/**
	 * {@link Element#getAttributes()}を呼び出す.
	 * @return {@link NamedNodeMap}を内包した{@link Map}オブジェクト
	 */
	Map<String, AttributeNode> getAttributes();
	/**
	 * このノードの子孫要素のなかから名前の合致するすべての要素を取得する.
	 * この要素の並びは要素を検索した時点におけるDOMの木構造において各要素が登場する順序に依存する。
	 * @param tagName タグ名。特殊値{@code "*"}はすべてのタグに合致する。
	 * @return 要素リスト
	 */
	List<ElementNode> getElementsByTagName(String tagName);
}
