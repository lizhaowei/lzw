package utils.sort;

/**
 * ��������
 * 
 * <pre>
 * ���㷨�����ݹ�ģС��ʱ��ʮ�ָ�Ч�����㷨ÿ�β����K+1��ǰK������������һ������λ�ã�
 * K��0��ʼ��N-1,�Ӷ��������
 * </pre>
 * 
 * @author ����ΰ
 * @param <E>
 */
public class InsertSorter<E extends Comparable<E>> extends Sorter<E> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see algorithms.Sorter#sort(E[], int, int)
	 */
	public void sort(E[] array, int left, int len) {
		E tmp = null;
		int j;
		for (int i = left + 1; i < left + len; i++) {
			tmp = array[i];
			j = i;
			for (; j > left; j--) {
				if (tmp.compareTo(array[j - 1]) < 0) {
					array[j] = array[j - 1];
				} else
					break;
			}
			array[j] = tmp;
		}
	}
}
