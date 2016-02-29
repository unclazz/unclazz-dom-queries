package org.unclazz.dom1;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

class DefaultElementNode implements ElementNode {
	private final Element inner;
	DefaultElementNode(Element inner) {
		this.inner = inner;
	}

	@Override
	public Node getWrappedNode() {
		return inner;
	}

	@Override
	public NodeType getNodeType() {
		return NodeType.ELEMENT;
	}

	@Override
	public <R> R query(Query<R> q) {
		return q.queryFrom(this);
	}

	@Override
	public List<ElementNode> getElementsByTagName(String tagName) {
		return NodeKindUtils.wrapElements(inner.getElementsByTagName(tagName));
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
	public boolean isLeaf() {
		return false;
	}

	@Override
	public boolean isBranch() {
		return true;
	}

	@Override
	public TreeStructuredNode getPreviousSibling() {
		return NodeKindUtils.wrapTreeStructuredNode(inner.getPreviousSibling());
	}

	@Override
	public TreeStructuredNode getNextSibling() {
		return NodeKindUtils.wrapTreeStructuredNode(inner.getNextSibling());
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
		return NodeKindUtils.wrapBranchNode(inner.getParentNode());
	}

	@Override
	public List<TreeStructuredNode> getChildNodes() {
		return NodeKindUtils.wrapTreeStructuredNodes(inner.getChildNodes());
	}

	@Override
	public TreeStructuredNode getFirstChild() {
		return NodeKindUtils.wrapTreeStructuredNode(inner.getFirstChild());
	}

	@Override
	public TreeStructuredNode getLastChild() {
		return NodeKindUtils.wrapTreeStructuredNode(inner.getLastChild());
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

	@Override
	public DocumentNode getOwnerDocument() {
		return new DefaultDocumentNode(inner.getOwnerDocument());
	}
}
