package org.unclazz.dom.queries;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ClassesQuery implements ListQuery<String> {
	ClassesQuery() {}
	
	@Override
	public List<String> queryFrom(NodeKind n) {
		if (n instanceof ElementNode) {
			final String classValue = ((ElementNode) n).getAttribute("class");
			if (classValue == null) {
				return Collections.emptyList();
			}
			return Arrays.asList(classValue.trim().split("\\s+"));
		}
		return Collections.emptyList();
	}
}
