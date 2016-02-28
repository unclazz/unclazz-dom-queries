package org.unclazz.dom1;

import org.w3c.dom.Attr;
import org.w3c.dom.Node;

class DefaultAttributeNode implements AttributeNode {
	private final Attr inner;
	DefaultAttributeNode(final Attr inner) {
		this.inner = inner;
	}

	@Override
	public Node getWrappedNode() {
		return inner;
	}

	@Override
	public NodeType getNodeType() {
		return NodeType.ATTRIBUTE;
	}

	@Override
	public <R> R query(Query<R> q) {
		return q.queryFrom(this);
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
