package org.unclazz.dom1;

import org.w3c.dom.Text;

class DefaultTextNode extends AbstractTreeStructureLeaf implements TextNode {
	private final Text inner;
	DefaultTextNode(final Text inner) {
		super(inner);
		this.inner = inner;
	}

	@Override
	public String getValue() {
		return inner.getTextContent();
	}

	@Override
	public TextNode splitText(int offset) {
		return new DefaultTextNode(inner.splitText(offset));
	}

	@Override
	public String toString() {
		return "TextNode()";
	}

	@Override
	public Text getWrappedNode() {
		return inner;
	}
}
