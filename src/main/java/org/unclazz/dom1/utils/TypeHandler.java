package org.unclazz.dom1.utils;

interface TypeHandler<T> {
	T cast(Object node);
}
