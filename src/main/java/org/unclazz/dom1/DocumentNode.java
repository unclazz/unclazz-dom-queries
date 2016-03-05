package org.unclazz.dom1;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * {@link Document}を内包するオブジェクト.
 */
public interface DocumentNode extends Root, NodeWrapper<Document> {
	/**
	 * {@link Document#createElement(String)}を呼び出す.
	 * @param tagName タグ名
	 * @return {@link Element}のラッパー
	 */
	ElementNode createElement(String tagName);
	/**
	 * {@link Document#createDocumentFragment()}を呼び出す.
	 * @return {@link DocumentFragment}のラッパー
	 */
	DocumentFragmentNode createDocumentFragment();
	/**
	 * {@link Document#createTextNode(String)}を呼び出す.
	 * @param data データ
	 * @return {@link Text}のラッパー
	 */
	TextNode createTextNode(String data);
	/**
	 * {@link Document#createComment(String)}を呼び出す.
	 * @param data データ
	 * @return {@link Comment}のラッパー
	 */
	CommentNode createComment(String data);
	/**
	 * {@link Document#createCDATASection(String)}を呼び出す.
	 * @param data データ
	 * @return {@link CDATASection}のラッパー
	 */
	CDATASectionNode createCDATASection(String data);
	/**
	 * {@link Document#createAttribute(String)}を呼び出す.
	 * @param name 属性名
	 * @return {@link Attr}のラッパー
	 */
	AttributeNode createAttribute(String name);
}
