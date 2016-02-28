package org.unclazz.dom1;

import org.w3c.dom.Node;

public class TagListQuery extends FunctionalListQuery<Node, ElementNode> {
	private final FunctionalListQuery<Node, ElementNode> tag;
	TagListQuery(final FunctionalListQuery<Node, ElementNode> tag) {
		this.tag = tag;
	}

	@Override
	protected Iterable<Node> source(Nodal n) {
		return tag.source(n);
	}

	@Override
	protected Function<Node, ElementNode> function() {
		return tag.function();
	}
	
	public FunctionalListQuery<Node, ElementNode> hasChildren() {
		return this.and(new Function<ElementNode, ElementNode>() {
			@Override
			public ElementNode apply(ElementNode target) {
				return target.hasChildNodes() ? target : null;
			}
		});
	}
	
	public FunctionalListQuery<Node, ElementNode> hasAttribute(final String name) {
		return this.and(new Function<ElementNode, ElementNode>() {
			@Override
			public ElementNode apply(ElementNode target) {
				return target.hasAttribute(name) ? target : null;
			}
		});
	}
}
