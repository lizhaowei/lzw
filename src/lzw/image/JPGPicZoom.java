package lzw.image;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * ����ͼʵ�֣���ͼƬ(jpg��ʽ)��ʵ�ı����Ҫ�Ĵ�С<br>
 * ����ͼ�࣬��java���ܽ�jpgͼƬ�ļ������еȱȻ�ǵȱȵĴ�Сת����<br>
 * �������ڣ�2007-11-15
 * 
 * @author ����ΰ
 */
public class JPGPicZoom{

	/** * ����ͼ·�� */
	private String inputDir;
	/** * ���ͼ·�� */
	private String outputDir;
	/** * ����ͼ�ļ��� */
	private String inputFileName;
	/** * ���ͼ�ļ��� */
	private String outputFileName;
	/** * Ĭ�����ͼƬ�� */
	private int outputWidth = 128;
	/** * Ĭ�����ͼƬ�� */
	private int outputHeight = 128;
	/** * �Ƿ�ȱ����ű��(Ĭ��Ϊ�ȱ�����) */
	private boolean proportion = true;

	public void setInputDir(String inputDir){
		this.inputDir = inputDir;
	}

	public void setinputFileName(String outputDir){
		this.outputDir = outputDir;
	}

	public void setInputFileName(String inputFileName){
		this.inputFileName = inputFileName;
	}

	public void setOutputFileName(String outputFileName){
		this.outputFileName = outputFileName;
	}

	public void setOutputWidth(int OutputWidth){
		this.outputWidth = OutputWidth;
	}

	public void setOutputHeight(int OutputHeight){
		this.outputHeight = OutputHeight;
	}

	public void setWidthAndHeight(int width, int height){
		this.outputWidth = width;
		this.outputHeight = height;
	}

	/**
	 * @return ͼƬ����
	 */
	private boolean reduceJPGPic(){
		Image img = null;
		Toolkit tk = Toolkit.getDefaultToolkit();
		MediaTracker mt = new MediaTracker(new Applet());
		img = tk.createImage(inputDir + inputFileName);
		mt.addImage(img, 0);
		try{
			mt.waitForID(0);
		}catch(InterruptedException e){
			e.printStackTrace();
			return false;
		}

		if (-1 == img.getWidth(null)){
			System.out.println("Can't read, retry!");
			return false;
		}else{
			int new_w;
			int new_h;

			// �ж��Ƿ��ǵȱ�����
			if (proportion){
				double width = (double)img.getWidth(null);
				double height = (double)img.getHeight(null);
				// Ϊ�ȱ����ż��������ͼƬ��ȼ��߶�
				double rate1 = width / (double)outputWidth + 0.1;
				double rate2 = height / (double)outputHeight + 0.1;
				double rate = rate1 > rate2 ? rate1 : rate2;
				new_w = (int)(width / rate);
				new_h = (int)(height / rate);
			}else{
				new_w = outputWidth; // �����ͼƬ���
				new_h = outputHeight; // �����ͼƬ�߶�
			}
			BufferedImage buffImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);

			Graphics g = buffImg.createGraphics();

			g.setColor(Color.white);
			g.fillRect(0, 0, new_w, new_h);

			g.drawImage(img, 0, 0, new_w, new_h, null);
			g.dispose();

			FileOutputStream tempout = null;
			try{
				try{
					File file = new File(outputDir + outputFileName);
					tempout = new FileOutputStream(file);
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(tempout);
					encoder.encode(buffImg);
					tempout.close();
				}finally{
					if (null != tempout) tempout.close();
				}
			}catch(FileNotFoundException e){
				e.printStackTrace();
				return false;
			}catch(IOException ex){
				ex.printStackTrace();
				return false;
			}
		}
		System.out.println("ͼƬ������ɣ�");
		return false;
	}

	/**
	 * JPG��ʽͼƬ����
	 * 
	 * @param inputDir Ҫ���ŵ�ͼƬԴ��ַ
	 * @param outputDir ���ź�ͼƬ�����ַ
	 * @param inputFileName ԭͼƬ���ƣ�JPG��ʽ
	 * @param outputFileName ���ź�ͼƬ����
	 * @return <code> true </code>�����ųɹ������򷵻�<code> false </code>
	 */
	public boolean reduceJPGPic(String inputDir, String outputDir,
	    String inputFileName, String outputFileName){
		// ����ͼ·��
		this.inputDir = inputDir;
		// ���ͼ·��
		this.outputDir = outputDir;
		// ����ͼ�ļ���
		this.inputFileName = inputFileName;
		// ���ͼ�ļ���
		this.outputFileName = outputFileName;
		return reduceJPGPic();
	}

	/**
	 * JPG��ʽͼƬ����
	 * 
	 * @param inputDir Ҫ���ŵ�ͼƬԴ��ַ
	 * @param outputDir ���ź�ͼƬ�����ַ
	 * @param inputFileName ԭͼƬ���ƣ�JPG��ʽ
	 * @param outputFileName ���ź�ͼƬ����
	 * @param width ͼƬ���ź�Ŀ��
	 * @param height ͼƬ���ź�ĸ߶�
	 * @param proportion true��ͼƬ���ձ������ţ�false��ͼƬʹ�ø����Ŀ�Ⱥ͸߶Ƚ�������
	 * @return <code> true </code>�����ųɹ������򷵻�<code> false </code>
	 */
	public boolean reduceJPGPic(String inputDir, String outputDir,
	    String inputFileName, String outputFileName, int width, int height,
	    boolean proportion){
		this.inputDir = inputDir;
		this.outputDir = outputDir;
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		setWidthAndHeight(width, height);
		this.proportion = proportion;
		return reduceJPGPic();
	}

	/**
	 * ����
	 */
	public static void main(String[] args){
		new JPGPicZoom().reduceJPGPic("D:/temp/", "D:/temp/", "car.jpg", "car1.jpg", 400, 400, true);
	}
}
