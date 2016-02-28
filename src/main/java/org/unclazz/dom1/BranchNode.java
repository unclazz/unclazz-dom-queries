package org.unclazz.dom1;

import java.util.List;

public interface BranchNode extends TreeStructuredNode {
	BranchNode getParentNode();
	List<TreeStructuredNode> getChildNodes();
	TreeStructuredNode getFirstChild();
	TreeStructuredNode getLastChild();
	TreeStructuredNode getPreviousSibling();
	TreeStructuredNode getNextSibling();
	boolean hasParentNode();
	boolean hasChildNodes();
	boolean hasPreviousSibling();
	boolean hasNextSibling();
}
