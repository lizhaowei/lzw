package utils.sort;

import java.lang.reflect.Array;

/**
 * �鲢����
 * 
 * <pre>
 *  �㷨˼����ÿ�ΰѴ������зֳ������֣��ֱ���������ֵݹ���ù鲢������ɺ���������Ӳ��ֺϲ���һ��
 *  ���С�
 *  �鲢�������һ��ȫ������ʱ����������������еĹ鲢�����㷨�������ڹ鲢��
 * </pre>
 * 
 * @author ����ΰ Create: 2007-12-17
 */
public class MergeSorter<E extends Comparable<E>> extends Sorter<E> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see algorithms.Sorter#sort(E[], int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void sort(E[] array, int from, int len) {
		if (len <= 1)
			return;
		E[] temporary = (E[]) Array.newInstance(array[0].getClass(), len);
		merge_sort(array, from, from + len - 1, temporary);

	}

	private final void merge_sort(E[] array, int from, int to, E[] temporary) {
		if (to <= from) {
			return;
		}
		int middle = (from + to) / 2;
		merge_sort(array, from, middle, temporary);
		merge_sort(array, middle + 1, to, temporary);
		merge(array, from, to, middle, temporary);
	}

	private final void merge(E[] array, int from, int to, int middle,
			E[] temporary) {
		int k = 0, leftIndex = 0, rightIndex = to - from;
		System.arraycopy(array, from, temporary, 0, middle - from + 1);
		for (int i = 0; i < to - middle; i++) {
			temporary[to - from - i] = array[middle + i + 1];
		}
		while (k < to - from + 1) {
			if (temporary[leftIndex].compareTo(temporary[rightIndex]) < 0) {
				array[k + from] = temporary[leftIndex++];

			} else {
				array[k + from] = temporary[rightIndex--];
			}
			k++;
		}
	}
}
