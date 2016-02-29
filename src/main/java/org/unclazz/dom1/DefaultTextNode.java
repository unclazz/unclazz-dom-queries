package org.unclazz.dom1;

import org.unclazz.dom1.TreeStructuredNode.LeafNode;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

class DefaultTextNode implements TextNode, LeafNode {
	private final Text inner;
	DefaultTextNode(final Text inner) {
		this.inner = inner;
	}

	@Override
	public NodeType getNodeType() {
		return NodeType.TEXT;
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
	public TextNode splitText(int offset) {
		return new DefaultTextNode(inner.splitText(offset));
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
		return NodeKindUtils.wrapBranchNode(inner.getParentNode());
	}

	@Override
	public TreeStructuredNode getPreviousSibling() {
		return NodeKindUtils.wrapTreeStructuredNode(inner.getPreviousSibling());
	}

	@Override
	public TreeStructuredNode getNextSibling() {
		return NodeKindUtils.wrapTreeStructuredNode(inner.getNextSibling());
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
	public Node getWrappedNode() {
		return inner;
	}
	
	@Override
	public String toString() {
		return "TextNode()";
	}

	@Override
	public DocumentNode getOwnerDocument() {
		return new DefaultDocumentNode(inner.getOwnerDocument());
	}
}
