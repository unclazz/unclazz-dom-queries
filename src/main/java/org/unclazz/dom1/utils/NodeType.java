package org.unclazz.dom1.utils;

import org.w3c.dom.Node;

public enum NodeType {
	DOCUMENT(Node.DOCUMENT_NODE),
	DOCUMENT_FRAGMENT(Node.DOCUMENT_FRAGMENT_NODE),
	DOCUMENT_TYPE(Node.DOCUMENT_TYPE_NODE),
	ENTITY_REFERENCE(Node.ENTITY_REFERENCE_NODE),
	ELEMENT(Node.ELEMENT_NODE),
	ATTRIBUTE(Node.ATTRIBUTE_NODE),
	PROCESSING_INSTRUCTION(Node.PROCESSING_INSTRUCTION_NODE),
	COMMENT(Node.COMMENT_NODE),
	TEXT(Node.TEXT_NODE),
	CDATA_SECTION(Node.CDATA_SECTION_NODE),
	ENTITY(Node.ENTITY_NODE),
	NOTATION(Node.NOTATION_NODE);
	
	private final short nodeTypeValue;
	private NodeType(short val) {
		this.nodeTypeValue = val;
	}
	
	public short getShortValue() {
		return nodeTypeValue;
	}
	
	public static NodeType valueOf(short val) {
		for (final NodeType t : values()) {
			if (t.nodeTypeValue == val) {
				return t;
			}
		}
		throw new IllegalArgumentException(String.
				format("Invalid value as node-type specified (value = %s).", val));
	}
}
