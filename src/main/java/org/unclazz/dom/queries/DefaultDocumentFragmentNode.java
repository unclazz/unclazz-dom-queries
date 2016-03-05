package org.unclazz.dom.queries;

import org.w3c.dom.DocumentFragment;

final class DefaultDocumentFragmentNode extends AbstractTreeStructureBranch implements DocumentFragmentNode {
	private final DocumentFragment inner;
	DefaultDocumentFragmentNode(final DocumentFragment inner) {
		super(inner);
		this.inner = inner;
	}
	@Override
	public DocumentFragment getWrappedNode() {
		return inner;
	}
	
	@Override
	public String toString() {
		return "DocumentFragment()";
	}
}
