package org.unclazz.dom1;

import org.w3c.dom.Node;

public interface NodeWrapper<T extends Node> {
	T getWrappedNode();
}
