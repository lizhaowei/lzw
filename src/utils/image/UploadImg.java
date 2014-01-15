package utils.image;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import utils.regex.Regexutils;

/**
 * ͼƬ�ϴ����������󣬻���������ͼƬ��С��һ��ͼ�꣬���ǿ�������javaǿ���ͼ�δ����ܣ�<br>
 * ���ϴ���ͼƬ�������Ŵ�������ĳ���ʹ��jdk1.4�����µ�ImageIO��ͼƬ���ж�д��<br>
 * ʹ��AffineTransform��ͼƬ�������š�<br>
 * �������ڣ�2007-11-15
 * 
 * @author ����ΰ
 */
public class UploadImg {

	/**
	 * ͼƬ�ϴ����������󣬻���������ͼƬ��С��һ��ͼ�꣬���ǿ�������javaǿ���ͼ�δ����ܣ�<br>
	 * ���ϴ���ͼƬ�������Ŵ�������ĳ���ʹ��jdk1.4�����µ�ImageIO��ͼƬ���ж�д��<br>
	 * ʹ��AffineTransform��ͼƬ��������.
	 * 
	 * <pre>
	 * �ó���ʹ����Java ��AWT,��linux�����п��ܱ��������ֽ����ʽ��
	 * (1) jdk1.4��ǰ�汾����Ҫ��װ XFree86��XFree86-Xvfb ,���� export DISPLAY=hostdomain:0.0
	 * (2) jdk1.4�Ժ�汾��ִ������java �������-Djava.awt.headless=true����ʾ����һ��û�м��� û����ʾ������ͷ����������ƻ����йܵķ�������
	 * </pre>
	 * 
	 * @param fromdir
	 *            ͼƬ��ԭʼĿ¼
	 * @param todir
	 *            ������ͼƬ���Ŀ¼
	 * @param imgfile
	 *            ԭʼͼƬ
	 * @param sysimgfile
	 *            ������ͼƬ�ļ���ǰ׺
	 * @return
	 * @throws IOException
	 */
	public static final boolean createThumbnail(String fromdir, String imgfile,
			String todir, String sysimgfile) throws IOException {
		// ext��ͼƬ�ĸ�ʽ gif JPG ��png
		String ext = "png";
		double Ratio = 0.0;
		File file = new File(fromdir, imgfile);
		if (!file.isFile())
			throw new IOException(file
					+ " is not image file error in CreateThumbnail!");

		// �����ж��ϴ���ͼƬ��gif����JPG ImageIOֻ�ܽ�gifת��Ϊpng
		if (isJpg(imgfile))
			ext = "jpg";

		File f = new File(todir, sysimgfile + "." + ext);
		BufferedImage bi = ImageIO.read(file);
		// ����ͼƬ�� �� ���Ϊ120 120
		Image itemp = bi
				.getScaledInstance(120, 120, BufferedImage.SCALE_SMOOTH);

		if ((bi.getHeight() > 120) || (bi.getWidth() > 120)) {
			if (bi.getHeight() > bi.getWidth())
				Ratio = 120.0 / bi.getHeight();
			else
				Ratio = 120.0 / bi.getWidth();
		}

		AffineTransformOp op = new AffineTransformOp(AffineTransform
				.getScaleInstance(Ratio, Ratio), null);
		itemp = op.filter(bi, null);

		try {
			ImageIO.write((BufferedImage) itemp, ext, f);
		} catch (IOException ex) {
			throw new IOException(" ImageIo.write error in CreatThum.: "
					+ ex.getMessage());
		}
		return true;
	}

	private static final boolean isJpg(String imgfile) {
		String regex = ".jpg";
		int cou = Regexutils.matcheRegexp(imgfile, regex, false);
		return (0 != cou ? true : false);
	}
}
