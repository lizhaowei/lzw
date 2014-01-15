package lzw.nio.ibm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FastCopyFile{
	static public void main(String args[]) throws Exception{
		long sTime = System.currentTimeMillis();
		String infile = "D:/temp/test.txt";
		String outfile = "D:/temp/ibm.txt";

		FileInputStream fin = new FileInputStream(infile);
		FileOutputStream fout = new FileOutputStream(outfile);

		FileChannel fcin = fin.getChannel();
		FileChannel fcout = fout.getChannel();

		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
		while (true) {
			buffer.clear();
			int r = fcin.read(buffer);
			if (r == -1) {
				break;
			}
			buffer.flip();
			fcout.write(buffer);
		}
		fcout.close();
		fcin.close();
		fout.close();
		fin.close();
		long eTime = System.currentTimeMillis() - sTime;
		System.out.println("Time: " + eTime);
	}
}
