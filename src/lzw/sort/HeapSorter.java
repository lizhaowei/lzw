package lzw.sort;

/**
 * ������
 * 
 * <pre>
 *  ����һ����ȫ��������һ��ʹ��������ʵ�֡�
 *  ����Ҫ�����ֺ��Ĳ�����
 *  1����ָ���ڵ����ϵ���(shiftUp)
 *  2����ָ���ڵ����µ���(shiftDown)
 *  ���ѣ��Լ�ɾ���Ѷ��ڵ�ʹ��shiftDwon,���ڲ���ڵ�ʱһ�������ֲ���һ��ʹ�á�
 *  ������������ֵ����ʵ�֣���i�δӶѶ��Ƴ����ֵ�ŵ�����ĵ�����i��λ�ã�Ȼ��shiftDown��������i+1��λ��,һ��ִ��N�˵��������������
 *  ��Ȼ��������Ҳ��һ��ѡ���Ե�����ÿ��ѡ���i���Ԫ�ء�
 * </pre>
 * 
 * @author ����ΰ Create: 2007-12-17
 */
public class HeapSorter<E extends Comparable<E>> extends Sorter<E> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see algorithms.Sorter#sort(E[], int, int)
	 */
	@Override
	public void sort(E[] array, int left, int len){
		build_heap(array, left, len);
		for (int i = 0; i < len; i++) {
			// swap max value to the (len-i)-th position
			swap(array, left, left + len - 1 - i);
			shift_down(array, left, len - 1 - i, 0);// always shiftDown from 0
		}
	}

	private final void build_heap(E[] array, int left, int len){
		// we start from (len-1)/2, because branch's node +1=leaf's node, and
		// all leaf node is already a heap
		int pos = (len - 1) / 2;
		for (int i = pos; i >= 0; i--) {
			shift_down(array, left, len, i);
		}

	}

	private final void shift_down(E[] array, int left, int len, int pos){
		E tmp = array[left + pos];
		int index = pos * 2 + 1;// use left child
		while (index < len) { // until no child
			// right child is bigger
			if (index + 1 < len && array[left + index].compareTo(array[left + index + 1]) < 0) {
				index += 1;// switch to right child
			}
			if (tmp.compareTo(array[left + index]) < 0) {
				array[left + pos] = array[left + index];
				pos = index;
				index = pos * 2 + 1;

			} else
				break;
		}
		array[left + pos] = tmp;

	}
}
