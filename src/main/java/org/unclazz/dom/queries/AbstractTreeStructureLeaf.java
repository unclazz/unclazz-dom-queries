package org.unclazz.dom.queries;

import org.w3c.dom.Node;

abstract class AbstractTreeStructureLeaf extends AbstractTreeStructure implements Leaf {
	private final Node inner;
	AbstractTreeStructureLeaf(final Node inner) {
		this.inner = inner;
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
}
