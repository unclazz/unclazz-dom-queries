package org.unclazz.dom1;

import java.util.Collections;

import org.unclazz.dom1.TreeStructuredNode.BranchNode;
import org.w3c.dom.Node;

class ChildrenQuery extends RelativeNodesQuery {
	@Override
	protected Iterable<Node> source(NodeKind n) {
		if (n instanceof BranchNode) {
			return NodeIterable.wrap(n.getWrappedNode().getChildNodes());
		}
		return Collections.emptyList();
	}
}
