package utils.mail;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * �ʼ����ͳ���<br>
 * <br>
 * <code>
 * <pre>
 * ʹ��ʾ����
 * String host = &quot;smtp.sina.com&quot;;
 * String username = &quot;leezhaowei&quot;;
 * String password = &quot;leezhaowei&quot;;
 * String from = &quot;&quot;;
 * String to = &quot;leezhaowei@sina.com&quot;;
 * // String cc = null;
 * String subject = &quot;����&quot;;
 * String content = &quot;��ð���&quot;;
 * // String affix = &quot;d:/temp/temp.txt&quot;;
 * // String affixName = &quot;temp.txt&quot;;
 * boolean debug = false;
 * 
 * SendMail mail = null;
 * try {
 * 	try {
 * 		User user = new User(host, username, password, from);
 * 		MailAddress mailAddress = new MailAddress(to);
 * 		MailBody mailBody = new MailBody(subject, content);
 * 
 * 		mail = new SendMail(user);
 * 		// ���÷����˵�ַ���ռ��˵�ַ���ʼ�����
 * 		mail.setAddress(mailAddress);
 * 
 * 		// ����Ҫ���͸�����λ�úͱ���
 * 		mail.setMailBody(mailBody);
 * 		// ����smtp�������Լ�������ʺź�����
 * 		mail.send(debug);
 * 	} finally {
 * 		if (null != mail)
 * 			mail.close();
 * 	}
 * } catch (AddressException e) {
 * 	e.printStackTrace();
 * } catch (MessagingException e) {
 * 	e.printStackTrace();
 * }
 * </pre>
 * </code>
 * 
 * @author ����ΰ Create: 2007-12-19
 */
public class SendMail {

	private Session session;
	private Transport transport;

	private User user;
	private MailAddress mailAddress;
	private MailBody mailBody;
	private static final String MAIL_SMTP_HOST = "mail.smtp.host";
	private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	private static final String MAIL_TRAN_PROT = "mail.transport.protocol";

	public SendMail(User user) {
		this.user = user;
		init();
	}

	/**
	 * ��ʼ��<code> Session, Transport </code>
	 */
	private void init() {
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user.getUsername(), user
						.getPassword());
			}
		};

		Properties props = new Properties();
		props.put(MAIL_TRAN_PROT, "smtp");
		// ���÷����ʼ����ʼ������������ԣ�����ʹ�����׵�smtp��������
		props.put(MAIL_SMTP_HOST, user.getHost());
		// ��Ҫ������Ȩ��Ҳ�����л����������У�飬��������ͨ����֤��һ��Ҫ����һ����
		props.put(MAIL_SMTP_AUTH, "true");
		// �øո����úõ�props���󹹽�һ��session
		session = Session.getDefaultInstance(props, auth);
		try {
			// �����ʼ�
			transport = session.getTransport("smtp");
			// ���ӷ�����������
			transport.connect(user.getHost(), user.getUsername(), user
					.getPassword());
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println("�� " + user.getHost() + " �ɹ������Ự");
	}

	/**
	 * �����ռ��˵�ַ
	 * 
	 * @param mailAddress
	 */
	public void setAddress(MailAddress mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	 * �����ʼ�����
	 * 
	 * @param mailBody
	 */
	public void setMailBody(MailBody mailBody) {
		this.mailBody = mailBody;
	}

	/**
	 * �����ʼ�������
	 * 
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws IOException
	 */
	private Message createMessage() throws AddressException,
			MessagingException, IOException {
		// ��sessionΪ����������Ϣ����
		MimeMessage message = new MimeMessage(session);
		// ���ط����˵�ַ
		message.setFrom(new InternetAddress(user.getFrom()));
		message.setSentDate(new Date());
		// �����ռ��˵�ַ
		message.addRecipients(Message.RecipientType.TO, getAddress(mailAddress
				.getTo()));
		if (mailAddress.isHasCC())
			message.addRecipients(Message.RecipientType.CC,
					getAddress(mailAddress.getCc()));
		if (mailAddress.isHasBCC())
			message.addRecipients(Message.RecipientType.BCC,
					getAddress(mailAddress.getBcc()));

		// ���ر���
		message.setSubject(mailBody.getSubject(), "GB2312");

		if (mailBody.isContentFlag() || mailBody.isAffixFlag()) {
			// ��multipart����������ʼ��ĸ����������ݣ������ı����ݺ͸���
			Multipart multipart = new MimeMultipart();

			if (mailBody.isContentFlag()) {
				// �����ʼ����ı�����
				MimeBodyPart contentPart = new MimeBodyPart();
				if (mailBody.isMimeContent())
					contentPart.setContent(mailBody.getContent(),
							"text/html;charset=GB2312");
				else
					contentPart.setText(mailBody.getContent());
				
				multipart.addBodyPart(contentPart);
			}

			if (mailBody.isAffixFlag()) {
				// ��Ӹ���
				BodyPart affixBody = new MimeBodyPart();
				DataSource source = new FileDataSource(mailBody.getAffix());
				// ��Ӹ���������
				affixBody.setDataHandler(new DataHandler(source));
				// ��Ӹ����ı����������Ҫ��ͨ�������Base64�����ת�����Ա�֤���
				// ���ĸ����������ڷ���ʱ����������
				// sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
				// String fileName = "=?GBK?B?"
				// + enc.encode(mailBody.getAffixName().getBytes()) + "?=";
				String fileName = encodBASE64(mailBody.getAffixName());
				affixBody.setFileName(fileName);
				multipart.addBodyPart(affixBody);
			}

			// ��multipart����ŵ�message��
			message.setContent(multipart);
		}
		// �����ʼ�
		message.saveChanges();
		return message;
	}

	private String encodBASE64(String src) {
		sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
		return "=?GB2312?B?" + enc.encode(src.getBytes()) + "?=";
	}

	/**
	 * �����ʼ����������ʼ����ġ���1��������
	 * 
	 * @param debug
	 *            ��������
	 */
	public void send(boolean debug) {
		// ������������ڷ����ʼ��Ĺ�������console����ʾ������Ϣ��������ʹ
		// �ã�������ڿ���̨��console)�Ͽ��������ʼ��Ĺ��̣�
		session.setDebug(debug);
		try {
			Message message = createMessage();
			transport.sendMessage(message, message.getAllRecipients());
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("\n----------------------------------------------------------");
		System.out.println("- �ʼ��ɹ����ͣ�");
		System.out.println("- TO : " + Arrays.toString(mailAddress.getTo()));
		if (mailAddress.isHasCC())
			System.out.println("- CC : " + Arrays.toString(mailAddress.getCc()));
		if (mailAddress.isHasBCC())
			System.out.println("- BCC: " + Arrays.toString(mailAddress.getBcc()));
		System.out.println("----------------------------------------------------------\n");
	}

	/**
	 * �ر���Դ
	 * 
	 * @throws MessagingException
	 */
	public void close() throws MessagingException {
		if (null != transport)
			transport.close();
	}

	public Address[] getAddress(String[] address) throws AddressException {
		Address[] addrs = new InternetAddress[address.length];
		for (int i = 0; i < address.length; i++)
			addrs[i] = new InternetAddress(address[i]);
		return addrs;
	}

	/**
	 * ����
	 */
	public static void main(String[] args) {
		// System.out.println(read());
		test();
	}

	static void test() {
		String host = "smtp.sina.com";
		String username = "leezhaowei";
		String password = "leezhaowei";
		// ---------------------------------------------------------------------
		// String host = "smtp.tom.com";
		// String username = "leezhaowei";
		// String password = "lizhaowei";
		// ---------------------------------------------------------------------
		// String from = "";
		String to = "leezhaowei@126.com";
		// String cc = null;
		String subject = "����";
		String content = "<a href=http://www.baidu.com>baidu</a>";
		// String content = read();
		boolean mimeContent = true;
		// String affix = "d:/temp/temp.txt";
		// String affixName = "temp.txt";
		String affix = null;
		String affixName = null;
		boolean debug = true;
		// String userFile = "user.properties";
		// String addressFile = "mailaddress.properties";

		SendMail mail = null;
		try {
			try {
				User user = new User(host, username, password);
				MailAddress mailAddress = new MailAddress(to);

				// User user = new User(userFile);
				// MailAddress mailAddress = new MailAddress(SendMail.class
				// .getResourceAsStream(addressFile));

				MailBody mailBody = new MailBody(subject, content, mimeContent,
						affix, affixName);
				mail = new SendMail(user);
				// for (int i = 0; i < 5; i++) {
				// ���÷����˵�ַ���ռ��˵�ַ���ʼ�����
				mail.setAddress(mailAddress);

				// ����Ҫ���͸�����λ�úͱ���
				mail.setMailBody(mailBody);
				// ����smtp�������Լ�������ʺź�����
				mail.send(debug);

				// try {
				// Thread.sleep(1 * 1000);
				// } catch (InterruptedException e) {
				// e.printStackTrace();
				// }
				// mailBody = new MailBody(subject + "_" + (i + 1), content);
				// }

			} finally {
				if (null != mail)
					mail.close();
			}
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
			// } catch (IOException e) {
			// e.printStackTrace();
		}
	}

	static String read() {
		String fn = "D:/o(��_��)o/delcam/Delcam News  issue 23.htm";
		BufferedReader br = null;
		try {
			try {
				br = new BufferedReader(new InputStreamReader(
						new FileInputStream(fn)));
				StringBuilder sb = new StringBuilder();
				while (br.ready()) {
					sb.append(br.readLine().trim() + "\n\r");
				}
				return sb.toString();
			} finally {
				if (null != br)
					br.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
