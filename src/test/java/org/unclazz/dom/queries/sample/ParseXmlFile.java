package org.unclazz.dom.queries.sample;

import static org.unclazz.dom.queries.Queries.*;

import java.io.File;

import org.unclazz.dom.queries.DocumentNode;
import org.unclazz.dom.queries.DocumentNodes;
import org.unclazz.dom.queries.NodeKind;

public class ParseXmlFile {
	public static final String SAMPLE_HTML_PATH = "src/test/resources/sample.html";
	public static final String SAMPLE_XML_PATH = "src/test/resources/sample.xml";
	
	public static void main(String[] args) throws Exception {
		final DocumentNode dn = DocumentNodes.from(new File("src/test/resources/sample.html"));
		
		for (final NodeKind nk : dn.query(children)) {
			System.out.println(nk);
			for (final NodeKind nk2 : nk.query(children)) {
				System.out.println("> " + nk2);
			}
		}
	}

}
