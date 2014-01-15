package lzw.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class AsyncServer implements Runnable {

	private ByteBuffer r_buff = ByteBuffer.allocate(1024);
	private ByteBuffer w_buff = ByteBuffer.allocate(1024);
	private static int port = 7890;

	public AsyncServer() {
		new Thread(this).start();
	}

	public void run() {
		try {
			// ����һ��������
			ServerSocketChannel ssc = ServerSocketChannel.open();
			// ����������Ϊ�첽��ʽ
			ssc.configureBlocking(false);
			// ����һ���źż�����
			Selector s = Selector.open();
			// �����˰󶨵�һ���˿�
			ssc.socket().bind(new InetSocketAddress(port));
			// ������������ѡ���첽�ź�OP_ACCEPT
			ssc.register(s, SelectionKey.OP_ACCEPT);

			System.out.println("echo server has been set up ......");

			while (true) {
				int n = s.select();
				if (n == 0) {// û��ָ����I/O�¼�����
					continue;
				}
				Iterator it = s.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey key = (SelectionKey) it.next();
					if (key.isAcceptable()) {// �������źŴ���
						ServerSocketChannel server = (ServerSocketChannel) key
								.channel();
						// ����һ���µ�����
						SocketChannel sc = server.accept();
						sc.configureBlocking(false);
						// ���ø�socket���첽�ź�OP_READ:��socket�ɶ�ʱ
						// ��������DealwithData();
						sc.register(s, SelectionKey.OP_READ);
					}
					if (key.isReadable()) {// ĳsocket�ɶ��ź�
						DealwithData(key);
					}
					it.remove();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void DealwithData(SelectionKey key) throws IOException {
		// ��key��ȡָ��socketchannel������
		SocketChannel sc = (SocketChannel) key.channel();
		r_buff.clear();
		// ��ȡ���ݵ�r_buff
		while (sc.read(r_buff) > 0)
			;
		// ȷ��r_buff�ɶ�
		r_buff.flip();

		w_buff.clear();
		// ��r_buff���ݿ���w_buff
		w_buff.put(r_buff);
		w_buff.flip();
		// �����ݷ��ظ��ͻ���
		EchoToClient(sc);

		w_buff.clear();
		r_buff.clear();
	}

	public void EchoToClient(SocketChannel sc) throws IOException {
		while (w_buff.hasRemaining())
			sc.write(w_buff);
	}

	public static void main(String args[]) {
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		new AsyncServer();
	}
}
