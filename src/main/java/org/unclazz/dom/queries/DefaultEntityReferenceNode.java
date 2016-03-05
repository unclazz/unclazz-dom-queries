package org.unclazz.dom.queries;

import org.w3c.dom.EntityReference;

final class DefaultEntityReferenceNode extends AbstractTreeStructureBranch implements EntityReferenceNode {
	private final EntityReference inner;
	DefaultEntityReferenceNode(final EntityReference inner) {
		super(inner);
		this.inner = inner;
	}
	
	@Override
	public String toString() {
		return "EntityReferenceNode()";
	}

	@Override
	public EntityReference getWrappedNode() {
		return inner;
	}
}
