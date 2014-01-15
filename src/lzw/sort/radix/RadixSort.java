package lzw.sort.radix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * ��������
 * 
 * @author ����ΰ Create: 2:44:21 PM Mar 16, 2009
 */
@SuppressWarnings("unchecked")
public class RadixSort {
	private int[] iarr;
	private int size;
	private List[] radixes;
	private int Radix;

	public RadixSort(int[] iarr, int Radix) {
		this.iarr = iarr;
		this.Radix = Radix;
		this.size = iarr.length;
	}

	public void display() {
		System.out.println(Arrays.toString(iarr));
	}

	/**
	 * ʹ�������������
	 */
	public void radixSort() {
		this.radixes = new LinkedList[Radix];
		for (int l = 0; l < Radix; l++)
			this.radixes[l] = new LinkedList<Integer>();

		radix();
	}

	private void radix() {
		for (int i = 0; i < Radix; i++) {
			radix(i);
			newArr();
		}
	}

	private void radix(int i) {
		int num, index;
		// �������飬��С����10��������
		for (int l = 0; l < Radix; l++)
			this.radixes[l].clear();

		for (int j = 0; j < size; j++) {
			num = iarr[j];
			// Ѱ�� ��λ-> ʮλ -> ��λ -> ǧλ -> ����
			for (int k = 1; k < i; k++)
				num /= 10;

			index = num % 10;
			radixes[index].add(iarr[j]);
		}
	}

	/**
	 * �ϲ������µ�����
	 */
	private void newArr() {
		LinkedList<Integer> ll;
		int len = -1;
		for (int i = 0; i < Radix; i++) {
			ll = (LinkedList<Integer>) radixes[i];
			for (int j = 0; j < ll.size(); j++) {
				iarr[++len] = ll.get(j).intValue();
			}
		}
	}

	// ------------------------------------------------------------------------------------
	/**
	 * ���������㷨
	 */
	public void sort() {
		int[] tmparr = new int[size];
		int[] count = new int[Radix];// ����ͳ��ÿ���������ֵĴ���
		int N = 1; // ���ڶ�λ��λ-> ʮλ -> ��λ -> ǧλ -> ����
		int index;

		for (int i = 0; i < Radix; i++) {
			System.arraycopy(iarr, 0, tmparr, 0, size);
			Arrays.fill(count, 0);
			index = 0;

			for (int j = 0; j < size; j++) {
				index = (tmparr[j] / N) % Radix;
				count[index]++;
			}

			// ȷ��ÿһ���������������ʼλ��
			for (int j = 1; j < Radix; j++) {
				count[j] = count[j] + count[j - 1];
			}

			index = 0;
			for (int j = size - 1; j >= 0; j--) {
				index = (tmparr[j] / N) % Radix;
				iarr[--count[index]] = tmparr[j];
			}

			// 1*10, 10*10, 100*10, 1000*10, ...
			N *= Radix;
		}
	}
	// ------------------------------------------------------------------------------------

}
