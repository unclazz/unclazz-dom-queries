package org.unclazz.dom1;

import java.util.List;

import org.unclazz.dom1.TreeStructuredNode.BranchNode;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * {@link Document}のためのラッパー.
 */
public interface DocumentNode extends NodeKind, BranchNode {
	/**
	 * {@link Document#getDocumentElement()}を呼び出す.
	 * @return {@link Element}のラッパー
	 */
	ElementNode getDocumentElement();
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
	TextNode createText(String data);
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
	/**
	 * このノードの子孫要素のなかから名前の合致するすべての要素を取得する.
	 * この要素の並びは要素を検索した時点におけるDOMの木構造において各要素が登場する順序に依存する。
	 * @param tagName タグ名。特殊値{@code "*"}はすべてのタグに合致する。
	 * @return 要素リスト
	 */
	List<ElementNode> getElementsByTagName(String tagName);
}
