package org.unclazz.dom1;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

class DefaultDocumentNode implements DocumentNode {
	private final Document inner;
	
	DefaultDocumentNode(final Document inner) {
		this.inner = inner;
	}

	@Override
	public Node getWrappedNode() {
		return inner;
	}

	@Override
	public NodeType getNodeType() {
		return NodeType.DOCUMENT;
	}

	@Override
	public <R> R query(Query<R> q) {
		return q.queryFrom(this);
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
	public ElementNode getDocumentElement() {
		return new DefaultElementNode(inner.getDocumentElement());
	}

	@Override
	public ElementNode createElement(String tagName) {
		return new DefaultElementNode(inner.createElement(tagName));
	}

	@Override
	public DocumentFragmentNode createDocumentFragment() {
		return new DefaultDocumentFragmentNode(inner.createDocumentFragment());
	}

	@Override
	public TextNode createText(String data) {
		return new DefaultTextNode(inner.createTextNode(data));
	}

	@Override
	public CommentNode createComment(String data) {
		return new DefaultCommentNode(inner.createComment(data));
	}

	@Override
	public CDATASectionNode createCDATASection(String data) {
		return new DefaultCDATASectionNode(inner.createCDATASection(data));
	}

	@Override
	public AttributeNode createAttribute(String name) {
		return new DefaultAttributeNode(inner.createAttribute(name));
	}

	@Override
	public BranchNode getParentNode() {
		return null;
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
	public TreeStructuredNode getPreviousSibling() {
		return null;
	}

	@Override
	public TreeStructuredNode getNextSibling() {
		return null;
	}

	@Override
	public boolean hasParentNode() {
		return false;
	}

	@Override
	public boolean hasPreviousSibling() {
		return false;
	}

	@Override
	public boolean hasNextSibling() {
		return false;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public boolean isBranch() {
		return true;
	}
}
