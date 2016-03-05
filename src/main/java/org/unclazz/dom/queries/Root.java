package org.unclazz.dom.queries;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

/**
 * 木構造のルートをあらわすインターフェース.
 * <p>DOMのAPIでは{@link Document}のみが該当する。</p>
 */
public interface Root extends TreeStructure {
	/**
	 * {@link Document#getDocumentElement()}を呼び出す.
	 * @return {@link Element}をラップしたオブジェクト
	 */
	ElementNode getDocumentElement();
	/**
	 * {@link Document#getDoctype()}を呼び出す.
	 * @return {@link DocumentType}をラップしたオブジェクト
	 */
	DocumentTypeNode getDocumentType();
	/**
	 * {@link Document#getElementsByTagName(String)}を呼び出す.
	 * @param tagName タグ名
	 * @return {@link Element}をラップしたオブジェクトのリスト
	 */
	List<ElementNode> getElementsByTagName(String tagName);
}