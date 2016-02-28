package org.unclazz.dom1;

public interface TextNode extends Nodal, HasNodeValue {
	TextNode splitText(int offset);
}
