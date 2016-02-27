package org.unclazz.dom1.utils;

import java.util.List;

import org.w3c.dom.DocumentFragment;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TreeStructuredNode> getChildNodes() {
		return UZNodeUtils.wrapTreeStructuredNodes(inner.getChildNodes());
	}

	@Override
	public TreeStructuredNode getFirstChild() {
		return UZNodeUtils.wrapTreeStructuredNode(inner.getFirstChild());
	}

	@Override
	public TreeStructuredNode getLastChild() {
		return UZNodeUtils.wrapTreeStructuredNode(inner.getLastChild());
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
	public DocumentFragment getWrappedNode() {
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
}
