package org.unclazz.dom1;

import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;

/**
 * 木構造のブランチをあらわすインターフェース.
 * <p>DOMのAPIでは以下のインターフェースが該当する：</p>
 * <ul>
 * <li>{@link Attr}</li>
 * <li>{@link DocumentFragment}</li>
 * <li>{@link Element}</li>
 * <li>{@link Entity}</li>
 * <li>{@link EntityReference}</li>
 * </ul>
 */
public interface Branch extends TreeStructure {
	/**
	 * {@link Node#getParentNode()}を呼び出す.
	 * @return {@code Node}をラップしたオブジェクト
	 */
	TreeStructure getParentNode();
	/**
	 * {@link Node#getPreviousSibling()}を呼び出す.
	 * @return {@code Node}をラップしたオブジェクト
	 */
	TreeStructure getPreviousSibling();
	/**
	 * {@link Node#getNextSibling()}を呼び出す.
	 * @return {@code Node}をラップしたオブジェクト
	 */
	TreeStructure getNextSibling();
	/**
	 * このノードが親ノードを持つかどうかを判定する.
	 * @return 該当ノードを持つ場合{@code true}
	 */
	boolean hasParentNode();
	/**
	 * このノードが前のノードを持つかどうかを判定する.
	 * @return 該当ノードを持つ場合{@code true}
	 */
	boolean hasPreviousSibling();
	/**
	 * このノードが次のノードを持つかどうかを判定する.
	 * @return 該当ノードを持つ場合{@code true}
	 */
	boolean hasNextSibling();
	/**
	 * このノードが子ノードを持つかどうかを判定する.
	 * @return 該当ノードを持つ場合{@code true}
	 */
	boolean hasChildNodes();
	/**
	 * {@link Node#getChildNodes()}を呼び出す.
	 * @return {@code Node}をラップしたオブジェクトのリスト
	 */
	List<TreeStructure> getChildNodes();
	/**
	 * {@link Node#getFirstChild()}を呼び出す.
	 * @return {@code Node}をラップしたオブジェクト
	 */
	TreeStructure getFirstChild();
	/**
	 * {@link Node#getLastChild()}を呼び出す.
	 * @return {@code Node}をラップしたオブジェクト
	 */
	TreeStructure getLastChild();
}