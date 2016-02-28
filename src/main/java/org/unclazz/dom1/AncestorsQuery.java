package org.unclazz.dom1;

import java.util.Collections;
import java.util.LinkedList;

import org.unclazz.dom1.TreeStructuredNode.BranchNode;
import org.w3c.dom.Node;

public class AncestorsQuery extends FunctionalListQuery<Node, BranchNode> {
	AncestorsQuery() {}
	
	private TagListQuery tag = null;

	public TagListQuery tag() {
		if (tag == null) {
			tag = new TagListQuery(this);
		}
		return tag;
	}

	public TagListQuery tag(String name) {
		return new TagListQuery(this, name);
	}
	
	@Override
	protected Iterable<Node> source(NodeKind n) {
		if (n instanceof BranchNode) {
			final LinkedList<Node> result = new LinkedList<Node>();
			Node base = n.getWrappedNode();
			Node parent = null;
			while ((parent = base.getParentNode()) != null) {
				result.addLast(parent);
				base = parent;
			}
			return result;
		}
		return Collections.emptyList();
	}

	@Override
	protected Function<Node, BranchNode> function() {
		return Functions.node2TreeStructuredNode.and(treeStructuredNode2BranchNode);
	}
	
	private static final UniFunction<TreeStructuredNode, BranchNode> treeStructuredNode2BranchNode =
			new UniFunction<TreeStructuredNode, BranchNode>() {
		@Override
		public BranchNode apply(TreeStructuredNode target) {
			return target instanceof BranchNode ? (BranchNode) target : null;
		}
	};
}
