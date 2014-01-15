package utils.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ʹ��������ʽ����ز���<br>
 * �������ڣ�2007-11-15
 * 
 * @author ����ΰ
 */
public class Regexutils {

	/**
	 * ʹ�ø�����������ʽƥ������Ĵ���
	 * 
	 * @param str
	 *          ����
	 * @param regex
	 *          ������ʽ
	 * @param print
	 *          ��ӡ����
	 * @return ƥ�����
	 */
	public static final int matcheRegexp(String str, String regex, boolean print) {
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE
				| Pattern.DOTALL | Pattern.UNICODE_CASE);
		Matcher m = p.matcher(str);
		int i = 0;
		while (m.find()) {
			if (print)
				System.out.println(m.group());
			i++;
		}
		return i;
	}
}
