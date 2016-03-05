package org.unclazz.dom.queries;

import org.w3c.dom.Node;

/**
 * XMLノードに子ノードとして別のノードを挿入するクエリのファクトリ.
 * <p>このファクトリが生成するクエリはノードの挿入位置を絶対値や相対値で指定することができる。</p>
 * <p>とくに記載のない限り、いずれのクエリの{@link Query#queryFrom(NodeKind)}メソッドの戻り値も
 * ノードが挿入されたノード、すなわちメソッドの第1引数そのものとなる。</p>
 * <p>インスタンスは{@link Queries#insert(Query)}などを通じて得られる。</p>
 */
public class InsertQueryFactory {
	private final Query<NodeKind> newChildFunction;
	private final Node newChildNode;
	
	/**
	 * クエリを引数にとるコンストラクタ.
	 * @param newChild 挿入されるノードを返すクエリ
	 */
	InsertQueryFactory(final Query<NodeKind> newChild) {
		this.newChildFunction = newChild;
		this.newChildNode = null;
	}
	
	/**
	 * ノードを引数によるコンストラクタ.
	 * @param newChild 挿入されるノード
	 */
	InsertQueryFactory(final Node newChild) {
		this.newChildFunction = null;
		this.newChildNode = newChild;
	}
	
	/**
	 * 親ノードとなるXMLノードを引数にとり挿入されるべきノードを返す.
	 * @param parent 親ノードとなるXMLノード
	 * @return 挿入されるノード
	 */
	private Node node(final NodeKind parent) {
		if (newChildFunction == null) {
			return newChildNode;
		} else {
			return newChildFunction.queryFrom(parent).getWrappedNode();
		}
	}
	
	/**
	 * 子ノードの先頭に新しいノードを挿入するクエリを返す.
	 * @return クエリ
	 */
	public Query<NodeKind> first() {
		return new Query<NodeKind>() {
			@Override
			public NodeKind queryFrom(final NodeKind parent) {
				final Node parentNode = parent.getWrappedNode();
				parentNode.insertBefore(node(parent), parentNode.getFirstChild());
				return parent;
			}
		};
	}
	
	/**
	 * 子ノードの末尾に新しいノードを挿入するクエリを返す.
	 * @return クエリ
	 */
	public Query<NodeKind> last() {
		return new Query<NodeKind>() {
			@Override
			public NodeKind queryFrom(final NodeKind parent) {
				final Node parentNode = parent.getWrappedNode();
				parentNode.appendChild(node(parent));
				return parent;
			}
		};
	}
	
	/**
	 * 指定された位置に新しいノードを挿入するクエリを返す.
	 * @param position 位置
	 * @return クエリ
	 */
	public Query<NodeKind> at(final int position) {
		return new Query<NodeKind>() {
			@Override
			public NodeKind queryFrom(final NodeKind parent) {
				final Node parentNode = parent.getWrappedNode();
				Node curr = parentNode.getFirstChild();
				for (int i = 0; i < position; i ++) {
					curr = curr.getNextSibling();
				}
				parentNode.insertBefore(node(parent), curr);
				return parent;
			}
		};
	}
	
	/**
	 * 基準となるノードの前に新しいノードを挿入するクエリを返す.
	 * @param refChild 基準となるノード
	 * @return クエリ
	 */
	public Query<NodeKind> before(final Node refChild) {
		return new Query<NodeKind>() {
			@Override
			public NodeKind queryFrom(final NodeKind parent) {
				final Node parentNode = parent.getWrappedNode();
				parentNode.insertBefore(node(parent), refChild);
				return parent;
			}
		};
	}
	
	/**
	 * 基準となるノードの前に新しいノードを挿入するクエリを返す.
	 * @param refChild 基準となるノード
	 * @return クエリ
	 */
	public Query<NodeKind> before(final NodeKind refChild) {
		return before(refChild.getWrappedNode());
	}
	
	/**
	 * 基準となるノードの後に新しいノードを挿入するクエリを返す.
	 * @param refChild 基準となるノード
	 * @return クエリ
	 */
	public Query<NodeKind> after(final Node refChild) {
		return new Query<NodeKind>() {
			@Override
			public NodeKind queryFrom(final NodeKind parent) {
				final Node parentNode = parent.getWrappedNode();
				parentNode.insertBefore(node(parent), refChild.getNextSibling());
				return parent;
			}
		};
	}
	
	/**
	 * 基準となるノードの後に新しいノードを挿入するクエリを返す.
	 * @param refChild 基準となるノード
	 * @return クエリ
	 */
	public Query<NodeKind> after(final NodeKind refChild) {
		return after(refChild.getWrappedNode());
	}
}
