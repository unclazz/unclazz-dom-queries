package org.unclazz.dom.queries;

import org.w3c.dom.CDATASection;

final class DefaultCDATASectionNode extends AbstractTreeStructureLeaf implements CDATASectionNode {
	private final CDATASection inner;
	DefaultCDATASectionNode(final CDATASection inner) {
		super(inner);
		this.inner = inner;
	}

	@Override
	public String getValue() {
		return inner.getTextContent();
	}

	@Override
	public CDATASection getWrappedNode() {
		return inner;
	}
}
