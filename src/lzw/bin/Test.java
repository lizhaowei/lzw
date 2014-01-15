package lzw.bin;

public class Test {

	public static void main(String[] args){
		a();
	}

	static int n = 100;

	static void a(){
		String r = "";
		// r = Integer.toBinaryString(-1);
		// System.out.println(r);
		// r = Integer.toBinaryString(2);
		// System.out.println(r);
		//		
		// System.out.println(6 ^ 2);
		// System.out.println(Integer.toBinaryString(6 ^ 2));
		// for (int i = 0; i < n; i++) {
		// String r = Integer.toBinaryString(i);
		// System.out.println(r);
		// }
		
		r = Long.toBinaryString(-1L);
		System.out.println(r.length());
		r = Integer.toBinaryString(-1);
		System.out.println(r.length());
		System.out.println(Byte.MAX_VALUE);
		System.out.println(Short.MAX_VALUE);
		System.out.println(Integer.MAX_VALUE);
	}
}
