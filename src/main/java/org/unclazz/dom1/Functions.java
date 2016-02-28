package org.unclazz.dom1;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

final class Functions {
	private Functions(){}
	
	public static final UniFunction<Node, TreeStructuredNode> node2TreeStructuredNode =
			new UniFunction<Node, TreeStructuredNode>() {
		@Override
		public TreeStructuredNode apply(Node node) {
			if (node == null) {
				return null;
			}
			
			switch (node.getNodeType()) {
			case Node.DOCUMENT_NODE:
				return new DefaultDocumentNode((Document) node);
			case Node.DOCUMENT_FRAGMENT_NODE:
				return new DefaultDocumentFragmentNode((DocumentFragment) node);
			case Node.ELEMENT_NODE:
				return new DefaultElementNode((Element) node);
			case Node.TEXT_NODE:
				return new DefaultTextNode((Text) node);
			case Node.COMMENT_NODE:
				return new DefaultCommentNode((Comment) node);
			case Node.CDATA_SECTION_NODE:
				return new DefaultCDATASectionNode((CDATASection) node);
			default:
				return null;
			}
		}
	};
}
