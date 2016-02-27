package org.unclazz.dom1.utils;

import java.util.List;

public interface ListQuery<R> extends Query<List<R>> {
	List<R> queryFrom(UZNode n);
}
