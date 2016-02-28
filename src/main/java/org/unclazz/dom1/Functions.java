package org.unclazz.dom1;

import org.unclazz.dom1.TreeStructuredNode.BranchNode;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

final class Functions {
	private Functions(){}
	
	public static<A> Function<A, Void> noret() {
		return new Function<A, Void>() {
			@Override
			public Void apply(A target) {
				return null;
			}
		};
	}
	
	public static<A> Function<A, Void> forEach(final Runnable r) {
		return new Function<A, Void>() {
			@Override
			public Void apply(A target) {
				r.run();
				return null;
			}
		};
	}
	
	public static<A> Function<A, A> id() {
		return new Function<A, A>() {
			@Override
			public A apply(A target) {
				return target;
			}
		};
	}
	
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
	
	public static final UniFunction<Nodal, ElementNode> uzNode2ElementNode =
			new UniFunction<Nodal, ElementNode>() {
		@Override
		public ElementNode apply(Nodal target) {
			return target instanceof ElementNode ? (ElementNode) target : null;
		}
	};
	
	public static final UniFunction<Nodal, TextNode> uzNode2TextNode =
			new UniFunction<Nodal, TextNode>() {
		@Override
		public TextNode apply(Nodal target) {
			return target instanceof TextNode ? (TextNode) target : null;
		}
	};
	
	public static final UniFunction<BranchNode, ElementNode> branchNode2ElementNode =
			new UniFunction<BranchNode, ElementNode>() {
		@Override
		public ElementNode apply(BranchNode target) {
			return target instanceof ElementNode ? (ElementNode) target : null;
		}
	};
	
	public static final UniFunction<TreeStructuredNode, ElementNode> treeStructuredNode2ElementNode =
			new UniFunction<TreeStructuredNode, ElementNode>() {
		@Override
		public ElementNode apply(TreeStructuredNode target) {
			return target instanceof ElementNode ? (ElementNode) target : null;
		}
	};
	
	public static final UniFunction<TreeStructuredNode, BranchNode> treeStructuredNode2BranchNode =
			new UniFunction<TreeStructuredNode, BranchNode>() {
		@Override
		public BranchNode apply(TreeStructuredNode target) {
			return target instanceof BranchNode ? (BranchNode) target : null;
		}
	};
	
	public static final UniFunction<TreeStructuredNode, TextNode> treeStructuredNode2TextNode =
			new UniFunction<TreeStructuredNode, TextNode>() {
		@Override
		public TextNode apply(TreeStructuredNode target) {
			return target instanceof TextNode ? (TextNode) target : null;
		}
	};
	
	public static UniFunction<ElementNode, ElementNode> tagNameEquals(final String name) {
		return new UniFunction<ElementNode, ElementNode>() {
			@Override
			public ElementNode apply(ElementNode target) {
				return target.getTagName().equalsIgnoreCase(name) ? target : null;
			}
		};
	};
}
