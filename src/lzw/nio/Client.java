package lzw.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client{

	static void connect(int port) throws Exception{
		SocketChannel sc = SocketChannel.open();
		SocketAddress sa = new InetSocketAddress("172.17.37.21", port);
		sc.connect(sa);
		System.out.println("Port=" + port);
		String s;
		ByteBuffer bW;
		ByteBuffer bR = ByteBuffer.allocate(1024 * 1000);
		StringBuilder info;
		while (true) {
			s = read();
			if (null == s || 0 == s.trim().length())
				break;

			bW = ByteBuffer.wrap(s.getBytes());
			sc.write(bW);

			System.out.println("#> " + s);
			info = new StringBuilder();
			while (sc.read(bR) != -1) {
				bR.flip();
				info.append(new String(bR.array()));
				bR.clear();
			}
			System.out.println("$> " + info);
		}

		sc.finishConnect();
		sc.close();
	}

	static String read(){
		Scanner scanner = new Scanner(System.in);
		String s = "";
		s = scanner.next();
		// System.out.println("#> " + s);
		scanner.close();
		return s;
	}

	public static void main(String args[]) throws Exception{
		// if (args.length != 1) {
		// System.err.println("java Client PORT");
		// System.exit(-1);
		// }
		args = new String[]{"9001"};
		connect(Integer.parseInt(args[0]));
		connect(Integer.parseInt("9002"));
		connect(Integer.parseInt("9003"));
		connect(Integer.parseInt("9004"));
		// read();
	}
}
