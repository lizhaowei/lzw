package lzw.bin;

/**
 * <pre>
 * value &lt;&lt; num 
 * 这里，num 指定要移位值value 移动的位数。也就是，左移运算符 &lt;&lt; 使指定值的所有位都左移num位。
 * 每左移一个位，高阶位都被移出（并且丢弃），并用0填充右边。这意味着当左移的运算数是int 类型时，每移动1位它的第
 * 31位就要被移出并且丢弃；当左移的运算数是long 类型时，每移动1位它的第63位就要被移出并且丢弃。
 * 
 * 在对byte 和short类型的值进行移位运算时，你必须小心。因为你知道Java 在对表达式求值时，将自动把这些类型扩大为
 * int 型，而且，表达式的值也是int 型。对byte 和short类型的值进行移位运算的结果是 int 型，而且如果左移不超过
 * 31位，原来对应各位的值也不会丢弃。但是，如果你对一个负的byte 或者short类型的值进行移位运算，它被扩大为 int 
 * 型后，它的符号也被扩展。这样，整数值结果的高位就会被1填充。因此，为了得到正确的结果，你就要舍弃得到结果的高位。
 * 这样做的最简单办法是将结果转换为 byte 型。
 * </pre>
 * 
 * @author 李赵伟 Create: 2:44:01 PM Jul 22, 2009
 */
public class ByteShift {

	public static void main(String args[]){
		byte a = 64, b;
		int i;

		i = a << 2;
		b = (byte)(a << 2);

		System.out.println("Original value of a: " + a);
		System.out.println("i and b: " + i + " " + b);
		/*
		 * 因变量a在赋值表达式中，故被扩大为int 型，64（0100 0000 ）被左移两次生成值256 （10000 0000 ）
		 * 被赋给变量i。然而，经过左移后，变量b中惟一的1被移出，低位全部成了0，因此b的值也变成了0。
		 */
	}
}
