package org.unclazz.dom.queries;

import java.io.File;

public final class TestUtils {
	private TestUtils() {}
	
	public static final String SAMPLE_HTML_PATH = "src/test/resources/sample.html";
	public static final String SAMPLE_XML_PATH = "src/test/resources/sample.xml";
	
	public static DocumentNode sampleXml() {
		try {
			return DocumentNodes.from(new File(SAMPLE_XML_PATH));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static DocumentNode sampleHtml() {
		try {
			return DocumentNodes.from(new File(SAMPLE_HTML_PATH));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
