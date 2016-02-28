package org.unclazz.dom1;

import java.util.Collections;

import org.unclazz.dom1.TreeStructuredNode.BranchNode;
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
		if (name.equals("*")) {
			return tag();
		}
		return new TagListQuery(this.and(Functions.treeStructuredNode2ElementNode
				.and(Functions.tagNameEquals(name))));
	}
	
	public TextListQuery text() {
		return new TextListQuery(this.and(Functions.treeStructuredNode2TextNode));
	}
	
	@Override
	protected Iterable<Node> source(Nodal n) {
		if (n instanceof BranchNode) {
			return NodeIterable.wrap(n.getWrappedNode().getChildNodes());
		}
		return Collections.emptyList();
	}

	@Override
	protected Function<Node, TreeStructuredNode> function() {
		return Functions.node2TreeStructuredNode;
	}
}
