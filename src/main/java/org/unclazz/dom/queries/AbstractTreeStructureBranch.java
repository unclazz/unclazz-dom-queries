package org.unclazz.dom.queries;

import java.util.List;

import org.w3c.dom.Node;

abstract class AbstractTreeStructureBranch extends AbstractTreeStructure implements Branch {
	private final Node inner;
	AbstractTreeStructureBranch(final Node inner) {
		this.inner = inner;
	}
	
	@Override
	public final List<TreeStructure> getChildNodes() {
		return NodeKindUtils.wrapTreeStructureList(inner.getChildNodes());
	}

	@Override
	public final TreeStructure getFirstChild() {
		return NodeKindUtils.wrapTreeStructure(inner.getFirstChild());
	}

	@Override
	public final TreeStructure getLastChild() {
		return NodeKindUtils.wrapTreeStructure(inner.getLastChild());
	}

	@Override
	public final boolean hasChildNodes() {
		return inner.hasChildNodes();
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
