package utils.util;

/**
 * ��̬��� �������з����о���ʹ�õ�һ���㷨�������ڿ���ʹ�õݹ飬����Ϊ�ݹ��ظ������ͬ�����������Ч�ʵ��µ�ʱ��<br>
 * ����Բ��ö�̬��̡�<br>
 * ���磬�뿴쳲�������Fibonacci�����У�0, 1, 1, 2, 3, 5, 8, 13, ... ��һ���͵ڶ���쳲��������� �ֱ���Ϊ 0 ��<br>
 * 1���� n ��쳲�����������ǰ����쳲��������ֵĺ͡�
 * 
 * @author ����ΰ Create: 11:12:01 AM Apr 25, 2008
 */
public class Fibonacci {
	public static void main(String args[]) {
		test();
	}

	static void test() {
		int n = 10;
		System.out.print(fibonacci2(n));
		// long s = System.currentTimeMillis();
		// for (int i = 0; i <= n; i++) {
		// // System.out.print(fibonacci1(i) + ", ");
		// System.out.print(fibonacci2(i) + ", ");
		// }
		// System.out.println();
		// long e = System.currentTimeMillis() - s;
		// System.out.println("Time: " + e);
	}

	/**
	 * �ݹ麯������� n ��쳲�������<br>
	 * �ݹ麯������Ч�ʲ��ߣ���Ϊ���ظ��ؽ����ͬ�ĵݹ�������
	 * 
	 * @param n
	 * @return
	 */
	public static long fibonacci1(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return fibonacci1(n - 1) + fibonacci1(n - 2);
		}
	}

	/**
	 * �������ϼ���쳲�������<br>
	 * ���м��������ڱ���У����������ظ�ʹ�����ǣ�������������֮�����ظ������Ρ�ȷʵ���洢�����ڴ�ʹ��Ч�ʽϵͣ�<br>
	 * ��Ϊһ��ֻʹ�ñ���е�������Ŀ�������������ҽ�����·���һ�ߡ����ǽ��ڱ�����ʹ�����Ƶı�񣨲����Ƕ�ά��񣩴���<br>
	 * ���嵥 1 ���ӵö��ʾ�����嵥 2 �е�ʵ�������ʱ����嵥 1 ����࣬�嵥 2 ������ʱ��Ϊ O(n)�����嵥 1 �еĵ�<br>
	 * ��ʵ�ֵ�����ʱ���� n ��ָ����<br>
	 * <br>
	 * <br>
	 * ����Ƕ�̬��̵Ĺ���ԭ������һ�����õݹ��㷨�������½�������⣬Ȼ���ô������ϵ����ķ�ʽ��������м���������<br>
	 * ����й���������ʹ�ã�������Ҫ�ظ��������� �� ����Ȼ��һ��Ч�ʺܵ͵��㷨�����Ƕ�̬���ͨ�����������Ż�����<br>
	 * �����籾�ĺ� ���ʾ��������������쳲����������������⡣�����ʾ����һ���ַ����㷨�����������ѧ�о���ʹ�õ��㷨���ơ�
	 * 
	 * @param n
	 * @return
	 */
	public static int fibonacci2(int n) {
		int[] table = new int[n + 1];
		for (int i = 0; i < table.length; i++) {
			if (i == 0) {
				table[i] = 0;
			} else if (i == 1) {
				table[i] = 1;
			} else {
				table[i] = table[i - 2] + table[i - 1];
			}
		}
		return table[n];
	}
}
