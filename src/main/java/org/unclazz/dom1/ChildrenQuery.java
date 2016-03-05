package org.unclazz.dom1;

import java.util.Collections;

import org.w3c.dom.Node;

class ChildrenQuery extends RelativeNodesQuery {
	@Override
	protected Iterable<Node> source(NodeKind n) {
		if (n instanceof TreeStructure.Leaf) {
			return Collections.emptyList();
		}
		return NodeIterable.wrap(n.getWrappedNode().getChildNodes());
	}
}
