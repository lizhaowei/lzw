package lzw.test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �����̳߳ء���������
 * 
 * @author ����ΰ Create: 1:55:17 PM Mar 12, 2008
 */
public class TestTransactionID {

	public static void main(String[] args) {
		test();
	}

	static final int THERAD_SIZE = 10; // �̳߳ش�С

	static void test() {
		ExecutorService pool = Executors.newFixedThreadPool(THERAD_SIZE);
		CreatIDTask fet = null;
		int size = 1000;
		for (int i = 0; i < size; i++) {
			fet = new CreatIDTask();
			pool.submit(fet);
		}
		pool.shutdown();
	}

	/**
	 * @return �������к�
	 */
	public synchronized static String getTransactionID() {
		String sChars = "0123456789";
		// String sPswd = "";
		StringBuffer id = new StringBuffer();
		int iIndex = 0;
		// int iseq = 0;
		Random x = new Random(); // default seed is time in milliseconds
		for (int i = 0; i < 18; i++) { // generator six bit random astring;
			iIndex = x.nextInt(9) + 1;
			// sPswd = sPswd + sChars.substring(iIndex, (iIndex + 1));
			id.append(sChars.substring(iIndex, (iIndex + 1)));
		}
		// if (iseq >= 999) {
		// iseq = 0;
		// } else {
		// iseq++;
		// }
		// System.out.println(iseq);
		// String sSeq = Integer.toString(iseq);
		// if (sSeq.length() == 1) {
		// sSeq = "00" + sSeq;
		// } else if (sSeq.length() == 2) {
		// sSeq = "0" + sSeq;
		// }
		// return sPswd + sSeq;
		return id.toString();
	}
}

class CreatIDTask implements Runnable {
	public void run() {
		System.out.println("ID=" + TestTransactionID.getTransactionID());
	}
}
