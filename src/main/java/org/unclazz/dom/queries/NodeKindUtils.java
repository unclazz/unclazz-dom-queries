package org.unclazz.dom.queries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Notation;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

final class NodeKindUtils {
	private NodeKindUtils() {}
	
	static TreeStructure wrapTreeStructure(final Node node) {
		if (node == null) {
			return null;
		}
		if (node instanceof Attr) {
			return new DefaultAttributeNode((Attr) node);
		} else if (node instanceof CDATASection) {
			return new DefaultCDATASectionNode((CDATASection) node);
		} else if (node instanceof Comment) {
			return new DefaultCommentNode((Comment) node);
		} else if (node instanceof DocumentFragment) {
			return new DefaultDocumentFragmentNode((DocumentFragment) node);
		} else if (node instanceof Document) {
			return new DefaultDocumentNode((Document) node);
		} else if (node instanceof DocumentType) {
			return new DefaultDocumentTypeNode((DocumentType) node);
		} else if (node instanceof Element) {
			return new DefaultElementNode((Element) node);
		} else if (node instanceof Entity) {
			return new DefaultEntityNode((Entity) node);
		} else if (node instanceof EntityReference) {
			return new DefaultEntityReferenceNode((EntityReference) node);
		} else if (node instanceof Notation) {
			return new DefaultNotationNode((Notation) node);
		} else if (node instanceof ProcessingInstruction) {
			return new DefaultProcessingInstructionNode((ProcessingInstruction) node);
		} else if (node instanceof Text) {
			return new DefaultTextNode((Text) node);
		}
		
		throw illegalArgument("Illegal target. The instance of %s "
				+ "cannot be wrapped with interface TreeStructure.", 
				node.getClass().getSimpleName());
	}
	
	static CharSequence toCharSequence(final NodeKind node) {
		if (node == null) {
			return null;
		}
		
		final StringBuilder sb = new StringBuilder();
		final Node wrapped = node.getWrappedNode();
		
		if (node instanceof AttributeNode) {
			sb.append("AttributeNode(").append(wrapped.getNodeName())
			.append('=').append(escapeAndQuote(wrapped.getNodeValue())).append(')');
			
		} else if (node instanceof CDATASectionNode) {
			sb.append("CDATASectionNode(")
			.append(escapeAndQuote(wrapped.getNodeValue())).append(')');
			
		} else if (node instanceof CommentNode) {
			sb.append("CommentNode(")
			.append(escapeAndQuote(wrapped.getNodeValue())).append(')');
			
		} else if (node instanceof DocumentFragmentNode) {
			sb.append("DocumentFragmentNode()");
			
		} else if (node instanceof DocumentNode) {
			sb.append("DocumentNode()");
			
		} else if (node instanceof DocumentTypeNode) {
			sb.append("DocumentTypeNode(").append(wrapped.getNodeName()).append(')');;
			
		} else if (node instanceof ElementNode) {
			sb.append("ElementNode(<").append(wrapped.getNodeName()).append("/>)");
			
		} else if (node instanceof EntityNode) {
			sb.append("EntityNode(").append(wrapped.getNodeName()).append(')');
			
		} else if (node instanceof EntityReferenceNode) {
			sb.append("EntityReferenceNode(").append(wrapped.getNodeName()).append(')');
			
		} else if (node instanceof NotationNode) {
			sb.append("NotationNode(").append(wrapped.getNodeName()).append(')');;
			
		} else if (node instanceof ProcessingInstructionNode) {
			sb.append("ProcessingInstructionNode(").append(wrapped.getNodeName()).append(')');;
			
		} else if (node instanceof TextNode) {
			sb.append("TextNode(")
			.append(escapeAndQuote(wrapped.getNodeValue())).append(')');
			
		} else {
			throw illegalArgument("Illegal target. The instance of %s "
					+ "cannot convert into character sequence.", 
					node.getClass().getSimpleName());
		}
		
		return sb;
	}
	
	private static CharSequence escapeAndQuote(final CharSequence original) {
		final StringBuilder raw = new StringBuilder(original);
		final int max = 12;
		if (max < original.length()) {
			raw.setLength(max - 3);
			raw.append("...");
		}
		
		final int len = raw.length();
		final StringBuilder sb = new StringBuilder().append('"');
		for (int i = 0; i < len; i ++) {
			final char c = raw.charAt(i);
			switch (c) {
			case '\b':
				sb.append("\\b");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '"':
				sb.append("\\\"");
				break;
			case '\'':
				sb.append("\\'");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			default:
				sb.append(c);
			}
		}
		return sb.append('"');
	}
	
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
	
	static List<TreeStructure> wrapTreeStructureList(final NodeList nodeList) {
		final int len = nodeList.getLength();
		if (len == 0) {
			return Collections.emptyList();
		}
		final ArrayList<TreeStructure> result = new ArrayList<TreeStructure>(len);
		for (int i = 0; i < len; i++) {
			final Node n = nodeList.item(i);
			final TreeStructure tsn = wrapTreeStructure(n);
			if (n != null) {
				result.add(tsn);
			}
		}
		return result;
	}
	
	private static IllegalArgumentException illegalArgument(final String format, final Object... args) {
		return new IllegalArgumentException(String.format(format, args));
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
