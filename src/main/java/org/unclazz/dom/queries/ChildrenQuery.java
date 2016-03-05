package org.unclazz.dom.queries;

import java.util.Collections;

import org.w3c.dom.Node;

class ChildrenQuery extends RelativeNodesQuery {
	@Override
	protected Iterable<Node> source(NodeKind n) {
		if (n instanceof Leaf) {
			return Collections.emptyList();
		}
		return NodeIterable.wrap(n.getWrappedNode().getChildNodes());
	}
}
