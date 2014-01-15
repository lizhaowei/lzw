package utils.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class AsyncClient{
	private SocketChannel sc;
	private final int MAX_LENGTH = 1024;
	private ByteBuffer r_buff = ByteBuffer.allocate(MAX_LENGTH);
	// private ByteBuffer w_buff = ByteBuffer.allocate(MAX_LENGTH);
	private static String host;
	private static int port = 7890;

	public AsyncClient(){
		try {
			InetSocketAddress addr = new InetSocketAddress(host, port);
			// ����һ��socketchannel
			sc = SocketChannel.open();

			// ���ӵ�server
			sc.connect(addr);
			while (!sc.finishConnect());
			System.out.println("connection has been established!...");

			while (true) {
				// // ������Ϣ
				// String echo = null;
				// try
				// {
				// System.err.println("Enter msg you'd like to send: ");
				// BufferedReader br = new BufferedReader(
				// new InputStreamReader(System.in));
				// // ���������Ϣ
				// echo = br.readLine();
				// echo = "send [test] to server.";
				//
				// // �ѻ�����Ϣ����w_buff��
				// w_buff.clear();
				// w_buff.put(echo.getBytes());
				// w_buff.flip();
				// }
				// catch (IOException ioe)
				// {
				// System.err.println("sth. is wrong with br.readline() ");
				// }
				//
				// // ������Ϣ
				// while (w_buff.hasRemaining())
				// sc.write(w_buff);
				// w_buff.clear();

				// �������״̬
				Rec();
				// ���1��
				// Thread.currentThread().sleep(1000);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		// catch (InterruptedException ie)
		// {
		// ie.printStackTrace();
		// }
	}

	// //////////
	// ��ȡserver�˷��ص����ݣ�����ʾ
	public void Rec() throws IOException{
		int count;
		r_buff.clear();
		count = sc.read(r_buff);
		r_buff.flip();
		byte[] temp = new byte[r_buff.limit()];
		r_buff.get(temp);
		System.out.println("reply is [" + count + "] long, and content is: " + new String(temp));
	}

	public static void main(String args[]){
		// if (args.length < 1)
		// {// ����������������IP��ַ
		// try
		// {
		// System.err.println("Enter host name: ");
		// BufferedReader br = new BufferedReader(new InputStreamReader(
		// System.in));
		// host = br.readLine();
		// }
		// catch (IOException ioe)
		// {
		// System.err.println("sth. is wrong with br.readline() ");
		// }
		// }
		// else if (args.length == 1)
		// {
		// host = args[0];
		// }
		// else if (args.length > 1)
		// {
		// host = args[0];
		// port = Integer.parseInt(args[1]);
		// }
		host = "127.0.0.1";
		port = 6000;
		new AsyncClient();
	}
}
