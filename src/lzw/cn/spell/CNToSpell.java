package lzw.cn.spell;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Set;

import lzw.string.StringUtils;

/**
 * 汉字转化为全拼
 * 
 * @author 李赵伟 Create: 2007-12-18
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
	 * 获得单个汉字的Ascii.
	 * 
	 * @param cn
	 *            char 汉字字符
	 * @return int 错误返回 0,否则返回ascii
	 */
	private static int getCnAscii(char cn) {
		byte[] bytes = (String.valueOf(cn)).getBytes();
		if (null == bytes || 2 < bytes.length || 0 >= bytes.length) { // 错误
			return 0;
		}

		final int EnglishChar = 1; // 英文字符
		final int CNChar = 2; // 中文字符
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
		return 0; // 错误
	}

	/**
	 * 根据ASCII码到SpellMap中查找对应的拼音
	 * 
	 * @param ascii
	 *            int 字符对应的ASCII
	 * @return String 拼音,首先判断ASCII是否>0&<160,如果是返回对应的字符, <BR>
	 *         否则到SpellMap中查找,如果没有找到拼音,则返回null,如果找到则返回拼音.
	 */
	private static String getSpellByAscii(int ascii) {
		if (ascii > 0 && ascii < 160) { // 单字符
			return String.valueOf((char) ascii);
		}

		if (ascii < -20319 || ascii > -10247) { // 不知道的字符
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

			if (ascii >= asciiRang && ascii < valueAsciiRang) { // 区间找到
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
	 * 返回字符串的全拼,是汉字转化为全拼,其它字符不进行转换
	 * 
	 * @param cnStr
	 *            String 字符串
	 * @return String 转换成全拼后的字符串
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
			if (0 == ascii) { // 取ascii时出错
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
	 * 测试
	 */
	public static void main(String[] args) {
		String str = null;
		str = "谢海101普降喜雨";
		System.out.println(str);
		System.out.println("Spell=" + CNToSpell.getFullSpell(str));

		str = "张牙舞爪》。，";
		System.out.println(str);
		System.out.println("Spell=" + CNToSpell.getFullSpell(str));

		str = "李鹏，可耻下场。";
		System.out.println(str);
		System.out.println("Spell=" + CNToSpell.getFullSpell(str));

		str = "猪油，猪八戒。";
		System.out.println(str);
		System.out.println("Spell=" + CNToSpell.getFullSpell(str));
	}
}
