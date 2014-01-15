package lzw.others;

/**
 * Java程序内存占用量计算
 * 
 * @author 李赵伟 Create: 10:40:06 AM Jul 22, 2009
 */
public class Memory {
	private final static int _SIZE = 500;

	/**
	 * 计算两次之差得到消耗的内存量。
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		Object[] array = new Object[_SIZE];
		Runtime.getRuntime().gc();
		long start = Runtime.getRuntime().totalMemory();
		System.out.println("Total Memory: " + start + " bytes");
		for (int i = 0; i < _SIZE; i++) {
			array[i] = new Object();
		}
		Runtime.getRuntime().gc();
		long end = Runtime.getRuntime().totalMemory();
		long difference = (start - end) / _SIZE;
		System.out.println(difference + " bytes used per object on average");
	}

	// private static final Runtime s_runtime = Runtime.getRuntime();
	//
	// private static long usedMemory(){
	// return s_runtime.totalMemory() - s_runtime.freeMemory();
	// }
	//
	// public static void runGC() throws Exception{
	// long usedMem1 = usedMemory(), usedMem2 = Long.MAX_VALUE;
	// for (int i = 0; (usedMem1 < usedMem2) && (i < 500); ++i) {
	// s_runtime.runFinalization();
	// s_runtime.gc();
	// Thread.currentThread().yield();
	// usedMem2 = usedMem1;
	// usedMem1 = usedMemory();
	// }
	// }
}
