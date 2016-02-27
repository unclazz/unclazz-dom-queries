package org.unclazz.dom1.utils;

import java.util.Map;

import org.w3c.dom.Element;

/**
 * DOMのElementをあらわすインターフェース.
 */
public interface ElementNode extends UZNode, ElementIncludable, BranchNode, NodeWrapper<Element> {
	String getTagName();
	boolean hasAttribute(String name);
	String getAttribute(String name);
	void setAttribute(String name, String value);
	AttributeNode getAttributeNode(String name);
	void setAttributeNode(AttributeNode newAttr);
	AttributeNode removeAttributeNode(AttributeNode oldAttr);
	void normalize();
	Map<String, AttributeNode> getAttributes();
}
