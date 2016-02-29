package org.unclazz.dom1;

import java.util.List;

import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;

class DefaultDocumentFragmentNode implements DocumentFragmentNode {
	private final DocumentFragment inner;
	DefaultDocumentFragmentNode(final DocumentFragment inner) {
		this.inner = inner;
	}

	@Override
	public NodeType getNodeType() {
		return NodeType.DOCUMENT_FRAGMENT;
	}

	@Override
	public <R> R query(Query<R> q) {
		return q.queryFrom(this);
	}

	@Override
	public List<TreeStructuredNode> getChildNodes() {
		return NodeKindUtils.wrapTreeStructuredNodes(inner.getChildNodes());
	}

	@Override
	public TreeStructuredNode getFirstChild() {
		return NodeKindUtils.wrapTreeStructuredNode(inner.getFirstChild());
	}

	@Override
	public TreeStructuredNode getLastChild() {
		return NodeKindUtils.wrapTreeStructuredNode(inner.getLastChild());
	}

	@Override
	public TreeStructuredNode getPreviousSibling() {
		return null;
	}

	@Override
	public TreeStructuredNode getNextSibling() {
		return null;
	}

	@Override
	public boolean hasParentNode() {
		return false;
	}

	@Override
	public boolean hasChildNodes() {
		return inner.hasAttributes();
	}

	@Override
	public boolean hasPreviousSibling() {
		return false;
	}

	@Override
	public boolean hasNextSibling() {
		return false;
	}

	@Override
	public Node getWrappedNode() {
		return inner;
	}

	@Override
	public BranchNode getParentNode() {
		return null;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public boolean isBranch() {
		return true;
	}
	
	@Override
	public String toString() {
		return "DocumentFragment()";
	}

	@Override
	public DocumentNode getOwnerDocument() {
		return new DefaultDocumentNode(inner.getOwnerDocument());
	}
}
