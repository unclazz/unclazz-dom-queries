package org.unclazz.dom.queries;

import org.w3c.dom.Attr;

final class DefaultAttributeNode extends AbstractTreeStructureBranch implements AttributeNode {
	private final Attr inner;
	DefaultAttributeNode(final Attr inner) {
		super(inner);
		this.inner = inner;
	}

	@Override
	public Attr getWrappedNode() {
		return inner;
	}

	@Override
	public String getValue() {
		return inner.getValue();
	}

	@Override
	public String getName() {
		return inner.getName();
	}

	@Override
	public void setValue(String value) {
		inner.setValue(value);
	}

	@Override
	public boolean isSpecified() {
		return inner.getSpecified();
	}
}
