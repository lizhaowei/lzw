package lzw.bin;

public class MultByTwo {
	/**
	 * num 指定要移位值value 移动的位数。也就是，左移运算符<<使指定值的所有位都左移num位。每左移一个位，高阶位都被移出（并且丢弃），并用0填充右边。这意味着当左移的运算数是int
	 * 类型时，每移动1位它的第31位就要被移出并且丢弃；当左移的运算数是long 类型时，每移动1位它的第63位就要被移出并且丢弃。
	 */
	public static void main(String args[]){
		int i;
		int num = 0xFFFFFFE;

		for (i = 0; i < 4; i++) {
			num = num << 1;
			System.out.println(num);
		}
	}
}
