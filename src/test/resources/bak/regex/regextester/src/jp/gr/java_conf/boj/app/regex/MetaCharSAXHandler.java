package jp.gr.java_conf.boj.app.regex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

class MetaCharSAXHandler implements ContentHandler,
                                           ErrorHandler {

	private List<MetaChar> metaCharList;
	
	private String chars;
	private String summary;
	private String example;
	
	private StringBuilder buffer;
	private boolean enabledCharacters;
	
	List<MetaChar> getMetaCharList() {
		return metaCharList;
	}
	
	/**
	 * 引数の InputSource オブジェクトが指す XML 文書のパースを行う。<br>
	 * スキーマや DTD による検証は行わない。<br>
	 * パーサはネームスペースを意識した処理を行う。<br>
	 * パース時に発生する可能性のある IOException や SAXException は
	 * 呼び出しもとで処理する。
	 * 
	 * @param inputSource XMLを読み込む為のInputSourceオブジェクト。
	 * @exception IllegalArgumentException inputSourceがnullの場合
	 * @exception ParserConfigurationException SAXParserFactoryが
	 *            SAXParserを生成できない場合
	 * @exception IOException  XMLReader#parseがスローする可能性あり
	 * @exception SAXException XMLReader#parseがスローする可能性あり
	 */
	void parse(InputSource inputSource)
	           throws IOException, SAXException,
	                  ParserConfigurationException {
		parse(inputSource, true);
	}

	/**
	 * 引数の InputSource オブジェクトが指す XML 文書のパースを行う。<br>
	 * スキーマや DTD による検証は行わない。<br>
	 * ネームスペースを意識しない場合には引数の boolean 値に
	 * false を指定する。<br>
	 * パース時に発生する可能性のある IOException や SAXException は
	 * 呼び出しもとで処理する。
	 * 
	 * @param inputSource XMLを読み込む為のInputSourceオブジェクト。
	 * @param namespaceAwareness ネームスペースを考慮する場合には
	 *                           trueを指定する。
	 * @exception IllegalArgumentException inputSourceがnullの場合
	 * @exception ParserConfigurationException SAXParserFactoryが
	 *            SAXParserを生成できない場合
	 * @exception IOException  XMLReader#parseがスローする可能性あり
	 * @exception SAXException XMLReader#parseがスローする可能性あり
	 */
	void parse(InputSource inputSource,
	           boolean namespaceAwareness)
	           throws IOException, SAXException,
	                  ParserConfigurationException {
		if (inputSource == null) {
			throw new IllegalArgumentException("inputSource is null !");
		}
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(namespaceAwareness);
		SAXParser parser = factory.newSAXParser();
		XMLReader reader = parser.getXMLReader();
		reader.setContentHandler(this);
		reader.setErrorHandler(this);
		reader.parse(inputSource);
	}


	//////////////////////////////////////////////
	// このメソッドはJDK1.5以降でコンパイル可能 //
	//////////////////////////////////////////////
	/**
	 * 引数の InputSource オブジェクトが指す XML 文書と
	 * Schema オブジェクトが表すスキーマでパースを行う。<br>
	 * スキーマによる検証を行わない場合には引数の Schema オブジェクトは
	 * null でよい。<br>
	 * パーサはネームスペースを意識した処理を行う。<br>
	 * パース時に発生する可能性のある IOException や SAXException は
	 * 呼び出しもとで処理する。
	 * 
	 * @param inputSource XMLを読み込む為のInputSourceオブジェクト。
	 * @param schema スキーマを表すSchemaオブジェクト。
	 *               nullの場合にはスキーマによる検証を行わない。
	 * @exception IllegalArgumentException inputSourceがnullの場合
	 * @exception ParserConfigurationException SAXParserFactoryが
	 *            SAXParserを生成できない場合
	 * @exception IOException  XMLReader#parseがスローする可能性あり
	 * @exception SAXException XMLReader#parseがスローする可能性あり
	 */
	void parse(InputSource inputSource,
	           Schema schema)
	           throws IOException, SAXException,
	                  ParserConfigurationException {
		parse(inputSource, schema, true);
	}


	//////////////////////////////////////////////
	// このメソッドはJDK1.5以降でコンパイル可能 //
	//////////////////////////////////////////////
	/**
	 * 引数の InputSource オブジェクトが指す XML 文書と
	 * Schema オブジェクトが表すスキーマでパースを行う。<br>
	 * スキーマによる検証を行わない場合には引数の Schema オブジェクトは
	 * null でよい。<br>
	 * ネームスペースを意識しない場合には引数の boolean 値に
	 * false を指定する。<br>
	 * パース時に発生する可能性のある IOException や SAXException は
	 * 呼び出しもとで処理する。
	 * 
	 * @param inputSource XMLを読み込む為のInputSourceオブジェクト。
	 * @param schema      スキーマを表すSchemaオブジェクト。
	 *                    nullの場合にはスキーマによる検証を行わない。
	 * @param namespaceAwareness ネームスペースを考慮する場合には
	 *                           trueを指定する。
	 * @exception IllegalArgumentException inputSourceがnullの場合
	 * @exception ParserConfigurationException SAXParserFactoryが
	 *            SAXParserを生成できない場合
	 * @exception IOException  XMLReader#parseがスローする可能性あり
	 * @exception SAXException XMLReader#parseがスローする可能性あり
	 */
	void parse(InputSource inputSource,
	           Schema schema,
	           boolean namespaceAwareness)
	           throws IOException, SAXException,
	                  ParserConfigurationException {
		if (inputSource == null) {
			throw new IllegalArgumentException("inputSource is null !");
		}
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setSchema(schema);
		factory.setNamespaceAware(namespaceAwareness);
		SAXParser parser = factory.newSAXParser();
		XMLReader reader = parser.getXMLReader();
		reader.setContentHandler(this);
		reader.setErrorHandler(this);
		reader.parse(inputSource);
	}

	/**
	 * ContentHandler#startElement の実装<br>
	 * XML 文書における要素の開始を通知する。<br>
	 * 
	 * @param uri 要素の名前空間、あるいは空の文字列
	 * @param localName プレフィックスのない要素名、あるいは空の文字列
	 * @param qName XML文書で要素にプレフィックスが
	 *              付いていればプレフィックス付きのそうでなければ
	 *              プレフィックス無しの要素名
	 * @param atts 要素内の属性をすべて含む Attributes オブジェクト
	 * @exception SAXException SAXExceptionを生成してスローする事で
	 *                         パース処理を終了させることができる。
	 */
	public void startElement(String uri,
	                         String localName,
	                         String qName,
	                         Attributes atts)
	                         throws SAXException {
		// Sun の jdk1.5 における動作では
		// SAXParserFactory factory = SAXParserFactory.newInstance();
		// SAXParser parser = factory.newSAXParser();
		// XMLReader reader = parser.getXMLReader();
		// このように作成された XMLReader の場合には
		// このメソッドの引数の uri と localName は設定されず
		// (空の文字列に設定される)、 qName は常に設定される。
		// ただし uri と localName は実装によっては
		// 設定されているかもしれない。
		//
		// SAXParserFactory factory = SAXParserFactory.newInstance();
		// factory.setNamespaceAware(true);
		// SAXParser parser = factory.newSAXParser();
		// XMLReader reader = parser.getXMLReader();
		// このように作成された XMLReader の場合には
		// uri, localName, qName すべてが設定されている。
		// よく理解していないがこの場合の qName は実装によっては
		// 設定されていないかもしれない...(そうではないかもしれない)
		// 名前空間を意識したプログラムでは uri と localName の
		// セットで要素を区別すれば間違いないと思われる。

		if (localName.equals("metachars")) {
			startElementMetachars(uri, localName, qName, atts);
		} else if (localName.equals("metachar")) {
			startElementMetachar(uri, localName, qName, atts);
		} else if (localName.equals("chars")) {
			startElementChars(uri, localName, qName, atts);
		} else if (localName.equals("summary")) {
			startElementSummary(uri, localName, qName, atts);
		} else if (localName.equals("example")) {
			startElementExample(uri, localName, qName, atts);
		}
	}

	/**
	 * ContentHandler#endElement の実装<br>
	 * XML 文書における要素の終了を通知する。<br>
	 * 
	 * @param uri 要素の名前空間、あるいは空の文字列
	 * @param localName プレフィックスのない要素名、あるいは空の文字列
	 * @param qName XML文書で要素にプレフィックスが
	 *              付いていればプレフィックス付きのそうでなければ
	 *              プレフィックス無しの要素名
	 * @param atts 要素内の属性をすべて含むAttributesオブジェクト
	 * @exception SAXException SAXExceptionを生成してスローする事で
	 *                         パース処理を終了させることができる。
	 */
	public void endElement(String uri,
	                       String localName,
	                       String qName)
	                       throws SAXException {
		if (localName.equals("metachars")) {
			endElementMetachars(uri, localName, qName);
		} else if (localName.equals("metachar")) {
			endElementMetachar(uri, localName, qName);
		} else if (localName.equals("chars")) {
			endElementChars(uri, localName, qName);
		} else if (localName.equals("summary")) {
			endElementSummary(uri, localName, qName);
		} else if (localName.equals("example")) {
			endElementExample(uri, localName, qName);
		}
	}
	
	private void initBuffer() {
		buffer.setLength(0);
		enabledCharacters = true;
	}

	// metachars要素の出現
	private void startElementMetachars(String uri, String localName,
	                                   String qName, Attributes atts)
	                                   throws SAXException {
		buffer = new StringBuilder();
		metaCharList = new ArrayList<MetaChar>();
	}

	// metachars要素の終了
	private void endElementMetachars(String uri, String localName,
	                                 String qName)
	                                 throws SAXException {
		
	}

	// metachar要素の出現
	private void startElementMetachar(String uri, String localName,
	                                  String qName, Attributes atts)
	                                  throws SAXException {
		chars = null;
		summary = null;
		example = null;
	}

	// metachar要素の終了
	private void endElementMetachar(String uri, String localName,
	                                String qName)
	                                throws SAXException {
		metaCharList.add(new MetaChar(chars, summary, example));
	}

	// chars要素の出現
	private void startElementChars(String uri, String localName,
	                               String qName, Attributes atts)
	                               throws SAXException {
		initBuffer();
	}

	// chars要素の終了
	private void endElementChars(String uri, String localName,
	                             String qName)
	                             throws SAXException {
		chars = buffer.toString();
	}

	// summary要素の出現
	private void startElementSummary(String uri, String localName,
	                                 String qName, Attributes atts)
	                                 throws SAXException {
		initBuffer();
	}

	// summary要素の終了
	private void endElementSummary(String uri, String localName,
	                               String qName)
	                               throws SAXException {
		summary = buffer.toString();
	}

	// example要素の出現
	private void startElementExample(String uri, String localName,
	                                 String qName, Attributes atts)
	                                 throws SAXException {
		initBuffer();
	}

	// example要素の終了
	private void endElementExample(String uri, String localName,
	                               String qName)
	                               throws SAXException {
		example = buffer.toString();
	}



	/**
	 * ContentHandler#characters の実装<br>
	 * 
	 * @param ch 文字データを含むchar[]
	 * @param start 文字データの ch に置ける開始インデックス
	 * @param length 文字データの文字数
	 * @exception SAXException SAXExceptionを生成してスローする事で
	 *                         パース処理を終了させることができる。
	 */
	public void characters(char[] ch,
	                       int start,
	                       int length)
	                       throws SAXException {
		
		if (enabledCharacters) {
			appendTrimedStr(buffer, ch, start, length);
		}
		// 文字データの通知を受ける。
		// 引数から String オブジェクトを生成する場合には
		// new String(ch, start, length)
		// となる。
		// StringBuffer オブジェクトに加える場合は
		// stringBuffer.append(ch, start, length)
		// となる。
		
	}

	/**
	 * ContentHandler#setDocumentLocator の実装<br>
	 * 
	 * @param locator Locatorオブジェクト
	 */
	public void setDocumentLocator(Locator locator) {
		// パース処理の最中に現在の XML 文書内の位置を
		// 特定するための Locator オブジェクトを受け取る。
		//     private Locator locator;
		//     public void setDocumentLocator(Locator locator) {
		//         this.locator = locator;
		//     }
		// このようにフィールドとして保持しておくことで
		// ContentHandler の他のメソッドが呼び出された際に
		// その時点で解析しているXML文書の内の位置を locator の
		// getLineNumber getColumnNumber メソッドで取得できる。
		// ContentHandler のメソッドの中で一番先に呼び出される。

	}

	/**
	 * ContentHandler#startDocument の実装<br>
	 * 
	 * @exception SAXException SAXExceptionを生成してスローする事で
	 *                         パース処理を終了させることができる。
	 */
	public void startDocument() throws SAXException {
		// XML 文書の開始の通知を受ける。
		// ContentHandler のメソッドの中で setDocumentLocator メソッドの
		// 次に呼び出される。
	}

	/**
	 * ContentHandler#endDocument の実装<br>
	 * 
	 * @exception SAXException SAXExceptionをスローする事ができる。
	 */
	public void endDocument() throws SAXException {
		// XML 文書の終了の通知を受ける。
		// jdk1.5 のドキュメントには以下のような記述がある。
		// 「このメソッドのマニュアルと ContentHandler.fatalError() の
		// マニュアルとの間には明らかに矛盾があります。
		// クライアントは、今後のメジャーリリースでこのあいまいさが
		// 解決されないかぎり、パーサが fatalError() を報告したり例外を
		// スローしたときに endDocument() が呼び出されるかどうかを
		// 仮定しないようにする必要があります。」
		
	}

	/**
	 * ContentHandler#startPrefixMapping の実装<br>
	 * 
	 * @param prefix 名前空間をマッピングするプレフィックス名
	 * @param uri 定義した名前空間
	 * @exception SAXException SAXExceptionを生成してスローする事で
	 *                         パース処理を終了させることができる。
	 */
	public void startPrefixMapping(String prefix,
	                               String uri)
	                               throws SAXException {
		// 名前空間が定義された事を通知する。
		// 名前空間を定義した要素の startElement メソッドと
		// このメソッドのどちらが先に呼び出されるかは定義されていない。
		// デフォルトの名前空間を定義してプレフィックスを
		// 定義していない場合には prefix は空の文字列となる。
		//     SAXParserFactory factory = SAXParserFactory.newInstance();
		//     factory.setNamespaceAware(true);
		// としてパーサが名前空間を意識している場合にこの通知を
		// 受ける事ができる。
		// パーサが名前空間を意識しない設定の場合にはこのメソッドは
		// 呼び出されない。
		// 
		// <cafe:hoge xmlns:cafe="http://hoge.huga.com/cafe">
		//     <text/>
		// </cafe:hoge>
		// この場合には cafe:hoge 要素に対する startElement メソッドの
		// 前後どちらかで prefix に cafe を uri に 
		// http://hoge.huga.com/cafe を引数に設定してこのメソッドが
		// 呼び出される。
		// 
		// <hoge xmlns="http://hoge.huga.com/cafe">
		//     <text/>
		// </hoge>
		// この場合には hoge 要素に対する startElement メソッドの
		// 前後どちらかで prefix に空の文字列を uri に
		// http://hoge.huga.com/cafe を引数に設定してこのメソッドが
		// 呼び出される。
		
	}

	/**
	 * ContentHandler#endPrefixMapping の実装<br>
	 * 
	 * @param prefix プレフィックス名
	 * @exception SAXException SAXExceptionを生成してスローする事で
	 *                         パース処理を終了させることができる。
	 */
	public void endPrefixMapping(String prefix) throws SAXException {
		// 名前空間の有効範囲の終了を通知する。
		// 名前空間を定義した要素の endElement メソッドと
		// このメソッドのどちらが先に呼び出されるかは定義されていない。
		// デフォルトの名前空間を定義してプレフィックスを
		// 定義していない場合には prefix は空の文字列となる。
		//     SAXParserFactory factory = SAXParserFactory.newInstance();
		//     factory.setNamespaceAware(true);
		// としてパーサが名前空間を意識している場合にこの通知を
		// 受ける事ができる。
		// パーサが名前空間を意識しない設定の場合にはこのメソッドは
		// 呼び出されない。
		// 
		// <cafe:hoge xmlns:cafe="http://hoge.huga.com/cafe">
		//     <text/>
		// </cafe:hoge> ←ここで呼び出される
		// この場合には cafe:hoge 要素に対する endElement メソッドの
		// 前後どちらかで prefix に cafe を引数に設定して
		// このメソッドが呼び出される。
		// 
		// <hoge xmlns="http://hoge.huga.com/cafe">
		//     <text/>
		// </hoge> ←ここで呼び出される
		// この場合には hoge 要素に対する endElement メソッドの
		// 前後どちらかで prefix に空の文字列を引数に設定して
		// このメソッドが呼び出される。
			
	}

	/**
	 * ContentHandler#ignorableWhitespace の実装<br>
	 * 
	 * @param ch 削除された空白類を含むchar[]
	 * @param start 削除された空白類の ch に置ける開始インデックス
	 * @param length 削除された空白類の文字数
	 * @exception SAXException SAXExceptionを生成してスローする事で
	 *                         パース処理を終了させることができる。
	 */
	public void ignorableWhitespace(char[] ch,
	                                int start,
	                                int length)
	                                throws SAXException {
		// パーサが無視できる空白として削除し、charactersメソッドの
		// 引数には含まれなかったスペースや改行などが通知される。
		// 通常はスキーマを基にバリデーションを行っている場合に
		// スキーマの定義をもとにパーサが無視できる空白を判断して
		// それらを削除した場合に、その削除した空白類を通知する。
		// よってバリデーションを行っていない場合には無視できる空白の
		// 削除は行われず、すべてが characters メソッドに通知され
		// このメソッドが呼び出される事も無い、という場合が多い。
		// 仕様ではバリデーションを行っていない場合でもパーサが
		// 無視できる空白を解析して削除し、それをこのメソッドで
		// 通知してもよい事になっている。
		// 引数から String オブジェクトを生成する場合には
		//     new String(ch, start, length)
		// となる。
		
	}

	/**
	 * ContentHandler#processingInstruction の実装<br>
	 * 
	 * @param target プロセッシングインストラクションのターゲット名
	 * @param data  XMLプロセッサが解釈する何らかの情報
	 * @exception SAXException SAXExceptionを生成してスローする事で
	 *                         パース処理を終了させることができる。
	 */
	public void processingInstruction(String target,
	                                  String data)
	                                  throws SAXException {
		// XML プロセッサ依存情報をXMLドキュメントに記述するために
		// 用いられるプロセッシングインストラクションの通知を受ける。
		// XML 宣言が通知されることはない。
		// XML 宣言以外のプロセッシングインストラクションを含んでいる
		// XML 文書は見たことが無く、あったとしても XML プロセッサに
		// 対する情報なのでパース時にこれを処理する必要はほとんど
		// 無いと思われる。
		// 
		// プロセッシングインストラクションは以下のような形式
		// <?hoge foo="bar" aaa?>
		// これは target が hoge になり
		// data は foo="bar" aaa になる。
		// あくまでXMLプロセッサに情報を伝えるものなので
		// XML文書の内容ではない。
		// < と > の中でなければ XML 文書内の任意の場所に
		// 記述できる。
		// data部分に < や > を含めても字句構文チェックは
		// 行われないので
		// <?hoge foo="bar" <aaa>?>
		// はエラーにはならないが
		// <?hoge foo="bar" <aaa?>?>
		// はエラーになる。
		
	}

	/**
	 * ContentHandler#skippedEntity の実装
	 * 
	 * @param name スキップされたエンティティ
	 * @exception SAXException SAXExceptionを生成してスローする事で
	 *                         パース処理を終了させることができる。
	 */
	public void skippedEntity(String name) throws SAXException {
		// DTD を使っていないので実装は不要(多分)
		
	}

	/**
	 * ErrorHandler#warning の実装<br>
	 * 
	 * @param exception SAXParseExceptionオブジェクト
	 * @exception SAXException SAXExceptionを生成してスローする事で
	 *                         パース処理を終了させることができる。
	 */
	public void warning(SAXParseException exception) 
	                    throws SAXException {
		// W3C XML 1.0 勧告セクション 1.2 の「fatal error」にも
		// 「error」にも該当しないが、問題のある部分を
		// 警告として通知するメソッド。
		// このメソッドが呼び出されてもそれは検証エラーではないので
		// XML 文書がバリッドではないという事にはならない。
		// jdk1.5 のドキュメントには「フィルタは、このメソッドを
		// 使用してその他の非 XML 警告も報告できます。」
		// という記述がある。
		// このメソッドが呼び出されてもパースはそのまま続けられるので
		// パース処理を終了したい場合には SAXException か
		// RuntimeException を生成してスローする。
		
	}

	/**
	 * ErrorHandler#error の実装<br>
	 * 
	 * @param exception SAXParseExceptionオブジェクト
	 * @exception SAXException SAXExceptionを生成してスローする事で
	 *                         パース処理を終了させることができる。
	 */
	public void error(SAXParseException exception)
	                  throws SAXException {
		// スキーマによる XML 文書検証に失敗した箇所で発生した
		// エラー通知を受けるメソッド。
		// 受け取るエラーの厳密な定義は、W3C XML 1.0 勧告セクション 1.2 の
		// 「error」に記述されている。
		// このメソッドが呼び出されてもパースはそのまま続けられるので
		// パース処理を終了したい場合には SAXException か
		// RuntimeException を生成してスローする。
		
	}

	/**
	 * ErrorHandler#fatalError の実装<br>
	 * 
	 * @param exception SAXParseExceptionオブジェクト
	 * @exception SAXException SAXExceptionを生成してスローする事ができる。
	 */
	public void fatalError(SAXParseException exception)
	                       throws SAXException {
		// XML 文書の整形式が正しくない場合などの致命的なエラーが
		// 発生した場合にその通知を受けるメソッド。
		// 致命的エラーとは W3C XML 1.0 勧告セクション 1.2 の
		// 「fatal error」の定義に相当する。
		// このエラーが発生した場合には SAX パーサはこのメソッドを
		// 呼び出した後、その他のイベントの報告を停止してもかまわない
		// と jdk1.5 のドキュメントにあり、 Sun の jdk1.5 の実装では
		// fatalError が発生した場合には parse メソッドは 
		// SAXParseException をスローして終了する。
		// 
		// jdk1.5 のドキュメントには以下のような記述がある。
		// 「このメソッドのマニュアルと ContentHandler.endDocument() の
		// マニュアルとの間には明らかに矛盾があります。
		// クライアントは、今後のメジャーリリースでこのあいまいさが
		// 解決されないかぎり、パーサが fatalError() を報告したり例外を
		// スローしたときに endDocument() が呼び出されるかどうかを
		// 仮定しないようにする必要があります。」
		
	}

	// 引数のStringBufferオブジェクトに
	// new String(ch, start, length).trim() を加える。
	/*
	private void appendTrimedStr(StringBuffer buf,
	                             char[] ch,
                                 int start,
                                 int length) {
		int first = start;
		int last = start + length - 1;
		while ((first <= last) && (ch[first] <= ' ')) {
			first++;
		}
		while ((first <= last) && (ch[last] <= ' ')) {
			last--;
		}
		if (first <= last) {
			buf.append(ch, first, last - first + 1);
		} 
	}
	*/

	//////////////////////////////////////////////
	// このメソッドはJDK1.5以降でコンパイル可能 //
	//////////////////////////////////////////////
	// 引数のStringBuilderオブジェクトに
	// new String(ch, start, length).trim() を加える。
	
	private void appendTrimedStr(StringBuilder buf,
	                             char[] ch,
                                 int start,
                                 int length) {
		int first = start;
		int last = start + length - 1;
		while ((first <= last) && (ch[first] <= ' ')) {
			first++;
		}
		while ((first <= last) && (ch[last] <= ' ')) {
			last--;
		}
		if (first <= last) {
			buf.append(ch, first, last - first + 1);
		} 
	}
}
