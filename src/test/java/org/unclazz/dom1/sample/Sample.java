package org.unclazz.dom1.sample;

import java.io.File;
import java.io.IOException;

import org.unclazz.dom1.AttributeNode;
import org.unclazz.dom1.DocumentNode;
import org.unclazz.dom1.DocumentNodes;
import org.unclazz.dom1.ElementNode;
import org.unclazz.dom1.Queries;
import org.unclazz.dom1.TreeStructuredNode.BranchNode;
import org.xml.sax.SAXException;
import static org.unclazz.dom1.Queries.*;

public class Sample {

	public static void main(String[] args) throws SAXException, IOException {
		final DocumentNode dn = DocumentNodes.from(new File("src/test/resources/sample.xml"));
		final ElementNode foo1 = dn.query(descendents.tag("foo").one());
		
		for (final AttributeNode en : foo1.query(attributes.specified(true))) {
			System.out.println("> " + en.getName());
		}
	}

}