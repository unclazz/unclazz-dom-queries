package org.unclazz.dom.queries;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Node;

/**
 * XMLドキュメントの木構造からノードを取り除くクエリのファクトリ.
 * <p>このファクトリで生成されたクエリの{@link Query#queryFrom(NodeKind)}
 * メソッドの戻り値は、取り除かれたXMLノードである。</p>
 */
public class RemoveQueryFactory {
	RemoveQueryFactory() {}
	
	/**
	 * 問合せ対象のXMLノードそのものを木構造から取り除くクエリを返す.
	 * @return クエリ
	 */
	public Query<NodeKind> itSelf() {
		return new Query<NodeKind>() {
			@Override
			public NodeKind queryFrom(final NodeKind n) {
				final Node oldChild = n.getWrappedNode();
				final Node parent = oldChild.getParentNode();
				if (parent != null) {
					parent.removeChild(oldChild);
				}
				return n;
			}
		};
	}
	
	/**
	 * XMLノードの子ノードの中から指定されたノードを取り除くクエリを返す.
	 * @param oldChild 取り除かれるノード
	 * @return 取り除かれたノード
	 */
	public Query<NodeKind> child(final Node oldChild) {
		return new Query<NodeKind>() {
			@Override
			public NodeKind queryFrom(final NodeKind n) {
				final Node parent = n.getWrappedNode();
				return NodeKindUtils.wrapTreeStructure(parent.removeChild(oldChild));
			}
		};
	}
	
	/**
	 * XMLノードの子ノードの中から指定されたノードを取り除くクエリを返す.
	 * @param oldChild 取り除かれるノード
	 * @return 取り除かれたノード
	 */
	public Query<NodeKind> child(final NodeKind oldChild) {
		return child(oldChild.getWrappedNode());
	}
	
	/**
	 * XMLノードの子ノードの中から指定されたノードを取り除くクエリを返す.
	 * <p>{@link #child(NodeKind)}と異なるのは取り除く対象ノードそのものを指定するのではなく、
	 * 対象ノードを問合せるクエリを指定するという点。</p>
	 * @param q 取り除くノードを問合せるクエリ
	 * @return 取り除かれたノード
	 * @param <T> 取り除くノードの型（{@link NodeKind}もしくはそのサブ・インターフェース）
	 */
	public<T extends NodeKind> Query<NodeKind> child(final Query<T> q) {
		return new Query<NodeKind>() {
			@Override
			public NodeKind queryFrom(final NodeKind n) {
				final T oldChild = q.queryFrom(n);
				final Node parent = n.getWrappedNode();
				return NodeKindUtils.wrapTreeStructure(parent
						.removeChild(oldChild.getWrappedNode()));
			}
		};
	}
	
	/**
	 * XMLノードの子ノードの中から指定されたノードを取り除くクエリを返す.
	 * <p>{@link #child(Query)}と同様に取り除く対象のノードをクエリにより指定する。</p>
	 * @param q 取り除くノードのリストを問合せるクエリ
	 * @return 取り除かれたノードのリスト
	 * @param <T> 取り除くノードの型（{@link NodeKind}もしくはそのサブ・インターフェース）
	 */
	public<T extends NodeKind> ListQuery<NodeKind> all(final ListQuery<T> q) {
		return new ListQuery<NodeKind>() {
			@Override
			public List<NodeKind> queryFrom(final NodeKind n) {
				final List<T> oldChildren = q.queryFrom(n);
				if (oldChildren.isEmpty()) {
					return Collections.emptyList();
				}
				final Node parent = n.getWrappedNode();
				final LinkedList<NodeKind> result = new LinkedList<NodeKind>();
				for (final NodeKind oldChild : oldChildren) {
					result.add(NodeKindUtils.wrapTreeStructure(parent.
							removeChild(oldChild.getWrappedNode())));
				}
				return result;
			}
		};
	}
	
	/**
	 * XMLノードの子ノードをすべて取り除くクエリを返す.
	 * @return 取り除かれたノードのリスト
	 */
	public ListQuery<NodeKind> all() {
		return new ListQuery<NodeKind>() {
			@Override
			public List<NodeKind> queryFrom(NodeKind n) {
				if (n instanceof Branch) {
					final Node parent = n.getWrappedNode();
					final LinkedList<NodeKind> result = new LinkedList<NodeKind>();
					for (final Node oldChild : NodeIterable.wrap(n.getWrappedNode().getChildNodes())) {
						result.add(NodeKindUtils.wrapTreeStructure(parent.removeChild(oldChild)));
					}
					return result;
				} else {
					return Collections.emptyList();
				}
			}
		};
	}
}
