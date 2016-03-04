package org.unclazz.dom1;

import java.util.List;

import org.w3c.dom.Node;
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
	public static final RelativeNodesQuery children = new ChildrenQuery();
	
	/**
	 * XMLノードの子孫要素にアクセスするためのクエリ.
	 * <p>このクエリ・オブジェクトを使った問合せの結果返されるリストの要素の順序は、
	 * 原則としてXMLの木構造を深さ優先で探索した結果の順序となる。
	 * このクエリ・オブジェクトのメンバーを通じてより特殊化された問合せを行うクエリを生成することができる。</p>
	 */
	public static final RelativeNodesQuery descendants = new DescendantsQuery();
	
	/**
	 * XMLノードの祖先要素にアクセスするためのクエリ.
	 * <p>このクエリ・オブジェクトを使った問合せの結果返されるリストの要素の順序は、原則として親→祖父→曽祖父という順序となる。
	 * 通常リストの最後の要素は{@link DocumentNode}となる。
	 * このクエリ・オブジェクトのメンバーを通じてより特殊化された問合せを行うクエリを生成することができる。</p>
	 */
	public static final RelativeNodesQuery ancestors = new AncestorsQuery();
	
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
	public static final RelativeNodesQuery prevs = new SiblingsQuery(true);
	
	/**
	 * XMLノードの兄弟要素にアクセスするためのクエリ.
	 * <p>このクエリ・オブジェクトを使った問合せの結果返されるリストの要素の順序は、起点となるノードとの近接度で決まる。
	 * リストの先頭の要素は起点となるノードにもっとも近接したノードとなる。
	 * このクエリ・オブジェクトのメンバーを通じてより特殊化された問合せを行うクエリを生成することができる。</p>
	 */
	public static final RelativeNodesQuery nexts = new SiblingsQuery(false);
	
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
	 * <p>class属性が指定されていない場合は空の{@link List}を返す。</p>
	 */
	public static final ListQuery<String> classes = new ClassesQuery();
	
	/**
	 * XMLノードの子孫要素のうちから指定されたclass属性値を持つものを問い合わせるクエリを返す.
	 * @param className class属性値
	 * @return クエリ
	 */
	public static final ListQuery<ElementNode> className(final String className) {
		return descendants.element().className(className);
	}
	
	/**
	 * XMLノードをオーナー・ドキュメントとして新規ノードを作成するクエリのファクトリ.
	 */
	public static final CreateQueryFactory create = new CreateQueryFactory();
	
	/**
	 * XMLノードの子ノードとして別のノードを挿入するクエリのファクトリ.
	 * @param newChild 挿入するノード
	 * @return ファクトリ
	 */
	public static final InsertQueryFactory insert(final Node newChild) {
		return new InsertQueryFactory(newChild);
	}
	
	/**
	 * XMLノードの子ノードとして別のノードを挿入するクエリのファクトリ.
	 * @param newChild 挿入するノード
	 * @return ファクトリ
	 */
	public static final InsertQueryFactory insert(final NodeKind newChild) {
		return new InsertQueryFactory(newChild.getWrappedNode());
	}
	
	/**
	 * XMLノードの子ノードとして別のノードを挿入するクエリのファクトリ.
	 * @param newChild 挿入するノードを返すクエリ
	 * @return ファクトリ
	 */
	public static final InsertQueryFactory insert(final Query<NodeKind> newChild) {
		return new InsertQueryFactory(newChild);
	}
	
	private static final AppendQueryFactory appendQueryFactory = new AppendQueryFactory();
	
	/**
	 * XMLノードの子ノードの先頭に別のノードを挿入するクエリを返す.
	 * @param newChild 挿入されるノード
	 * @return クエリ
	 */
	public static final Query<NodeKind> prepend(final Node newChild) {
		return appendQueryFactory.prepend(newChild);
	}
	
	/**
	 * XMLノードの子ノードの先頭に別のノードを挿入するクエリを返す.
	 * @param newChild 挿入されるノード
	 * @return クエリ
	 */
	public static final Query<NodeKind> prepend(final NodeKind newChild) {
		return appendQueryFactory.prepend(newChild);
	}
	
	/**
	 * XMLノードの子ノードの先頭に別のノードを挿入するクエリを返す.
	 * @param newChild 挿入されるノードを返すクエリ
	 * @return クエリ
	 */
	public static final Query<NodeKind> prepend(final Query<NodeKind> newChild) {
		return appendQueryFactory.prepend(newChild);
	}
	
	/**
	 * XMLノードの子ノードの末尾に別のノードを挿入するクエリを返す.
	 * @param newChild 挿入されるノードを返すクエリ
	 * @return クエリ
	 */
	public static final Query<NodeKind> append(final Query<NodeKind> newChild) {
		return appendQueryFactory.append(newChild);
	}
	
	/**
	 * XMLノードの子ノードの末尾に別のノードを挿入するクエリを返す.
	 * @param newChild 挿入されるノード
	 * @return クエリ
	 */
	public static final Query<NodeKind> append(final NodeKind newChild) {
		return appendQueryFactory.append(newChild);
	}
	
	/**
	 * XMLノードの子ノードの末尾に別のノードを挿入するクエリを返す.
	 * @param newChild 挿入されるノード
	 * @return クエリ
	 */
	public static final Query<NodeKind> append(final Node newChild) {
		return appendQueryFactory.append(newChild);
	}
}
