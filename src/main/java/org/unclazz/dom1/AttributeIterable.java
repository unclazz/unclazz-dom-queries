package org.unclazz.dom1;

import java.util.Iterator;

import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * {@link Attr}を要素とする{@link NamedNodeMap}をラップする{@link Iterable}オブジェクト.
 * <p>{@link NamedNodeMap}には要素型の制約付けを行う仕組みがない。
 * そしてこの{@link AttributeIterable}は{@link NamedNodeMap}の要素型の事前チェックを行わない。
 * したがって{@link Iterator}を使用したイテレート処理中に実行時例外がスローされる可能性がある。</p>
 */
public class AttributeIterable implements Iterable<Attr> {
	/**
	 * {@link NamedNodeMap}をラップする.
	 * @param nodeMap ラップする対象
	 * @return インスタンス
	 */
	public static AttributeIterable wrap(final NamedNodeMap nodeMap) {
		return new AttributeIterable(nodeMap);
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
