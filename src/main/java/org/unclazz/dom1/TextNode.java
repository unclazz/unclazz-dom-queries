package org.unclazz.dom1;

public interface TextNode extends NodeKind {
	TextNode splitText(int offset);
	String getValue();
}
