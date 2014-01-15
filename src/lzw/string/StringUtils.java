package lzw.string;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import lzw.regex.RegexUtils;

/**
 * �ַ�����������<br>
 * �������ڣ�2007-11-05
 * 
 * @author ����ΰ
 */
public class StringUtils {

	public static final String GBK = "GBK";
	public static final String GB2312 = "GB2312";
	public static final String UTF8 = "UTF-8";
	public static final String ISO8859_1 = "ISO-8859-1";
	public static final String UNICODE = "UNICODE";

	/**
	 * ����Ҵ�дת��
	 * 
	 * <pre>
	 *  ��Ʒ�������:
	 * 	��Ҫע�������:
	 * 	1.�������������ֿ���ͨ��һ������'Ҽ','��','��'....��ʾ.
	 * 	2.���ڴ���10000�ʹ���100000000������,���ܳ���'��','��'����
	 * 	3.�����м�����Ϊ0������,��ȷ����'��'������,�����м��ֲ�ͬ�������Ҫ����
	 * 	4.����ĳ���ε�����ȫ������,����,������ζ���0�����-100000101,�м��0��γ���
	 * 	5.�ǷֵĴ���,��������ڽǷֵĻ�,Ӧ�ó���'Բ��'������
	 * 	6.�������ֲ����ڵ����,��ֻ�нǷ�,Ӧ��û��'Բ'������
	 * 
	 * 	��ƿ��:
	 * 	1.������ת�����ַ�������,ʹ��Java��ʱ��,��һ��double����ת����һ���ַ������ͺܼ�,
	 * 	����    String.valueOf(double_var)���ɵõ�,������һ������,��������ִ���10��λ��ʱ��,
	 * 	Ҳ���Ǵ��ڵ�ʱ��,����ת���ɿ�ѧ���������ִ�,����������ǰ���ת��������long.
	 * 
	 * 	2.�����ַָ���������ֺ�С�����ֱַ���,��������ķ���,�������԰�double����100,
	 * 	ȡ����λΪС������,ǰ���Ϊ��������,�õ�
	 * 	long l = (long)(d*100);
	 * 	String strVal = String.valueOf(l);
	 * 	String head = strVal.substring(0,strVal.length()-2);         //��������
	 * 	String end = strVal.substring(strVal.length()-2);              //С������
	 * 	
	 * 	3.����Ӧ�ð�Ǯ���ֳɶ�,ÿ�ĸ�һ��,ʵ���ϵõ�����һ����ά����,����:
	 * 	       Ǫ        ��      ʰ        ''
	 * 	''     $4       $3      $2         $1
	 * 	��     $8       $7      $6         $5
	 * 	��     $12      $11     $10        $9
	 * 	
	 * 	    ����$i��ʾ������ֵĵ�i��λ�õ�����,���ǲ���ʵ���趨��ά����,���ǵõ��������ֵ�λ��,
	 * 	Ҫ����ĸò���ʲô���ı�ʾ��,�ܼ����ִ���ʽ��������:��pos��ʾ����λ��,pos/4 ����һ����
	 * 	�����¶�,���,�ڶ�.pos%4��ʾĳһ���εĶ���λ��,Ǫ,��,ʰ,���ڵ��ӵ�Ե��,������ǧ��,����,ǧ�ڵ�
	 * 	����,�����������ǳ�����.������������һ���������,���ǵ�ǰ�Ĵ����������ִ�ǧ��λ,
	 * 	��������������ֽṹ�ǲ��׵�,��Ϊ���ܻ�������,��ʱ���Ƽ����뷨�ǰ���Щ��Ƴɵ�ά������ṹ,
	 * 	�Ӷ�ȡ�õ��ӵı�ʾ.
	 * 
	 * 	4.ѭ���������λ�Ĺ�����,���ǿ���Ԥ�뵽,������������ѽ����.
	 * 	��Ϊ���Ƕ������������ֻ�ܳ���һ����ʾ,��������,��ĳ��ȫΪ0ʱ,'��'�����ܳ���.
	 * 	�����Щ�����ۺϿ��ǵõ����´���.
	 * </pre>
	 * 
	 * @param price
	 * @return
	 */
	public static String changeToBig(double price) {
		char[] hunit = { 'ʰ', '��', 'Ǫ' }; // ����λ�ñ�ʾ
		char[] vunit = { '��', '��' }; // ������ʾ
		char[] digit = { '��', 'Ҽ', '��', '��', '��', '��', '½', '��', '��', '��' }; // ���ֱ�ʾ
		long lPrice = (long) (price * 100); // ת��������
		String sPrice = String.valueOf(lPrice); // ת�����ַ���
		String head = sPrice.substring(0, sPrice.length() - 2); // ȡ��������
		String rail = sPrice.substring(sPrice.length() - 2); // ȡС������

		String prefix = ""; // ��������ת���Ľ��
		String suffix = ""; // С������ת���Ľ��
		// ����С����������
		if (rail.equals("00")) { // ���С������Ϊ0
			suffix = "��";
		} else {
			suffix = digit[rail.charAt(0) - '0'] + "��"
					+ digit[rail.charAt(1) - '0'] + "��"; // ����ѽǷ�ת������
		}
		// ����С����ǰ�����
		char[] chDig = head.toCharArray(); // ����������ת�����ַ�����
		char zero = '0'; // ��־'0'��ʾ���ֹ�0
		byte zeroSerNum = 0; // ��������0�Ĵ���
		for (int i = 0; i < chDig.length; i++) { // ѭ������ÿ������
			int idx = (chDig.length - i - 1) % 4; // ȡ����λ��
			int vidx = (chDig.length - i - 1) / 4; // ȡ��λ��
			if (chDig[i] == '0') { // �����ǰ�ַ���0
				zeroSerNum++; // ����0��������
				if (zero == '0') { // ��־
					zero = digit[0];
				} else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
					prefix += vunit[vidx - 1];
					zero = '0';
				}
				continue;
			}
			zeroSerNum = 0; // ����0��������
			if (zero != '0') { // �����־��Ϊ0,�����,������,��ʲô��
				prefix += zero;
				zero = '0';
			}
			prefix += digit[chDig[i] - '0']; // ת�������ֱ�ʾ
			if (idx > 0)
				prefix += hunit[idx - 1];
			if (idx == 0 && vidx > 0) {
				prefix += vunit[vidx - 1]; // �ν���λ��Ӧ�ü��϶�������,��
			}
		}

		if (prefix.length() > 0)
			prefix += 'Բ'; // ����������ִ���,����Բ������
		return prefix + suffix; // ������ȷ��ʾ
	}

	/**
	 * �Ը�����<code> LENGTH </code>�ָ�������ݣ����طָ����ַ�������<br>
	 * �����Ƿ�������ʾ����
	 * 
	 * <pre>
	 * sSMS = &quot;abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz&quot;;
	 * 1. �ָ��Ķ��Ÿ��� &lt; 10
	 * LENGTH = 10;
	 * (1/6)abcdefghij
	 * (2/6)klmnopqrst
	 * (3/6)uvwxyzabcd
	 * (4/6)efghijklmn
	 * (5/6)opqrstuvwx
	 * (6/6)yz = 7
	 * 
	 * 2. �ָ��Ķ��Ÿ��� &gt;= 10
	 * LENGTH = 5;
	 * (1/15)abcd
	 * (2/15)efgh
	 * (3/15)ijkl
	 * ......
	 * (13/15)tuv
	 * (14/15)wxy
	 * (15/15)z
	 * </pre>
	 * 
	 * @param sSMS
	 *            ��������
	 * @param LENGTH
	 *            ÿ�����ŵĳ���
	 * @return һ���������
	 */
	public static String[] smsSplit(String sSMS, final int LENGTH) {
		int iContentLength = sSMS.trim().length();
		if (LENGTH >= iContentLength) {
			// �������ݵĳ���С�ڵ���LENGTH�����÷ָ�ֱ�ӷ���
			return new String[] { sSMS };
		}

		final int iSMS_Num_9 = 9; // �ָ�9������
		int iSum = LENGTH * iSMS_Num_9; // ������������9���ܳ��ȣ�
		int iLength = 0;
		if (iContentLength > iSum) {
			// Դ���ŵĳ��ȴ���iSum����ʾԴ���ſ��Ա��ָ����������10��
			iLength = LENGTH - 1;
			iSum = iLength * iSMS_Num_9;
		} else if (iContentLength < iSum) {
			// Դ���ŵĳ���С��iSum����ʾԴ���ſ��Ա��ָ������С�ڵ���9��
			iLength = LENGTH;
			iSum = iContentLength;
		}

		// ���Ÿ���С��10
		String sSMS9 = sSMS.trim().substring(0, iSum);
		String[] aSMS9 = smsSplitByLength(sSMS9, iLength);

		if (iContentLength == iSum) {
			List<String> oList = new ArrayList<String>();
			String sTemp;
			final int iSMSNum = aSMS9.length;
			for (int i = 0; i < aSMS9.length; i++) {
				sTemp = "(" + (i + 1) + "/" + iSMSNum + ")" + aSMS9[i];
				oList.add(sTemp);
			}
			String[] aSMS = oList.toArray(new String[0]);
			return aSMS;
		}

		String sTemp1 = aSMS9[aSMS9.length - 1];
		String sTemp2 = aSMS9[aSMS9.length - 2];

		String sSMS10 = null;
		String[] aSMS10 = null;
		if (sTemp1.length() != sTemp2.length()) {
			String[] aTemp = aSMS9;
			aSMS9 = new String[aTemp.length - 1];
			System.arraycopy(aTemp, 0, aSMS9, 0, aTemp.length - 1);
			sSMS10 = sTemp1 + sSMS.trim().substring(iSum);
			aSMS10 = smsSplitByLength(sSMS10, iLength - 1);
		} else {
			sSMS10 = sSMS.trim().substring(iSum);
			aSMS10 = smsSplitByLength(sSMS10, iLength - 1);
		}

		List<String> oList = new ArrayList<String>();
		String sTemp;
		final int iSMSNum = aSMS9.length + aSMS10.length;
		for (int i = 0; i < aSMS9.length; i++) {
			sTemp = "(" + (i + 1) + "/" + iSMSNum + ")" + aSMS9[i];
			oList.add(sTemp);
		}
		for (int i = 0; i < (aSMS10.length); i++) {
			sTemp = "(" + (i + 10) + "/" + iSMSNum + ")" + aSMS10[i];
			oList.add(sTemp);
		}
		String[] aSMS = oList.toArray(new String[0]);
		return aSMS;
	}

	/**
	 * �Ը�����<code> LENGTH </code>�ָ�������ݣ����طָ����ַ�������<br>
	 * <b>ע�⣺���������е����һ��Ԫ�ص��ַ������Ȳ�һ������<code> LENGTH </code>��<br>
	 * ��������<code> sSMS.length % LENGTH != 0 </code></b>
	 * 
	 * @param sSMS
	 *            ��������
	 * @param LENGTH
	 *            ÿ�����ŵĳ���
	 * @return һ���������
	 */
	private static String[] smsSplitByLength(String sSMS, final int LENGTH) {
		int iContentLength = sSMS.trim().length();
		String sTemp;
		int x = 0;
		int y = 0;
		List<String> l = new ArrayList<String>();
		while (true) {
			y = x + LENGTH;
			if (y > iContentLength)
				y = iContentLength;
			sTemp = sSMS.substring(x, y);
			l.add(sTemp);
			x = y;
			if (x == iContentLength)
				break;
		}
		return l.toArray(new String[0]);
	}

	/**
	 * ��ʮ�����Ʊ�ʾ�����ֽ�����Ϊʮ������
	 * 
	 * @param sn
	 *            ʮ�������ַ�������
	 * @return
	 */
	public static int hexToDec(String sn) {
		if (null == sn || 0 == sn.trim().length())
			throw new IllegalArgumentException("���յĲ��� ��" + sn + "�� ����ʮ�����Ʊ�ʾ��ʽ��");

		final String P = "0x";
		if (sn.toLowerCase().startsWith(P)) {
			final String regex = "[^\\da-fA-F]";
			int i = RegexUtils.matcheRegexp(sn.substring(2), regex, false);
			if (0 != i)
				throw new IllegalArgumentException("���յĲ��� ��" + sn
						+ "�� ����ʮ�����Ʊ�ʾ��ʽ��");
			else {
				char[] cs = sn.substring(2).toUpperCase().toCharArray();
				char c;
				for (int j = 0; j < cs.length; j++) {
					c = cs[j];
					i += toDec(c, (cs.length - j - 1));
				}
				// Out.pln("SRC: " + sn);
				// Out.pln("Dec: " + i);
				// Out.pln("Hex: 0x" + Integer.toHexString(i).toUpperCase());
				return i;
			}
		}
		return 0;
	}

	private static int toDec(char c, int i) {
		final double N = 16.0;
		int n = getNum(c);
		int r = (int) (n * Math.pow(N, (double) i));
		return r;
	}

	private static int getNum(char c) {
		// �ַ�Fת����Ϊ����Ϊ70
		if ((int) c > 70)
			return 0;

		int i = 0;
		switch (c) {
		case 'A':
			i = 10;
			break;
		case 'B':
			i = 11;
			break;
		case 'C':
			i = 12;
			break;
		case 'D':
			i = 13;
			break;
		case 'E':
			i = 14;
			break;
		case 'F':
			i = 15;
			break;
		case 'a':
			i = 10;
			break;
		case 'b':
			i = 11;
			break;
		case 'c':
			i = 12;
			break;
		case 'd':
			i = 13;
			break;
		case 'e':
			i = 14;
			break;
		case 'f':
			i = 15;
			break;
		}
		if (0 == i) {
			// �ַ�0ת����Ϊ����Ϊ48
			return (int) c - 48;
		} else
			return i;
	}

	/**
	 * �������ָ�ʽ���ַ������õ�<code> int </code>
	 * 
	 * @param s
	 * @return ���֣�<code> int </code>
	 */
	public static int parseInt(String s) {
		int count = s.length();
		char a;
		int r = 0, n = 0;
		for (int i = 0; i < count; i++) {
			a = s.charAt(i);
			n = (int) a - 48;
			r += n * getNum(count - i - 1);
		}
		return r;
	}

	/**
	 * �жϵ�ǰ��������һλ�ϣ���ʮ��ǧ�򡭡�
	 */
	private static int getNum(int c) {
		if (0 == c)
			return 1;
		int r = 1;
		for (int i = 0; i < c; i++) {
			r *= 10;
		}
		return r;
	}

	/**
	 * �ַ���ȫ��ת��� <br>
	 * <br>
	 * ע�⣺<br>
	 * ���<code> str = null </code>��մ����򷵻�<code> null </code>
	 * 
	 * <pre>
	 *  ȫ�ǿո�Ϊ12288����ǿո�Ϊ32
	 * 	�����ַ����(33-126)��ȫ��(65281-65374)�Ķ�Ӧ��ϵ�ǣ������65248
	 * </pre>
	 * 
	 * @param str
	 *            ȫ���ַ�
	 * @return ����ַ�
	 * @throws UnsupportedEncodingException
	 */
	public static String sbcTodbc(String str)
			throws UnsupportedEncodingException {
		if (null == str || 0 == str.trim().length())
			return null;

		// StringBuilder r = new StringBuilder();
		// String t = "";
		// byte[] b = null;
		//
		// for (int i = 0; i < str.length(); i++) {
		// t = str.substring(i, i + 1);
		// b = t.getBytes(UNICODE);
		//
		// if (null != b) {
		// if (b[3] == -1) {
		// b[2] = (byte) (b[2] + 32);
		// b[3] = 0;
		//
		// // dbc = dbc + new String(b, UNICODE);
		// r.append(new String(b, UNICODE));
		// } else
		// // dbc = dbc + t;
		// r.append(t);
		// } else {
		// // dbc = str;
		// r.append(str);
		// }
		// }
		// // return dbc;
		// return r.toString();

		final int n = 65248;
		// int c;
		// int t;
		// char[] codes = new char[str.length()];
		// for (int i = 0; i < str.length(); i++) {
		// c = (int) str.charAt(i);
		// if (256 < c) {
		// t = c - n;
		// if (0 <= t)
		// codes[i] = (char) t;
		// else
		// codes[i] = (char) c;
		// } else
		// codes[i] = (char) c;
		// }
		// return String.valueOf(codes);

		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - n);
		}
		return new String(c);
	}

	/**
	 * ��<code> regex </code>�ָ��ַ���������ַ������� <br>
	 * <br>
	 * ע�⣺<br>
	 * ���<code> str = null </code>��մ����򷵻�<code> null </code><br>
	 * ���<code> regex = null </code>��մ����򷵻������С����1��Ԫ��Ϊ<code> str </code>
	 * 
	 * @param str
	 *            Ҫ�ָ���ַ���
	 * @param regex
	 *            �ָ�����
	 * @return �ָ����ַ�������
	 */
	public static String[] split(String str, final String regex) {
		if (null == str || 0 == str.trim().length())
			return null;
		if (null == regex || 0 == regex.trim().length())
			return new String[] { str };

		StringTokenizer st = new StringTokenizer(str, regex);
		String a[] = new String[st.countTokens()];
		int i = 0;
		while (st.hasMoreTokens()) {
			a[i] = st.nextToken();
			i++;
		}
		return a;
	}

	/**
	 * �����ַ���
	 * 
	 * @param st
	 * @param decodingCharset
	 *            ʹ��ָ���ַ�������
	 * @return �쳣������null�����򷵻ر������ַ���
	 */
	public static String toGBKString(String st, final String decodingCharset) {
		try {
			if (null == decodingCharset)
				return new String(st.getBytes(), GBK);
			else
				return new String(st.getBytes(decodingCharset), GBK);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * �ַ����滻����
	 * 
	 * @param src
	 *            ԭ�ַ���
	 * @param rep
	 *            ���滻������
	 * @param newstr
	 *            ������
	 * @return ���ַ���
	 */
	public static String replace(String src, String rep, String newstr) {
		int i = 0, j = 0;
		i = src.indexOf(rep);
		StringBuilder sb = new StringBuilder();
		while (j < src.length()) {
			if (i != -1) {
				sb.append(src.substring(j, i) + newstr);
				j = i + rep.length();
				i = src.indexOf(rep, j);
			} else {
				sb.append(src.substring(j));
				break;
			}
		}
		return sb.toString().trim();
	}

	// /**
	// * ���ַ���ת���ɶ������ַ������Կո����
	// *
	// * @param str
	// * @return �ַ����Ķ�������ʽ
	// */
	// public static String toBinary(String str) {
	// char[] strChar = str.toCharArray();
	// String result = null;
	// StringBuilder res = new StringBuilder();
	// for (int i = 0; i < strChar.length; i++) {
	// result = Integer.toBinaryString(strChar[i]) + " ";
	// res.append(result);
	// }
	// return res.toString();
	// }
	//
	// /**
	// * ���������ַ���ת����<code> Unicode </code>�ַ���
	// *
	// * @param binstr
	// * @return ���ַ���
	// */
	// public static String toString(String binstr) {
	// String[] tempStr = binstr.split(" ");
	// char[] tempChar = new char[tempStr.length];
	// for (int i = 0; i < tempStr.length; i++) {
	// tempChar[i] = toChar(tempStr[i]);
	// }
	// return String.valueOf(tempChar);
	// }
	//
	// /**
	// * ���������ַ���ת��Ϊchar
	// *
	// * @param binstr
	// * @return
	// */
	// private static char toChar(String binstr) {
	// int[] temp = binStringToIntArray(binstr);
	// int sum = 0;
	// int t = 0;
	// for (int i = 0; i < temp.length; i++) {
	// t = temp[temp.length - 1 - i] << i;
	// sum += t;
	// }
	// return (char) sum;
	// }
	//
	// /**
	// * ���������ַ���ת����<code> int </code>����
	// *
	// * @param binstr
	// * @return
	// */
	// private static int[] binStringToIntArray(String binstr) {
	// char[] temp = binstr.toCharArray();
	// int[] result = new int[temp.length];
	// for (int i = 0; i < temp.length; i++) {
	// result[i] = temp[i] - 48;
	// }
	// return result;
	// }
}
