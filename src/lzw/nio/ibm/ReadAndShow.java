package lzw.nio.ibm;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadAndShow{
	static public void main(String args[]) throws Exception{
		String infile = "D:/temp/test.txt";
		FileInputStream fin = new FileInputStream(infile);
		FileChannel fc = fin.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		fc.read(buffer);
		buffer.flip();
		int i = 0;
		while (buffer.remaining() > 0) {
			byte b = buffer.get();
			System.out.println("Character " + i + ": " + ((char)b));
			
//			byte c = buffer.get();
//			String s = new String(new byte[]{b, c});
//			System.out.println("Character " + i + ": " + s);
			i++;
		}
		fin.close();
	}
}
