package utils.io;

import static java.lang.System.err;
import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author ����ΰ Create: 2:13:25 PM Apr 3, 2008
 */
public class Fileutils {

	/**
	 * �ļ���С����λ��KB
	 * 
	 * @param fileName
	 *          �ļ�����
	 * @return
	 */
	public static long fileSizeToKB(String fileName) {
		File f = new File(fileName);
		boolean exist = f.exists();
		boolean isfile = f.isFile();
		if (exist && isfile) {
			long fileSize = f.length();
			long fileKB = fileSize / 1024;
			return fileKB;
		} else
			throw new IllegalArgumentException("�ļ����ܲ����ڻ�����Ŀ¼��");
	}

	/**
	 * ������д���ļ�
	 * 
	 * @param fileName
	 *          �ļ�����
	 * @param content
	 *          �ļ�����
	 * @param append
	 *          true��д���ļ�ĩβ��false�������ļ�
	 * @throws IOException
	 */
	public static void write(String fileName, String content, boolean append)
			throws IOException {
		File f = new File(fileName);
		if (!f.exists())
			f.createNewFile();
		write(fileName, content.getBytes(), append);
	}

	/**
	 * �����ļ�
	 * 
	 * @param fileName
	 * @param buf
	 * @param append
	 * @throws IOException
	 */
	public static void write(String fileName, byte buf[], boolean append)
			throws IOException {
		File f = new File(fileName);
		if (!f.exists())
			f.createNewFile();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(new FileOutputStream(f, append));
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
		File f = new File(fileName);
		if (!f.exists())
			err.println("�ļ���" + fileName + " �����ڣ�");

		return read(new FileInputStream(f));
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
				if (null != s && 0 != s.trim().length())
					sb.append(s.trim() + "\n");
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
		File f = new File(fileName);
		if (!f.exists())
			err.println("�ļ���" + fileName + " �����ڣ�");
		return readForByte(new FileInputStream(f));
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

}
