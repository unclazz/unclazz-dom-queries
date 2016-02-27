package org.unclazz.dom1.utils;

import org.w3c.dom.Attr;

class DefaultAttributeNode implements AttributeNode {
	private final Attr inner;
	DefaultAttributeNode(final Attr inner) {
		this.inner = inner;
	}

	@Override
	public Attr getWrappedNode() {
		return inner;
	}

	@Override
	public NodeType getNodeType() {
		return NodeType.ATTRIBUTE;
	}

	@Override
	public <R> R query(Query<R> q) {
		// TODO Auto-generated method stub
		return null;
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
