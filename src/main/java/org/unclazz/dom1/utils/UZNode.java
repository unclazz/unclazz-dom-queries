package org.unclazz.dom1.utils;

public interface UZNode {
	NodeType getNodeType();
	<R> R query(Query<R> q);
}
