package org.unclazz.dom1;

import java.util.Iterator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeIterable implements Iterable<Node> {
	public static NodeIterable wrap(final NodeList nodeList) {
		return new NodeIterable(nodeList);
	}
	
	private final NodeList nodeList;
	
	private NodeIterable(final NodeList nodeList) {
		this.nodeList = nodeList;
	}

	@Override
	public Iterator<Node> iterator() {
		return new Iterator<Node>() {
			private int i = 0;
			private final int j = NodeIterable.this.nodeList.getLength();
			@Override
			public boolean hasNext() {
				return i < j;
			}

			@Override
			public Node next() {
				final Node n = nodeList.item(i ++);
				return n;
			}
			
			@Override
			public void remove() {}
		};
	}

}
