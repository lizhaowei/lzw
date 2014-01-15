package utils.base64;

import java.io.IOException;

public class BASE64Encoder {

	/**
	 * 测试
	 */
	public static void main(String[] args) {
		test();
	}

	static void test() {
		String s = "韦小宝道：“这些刺客当真不是你派去的？”";
		String t = encode(s.getBytes());
		System.out.println(t);
		sun.misc.BASE64Encoder sunEncode = new sun.misc.BASE64Encoder();
		String tt = sunEncode.encode(s.getBytes());
		System.out.println(tt);
		sun.misc.BASE64Decoder sunDecode = new sun.misc.BASE64Decoder();
		try {
			byte[] buf = sunDecode.decodeBuffer(t);
			s = new String(buf);
			System.out.println(s);

			buf = sunDecode.decodeBuffer(tt);
			s = new String(buf);
			System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void testTime() {
		sun.misc.BASE64Encoder sunEncoder = new sun.misc.BASE64Encoder();
		byte[] testBytes = new byte[1024 * 1024 * 2];
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			sunEncoder.encode(testBytes);
		}
		System.out.println("[sun encoder]use time :"
				+ (System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			encode(testBytes);
		}
		System.out.println("[our encoder]use time :"
				+ (System.currentTimeMillis() - start));
	}

	private static final char[] CODE_TABLE = { 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', '+', '/' };

	public static String encode(byte[] a) {
		int totalBits = a.length * 8;
		int nn = totalBits % 6;
		int curPos = 0;// process bits
		StringBuffer toReturn = new StringBuffer();
		while (curPos < totalBits) {
			int bytePos = curPos / 8;
			switch (curPos % 8) {
				case 0:
					toReturn.append(CODE_TABLE[(a[bytePos] & 0xfc) >> 2]);
					break;
				case 2:

					toReturn.append(CODE_TABLE[(a[bytePos] & 0x3f)]);
					break;
				case 4:
					if (bytePos == a.length - 1) {
						toReturn
								.append(CODE_TABLE[((a[bytePos] & 0x0f) << 2) & 0x3f]);
					} else {
						int pos = (((a[bytePos] & 0x0f) << 2) | ((a[bytePos + 1] & 0xc0) >> 6)) & 0x3f;
						toReturn.append(CODE_TABLE[pos]);
					}
					break;
				case 6:
					if (bytePos == a.length - 1) {
						toReturn
								.append(CODE_TABLE[((a[bytePos] & 0x03) << 4) & 0x3f]);
					} else {
						int pos = (((a[bytePos] & 0x03) << 4) | ((a[bytePos + 1] & 0xf0) >> 4)) & 0x3f;
						toReturn.append(CODE_TABLE[pos]);
					}
					break;
				default:
					// never hanppen
					break;
			}
			curPos += 6;
		}
		if (nn == 2) {
			toReturn.append("==");
		} else if (nn == 4) {
			toReturn.append("=");
		}
		return toReturn.toString();
	}
}