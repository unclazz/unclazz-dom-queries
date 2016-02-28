package org.unclazz.dom1;

import org.w3c.dom.Node;

/**
 * {@link ElementNode}を取得するためのクエリ.
 */
public class TagListQuery extends FunctionalListQuery<Node, ElementNode> {
	private static final Function<? extends TreeStructuredNode, ElementNode> treeStructuredNode2ElementNode =
		new Function<TreeStructuredNode, ElementNode>() {
		@Override
		public ElementNode apply(TreeStructuredNode target) {
			return target instanceof ElementNode ? (ElementNode) target : null;
		}
	};
	
	private final FunctionalListQuery<Node, ElementNode> inner;
	
	<T extends TreeStructuredNode>
	TagListQuery(final FunctionalListQuery<Node, T> childrenQuery) {
		this(childrenQuery, null);
	}
	
	<T extends TreeStructuredNode>
	TagListQuery(final FunctionalListQuery<Node, T> childrenQuery, final String name) {
		@SuppressWarnings("unchecked")
		final Function<T, ElementNode> t2ElementNode = 
				(Function<T, ElementNode>) treeStructuredNode2ElementNode;
		
		if (name == null) {
			// 関数を合成して新しいクエリを生成する
			this.inner = childrenQuery.and(t2ElementNode);
		} else {
			// タグ名によるフィルタリングを行うための関数オブジェクトを生成
			final Function<ElementNode, ElementNode> tagName =
					new Function<ElementNode, ElementNode>() {
				@Override
				public ElementNode apply(ElementNode target) {
					return target.getTagName().equalsIgnoreCase(name) ? target : null;
				}
			};
			// 関数を合成して新しいクエリを生成する
			this.inner = childrenQuery.and(t2ElementNode).and(tagName);
		}
	}

	@Override
	protected Iterable<Node> source(NodeKind n) {
		return inner.source(n);
	}

	@Override
	protected Function<Node, ElementNode> function() {
		return inner.function();
	}
	
	/**
	 * 子要素を持つ{@link ElementNode}のみを返すクエリを返す.
	 * @return クエリ
	 */
	public FunctionalListQuery<Node, ElementNode> hasChildren() {
		return this.and(new Function<ElementNode, ElementNode>() {
			@Override
			public ElementNode apply(ElementNode target) {
				return target.hasChildNodes() ? target : null;
			}
		});
	}
	
	/**
	 * 指定された名前の属性を持つ{@link ElementNode}のみを返すクエリを返す.
	 * 属性名の照合のとき大文字小文字は区別される。
	 * @param name 属性名
	 * @return クエリ
	 */
	public FunctionalListQuery<Node, ElementNode> hasAttribute(final String name) {
		return this.and(new Function<ElementNode, ElementNode>() {
			@Override
			public ElementNode apply(ElementNode target) {
				return target.hasAttribute(name) ? target : null;
			}
		});
	}
}
