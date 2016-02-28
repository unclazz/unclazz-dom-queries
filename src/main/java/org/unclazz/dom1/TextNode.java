package org.unclazz.dom1;

public interface TextNode extends NodeKind, HasNodeValue {
	TextNode splitText(int offset);
}
