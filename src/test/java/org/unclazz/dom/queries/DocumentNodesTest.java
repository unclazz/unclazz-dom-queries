package org.unclazz.dom.queries;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.unclazz.dom.queries.TestUtils.*;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DocumentNodesTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void from_whenValidXmlSequenceSpecified_returnsDocumentNodeInstance() throws ParseException {
		// Arrange
		
		// Act
		final DocumentNode d = DocumentNodes.fromCharSequence("<foo></foo>");
		
		// Assert
		assertThat(d, notNullValue());
	}

	@Test
	public void from_whenValidXmlFileSpecified_returnsDocumentNodeInstance() throws ParseException {
		// Arrange
		
		// Act
		final DocumentNode d = DocumentNodes.fromFile(new File(SAMPLE_XML_PATH));
		
		// Assert
		assertThat(d, notNullValue());
	}
}
