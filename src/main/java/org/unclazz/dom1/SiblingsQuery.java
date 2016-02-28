package org.unclazz.dom1;

import java.util.Collections;
import java.util.LinkedList;

import org.w3c.dom.Node;

/**
 * 兄弟要素の問合せを行うためのクエリ.
 */
public class SiblingsQuery extends FunctionalListQuery<Node, TreeStructuredNode> {
	SiblingsQuery(boolean previous) { this.previous = previous; }
	
	private final boolean previous;
	private final TagListQuery tag = new TagListQuery(this);

	/**
	 * 兄弟要素の{@link ElementNode}を返すクエリを返す.
	 * @return クエリ
	 */
	public TagListQuery tag() {
		return tag;
	}

	/**
	 * 兄弟要素の{@link ElementNode}を返すクエリを返す.
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
	 * 兄弟要素の{@link TextNode}を返すクエリを返す.
	 * @return クエリ
	 */
	public TextListQuery text() {
		return new TextListQuery(this);
	}
	
	@Override
	protected Iterable<Node> source(NodeKind n) {
		if (n instanceof TreeStructuredNode) {
			final LinkedList<Node> nodeList = new LinkedList<Node>();
			collectSiblings(n.getWrappedNode(), previous, nodeList);
			return nodeList;
		}
		return Collections.emptyList();
	}
	
	@Override
	protected Function<Node, TreeStructuredNode> function() {
		return Functions.node2TreeStructuredNode;
	}
	
	private static void collectSiblings(final Node base, 
			final boolean previous, final LinkedList<Node> result) {
		Node curr = base;
		while ((curr = (previous ? curr.getPreviousSibling() : curr.getNextSibling())) != null) {
			result.addLast(curr);
		}
	}
}
