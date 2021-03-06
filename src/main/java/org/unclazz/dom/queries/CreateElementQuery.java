package org.unclazz.dom.queries;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * 新しい{@link Element}ノードを生成するクエリ.
 * <p>インスタンスは{@link CreateQueryFactory#element(String)}を通じて得られる。</p>
 */
public class CreateElementQuery implements Query<NodeKind> {
	private static Document document(Node node) {
		return node instanceof Document ? (Document) node : node.getOwnerDocument();
	}
	
	private final FunctionalQuery<Document, NodeKind> f;
	
	CreateElementQuery(final String tagName) {
		this.f = new FunctionalQuery<Document, NodeKind>() {
			@Override
			protected Document source(final NodeKind n) {
				return document(n.getWrappedNode());
			}
			@Override
			protected Function<Document, NodeKind> function() {
				return new Function<Document, NodeKind>() {
					@Override
					public NodeKind apply(final Document target) {
						return new DefaultElementNode(target.createElement(tagName));
					}
				};
			}
		};
	}

	CreateElementQuery(final FunctionalQuery<Document, NodeKind> f) {
		this.f = f;
	}

	/**
	 * 引数で与えられたXMLノードとオーナーを同じくする新しいXMLノードを生成して返す.
	 */
	@Override
	public NodeKind queryFrom(NodeKind n) {
		return f.queryFrom(n);
	}
	
	/**
	 * 子ノード追加の指定を加えた新しいクエリを返す.
	 * <p>{@code parentNode.query(create.element("child").append(newChild))}というように利用する。</p>
	 * @param newChild 子ノードとして追加されるノード
	 * @return クエリ
	 */
	public CreateElementQuery append(final Node newChild) {
		return new CreateElementQuery(this.f.and(new Function<NodeKind, NodeKind>() {
			@Override
			public NodeKind apply(final NodeKind target) {
				final Node n = target.getWrappedNode();
				n.appendChild(newChild);
				return target;
			}
		}));
	}
	
	/**
	 * 子ノード追加の指定を加えた新しいクエリを返す.
	 * <p>{@code parentNode.query(create.element("child").append(newChild))}というように利用する。</p>
	 * @param newChild 子ノードとして追加されるノード
	 * @return クエリ
	 */
	public CreateElementQuery append(final NodeKind newChild) {
		return new CreateElementQuery(this.f.and(new Function<NodeKind, NodeKind>() {
			@Override
			public NodeKind apply(final NodeKind target) {
				final Node n = target.getWrappedNode();
				n.appendChild(newChild.getWrappedNode());
				return target;
			}
		}));
	}
	
	/**
	 * 子ノード追加の指定を加えた新しいクエリを返す.
	 * <p>{@code parentNode.query(create.element("child").append(create.element("grandchild")))}というように利用する。</p>
	 * @param newChild 子ノードとして追加されるノードを返すクエリ
	 * @return クエリ
	 */
	public CreateElementQuery append(final CreateElementQuery newChild) {
		return new CreateElementQuery(this.f.and(new Function<NodeKind, NodeKind>() {
			@Override
			public NodeKind apply(final NodeKind target) {
				final Node n = target.getWrappedNode();
				n.appendChild(newChild.queryFrom(target).getWrappedNode());
				return target;
			}
		}));
	}
	
	/**
	 * 属性追加の指定を加えた新しいクエリを返す.
	 * @param name 属性名
	 * @param value 属性値
	 * @return クエリ
	 */
	public CreateElementQuery attribute(final String name, final String value) {
		return new CreateElementQuery(this.f.and(new Function<NodeKind, NodeKind>() {
			@Override
			public NodeKind apply(NodeKind target) {
				final Node n = target.getWrappedNode();
				if (n instanceof Element) {
					final Element e = (Element)n;
					e.setAttribute(name, value);
				}
				return target;
			}
		}));
	}
	
	/**
	 * id属性追加の指定を加えた新しいクエリを返す.
	 * @param value id値
	 * @return クエリ
	 */
	public CreateElementQuery id(final String value) {
		return attribute("id", value);
	}
	
	/**
	 * class属性追加の指定を加えた新しいクエリを返す.
	 * @param values class属性値
	 * @return クエリ
	 */
	public CreateElementQuery className(final String... values) {
		final StringBuilder buff = new StringBuilder();
		for (final String clazz : values) {
			if (buff.length() > 0) {
				buff.append(' ');
			}
			buff.append(clazz);
		}
		return attribute("class", buff.toString());
	}
	
	/**
	 * テキスト追加の指定を加えた新しいクエリを返す.
	 * @param data テキスト・データ
	 * @return クエリ
	 */
	public CreateElementQuery text(final CharSequence data) {
		return new CreateElementQuery(this.f.and(new Function<NodeKind, NodeKind>() {
			@Override
			public NodeKind apply(NodeKind target) {
				final Node n = target.getWrappedNode();
				if (n instanceof Element) {
					final Element e = (Element)n;
					final Text t = document(n).createTextNode(data.toString());
					e.appendChild(t);
				}
				return target;
			}
		}));
	}
}
