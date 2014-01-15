package utils.nio.ibm;

import java.io.UnsupportedEncodingException;

public class CutString{
	/**
	 * * �ж��Ƿ���һ�����ĺ��� * *
	 * 
	 * @param c * �ַ� *
	 * @return true��ʾ�����ĺ��֣�false��ʾ��Ӣ����ĸ *
	 * @throws UnsupportedEncodingException * ʹ����JAVA��֧�ֵı����ʽ
	 */
	public static boolean isChineseChar(char c) throws UnsupportedEncodingException{
		// ����ֽ�������1���Ǻ���
		// �����ַ�ʽ����Ӣ����ĸ�����ĺ��ֲ�����ʮ���Ͻ������������Ŀ�У������ж��Ѿ��㹻��
		return String.valueOf(c).getBytes("GBK").length > 1;
	}

	/**
	 * * ���ֽڽ�ȡ�ַ��� * *
	 * 
	 * @param orignal * ԭʼ�ַ��� *
	 * @param count * ��ȡλ�� *
	 * @return ��ȡ����ַ��� *
	 * @throws UnsupportedEncodingException * ʹ����JAVA��֧�ֵı����ʽ
	 */
	public static String substring(String orignal, int count) throws UnsupportedEncodingException{
		// ԭʼ�ַ���Ϊnull��Ҳ���ǿ��ַ���
		if (orignal != null && !"".equals(orignal)) {
			// ��ԭʼ�ַ���ת��ΪGBK�����ʽ
			orignal = new String(orignal.getBytes(), "GBK");
			// Ҫ��ȡ���ֽ�������0����С��ԭʼ�ַ������ֽ���
			if (count > 0 && count < orignal.getBytes("GBK").length) {
				StringBuffer buff = new StringBuffer();
				char c;
				for (int i = 0; i < count; i++) {
					// charAt(int index)Ҳ�ǰ����ַ����ֽ��ַ�����
					c = orignal.charAt(i);
					buff.append(c);
					if (CutString.isChineseChar(c)) {
						// �������ĺ��֣���ȡ�ֽ�������1
						--count;
					}
				}
				return buff.toString();
			}
		}
		return orignal;
	}

	public static void main(String[] args){
		// ԭʼ�ַ���
		String s = "��ZWR��JAVA";
		System.out.println("ԭʼ�ַ�����" + s);
		try {
			System.out.println("��ȡǰ1λ��" + CutString.substring(s, 1));
			System.out.println("��ȡǰ2λ��" + CutString.substring(s, 2));
			System.out.println("��ȡǰ4λ��" + CutString.substring(s, 4));
			System.out.println("��ȡǰ6λ��" + CutString.substring(s, 6));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
