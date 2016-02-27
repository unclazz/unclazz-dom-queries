package org.unclazz.dom1.utils;

import org.w3c.dom.Node;

public interface NodeWrapper<T extends Node> {
	T getWrappedNode();
}
