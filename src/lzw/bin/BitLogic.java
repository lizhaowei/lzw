package lzw.bin;

public class BitLogic {
	public static void main(String args[]){
		String binary[] = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};

		int a = 3; // 0 + 2 + 1 or 0011 in binary
		int b = 6; // 4 + 2 + 0 or 0110 in binary
		int c = a | b;
		int d = a & b;
		int e = a ^ b;
		int f = (~a & b) | (a & ~b);
		int g = ~a & 0x0f;

		System.out.println(" a = " + binary[a]);
		System.out.println(" b = " + binary[b]);
		System.out.println(" a|b = " + binary[c]);
		System.out.println(" a&b = " + binary[d]);
		System.out.println(" a^b = " + binary[e]);
		System.out.println("~a&b|a&~b = " + binary[f]);
		System.out.println("~a = " + binary[g]);

		/*
		 * ����a��b��Ӧλ����ϴ����˶����������е� 4 �����ģʽ��0-0��0-1��1-0 ����1-1 ����|��������͡�&����
		 * ����ֱ�Ա���a��b������Ӧλ������õ��˱���c�ͱ���d��ֵ���Ա���e��f�ĸ�ֵ˵���ˡ�^��������Ĺ��ܡ� �ַ�������binary ������0��15
		 * ��Ӧ�Ķ����Ƶ�ֵ���ڱ����У������Ԫ�ص�����˳����ʾ�˱�����Ӧֵ��
		 * �����ƴ��롣����֮����������������Ϊ������ֵn��Ӧ�Ķ����ƴ�����Ա���ȷ�Ĵ洢�������ӦԪ��binary[n]
		 * �С��������a��ֵΪ3�������Ķ����ƴ����Ӧ�ش洢������Ԫ��binary[3] �С�~a��ֵ������0x0f ����Ӧ������Ϊ0000 1111 ��
		 * ���а�λ�������Ŀ���Ǽ�С~a��ֵ����֤����g�Ľ��С��16����˸ó�������н������������binary ��Ӧ��Ԫ������ʾ��
		 */
	}
}
