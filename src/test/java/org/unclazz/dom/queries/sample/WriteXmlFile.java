package org.unclazz.dom.queries.sample;

import static org.unclazz.dom.queries.Queries.*;
import static org.unclazz.dom.queries.sample.SampleUtils.*;

import java.io.File;

import org.unclazz.dom.queries.DocumentNode;
import org.unclazz.dom.queries.DocumentNodes;
import org.unclazz.dom.queries.ElementNode;
import org.unclazz.dom.queries.ParseException;

/**
 * XMLドキュメントを文字シーケンスや出力ストリームに書き出すサンプル.
 */
public class WriteXmlFile {
	
	public static void main(String[] args) throws ParseException {
		// XMLドキュメントをパースする
		final File xml = new File(SAMPLE_XML_PATH);
		final DocumentNode dn = DocumentNodes.fromFile(xml);
		
		// <foo/>の1つめを取得
		final ElementNode foo0 = dn.query(descendants.element("foo").one());
		
		// 要素（タグ）を文字シーケンスとして書き出す
		printLabel("ElementNode.query(writeTo.charSequence())");;
		System.out.println(foo0.query(writeTo.charSequence()));
		
		// ドキュメント全体をストリームに書き出す
		printLabel("DocumentNode.query(writeTo.stream(System.out))");;
		dn.query(writeTo.stream(System.out));
	}
}
