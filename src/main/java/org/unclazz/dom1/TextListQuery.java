package org.unclazz.dom1;

import java.util.List;

import org.w3c.dom.Node;

public class TextListQuery extends FunctionalListQuery<Node, TextNode> {
	private final FunctionalListQuery<Node, TextNode> tag;
	TextListQuery(final FunctionalListQuery<Node, TextNode> tag) {
		this.tag = tag;
	}

	@Override
	protected Iterable<Node> source(UZNode n) {
		return tag.source(n);
	}

	@Override
	protected Function<Node, TextNode> function() {
		return tag.function();
	}
	
	public Query<CharSequence> concat() {
		return concat(false);
	}
	
	public Query<CharSequence> concat(final boolean trim) {
		return new Query<CharSequence>() {
			@Override
			public CharSequence queryFrom(UZNode n) {
				final List<TextNode> l = TextListQuery.this.queryFrom(n);
				if (l.isEmpty()) {
					return "";
				}
				final StringBuilder buff = new StringBuilder();
				for (final TextNode t : l) {
					buff.append(trim ? t.getValue().trim() : t.getValue());
				}
				return buff;
			}
		};
	}
	
	public FunctionalListQuery<Node, TextNode> startsWith(final String s) {
		return this.and(new Function<TextNode, TextNode>() {
			@Override
			public TextNode apply(TextNode target) {
				return target.getValue().startsWith(s) ? target : null;
			}
		});
	}
	
	public FunctionalListQuery<Node, TextNode> endsWith(final String s) {
		return this.and(new Function<TextNode, TextNode>() {
			@Override
			public TextNode apply(TextNode target) {
				return target.getValue().endsWith(s) ? target : null;
			}
		});
	}
	
	public FunctionalListQuery<Node, TextNode> contains(final String s) {
		return this.and(new Function<TextNode, TextNode>() {
			@Override
			public TextNode apply(TextNode target) {
				return target.getValue().contains(s) ? target : null;
			}
		});
	}
}
