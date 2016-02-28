package org.unclazz.dom1.sample;

import java.io.File;
import java.io.IOException;

import org.unclazz.dom1.DocumentNode;
import org.unclazz.dom1.DocumentNodes;
import org.unclazz.dom1.ElementNode;
import org.unclazz.dom1.Queries;
import org.unclazz.dom1.TreeStructuredNode.BranchNode;
import org.xml.sax.SAXException;

public class Sample {

	public static void main(String[] args) throws SAXException, IOException {
		final DocumentNode dn = DocumentNodes.from(new File("src/test/resources/sample.xml"));
		for (final BranchNode en : dn.query(Queries.descendents.tag("bar").one()).query(Queries.ancestors)) {
			System.out.println("> " + en.getNodeType());
		}
		
		System.out.println(dn.query(Queries.descendents.text().concat(true)));
		
		System.out.println(dn.query(Queries.descendents.tag("bar").one()).getAttribute("id"));
	}

}
