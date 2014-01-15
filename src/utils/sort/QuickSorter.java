package utils.sort;

/**
 * ��������
 * 
 * <pre>
 *  ����������Ŀǰʹ�ÿ�����㷺�������㷨�ˡ�
 *  һ������²��裺
 *  1��ѡ��һ����ŦԪ�أ��кܶ�ѡ�����ҵ�ʵ�������ȥ�м�Ԫ�صļ򵥷�����
 *  2��ʹ�ø���ŦԪ�طָ����飬ʹ�ñȸ�Ԫ��С��Ԫ����������ߣ�����������ұߡ�������ŦԪ��
 *     ���ں��ʵ�λ�á�
 *  3��������ŦԪ�����ȷ����λ�ã�������ֳ������֣���ߵģ��ұߵģ���ŦԪ���Լ�������ߵģ�
 *     �ұߵķֱ�ݹ���ÿ��������㷨���ɡ�
 *  ��������ĺ������ڷָ��㷨��Ҳ����˵�����м��ɵĲ��֡�
 * </pre>
 * 
 * @author ����ΰ
 * @param <E>
 */
public class QuickSorter<E extends Comparable<E>> extends Sorter<E> {

	/*
	 * @see algorithms.Sorter#sort(E[], int, int)
	 */
	@Override
	public void sort(E[] array, int left, int len){
		q_sort(array, left, left + len - 1);
	}

	private final void q_sort(E[] array, int left, int right){
		if (right - left < 1)
			return;
		int pivot = selectPivot(left, right);

		pivot = partion(array, left, right, pivot);

		q_sort(array, left, pivot - 1);
		q_sort(array, pivot + 1, right);
	}

	private int partion(E[] array, int left, int right, int pivot){
		E tmp = array[pivot];
		array[pivot] = array[right];// now to's position is available

		while (left != right) {
			while (left < right && array[left].compareTo(tmp) <= 0) {
				left++;
			}
			if (left < right) {
				array[right] = array[left];// now from's position is available
				right--;
			}
			while (left < right && array[right].compareTo(tmp) >= 0) {
				right--;
			}
			if (left < right) {
				array[left] = array[right];// now to's position is available
				// now
				left++;
			}
		}
		array[left] = tmp;
		return left;
	}

	// private int selectPivot(int left, int right){
	// // return (left + right) / 2;
	// return (left + right) >> 1;
	// }
}