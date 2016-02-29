package org.unclazz.dom1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Node;

/**
 * 属性情報をもとに{@link ElementNode}の問合せを行うクエリ.
 * <p>このオブジェクト自体がクエリとして機能すると同時に、
 * このオブジェクトのメンバーが返すオブジェクトもまたより特殊化された問合せを行うクエリとして機能する。
 * インスタンスは{@link ElementListQuery#hasAttribute(String)}メソッドを通じて得られる。</p>
 */
public class AttributedElementListQuery extends FunctionalListQuery<Node, ElementNode> {
	private final ElementListQuery inner;
	private final String name;
	AttributedElementListQuery(final ElementListQuery inner, final String name) {
		this.inner = inner;
		this.name = name;
	}
	
	@Override
	protected Iterable<Node> source(NodeKind n) {
		return inner.source(n);
	}
	
	@Override
	protected Function<Node, ElementNode> function() {
		return SyntheticFunction.synthesize(inner.function(), 
				new Function<ElementNode, ElementNode>() {
			@Override
			public ElementNode apply(ElementNode target) {
				return target.hasAttribute(name) ? target : null;
			}
		});
	}
	
	/**
	 * このオブジェクトのコンストラクタ引数で指定された名前の属性を持ち、
	 * かつ このメソッドの引数で指定された値を持つ要素を問い合わせるクエリを返す.
	 * @param value 属性値
	 * @return クエリ
	 */
	public ListQuery<ElementNode> valueEquals(final String value) {
		return this.and(new Function<ElementNode, ElementNode>() {
			@Override
			public ElementNode apply(ElementNode target) {
				return value.equals(target.getAttribute(name)) ? target : null;
			}
		});
	}
	/**
	 * このオブジェクトのコンストラクタ引数で指定された名前の属性を持ち、
	 * かつ このメソッドの引数で指定されたパターンにマッチする値を持つ要素を問い合わせるクエリを返す.
	 * @param pattern 正規表現パターン
	 * @return クエリ
	 */
	public ListQuery<ElementNode> valueMatches(final Pattern pattern) {
		return this.and(new Function<ElementNode, ElementNode>() {
			@Override
			public ElementNode apply(ElementNode target) {
				final Matcher m = pattern.matcher(target.getAttribute(name));
				return m.matches() ? target : null;
			}
		});
	}
	/**
	 * このオブジェクトのコンストラクタ引数で指定された名前の属性を持ち、
	 * かつ このメソッドの引数で指定されたパターンにマッチする値を持つ要素を問い合わせるクエリを返す.
	 * @param pattern 正規表現パターン
	 * @return クエリ
	 */
	public ListQuery<ElementNode> valueMatches(final String pattern) {
		return valueMatches(Pattern.compile(pattern));
	}
	/**
	 * このオブジェクトのコンストラクタ引数で指定された名前の属性を持ち、
	 * かつ このメソッドの引数で指定された値で始まる値を持つ要素を問い合わせるクエリを返す.
	 * @param s 属性値の部分文字列
	 * @return クエリ
	 */
	public ListQuery<ElementNode> valueStartsWith(final String s) {
		return this.and(new Function<ElementNode, ElementNode>() {
			@Override
			public ElementNode apply(ElementNode target) {
				return target.getAttribute(name).startsWith(s) ? target : null;
			}
		});
	}
	/**
	 * このオブジェクトのコンストラクタ引数で指定された名前の属性を持ち、
	 * かつ このメソッドの引数で指定された値で終わる値を持つ要素を問い合わせるクエリを返す.
	 * @param s 属性値の部分文字列
	 * @return クエリ
	 */
	public ListQuery<ElementNode> valueEndsWith(final String s) {
		return this.and(new Function<ElementNode, ElementNode>() {
			@Override
			public ElementNode apply(ElementNode target) {
				return target.getAttribute(name).endsWith(s) ? target : null;
			}
		});
	}
	/**
	 * このオブジェクトのコンストラクタ引数で指定された名前の属性を持ち、
	 * かつ このメソッドの引数で指定された値を含む値を持つ要素を問い合わせるクエリを返す.
	 * @param s 属性値の部分文字列
	 * @return クエリ
	 */
	public ListQuery<ElementNode> valueContains(final String s) {
		return this.and(new Function<ElementNode, ElementNode>() {
			@Override
			public ElementNode apply(ElementNode target) {
				return target.getAttribute(name).contains(s) ? target : null;
			}
		});
	}
}
