package utils.sort;

/**
 * ð������<br>
 * 
 * <pre>
 * ���������򵥵������㷨�ˣ��㷨˼����ÿ�δ�����ĩ�˿�ʼ�Ƚ�������Ԫ�أ��ѵ�iС��ð�ݵ�
 * ����ĵ�i��λ�á�i��0һֱ��N-1�Ӷ�������򡣣���ȻҲ���Դ����鿪ʼ�˿�ʼ�Ƚ�������Ԫ�أ�
 * �ѵ�i���ð�ݵ�����ĵ�N-i��λ�á�i��0һֱ��N-1�Ӷ��������)
 * </pre>
 * 
 * @author ����ΰ
 * @param <E>
 */
public class BubbleSorter<E extends Comparable<E>> extends Sorter<E> {

	private static boolean DWON = true;

	private final void bubble_down(E[] array, int from, int len) {
		for (int i = from; i < from + len; i++) {
			for (int j = from + len - 1; j > i; j--) {
				if (array[j].compareTo(array[j - 1]) < 0) {
					swap(array, j - 1, j);
				}
			}
		}
	}

	private final void bubble_up(E[] array, int from, int len) {
		for (int i = from + len - 1; i >= from; i--) {
			for (int j = from; j < i; j++) {
				if (array[j].compareTo(array[j + 1]) > 0) {
					swap(array, j, j + 1);
				}
			}
		}
	}

	@Override
	public void sort(E[] array, int from, int len) {
		if (DWON) {
			bubble_down(array, from, len);
		} else {
			bubble_up(array, from, len);
		}
	}
}
