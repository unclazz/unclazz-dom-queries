package org.unclazz.dom.queries.sample;

import static org.unclazz.dom.queries.Queries.*;
import static org.unclazz.dom.queries.sample.SampleUtils.*;

import java.io.File;

import org.unclazz.dom.queries.DocumentNode;
import org.unclazz.dom.queries.DocumentNodes;
import org.unclazz.dom.queries.ElementNode;
import org.unclazz.dom.queries.ParseException;

/**
 * XMLドキュメントの木構造内をトラバースするサンプル.
 */
public class TraverseXmlFile {
	
	public static void main(String[] args) throws ParseException {
		// XML（HTML）ドキュメントをパースする
		final File html = new File(SAMPLE_HTML_PATH);
		final DocumentNode dn = DocumentNodes.from(html);
		
		// ドキュメントに直属する要素（＝<html/>）を取得
		final ElementNode htmlTag = dn.getDocumentElement();
		
		// html要素の子ノードのうちheadという名前の要素（<head/>）を1つだけ取得
		final ElementNode headTag = htmlTag.query(children.element("head").one());
		
		// head要素のあとに続くXMLノードのうち要素（タグ）だけ、しかもその直近の1つだけ（<body/>）を取得
		final ElementNode bodyTag = headTag.query(nexts.element().one());
		
		// body要素の子孫ノードのうちliという名前の要素（<li/>）をすべて取得
		printLabel("ElementNode.query(descendants.element(\"li\")), then ElementNode.query(text)");
		for (final ElementNode en : bodyTag.query(descendants.element("li"))) {
			// li要素の子孫ノードのテキストを一括（連結）して取得
			System.out.println(en.query(text));
		}
	}
}
