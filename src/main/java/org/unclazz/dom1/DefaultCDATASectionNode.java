package org.unclazz.dom1;

import org.unclazz.dom1.TreeStructuredNode.LeafNode;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Node;

class DefaultCDATASectionNode implements CDATASectionNode, LeafNode {
	private final CDATASection inner;
	DefaultCDATASectionNode(final CDATASection inner) {
		this.inner = inner;
	}
	
	@Override
	public NodeType getNodeType() {
		return NodeType.CDATA_SECTION;
	}

	@Override
	public <R> R query(Query<R> q) {
		return q.queryFrom(this);
	}

	@Override
	public String getValue() {
		return inner.getTextContent();
	}

	@Override
	public Node getWrappedNode() {
		return inner;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public boolean isBranch() {
		return false;
	}

	@Override
	public BranchNode getParentNode() {
		return UZNodeUtils.wrapBranchNode(inner.getParentNode());
	}

	@Override
	public TreeStructuredNode getPreviousSibling() {
		return UZNodeUtils.wrapTreeStructuredNode(inner.getPreviousSibling());
	}

	@Override
	public TreeStructuredNode getNextSibling() {
		return UZNodeUtils.wrapTreeStructuredNode(inner.getNextSibling());
	}

	@Override
	public boolean hasParentNode() {
		return inner.getParentNode() != null;
	}

	@Override
	public boolean hasPreviousSibling() {
		return inner.getPreviousSibling() != null;
	}

	@Override
	public boolean hasNextSibling() {
		return inner.getNextSibling() != null;
	}
	
	@Override
	public String toString() {
		return "CDATASectionNode(<![CDATA[ ... ]])";
	}
}
