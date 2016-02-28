package org.unclazz.dom1;

import java.util.Iterator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * {@link NodeList}をラップする{@link Iterable}オブジェクト.
 */
public class NodeIterable implements Iterable<Node> {
	/**
	 * {@link NodeList}をラップする.
	 * @param nodeList ラップ対象
	 * @return インスタンス
	 */
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
