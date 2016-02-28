package org.unclazz.dom1;

import java.util.Collections;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;

public class AttributesQuery extends FunctionalListQuery<Attr, AttributeNode> {
	AttributesQuery() {}
	
	@Override
	protected Iterable<Attr> source(Nodal n) {
		final NamedNodeMap nm =  n.getWrappedNode().getAttributes();
		if (nm == null) {
			return Collections.emptyList();
		}
		
		return AttributeIterable.wrap(nm);
	}

	public Query<AttributeNode> equals(final String name) {
		return new Query<AttributeNode>() {
			@Override
			public AttributeNode queryFrom(Nodal n) {
				for (final AttributeNode a : AttributesQuery.this.queryFrom(n)) {
					if (name.equalsIgnoreCase(name)) {
						return a;
					}
				}
				return null;
			}
		};
	}

	public AttributesQuery specified(final boolean specified) {
		return new AttributesQuery() {
			@Override
			protected Function<Attr, AttributeNode> function() {
				return new Function<Attr, AttributeNode>() {
					@Override
					public AttributeNode apply(Attr target) {
						if (target.getSpecified() == specified) {
							return new DefaultAttributeNode(target);
						} else {
							return null;
						}
					}
				};
			}
		};
	}
	
	public ListQuery<AttributeNode> nameEqualsIgnoreCase(final String name) {
		return this.and(new Function<AttributeNode, AttributeNode>() {
			@Override
			public AttributeNode apply(AttributeNode target) {
				return target.getName().equals(name) ? target : null;
			}
		});
	}
	
	public ListQuery<AttributeNode> nameStartsWith(final String s) {
		return this.and(new Function<AttributeNode, AttributeNode>() {
			@Override
			public AttributeNode apply(AttributeNode target) {
				return target.getName().startsWith(s) ? target : null;
			}
		});
	}
	
	public ListQuery<AttributeNode> nameEndsWith(final String s) {
		return this.and(new Function<AttributeNode, AttributeNode>() {
			@Override
			public AttributeNode apply(AttributeNode target) {
				return target.getName().endsWith(s) ? target : null;
			}
		});
	}
	
	public ListQuery<AttributeNode> nameContains(final String s) {
		return this.and(new Function<AttributeNode, AttributeNode>() {
			@Override
			public AttributeNode apply(AttributeNode target) {
				return target.getName().contains(s) ? target : null;
			}
		});
	}
	
	@Override
	protected Function<Attr, AttributeNode> function() {
		return new Function<Attr, AttributeNode>() {
			@Override
			public AttributeNode apply(Attr target) {
				return new DefaultAttributeNode(target);
			}
		};
	}
}
