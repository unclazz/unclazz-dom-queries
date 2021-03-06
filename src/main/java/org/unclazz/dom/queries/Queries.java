package org.unclazz.dom.queries;

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
	public static final InsertQueryFactory<NodeKind> insert(final Node newChild) {
		return new InsertQueryFactory<NodeKind>(newChild);
	}
	
	/**
	 * XMLノードの子ノードとして別のノードを挿入するクエリのファクトリ.
	 * @param newChild 挿入するノード
	 * @return ファクトリ
	 */
	public static final InsertQueryFactory<NodeKind> insert(final NodeKind newChild) {
		return new InsertQueryFactory<NodeKind>(newChild.getWrappedNode());
	}
	
	/**
	 * XMLノードの子ノードとして別のノードを挿入するクエリのファクトリ.
	 * @param newChild 挿入するノードを返すクエリ
	 * @return ファクトリ
	 * @param <T> 挿入するノードの型（{@link NodeKind}もしくはそのサブ・インターフェース）
	 */
	public static final<T extends NodeKind> InsertQueryFactory<T> insert(final Query<T> newChild) {
		return new InsertQueryFactory<T>(newChild);
	}
	
	/**
	 * XMLドキュメントの全体もしくは一部分を各種出力ソースに書き出すためのクエリのファクトリ.
	 */
	public static final WriteToQueryFactory writeTo = new WriteToQueryFactory();
	
	/**
	 * XMLドキュメントからノードを取り除くためのクエリのファクトリ.
	 */
	public static final RemoveQueryFactory remove = new RemoveQueryFactory();
}
