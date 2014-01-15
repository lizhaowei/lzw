package utils.cn.stroke;

import java.io.UnsupportedEncodingException;

import utils.string.Stringutils;

/**
 * 该类包含处理汉字的一组操作
 * 
 * @author 李赵伟 Create: 10:58:25 AM Dec 22, 2007
 */
public class Chinese {

	/**
	 * 测试
	 */
	public static void main(String[] args) {
		// createXML();
		test();
	}

	static void test() {
		long s = System.currentTimeMillis();
		// String cn = "中国推出新型单兵火箭";
		String cn = "中國推出新型單兵火箭";
		char c = '國';
		for (int i = 0; i < cn.length(); i++) {
			c = cn.charAt(i);
			System.out.println(c + " = " + countStroke(c));
		}

		final int SIZE = 1000000;
		for (int j = 0; j < SIZE; j++) {
			for (int i = 0; i < cn.length(); i++) {
				c = cn.charAt(i);
				countStroke(c);
			}
		}
		long e = System.currentTimeMillis() - s;
		System.out.println("Time: " + e);
	}

	// /**
	// * <code>
	// * String datfn = "D:/temp/ChineseStroke.dat";<br>
	// * String xmlfn = "D:/temp/ChineseStroke.xml";
	// * </code>
	// */
	// static void createXML() {
	// // int r = 0x9FA5 - 0x4E00;
	// // System.out.println(r);
	// // System.out.println((char) 0x9FA5);
	// // System.out.println((char) 0x4E00);
	// // String fn = "D:/temp/Chinese.csv";
	// // String fn1 = "D:/temp/ChineseStroke.dat";
	// String datfn = "D:/temp/ChineseStroke.dat";
	// String xmlfn = "D:/temp/ChineseStroke.xml";
	// // cnCoder(fn);
	// // stroke(fn, fn1);
	// createXML(datfn, xmlfn);
	// }

	// private SAXReader sax;
	// private Document doc;
	// private InputStream is;
	// private List chineseStrokes;

	// private Chinese() {
	// init();
	// }

	// private void init() {
	// is = getClass().getResourceAsStream("ChineseStroke.xml");
	// sax = new SAXReader();
	// sax.setEncoding(Stringutils.GBK);
	// try {
	// doc = sax.read(is);
	// } catch (DocumentException e) {
	// e.printStackTrace();
	// }
	// chineseStrokes = ((Element) doc.selectSingleNode("ChineseStrokes"))
	// .selectNodes("ChineseStroke");
	// }

	/**
	 * 获取汉字的笔画数
	 * 
	 * @param cn
	 *            一个汉字
	 * @return 汉字的笔画数
	 */
	public static int countStroke(char cn) {
		int stroke = countStrokeFromGB2312Table(cn);
		if (0 != stroke && -1 != stroke)
			return stroke;
		else {
			int index = ((int) cn) - 0x4E00;
			Integer r = GBKStrokeTable.gbkStrokeTable().get(index);
			if (null == r)
				return 0;
			else
				return r.intValue();
			// Chinese ch = null;
			// try {
			// try {
			// ch = new Chinese();
			// return ch.countStrokeFromXML(cn);
			// } finally {
			// if (null != ch)
			// ch.close();
			// }
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		}
		// return 0;
	}

	/**
	 * 根据汉字字符获得笔画数,拼音和非法字符默认为0
	 * 
	 * @param cn
	 *            char
	 * @return int
	 */
	private static int countStrokeFromGB2312Table(char cn) {
		try {
			byte[] bytes = (String.valueOf(cn)).getBytes(Stringutils.GB2312);
			if (bytes == null || bytes.length > 2 || bytes.length <= 0) {
				// 错误引用,非合法字符
				return 0;
			}
			if (bytes.length == 1) {
				// 英文字符
				return 0;
			}
			if (bytes.length == 2) {
				// 中文字符
				int highByte = 256 + bytes[0];
				int lowByte = 256 + bytes[1];
				return countStrokeFromGB2312Table(highByte, lowByte);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 未知错误
		return -1;
	}

	/**
	 * @param highByte
	 *            int：高位字节
	 * @param lowByte
	 *            int：低位字节
	 * @return int
	 */
	private static int countStrokeFromGB2312Table(int highByte, int lowByte) {
		if (highByte < 0xB0 || highByte > 0xF7 || lowByte < 0xA1
				|| lowByte > 0xFE) {
			// 非GB2312合法字符
			return -1;
		}
		int offset = (highByte - 0xB0) * (0xFE - 0xA0) + (lowByte - 0xA1);
		return GB2312StrokeTable.gb2312StrokeTable[offset];
	}

	// /**
	// * 从XML中获得繁体字的笔画数
	// *
	// * @param cn
	// * @return
	// */
	// private int countStrokeFromXML(char cn) {
	// Element chineseStroke;
	// Element codePoint;
	// Element stroke;
	// int cp;
	// for (int i = 0; i < chineseStrokes.size(); i++) {
	// chineseStroke = (Element) chineseStrokes.get(i);
	// codePoint = (Element) chineseStroke.selectSingleNode("CodePoint");
	// cp = Integer.parseInt(codePoint.getTextTrim());
	// if (((int) cn) == cp) {
	// stroke = (Element) chineseStroke.selectObject("Stroke");
	// return Integer.parseInt(stroke.getTextTrim());
	// }
	// }
	// return 0;
	// }

	// private void close() throws IOException {
	// if (null != is)
	// is.close();
	// this.chineseStrokes = null;
	// this.doc = null;
	// this.sax = null;
	// }

	// /**
	// * 把统计到的汉字笔画数保存成XML
	// */
	// static void processStroke() {
	// Scanner in = null;
	// String datfn = "D:/temp/ChineseStroke.dat";
	// try {
	// try {
	// in = new Scanner(new File(datfn));
	// String[] a;
	// StringBuilder sb = new StringBuilder();
	// while (in.hasNextLine()) {
	// String line = in.nextLine();
	// if (null != line && 0 != line.trim().length()) {
	// // System.out.println(line);
	// a = line.split(",");
	// sb.append(a[3] + ",");
	// }
	// }
	// } finally {
	// if (null != in)
	// in.close();
	// }
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// }
	// }
	// /**
	// * 把统计到的汉字笔画数保存成XML
	// */
	// static void createXML(String datfn, String xmlfn) {
	// Scanner in = null;
	// Document doc = null;
	// DocumentFactory df = DocumentFactory.getInstance();
	// OutputFormat of = OutputFormat.createPrettyPrint();
	// of.setEncoding(Stringutils.GBK);
	// XMLWriter xmlout = null;
	// try {
	// try {
	// doc = df.createDocument(Stringutils.GBK);
	// Element root = df.createElement("ChineseStrokes");
	// Element chineseStroke;
	// Element codePoint;
	// Element chinese;
	// Element stroke;
	// in = new Scanner(new File(datfn));
	// String[] a;
	// while (in.hasNextLine()) {
	// String line = in.nextLine();
	// if (null != line && 0 != line.trim().length()) {
	// // System.out.println(line);
	// a = line.split(",");
	// chineseStroke = df.createElement("ChineseStroke");
	//
	// codePoint = df.createElement("CodePoint");
	// codePoint.add(df.createText(a[0]));
	// chinese = df.createElement("Chinese");
	// chinese.add(df.createText(a[1]));
	// stroke = df.createElement("Stroke");
	// stroke.add(df.createText(a[2]));
	//
	// chineseStroke.add(codePoint);
	// chineseStroke.add(chinese);
	// chineseStroke.add(stroke);
	//
	// root.add(chineseStroke);
	// }
	// }
	// doc.setRootElement(root);
	// xmlout = new XMLWriter(new PrintWriter(xmlfn), of);
	// xmlout.write(doc);
	// xmlout.flush();
	// } finally {
	// if (null != xmlout)
	// xmlout.close();
	// if (null != in)
	// in.close();
	// }
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	// /**
	// * 构造汉字笔画数表
	// *
	// * <pre>
	// * 现在大多数系统还只能支持Unicode中的基本汉字那部分汉字，编码从U9FA6-U9FBF。所以我
	// * 们只建这部分汉字的笔画表。汉字笔画数表，我们可以按照下面的方法生成：
	// *
	// * 1. 用java程序生成一个文本文件(Chinese.csv)。包括所有的从U9FA6-U9FBF的字符的编
	// * 码和文字。利用excel的按笔画排序功能，对Chinese.csv文件中的内容排序。
	// * 2. 编写Java程序分析Chinese.csv文件，求得笔画数, 生成ChineseStroke.csv。矫正笔
	// * 画数，重新按汉字的Unicode编码对ChineseStroke.csv文件排序。
	// * 3. 只保留ChineseStroke.csv文件的最后一列，生成Stroke.csv。
	// * </pre>
	// *
	// * @param fileName
	// */
	// static void cnCoder(String fileName) {
	// if (null == fileName || 0 == fileName.trim().length())
	// fileName = "Chinese.csv";
	// PrintWriter out = null;
	// try {
	// try {
	// out = new PrintWriter(fileName);
	// // 基本汉字
	// for (char c = 0x4E00; c <= 0x9FA5; c++) {
	// out.println((int) c + "," + c);
	// }
	// out.flush();
	// } finally {
	// if (null != out)
	// out.close();
	// }
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// }
	// }

	// /**
	// * 统计汉字的笔画
	// *
	// * @param fileName
	// * @param strokeFileName
	// */
	// static void stroke(String fileName, String strokeFileName) {
	// Scanner in = null;
	// PrintWriter out = null;
	// try {
	// try {
	// in = new Scanner(new File(fileName));
	// out = new PrintWriter(strokeFileName);
	// String oldLine = "999999";
	// int stroke = 0;
	// while (in.hasNextLine()) {
	// String line = in.nextLine();
	// // System.out.println(line);
	// if (line.compareTo(oldLine) < 0) {
	// stroke++;
	// }
	// oldLine = line;
	// out.println(line + "," + stroke);
	// }
	// out.flush();
	// } finally {
	// if (null != out)
	// out.close();
	// if (null != in)
	// in.close();
	// }
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// }
	// }
}
