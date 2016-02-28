package org.unclazz.dom1;

/**
 * {@link Query}オブジェクトのためのユーティリティ・クラス.
 */
public final class Queries {
	private Queries(){}
	
	/**
	 * XMLノードの子要素にアクセスするためのクエリ.
	 * <p>このクエリ・オブジェクトのメンバーを通じてより特殊化された問合せを行うクエリを生成することができる。</p>
	 */
	public static final ChildrenQuery children = new ChildrenQuery();
	/**
	 * XMLノードの子孫要素にアクセスするためのクエリ.
	 * <p>このクエリ・オブジェクトを使った問合せの結果返される要素の順序は、原則としてXMLの木構造を深さ優先で探索した結果の順序となる。
	 * このクエリ・オブジェクトのメンバーを通じてより特殊化された問合せを行うクエリを生成することができる。</p>
	 */
	public static final DescendantsQuery descendents = new DescendantsQuery();
	/**
	 * XMLノードの祖先要素にアクセスするためのクエリ.
	 * <p>このクエリ・オブジェクトを使った問合せの結果返される要素の順序は、原則として親→祖父→曽祖父という順序となる。
	 * このクエリ・オブジェクトのメンバーを通じてより特殊化された問合せを行うクエリを生成することができる。</p>
	 */
	public static final AncestorsQuery ancestors = new AncestorsQuery();
	/**
	 * XMLノードの属性情報にアクセスするためのクエリ.
	 * <p>このクエリ・オブジェクトのメンバーを通じてより特殊化された問合せを行うクエリを生成することができる。</p>
	 */
	public static final AttributesQuery attributes = new AttributesQuery();
	
	
}
