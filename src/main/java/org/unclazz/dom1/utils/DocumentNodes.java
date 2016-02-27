package org.unclazz.dom1.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public final class DocumentNodes {
	private DocumentNodes() {}
	
	private static DocumentBuilder defaultDocumentBuilder() throws ParserConfigurationException {
		return DocumentBuilderFactory.newInstance().newDocumentBuilder();
	}
	
	public static DocumentNode from(final InputStream is, final DocumentBuilder builder)
			throws SAXException, IOException {
		return new DefaultDocumentNode(builder.parse(is));
	}
	
	public static DocumentNode from(final InputStream is) throws SAXException, IOException {
		try {
			return from(is, defaultDocumentBuilder());
		} catch (final ParserConfigurationException e) {
			throw new RuntimeException("Unexpected error has occurred.", e);
		}
	}
	
	public static DocumentNode from(final File file, final DocumentBuilder builder)
			throws SAXException, IOException {
		return new DefaultDocumentNode(builder.parse(file));
	}
	
	public static DocumentNode from(final File file) throws SAXException, IOException {
		try {
			return from(file, defaultDocumentBuilder());
		} catch (final ParserConfigurationException e) {
			throw new RuntimeException("Unexpected error has occurred.", e);
		}
	}
	
	public static DocumentNode from(final CharSequence cs) throws SAXException {
		final ByteArrayInputStream is = new ByteArrayInputStream(cs.toString().getBytes());
		try {
			return from(is);
		} catch (final IOException e) {
			throw new RuntimeException("Unexpected error has occurred.", e);
		}
	}
}
