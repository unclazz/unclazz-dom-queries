package org.unclazz.dom1;

import org.w3c.dom.Comment;

/**
 * {@link Comment}を内包するオブジェクト.
 */
public interface CommentNode extends TreeStructure.Leaf, NodeWrapper<Comment> {
	/**
	 * {@link Comment#getNodeValue()}を呼び出す.
	 * @return コメントの内容
	 */
	String getValue();
}
