package lzw.nio.ibm;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class UseScatterGather{
	static private final int firstHeaderLength = 2;
	static private final int secondHeaderLength = 4;
	static private final int bodyLength = 6;

	static public void main(String args[]) throws Exception{
		// if (args.length != 1) {
		// System.err.println("Usage: java UseScatterGather port");
		// System.exit(1);
		// }

		int port = 9009;// Integer.parseInt(args[0]);

		ServerSocketChannel ssc = ServerSocketChannel.open();
		InetSocketAddress address = new InetSocketAddress(port);
		System.out.println(address);
		ssc.socket().bind(address);

		int messageLength = firstHeaderLength + secondHeaderLength + bodyLength;
		ByteBuffer buffers[] = new ByteBuffer[3];
		buffers[0] = ByteBuffer.allocate(firstHeaderLength);
		buffers[1] = ByteBuffer.allocate(secondHeaderLength);
		buffers[2] = ByteBuffer.allocate(bodyLength);

		SocketChannel sc = ssc.accept();
		while (true) {
			// Scatter-read into buffers
			int bytesRead = 0;
			while (bytesRead < messageLength) {
				long r = sc.read(buffers);
				bytesRead += r;

				System.out.println("r " + r);
				StringBuilder info=null;
				for (int i = 0; i < buffers.length; ++i) {
					info=new StringBuilder();
					ByteBuffer bb = buffers[i];
					
					String s = new String(bb.array());
					info.append(s);
					
					System.out.println("b " + i + " " + bb.position() + " " + bb.limit());
				}
				System.out.println(info);
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
			}

			// Process message here
			// Flip buffers
			for (int i = 0; i < buffers.length; ++i) {
				ByteBuffer bb = buffers[i];
				bb.flip();
			}

			// Scatter-write back out
			long bytesWritten = 0;
			while (bytesWritten < messageLength) {
				long r = sc.write(buffers);
				bytesWritten += r;
			}

			// Clear buffers
			for (int i = 0; i < buffers.length; ++i) {
				ByteBuffer bb = buffers[i];
				bb.clear();
			}

			System.out.println(bytesRead + " " + bytesWritten + " " + messageLength);
			System.out.println("##################################################");
		}
	}
}
