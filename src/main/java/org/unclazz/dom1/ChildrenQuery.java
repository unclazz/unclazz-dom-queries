package org.unclazz.dom1;

import java.util.Collections;

import org.unclazz.dom1.TreeStructuredNode.BranchNode;
import org.w3c.dom.Node;

/**
 * 子要素の問合せを行うためのクエリ.
 * <p>{@link DescendantsQuery}と異なりこのクエリは起点となる要素の直接の子要素のみを問合せの対象とする。</p>
 */
class ChildrenQuery extends RelativeNodesQuery {
	ChildrenQuery() {}
	
	@Override
	protected Iterable<Node> source(NodeKind n) {
		if (n instanceof BranchNode) {
			return NodeIterable.wrap(n.getWrappedNode().getChildNodes());
		}
		return Collections.emptyList();
	}
}
