package org.unclazz.dom1;

import org.unclazz.dom1.TreeStructuredNode.BranchNode;
import org.w3c.dom.Document;

public interface DocumentNode extends Nodal, ElementIncludable, BranchNode, NodeWrapper<Document> {
	ElementNode getDocumentElement();
	ElementNode createElement(String tagName);
	DocumentFragmentNode createDocumentFragment();
	TextNode createText(String data);
	CommentNode createComment(String data);
	CDATASectionNode createCDATASection(String data);
	AttributeNode createAttribute(String name);
}
