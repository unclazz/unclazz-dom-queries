package org.unclazz.dom1;

import java.util.Collections;

import org.w3c.dom.Node;

class UnsupportedNode implements EntityNode, EntityReferenceNode, 
		DocumentTypeNode, NotationNode, ProcessingInstructionNode {
	private final Node inner;
	
	UnsupportedNode(final Node inner) {
		this.inner = inner;
	}
	
	@Override
	public NodeType getNodeType() {
		return NodeType.valueOf(inner.getNodeType());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <R> R query(Query<R> q) {
		if (q instanceof ListQuery) {
			return (R) Collections.emptyList();
		} 
		return null;
	}

	@Override
	public Node getWrappedNode() {
		return inner;
	}

	@Override
	public DocumentNode getOwnerDocument() {
		return new DefaultDocumentNode(inner.getOwnerDocument());
	}
}
