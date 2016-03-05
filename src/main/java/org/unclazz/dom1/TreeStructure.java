package org.unclazz.dom1;

import java.util.List;

public interface TreeStructure extends NodeKind {
	public interface Root extends TreeStructure {
		ElementNode getDocumentElement();
		DocumentTypeNode getDocumentType();
		List<ElementNode> getElementsByTagName(String tagName);
	}
	public interface Leaf extends TreeStructure {
		TreeStructure getParentNode();
		TreeStructure getPreviousSibling();
		TreeStructure getNextSibling();
		boolean hasParentNode();
		boolean hasPreviousSibling();
		boolean hasNextSibling();
	}
	public interface Branch extends Leaf {
		TreeStructure getParentNode();
		List<TreeStructure> getChildNodes();
		TreeStructure getFirstChild();
		TreeStructure getLastChild();
		TreeStructure getPreviousSibling();
		TreeStructure getNextSibling();
		boolean hasParentNode();
		boolean hasChildNodes();
		boolean hasPreviousSibling();
		boolean hasNextSibling();
	}
	
	boolean isRoot();
	boolean isBranch();
	boolean isLeaf();
}
