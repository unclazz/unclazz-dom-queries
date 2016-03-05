package org.unclazz.dom1;

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
	
	@Override
	public String toString() {
		return "CDATASectionNode(<![CDATA[ ... ]])";
	}
}
