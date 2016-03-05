package org.unclazz.dom1;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

class DefaultElementNode extends AbstractTreeStructureBranch implements ElementNode {
	private final Element inner;
	DefaultElementNode(Element inner) {
		super(inner);
		this.inner = inner;
	}

	@Override
	public Element getWrappedNode() {
		return inner;
	}

	@Override
	public List<ElementNode> getElementsByTagName(String tagName) {
		return NodeKindUtils.wrapElements(inner.getElementsByTagName(tagName));
	}

	@Override
	public String getTagName() {
		return inner.getTagName();
	}

	@Override
	public String getAttribute(String name) {
		return inner.getAttribute(name);
	}

	@Override
	public void setAttribute(String name, String value) {
		inner.setAttribute(name, value);
	}

	@Override
	public AttributeNode getAttributeNode(String name) {
		final Attr attr = inner.getAttributeNode(name);
		if (attr == null) {
			return null;
		}
		return new DefaultAttributeNode(attr);
	}

	@Override
	public void setAttributeNode(AttributeNode newAttr) {
		inner.setAttributeNode((Attr)newAttr.getWrappedNode());
	}

	@Override
	public AttributeNode removeAttributeNode(AttributeNode oldAttr) {
		final Attr a = inner.removeAttributeNode((Attr)oldAttr.getWrappedNode());
		if (a == null) {
			return null;
		}
		return new DefaultAttributeNode(a);
	}

	@Override
	public void normalize() {
		inner.normalize();
	}

	@Override
	public Map<String, AttributeNode> getAttributes() {
		return NodeKindUtils.wrapAttributesMap(inner.getAttributes());
	}

	@Override
	public boolean hasAttribute(String name) {
		return inner.hasAttribute(name);
	}
	
	@Override
	public String toString() {
		final StringBuilder buff = new StringBuilder();
		buff.append("ElementNode(<")
		.append(getTagName())
		.append(">)");
		return buff.toString();
	}
}
