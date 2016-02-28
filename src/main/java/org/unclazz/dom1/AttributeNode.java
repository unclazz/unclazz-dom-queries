package org.unclazz.dom1;

import org.w3c.dom.Attr;

public interface AttributeNode extends Nodal, HasNodeValue, NodeWrapper<Attr> {
	String getName();
	void setValue(String value);
	boolean isSpecified();
}
