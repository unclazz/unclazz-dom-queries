package org.unclazz.dom.queries;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.unclazz.dom.queries.TestUtils.*;

import java.util.List;
import java.util.NoSuchElementException;

import static org.unclazz.dom.queries.Queries.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ChildrenQueryTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void queryFrom_whenReceiverIsDocumentNode_returnsSingletonList() {
		// Arrange
		final DocumentNode d = sampleXml();
		
		// Act
		final List<TreeStructure> r = d.query(children);
		
		// Assert
		assertThat(r.size(), equalTo(1));
		assertThat(((ElementNode) r.get(0)).getTagName(), equalTo("sample"));
	}

	@Test
	public void queryFrom_whenReceiverIsElementNode_returnsChildNodeList() {
		// Arrange
		final ElementNode d = sampleXml().getDocumentElement();
		
		// Act
		final List<TreeStructure> r = d.query(children);
		
		// Assert
		assertThat(r.size(), equalTo(5));
	}

	@Test
	public void one$queryFrom_whenReceiverHasAChildNode_returnsTheNode() {
		// Arrange
		final ElementNode d = sampleXml().getDocumentElement();
		
		// Act
		final TreeStructure r = d.query(children.one());
		
		// Assert
		assertThat(r.getNodeType(), equalTo(NodeType.TEXT));
	}

	@Test
	public void one$queryFrom_whenReceiverHasNotAChildNode_throwsException() {
		// Arrange
		final ElementNode d = sampleXml().query(descendants.element("bar").one());
		exception.expect(NoSuchElementException.class);
		
		// Act
		final TreeStructure r = d.query(children.one());
		
		// Assert
		fail(r.toString());
	}

	@Test
	public void element$queryFrom_whenReceiverIsElementNode_returnsChildElementNodeList() {
		// Arrange
		final ElementNode d = sampleXml().getDocumentElement();
		
		// Act
		final List<ElementNode> r = d.query(children.element());
		
		// Assert
		assertThat(r.size(), equalTo(2));
	}

	@Test
	public void element$one$queryFrom_whenReceiverHasAChildElement_returnsTheNode() {
		// Arrange
		final ElementNode d = sampleXml().getDocumentElement();
		
		// Act
		final ElementNode r = d.query(children.element().one());
		
		// Assert
		assertThat(r.getTagName(), equalTo("foo"));
	}

	@Test
	public void element$one$queryFrom_whenReceiverHasNotAChildElement_throwsException() {
		// Arrange
		final ElementNode d = sampleXml().query(descendants.element("bar").one());
		exception.expect(NoSuchElementException.class);
		
		// Act
		final ElementNode r = d.query(children.element().one());
		
		// Assert
		fail(r.toString());
	}

	@Test
	public void cdata$queryFrom_whenReceiverIsElementNode_returnsChildCDATASectionNodeList() {
		// Arrange
		final ElementNode d = sampleXml().query(descendants.element("foo").id("foo1"));
		
		// Act
		final List<CDATASectionNode> r = d.query(children.cdata());
		
		// Assert
		assertThat(r.size(), equalTo(1));
	}

	@Test
	public void cdata$one$queryFrom_whenReceiverHasAChildCDATASection_returnsTheNode() {
		// Arrange
		final ElementNode d = sampleXml().query(descendants.element("foo").id("foo1"));
		
		// Act
		final CDATASectionNode r = d.query(children.cdata().one());
		
		// Assert
		assertThat(r.getNodeType(), equalTo(NodeType.CDATA_SECTION));
	}

	@Test
	public void cdata$one$queryFrom_whenReceiverHasNotAChildCDATASection_throwsException() {
		// Arrange
		final ElementNode d = sampleXml().query(descendants.element("foo").id("foo0"));
		exception.expect(NoSuchElementException.class);

		// Act
		final CDATASectionNode r = d.query(children.cdata().one());
		
		// Assert
		fail(r.toString());
	}

	@Test
	public void text$queryFrom_whenReceiverIsElementNode_returnsTextNodeList() {
		// Arrange
		final ElementNode d = sampleXml().query(descendants.element().id("bar1"));

		// Act
		final List<TextNode> r = d.query(children.text());
		
		// Assert
		assertThat(r.size(), equalTo(1));
		assertThat(r.get(0).getValue(), equalTo(" "));
	}

	@Test
	public void text$concat$queryFrom_whenReceiverHasATextNode_returnsConcatenatedSequence() {
		// Arrange
		final ElementNode d = sampleXml().query(descendants.element().id("bar1"));

		// Act
		final CharSequence r = d.query(children.text().concat());
		
		// Assert
		assertThat(r.length(), equalTo(1));
		assertThat(r.toString(), equalTo(" "));
	}

	@Test
	public void text$concat$queryFrom_whenReceiverHasNoTextNode_returnsEmptySequence() {
		// Arrange
		final ElementNode d = sampleXml().query(descendants.element().id("bar0"));

		// Act
		final CharSequence r = d.query(children.text().concat());
		
		// Assert
		assertThat(r.length(), equalTo(0));
		assertThat(r.toString(), equalTo(""));
	}

	@Test
	public void text$concatTrue$queryFrom_whenReceiverHasATextNode_returnsTrimmedSequence() {
		// Arrange
		final ElementNode d = sampleXml().query(descendants.element().id("bar1"));

		// Act
		final CharSequence r = d.query(children.text().concat(true));
		
		// Assert
		assertThat(r.length(), equalTo(0));
		assertThat(r.toString(), equalTo(""));
	}

	@Test
	public void text$concatFalse$queryFrom_whenReceiverHasATextNode_returnsUntrimmedSequence() {
		// Arrange
		final ElementNode d = sampleXml().query(descendants.element().id("bar1"));

		// Act
		final CharSequence r = d.query(children.text().concat(false));
		
		// Assert
		assertThat(r.length(), equalTo(1));
		assertThat(r.toString(), equalTo(" "));
	}

	@Test
	public void text$containsString$queryFrom_whenReceiverHasATextNodeContainsTheString_returnsNonemptyList() {
		// Arrange
		final ElementNode d = sampleXml().query(descendants.element().id("foo1"));

		// Act
		final List<TextNode> r = d.query(children.text().contains("a"));
		
		// Assert
		assertThat(r.size(), equalTo(1));
		assertThat(r.get(0).getValue().trim(), equalTo("baz"));
	}

	@Test
	public void text$containsString$queryFrom_whenReceiverHasNoTextNodeContainsTheString_returnsEmptyList() {
		// Arrange
		final ElementNode d = sampleXml().query(descendants.element().id("foo1"));

		// Act
		final List<TextNode> r = d.query(children.text().contains("c"));
		
		// Assert
		assertThat(r.size(), equalTo(0));
	}

	@Test
	public void text$startsWithString$queryFrom_whenReceiverHasATextNodeStartsWithTheString_returnsNonemptyList() {
		// Arrange
		final ElementNode d = sampleXml().query(descendants.element().id("foo1"));

		// Act
		final List<TextNode> r = d.query(children.text().startsWith("\n\t\tba"));
		
		// Assert
		assertThat(r.size(), equalTo(1));
	}

	@Test
	public void text$startsWithString$queryFrom_whenReceiverHasNoTextNodeStartsWithTheString_returnsEmptyList() {
		// Arrange
		final ElementNode d = sampleXml().query(descendants.element().id("foo1"));

		// Act
		final List<TextNode> r = d.query(children.text().startsWith("\n\t\tca"));
		
		// Assert
		assertThat(r.size(), equalTo(0));
	}

	@Test
	public void text$startsWithString$queryFrom_whenReceiverHasATextNodeEndsWithTheString_returnsNonemptyList() {
		// Arrange
		final ElementNode d = sampleXml().query(descendants.element().id("foo1"));

		// Act
		final List<TextNode> r = d.query(children.text().endsWith("az\n\t"));
		
		// Assert
		assertThat(r.size(), equalTo(1));
	}

	@Test
	public void text$startsWithString$queryFrom_whenReceiverHasNoTextNodeEndsWithTheString_returnsEmptyList() {
		// Arrange
		final ElementNode d = sampleXml().query(descendants.element().id("foo1"));

		// Act
		final List<TextNode> r = d.query(children.text().endsWith("bz\n\t"));
		
		// Assert
		assertThat(r.size(), equalTo(0));
	}
}
