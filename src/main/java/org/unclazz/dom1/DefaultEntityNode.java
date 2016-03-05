package org.unclazz.dom1;

import org.w3c.dom.Entity;

final class DefaultEntityNode extends AbstractTreeStructureBranch implements EntityNode {
	private final Entity inner;
	DefaultEntityNode(final Entity inner) {
		super(inner);
		this.inner = inner;
	}
	
	@Override
	public String toString() {
		return "EntityNode()";
	}

	@Override
	public Entity getWrappedNode() {
		return inner;
	}
}
