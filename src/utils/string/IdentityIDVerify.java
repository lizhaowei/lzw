package utils.string;


/**
 * <pre>
 *  ������ݺ��������������,��ʮ��λ���ֱ������һλ����У�������.����˳�������������
 *  Ϊ:��λ���ֵ�ַ��,��λ���ֳ���������,��λ����˳�����һλ����У���롣
 * 
 *  1����ַ��
 *  ��ʾ�������ס���������أ��С��졢�����������������룬�� GB/T 2260 �Ĺ涨ִ�С�
 * 
 *  2������������
 *  ��ʾ�������������ꡢ�¡��գ��� GB/T 7408 �Ĺ涨ִ�С��ꡢ�¡��մ���֮�䲻�÷ָ�����
 *  ����ĳ�˳�������Ϊ 1966��10��26�գ������������Ϊ 19661026��
 * 
 *  3��˳����
 *  ��ʾ��ͬһ��ַ������ʶ������Χ�ڣ���ͬ�ꡢͬ�¡�ͬ�ճ������˱ඨ��˳��ţ�˳�����
 *  ������������ԣ�ż��ǧ�����Ů�ԡ�
 * 
 *  4 ��У����
 *  У�������ISO 7064��1983��MOD 11-2 У����ϵͳ��
 * 
 *  ��1��ʮ��λ���ֱ������Ȩ��͹�ʽ
 *  S = Sum(Ai * Wi), i = 0, ... , 16 ���ȶ�ǰ17λ���ֵ�Ȩ���
 *  Ai:��ʾ��iλ���ϵ����֤��������ֵ
 *  Wi:��ʾ��iλ���ϵļ�Ȩ����
 *  Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
 * 
 *  ��2������ģ
 *  Y = mod(S, 11)
 * 
 *  ��3��ͨ��ģ�õ���Ӧ��У����
 *  Y:���� 0 1 2 3 4 5 6 7 8 9 10
 *  У����: 1 0 X 9 8 7 6 5 4 3 2
 * </pre>
 * 
 * @author ����ΰ Create: 2:19:41 PM Dec 27, 2007
 */
public class IdentityIDVerify {

	/**
	 * ����
	 */
	public static void main(String args[]) {
		String id = "130103198202010910";
		System.out.println(IdentityIDVerify.verify(id));
	}

	/**
	 * ��ʾ��iλ���ϵļ�Ȩ����<br>
	 * wi = 2(n-1)(mod 11)
	 */
	private static final int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
			5, 8, 4, 2, 1 };
	/** У���� */
	private static final int[] vi = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };
	/** ��iλ���ϵ����֤��������ֵ */
	private static int[] ai = new int[18];

	/**
	 * У�����֤����Ч��
	 * 
	 * @param identityid
	 *            ���֤����
	 * @return
	 */
	public static boolean verify(String identityid) {
		if (identityid.length() == 15) {
			identityid = modifyID(identityid);
		}
		if (identityid.length() != 18) {
			return false;
		}
		String verify = identityid.substring(17, 18);
		if (verify.equals(getVerify(identityid))) {
			return true;
		}
		return false;
	}

	// get verify
	private static String getVerify(String eightid) {
		int remaining = 0;
		if (eightid.length() == 18) {
			eightid = eightid.substring(0, 17);
		}

		if (eightid.length() == 17) {
			int sum = 0;
			for (int i = 0; i < 17; i++) {
				String k = eightid.substring(i, i + 1);
				ai[i] = Integer.parseInt(k);
			}
			for (int i = 0; i < 17; i++) {
				sum = sum + wi[i] * ai[i];
			}
			remaining = sum % 11;
		}

		return remaining == 2 ? "X" : String.valueOf(vi[remaining]);
	}

	// 15 update to 18
	private static String modifyID(String fifteenid) {
		String eightid = fifteenid.substring(0, 6);
		eightid = eightid + "19";
		eightid = eightid + fifteenid.substring(6, 15);
		eightid = eightid + getVerify(eightid);
		return eightid;
	}
}
