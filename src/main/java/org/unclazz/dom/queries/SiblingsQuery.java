package org.unclazz.dom.queries;

import java.util.Collections;
import java.util.LinkedList;

import org.w3c.dom.Node;

class SiblingsQuery extends RelativeNodesQuery {
	SiblingsQuery(boolean previous) { this.previous = previous; }
	
	private final boolean previous;
	
	@Override
	protected Iterable<Node> source(NodeKind n) {
		if (n instanceof Root) {
			return Collections.emptyList();
		}
		final LinkedList<Node> nodeList = new LinkedList<Node>();
		collectSiblings(n.getWrappedNode(), previous, nodeList);
		return nodeList;
	}
	
	private static void collectSiblings(final Node base, 
			final boolean previous, final LinkedList<Node> result) {
		Node curr = base;
		while ((curr = (previous ? curr.getPreviousSibling() : curr.getNextSibling())) != null) {
			result.addLast(curr);
		}
	}
}
