package org.unclazz.dom1;

import java.util.Iterator;

import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class AttributeIterable implements Iterable<Attr> {
	public static AttributeIterable wrap(final NamedNodeMap nodeList) {
		return new AttributeIterable(nodeList);
	}
	
	private final NamedNodeMap nodeList;
	
	private AttributeIterable(final NamedNodeMap nodeMap) {
		this.nodeList = nodeMap;
	}

	@Override
	public Iterator<Attr> iterator() {
		return new Iterator<Attr>() {
			private int i = 0;
			private final int j = AttributeIterable.this.nodeList.getLength();
			@Override
			public boolean hasNext() {
				return i < j;
			}

			@Override
			public Attr next() {
				final Node n = nodeList.item(i ++);
				if (n instanceof Attr) {
					return (Attr) n;
				} else {
					throw new IllegalStateException(String.format(
							"Node of %s expected but node of %s found.",
							NodeType.ATTRIBUTE, NodeType.valueOf(n.getNodeType())));
				}
			}
			
			@Override
			public void remove() {}
		};
	}

}
