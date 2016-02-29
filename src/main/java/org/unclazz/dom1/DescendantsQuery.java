package org.unclazz.dom1;

import java.util.Collections;
import java.util.LinkedList;

import org.unclazz.dom1.TreeStructuredNode.BranchNode;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class DescendantsQuery extends RelativeNodesQuery {
	@Override
	public Iterable<Node> source(NodeKind n) {
		if (n instanceof BranchNode) {
			final LinkedList<Node> descendants = new LinkedList<Node>();
			collectDeschendants(n.getWrappedNode(), descendants);
			return descendants;
		}
		return Collections.emptyList();
	}
	
	private void collectDeschendants(final Node parent, final LinkedList<Node> result) {
		final NodeList children = parent.getChildNodes();
		final int len = children.getLength();
		for (int i = 0; i < len; i ++) {
			final Node n = children.item(i);
			result.addLast(n);
			collectDeschendants(n, result);
		}
	}
}
