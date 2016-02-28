package org.unclazz.dom1;

import org.w3c.dom.Attr;

public interface AttributeNode extends UZNode, HasNodeValue, NodeWrapper<Attr> {
	String getName();
	void setValue(String value);
	boolean isSpecified();
}
