package utils.sort;

/**
 * <pre>
 * �����㷨�ķ������£�
 * 1.��������ֱ�Ӳ��������۰��������ϣ�����򣩣�
 * 2.��������ð�������򡢿������򣩣�
 * 3.ѡ������ֱ��ѡ�����򡢶����򣩣�
 * 4.�鲢����
 * 5.��������
 * 
 * �������򷽷���ѡ��
 * (1)��n��С(��n��50)���ɲ���ֱ�Ӳ����ֱ��ѡ������
 *    ����¼��ģ��Сʱ��ֱ�Ӳ�������Ϻã�������Ϊֱ��ѡ���ƶ��ļ�¼������ֱ�Ӳ��ˣ�Ӧѡֱ��ѡ������Ϊ�ˡ�
 * (2)���ļ���ʼ״̬��������(ָ����)����Ӧѡ��ֱ�Ӳ��ˡ�ð�ݻ�����Ŀ�������Ϊ�ˣ�
 * (3)��n�ϴ���Ӧ����ʱ�临�Ӷ�ΪO(nlgn)�����򷽷����������򡢶������鲢����
 * </pre>
 * 
 * @author ����ΰ �������ڣ�2007-12-13
 * @param <E>
 */
public abstract class Sorter<E extends Comparable<E>> {

	public abstract void sort(E[] array, int left, int len);

	public final void sort(E[] array){
		sort(array, 0, array.length);
	}

	protected final void swap(E[] array, int left, int right){
		E tmp = array[left];
		array[left] = array[right];
		array[right] = tmp;
	}

	protected int selectPivot(int left, int right){
		// return (left + right) / 2;
		return (left + right) >>> 1;
	}
}
