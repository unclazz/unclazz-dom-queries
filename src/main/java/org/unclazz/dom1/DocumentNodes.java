package org.unclazz.dom1;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
	 * @return XMLドキュメント
	 * @throws SAXException XMLドキュメントの読み取り中にエラーが発生した場合
	 * @throws IOException 入力ストリームからの読み取り中にエラーが発生した場合
	 */
	public static DocumentNode from(final InputStream is, final DocumentBuilder builder)
			throws SAXException, IOException {
		return new DefaultDocumentNode(builder.parse(is));
	}
	
	/**
	 * 入力ストリームからXMLドキュメントを読み取る.
	 * @param is 入力ストリーム
	 * @return XMLドキュメント
	 * @throws SAXException XMLドキュメントの読み取り中にエラーが発生した場合
	 * @throws IOException 入力ストリームからの読み取り中にエラーが発生した場合
	 */
	public static DocumentNode from(final InputStream is) throws SAXException, IOException {
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
	 * @return XMLドキュメント
	 * @throws SAXException XMLドキュメントの読み取り中にエラーが発生した場合
	 * @throws IOException ファイルの読み取り中にエラーが発生した場合
	 */
	public static DocumentNode from(final File file, final DocumentBuilder builder)
			throws SAXException, IOException {
		return new DefaultDocumentNode(builder.parse(file));
	}
	
	/**
	 * ファイルからXMLドキュメントを読み取る.
	 * @param file ファイル
	 * @return XMLドキュメント
	 * @throws SAXException XMLドキュメントの読み取り中にエラーが発生した場合
	 * @throws IOException ファイルの読み取り中にエラーが発生した場合
	 */
	public static DocumentNode from(final File file) throws SAXException, IOException {
		try {
			return from(file, defaultDocumentBuilder());
		} catch (final ParserConfigurationException e) {
			throw new RuntimeException("Unexpected error has occurred.", e);
		}
	}
	
	/**
	 * 文字シーケンスからXMLドキュメントを読み取る.
	 * @param cs 文字シーケンス
	 * @return XMLドキュメント
	 * @throws SAXException XMLドキュメントの読み取り中にエラーが発生した場合
	 */
	public static DocumentNode from(final CharSequence cs) throws SAXException {
		final ByteArrayInputStream is = new ByteArrayInputStream(cs.toString().getBytes());
		try {
			return from(is);
		} catch (final IOException e) {
			throw new RuntimeException("Unexpected error has occurred.", e);
		}
	}
}
