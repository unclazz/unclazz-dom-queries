package org.unclazz.dom1;

import org.w3c.dom.Comment;

/**
 * {@link Comment}のためのラッパー.
 */
public interface CommentNode extends NodeKind {
	/**
	 * {@link Comment#getNodeValue()}を呼び出す.
	 * @return コメントの内容
	 */
	String getValue();
}
