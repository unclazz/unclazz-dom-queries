package org.unclazz.dom.queries.sample;

public final class SampleUtils {
	private SampleUtils() {}
	
	public static final String SAMPLE_HTML_PATH = "src/test/resources/sample.html";
	public static final String SAMPLE_XML_PATH = "src/test/resources/sample.xml";
	
	public static void printLabel(final String label) {
		System.out.println();
		System.out.println(label);
		System.out.println(border(label.length()));
	}
	
	private static CharSequence border(final int length) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i ++) {
			sb.append('-');
		}
		return sb;
	}
}
