package org.unclazz.dom1;

import java.util.Collections;
import java.util.List;

class DescendantsQuery implements ListQuery<TreeStructuredNode> {
	DescendantsQuery() {}

	@Override
	public List<TreeStructuredNode> queryFrom(final UZNode n) {
		if (n instanceof BranchNode) {
			return ((BranchNode) n).getChildNodes();
		} else {
			return Collections.emptyList();
		}
	}
}
