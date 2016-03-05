package org.unclazz.dom1;

import java.util.Collections;
import java.util.LinkedList;
import org.w3c.dom.Node;

class AncestorsQuery extends RelativeNodesQuery {
	@Override
	protected Iterable<Node> source(NodeKind n) {
		if (n instanceof TreeStructure.Root) {
			return Collections.emptyList();
		}
		final LinkedList<Node> result = new LinkedList<Node>();
		Node base = n.getWrappedNode();
		Node parent = null;
		while ((parent = base.getParentNode()) != null) {
			result.addLast(parent);
			base = parent;
		}
		return result;
	}
}
