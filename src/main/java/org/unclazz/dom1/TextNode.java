package org.unclazz.dom1;

public interface TextNode extends UZNode, HasNodeValue {
	TextNode splitText(int offset);
}
