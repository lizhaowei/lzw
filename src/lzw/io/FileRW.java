package lzw.io;

import static java.lang.System.err;
import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * �ļ���ȡ��д����<br>
 * �������ڣ�2007-10-25
 * 
 * @author ����ΰ
 */
public class FileRW {

	/**
	 * ����
	 */
	public static void main(String[] args) {
		// String content =
		// "ȡ������ĵص㣺\t�찲��,116.398,39.9091;�찲��,116.398,39.9091;�ʹ�,116.402,39.9152;�찲��,116.398,39.9091;�ʹ�,116.402,39.9152;�ú�԰,116.285725,39.99835333;";
		// content = content
		// + "\r\nδȡ������ĵص㣺\tasldkfjlaksdfjlkajsdflkadsjflkjasldkjfl";
		// String fileName = "D:/temp/coors.txt";
		// boolean append = false;
		// write(fileName, content, append);
		String fn = "D:/temp/hehe.gif";
		fn = "D:/temp/music.mp3";
		byte buf[];
		try {
			buf = readForByte(fn);
			out.println(buf.length);

			String fileName = "D:/temp/hehe1.gif";
			fileName = "D:/temp/music1.mp3";
			write(fileName, buf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������д���ļ�
	 * 
	 * @param fileName
	 *            �ļ�����
	 * @param content
	 *            �ļ�����
	 * @param append
	 *            true��д���ļ�ĩβ��false�������ļ�
	 * @throws IOException
	 */
	public static void write(String fileName, String content, boolean append)
			throws IOException {
		File f = new File(fileName);
		BufferedWriter bw = null;
		try {
			if (!f.exists())
				f.createNewFile();

			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(f, append)));
			bw.write(content);
			bw.flush();
			out.println("д�ļ���ϣ�" + fileName);
		} finally {
			if (null != bw)
				bw.close();
		}
	}

	/**
	 * �����ļ�
	 * 
	 * @param fileName
	 * @param buf
	 * @throws IOException
	 */
	public static void write(String fileName, byte buf[]) throws IOException {
		File f = new File(fileName);
		DataOutputStream dos = null;
		try {
			if (!f.exists())
				f.createNewFile();

			dos = new DataOutputStream(new FileOutputStream(f));
			dos.write(buf);
			dos.flush();
			out.println("д�ļ���ϣ�" + fileName);
		} finally {
			if (null != dos)
				dos.close();
		}
	}

	/**
	 * ��ȡ�ļ�
	 * 
	 * @param fileName
	 * @return �ļ�����
	 * @throws IOException
	 */
	public static String read(String fileName) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		File f = new File(fileName);
		if (!f.exists())
			err.println("�ļ���" + fileName + " �����ڣ�");

		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(f)));
			String s;
			while (br.ready()) {
				s = br.readLine();
				sb.append(s + "\n");
			}
			return sb.toString();
		} finally {
			if (null != br)
				br.close();
		}
	}

	/**
	 * ��ȡ�ļ�����ֽ�����
	 * 
	 * @param fileName
	 * @return �ļ����ݵ��ֽ���ʽ
	 * @throws IOException
	 */
	public static byte[] readForByte(String fileName) throws IOException {
		FileInputStream fis = null;
		File f = new File(fileName);
		if (!f.exists())
			err.println("�ļ���" + fileName + " �����ڣ�");

		ByteArrayOutputStream baos = null;
		try {
			byte buf[] = new byte[8192];
			fis = new FileInputStream(f);
			baos = new ByteArrayOutputStream();
			int len = fis.read(buf);
			while (-1 != len) {
				baos.write(buf, 0, len);
				len = fis.read(buf);
			}
			buf = baos.toByteArray();
			return buf;
		} finally {
			if (null != fis)
				fis.close();
			if (null != baos)
				baos.close();
		}
	}

	/**
	 * ���������л���ֽ�����
	 * 
	 * @param is
	 * @return �������е��ֽ�
	 * @throws IOException
	 */
	public static byte[] readForByte(InputStream is) throws IOException {
		ByteArrayOutputStream baos = null;
		try {
			byte buf[] = new byte[8192];
			baos = new ByteArrayOutputStream();
			int len = is.read(buf);
			while (-1 != len) {
				baos.write(buf, 0, len);
				len = is.read(buf);
			}
			buf = baos.toByteArray();
			return buf;
		} finally {
			if (null != is)
				is.close();
			if (null != baos)
				baos.close();
		}
	}

	/**
	 * ���������ж�ȡ�ļ�����
	 * 
	 * @param is
	 * @return �ļ�����
	 * @throws IOException
	 */
	public static String read(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			String s;
			while (br.ready()) {
				s = br.readLine();
				sb.append(s + "\n");
			}
			return sb.toString();
		} finally {
			if (null != br)
				br.close();
		}
	}
}
