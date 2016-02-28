package org.unclazz.dom1;

import java.util.Collections;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;

/**
 * {@link AttributeNode}を取得するためのクエリ.
 */
public class AttributesQuery extends FunctionalListQuery<Attr, AttributeNode> {
	AttributesQuery() {}
	
	@Override
	protected Iterable<Attr> source(NodeKind n) {
		final NamedNodeMap nm =  n.getWrappedNode().getAttributes();
		if (nm == null) {
			return Collections.emptyList();
		}
		
		return AttributeIterable.wrap(nm);
	}
	
	@Override
	protected Function<Attr, AttributeNode> function() {
		return new Function<Attr, AttributeNode>() {
			@Override
			public AttributeNode apply(Attr target) {
				return new DefaultAttributeNode(target);
			}
		};
	}

	/**
	 * 属性が明示的に指定されているかどうかを判定し条件に合致したものだけを返すクエリを生成する.
	 * @param specified {@code true}の場合明示的に指定されている属性を返す
	 * @return 属性リストを返すクエリ
	 */
	public AttributesQuery specified(final boolean specified) {
		return new AttributesQuery() {
			@Override
			protected Function<Attr, AttributeNode> function() {
				return new Function<Attr, AttributeNode>() {
					@Override
					public AttributeNode apply(Attr target) {
						if (target.getSpecified() == specified) {
							return new DefaultAttributeNode(target);
						} else {
							return null;
						}
					}
				};
			}
		};
	}

	/**
	 * 引数で指定された名前を持つ属性を返すクエリを生成する.
	 * <p>名前の照合のとき大文字小文字は区別される。
	 * 指定された名前の属性が存在しない場合このクエリは{@code null}を返す。</p>
	 * @param name 属性名
	 * @return 属性を返すクエリ
	 */
	public Query<AttributeNode> nameEquals(final String name) {
		return new Query<AttributeNode>() {
			@Override
			public AttributeNode queryFrom(NodeKind n) {
				for (final AttributeNode a : AttributesQuery.this.queryFrom(n)) {
					if (name.equals(name)) {
						return a;
					}
				}
				return null;
			}
		};
	}
	
	/**
	 * 引数で指定された名前を持つ属性を返すクエリを生成する.
	 * <p>名前の照合のとき大文字小文字は区別されない。
	 * 結果値には単一性が保証されないことになるため、このクエリの返す値の型は{@link List}になる。
	 * 指定された名前に合致する属性が存在しない場合は空の{@link List}が返される。</p>
	 * @param name 属性名
	 * @return 属性リストを返すクエリ
	 */
	public ListQuery<AttributeNode> nameEqualsIgnoreCase(final String name) {
		return this.and(new Function<AttributeNode, AttributeNode>() {
			@Override
			public AttributeNode apply(AttributeNode target) {
				return target.getName().equalsIgnoreCase(name) ? target : null;
			}
		});
	}
	
	/**
	 * 引数で指定された部分文字列で名前がはじまる属性を返すクエリを生成する.
	 * <p>名前の照合のとき大文字小文字は区別される。
	 * 条件に合致する属性が存在しない場合は空の{@link List}が返される。</p>
	 * @param s 部分文字列
	 * @return 属性リストを返すクエリ
	 */
	public ListQuery<AttributeNode> nameStartsWith(final String s) {
		return this.and(new Function<AttributeNode, AttributeNode>() {
			@Override
			public AttributeNode apply(AttributeNode target) {
				return target.getName().startsWith(s) ? target : null;
			}
		});
	}
	
	/**
	 * 引数で指定された部分文字列で名前が終わる属性を返すクエリを生成する.
	 * <p>名前の照合のとき大文字小文字は区別される。
	 * 条件に合致する属性が存在しない場合は空の{@link List}が返される。</p>
	 * @param s 部分文字列
	 * @return 属性リストを返すクエリ
	 */
	public ListQuery<AttributeNode> nameEndsWith(final String s) {
		return this.and(new Function<AttributeNode, AttributeNode>() {
			@Override
			public AttributeNode apply(AttributeNode target) {
				return target.getName().endsWith(s) ? target : null;
			}
		});
	}
	
	/**
	 * 引数で指定された部分文字列が名前に含まれる属性を返すクエリを生成する.
	 * <p>名前の照合のとき大文字小文字は区別される。
	 * 条件に合致する属性が存在しない場合は空の{@link List}が返される。</p>
	 * @param s 部分文字列
	 * @return 属性リストを返すクエリ
	 */
	public ListQuery<AttributeNode> nameContains(final String s) {
		return this.and(new Function<AttributeNode, AttributeNode>() {
			@Override
			public AttributeNode apply(AttributeNode target) {
				return target.getName().contains(s) ? target : null;
			}
		});
	}
}
