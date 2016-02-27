package org.unclazz.dom1.utils;

public final class Filters {
	private Filters() {}
	
	public static<T> CompositeFilter<T> noop() {
		return new CompositeFilter<T>(new Filter<T>() {
			@Override
			public boolean test(T target) {
				return true;
			}
		});
	}
	
	public static<T> CompositeFilter<T> tag() {
		return new CompositeFilter<T>(new Filter<T>() {
			@Override
			public boolean test(T target) {
				return target instanceof ElementNode;
			}
		});
	}
	
	public static CompositeFilter<TreeStructuredNode> tag(final String name) {
		if (name.equals("*")) {
			return tag();
		}
		return new CompositeFilter<TreeStructuredNode>(new Filter<TreeStructuredNode>() {
			@Override
			public boolean test(TreeStructuredNode target) {
				if (target instanceof ElementNode) {
					final ElementNode e = (ElementNode) target;
					return e.getTagName().equalsIgnoreCase(name);
				} else {
					return false;
				}
			}
		});
	}
	
	public static CompositeFilter<TreeStructuredNode> hasCildren() {
		return new CompositeFilter<TreeStructuredNode>(new Filter<TreeStructuredNode>() {
			@Override
			public boolean test(TreeStructuredNode target) {
				return target instanceof BranchNode && ((BranchNode) target).hasChildNodes();
			}
		});
	}
	
	public static CompositeFilter<TreeStructuredNode> hasAttribute(final String name) {
		return new CompositeFilter<TreeStructuredNode>(new Filter<TreeStructuredNode>() {
			@Override
			public boolean test(TreeStructuredNode target) {
				if (target instanceof ElementNode) {
					final ElementNode e = (ElementNode) target;
					return e.hasAttribute(name);
				} else {
					return false;
				}
			}
		});
	}
	
	public static CompositeFilter<TreeStructuredNode> hasAttribute(final Filter<String> f) {
		return new CompositeFilter<TreeStructuredNode>(new Filter<TreeStructuredNode>() {
			@Override
			public boolean test(TreeStructuredNode target) {
				if (target instanceof ElementNode) {
					final ElementNode e = (ElementNode) target;
					for (final String k : e.getAttributes().keySet()) {
						if (f.test(k)) {
							return true;
						}
					}
				}
				return false;
			}
		});
	}
	
	public static CompositeFilter<String> equals(final String s) {
		return new CompositeFilter<String>(new Filter<String>() {
			@Override
			public boolean test(String target) {
				return target.equals(s);
			}
		});
	}
	
	public static CompositeFilter<String> startsWith(final String s) {
		return new CompositeFilter<String>(new Filter<String>() {
			@Override
			public boolean test(String target) {
				return target.startsWith(s);
			}
		});
	}
	
	public static CompositeFilter<String> endsWith(final String s) {
		return new CompositeFilter<String>(new Filter<String>() {
			@Override
			public boolean test(String target) {
				return target.endsWith(s);
			}
		});
	}
	
	public static CompositeFilter<String> contains(final String s) {
		return new CompositeFilter<String>(new Filter<String>() {
			@Override
			public boolean test(String target) {
				return target.contains(s);
			}
		});
	}
}
