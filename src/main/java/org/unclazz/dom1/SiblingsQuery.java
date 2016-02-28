package org.unclazz.dom1;

import java.util.Collections;
import java.util.LinkedList;

import org.w3c.dom.Node;

/**
 * 兄弟要素の問合せを行うためのクエリ.
 */
class SiblingsQuery extends RelativeNodesQuery {
	SiblingsQuery(boolean previous) { this.previous = previous; }
	
	private final boolean previous;
	
	@Override
	protected Iterable<Node> source(NodeKind n) {
		if (n instanceof TreeStructuredNode) {
			final LinkedList<Node> nodeList = new LinkedList<Node>();
			collectSiblings(n.getWrappedNode(), previous, nodeList);
			return nodeList;
		}
		return Collections.emptyList();
	}
	
	private static void collectSiblings(final Node base, 
			final boolean previous, final LinkedList<Node> result) {
		Node curr = base;
		while ((curr = (previous ? curr.getPreviousSibling() : curr.getNextSibling())) != null) {
			result.addLast(curr);
		}
	}
}
