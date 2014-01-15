package lzw.net;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

@SuppressWarnings("unchecked")
public class NetPara {

	public static void main(String args[]) {
		test();
	}

	static void test() {
		init();
		Enumeration keys = resource.getKeys();
		String key;
		int i = 0;
		while (keys.hasMoreElements()) {
			key = (String) keys.nextElement();
			System.out.println(key);
			i++;
		}
		System.out.println(i);
	}

	private static ResourceBundle resource;

	// static {
	// init();
	// }

	private static void init() {
		resource = ResourceBundle.getBundle("com.lzw.net.net", Locale.CHINA);
	}

}
