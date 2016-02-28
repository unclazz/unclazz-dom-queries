package org.unclazz.dom1;

import java.util.Collections;

import org.unclazz.dom1.TreeStructuredNode.BranchNode;
import org.w3c.dom.Node;

/**
 * 子要素の問合せを行うためのクエリ.
 * <p>{@link DescendantsQuery}と異なりこのクエリは起点となる要素の直接の子要素のみを問合せの対象とする。</p>
 */
class ChildrenQuery extends FunctionalListQuery<Node, TreeStructuredNode> {
	ChildrenQuery() {}
	
	private final TagListQuery tag = new TagListQuery(this);

	/**
	 * 子要素の{@link ElementNode}を返すクエリを返す.
	 * @return クエリ
	 */
	public TagListQuery tag() {
		return tag;
	}

	/**
	 * 子要素の{@link ElementNode}を返すクエリを返す.
	 * <p>引数に特殊値{@code "*"}を指定した場合、{@link #tag()}と同義となる。</p>
	 * @param name 要素名
	 * @return クエリ
	 */
	public TagListQuery tag(String name) {
		if (name.equals("*")) {
			return tag();
		}
		return new TagListQuery(this, name);
	}
	
	/**
	 * 子要素の{@link TextNode}を返すクエリを返す.
	 * @return クエリ
	 */
	public TextListQuery text() {
		return new TextListQuery(this);
	}
	
	@Override
	protected Iterable<Node> source(Nodal n) {
		if (n instanceof BranchNode) {
			return NodeIterable.wrap(n.getWrappedNode().getChildNodes());
		}
		return Collections.emptyList();
	}

	@Override
	protected Function<Node, TreeStructuredNode> function() {
		return Functions.node2TreeStructuredNode;
	}
}
