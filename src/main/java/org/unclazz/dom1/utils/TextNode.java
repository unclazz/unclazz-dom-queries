package org.unclazz.dom1.utils;

public interface TextNode extends UZNode, HasNodeValue {
	TextNode splitText(int offset);
}
