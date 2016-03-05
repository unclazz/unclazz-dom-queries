package org.unclazz.dom1;

import org.w3c.dom.Node;

abstract class AbstractTreeStructureLeaf implements TreeStructure.Leaf {
	private final Node inner;
	AbstractTreeStructureLeaf(final Node inner) {
		this.inner = inner;
	}

	@Override
	public final boolean isRoot() {
		return false;
	}

	@Override
	public final boolean isBranch() {
		return false;
	}

	@Override
	public final boolean isLeaf() {
		return true;
	}

	@Override
	public final NodeType getNodeType() {
		return NodeType.valueOf(inner.getNodeType());
	}

	@Override
	public final <R> R query(Query<R> q) {
		return q.queryFrom(this);
	}

	@Override
	public final DocumentNode getOwnerDocument() {
		return new DefaultDocumentNode(inner.getOwnerDocument());
	}

	@Override
	public final DocumentNode getOwnerDocument(boolean self) {
		return getOwnerDocument();
	}

	@Override
	public final TreeStructure getParentNode() {
		return NodeKindUtils.wrapTreeStructure(inner.getParentNode());
	}

	@Override
	public final TreeStructure getPreviousSibling() {
		return NodeKindUtils.wrapTreeStructure(inner.getPreviousSibling());
	}

	@Override
	public final TreeStructure getNextSibling() {
		return NodeKindUtils.wrapTreeStructure(inner.getNextSibling());
	}

	@Override
	public final boolean hasParentNode() {
		return inner.getParentNode() != null;
	}

	@Override
	public final boolean hasPreviousSibling() {
		return inner.getPreviousSibling() != null;
	}

	@Override
	public final boolean hasNextSibling() {
		return inner.getNextSibling() != null;
	}
	
	@Override
	public abstract String toString();
}
