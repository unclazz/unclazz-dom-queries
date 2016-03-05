package org.unclazz.dom.queries;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;
import org.w3c.dom.Notation;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

/**
 * 木構造のリーフをあらわすインターフェース.
 * <p>DOMのAPIでは以下のインターフェースが該当する：</p>
 * <ul>
 * <li>{@link CDATASection}</li>
 * <li>{@link Comment}</li>
 * <li>{@link DocumentType}</li>
 * <li>{@link Notation}</li>
 * <li>{@link ProcessingInstruction}</li>
 * <li>{@link Text}</li>
 * </ul>
 */
public interface Leaf extends TreeStructure {
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
}