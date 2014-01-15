package utils.util;

import java.io.UnsupportedEncodingException;

/**
 * @author ����ΰ Create: 4:59:29 PM May 11, 2009
 */
class CharsetUtil{

	public static void main(String args[]){
		a();
		b();
	}

	static void b(){
		String s1 = "�����й���";
		String s2 = "imchinese";
		String s3 = "im�й���";
		System.out.println(s1 + ":" + s1.length());
		System.out.println(s2 + ":" + s2.length());
		System.out.println(s3 + ":" + s3.length());

		System.out.println("++++++++++++++++++++++++++++++++++++");
		System.out.println((s1.getBytes().length == s1.length()) ? "s1�޺���" : "s1�к���");
		System.out.println((s2.getBytes().length == s2.length()) ? "s2�޺���" : "s2�к���");
		System.out.println((s3.getBytes().length == s3.length()) ? "s3�޺���" : "s3�к���");
	}

	static void a(){
		try{
			String s = new String("����ΰ".getBytes("big5"), "iso-8859-1");
			System.out.println(s);
			System.out.println(isBIG5(s));

		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}

	/**
	 * �ж�һ��ISO8859-1�ַ����Ƿ����BIG5�ַ���
	 * 
	 * @param line
	 * @return
	 */
	public static int isBIG5(String line){
		int count = 0;
		for(int i = 0; i < line.length() - 1; i++){
			if (isBIG5((byte)line.charAt(i), (byte)line.charAt(i + 1))){
				count++;
				i++;//����һ���ַ�
			}
		}
		return count;
	}

	//�ж�һ��ISO8859-1�ַ����Ƿ����GB2312�ַ���
	public static int isGB2312(String line){
		int count = 0;
		for(int i = 0; i < line.length() - 1; i++){
			if (isGB2312((byte)line.charAt(i), (byte)line.charAt(i + 1))){
				count++;
				i++;//����һ���ַ�
			}
		}
		return count;
	}

	//�ж�һ��ISO8859-1�ַ����Ƿ����GBK�ַ���
	public static int isGBK(String line){
		int count = 0;
		for(int i = 0; i < line.length() - 1; i++){
			if (isGBK((byte)line.charAt(i), (byte)line.charAt(i + 1))){
				count++;
				i++;//����һ���ַ�
			}
		}
		return count;
	}

	//�ж�һ��ISO8859-1�ַ����Ƿ����GB18030�ַ���
	public static int isGB18030(String line){
		int count = 0;
		for(int i = 0; i < line.length() - 1; i++){
			if (isGB18030((byte)line.charAt(i), (byte)line.charAt(i + 1))){
				count++;
				i++;//����һ���ַ�
			}
		}
		return count;
	}

	/**
	 * �ж��Ƿ�ΪGBK�ַ�
	 * 
	 * @param head
	 * @param tail
	 * @return
	 */
	public static boolean isGBK(byte head, byte tail){
		int iHead = head & 0xff;
		int iTail = tail & 0xff;
		return((iHead >= 0x81 && iHead <= 0xfe) && (iTail >= 0x40 && iTail <= 0xfe));
	}

	/**
	 * �ж��Ƿ�ΪGB2312�ַ�
	 * 
	 * @param head
	 * @param tail
	 * @return
	 */
	public static boolean isGB2312(byte head, byte tail){
		int iHead = head & 0xff;
		int iTail = tail & 0xff;
		return (iHead >= 0xb0 && iHead <= 0xf7) && (iTail >= 0xa0 && iTail <= 0xfe);
	}

	/**
	 * �ж��Ƿ�ΪGB18030�ַ�
	 * 
	 * @param head
	 * @param tail
	 * @return
	 */
	public static boolean isGB18030(byte head, byte tail){
		int iHead = head & 0xff;
		int iTail = tail & 0xff;
		return(iHead >= 0x81 && iHead <= 0xFE && ((iTail >= 0x40 && iTail <= 0x7E) || (iTail >= 0x80 && iTail <= 0xFE)));
	}

	/**
	 * �ж��Ƿ�ΪBIG5�ַ�
	 * 
	 * @param head
	 * @param tail
	 * @return
	 */
	public static boolean isBIG5(byte head, byte tail){
		int iHead = head & 0xff;
		int iTail = tail & 0xff;
		return((iHead >= 0x81 && iHead <= 0xfe) && ((iTail >= 0x40 && iTail <= 0x7e) || (iTail >= 0xa1 && iTail <= 0xfe)));
	}
}
