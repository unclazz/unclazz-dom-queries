package org.unclazz.dom1;

import java.util.List;

/**
 * {@link #getElementsByTagName(String)}メソッドを持つ{@link NodeKind}のサブインタフェース.
 */
public interface ElementIncludable extends NodeIncludable {
	/**
	 * このノードの子孫要素のなかから名前の合致するすべての要素を取得する.
	 * この要素の並びは要素を検索した時点におけるDOMの木構造において各要素が登場する順序に依存する。
	 * @param tagName タグ名。特殊値{@code "*"}はすべてのタグに合致する。
	 * @return 要素リスト
	 */
	List<ElementNode> getElementsByTagName(String tagName);
}
