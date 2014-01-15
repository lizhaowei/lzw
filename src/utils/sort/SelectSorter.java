package utils.sort;

/**
 * ѡ������
 * 
 * <pre>
 *  ѡ�����������ð����˵��������ÿ�η������򶼽������������ҵ�ȫ�ֵ�iС��ʱ����¸�Ԫ��λ�ã�
 *  ������i��Ԫ�ؽ������Ӷ���֤�������յ�������������������˵��ѡ������ÿ��ѡ���Ķ���ȫ
 *  �ֵ�iС�ģ��������ǰi��Ԫ���ˡ�
 * </pre>
 * 
 * @author ����ΰ
 * @param <E>
 */
public class SelectSorter<E extends Comparable<E>> extends Sorter<E> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see algorithms.Sorter#sort(E[], int, int)
	 */
	@Override
	public void sort(E[] array, int from, int len) {
		int smallest;
		int j;
		for (int i = 0; i < len; i++) {
			smallest = i;
			j = i + from;
			for (; j < from + len; j++) {
				if (array[j].compareTo(array[smallest]) < 0) {
					smallest = j;
				}
			}
			swap(array, i, smallest);
		}
	}
}
