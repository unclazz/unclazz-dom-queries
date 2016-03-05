package org.unclazz.dom.queries;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;

final class DefaultDocumentNode implements DocumentNode {
	private final Document inner;
	
	DefaultDocumentNode(final Document inner) {
		this.inner = inner;
	}

	@Override
	public Document getWrappedNode() {
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
		return NodeKindUtils.wrapElements(inner.getElementsByTagName(tagName));
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
	public TextNode createTextNode(String data) {
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
	public boolean isLeaf() {
		return false;
	}

	@Override
	public boolean isBranch() {
		return false;
	}
	
	@Override
	public String toString() {
		return "DocumentNode()";
	}

	@Override
	public DocumentNode getOwnerDocument() {
		return null;
	}

	@Override
	public DocumentTypeNode getDocumentType() {
		final DocumentType t = inner.getDoctype();
		return t == null ? null : new DefaultDocumentTypeNode(t);
	}

	@Override
	public boolean isRoot() {
		return true;
	}

	@Override
	public DocumentNode getOwnerDocument(boolean self) {
		return self ? this : null;
	}
}
