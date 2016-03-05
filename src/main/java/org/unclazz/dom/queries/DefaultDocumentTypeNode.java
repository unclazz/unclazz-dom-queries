package org.unclazz.dom.queries;

import org.w3c.dom.DocumentType;

final class DefaultDocumentTypeNode extends AbstractTreeStructureLeaf implements DocumentTypeNode {
	private final DocumentType inner;
	DefaultDocumentTypeNode(final DocumentType inner) {
		super(inner);
		this.inner = inner;
	}
	
	@Override
	public String toString() {
		return "DocumentTypeNode()";
	}

	@Override
	public DocumentType getWrappedNode() {
		return inner;
	}
}
