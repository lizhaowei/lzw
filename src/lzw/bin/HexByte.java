package lzw.bin;

/**
 * 由于符号位扩展（保留符号位）每次都会在高位补1，因此-1右移的结果总是–1。有时你不希望在右移时保留符号。例如，下面的例子将一个byte
 * 型的值转换为用十六 <br>
 * 进制表示。注意右移后的值与0x0f进行按位与运算，这样可以舍弃任何的符号位扩展，以便得到的值可以作为定义数组的下标，从而得到对应数组元素代表的十六进制字符。
 * 
 * @author 李赵伟 Create: 2:54:13 PM Jul 22, 2009
 */
public class HexByte {
	public static void main(String args[]){

		char hex[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		byte b = (byte)0xF1;

		System.out.println("b = 0x" + hex[(b >> 4) & 0x0f] + hex[b & 0x0f]);
	}
}
