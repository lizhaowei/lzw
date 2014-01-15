package lzw.bin;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import lzw.util.Tools;

/**
 * <pre>
 *  byte的取值范围：-128 &tilde; 127，即，负的2的7次方到2的7次方减去1。
 *  
 *  从计算机组成原理的角度可以解释：byte在计算机中是占8个字节，而且byte 是有符号整形，用二进制表示时候最高位为符号位 0代表正数 1代表负数。
 *  最大值：127 -&gt; 0111 1111 即2的7次方减去1，正数在计算机中是以原码形式存在；
 *  最小值：-128，负数在计算机中是以其补码形式存在；负数的绝对值的原码转为二进制再按位取反后加1；
 *  
 *  例如：10和-10；
 *  10原码：0000 1010，它在计算机中的存储就是 0000 1010；
 *  -10：绝对值为10，转为二进制 0000 1010，按位取反 1111 0101，再加1后：1111 0110，此为-10补码，计算机中的1111 0110就是代表-10了。
 *  
 *  -128：绝对值128的二进制表示：1000 0000，按位取反 0111 1111，加1后：1000 0000。
 * <hr>
 *  short ：16位有符号整形；
 *  int   ：32位有符号整形；
 *  long  ：64位有符号整形；
 *  char  ：16位无符号整形，其范围为 0 -- 2的15次方；
 *  float ：32位的浮点型；
 *  double：64为浮点型；
 * </pre>
 * 
 * <b> 注意：(A & 0xFF) 就是把最后8位保留，其他的变为零。 </b>
 * 
 * @author 李赵伟 Create: 3:36:57 PM Jul 22, 2009
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
	 * long到byte数组的转换
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
	 * byte数组到long的转换
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
	 * char到byte数组转换
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
	 * byte数组到char转换
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
	 * double到byte数组转换
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
	 * byte数组到double转换
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
	 * int到byte数组的转换
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
	 * byte数组到int的转换
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
	 * byte到16进制转换
	 * 
	 * @param b 一个字节
	 * @return HEX字符串
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
	 * byte数组到16进制转换
	 * 
	 * @param arr
	 * @return HEX字符串
	 */
	public static String byteToHex(final byte[] arr){
		StringBuilder hex = new StringBuilder("");
		for (int i = 0; i < arr.length; i++) {
			hex.append(byteToHex(arr[i]));
		}
		return hex.toString().trim();
	}

	/**
	 * 16进制到byte数组转换
	 * 
	 * @param hex
	 * @return byte数组
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
	 * 测试
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
		char c = '啊';
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
		String s = "啊啊";
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
