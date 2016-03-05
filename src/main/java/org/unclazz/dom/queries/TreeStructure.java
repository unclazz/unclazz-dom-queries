package org.unclazz.dom.queries;

/**
 * XMLノードの木構造をあらわすインターフェース.
 */
public interface TreeStructure extends NodeKind {
	/**
	 * レシーバ・オブジェクトが{@link Root}のインスタンスかどうか判定する.
	 * @return 該当する場合{@code true}
	 */
	boolean isRoot();
	/**
	 * レシーバ・オブジェクトが{@link Branch}のインスタンスかどうか判定する.
	 * @return 該当する場合{@code true}
	 */
	boolean isBranch();
	/**
	 * レシーバ・オブジェクトが{@link Leaf}のインスタンスかどうか判定する.
	 * @return 該当する場合{@code true}
	 */
	boolean isLeaf();
}
