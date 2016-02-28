package org.unclazz.dom1;

import java.util.Map;

import org.unclazz.dom1.TreeStructuredNode.BranchNode;

/**
 * DOMのElementをあらわすインターフェース.
 */
public interface ElementNode extends NodeKind, ElementIncludable, BranchNode {
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
