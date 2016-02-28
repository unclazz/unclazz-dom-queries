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
	 * <p>このクエリ・オブジェクトを使った問合せの結果返されるリストの要素の順序は、
	 * 原則としてXMLの木構造を深さ優先で探索した結果の順序となる。
	 * このクエリ・オブジェクトのメンバーを通じてより特殊化された問合せを行うクエリを生成することができる。</p>
	 */
	public static final DescendantsQuery descendents = new DescendantsQuery();
	/**
	 * XMLノードの祖先要素にアクセスするためのクエリ.
	 * <p>このクエリ・オブジェクトを使った問合せの結果返されるリストの要素の順序は、原則として親→祖父→曽祖父という順序となる。
	 * 通常リストの最後の要素は{@link DocumentNode}となる。
	 * このクエリ・オブジェクトのメンバーを通じてより特殊化された問合せを行うクエリを生成することができる。</p>
	 */
	public static final AncestorsQuery ancestors = new AncestorsQuery();
	/**
	 * XMLノードの属性情報にアクセスするためのクエリ.
	 * <p>このクエリ・オブジェクトのメンバーを通じてより特殊化された問合せを行うクエリを生成することができる。</p>
	 */
	public static final AttributesQuery attributes = new AttributesQuery();
	/**
	 * XMLノードの兄弟要素にアクセスするためのクエリ.
	 * <p>このクエリ・オブジェクトを使った問合せの結果返されるリストの要素の順序は、起点となるノードとの近接度で決まる。
	 * リストの先頭の要素は起点となるノードにもっとも近接したノードとなる。
	 * このクエリ・オブジェクトのメンバーを通じてより特殊化された問合せを行うクエリを生成することができる。</p>
	 */
	public static final SiblingsQuery previouSiblings = new SiblingsQuery(true);
	/**
	 * XMLノードの兄弟要素にアクセスするためのクエリ.
	 * <p>このクエリ・オブジェクトを使った問合せの結果返されるリストの要素の順序は、起点となるノードとの近接度で決まる。
	 * リストの先頭の要素は起点となるノードにもっとも近接したノードとなる。
	 * このクエリ・オブジェクトのメンバーを通じてより特殊化された問合せを行うクエリを生成することができる。</p>
	 */
	public static final SiblingsQuery nextSiblings = new SiblingsQuery(true);
}
