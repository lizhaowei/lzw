package lzw.cn.spell;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Set;

import lzw.string.StringUtils;

/**
 * ����ת��Ϊȫƴ
 * 
 * @author ����ΰ Create: 2007-12-18
 */
@SuppressWarnings("unchecked")
public class CNToSpell {

	private static LinkedHashMap<String, Integer> spellMap = null;

	static {
		if (null == spellMap) {
			spellMap = new LinkedHashMap<String, Integer>(400);
		}
		init();
		System.out.println("Chinese transfer Spell Done.");
	}

	private CNToSpell() {
	}

	private static void spellPut(String spell, int ascii) {
		spellMap.put(spell, new Integer(ascii));
	}

	private static void init() {
		InputStream is = CNToSpell.class
				.getResourceAsStream("spell.properties");
		Properties p = new Properties();
		try {
			try {
				p.load(is);
			} finally {
				if (null != is)
					is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String spells = p.getProperty("SPELL");
		spells = StringUtils.toGBKString(spells, StringUtils.ISO8859_1);
		String[] a1, a2;
		a1 = spells.split(";");
		String spell;
		for (int i = 0; i < a1.length; i++) {
			spell = a1[i];
			a2 = spell.split(",");
			spellPut(a2[0], Integer.parseInt(a2[1]));
		}
	}

	/**
	 * ��õ������ֵ�Ascii.
	 * 
	 * @param cn
	 *            char �����ַ�
	 * @return int ���󷵻� 0,���򷵻�ascii
	 */
	private static int getCnAscii(char cn) {
		byte[] bytes = (String.valueOf(cn)).getBytes();
		if (null == bytes || 2 < bytes.length || 0 >= bytes.length) { // ����
			return 0;
		}

		final int EnglishChar = 1; // Ӣ���ַ�
		final int CNChar = 2; // �����ַ�
		if (EnglishChar == bytes.length) {
			return bytes[0];
		}
		if (CNChar == bytes.length) {
			int hightByte = 256 + bytes[0];
			int lowByte = 256 + bytes[1];
			int ascii = (256 * hightByte + lowByte) - 256 * 256;
			// System.out.println("ASCII = " + ascii);
			return ascii;
		}
		return 0; // ����
	}

	/**
	 * ����ASCII�뵽SpellMap�в��Ҷ�Ӧ��ƴ��
	 * 
	 * @param ascii
	 *            int �ַ���Ӧ��ASCII
	 * @return String ƴ��,�����ж�ASCII�Ƿ�>0&<160,����Ƿ��ض�Ӧ���ַ�, <BR>
	 *         ����SpellMap�в���,���û���ҵ�ƴ��,�򷵻�null,����ҵ��򷵻�ƴ��.
	 */
	private static String getSpellByAscii(int ascii) {
		if (ascii > 0 && ascii < 160) { // ���ַ�
			return String.valueOf((char) ascii);
		}

		if (ascii < -20319 || ascii > -10247) { // ��֪�����ַ�
			return null;
		}

		Set keySet = spellMap.keySet();
		Iterator it = keySet.iterator();

		String spell = null;
		String keySpell = null;

		int asciiRang = -20319;
		int valueAsciiRang;
		while (it.hasNext()) {
			keySpell = (String) it.next();
			// Object valObj = spellMap.get(keySpell);
			// if (valObj instanceof Integer) {
			valueAsciiRang = ((Integer) spellMap.get(keySpell)).intValue();

			if (ascii >= asciiRang && ascii < valueAsciiRang) { // �����ҵ�
				return (null == spell) ? keySpell : spell;
			} else {
				spell = keySpell;
				asciiRang = valueAsciiRang;
			}
			// }
		}
		return null;
	}

	/**
	 * �����ַ�����ȫƴ,�Ǻ���ת��Ϊȫƴ,�����ַ�������ת��
	 * 
	 * @param cnStr
	 *            String �ַ���
	 * @return String ת����ȫƴ����ַ���
	 */
	public static String getFullSpell(String cnStr) {
		if (null == cnStr || 0 == cnStr.trim().length()) {
			return cnStr;
		}

		char[] chars = cnStr.toCharArray();
		StringBuffer retuBuf = new StringBuffer();
		int ascii = 0;
		String spell = null;
		for (int i = 0; i < chars.length; i++) {
			ascii = getCnAscii(chars[i]);
			if (0 == ascii) { // ȡasciiʱ����
				retuBuf.append(chars[i]);
			} else {
				spell = getSpellByAscii(ascii);
				if (null == spell) {
					retuBuf.append(chars[i]);
				} else {
					retuBuf.append(spell);
				} // end of if spell == null
			} // end of if ascii <= -20400
		} // end of for

		return retuBuf.toString();
	}

	// public static String getFirstSpell(String cnStr) {
	// return null;
	// }

	/**
	 * ����
	 */
	public static void main(String[] args) {
		String str = null;
		str = "л��101�ս�ϲ��";
		System.out.println(str);
		System.out.println("Spell=" + CNToSpell.getFullSpell(str));

		str = "������צ������";
		System.out.println(str);
		System.out.println("Spell=" + CNToSpell.getFullSpell(str));

		str = "�������ɳ��³���";
		System.out.println(str);
		System.out.println("Spell=" + CNToSpell.getFullSpell(str));

		str = "���ͣ���˽䡣";
		System.out.println(str);
		System.out.println("Spell=" + CNToSpell.getFullSpell(str));
	}
}
