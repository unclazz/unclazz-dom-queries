package org.unclazz.dom1;

import java.util.List;

import org.w3c.dom.Node;

/**
 * {@link TextNode}の問合せを行うクエリ.
 * <p>このオブジェクト自体がクエリとして機能すると同時に、
 * このオブジェクトのメンバーが返すオブジェクトもまたより特殊化された問合せを行うクエリとして機能する。
 * インスタンスは{@link Queries#text}を通じて得られる。</p>
 */
public class TextListQuery extends FunctionalListQuery<Node, TextNode> {
	private static Function<TreeStructure, TextNode> treeStructureNode2TextNode =
			new Function<TreeStructure, TextNode>() {
		@Override
		public TextNode apply(TreeStructure target) {
			return target instanceof TextNode ? (TextNode) target : null;
		}
	};
	
	private final FunctionalListQuery<Node, TextNode> inner;
	
	TextListQuery(final FunctionalListQuery<Node, TreeStructure> childrenQuery) {
		this.inner = childrenQuery.and(treeStructureNode2TextNode);
	}

	@Override
	protected Iterable<Node> source(NodeKind n) {
		return inner.source(n);
	}

	@Override
	protected Function<Node, TextNode> function() {
		return inner.function();
	}
	
	/**
	 * 問合せ結果に含まれる{@link TextNode}の文字列を連結するクエリを返す.
	 * @return 連結された結果の文字シーケンス
	 */
	public Query<CharSequence> concat() {
		return concat(false);
	}
	
	/**
	 * 問合せ結果に含まれる{@link TextNode}の文字列を連結するクエリを返す.
	 * @param trim {@code true}の場合トリミングをする
	 * @return 連結された結果の文字シーケンス
	 */
	public Query<CharSequence> concat(final boolean trim) {
		return new Query<CharSequence>() {
			@Override
			public CharSequence queryFrom(NodeKind n) {
				final List<TextNode> l = TextListQuery.this.queryFrom(n);
				if (l.isEmpty()) {
					return "";
				}
				final StringBuilder buff = new StringBuilder();
				for (final TextNode t : l) {
					buff.append(trim ? t.getValue().trim() : t.getValue());
				}
				return buff;
			}
		};
	}
	
	/**
	 * 指定された文字列で始まる{@link TextNode}を返すクエリを返す.
	 * @param s 部分文字列
	 * @return クエリ
	 */
	public FunctionalListQuery<Node, TextNode> startsWith(final String s) {
		return this.and(new Function<TextNode, TextNode>() {
			@Override
			public TextNode apply(TextNode target) {
				return target.getValue().startsWith(s) ? target : null;
			}
		});
	}
	
	/**
	 * 指定された文字列で終わる{@link TextNode}を返すクエリを返す.
	 * @param s 部分文字列
	 * @return クエリ
	 */
	public FunctionalListQuery<Node, TextNode> endsWith(final String s) {
		return this.and(new Function<TextNode, TextNode>() {
			@Override
			public TextNode apply(TextNode target) {
				return target.getValue().endsWith(s) ? target : null;
			}
		});
	}
	
	/**
	 * 指定された文字列を含む{@link TextNode}を返すクエリを返す.
	 * @param s 部分文字列
	 * @return クエリ
	 */
	public FunctionalListQuery<Node, TextNode> contains(final String s) {
		return this.and(new Function<TextNode, TextNode>() {
			@Override
			public TextNode apply(TextNode target) {
				return target.getValue().contains(s) ? target : null;
			}
		});
	}
}
