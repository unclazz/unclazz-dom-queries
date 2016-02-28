package org.unclazz.dom1;

import org.w3c.dom.Node;

public interface UZNode {
	NodeType getNodeType();
	<R> R query(Query<R> q);
	Node getWrappedNode();
}
