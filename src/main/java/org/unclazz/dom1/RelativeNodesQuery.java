package org.unclazz.dom1;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * 関連ノードの問合せを行うためのクエリ.
 * <p>具象クラスの実装により対象とするノードの範囲は子ノード、子孫ノード、兄弟ノード、祖先ノードというように異なる。</p>
 * <p>このオブジェクト自体がクエリとして機能すると同時に、
 * このオブジェクトのメンバーが返すオブジェクトもまたより特殊化された問合せを行うクエリとして機能する。
 * インスタンスは{@link Queries#children}、{@link Queries#descendants}、
 * {@link Queries#prevs}、{@link Queries#nexts}、{@link Queries#ancestors}を通じて得られる。</p>
 */
public abstract class RelativeNodesQuery extends FunctionalListQuery<Node, TreeStructuredNode> {
	private final ElementListQuery tag = new ElementListQuery(this);

	/**
	 * 関連ノードの{@link ElementNode}を返すクエリを返す.
	 * @return クエリ
	 */
	public ElementListQuery element() {
		return tag;
	}

	/**
	 * 関連ノードの{@link ElementNode}を返すクエリを返す.
	 * <p>引数に特殊値{@code "*"}を指定した場合、{@link #element()}と同義となる。</p>
	 * @param name 要素名
	 * @return クエリ
	 */
	public ElementListQuery element(String name) {
		if (name.equals("*")) {
			return element();
		}
		return new ElementListQuery(this, name);
	}
	
	/**
	 * 関連ノードの{@link TextNode}を返すクエリを返す.
	 * @return クエリ
	 */
	public TextListQuery text() {
		return new TextListQuery(this);
	}

	@Override
	protected Function<Node, TreeStructuredNode> function() {
		return node2TreeStructuredNode;
	}
	
	private static final SyntheticFunction<Node, TreeStructuredNode> node2TreeStructuredNode =
			new SyntheticFunction<Node, TreeStructuredNode>() {
		@Override
		public TreeStructuredNode apply(Node node) {
			if (node == null) {
				return null;
			}
			
			switch (node.getNodeType()) {
			case Node.DOCUMENT_NODE:
				return new DefaultDocumentNode((Document) node);
			case Node.DOCUMENT_FRAGMENT_NODE:
				return new DefaultDocumentFragmentNode((DocumentFragment) node);
			case Node.ELEMENT_NODE:
				return new DefaultElementNode((Element) node);
			case Node.TEXT_NODE:
				return new DefaultTextNode((Text) node);
			case Node.COMMENT_NODE:
				return new DefaultCommentNode((Comment) node);
			case Node.CDATA_SECTION_NODE:
				return new DefaultCDATASectionNode((CDATASection) node);
			default:
				return null;
			}
		}
	};
}
