package org.unclazz.dom1.utils;

public interface TreeStructuredNode extends UZNode {
	boolean isLeaf();
	boolean isBranch();
	BranchNode getParentNode();
	TreeStructuredNode getPreviousSibling();
	TreeStructuredNode getNextSibling();
	boolean hasParentNode();
	boolean hasPreviousSibling();
	boolean hasNextSibling();
}
