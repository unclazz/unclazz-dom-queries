package org.unclazz.dom1;

import org.w3c.dom.Notation;

final class DefaultNotationNode extends AbstractTreeStructureLeaf implements NotationNode {
	private final Notation inner;
	DefaultNotationNode(final Notation inner) {
		super(inner);
		this.inner = inner;
	}
	
	@Override
	public String toString() {
		return "NotationNode()";
	}

	@Override
	public Notation getWrappedNode() {
		return inner;
	}
}
