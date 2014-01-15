package lzw.test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// testChar();
		// test();
		// testTransactionID();
		// testPrime();
		a();
		// b();
	}

	static double rad(double d) {
		d = d * Math.PI / 180.0;
		// System.out.println("rad = " + d);
		return d;
	}

	static void b() {
		String a = "minx=106.38116455078125&miny=29.42595020372733&maxx=106.71514892578125&maxy=29.684246166853697";
		String b = "����,29.5549144, 106.548425";
		account(a, b);
	}

	static void account(String a, String b) {
		String[] c = a.split("&");
		String[] d = b.split(",");
		System.out.println(Arrays.toString(c));
		System.out.println(Arrays.toString(d));

		double x = Double.parseDouble(d[2]), y = Double.parseDouble(d[1]);
		System.out.println("x = " + x + ", y = " + y);
		double minx = Double.parseDouble(c[0].split("=")[1]);
		double miny = Double.parseDouble(c[1].split("=")[1]);
		double maxx = Double.parseDouble(c[2].split("=")[1]);
		double maxy = Double.parseDouble(c[3].split("=")[1]);
		System.out.println("minx = " + minx + ", miny = " + miny + ", maxx = "
				+ maxx + ", maxy = " + maxy);
		double subx = x - minx, suby = y - miny, addx = maxx - x, addy = maxy - y;
		System.out.println("subx = " + subx + ", suby = " + suby + ", addx = "
				+ addx + ", addy = " + addy);
		// System.out.println("rminx = " + rad(minx)/4 + ", rminy = " + rad(miny)/4
		// + ", rmaxx = " + rad(maxx)/4 + ", rmaxy = " + rad(maxy)/4);
		System.out.println("================================================");
	}

	static void a() {
		String s = "0,ȫ��;010,����;021,�Ϻ�;022,���;023,����;0311,ʯ��ׯ;0312,����;0314,�е�;0310,����;0315,��ɽ;0335,�ػʵ�;0317,����;0318,��ˮ;0316,�ȷ�;0319,��̨;0313,�żҿ�;0351,̫ԭ;0355,����;0352,��ͬ;0356,����;0354,����;0357,�ٷ�;0358,����;0349,˷��;0350,����;0359,�˳�;0353,��Ȫ;0471,���ͺ���;0472,��ͷ;0476,���;0477,������˹;0470,���ױ���;0475,ͨ��;0474,�����첼;0473,�ں�;0482,�˰���;024,����;0411,����;0412,��ɽ;0415,����;0413,��˳;0416,����;0417,Ӫ��;0414,��Ϫ;0428,����;0418,����;0429,��«��;0419,����;0427,�̽�;0410,����;0431,����;0432,����;0436,�׳�;0439,��ɽ;0437,��Դ;0434,��ƽ;0438,��ԭ;0435,ͨ��;0451,������;0459,����;0452,�������;0454,��ľ˹;0457,���˰���;0456,�ں�;0468,�׸�;0467,����;0453,ĵ����;0464,��̨��;0455,�绯;0469,˫Ѽɽ;0458,����;025,�Ͼ�;0512,����;0519,����;0518,���Ƹ�;0523,̩��;0510,����;0516,����;0514,����;0511,��;0517,����;0513,��ͨ;0527,��Ǩ;0515,�γ�;0571,����;0574,����;0573,����;0575,����;0577,����;0580,��ɽ;0572,����;0579,��;0578,��ˮ;0576,̨��;0551,�Ϸ�;0553,�ߺ�;0556,����;0552,����;0558,����;0565,����;0566,����;0550,����;0558,����;0559,��ɽ;0561,����;0554,����;0564,����;0555,��ɽ;0557,����;0562,ͭ��;0563,����;0591,����;0592,����;0595,Ȫ��;0597,����;0593,����;0599,��ƽ;0594,����;0598,����;0596,����;0791,�ϲ�;0797,����;0792,�Ž�;0798,������;0796,����;0799,Ƽ��;0793,����;0790,����;0795,�˴�;0701,ӥ̶;0531,����;0532,�ൺ;0631,����;0535,��̨;0536,Ϋ��;0538,̩��;0543,����;0534,����;0546,��Ӫ;0530,����;0537,����;0635,�ĳ�;0539,����;0634,����;0633,����;0533,�Ͳ�;0632,��ׯ;020,����;0755,����;0756,�麣;0769,��ݸ;0757,��ɽ;0752,����;0750,����;0760,��ɽ;0754,��ͷ;0759,տ��;0768,����;0762,��Դ;0663,����;0668,ï��;0753,÷��;0763,��Զ;0751,�ع�;0660,��β;0662,����;0766,�Ƹ�;0758,����;0898,����;0898,����;0771,����;0779,����;0771,����;0770,���Ǹ�;0773,����;0775,���;0778,�ӳ�;0774,����;0772,����;0772,����;0777,����;0775,����;0774,����;0371,֣��;0379,����;0378,����;0374,���;0372,����;0375,ƽ��ɽ;0392,�ױ�;0391,����;0391,��Դ;0395,���;0377,����;0393,���;0398,����Ͽ;0370,����;0373,����;0376,����;0396,פ���;0394,�ܿ�;027,�人;0710,�差;0719,ʮ��;0714,��ʯ;0711,����;0718,��ʩ;0713,�Ƹ�;0716,����;0724,����;0722,����;0717,�˲�;0728,����;0728,Ǳ��;0728,����;0712,Т��;0715,����;0731,��ɳ;0730,����;0732,��̶;0736,����;0735,����;0743,���;0734,����;0745,����;0738,¦��;0739,����;0737,����;0746,����;0733,����;0744,�żҽ�;028,�ɶ�;0816,����;0832,����;0827,����;0838,����;0818,����;0826,�㰲;0839,��Ԫ;0833,��ɽ;0830,����;0833,üɽ;0832,�ڽ�;0817,�ϳ�;0812,��֦��;0825,����;0831,�˱�;0835,�Ű�;0813,�Թ�;0851,����;0853,��˳;0857,�Ͻ�;0856,ͭ��;0852,����;0871,����;0877,��Ϫ;0878,����;0872,����;0873,���;0874,����;0691,��˫����;0870,��ͨ;0891,����;0892,�տ���;0983,ɽ��;029,����;0915,����;0917,����;0916,����;0914,����;0919,ͭ��;0913,μ��;0910,����;0911,�Ӱ�;0912,����;0931,����;0943,����;0932,����;0935,���;0937,��Ȫ;0939,¤��;0930,����;0933,ƽ��;0930,����;0935,����;0938,��ˮ;0936,��Ҵ;0971,����;0972,����;0970,����;0974,����;0951,����;0952,ʯ��ɽ;0953,����;0953,����;0991,��³ľ��;0994,����;0990,��������;0993,ʯ����;0995,��³��;0937,������;0888,����;0837,���Ӳ���Ǽ��������;0834,��ɽ����������;0898,��ˮ����������;0887,�������������;0875,��ɽ;0719,��ũ������;0570,������;0836,���β���������;0876,��ɽ׳������������;0692,�º���徰����������;";
		String[] a = s.split(";");
		String[] b;
		for (int i = 0; i < a.length; i++) {
			b = a[i].split(",");
			// System.out.print(b[1] + ",");
			System.out.println(b[1]);
		}
	}

	/** * �������� */
	static void testPrime() {
		int size = 1000;
		StringBuilder p = new StringBuilder();
		for (int i = 1; i <= size; i++) {
			if (isPrime(i))
				p.append(i + ", ");
		}
		// String s = p.substring(0, p.length() - 2);
		String s = p.substring(p.length() - 20, p.length() - 2);
		System.out.println("������" + s);
		long st = System.currentTimeMillis();
		System.out.println(isPrime(999983));
		long time = System.currentTimeMillis() - st;
		System.out.println("Time: " + time);
	}

	/**
	 * @param n
	 * @return true, ������false, ������
	 */
	static boolean isPrime(int n) {
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	static void testTransactionID() {
		int size = 100000;
		String id;
		Set<String> set = new HashSet<String>(size);
		for (int i = 0; i < size; i++) {
			id = getTransactionID();
			// System.out.println("id=" + id);
			set.add(id);
		}
		System.out.println(set.size());
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

	static void test() {
		String str = "�������磱��������67890";
		// System.out.println(str);
		// str = sbcTodbc(str);
		// System.out.println(str);
		try {
			str = toDBC(new String(str.getBytes("gb2312")));
			System.out.println(str);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static String toDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			System.out.println(Integer.toHexString((int) c[i]));
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	static String sbcTodbc(String str) {
		final int n = 65248;
		int c;
		int t;
		char[] codes = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			c = (int) str.charAt(i);
			if (256 < c) {
				t = c - n;
				if (0 <= t)
					codes[i] = (char) t;
				else
					codes[i] = (char) c;
			} else
				codes[i] = (char) c;
		}
		return String.valueOf(codes);
	}

	public static void testChar() {
		String s1 = "123";
		String s2 = "����";
		String s3 = "123����";
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		for (int i = 0; i < s1.length(); i++) {
			int j = s1.charAt(i);
			if (j > 256) {
				int temp = j - 65248;
				if (temp >= 0) {
					System.out.print((char) j + "-->:" + (char) temp);
				} else {
					System.out.print((char) j);
				}
			} else {
				System.out.print((char) j);
			}
			System.out.println();
		}
		System.out.println();

		for (int i = 0; i < s2.length(); i++) {
			int j = s2.charAt(i);
			if (j > 256) {
				int temp = j - 65248;
				if (temp >= 0) {
					System.out.print((char) j + "-->:" + (char) temp);
				} else {
					System.out.print((char) j);
				}
			} else {
				System.out.print((char) j);
			}
			System.out.println();
		}
		System.out.println();

		for (int i = 0; i < s3.length(); i++) {
			int j = s3.charAt(i);
			if (j > 256) {
				int temp = j - 65248;
				if (temp >= 0) {
					System.out.print((char) j + "-->:" + (char) temp);
				} else {
					System.out.print((char) j);
				}
			} else {
				System.out.print((char) j);
			}
			System.out.println();
		}
		System.out.println();

	}
}
