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
 * 邮件发送程序<br>
 * <br>
 * <code>
 * <pre>
 * 使用示例：
 * String host = &quot;smtp.sina.com&quot;;
 * String username = &quot;leezhaowei&quot;;
 * String password = &quot;leezhaowei&quot;;
 * String from = &quot;&quot;;
 * String to = &quot;leezhaowei@sina.com&quot;;
 * // String cc = null;
 * String subject = &quot;测试&quot;;
 * String content = &quot;你好啊！&quot;;
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
 * 		// 设置发件人地址、收件人地址和邮件标题
 * 		mail.setAddress(mailAddress);
 * 
 * 		// 设置要发送附件的位置和标题
 * 		mail.setMailBody(mailBody);
 * 		// 设置smtp服务器以及邮箱的帐号和密码
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
 * @author 李赵伟 Create: 2007-12-19
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
	 * 初始化<code> Session, Transport </code>
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
		// 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
		props.put(MAIL_SMTP_HOST, user.getHost());
		// 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
		props.put(MAIL_SMTP_AUTH, "true");
		// 用刚刚设置好的props对象构建一个session
		session = Session.getDefaultInstance(props, auth);
		try {
			// 发送邮件
			transport = session.getTransport("smtp");
			// 连接服务器的邮箱
			transport.connect(user.getHost(), user.getUsername(), user
					.getPassword());
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println("与 " + user.getHost() + " 成功建立会话");
	}

	/**
	 * 设置收件人地址
	 * 
	 * @param mailAddress
	 */
	public void setAddress(MailAddress mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	 * 设置邮件内容
	 * 
	 * @param mailBody
	 */
	public void setMailBody(MailBody mailBody) {
		this.mailBody = mailBody;
	}

	/**
	 * 构造邮件的内容
	 * 
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws IOException
	 */
	private Message createMessage() throws AddressException,
			MessagingException, IOException {
		// 用session为参数定义消息对象
		MimeMessage message = new MimeMessage(session);
		// 加载发件人地址
		message.setFrom(new InternetAddress(user.getFrom()));
		message.setSentDate(new Date());
		// 加载收件人地址
		message.addRecipients(Message.RecipientType.TO, getAddress(mailAddress
				.getTo()));
		if (mailAddress.isHasCC())
			message.addRecipients(Message.RecipientType.CC,
					getAddress(mailAddress.getCc()));
		if (mailAddress.isHasBCC())
			message.addRecipients(Message.RecipientType.BCC,
					getAddress(mailAddress.getBcc()));

		// 加载标题
		message.setSubject(mailBody.getSubject(), "GB2312");

		if (mailBody.isContentFlag() || mailBody.isAffixFlag()) {
			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();

			if (mailBody.isContentFlag()) {
				// 设置邮件的文本内容
				MimeBodyPart contentPart = new MimeBodyPart();
				if (mailBody.isMimeContent())
					contentPart.setContent(mailBody.getContent(),
							"text/html;charset=GB2312");
				else
					contentPart.setText(mailBody.getContent());
				
				multipart.addBodyPart(contentPart);
			}

			if (mailBody.isAffixFlag()) {
				// 添加附件
				BodyPart affixBody = new MimeBodyPart();
				DataSource source = new FileDataSource(mailBody.getAffix());
				// 添加附件的内容
				affixBody.setDataHandler(new DataHandler(source));
				// 添加附件的标题这里很重要，通过下面的Base64编码的转换可以保证你的
				// 中文附件标题名在发送时不会变成乱码
				// sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
				// String fileName = "=?GBK?B?"
				// + enc.encode(mailBody.getAffixName().getBytes()) + "?=";
				String fileName = encodBASE64(mailBody.getAffixName());
				affixBody.setFileName(fileName);
				multipart.addBodyPart(affixBody);
			}

			// 将multipart对象放到message中
			message.setContent(multipart);
		}
		// 保存邮件
		message.saveChanges();
		return message;
	}

	private String encodBASE64(String src) {
		sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
		return "=?GB2312?B?" + enc.encode(src.getBytes()) + "?=";
	}

	/**
	 * 发送邮件，包含：邮件正文、（1个附件）
	 * 
	 * @param debug
	 *            调试设置
	 */
	public void send(boolean debug) {
		// 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
		// 用（你可以在控制台（console)上看到发送邮件的过程）
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
		System.out.println("- 邮件成功发送！");
		System.out.println("- TO : " + Arrays.toString(mailAddress.getTo()));
		if (mailAddress.isHasCC())
			System.out.println("- CC : " + Arrays.toString(mailAddress.getCc()));
		if (mailAddress.isHasBCC())
			System.out.println("- BCC: " + Arrays.toString(mailAddress.getBcc()));
		System.out.println("----------------------------------------------------------\n");
	}

	/**
	 * 关闭资源
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
	 * 测试
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
		String subject = "测试";
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
				// 设置发件人地址、收件人地址和邮件标题
				mail.setAddress(mailAddress);

				// 设置要发送附件的位置和标题
				mail.setMailBody(mailBody);
				// 设置smtp服务器以及邮箱的帐号和密码
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
		String fn = "D:/o(∩_∩)o/delcam/Delcam News  issue 23.htm";
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
