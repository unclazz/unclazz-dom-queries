package org.unclazz.dom1;

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
	
	@Override
	public String toString() {
		final StringBuilder buff = new StringBuilder();
		buff.append("AttributeNode(").append(getName()).append('=').append(getValue()).append(')');
		return buff.toString();
	}
}
