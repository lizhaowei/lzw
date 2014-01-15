package lzw.bin;

import java.util.Arrays;

/**
 * （有符号）byte类型转换器
 * 
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
 * @author lzw
 */
public class ByteConverter{

	/**
	 * 转换有符号byte类型到32位integer
	 * 
	 * @param b byte
	 * @return convert result
	 */
	public static int unsignedByteToInt(byte b){
		return (int)b & 0xFF;
	}

	/**
	 * 还原32位integer到有符号byte类型
	 * 
	 * @param buf bytes buffer
	 * @param pos beginning <code>byte</code>> for converting
	 * @return convert result
	 */
	public static long unsigned4BytesToInt(byte[] buf, int pos){
		int firstByte = 0;
		int secondByte = 0;
		int thirdByte = 0;
		int fourthByte = 0;
		int index = pos;
		firstByte = (0x000000FF & ((int)buf[index]));
		secondByte = (0x000000FF & ((int)buf[index + 1]));
		thirdByte = (0x000000FF & ((int)buf[index + 2]));
		fourthByte = (0x000000FF & ((int)buf[index + 3]));
		index = index + 4;
		return ((long)(firstByte << 24 | secondByte << 16 | thirdByte << 8 | fourthByte)) & 0xFFFFFFFFL;
	}

	/**
	 * 转换有符号byte类型到16进制数
	 * 
	 * @param b byte
	 * @return convert result
	 */
	public static String byteToHex(byte b){
		int i = b & 0xFF;
		return Integer.toHexString(i);
	}

	/**
	 * 还原16进制数到有符号byte类型
	 * 
	 * @param hex
	 * @return
	 */
	public static byte hexToByte(String hex){
		int i = Integer.parseInt(hex, 16);
		return (byte)(i & 0xFF);
	}

	/**
	 * double到byte转换
	 * 
	 * @param d bouble
	 * @return convert result
	 */
	public static byte[] doubleToByte(double d){
		final int SIZE = 8;
		byte[] b = new byte[SIZE];
		long l = Double.doubleToLongBits(d);
		for(int i = 0; i < SIZE; i++){
			b[i] = new Long(l).byteValue();
			l >>= 8;
		}
		return b;
	}

	/**
	 * byte到double转换
	 * 
	 * @param b
	 * @return convert result
	 */
	public static double byteToDouble(byte[] b){
		long l;
		l = b[0];
		l &= 0xff;
		l |= ((long)b[1] << 8);
		l &= 0xffff;
		l |= ((long)b[2] << 16);
		l &= 0xffffff;
		l |= ((long)b[3] << 24);
		l &= 0xffffffffl;
		l |= ((long)b[4] << 32);
		l &= 0xffffffffffl;

		l |= ((long)b[5] << 40);
		l &= 0xffffffffffffl;
		l |= ((long)b[6] << 48);

		l |= ((long)b[7] << 56);
		return Double.longBitsToDouble(l);
	}

	//	public static byte[] floatToByte(float f){
	//		final int SIZE = 4;
	//		byte[] b = new byte[SIZE];
	//		int n = Float.floatToIntBits(f);
	//		for(int i = 0; i < SIZE; i++){
	//			b[i] = new Integer(n).byteValue();
	//			i >>= 8;
	//		}
	//		return b;
	//	}
	//
	//	public static float byteToFloat(byte[] b){
	//		int i;
	//		i = b[0];
	//		i &= 0xff;
	//		i |= ((int)b[1] << 8);
	//		i &= 0xffff;
	//		i |= ((int)b[2] << 16);
	//		i &= 0xffffff;
	//		i |= ((int)b[3] << 24);
	//		i &= 0xffffffffl;
	//		return Float.intBitsToFloat(i);
	//	}

	/**
	 * 整数到字节数组的转换
	 * 
	 * @param number1
	 * @return
	 */
	public static byte[] intToByte(int number){
		byte[] b = new byte[4];
		for(int i = b.length - 1; i > -1; i--){
			b[i] = new Integer(number & 0xff).byteValue(); //将最高位保存在最低位
			number = number >> 8; //向右移8位
		}
		return b;
	}

	/**
	 * 字节数组到整数的转换
	 * 
	 * @param b
	 * @return
	 */
	public static int byteToInt(byte[] b){
		int s = 0;
		for(int i = 0; i < 3; i++){
			if (b[i] >= 0)
				s = s + b[i];
			else
				s = s + 256 + b[i]; //此处可以使用mod 256来省去if判断
			s = s * 256;
		}
		if (b[3] >= 0) //最后一个之所以不乘，是因为可能会溢出
			s = s + b[3];
		else
			s = s + 256 + b[3]; //同上
		return s;
	}

	/**
	 * 字符到字节转换
	 * 
	 * @param ch
	 * @return
	 */
	public static byte[] charToByte(char ch){
		int temp = (int)ch;
		byte[] b = new byte[2];
		for(int i = b.length - 1; i > -1; i--){
			b[i] = new Integer(temp & 0xff).byteValue(); //将最高位保存在最低位
			temp = temp >> 8; //向右移8位
		}
		return b;
	}

	/**
	 * 字节到字符转换
	 * 
	 * @param b
	 * @return
	 */
	public static char byteToChar(byte[] b){
		int s = 0;
		if (b[0] > 0)
			s += b[0];
		else
			s += 256 + b[0];
		s *= 256;
		if (b[1] > 0)
			s += b[1];
		else
			s += 256 + b[1];
		char ch = (char)s;
		return ch;
	}

	public static void main(String[] args){
		b();
	}

	static void b(){
		double d = 116.325806;
		System.out.println(d);
		byte[] buf = doubleToByte(d);
		System.out.println(Arrays.toString(buf));
		System.out.println(byteToDouble(buf));

		StringBuilder info = new StringBuilder();
		for(int i = 0; i < buf.length; i++){
			info.append(byteToHex(buf[i]));
		}
		System.out.println(info);
		System.out.println("=========================================");
		d = 116.325806;
		String t = Long.toHexString(Double.doubleToLongBits(d));
		
		System.out.println(t);
		
		
		
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++++");

//		float f = 116.325806F;
//		System.out.println(f);
//		int n = Float.floatToIntBits(f);
//		System.out.println(n);
//		System.out.println(Integer.valueOf(n).byteValue() & 0xFF);
//		System.out.println(Integer.toBinaryString(n));
//		System.out.println(Integer.toHexString(n));
//		String t = "0100 0010 1110 1000 1010 0110 1101 0000";
//		t = "1000010111010001010011011010000";
//		System.out.println(Integer.parseInt(t, 2));
//		System.out.print(Integer.parseInt("01000010", 2) + " ");
//		System.out.print(Integer.parseInt("11101000", 2) + " ");
//		System.out.print(Integer.parseInt("10100110", 2) + " ");
//		System.out.print(Integer.parseInt("11010000", 2) + "\n");
//		System.out.println(Float.intBitsToFloat(Integer.parseInt(t, 2)));
//		System.out.println("=========================================");
//		int n1 = 66, n2 = 232, n3 = 166, n4 = 208;
//		System.out.print(Integer.toHexString(n1));
//		System.out.print(Integer.toHexString(n2));
//		System.out.print(Integer.toHexString(n3));
//		System.out.print(Integer.toHexString(n4));
//		System.out.println("\n=========================================");
//		t = "42e8a6d0";
//		n = Integer.parseInt(t, 16);
//		System.out.println(n);
//		System.out.println(Float.intBitsToFloat(n));

	}

	public static float byteToFloat(byte[] b){
		// 4 bytes
		int n = 0;
		for(int i = 0; i < 4; i++){
			n |= (b[i] & 0xff) << i * 8;
		}
		return Float.intBitsToFloat(n);
	}

	static void a(){
		try{
			String a = "返回具有至多单个1位的 int 值";
			byte[] ba = a.getBytes("utf-16");
			System.out.println(Arrays.toString(ba));
			String st;
			StringBuilder info = new StringBuilder();
			for(int i = 0; i < ba.length; i++){
				st = byteToHex(ba[i]);
				//				stt = Integer.toHexString(ba[i]);
				//				System.out.println(st + " -> " + stt);
				info.append(st + ",");
			}
			System.out.println(info);
			String[] sa = info.toString().split(",");
			System.out.println(Arrays.toString(sa));
			byte bb;
			ba = new byte[sa.length];
			for(int i = 0; i < sa.length; i++){
				bb = hexToByte(sa[i]);
				//	      System.out.print(bb+"  ");
				ba[i] = bb;
			}
			a = new String(ba, "utf-16");
			System.out.println(a);

			byte[] tt;
			info = new StringBuilder();
			for(int i = 0; i < ba.length; i += 2){
				tt = new byte[2];
				tt[0] = ba[i];
				tt[1] = ba[i + 1];
				a = new String(tt, "utf-16");
				info.append(a.trim());
			}
			System.out.println(info);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}