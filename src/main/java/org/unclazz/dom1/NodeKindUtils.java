package org.unclazz.dom1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.unclazz.dom1.TreeStructuredNode.BranchNode;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

final class NodeKindUtils {
	private NodeKindUtils() {}
	
	static List<ElementNode> wrapElements(final NodeList nodeList) {
		final int len = nodeList.getLength();
		if (len == 0) {
			return Collections.emptyList();
		}
		final ArrayList<ElementNode> result = new ArrayList<ElementNode>(len);
		for (int i = 0; i < len; i++) {
			final Node n = nodeList.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				result.add(new DefaultElementNode((Element) n));
			}
		}
		return result;
	}
	
	static List<TreeStructuredNode> wrapTreeStructuredNodes(final NodeList nodeList) {
		final int len = nodeList.getLength();
		if (len == 0) {
			return Collections.emptyList();
		}
		final ArrayList<TreeStructuredNode> result = new ArrayList<TreeStructuredNode>(len);
		for (int i = 0; i < len; i++) {
			final Node n = nodeList.item(i);
			final TreeStructuredNode tsn = wrapTreeStructuredNode(n);
			if (n != null) {
				result.add(tsn);
			}
		}
		return result;
	}
	
	static TreeStructuredNode wrapTreeStructuredNode(final Node node) {
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
			throw new IllegalArgumentException(String.format(
					"Unsupported node type (node-type = %s).",
					NodeType.valueOf(node.getNodeType())));
		}
	}
	
	static BranchNode wrapBranchNode(final Node node) {
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
		default:
			throw new IllegalArgumentException(String.format(
					"Unsupported node type (node-type = %s).",
					NodeType.valueOf(node.getNodeType())));
		}
	}
	
	static Map<String, AttributeNode> wrapAttributesMap(final NamedNodeMap nnm) {
		final int size = nnm.getLength();
		if (size == 0) {
			return Collections.emptyMap();
		}
		
		final Map<String, AttributeNode> result = new HashMap<String, AttributeNode>();
		for (int i = 0; i < size; i ++) {
			final Node n = nnm.item(i);
			if (n instanceof Attr) {
				final Attr a = (Attr) n;
				result.put(a.getName(), new DefaultAttributeNode(a));
			} else {
				throw new IllegalArgumentException();
			}
		}
		
		return result;
	}
}
