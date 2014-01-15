package lzw.secunity;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

/**
 * <p>
 * ��Ϊ����һ���ַ����������������ֽڱ�ʾ�ģ�ÿ���ֽ�ʵ�ʾ���һ����8λ�Ľ���������
 * ����Ϊһ��8λ����������������λ16�����ַ�����ʾ���������һ���ַ�����������λ16�����ַ�����ʾ��
 * ��DES�Ƕ�8λ�����������м��ܣ����ܡ�������DES���ܽ���ʱ�����԰Ѽ������õ�8λ����������ת����λ16���������б��棬���䡣<br>
 * <br>
 * <b>��һ���ַ������ܺ����õ�8λ����������ͨ������ʱ�ַ����ˣ���� ֱ�Ӱ������������õ�8λ��������ǿ��ת���ַ������������Ϣ��Ϊ��
 * ������ʧ�����ƽ���ʧ�ܡ�����Ҫ�����8λ��������ֱ����������ʽ ������������ͨ��������λʮ����������ʾ��</b><br>
 * <br>
 * ���巽����<br>
 * 1. ��һ���ַ���ת��8λ������������DES���ܣ��õ�8λ�������������ģ�<br>
 * 2. Ȼ��ѣ���1�����õ�����ת����λʮ�������ַ�����<br>
 * 3. ����ʱ���ѣ���2�����õ���λʮ�������ַ�����ת����8λ������ �������ģ�<br>
 * 4. �ѣ���3�����õ����ģ���DES���н��ܣ��õ�8λ����������ʽ�����ģ���ǿ��ת�����ַ�����<br>
 * </p>
 */
public class DESEncrypt {

	private static final String T = "0";
	private static final String DES = "DES";
	private Key key;
	private Cipher cipherEnc;
	private Cipher cipherDec;

	public DESEncrypt(String key) throws Exception {
		cipherEnc = Cipher.getInstance(DES);
		cipherDec = Cipher.getInstance(DES);
		generateKey(key);
	}

	/**
	 * ���ɼ�����Կ
	 * 
	 * @param key
	 */
	private void generateKey(String key) throws Exception {
		KeyGenerator generator = KeyGenerator.getInstance(DES);
		generator.init(new SecureRandom(key.getBytes()));
		// ������Կ
		this.key = generator.generateKey();

		// ��ʼ�����ܽ��ܹ���
		cipherEnc.init(Cipher.ENCRYPT_MODE, this.key);
		cipherDec.init(Cipher.DECRYPT_MODE, this.key);

		generator = null;
	}

	/**
	 * ����
	 * 
	 * @param src
	 *            Դ����
	 * @return ���ܺ�����
	 */
	public String encrypt(String src) throws Exception {
		return byte2hex(encrypt(src.getBytes()));
	}

	/**
	 * ����
	 * 
	 * @param encryptedSrc
	 *            ����
	 * @return ����
	 */
	public String decrypt(String encryptedSrc) throws Exception {
		return new String(decrypt(hex2byte(encryptedSrc.getBytes())));
	}

	/**
	 * ������byte[]��������,byte[]�������
	 * 
	 * @param bytes
	 * @return
	 */
	private byte[] encrypt(byte[] bytes) throws Exception {
		// Cipher cipher = Cipher.getInstance(DES);
		// cipher.init(Cipher.ENCRYPT_MODE, key);
		// return cipher.doFinal(bytes);
		return cipherEnc.doFinal(bytes);
	}

	/**
	 * ������byte[]��������,��byte[]�������
	 * 
	 * @param bytes
	 * @return
	 */
	private byte[] decrypt(byte[] bytes) throws Exception {
		// Cipher cipher = Cipher.getInstance(DES);
		// cipher.init(Cipher.DECRYPT_MODE, key);
		// return cipher.doFinal(bytes);
		return cipherDec.doFinal(bytes);
	}

	/**
	 * ������ת�ַ���
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) { // һ���ֽڵ�����
		// ת��16�����ַ���
		String stmp = "";
		StringBuilder info = new StringBuilder();
		final int N = 0XFF;
		for (int n = 0; n < b.length; n++) {
			// ����ת��ʮ�����Ʊ�ʾ
			stmp = (Integer.toHexString(b[n] & N));
			if (stmp.length() == 1) {
				info.append(T).append(stmp);
			} else {
				info.append(stmp);
			}
		}
		return info.toString().toUpperCase();
	}

	private static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("Argument is not even.");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			// ��λһ�飬��ʾһ���ֽ�,��������ʾ��16�����ַ�������ԭ��һ�������ֽ�
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	public static void main(String[] args) {
		long a = System.currentTimeMillis();
		String src = "096debab87e68ed1095cf9c128260515<?xml version=\"1.0\" encoding=\"utf-8\"?><message><header><agentID>3022</agentID><timestamp>2011-12-14 19:27:37</timestamp><tradeType>1001</tradeType></header><body><lotteryRequest><agentOrders><agentOrder ID=\"3022201112141000928975\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"10.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889331\" orderTimes=\"5\" payMoney=\"10.0\" playMethod=\"101\" >6,2,1</orderItem></agentOrder><agentOrder ID=\"3022201112141000928978\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"2.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889334\" orderTimes=\"1\" payMoney=\"2.0\" playMethod=\"101\" >5,5,4</orderItem></agentOrder><agentOrder ID=\"3022201112141000928979\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"4.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889335\" orderTimes=\"1\" payMoney=\"4.0\" playMethod=\"102\" >68,4,2</orderItem></agentOrder><agentOrder ID=\"3022201112141000928987\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"6.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889343\" orderTimes=\"3\" payMoney=\"6.0\" playMethod=\"101\" >7,8,1</orderItem></agentOrder><agentOrder ID=\"3022201112141000928988\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"4.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889344\" orderTimes=\"2\" payMoney=\"4.0\" playMethod=\"801\" >6,7,8</orderItem></agentOrder><agentOrder ID=\"3022201112141000928989\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"2.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889345\" orderTimes=\"1\" payMoney=\"2.0\" playMethod=\"101\" >6,1,8</orderItem></agentOrder><agentOrder ID=\"3022201112141000928990\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"4.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889346\" orderTimes=\"2\" payMoney=\"4.0\" playMethod=\"101\" >8,8,7</orderItem></agentOrder><agentOrder ID=\"3022201112141000929147\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"2.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889503\" orderTimes=\"1\" payMoney=\"2.0\" playMethod=\"101\" >4,6,3</orderItem></agentOrder><agentOrder ID=\"3022201112141000929148\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"2.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889504\" orderTimes=\"1\" payMoney=\"2.0\" playMethod=\"101\" >2,5,6</orderItem></agentOrder><agentOrder ID=\"3022201112141000929149\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"2.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889505\" orderTimes=\"1\" payMoney=\"2.0\" playMethod=\"801\" >2,3,8</orderItem></agentOrder><agentOrder ID=\"3022201112141000929150\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"4.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889506\" orderTimes=\"1\" payMoney=\"4.0\" playMethod=\"101\" >2,8,6;6,4,6</orderItem></agentOrder><agentOrder ID=\"3022201112141000929152\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"10.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889508\" orderTimes=\"5\" payMoney=\"10.0\" playMethod=\"101\" >2,1,9</orderItem></agentOrder><agentOrder ID=\"3022201112141000929153\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"2.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889509\" orderTimes=\"1\" payMoney=\"2.0\" playMethod=\"101\" >3,0,8</orderItem></agentOrder><agentOrder ID=\"3022201112141000929154\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"4.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889510\" orderTimes=\"2\" payMoney=\"4.0\" playMethod=\"101\" >3,6,5</orderItem></agentOrder></agentOrders></lotteryRequest></body></message>";
		src = "�����������˽���ǰ�������ѹ�������ǰ���������������ǰ���Ӱ��������ǰ������ǰ��ù��࣬�ൽ���԰Ѻ��������ģ����ǡ��簮������ʵÿ�ֶ��ﶼ�ᰮ�������ں���С��ʱ�򣬶����簮�������Ҵ�ǰ�п�ʯ��������˴��죬����֪�����������������������ܿ���������裬��վ�ڳ���һ�ߣ��������ڵ�С�棬�����������޵����������Բ����˻�Ӳ��������ǣ�������������������һ�죬�ִ��š�������⡹�ؼң�ȴ���ֺ���ȫ�����ˡ���ʱ��ֻ������վ�ڳ��߷�㶣���֪��������ʲô��ζ��С����ˣ���Ϊ���Ӳ�ˣ�������Ҫ��ĸ�簮��";
		String key = "000000";

		try {
			DESEncrypt des = new DESEncrypt(key);

			System.out.println("src=\t" + src);
			String encSrc = des.encrypt(src);
			System.out.println("encSrc=" + encSrc);

			String decSrc = des.decrypt(encSrc);
			System.out.println("decSrc=\t" + decSrc);
			long b = System.currentTimeMillis() - a;
			System.out.println("time=" + b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
