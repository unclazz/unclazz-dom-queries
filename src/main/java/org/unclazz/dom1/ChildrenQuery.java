package org.unclazz.dom1;

import java.util.Collections;

import org.w3c.dom.Node;

class ChildrenQuery extends FunctionalListQuery<Node, TreeStructuredNode> {
	ChildrenQuery() {}
	
	private FunctionalListQuery<Node, ElementNode> tag = null;

	public FunctionalListQuery<Node, ElementNode> tag() {
		if (tag == null) {
			tag = this.and(Functions.treeStructuredNode2ElementNode);
		}
		return tag;
	}

	public FunctionalListQuery<Node, ElementNode> tag(String name) {
		return tag().and(Functions.tagNameEquals(name));
	}

	@Override
	public Iterable<Node> source(UZNode n) {
		if (n instanceof BranchNode) {
			final BranchNode b = (BranchNode) n;
			return NodeIterable.wrap(b.getWrappedNode().getChildNodes());
		}
		return Collections.emptyList();
	}

	@Override
	public Function<Node, TreeStructuredNode> function() {
		return Functions.node2TreeStructuredNode;
	}
}
