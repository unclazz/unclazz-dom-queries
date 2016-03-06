package org.unclazz.dom.queries.sample;

import static org.unclazz.dom.queries.Queries.*;
import static org.unclazz.dom.queries.sample.SampleUtils.*;

import java.io.File;

import org.unclazz.dom.queries.DocumentNode;
import org.unclazz.dom.queries.DocumentNodes;
import org.unclazz.dom.queries.ElementNode;
import org.unclazz.dom.queries.ParseException;

/**
 * XMLドキュメントの木構造に新しいノードを追加するサンプル.
 */
public class ManipulateXmlFile {
	
	public static void main(String[] args) throws ParseException {
		// XML（HTML）ドキュメントをパースする
		final File html = new File(SAMPLE_HTML_PATH);
		final DocumentNode dn = DocumentNodes.fromFile(html);
		
		// ドキュメントの子孫ノードのうちからbody要素（＝<html/>）を取得
		final ElementNode bodyTag = dn.query(descendants.element("body").one());
		
		// html要素の子ノードの末尾に新規作成したp要素を追加
		bodyTag.query(insert(create.element("p").className("new")
				.text("3rd paragraph...")).last());
		
		// 追加した要素とその要素内のテキストを取得
		final ElementNode pTag = bodyTag.query(children.element("p")).get(2);
		System.out.println(pTag.query(children.text().concat()));
	}
}
