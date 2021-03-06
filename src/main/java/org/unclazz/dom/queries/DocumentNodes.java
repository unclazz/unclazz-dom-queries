package org.unclazz.dom.queries;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * {@link DocumentNode}のためのユーティリティ・クラス.
 * <p>種々のデータソースからXMLドキュメントを読み取るためのメソッドを提供する。</p>
 */
public final class DocumentNodes {
	private DocumentNodes() {}
	
	private static DocumentBuilder defaultDocumentBuilder() throws ParserConfigurationException {
		return DocumentBuilderFactory.newInstance().newDocumentBuilder();
	}
	
	/**
	 * 入力ストリームからXMLドキュメントを読み取る.
	 * @param is 入力ストリーム
	 * @param builder カスタマイズした{@link DocumentBuilder}インスタンス
	 * @return {@link DocumentNode}インスタンス
	 * @throws ParseException XMLドキュメントのパース中にエラーが発生した場合
	 */
	public static DocumentNode from(final InputStream is, final DocumentBuilder builder) throws ParseException {
		try {
			return new DefaultDocumentNode(builder.parse(is));
		} catch (final SAXException e) {
			throw new ParseException("Error has occurred. The xml content may be malformed.", e);
		} catch (final IOException e) {
			throw new ParseException("Error has occurred. It is caused by I/O problem.", e);
		}
	}
	
	/**
	 * 入力ストリームからXMLドキュメントを読み取る.
	 * @param is 入力ストリーム
	 * @return {@link DocumentNode}インスタンス
	 * @throws ParseException XMLドキュメントのパース中にエラーが発生した場合
	 */
	public static DocumentNode fromStream(final InputStream is) throws ParseException {
		try {
			return from(is, defaultDocumentBuilder());
		} catch (final ParserConfigurationException e) {
			throw new RuntimeException("Unexpected error has occurred.", e);
		}
	}
	
	/**
	 * ファイルからXMLドキュメントを読み取る.
	 * @param file ファイル
	 * @param builder カスタマイズした{@link DocumentBuilder}インスタンス
	 * @return {@link DocumentNode}インスタンス
	 * @throws ParseException XMLドキュメントのパース中にエラーが発生した場合
	 */
	public static DocumentNode fromFile(final File file, final DocumentBuilder builder) throws ParseException {
		try {
			return new DefaultDocumentNode(builder.parse(file));
		} catch (SAXException e) {
			throw new ParseException("Error has occurred. The xml content may be malformed.", e);
		} catch (final IOException e) {
			throw new ParseException("Error has occurred. It is caused by I/O problem.", e);
		}
	}
	
	/**
	 * ファイルからXMLドキュメントを読み取る.
	 * @param file ファイル
	 * @return {@link DocumentNode}インスタンス
	 * @throws ParseException XMLドキュメントのパース中にエラーが発生した場合
	 */
	public static DocumentNode fromFile(final File file) throws ParseException {
		try {
			return fromFile(file, defaultDocumentBuilder());
		} catch (final ParserConfigurationException e) {
			throw new RuntimeException("Unexpected error has occurred.", e);
		}
	}
	
	/**
	 * 文字シーケンスからXMLドキュメントを読み取る.
	 * @param cs 文字シーケンス
	 * @return {@link DocumentNode}インスタンス
	 * @throws ParseException XMLドキュメントのパース中にエラーが発生した場合
	 */
	public static DocumentNode fromCharSequence(final CharSequence cs) throws ParseException {
		final ByteArrayInputStream is = new ByteArrayInputStream(cs.toString().getBytes());
		return fromStream(is);
	}
	
	/**
	 * {@link Document}を内容とする{@link DocumentNode}インスタンスを返す.
	 * @param document XMLドキュメント
	 * @return {@link DocumentNode}インスタンス
	 */
	public static DocumentNode of(final Document document) {
		return new DefaultDocumentNode(document);
	}
}
