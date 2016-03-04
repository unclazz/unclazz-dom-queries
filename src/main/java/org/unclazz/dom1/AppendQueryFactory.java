package org.unclazz.dom1;

import org.w3c.dom.Node;

/**
 * XMLノードに子ノードとして別のノードを挿入するクエリのファクトリ.
 * <p>このファクトリが生成するクエリはノードの挿入位置を先頭/末尾のいずれかしか指定できない。
 * したがってそのクエリは{@link InsertQueryFactory}が生成するそれに対して比較的シンプルなものにとどまる。</p>
 * <p>とくに記載のない限り、いずれのクエリの{@link Query#queryFrom(NodeKind)}メソッドの戻り値も
 * ノードが挿入されたノード、すなわちメソッドの第1引数そのものとなる。</p>
 */
public class AppendQueryFactory {
	AppendQueryFactory() {}
	
	/**
	 * 子ノードの先頭に新しいノードを挿入するクエリを返す.
	 * @param newChild 挿入されるノードを返すクエリ
	 * @return クエリ
	 */
	public Query<NodeKind> prepend(final Query<NodeKind> newChild) {
		return new Query<NodeKind>() {
			@Override
			public NodeKind queryFrom(final NodeKind parent) {
				final Node parentNode = parent.getWrappedNode();
				final Node first = parentNode.getFirstChild();
				parentNode.insertBefore(newChild.queryFrom(parent).getWrappedNode(), first);
				return parent;
			}
		};
	}
	
	/**
	 * 子ノードの先頭に新しいノードを挿入するクエリを返す.
	 * @param newChild 挿入されるノード
	 * @return クエリ
	 */
	public Query<NodeKind> prepend(final Node newChild) {
		return new Query<NodeKind>() {
			@Override
			public NodeKind queryFrom(final NodeKind parent) {
				final Node parentNode = parent.getWrappedNode();
				final Node first = parentNode.getFirstChild();
				parentNode.insertBefore(newChild, first);
				return parent;
			}
		};
	}
	
	/**
	 * 子ノードの先頭に新しいノードを挿入するクエリを返す.
	 * @param newChild 挿入されるノード
	 * @return クエリ
	 */
	public Query<NodeKind> prepend(final NodeKind newChild) {
		return prepend(newChild.getWrappedNode());
	}
	
	/**
	 * 子ノードの末尾に新しいノードを挿入するクエリを返す.
	 * @param newChild 挿入されるノードを返すクエリ
	 * @return クエリ
	 */
	public Query<NodeKind> append(final Query<NodeKind> newChild) {
		return new Query<NodeKind>() {
			@Override
			public NodeKind queryFrom(final NodeKind n) {
				n.getWrappedNode().appendChild(newChild.queryFrom(n).getWrappedNode());
				return n;
			}
		};
	}
	
	/**
	 * 子ノードの末尾に新しいノードを挿入するクエリを返す.
	 * @param newChild 挿入されるノード
	 * @return クエリ
	 */
	public Query<NodeKind> append(final Node newChild) {
		return new Query<NodeKind>() {
			@Override
			public NodeKind queryFrom(final NodeKind n) {
				n.getWrappedNode().appendChild(newChild);
				return n;
			}
		};
	}
	
	/**
	 * 子ノードの末尾に新しいノードを挿入するクエリを返す.
	 * @param newChild 挿入されるノード
	 * @return クエリ
	 */
	public Query<NodeKind> append(final NodeKind newChild) {
		return append(newChild.getWrappedNode());
	}
}
