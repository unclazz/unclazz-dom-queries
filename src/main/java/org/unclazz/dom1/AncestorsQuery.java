package org.unclazz.dom1;

import java.util.Collections;
import java.util.LinkedList;

import org.unclazz.dom1.TreeStructuredNode.BranchNode;
import org.w3c.dom.Node;

/**
 * 祖先要素の問合せを行うためのクエリ.
 */
class AncestorsQuery extends RelativeNodesQuery {
	AncestorsQuery() {}
	
	private TagListQuery tag = null;

	/**
	 * 祖先要素の{@link ElementNode}を返すクエリを返す.
	 * @return クエリ
	 */
	public TagListQuery tag() {
		if (tag == null) {
			tag = new TagListQuery(this);
		}
		return tag;
	}
	/**
	 * 祖先要素の{@link ElementNode}を返すクエリを返す.
	 * @param name タグ名
	 * @return クエリ
	 */
	public TagListQuery tag(String name) {
		return new TagListQuery(this, name);
	}
	
	@Override
	protected Iterable<Node> source(NodeKind n) {
		if (n instanceof BranchNode) {
			final LinkedList<Node> result = new LinkedList<Node>();
			Node base = n.getWrappedNode();
			Node parent = null;
			while ((parent = base.getParentNode()) != null) {
				result.addLast(parent);
				base = parent;
			}
			return result;
		}
		return Collections.emptyList();
	}
}
