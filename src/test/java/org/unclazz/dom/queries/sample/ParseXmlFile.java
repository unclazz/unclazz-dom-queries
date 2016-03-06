package org.unclazz.dom.queries.sample;

import static org.unclazz.dom.queries.Queries.*;
import static org.unclazz.dom.queries.sample.SampleUtils.*;

import java.io.File;

import org.unclazz.dom.queries.DocumentNode;
import org.unclazz.dom.queries.DocumentNodes;
import org.unclazz.dom.queries.NodeKind;
import org.unclazz.dom.queries.ParseException;

/**
 * ファイルや文字シーケンスからXMLドキュメントを読み込むサンプル.
 */
public class ParseXmlFile {
	
	public static void main(String[] args) throws ParseException {
		// ファイルからXMLドキュメントをパースする
		final File xml = new File(SAMPLE_XML_PATH);
		final DocumentNode dn = DocumentNodes.fromFile(xml);
		
		// ドキュメントに含まれる要素（タグ）を列挙する
		printLabel("DocumentNode.query(descendants.element())");
		for (final NodeKind nk : dn.query(descendants.element())) {
			System.out.println(nk);
		}
		
		// 文字シーケンスからXMLドキュメントをパースする
		final DocumentNode dn2 = DocumentNodes.fromCharSequence("<sample>"
				+ "<foo/><bar>text content</bar><baz> </baz></sample>");
		
		// ドキュメントの含まれる要素のうち子要素を持つものを列挙する
		printLabel("DocumentNode.query(descendants.element().hasChildren())");
		for (final NodeKind nk : dn2.query(descendants.element().hasChildren())) {
			System.out.println(nk);
		}
	}
}
