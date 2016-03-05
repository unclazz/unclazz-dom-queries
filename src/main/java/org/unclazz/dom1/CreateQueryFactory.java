package org.unclazz.dom1;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * 新しいXMLノードを生成するクエリのファクトリ.
 * <p>クエリの{@link Query#queryFrom(NodeKind)}メソッドの
 * 第1引数のノードのオーナー・ドキュメントが新規に生成されるノードのオーナー・ドキュメントとなる。
 * またとくに記載のない限り同メソッドの戻り値はその第1引数と同じノードとなる。</p>
 * <p>インスタンスは{@link Queries#create}を通じて得られる。</p>
 */
public class CreateQueryFactory {
	private static Document document(NodeKind node) {
		final Node wrapped = node.getWrappedNode();
		return wrapped instanceof Document ? (Document) wrapped : wrapped.getOwnerDocument();
	}
	
	CreateQueryFactory() {}
	
	/**
	 * {@link Element}を生成するクエリを返す.
	 * @param tagName タグ名
	 * @return クエリ
	 */
	public CreateElementQuery element(final String tagName) {
		return new CreateElementQuery(tagName);
	}
	
	/**
	 * {@link Text}を生成するクエリを返す.
	 * @param data データ
	 * @return クエリ
	 */
	public Query<NodeKind> text(final CharSequence data) {
		return new Query<NodeKind>() {
			@Override
			public NodeKind queryFrom(NodeKind n) {
				return new DefaultTextNode(document(n).
						createTextNode(data.toString()));
			}
		};
	}
	
	/**
	 * {@link CDATASection}を生成するクエリを返す.
	 * @param data データ
	 * @return クエリ
	 */
	public Query<NodeKind> cdata(final CharSequence data) {
		return new Query<NodeKind>() {
			@Override
			public NodeKind queryFrom(NodeKind n) {
				return new DefaultCDATASectionNode(document(n).
						createCDATASection(data.toString()));
			}
		};
	}
	
	/**
	 * {@link Comment}を生成するクエリを返す.
	 * @param data データ
	 * @return クエリ
	 */
	public Query<NodeKind> comment(final CharSequence data) {
		return new Query<NodeKind>() {
			@Override
			public NodeKind queryFrom(NodeKind n) {
				return new DefaultCommentNode(document(n).
						createComment(data.toString()));
			}
		};
	}
}
