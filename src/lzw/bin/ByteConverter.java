package lzw.bin;

import java.util.Arrays;

/**
 * ���з��ţ�byte����ת����
 * 
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
 * @author lzw
 */
public class ByteConverter{

	/**
	 * ת���з���byte���͵�32λinteger
	 * 
	 * @param b byte
	 * @return convert result
	 */
	public static int unsignedByteToInt(byte b){
		return (int)b & 0xFF;
	}

	/**
	 * ��ԭ32λinteger���з���byte����
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
	 * ת���з���byte���͵�16������
	 * 
	 * @param b byte
	 * @return convert result
	 */
	public static String byteToHex(byte b){
		int i = b & 0xFF;
		return Integer.toHexString(i);
	}

	/**
	 * ��ԭ16���������з���byte����
	 * 
	 * @param hex
	 * @return
	 */
	public static byte hexToByte(String hex){
		int i = Integer.parseInt(hex, 16);
		return (byte)(i & 0xFF);
	}

	/**
	 * double��byteת��
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
	 * byte��doubleת��
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
	 * �������ֽ������ת��
	 * 
	 * @param number1
	 * @return
	 */
	public static byte[] intToByte(int number){
		byte[] b = new byte[4];
		for(int i = b.length - 1; i > -1; i--){
			b[i] = new Integer(number & 0xff).byteValue(); //�����λ���������λ
			number = number >> 8; //������8λ
		}
		return b;
	}

	/**
	 * �ֽ����鵽������ת��
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
				s = s + 256 + b[i]; //�˴�����ʹ��mod 256��ʡȥif�ж�
			s = s * 256;
		}
		if (b[3] >= 0) //���һ��֮���Բ��ˣ�����Ϊ���ܻ����
			s = s + b[3];
		else
			s = s + 256 + b[3]; //ͬ��
		return s;
	}

	/**
	 * �ַ����ֽ�ת��
	 * 
	 * @param ch
	 * @return
	 */
	public static byte[] charToByte(char ch){
		int temp = (int)ch;
		byte[] b = new byte[2];
		for(int i = b.length - 1; i > -1; i--){
			b[i] = new Integer(temp & 0xff).byteValue(); //�����λ���������λ
			temp = temp >> 8; //������8λ
		}
		return b;
	}

	/**
	 * �ֽڵ��ַ�ת��
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
			String a = "���ؾ������൥��1λ�� int ֵ";
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