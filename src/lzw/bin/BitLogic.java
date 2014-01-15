package lzw.bin;

public class BitLogic {
	public static void main(String args[]){
		String binary[] = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};

		int a = 3; // 0 + 2 + 1 or 0011 in binary
		int b = 6; // 4 + 2 + 0 or 0110 in binary
		int c = a | b;
		int d = a & b;
		int e = a ^ b;
		int f = (~a & b) | (a & ~b);
		int g = ~a & 0x0f;

		System.out.println(" a = " + binary[a]);
		System.out.println(" b = " + binary[b]);
		System.out.println(" a|b = " + binary[c]);
		System.out.println(" a&b = " + binary[d]);
		System.out.println(" a^b = " + binary[e]);
		System.out.println("~a&b|a&~b = " + binary[f]);
		System.out.println("~a = " + binary[g]);

		/*
		 * 变量a与b对应位的组合代表了二进制数所有的 4 种组合模式：0-0，0-1，1-0 ，和1-1 。“|”运算符和“&”运
		 * 算符分别对变量a与b各个对应位的运算得到了变量c和变量d的值。对变量e和f的赋值说明了“^”运算符的功能。 字符串数组binary 代表了0到15
		 * 对应的二进制的值。在本例中，数组各元素的排列顺序显示了变量对应值的
		 * 二进制代码。数组之所以这样构造是因为变量的值n对应的二进制代码可以被正确的存储在数组对应元素binary[n]
		 * 中。例如变量a的值为3，则它的二进制代码对应地存储在数组元素binary[3] 中。~a的值与数字0x0f （对应二进制为0000 1111 ）
		 * 进行按位与运算的目的是减小~a的值，保证变量g的结果小于16。因此该程序的运行结果可以用数组binary 对应的元素来表示。
		 */
	}
}
