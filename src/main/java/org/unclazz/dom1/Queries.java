package org.unclazz.dom1;

public final class Queries {
	private Queries(){}
	public static final ChildrenQuery children = new ChildrenQuery();
	public static final DescendantsQuery descendents = new DescendantsQuery();
	public static final AncestorsQuery ancestors = new AncestorsQuery();
	public static final AttributesQuery attributes = new AttributesQuery();
}
