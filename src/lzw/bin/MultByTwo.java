package lzw.bin;

public class MultByTwo {
	/**
	 * num ָ��Ҫ��λֵvalue �ƶ���λ����Ҳ���ǣ����������<<ʹָ��ֵ������λ������numλ��ÿ����һ��λ���߽�λ�����Ƴ������Ҷ�����������0����ұߡ�����ζ�ŵ����Ƶ���������int
	 * ����ʱ��ÿ�ƶ�1λ���ĵ�31λ��Ҫ���Ƴ����Ҷ����������Ƶ���������long ����ʱ��ÿ�ƶ�1λ���ĵ�63λ��Ҫ���Ƴ����Ҷ�����
	 */
	public static void main(String args[]){
		int i;
		int num = 0xFFFFFFE;

		for (i = 0; i < 4; i++) {
			num = num << 1;
			System.out.println(num);
		}
	}
}
