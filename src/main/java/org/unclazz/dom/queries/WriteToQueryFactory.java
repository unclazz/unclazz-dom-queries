package org.unclazz.dom.queries;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * XMLドキュメントもしくはその一部分を各種出力ソースに書き出すためのクエリのファクトリ.
 * <p>インスタンスは{@link Queries#writeTo}を通じて得られる。</p>
 */
public class WriteToQueryFactory {
	WriteToQueryFactory() {}
	
	/**
	 * 出力ストリームに対して書き出しを行うクエリを返す.
	 * クエリはストリームのクローズを行わない。
	 * @param stream ストリーム
	 * @param charset キャラクターセット
	 * @return {@code null}
	 */
	public Query<Void> stream(final OutputStream stream, final Charset charset) {
		return new Query<Void>() {
			@Override
			public Void queryFrom(final NodeKind n) {
				toStream(n, stream, charset);
				return null;
			}
		};
	}
	
	/**
	 * 出力ストリームに対して書き出しを行うクエリを返す.
	 * クエリはストリームのクローズを行わない。キャラクターセットにはutf-8を利用する。
	 * @param stream ストリーム
	 * @return {@code null}
	 */
	public Query<Void> stream(final OutputStream stream) {
		return stream(stream, Charset.forName("utf-8"));
	}
	
	/**
	 * ファイルに対して書き出しを行うクエリを返す.
	 * @param file ファイル
	 * @param charset キャラクターセット
	 * @return {@code null}
	 */
	public Query<Void> file(final File file, final Charset charset) {
		return new Query<Void>() {
			@Override
			public Void queryFrom(final NodeKind n) {
				toFile(n, file, charset);
				return null;
			}
		};
	}
	
	/**
	 * ファイルに対して書き出しを行うクエリを返す.
	 * キャラクターセットにはutf-8を利用する。
	 * @param file ファイル
	 * @return {@code null}
	 */
	public Query<Void> file(final File file) {
		return file(file, Charset.forName("utf-8"));
	}
	
	/**
	 * 文字シーケンスに対して書き出しを行うクエリを返す.
	 * キャラクターセットはXML宣言のencoding属性値の指定に利用される。
	 * @param charset キャラクターセット
	 * @return 文字シーケンス
	 */
	public Query<CharSequence> charSequence(final Charset charset) {
		return new Query<CharSequence>() {
			@Override
			public CharSequence queryFrom(final NodeKind n) {
				return toCharSequence(n, charset);
			}
		};
	}
	
	/**
	 * 文字シーケンスに対して書き出しを行うクエリを返す.
	 * XML宣言のencoding属性値には{@code "utf-8"}が指定される。
	 * @return 文字シーケンス
	 */
	public Query<CharSequence> charSequence() {
		return charSequence(Charset.forName("utf-8"));
	}
	
	private static CharSequence toCharSequence(final NodeKind nodeKind, final Charset charset) {
		final CharArrayWriter caw = new CharArrayWriter();
		toWriter(nodeKind, caw, charset);
		final StringBuilder builder = new StringBuilder();
		builder.append(caw.toCharArray());
		builder.trimToSize();
		return builder;
	}
	
	private static void toFile(final NodeKind nodeKind, final File file, final Charset charset) {
		try {
			final FileWriter w = new FileWriter(file);
			try {
				toWriter(nodeKind, w, charset);
			} finally {
				w.close();
			}
		} catch (final IOException e) {
			throw new QueryException("Error has occurred. It is caused by I/O problem.", e);
		}
	}
	
	private static void toStream(final NodeKind nodeKind, final OutputStream stream, final Charset charset) {
		final Writer w = new OutputStreamWriter(stream, charset);
		toWriter(nodeKind, w, charset);
	}
	
	private static void toWriter(final NodeKind nodeKind, final Writer writer, final Charset charset) {
		try {
			final StreamResult res = new StreamResult(writer);
			final TransformerFactory tff = TransformerFactory.newInstance();
			final Transformer tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, charset.name());
			if (!(nodeKind instanceof DocumentNode)) {
				tf.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			}
			final DOMSource src = new DOMSource(nodeKind.getWrappedNode());
			tf.transform(src, res);
		} catch (final Exception e) {
			throw new QueryException("Error has occurred. It is caused by I/O problem.", e);
		}
	}
}
