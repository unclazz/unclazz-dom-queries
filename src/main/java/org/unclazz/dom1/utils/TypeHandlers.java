package org.unclazz.dom1.utils;

public final class TypeHandlers {
	private TypeHandlers() {}
	
	public static final TypeHandler<AttributeNode> ATTRIBUTE = new TypeHandler<AttributeNode>() {
		@Override
		public AttributeNode cast(Object node) {
			return node instanceof AttributeNode ? (AttributeNode) node : null;
		}
	};
	public static final TypeHandler<CDATASectionNode> CDATA_SECTION = new TypeHandler<CDATASectionNode>() {
		@Override
		public CDATASectionNode cast(Object node) {
			return node instanceof CDATASectionNode ? (CDATASectionNode) node : null;
		}
	};
	public static final TypeHandler<CommentNode> COMMENT = new TypeHandler<CommentNode>() {
		@Override
		public CommentNode cast(Object node) {
			return node instanceof CommentNode ? (CommentNode) node : null;
		}
	};
	public static final TypeHandler<DocumentNode> DOCUMENT = new TypeHandler<DocumentNode>() {
		@Override
		public DocumentNode cast(Object node) {
			return node instanceof DocumentNode ? (DocumentNode) node : null;
		}
	};
	public static final TypeHandler<DocumentFragmentNode> DOCUMENT_FRAGMENT = new TypeHandler<DocumentFragmentNode>() {
		@Override
		public DocumentFragmentNode cast(Object node) {
			return node instanceof DocumentFragmentNode ? (DocumentFragmentNode) node : null;
		}
	};
	public static final TypeHandler<TextNode> TEXT = new TypeHandler<TextNode>() {
		@Override
		public TextNode cast(Object node) {
			return node instanceof TextNode ? (TextNode) node : null;
		}
	};
	public static final TypeHandler<ElementNode> ELEMENT = new TypeHandler<ElementNode>() {
		@Override
		public ElementNode cast(Object node) {
			return node instanceof ElementNode ? (ElementNode) node : null;
		}
	};
}
