package org.unclazz.dom1;

import org.w3c.dom.Node;

/**
 * {@link Node}オブジェクトのラッパー.
 * <p>APIユーザ・コードにおける{@link Node}オブジェクトとの名前衝突を避けるため
 * インターフェース名には形容詞を使用している。</p>
 * <p>DOMの実装とは異なりこのライブラリにおける{@link Nodal}は必要最小限のメンバーのみを宣言している。
 * このオブジェクトがあらわすXMLノードから値を取得したりこのノードを起点にしてXMLの木構造をトラバースするには、
 * {@link #query(Query)}メソッドを利用する。
 * 引数として指定する{@link Query}オブジェクトの種々の定義済み実装は{@link Queries}を通じて得られる。</p>
 */
public interface Nodal {
	/**
	 * このXMLノードの種別を返す.
	 * @return ノード種別
	 */
	NodeType getNodeType();
	/**
	 * {@link Query}オブジェクトを利用してこのXMLノードから（あるいはこのノードを起点として）情報を取得する.
	 * <p>このメソッドが返すデータの型と内容は引数として渡されたクエリの宣言と実装に依存する。</p>
	 * @param q クエリ
	 * @return 取得結果
	 */
	<R> R query(Query<R> q);
	/**
	 * このオブジェクトがラップしている{@link Node}オブジェクトを返す.
	 * @return ノード
	 */
	Node getWrappedNode();
}
