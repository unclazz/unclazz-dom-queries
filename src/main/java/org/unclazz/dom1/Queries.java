package org.unclazz.dom1;

import org.w3c.dom.Text;

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
	public static final DescendantsQuery descendants = new DescendantsQuery();
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
	public static final SiblingsQuery prevs = new SiblingsQuery(true);
	/**
	 * XMLノードの兄弟要素にアクセスするためのクエリ.
	 * <p>このクエリ・オブジェクトを使った問合せの結果返されるリストの要素の順序は、起点となるノードとの近接度で決まる。
	 * リストの先頭の要素は起点となるノードにもっとも近接したノードとなる。
	 * このクエリ・オブジェクトのメンバーを通じてより特殊化された問合せを行うクエリを生成することができる。</p>
	 */
	public static final SiblingsQuery nexts = new SiblingsQuery(false);
	/**
	 * XMLノードの子孫要素をid属性値で問い合わせるためのクエリ.
	 * <p>起点となる要素の子孫要素のなかに該当する要素が見つからなかった場合このクエリは{@code null}を返す。
	 * @param id ID
	 * @return クエリ
	 */
	public static final Query<ElementNode> id(final String id) {
		return new IdQuery(id);
	}
	/**
	 * XMLノードの子孫{@link Text}ノードの内容をまとめて取得するためのクエリ.
	 */
	public static final Query<CharSequence> text = descendants.text().concat();
	/**
	 * XMLノードの子孫{@link Text}ノードの内容をまとめて取得するためのクエリを返す.
	 * @param trim {@code true}の場合各{@link Text}ノードの内容はトリムされた上で連結される
	 * @return クエリ
	 */
	public static final Query<CharSequence> text(final boolean trim) {
		return descendants.text().concat(trim);
	}
	/**
	 * XMLノードのclass属性を取得するためのクエリ.
	 * <p>class属性が指定されていない場合は空の{@like List}を返す。</p>
	 */
	public static final ListQuery<String> classes = new ClassesQuery();
	/**
	 * XMLノードの子孫要素のうちから指定されたclass属性値を持つものを問い合わせるクエリを返す.
	 * @param className class属性値
	 * @return クエリ
	 */
	public static final ListQuery<ElementNode> className(final String className) {
		return descendants.tag().className(className);
	}
}
