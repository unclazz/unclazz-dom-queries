package org.unclazz.dom1;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class WriteToQueryFactory {
	WriteToQueryFactory() {}
	
	public Query<OutputStream> stream(final OutputStream stream, final Charset charset) {
		return new Query<OutputStream>() {
			@Override
			public OutputStream queryFrom(final NodeKind n) {
				toStream(n, stream, charset);
				return stream;
			}
		};
	}
	
	public Query<OutputStream> stream(final OutputStream stream) {
		return stream(stream, Charset.forName("utf-8"));
	}
	
	public Query<File> file(final File file, final Charset charset) {
		return new Query<File>() {
			@Override
			public File queryFrom(final NodeKind n) {
				toFile(n, file, charset);
				return file;
			}
		};
	}
	
	public Query<File> file(final File file) {
		return file(file, Charset.forName("utf-8"));
	}
	
	public Query<CharSequence> charSequence(final Charset charset) {
		return new Query<CharSequence>() {
			@Override
			public CharSequence queryFrom(final NodeKind n) {
				return toCharSequence(n, charset);
			}
		};
	}
	
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
			final FileOutputStream fos = new FileOutputStream(file);
			try {
				toStream(nodeKind, fos, charset);
			} finally {
				fos.close();
			}
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void toStream(final NodeKind nodeKind, final OutputStream stream, final Charset charset) {
		try {
			final StreamResult res = new StreamResult(stream);
			final TransformerFactory tff = TransformerFactory.newInstance();
			final Transformer tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, charset.name());
			if (!(nodeKind instanceof DocumentNode)) {
				tf.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			}
			final DOMSource src = new DOMSource(nodeKind.getWrappedNode());
			tf.transform(src, res);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
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
			throw new RuntimeException(e);
		}
	}
}
