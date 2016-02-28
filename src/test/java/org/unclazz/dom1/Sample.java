package org.unclazz.dom1;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;

public class Sample {

	public static void main(String[] args) throws SAXException, IOException {
		final DocumentNode dn = DocumentNodes.from(new File("src/test/resources/sample.xml"));
		final List<TreeStructuredNode> tns = dn.query(Queries.children);
		final List<ElementNode> ens = dn.query(Queries.children.tag());
		System.out.println(ens);
		for (final ElementNode en : ens) {
			for (final ElementNode en2 : en.query(Queries.children.tag())) {
				System.out.println(en2.getTagName());
			}
			final List<ElementNode> ens2 = en.query(Queries.children.tag("not-foo"));
			System.out.println(ens2.get(0).getTagName());
		}
	}

}
