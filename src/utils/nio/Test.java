package utils.nio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class Test{
	static void a(){
		try {
			SocketChannel sc = SocketChannel.open();
			SocketAddress sa = new InetSocketAddress("172.17.37.21", 9009);
			sc.connect(sa);

			ByteBuffer bb = ByteBuffer.allocate(1024 * 1000);
			String s = "Hello World!";
			for (int i = 0; i < 10; i++) {
				s = i + "# Hello World!";
				bb.put(s.getBytes());
				bb.flip();
				sc.write(bb);
				bb.clear();
				Thread.sleep(1000 * 10);
			}
			sc.finishConnect();
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void testNIOWriteString(){
		String s = "��ã�����ΰ��";
		File f = new File("D:/temp/test.txt");
		RandomAccessFile raf = null;
		MappedByteBuffer mbb = null;
		FileChannel fc = null;
		try {
			try {
				raf = new RandomAccessFile(f, "rw");
				fc = raf.getChannel();

				byte[] data = s.getBytes();
				mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, data.length);
				mbb.put(data, 0, data.length);
				mbb.force();

			} finally {
				if (null != fc)
					fc.close();
				if (null != raf)
					raf.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static String fn = "D:/temp/test_.txt";
	static String dir = "D:/temp/listener_/";

	static void b(){
		long startTime = System.currentTimeMillis();
		FileInputStream fis = null;
		FileChannel fci = null;
		FileOutputStream fos = null;
		FileChannel fco = null;
		try {
			try {
				fis = new FileInputStream(fn);
				fci = fis.getChannel();

				File f = new File(dir);
				if (!f.exists())
					f.mkdir();

				String s = "nio.txt";
				fos = new FileOutputStream(dir + s);
				fco = fos.getChannel();

				ByteBuffer bb = ByteBuffer.allocate(1024 * 10000);
				int count = fci.read(bb);
				while (-1 != count) {
					bb.flip();
					fco.write(bb);
					fco.force(true);
					bb.clear();
					count = fci.read(bb);
				}

				// long size = fci.size();
				// System.out.println(size);
				// bb.flip();
				// s = new String(bb.array());
				// System.out.println(s);
			} finally {
				if (null != fci)
					fci.close();
				if (null != fco)
					fco.close();
				if (null != fis)
					fis.close();
				if (null != fos)
					fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis() - startTime;
		System.out.println("NIO.Time = " + endTime);
	}

	static void c(){
		long startTime = System.currentTimeMillis();
		// String fn = "D:/temp/test.txt";
		// String dir = "D:/temp/listener_/";
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			try {
				File f = new File(dir);
				if (!f.exists())
					f.mkdir();

				bis = new BufferedInputStream(new FileInputStream(fn));
				// int size = bis.available();
				// System.out.println(size);
				String s = "io.txt";
				bos = new BufferedOutputStream(new FileOutputStream(dir + s));
				byte[] b = new byte[1024 * 10000];
				int count = bis.read(b);
				while (-1 != count) {
					bos.write(b, 0, count);
					count = bis.read(b);
					bos.flush();
				}
			} finally {
				if (null != bis)
					bis.close();
				if (null != bos) {
					// bos.flush();
					bos.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis() - startTime;
		System.out.println("IO.Time = " + endTime);
	}

	static void e(){
		String en = "A";
		String ch = "��";

		// ����һ��Ӣ����ĸ�ڸ��ֱ����µ��ֽ���
		System.out.println("Ӣ����ĸ��" + en);
		printByteLength(en, "GB2312");
		printByteLength(en, "GBK");
		printByteLength(en, "GB18030");
		printByteLength(en, "ISO-8859-1");
		printByteLength(en, "UTF-8");
		printByteLength(en, "UTF-16");
		printByteLength(en, "UTF-16BE");
		printByteLength(en, "UTF-16LE");

		System.out.println();

		// ����һ�����ĺ����ڸ��ֱ����µ��ֽ���
		System.out.println("���ĺ��֣�" + ch);
		printByteLength(ch, "GB2312");
		printByteLength(ch, "GBK");
		printByteLength(ch, "GB18030");
		printByteLength(ch, "ISO-8859-1");
		printByteLength(ch, "UTF-8");
		printByteLength(ch, "UTF-16");
		printByteLength(ch, "UTF-16BE");
		printByteLength(ch, "UTF-16LE");
	}

	/**
	 * ��ӡ�ַ�����ָ�������µ��ֽ����ͱ������Ƶ�����̨
	 * 
	 * @param s �ַ���
	 * @param encodingName �����ʽ
	 */
	public static void printByteLength(String s, String encodingName){
		System.out.print("�ֽ�����");
		try {
			System.out.print(s.getBytes(encodingName).length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(";���룺" + encodingName);
	}

	public static void main(String args[]){
		// a();
		// testNIOWriteString();
		// c();
		// b();
		e();
	}
}
