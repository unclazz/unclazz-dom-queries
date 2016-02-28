package org.unclazz.dom1;

interface TypeHandler<T> {
	T cast(Object node);
}
