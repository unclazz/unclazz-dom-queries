package org.unclazz.dom.queries;

import org.w3c.dom.Node;

/**
 * 関連ノードの問合せを行うためのクエリ.
 * <p>具象クラスの実装により対象とするノードの範囲は子ノード、子孫ノード、兄弟ノード、祖先ノードというように異なる。</p>
 * <p>このオブジェクト自体がクエリとして機能すると同時に、
 * このオブジェクトのメンバーが返すオブジェクトもまたより特殊化された問合せを行うクエリとして機能する。
 * インスタンスは{@link Queries#children}、{@link Queries#descendants}、
 * {@link Queries#prevs}、{@link Queries#nexts}、{@link Queries#ancestors}を通じて得られる。</p>
 */
public abstract class RelativeNodesQuery extends FunctionalListQuery<Node, TreeStructure> {
	private final ElementListQuery tag = new ElementListQuery(this);

	/**
	 * 関連ノードの{@link ElementNode}を問合せるクエリを返す.
	 * @return クエリ
	 */
	public ElementListQuery element() {
		return tag;
	}

	/**
	 * 関連ノードの{@link ElementNode}を問合せるクエリを返す.
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
	 * 関連ノードの{@link TextNode}を問合せるクエリを返す.
	 * @return クエリ
	 */
	public TextListQuery text() {
		return new TextListQuery(this);
	}
	
	/**
	 * 関連ノードの{@link CommentNode}を問合せるクエリを返す.
	 * @return クエリ
	 */
	public FunctionalListQuery<Node, CommentNode> comment() {
		return this.and(new Function<TreeStructure, CommentNode>() {
			@Override
			public CommentNode apply(TreeStructure target) {
				return target instanceof CommentNode ? (CommentNode) target : null;
			}
		});
	}
	
	/**
	 * 関連ノードの{@link CDATASectionNode}を問合せるクエリを返す.
	 * @return クエリ
	 */
	public FunctionalListQuery<Node, CDATASectionNode> cdata() {
		return this.and(new Function<TreeStructure, CDATASectionNode>() {
			@Override
			public CDATASectionNode apply(TreeStructure target) {
				return target instanceof CDATASectionNode ? (CDATASectionNode) target : null;
			}
		});
	}

	@Override
	protected Function<Node, TreeStructure> function() {
		return node2TreeStructuredNode;
	}
	
	private static final SyntheticFunction<Node, TreeStructure> node2TreeStructuredNode =
			new SyntheticFunction<Node, TreeStructure>() {
		@Override
		public TreeStructure apply(Node node) {
			return NodeKindUtils.wrapTreeStructure(node);
		}
	};
}
