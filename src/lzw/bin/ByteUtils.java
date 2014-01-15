package lzw.bin;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import lzw.util.Tools;

/**
 * <pre>
 *  byte��ȡֵ��Χ��-128 &tilde; 127����������2��7�η���2��7�η���ȥ1��
 *  
 *  �Ӽ�������ԭ��ĽǶȿ��Խ��ͣ�byte�ڼ��������ռ8���ֽڣ�����byte ���з������Σ��ö����Ʊ�ʾʱ�����λΪ����λ 0�������� 1��������
 *  ���ֵ��127 -&gt; 0111 1111 ��2��7�η���ȥ1�������ڼ����������ԭ����ʽ���ڣ�
 *  ��Сֵ��-128�������ڼ�����������䲹����ʽ���ڣ������ľ���ֵ��ԭ��תΪ�������ٰ�λȡ�����1��
 *  
 *  ���磺10��-10��
 *  10ԭ�룺0000 1010�����ڼ�����еĴ洢���� 0000 1010��
 *  -10������ֵΪ10��תΪ������ 0000 1010����λȡ�� 1111 0101���ټ�1��1111 0110����Ϊ-10���룬������е�1111 0110���Ǵ���-10�ˡ�
 *  
 *  -128������ֵ128�Ķ����Ʊ�ʾ��1000 0000����λȡ�� 0111 1111����1��1000 0000��
 * <hr>
 *  short ��16λ�з������Σ�
 *  int   ��32λ�з������Σ�
 *  long  ��64λ�з������Σ�
 *  char  ��16λ�޷������Σ��䷶ΧΪ 0 -- 2��15�η���
 *  float ��32λ�ĸ����ͣ�
 *  double��64Ϊ�����ͣ�
 * </pre>
 * 
 * <b> ע�⣺(A & 0xFF) ���ǰ����8λ�����������ı�Ϊ�㡣 </b>
 * 
 * @author ����ΰ Create: 3:36:57 PM Jul 22, 2009
 */
public class ByteUtils {

	private ByteUtils(){
	}

	public static byte[] shortToByte(short s){
		byte[] arr = new byte[2];
		arr[0] = (byte)((s >> 8) & 0xFF);
		arr[1] = (byte)(s & 0xFF);
		return arr;
	}

	public static short byteToShort(byte[] arr){
		int r = arr[0];
		r = (r << 8) | ((int)(arr[1]) & 0xFF);
		return (short)r;

	}

	/**
	 * long��byte�����ת��
	 * 
	 * @param l
	 * @return
	 */
	public static byte[] longToByte(long l){
		byte arr[] = new byte[8];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (byte)((l >> (56 - i * 8)) & 0xFF);
		}
		return arr;
	}

	/**
	 * byte���鵽long��ת��
	 * 
	 * @param arr
	 * @return
	 */
	public static long byteToLong(byte[] arr){
		long l = arr[0];
		for (int i = 1; i < arr.length; i++) {
			l = (l << 8) | ((long)(arr[i]) & 0xFF);
		}
		return l;
	}

	static byte[] floatToByte(float f){
		final int SIZE = 4;
		byte[] arr = new byte[SIZE];
		int n = Float.floatToIntBits(f);
		arr = intToByte(n);
		return arr;
	}

	static float byteToFloat(byte[] arr){
		int i;
		i = byteToInt(arr);
		return Float.intBitsToFloat(i);
	}

	/**
	 * char��byte����ת��
	 * 
	 * @param c
	 * @return
	 */
	public static byte[] charToByte(char c){
		int n = (int)c;
		byte[] arr = new byte[2];
		arr[0] = (byte)((n >> 8) & 0xFF);
		arr[1] = (byte)(n & 0xFF);
		return arr;
	}

	/**
	 * byte���鵽charת��
	 * 
	 * @param arr
	 * @return
	 */
	public static char byteToChar(byte[] arr){
		int n = arr[0];
		n = (n << 8) | ((int)(arr[1]) & 0xFF);
		return ((char)n);
	}

	/**
	 * double��byte����ת��
	 * 
	 * @param d bouble
	 * @return convert result
	 */
	public static byte[] doubleToByte(double d){
		final int SIZE = 8;
		byte[] arr = new byte[SIZE];
		long l = Double.doubleToLongBits(d);
		for (int i = 0; i < SIZE; i++) {
			arr[i] = (byte)l;
			l >>= 8;
		}
		return arr;
	}

	/**
	 * byte���鵽doubleת��
	 * 
	 * @param arr
	 * @return convert result
	 */
	public static double byteToDouble(byte[] arr){
		long l;
		l = arr[0];
		l &= 0xFF;
		l |= ((long)arr[1] << 8);
		l &= 0xFFFF;
		l |= ((long)arr[2] << 16);
		l &= 0xFFFFFF;
		l |= ((long)arr[3] << 24);
		l &= 0xFFFFFFFFL;
		l |= ((long)arr[4] << 32);
		l &= 0xFFFFFFFFFFL;
		l |= ((long)arr[5] << 40);
		l &= 0xFFFFFFFFFFFFL;
		l |= ((long)arr[6] << 48);
		l |= ((long)arr[7] << 56);
		return Double.longBitsToDouble(l);
	}

	/**
	 * int��byte�����ת��
	 * 
	 * @param n
	 * @return
	 */
	public static byte[] intToByte(int n){
		byte[] arr = new byte[4];
		arr[0] = (byte)((n >> 24) & 0xFF);
		arr[1] = (byte)((n >> 16) & 0xFF);
		arr[2] = (byte)((n >> 8) & 0xFF);
		arr[3] = (byte)(n & 0xFF);
		return arr;
	}

	/**
	 * byte���鵽int��ת��
	 * 
	 * @param arr
	 * @return
	 */
	public static int byteToInt(byte[] arr){
		int n = arr[0];
		n = (n << 8) | ((int)(arr[1]) & 0xFF);
		n = (n << 8) | ((int)(arr[2]) & 0xFF);
		n = (n << 8) | ((int)(arr[3]) & 0xFF);
		return n;
	}

	/**
	 * byte��16����ת��
	 * 
	 * @param b һ���ֽ�
	 * @return HEX�ַ���
	 */
	public static String byteToHex(byte b){
		int i = b & 0xFF;
		String s = Integer.toHexString(i);
		if (s.length() % 2 != 0) {
			s = "0" + s;
		}
		return s;
	}

	/**
	 * byte���鵽16����ת��
	 * 
	 * @param arr
	 * @return HEX�ַ���
	 */
	public static String byteToHex(final byte[] arr){
		StringBuilder hex = new StringBuilder("");
		for (int i = 0; i < arr.length; i++) {
			hex.append(byteToHex(arr[i]));
		}
		return hex.toString().trim();
	}

	/**
	 * 16���Ƶ�byte����ת��
	 * 
	 * @param hex
	 * @return byte����
	 */
	public static byte[] hexToByte(String hex){
		if (!Tools.checkStr(hex))
			return null;

		int len = hex.length() >>> 1;
		byte[] arr = new byte[len];
		int index = 0;
		for (int i = 0; i < len; i++) {
			index = i * 2;
			// arr[i] = (byte)(Integer.parseInt(hex.substring(index, index + 2), 16));
			arr[i] = (byte)((Integer.parseInt(hex.substring(index, index + 2), 16)) & 0xFF);
		}
		return arr;
	}

	// *****************************************************************************************************************
	/**
	 * ����
	 */
	public static void main(String args[]){
		testByteToHex();
		System.out.println("==============================");
		testIntToByte();
		System.out.println("==============================");
		testDoubleToByte();
		System.out.println("==============================");
		testCharToByte();
		// System.out.println("==============================");
		// testFloatToByte();
		System.out.println("==============================");
		testLongToByte();
		System.out.println("==============================");
		testShortToByte();
	}

	static void testShortToByte(){
		short s = 12340;
		System.out.println("short = " + s);
		byte arr[] = shortToByte(s);
		System.out.println(Arrays.toString(arr));
		bth(arr);
		s = byteToShort(arr);
		System.out.println(s);
	}

	static void testLongToByte(){
		long l = 31415926L;
		System.out.println("long = " + l);
		byte arr[] = longToByte(l);
		System.out.println(Arrays.toString(arr));
		bth(arr);
		l = byteToLong(arr);
		System.out.println(l);
	}

	static void testFloatToByte(){
		float f = 3.1415926F;
		System.out.println("float = " + f);
		byte arr[] = floatToByte(f);
		System.out.println(Arrays.toString(arr));
		bth(arr);
		f = byteToFloat(arr);
		System.out.println(f);
	}

	static void testCharToByte(){
		char c = '��';
		System.out.println("char = " + c);
		byte arr[] = charToByte(c);
		System.out.println(Arrays.toString(arr));
		bth(arr);
		c = byteToChar(arr);
		System.out.println(c);
	}

	static void testDoubleToByte(){
		double d = 3.1415926;
		System.out.println("double = " + d);
		byte arr[] = doubleToByte(d);
		System.out.println(Arrays.toString(arr));
		bth(arr);
		d = byteToDouble(arr);
		System.out.println(d);
	}

	static void testIntToByte(){
		int i = -1009;
		System.out.println("int = " + i);
		byte arr[] = intToByte(i);
		System.out.println(Arrays.toString(arr));
		bth(arr);
		i = byteToInt(arr);
		System.out.println(i);
	}

	static void bth(byte arr[]){
		String hex = byteToHex(arr);
		System.out.println(hex);
		arr = hexToByte(hex);
		System.out.println(Arrays.toString(arr));
	}

	static void testByteToHex(){
		String s = "����";
		System.out.println("String = " + s);
		try {
			byte arr[] = s.getBytes("utf-8");
			System.out.println(Arrays.toString(arr));
			String hex = byteToHex(arr);
			System.out.println(hex);
			arr = hexToByte(hex);
			System.out.println(Arrays.toString(arr));
			s = new String(arr, "utf-8");
			System.out.println(s);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
