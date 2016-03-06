package org.unclazz.dom.queries;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.unclazz.dom.queries.TestUtils.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DocumentNodeTest {
	
	public static final ExpectedException exception = ExpectedException.none();

	@Test
	public void getDocumentElement_always_returnsFarOuterElementNode() {
		// Arrange
		final DocumentNode d = sampleXml();
		
		// Act
		final ElementNode r = d.getDocumentElement();
		
		// Assert
		assertThat(r.getTagName(), equalTo("sample"));
	}

	@Test
	public void getOwnerDocument_always_returnsNull() {
		// Arrange
		final DocumentNode d = sampleXml();
		
		// Act
		final DocumentNode r = d.getOwnerDocument();
		
		// Assert
		assertNull(r);
	}

	@Test
	public void getOwnerDocument_whenArg0IsFalse_returnsNull() {
		// Arrange
		final DocumentNode d = sampleXml();
		
		// Act
		final DocumentNode r = d.getOwnerDocument(false);
		
		// Assert
		assertNull(r);
	}

	@Test
	public void getOwnerDocument_whenArg0IsTrue_returnsReceiverSelf() {
		// Arrange
		final DocumentNode d = sampleXml();
		
		// Act
		final DocumentNode r = d.getOwnerDocument(true);
		
		// Assert
		assertTrue(d == r);
	}

	@Test
	public void createAttribute_whenArg0IsValid_returnsAttributeNode() {
		// Arrange
		final DocumentNode d = sampleXml();
		
		// Act
		final AttributeNode r = d.createAttribute("foo");
		
		// Assert
		assertThat(r.getName(), equalTo("foo"));
	}

	@Test
	public void createCDATASection_whenArg0IsValid_returnsCDATASectionNode() {
		// Arrange
		final DocumentNode d = sampleXml();
		
		// Act
		final CDATASectionNode r = d.createCDATASection("cdata");
		
		// Assert
		assertThat(r.getValue(), equalTo("cdata"));
	}

	@Test
	public void createComment_whenArg0IsValid_returnsCommentNode() {
		// Arrange
		final DocumentNode d = sampleXml();
		
		// Act
		final CommentNode r = d.createComment("comment");
		
		// Assert
		assertThat(r.getValue(), equalTo("comment"));
	}

	@Test
	public void createDocumentFragment_always_returnsDocumentFragmentNode() {
		// Arrange
		final DocumentNode d = sampleXml();
		
		// Act
		final DocumentFragmentNode r = d.createDocumentFragment();
		
		// Assert
		assertNotNull(r);
	}

	@Test
	public void createElement_whenArg0IsValid_returnsElementNode() {
		// Arrange
		final DocumentNode d = sampleXml();
		
		// Act
		final ElementNode r = d.createElement("tag");
		
		// Assert
		assertThat(r.getTagName(), equalTo("tag"));
	}

	@Test
	public void createTextNode_whenArg0IsValid_returnsTextNode() {
		// Arrange
		final DocumentNode d = sampleXml();
		
		// Act
		final TextNode r = d.createTextNode("text");
		
		// Assert
		assertThat(r.getValue(), equalTo("text"));
	}

	@Test
	public void getDocumentType_whenDocumentTypeDoesNotExist_returnsNull() {
		// Arrange
		final DocumentNode d = sampleXml();
		
		// Act
		final DocumentTypeNode r = d.getDocumentType();
		
		// Assert
		assertNull(r);
	}

	@Test
	public void getDocumentType_whenDocumentTypeExists_returnsNotNull() {
		// Arrange
		final DocumentNode d = sampleHtml();
		
		// Act
		final DocumentTypeNode r = d.getDocumentType();
		
		// Assert
		assertNotNull(r);
	}

	@Test
	public void isBranch_always_returnsFalse() {
		// Arrange
		final DocumentNode d = sampleHtml();
		
		// Act
		final boolean r = d.isBranch();
		
		// Assert
		assertFalse(r);
	}

	@Test
	public void isLeaf_always_returnsFalse() {
		// Arrange
		final DocumentNode d = sampleHtml();
		
		// Act
		final boolean r = d.isLeaf();
		
		// Assert
		assertFalse(r);
	}

	@Test
	public void isRoot_always_returnsTrue() {
		// Arrange
		final DocumentNode d = sampleHtml();
		
		// Act
		final boolean r = d.isRoot();
		
		// Assert
		assertTrue(r);
	}
}
