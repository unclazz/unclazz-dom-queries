package org.unclazz.dom1;

import org.w3c.dom.Comment;

final class DefaultCommentNode extends AbstractTreeStructureLeaf implements CommentNode {
	private final Comment inner;
	DefaultCommentNode(final Comment inner) {
		super(inner);
		this.inner = inner;
	}

	@Override
	public String getValue() {
		return inner.getTextContent();
	}

	@Override
	public Comment getWrappedNode() {
		return inner;
	}
	
	@Override
	public String toString() {
		return "CommentNode(<!-- ... -->)";
	}
}
