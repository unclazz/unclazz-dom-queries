package org.unclazz.dom1;

import java.util.List;

public interface ListQuery<R> extends Query<List<R>> {
	List<R> queryFrom(Nodal n);
}
