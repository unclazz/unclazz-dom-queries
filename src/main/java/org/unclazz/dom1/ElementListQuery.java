package org.unclazz.dom1;

import org.w3c.dom.Node;

/**
 * {@link ElementNode}の問合わせを行うクエリ.
 * <p>このオブジェクト自体がクエリとして機能すると同時に、
 * このオブジェクトのメンバーが返すオブジェクトもまたより特殊化された問合せを行うクエリとして機能する。
 * インスタンスは{@link RelativeNodesQuery#element()}メソッドなどを通じて得られる。
 * </p>
 */
public class ElementListQuery extends FunctionalListQuery<Node, ElementNode> {
	private static final Function<? extends TreeStructuredNode, ElementNode> treeStructuredNode2ElementNode =
		new Function<TreeStructuredNode, ElementNode>() {
		@Override
		public ElementNode apply(TreeStructuredNode target) {
			return target instanceof ElementNode ? (ElementNode) target : null;
		}
	};
	
	private final FunctionalListQuery<Node, ElementNode> inner;
	
	<T extends TreeStructuredNode>
	ElementListQuery(final FunctionalListQuery<Node, T> childrenQuery) {
		this(childrenQuery, null);
	}
	
	<T extends TreeStructuredNode>
	ElementListQuery(final FunctionalListQuery<Node, T> childrenQuery, final String name) {
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
	public AttributedElementListQuery hasAttribute(final String name) {
		return new AttributedElementListQuery(this, name);
	}
	/**
	 * 指定されたclass属性値を持つ{@link ElementNode}を返すクエリを返す.
	 * @param className class属性名
	 * @return クエリ
	 */
	public ListQuery<ElementNode> className(final String className) {
		return this.and(new Function<ElementNode, ElementNode>() {
			@Override
			public ElementNode apply(ElementNode target) {
				return target.query(Queries.classes).contains(className) ? target : null;
			}
		});
	}

	public final Query<ElementNode> id(final String id) {
		final ListQuery<ElementNode> base = this;
		return new Query<ElementNode>() {
			@Override
			public ElementNode queryFrom(final NodeKind n) {
				for (final ElementNode e : base.queryFrom(n)) {
					if (id.equals(e.getAttribute("id"))) {
						return e;
					}
				}
				return null;
			}
		};
	}
}
