package org.unclazz.dom.queries;

import org.w3c.dom.Document;

abstract class AbstractTreeStructure implements TreeStructure {

	@Override
	public final NodeType getNodeType() {
		return NodeType.valueOf(getWrappedNode().getNodeType());
	}

	@Override
	public final <R> R query(Query<R> q) {
		return q.queryFrom(this);
	}

	@Override
	public final DocumentNode getOwnerDocument() {
		final Document d = getWrappedNode().getOwnerDocument();
		if (d == null) {
			return null;
		}
		return new DefaultDocumentNode(d);
	}

	@Override
	public final DocumentNode getOwnerDocument(boolean self) {
		if (self && this instanceof DocumentNode) {
			return (DocumentNode) this;
		}
		return getOwnerDocument();
	}

	@Override
	public final boolean isRoot() {
		return this instanceof Root;
	}

	@Override
	public final boolean isBranch() {
		return this instanceof Branch;
	}

	@Override
	public final boolean isLeaf() {
		return this instanceof Leaf;
	}
	
	@Override 
	public final int hashCode() {
		return getWrappedNode().hashCode();
	}
	
	@Override 
	public final boolean equals(final Object other) {
		return getWrappedNode().equals(other);
	}
	
	@Override
	public final String toString() {
		return NodeKindUtils.toCharSequence(this).toString();
	}
}
