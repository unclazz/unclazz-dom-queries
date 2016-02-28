package org.unclazz.dom1;

import java.util.Collections;

import org.w3c.dom.Node;

class ChildrenQuery extends FunctionalListQuery<Node, TreeStructuredNode> {
	ChildrenQuery() {}
	
	private TagListQuery tag = null;

	public TagListQuery tag() {
		if (tag == null) {
			tag = new TagListQuery(this.and(Functions.treeStructuredNode2ElementNode));
		}
		return tag;
	}

	public TagListQuery tag(String name) {
		return new TagListQuery(this.and(Functions.treeStructuredNode2ElementNode
				.and(Functions.tagNameEquals(name))));
	}
	
	public TextListQuery text() {
		return new TextListQuery(this.and(Functions.treeStructuredNode2TextNode));
	}
	
	@Override
	protected Iterable<Node> source(UZNode n) {
		if (n instanceof BranchNode) {
			final BranchNode b = (BranchNode) n;
			return NodeIterable.wrap(b.getWrappedNode().getChildNodes());
		}
		return Collections.emptyList();
	}

	@Override
	protected Function<Node, TreeStructuredNode> function() {
		return Functions.node2TreeStructuredNode;
	}
}
