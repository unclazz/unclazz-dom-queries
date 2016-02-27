package org.unclazz.dom1.utils;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

class DefaultElementNode implements ElementNode {
	private final Element inner;
	DefaultElementNode(Element inner) {
		this.inner = inner;
	}

	@Override
	public Element getWrappedNode() {
		return inner;
	}

	@Override
	public NodeType getNodeType() {
		return NodeType.ELEMENT;
	}

	@Override
	public <R> R query(Query<R> q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElementNode> getElementsByTagName(String tagName) {
		return UZNodeUtils.wrapElements(inner.getElementsByTagName(tagName));
	}

	@Override
	public boolean hasChildNodes() {
		return inner.hasChildNodes();
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
		inner.setAttributeNode(newAttr.getWrappedNode());
	}

	@Override
	public AttributeNode removeAttributeNode(AttributeNode oldAttr) {
		final Attr a = inner.removeAttributeNode(oldAttr.getWrappedNode());
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
		return UZNodeUtils.wrapAttributesMap(inner.getAttributes());
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public boolean isBranch() {
		return true;
	}

	@Override
	public TreeStructuredNode getPreviousSibling() {
		return UZNodeUtils.wrapTreeStructuredNode(inner.getPreviousSibling());
	}

	@Override
	public TreeStructuredNode getNextSibling() {
		return UZNodeUtils.wrapTreeStructuredNode(inner.getNextSibling());
	}

	@Override
	public boolean hasParentNode() {
		return inner.getParentNode() != null;
	}

	@Override
	public boolean hasPreviousSibling() {
		return inner.getPreviousSibling() != null;
	}

	@Override
	public boolean hasNextSibling() {
		return inner.getNextSibling() != null;
	}

	@Override
	public BranchNode getParentNode() {
		return UZNodeUtils.wrapBranchNode(inner.getParentNode());
	}

	@Override
	public List<TreeStructuredNode> getChildNodes() {
		return UZNodeUtils.wrapTreeStructuredNodes(inner.getChildNodes());
	}

	@Override
	public TreeStructuredNode getFirstChild() {
		return UZNodeUtils.wrapTreeStructuredNode(inner.getFirstChild());
	}

	@Override
	public TreeStructuredNode getLastChild() {
		return UZNodeUtils.wrapTreeStructuredNode(inner.getLastChild());
	}

	@Override
	public boolean hasAttribute(String name) {
		return inner.hasAttribute(name);
	}
}
