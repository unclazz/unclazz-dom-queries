package org.unclazz.dom1;

import java.util.List;

public interface TreeStructuredNode extends Nodal {
	public interface LeafNode extends TreeStructuredNode {}
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
	
	boolean isLeaf();
	boolean isBranch();
	BranchNode getParentNode();
	TreeStructuredNode getPreviousSibling();
	TreeStructuredNode getNextSibling();
	boolean hasParentNode();
	boolean hasPreviousSibling();
	boolean hasNextSibling();
}
