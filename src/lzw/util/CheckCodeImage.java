package lzw.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * ���У����ͼƬ
 * 
 * @author ����ΰ Create: 2:21:00 PM Dec 29, 2007
 */
public class CheckCodeImage {

	public static void main(String args[]) {
		test();
	}

	static void test() {
		final int SIZE = 4;
		final int width = 60;
		final int height = 18;
		String checkcode = getCheckCode(SIZE);
		String fn = "D:/temp/checkcode.png";
		System.out.println(checkcode);
		BufferedImage bi = createCheckCodeImage(width, height, checkcode);
		try {
			ImageIO.write(bi, "png", new File(fn));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static final char CHECKCODE[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z' };

	public static String getCheckCode(final int SIZE) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < SIZE; i++) {
			sb.append(CHECKCODE[random.nextInt(CHECKCODE.length)]);
		}
		return sb.toString();
	}

	/**
	 * �����֤��ͼƬ<br>
	 * <br>
	 * ����Ĵ�СӦ�ø���ͼƬ�ĸ߶�������<br>
	 * <code>
	 * Font font = new Font("Courier New", Font.BOLD, height);
	 * </code>
	 * 
	 * @param width
	 *            ��֤��ͼƬ���
	 * @param height
	 *            ��֤��ͼƬ�߶�
	 * @param checkcode
	 *            ��֤��
	 * @return ��֤��ͼƬ
	 */
	public static BufferedImage createCheckCodeImage(final int width,
			final int height, final String checkcode) {
		BufferedImage bi = null;
		Graphics2D g = null;
		Random random = new Random();

		try {
			bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			g = bi.createGraphics();
			// g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, width, height);
			// �������壬����Ĵ�СӦ�ø���ͼƬ�ĸ߶�������
			// Font font = new Font("Times New Roman", Font.BOLD, height);
			Font font = new Font("Courier New", Font.BOLD | Font.ITALIC, height);
			g.setFont(font);
			// ���߿�
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, width - 1, height - 1);
			// ������������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽��
			// g.setColor(getColor(random));

			// int lineNum = height / 2;
			// // if (height > 100)
			// // lineNum = height;
			// int x1, y1, x2, y2;
			// for (int i = 0; i < lineNum; i++) {
			// x1 = random.nextInt(width - 1);
			// y1 = random.nextInt(height - 1);
			// x2 = random.nextInt(12);
			// y2 = random.nextInt(12);
			// g.drawLine(x1, y1, x1 + x2, y1 + y2);
			// }

			int fontNum = checkcode.length();
			final int m = 2;
			int fontWidth = (width - m * (fontNum + 1)) / fontNum;
			int n = height - 3;
			for (int i = 0; i < fontNum; i++) {
				// �������������ɫ����֤����Ƶ�ͼ���С�
				g.setColor(getColor(random));
				g.setColor(new Color(0, 48, 96));
				g.drawString(checkcode.charAt(i) + "", m * (i + 1) + fontWidth
						* i, n);
			}
		} finally {
			if (null != g)
				g.dispose();
		}
		return bi;
	}

	private static Color getColor(Random random) {
		final int n = 255;
		int r = random.nextInt(n);
		int g = random.nextInt(n);
		int b = random.nextInt(n);
		return new Color(r, g, b);
	}
}
