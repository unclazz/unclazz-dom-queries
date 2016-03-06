# Unclazz DOM Queriesについて

Unclazz DOM QueriesはDOM（Document Object Model）のAPIを拡張し使い勝手を向上させることを目的としたライブラリです。
DOMはXMLドキュメントの探索と変更をするための標準化された手段を提供してくれますが、
その使い勝手は悪く、とりわけJavaのような静的型付けの言語ではキャストを多用せざるを得ないことから、
概念的な重みとコーディング量の双方で開発者への負担の大きなAPIとなってしまっています。
このライブラリはそうした問題を解消/抑制するため、DOMのAPIを包み込み、代替する機能を提供するものです。

## 重要なオブジェクト

このライブラリの中核にあるのは`Query<R>`オブジェクトです。
このオブジェクトはXMLノードに対する何かしらのクエリ（問合せ）を表わし、
XMLノードから任意の型の情報を取得したりXMLノードに対して働きかけをしたりします。
ご察しの通りこのオブジェクトはJava 8で導入されたDate-Time APIの`TemporalQuery<R>`に着想を得たものです。

クエリは`queryFrom(NodeKind)`メソッドでXMLノードを受け取って、何かしらの処理を行った後で、
その結果となる任意の型のオブジェクトを返します。
クエリによりXMLノード木構造の中を探索したり属性値を取得したりできるほか、
新規ノードの作成やXMLドキュメントのファイルへの書き出しなどもできます。

ほかにも重要なオブジェクトがあります：

* `Queries`は定義済みのクエリやクエリのファクトリを提供するユーティリティ・クラスです。
* `DocumentNodes`はファイルや文字シーケンスからXMLドキュメントを読み取って`DocumentNode`インスタンスを生成するユーティリティ・クラスです。
* `NodeKind`はDOMの`Node`に対応するオブジェクトですがそのメンバーは大幅に切詰められています。ライブラリには`Document`や`Element`などに対応するオブジェクトも用意されていますが、いずれも可能な限りメンバーを削っています。これは概念的な重みを低減させるためです。
* `Function<A,B>`は関数をあらわすインターフェースで、XMLノードの探索と型変換とフィルタリングのために利用されます。定義済みクエリの多くはこの関数オブジェクトを内部的に利用しています。

## 使用方法

まず[リリース一覧](https://github.com/unclazz/unclazz-dom-queries/releases)からjarファイルを取得してプロジェクトのビルドパスに含めてください。もしあなたのプロジェクトがMavenを使用しているのであれば、DOM QueriesのアーティファクトはGithub上の[Mavenリポジトリ](https://github.com/unclazz/mvn-repo)から取得できます。そのための設定は`pom.xml`に以下のコード断片を追加するだけです：

```xml
<repositories>
	...
	<repository>
		<id>unclazz-mvn-repo</id>
		<url>https://raw.github.com/unclazz/mvn-repo/master/</url>
		<snapshots>
			<enabled>true</enabled>
			<updatePolicy>always</updatePolicy>
		</snapshots>
	</repository>
</repositories>
<dependencies>
	...
	<dependency>
		<groupId>org.unclazz.dom.queries</groupId>
		<artifactId>unclazz-dom-queries</artifactId>
		<version>1.0.0-RELEASE</version>
	</dependency>
<dependencies>
```

## XMLドキュメントの読み込み

例えば次のようなXMLファイルがあったとします：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<sample>
	<foo id="foo0" class="class0 class1" attr0="attr0-value" attr1="attr1-value"/>
	<foo id="foo1" attr1="attr1-value">
		<bar id="bar0" attr1="attr1-value" attr2="attr2-value"></bar>
		<bar id="bar1" class="class0 class2" attr0="attr0-value"> </bar>
		<![CDATA[
			here cdata section!
		]]>
		<!-- here comment! -->
		baz
	</foo>
</sample>

```

このファイルを読み込むには次のように`DocumentNodes`ユーティリティ・クラスを利用します：

```java
// ファイルからXMLドキュメントをパースする
final File xml = new File(SAMPLE_XML_PATH);
final DocumentNode dn = DocumentNodes.fromFile(xml);

// ドキュメントに含まれる要素（タグ）を列挙する
printLabel("DocumentNode.query(descendants.element())");
for (final NodeKind nk : dn.query(descendants.element())) {
	System.out.println(nk);
}
```

標準出力には次のように出力されます：

```
DocumentNode.query(descendants.element())
-----------------------------------------
ElementNode(<sample/>)
ElementNode(<foo/>)
ElementNode(<foo/>)
ElementNode(<bar/>)
ElementNode(<bar/>)
```

## XML木構造の探索

XMLドキュメントの木構造の中を探索するには`Queries`ユーティリティ・クラスが提供している各種の定義済みクエリ/ファクトリを利用します。
中でも`Queries.children`、`Queries.descendants`、`Queries.ancestors`、`Queries.prevs`/`nexts`は重要で、
クエリ対象のXMLノードを起点とした親戚関係ベースでのノード探索の機能を提供するものです。

例えば次のようなHTMLファイルがあったとします：

```html
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>sample html page</title>
	<script type="text/javascript"><![CDATA[
		// javascript code here
	]]></script>
</head>
<body>
	<!-- comment here -->
	<h1>Sample Html Page</h1>
	<p>1st paragraph ...</p>
	<p>2nd paragraph ...</p>
	<ul>
		<li><em>1st</em> list item</li>
		<li>2nd list item</li>
		<li>3rd list item</li>
	</ul>
</body>
</html>
```

このHTML（XML）ドキュメント内を探索するには次のようにします：

```java
// XML（HTML）ドキュメントをパースする
final File html = new File(SAMPLE_HTML_PATH);
final DocumentNode dn = DocumentNodes.fromFile(html);

// ドキュメントに直属する要素（＝<html/>）を取得
final ElementNode htmlTag = dn.getDocumentElement();

// html要素の子ノードのうちheadという名前の要素（<head/>）を1つだけ取得
final ElementNode headTag = htmlTag.query(children.element("head").one());

// head要素のあとに続くXMLノードのうち要素（タグ）だけ、しかもその直近の1つだけ（<body/>）を取得
final ElementNode bodyTag = headTag.query(nexts.element().one());

// body要素の子孫ノードのうちliという名前の要素（<li/>）をすべて取得
printLabel("ElementNode.query(descendants.element(\"li\")), then ElementNode.query(text)");
for (final ElementNode en : bodyTag.query(descendants.element("li"))) {
	// li要素の子孫ノードのテキストを一括（連結）して取得
	System.out.println(en.query(text));
}
```

標準出力には次のように出力されます：

```
ElementNode.query(descendants.element("li")), then ElementNode.query(text)
--------------------------------------------------------------------------
1st list item
2nd list item
3rd list item
```

## XMLドキュメントの変更

Unclazz DOM QueriesではXMLドキュメントの木構造への変更オペレーションもまたクエリとして実装されています。
ノードの新規作成には`Queries.create`を、またノードの木構造への挿入には`Queries.insert(...)`、ノードの削除には`Queries.remove`を利用できます：

```java
// ドキュメントの子孫ノードのうちからbody要素（＝<html/>）を取得
final ElementNode bodyTag = dn.query(descendants.element("body").one());

// html要素の子ノードの末尾に新規作成したp要素を追加
bodyTag.query(insert(create.element("p").className("new")
		.text("3rd paragraph...")).last());

// 追加した要素とその要素内のテキストを取得
final ElementNode pTag = bodyTag.query(children.element("p")).get(2);
System.out.println(pTag.query(children.text().concat()));
```

標準出力には次のように出力されます：

```
3rd paragraph...
```

## XMLドキュメントの直列化

最後に、Unclazz DOM QueriesではXMLドキュメントの全体や一部分を文字シーケンス化したり、ファイルに書きだしたりするのにもクエリを利用します：

```
// XMLドキュメントをパースする
final File xml = new File(SAMPLE_XML_PATH);
final DocumentNode dn = DocumentNodes.fromFile(xml);

// <foo/>の1つめを取得
final ElementNode foo0 = dn.query(descendants.element("foo").one());

// 要素（タグ）を文字シーケンスとして書き出す
printLabel("ElementNode.query(writeTo.charSequence())");;
System.out.println(foo0.query(writeTo.charSequence()));

// ドキュメント全体をストリームに書き出す
printLabel("DocumentNode.query(writeTo.stream(System.out))");;
dn.query(writeTo.stream(System.out));
```

標準出力には次のように出力されます：

```
ElementNode.query(writeTo.charSequence())
-----------------------------------------
<foo attr0="attr0-value" attr1="attr1-value" class="class0 class1" id="foo0"/>

DocumentNode.query(writeTo.stream(System.out))
----------------------------------------------
<?xml version="1.0" encoding="UTF-8" standalone="no"?><sample>
	<foo attr0="attr0-value" attr1="attr1-value" class="class0 class1" id="foo0"/>
	<foo attr1="attr1-value" id="foo1">
		<bar attr1="attr1-value" attr2="attr2-value" id="bar0"/>
		<bar attr0="attr0-value" class="class0 class2" id="bar1"> </bar>
		<![CDATA[
			here cdata section!
		]]>
		<!-- here comment! -->
		baz
	</foo>
</sample>
```





