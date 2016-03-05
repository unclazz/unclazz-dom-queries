package org.unclazz.dom1.sample;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.unclazz.dom1.AttributeNode;
import org.unclazz.dom1.CommentNode;
import org.unclazz.dom1.DocumentNode;
import org.unclazz.dom1.DocumentNodes;
import org.unclazz.dom1.ElementNode;
import org.unclazz.dom1.NodeKind;
import org.unclazz.dom1.Queries;
import org.unclazz.dom1.TreeStructure;
import org.unclazz.dom1.TreeStructure.Branch;
import org.w3c.dom.Document;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import static org.unclazz.dom1.Queries.*;

public class Sample {

	public static void main(String[] args) throws SAXException, IOException, TransformerException {
		final DocumentNode dn = DocumentNodes.from(new File("src/test/resources/sample.xml"));
		final ElementNode foo1 = dn.query(descendants.element("foo").one());
		final ElementNode bar1 = dn.query(descendants.element("bar").one());
		
		foo1.query(insert(create.element("baz")
				.attribute("baz-attr", "baz-attr-value")
				.attribute("baz-attr2", "baz-attr2-value")
				.append(create.element("baz-child"))
				.text("@@@@@@@")).last());
		
		for (final NodeKind nk : foo1.query(children)) {
			System.out.println(nk);
			for (final NodeKind nk2 : nk.query(children)) {
				System.out.println("> " + nk2);
			}
		}
		
		System.out.println(dn.query(children.element("sample").one()).query(writeTo.charSequence()));
	}

}
