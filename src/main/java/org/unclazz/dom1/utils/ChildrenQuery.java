package org.unclazz.dom1.utils;

import java.util.Collections;
import java.util.List;

import javax.lang.model.element.Element;

class ChildrenQuery implements ListQuery<TreeStructuredNode> {
	ChildrenQuery() {}

	@Override
	public List<TreeStructuredNode> queryFrom(final UZNode n) {
		if (n instanceof BranchNode) {
			return ((BranchNode) n).getChildNodes();
		} else {
			return Collections.emptyList();
		}
	}
	
	public ListQuery<ElementNode> tag() {
		return new TypedFilterListQuery<ElementNode>(this,
				TypeHandlers.ELEMENT, Filters.<ElementNode>noop());
	}
}
