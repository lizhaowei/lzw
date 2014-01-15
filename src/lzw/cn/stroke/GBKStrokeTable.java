package lzw.cn.stroke;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import lzw.string.StringUtils;

/**
 * GBK编码汉字笔画数列表
 * 
 * @author 李赵伟 Create: 3:30:41 PM Dec 24, 2007
 */
public class GBKStrokeTable {
	private static List<Integer> gbkStrokeTable;
	private String strokes;

	private GBKStrokeTable() {
		init();
	}

	private void init() {
		InputStream is = getClass().getResourceAsStream("gbkstroke.properties");
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

		strokes = StringUtils.toGBKString(p.getProperty("gbkstroke"),
				StringUtils.GBK);
		String[] a = strokes.split(",");
		gbkStrokeTable = new ArrayList<Integer>();
		for (int i = 0; i < a.length; i++) {
			gbkStrokeTable.add(Integer.valueOf(a[i]));
		}
	}

	/**
	 * @return 获得GBK编码的汉字笔画数列表
	 */
	public static List<Integer> gbkStrokeTable() {
		if (null != gbkStrokeTable)
			return gbkStrokeTable;

		new GBKStrokeTable();
		return gbkStrokeTable;
	}
}
