package utils.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.internet.AddressException;

import utils.string.Stringutils;

/**
 * ��װ�ռ��˵�ַ
 * 
 * <pre>
 * �����ļ���
 * to=
 * cc=
 * bcc=
 * 
 * �ʼ���ַʹ�á�,���ָ�
 * </pre>
 * 
 * @author ����ΰ Create: 2007-12-20
 */
public class MailAddress {

	private String[] to; // �ռ��˵�ַ
	private String[] cc; // �����˵�ַ
	private String[] bcc; // �ܼ�����
	private boolean hasCC; // ��Ǹ��ʼ��Ƿ����CC
	private boolean hasBCC;

	private static final String KEY_TO = "to";
	private static final String KEY_CC = "cc";
	private static final String KEY_BCC = "bcc";

	public MailAddress(String to) throws AddressException {
		this(new String[] { to }, null);
	}

	public MailAddress(String to[]) throws AddressException {
		this(to, null);
	}

	public MailAddress(String to, String cc) throws AddressException {
		this(new String[] { to }, new String[] { cc });
	}

	public MailAddress(String[] to, String[] cc) throws AddressException {
		this(to, cc, null);
	}

	public MailAddress(String[] to, String[] cc, String[] bcc)
			throws AddressException {
		set(to, cc, bcc);
	}

	private void set(String[] to, String[] cc, String[] bcc)
			throws AddressException {
		if (!check(to))
			throw new AddressException("û���ռ��˵�ַ��");

		setTo(to);
		setCc(cc);
		setBcc(bcc);
	}

	/**
	 * ʹ�������ļ������ռ��˵�ַ
	 * 
	 * @param propertiesFile
	 * @throws IOException
	 * @throws AddressException
	 */
	public MailAddress(InputStream propertiesFile) throws IOException,
			AddressException {
		readProperties(propertiesFile);
	}

	private void readProperties(InputStream is) throws IOException,
			AddressException {
		Properties p = new Properties();
		try {
			p.load(is);
		} finally {
			if (null != is)
				is.close();
		}
		String to = Stringutils.toGBKString(p.getProperty(KEY_TO),
				Stringutils.ISO8859_1);
		String cc = Stringutils.toGBKString(p.getProperty(KEY_CC),
				Stringutils.ISO8859_1);
		String bcc = Stringutils.toGBKString(p.getProperty(KEY_BCC),
				Stringutils.ISO8859_1);
		String ccs[] = null;
		String bccs[] = null;
		if (null != cc && 0 != cc.trim().length())
			ccs = cc.split(",");
		if (null != bcc && 0 != bcc.trim().length())
			bccs = bcc.split(",");

		set(to.split(","), ccs, bccs);

		// if (null != cc && 0 != cc.trim().length())
		// set(to.split(","), cc.split(","));
		// else
		// set(to.split(","), null);
	}

	private boolean check(String[] to) {
		if (null == to || 0 == to.length)
			return false;

		String s;
		for (int i = 0; i < to.length; i++) {
			s = to[i];
			if (null == s || 0 == s.trim().length())
				return false;
		}
		return true;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		if (null != cc && 0 != cc.length) {
			this.cc = cc;
			setHasCC(true);
		} else
			setHasCC(false);
	}

	public boolean isHasCC() {
		return hasCC;
	}

	private void setHasCC(boolean hasCC) {
		this.hasCC = hasCC;
	}

	public String[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) {
		if (null != bcc && 0 != bcc.length) {
			this.bcc = bcc;
			setHasBCC(true);
		} else
			setHasBCC(false);
	}

	public boolean isHasBCC() {
		return hasBCC;
	}

	private void setHasBCC(boolean hasBCC) {
		this.hasBCC = hasBCC;
	}

}
