package utils.cn.stroke;

import java.io.UnsupportedEncodingException;

import utils.string.Stringutils;

/**
 * ������������ֵ�һ�����
 * 
 * @author ����ΰ Create: 10:58:25 AM Dec 22, 2007
 */
public class Chinese {

	/**
	 * ����
	 */
	public static void main(String[] args) {
		// createXML();
		test();
	}

	static void test() {
		long s = System.currentTimeMillis();
		// String cn = "�й��Ƴ����͵������";
		String cn = "�Ї��Ƴ����͆α����";
		char c = '��';
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
	 * ��ȡ���ֵıʻ���
	 * 
	 * @param cn
	 *            һ������
	 * @return ���ֵıʻ���
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
	 * ���ݺ����ַ���ñʻ���,ƴ���ͷǷ��ַ�Ĭ��Ϊ0
	 * 
	 * @param cn
	 *            char
	 * @return int
	 */
	private static int countStrokeFromGB2312Table(char cn) {
		try {
			byte[] bytes = (String.valueOf(cn)).getBytes(Stringutils.GB2312);
			if (bytes == null || bytes.length > 2 || bytes.length <= 0) {
				// ��������,�ǺϷ��ַ�
				return 0;
			}
			if (bytes.length == 1) {
				// Ӣ���ַ�
				return 0;
			}
			if (bytes.length == 2) {
				// �����ַ�
				int highByte = 256 + bytes[0];
				int lowByte = 256 + bytes[1];
				return countStrokeFromGB2312Table(highByte, lowByte);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// δ֪����
		return -1;
	}

	/**
	 * @param highByte
	 *            int����λ�ֽ�
	 * @param lowByte
	 *            int����λ�ֽ�
	 * @return int
	 */
	private static int countStrokeFromGB2312Table(int highByte, int lowByte) {
		if (highByte < 0xB0 || highByte > 0xF7 || lowByte < 0xA1
				|| lowByte > 0xFE) {
			// ��GB2312�Ϸ��ַ�
			return -1;
		}
		int offset = (highByte - 0xB0) * (0xFE - 0xA0) + (lowByte - 0xA1);
		return GB2312StrokeTable.gb2312StrokeTable[offset];
	}

	// /**
	// * ��XML�л�÷����ֵıʻ���
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
	// * ��ͳ�Ƶ��ĺ��ֱʻ��������XML
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
	// * ��ͳ�Ƶ��ĺ��ֱʻ��������XML
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
	// * ���캺�ֱʻ�����
	// *
	// * <pre>
	// * ���ڴ����ϵͳ��ֻ��֧��Unicode�еĻ��������ǲ��ֺ��֣������U9FA6-U9FBF��������
	// * ��ֻ���ⲿ�ֺ��ֵıʻ������ֱʻ��������ǿ��԰�������ķ������ɣ�
	// *
	// * 1. ��java��������һ���ı��ļ�(Chinese.csv)���������еĴ�U9FA6-U9FBF���ַ��ı�
	// * ������֡�����excel�İ��ʻ������ܣ���Chinese.csv�ļ��е���������
	// * 2. ��дJava�������Chinese.csv�ļ�����ñʻ���, ����ChineseStroke.csv��������
	// * ���������°����ֵ�Unicode�����ChineseStroke.csv�ļ�����
	// * 3. ֻ����ChineseStroke.csv�ļ������һ�У�����Stroke.csv��
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
	// // ��������
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
	// * ͳ�ƺ��ֵıʻ�
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
