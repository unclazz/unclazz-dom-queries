package org.unclazz.dom1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class IdQuery implements Query<ElementNode> {
	private final String id;
	IdQuery(final String id) {
		this.id = id;
	}

	@Override
	public ElementNode queryFrom(NodeKind n) {
		final Node node = n.getWrappedNode();
		final NodeIterable iter;
		if (node instanceof Element) {
			iter = NodeIterable.wrap(((Element) node).getElementsByTagName("*"));
		} else if (node instanceof Document) {
			iter = NodeIterable.wrap(((Document) node).getElementsByTagName("*"));
		} else {
			return null;
		}
		for (final Node tag : iter) {
			final Element e = (Element) tag;
			if (e.getAttribute("id").equals(id)) {
				return new DefaultElementNode(e);
			}
		}
		return null;
	}
}
