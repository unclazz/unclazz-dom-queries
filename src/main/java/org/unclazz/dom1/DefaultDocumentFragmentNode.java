package org.unclazz.dom1;

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
