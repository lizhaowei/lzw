package lzw.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import lzw.datetime.DateUtils;

/**
 * @author ����ΰ Create: 10:18:03 AM May 12, 2008
 */
public class Tools{

	private static Map<String, String> citys = null;

	/**
	 * KEY������ - VALUE������
	 * 
	 * @return <code>Map&lt;String, String&gt;</code>
	 */
	public static Map<String, String> citys(){
		if (null != citys)
			return citys;
		String s = "0,ȫ��;010,����;021,�Ϻ�;022,���;023,����;0311,ʯ��ׯ;0312,����;0314,�е�;0310,����;0315,��ɽ;0335,�ػʵ�;0317,����;0318,��ˮ;0316,�ȷ�;0319,��̨;0313,�żҿ�;0351,̫ԭ;0355,����;0352,��ͬ;0356,����;0354,����;0357,�ٷ�;0358,����;0349,˷��;0350,����;0359,�˳�;0353,��Ȫ;0471,���ͺ���;0472,��ͷ;0476,���;0477,������˹;0470,���ױ���;0475,ͨ��;0474,�����첼;0473,�ں�;0482,�˰���;024,����;0411,����;0412,��ɽ;0415,����;0413,��˳;0416,����;0417,Ӫ��;0414,��Ϫ;0428,����;0418,����;0429,��«��;0419,����;0427,�̽�;0410,����;0431,����;0432,����;0436,�׳�;0439,��ɽ;0437,��Դ;0434,��ƽ;0438,��ԭ;0435,ͨ��;0451,������;0459,����;0452,�������;0454,��ľ˹;0457,���˰���;0456,�ں�;0468,�׸�;0467,����;0453,ĵ����;0464,��̨��;0455,�绯;0469,˫Ѽɽ;0458,����;025,�Ͼ�;0512,����;0519,����;0518,���Ƹ�;0523,̩��;0510,����;0516,����;0514,����;0511,��;0517,����;0513,��ͨ;0527,��Ǩ;0515,�γ�;0571,����;0574,����;0573,����;0575,����;0577,����;0580,��ɽ;0572,����;0579,��;0578,��ˮ;0576,̨��;0551,�Ϸ�;0553,�ߺ�;0556,����;0552,����;0558,����;0565,����;0566,����;0550,����;0558,����;0559,��ɽ;0561,����;0554,����;0564,����;0555,��ɽ;0557,����;0562,ͭ��;0563,����;0591,����;0592,����;0595,Ȫ��;0597,����;0593,����;0599,��ƽ;0594,����;0598,����;0596,����;0791,�ϲ�;0797,����;0792,�Ž�;0798,������;0796,����;0799,Ƽ��;0793,����;0790,����;0795,�˴�;0701,ӥ̶;0531,����;0532,�ൺ;0631,����;0535,��̨;0536,Ϋ��;0538,̩��;0543,����;0534,����;0546,��Ӫ;0530,����;0537,����;0635,�ĳ�;0539,����;0634,����;0633,����;0533,�Ͳ�;0632,��ׯ;020,����;0755,����;0756,�麣;0769,��ݸ;0757,��ɽ;0752,����;0750,����;0760,��ɽ;0754,��ͷ;0759,տ��;0768,����;0762,��Դ;0663,����;0668,ï��;0753,÷��;0763,��Զ;0751,�ع�;0660,��β;0662,����;0766,�Ƹ�;0758,����;0898,����;0898,����;0771,����;0779,����;0771,����;0770,���Ǹ�;0773,����;0775,���;0778,�ӳ�;0774,����;0772,����;0772,����;0777,����;0775,����;0774,����;0371,֣��;0379,����;0378,����;0374,���;0372,����;0375,ƽ��ɽ;0392,�ױ�;0391,����;0391,��Դ;0395,���;0377,����;0393,���;0398,����Ͽ;0370,����;0373,����;0376,����;0396,פ���;0394,�ܿ�;027,�人;0710,�差;0719,ʮ��;0714,��ʯ;0711,����;0718,��ʩ;0713,�Ƹ�;0716,����;0724,����;0722,����;0717,�˲�;0728,����;0728,Ǳ��;0728,����;0712,Т��;0715,����;0731,��ɳ;0730,����;0732,��̶;0736,����;0735,����;0743,���;0734,����;0745,����;0738,¦��;0739,����;0737,����;0746,����;0733,����;0744,�żҽ�;028,�ɶ�;0816,����;0832,����;0827,����;0838,����;0818,����;0826,�㰲;0839,��Ԫ;0833,��ɽ;0830,����;0833,üɽ;0832,�ڽ�;0817,�ϳ�;0812,��֦��;0825,����;0831,�˱�;0835,�Ű�;0813,�Թ�;0851,����;0853,��˳;0857,�Ͻ�;0856,ͭ��;0852,����;0871,����;0877,��Ϫ;0878,����;0872,����;0873,���;0874,����;0691,��˫����;0870,��ͨ;0891,����;0892,�տ���;0983,ɽ��;029,����;0915,����;0917,����;0916,����;0914,����;0919,ͭ��;0913,μ��;0910,����;0911,�Ӱ�;0912,����;0931,����;0943,����;0932,����;0935,���;0937,��Ȫ;0939,¤��;0930,����;0933,ƽ��;0930,����;0935,����;0938,��ˮ;0936,��Ҵ;0971,����;0972,����;0970,����;0974,����;0951,����;0952,ʯ��ɽ;0953,����;0953,����;0991,��³ľ��;0994,����;0990,��������;0993,ʯ����;0995,��³��;0937,������;0888,����;0837,���Ӳ���Ǽ��������;0834,��ɽ����������;0898,��ˮ����������;0887,�������������;0875,��ɽ;0719,��ũ������;0570,������;0836,���β���������;0876,��ɽ׳������������;0692,�º���徰����������;";
		String[] a = s.split(";");
		String[] b = null;
		String t;
		citys = new HashMap<String, String>();
		for(int i = 0; i < a.length; i++){
			t = a[i];
			b = t.split(",");
			citys.put(b[1], b[0]);
		}
		return citys;
	}

	/**
	 * ���ݼ�¼������ҳ����ʾ���������㷭ҳ�Ĵ���
	 * 
	 * @param tr ��¼����
	 * @param pr ҳ����ʾ����
	 * @return ҳ������
	 */
	public static int countTotalPage(int tr, int pr){
		if (0 != tr){
			// ��ҳ��
			if ((tr % pr) == 0)
				return(tr / pr);
			else
				return (tr / pr) + 1;
		}
		return 0;
	}

	/**
	 * ����ĳһҳ����ʼ��¼
	 * 
	 * @param cp
	 * @param pr
	 * @return
	 */
	public static int countStartRow(int cp, int pr){
		return((1 >= cp) ? 1 : ((cp - 1) * pr + 1));
	}

	/**
	 * ������񷽷���GET
	 * 
	 * @param sUrl ��ַ
	 * @return response
	 */
	public static String GET(String sUrl){
		URL url = null;
		URLConnection uc = null;
		HttpURLConnection huc = null;
		BufferedReader br = null;
		try{
			try{
				url = new URL(sUrl);
				uc = url.openConnection();
				huc = (HttpURLConnection)uc;
				huc.setRequestMethod("GET");
				huc.setDoInput(true);
				huc.setDoOutput(true);
				huc.connect();

				br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
				StringBuffer info = new StringBuffer("");
				String t = null;
				while((t = br.readLine()) != null){
					info.append(t.trim());
				}
				return info.toString();
			}finally{
				if (br != null)
					br.close();
				if (huc != null)
					huc.disconnect();
			}
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * ������񷽷���POST
	 * 
	 * @param sUrl ��ַ
	 * @param xml ��������
	 * @return response
	 */
	public static String POST(String sUrl, String xml, String encoding){
		if (!checkStr(encoding))
			encoding = "gbk";
		URL url = null;
		URLConnection uc = null;
		HttpURLConnection huc = null;
		DataOutputStream dos = null;
		BufferedReader br = null;
		try{
			try{
				url = new URL(sUrl);
				uc = url.openConnection();
				huc = (HttpURLConnection)uc;

				huc.setRequestMethod("POST");
				huc.setDoInput(true);
				huc.setDoOutput(true);
				huc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				huc.setRequestProperty("Content-Length", xml.getBytes("iso-8859-1").length + "");

				dos = new DataOutputStream(huc.getOutputStream());
				huc.connect();
				dos.write(xml.getBytes("gbk"));
				dos.flush();

				br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
				StringBuffer info = new StringBuffer("");
				String t = null;
				while((t = br.readLine()) != null){
					info.append(t.trim());
				}
				return info.toString();
			}finally{
				if (dos != null)
					dos.close();
				if (br != null)
					br.close();
				if (huc != null)
					huc.disconnect();
			}
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * ������������ڣ��������sd��ed֮������������µ��³�����ĩ��������<br>
	 * ���ظ�ʽ���³���ĩ,�³���ĩ
	 * 
	 * @param sd YYYYMMDD
	 * @param ed YYYYMMDD
	 * @return
	 */
	public static String getDateForDetailMonth(String pattern, String sd,
	    String ed){
		String fmt = DateUtils.DateDayFormat1;
		if (checkStr(pattern))
			fmt = pattern;
		Calendar sc = DateUtils.parseCalendarByFormat(sd, fmt);
		Calendar ec = DateUtils.parseCalendarByFormat(ed, fmt);

		String a = DateUtils.toStringByFormat(sc, fmt);

		long sl = sc.getTimeInMillis();
		long el = ec.getTimeInMillis();

		List<String> list = new ArrayList<String>();
		list.add(a);
		while(sl < el){
			sc.add(Calendar.MONTH, 1);
			sl = sc.getTimeInMillis();
			if (sl <= el){
				a = DateUtils.toStringByFormat(sc, fmt);
				// Out.pln(a);
				list.add(a);
			}
		}
		String t, r;
		Calendar cal;
		StringBuilder d = new StringBuilder();
		for(int i = 0; i < list.size(); i++){
			t = list.get(i);
			// Out.pln("Date = " + t);
			cal = DateUtils.parseCalendarByFormat(t, fmt);
			cal = DateUtils.getLastDayOfMonth(cal);
			if (cal.getTimeInMillis() > ec.getTimeInMillis()){
				ec.add(Calendar.DAY_OF_MONTH, -1);
				r = DateUtils.toStringByFormat(ec, fmt);
			}else
				r = DateUtils.toStringByFormat(cal, fmt);
			d.append(t + r + ",");
		}
		t = d.substring(0, d.length() - 1);
		return t;
	}

	/**
	 * ������������ڣ��������sd��ed֮��������������ڵ�����һ����������������<br>
	 * ���ظ�ʽ������һ������,����һ������
	 * 
	 * @param sd YYYYMMDD
	 * @param ed YYYYMMDD
	 * @return
	 */
	public static String getDateForDetailWeek(String sd, String ed){
		final String fmt = DateUtils.DateDayFormat1;
		Calendar sc = DateUtils.parseCalendarByFormat(sd, fmt);
		Calendar ec = DateUtils.parseCalendarByFormat(ed, fmt);
		long sl = sc.getTimeInMillis();
		long el = ec.getTimeInMillis();

		Calendar nextWeek;
		String n;
		List<String> list = new ArrayList<String>();
		list.add(sd);
		while(sl < el){
			nextWeek = DateUtils.getNextWeek(sc);
			sl = nextWeek.getTimeInMillis();
			if (sl < el){
				n = DateUtils.toStringByFormat(nextWeek, fmt);
				list.add(n);
			}
			sc = nextWeek;
		}
		Calendar cal;
		String t, x, y;
		StringBuilder d = new StringBuilder();
		for(int i = 0; i < list.size(); i++){
			t = list.get(i);
			cal = DateUtils.parseCalendarByFormat(t, fmt);
			x = t;
			cal.add(Calendar.DAY_OF_WEEK, 6);
			if (cal.getTimeInMillis() < ec.getTimeInMillis())
				y = DateUtils.toStringByFormat(cal, fmt);
			else{
				ec.add(Calendar.DAY_OF_WEEK, -1);
				y = DateUtils.toStringByFormat(ec, fmt);
			}
			d.append(x + y + ",");
		}
		t = d.substring(0, d.length() - 1);
		return t;
	}

	/**
	 * ����ַ���
	 * 
	 * @param str
	 * @return ���str!=null && str!=""������true�����򷵻�false��
	 */
	public static final boolean checkStr(String str){
		if (null == checkStr(str, null))
			return false;
		return true;
	}

	/**
	 * ����ַ����Ƿ�Ϊ<code>null</code>
	 * 
	 * @param str
	 * @return if str is null then return "".
	 */
	public static final String checkStr(Object str){
		if (str instanceof String)
			return checkStr((String)str, "");
		return "";
	}

	/**
	 * ����ַ����Ƿ�Ϊ<code>null</code>
	 * 
	 * @param str
	 * @param show
	 * @return if str is null then return show.
	 */
	public static final String checkStr(Object str, String show){
		if (str instanceof String)
			return checkStr((String)str, show);
		return show;
	}

	/**
	 * ����ַ����Ƿ�Ϊ<code>null</code>
	 * 
	 * @param str ��Ҫ���Ĳ���
	 * @param view ����һ������Ϊnull�򷵻ظò�����ֵ
	 * @return if str is null then return view.
	 */
	public static final String checkStr(String str, String view){
		boolean b = (null != str);
		return(b ? str.trim() : view.trim());
	}

	/**
	 * ����Key�������ļ���Properties���л�ȡValue
	 * 
	 * @param p
	 * @param key
	 * @return
	 */
	public static String get(Properties p, final String key){
		return encoding(p.getProperty(key));
	}

	/**
	 * �ַ������룺<code>iso-8859-1 => gbk</code>
	 * 
	 * @param str
	 * @return
	 */
	public static String encoding(String str){
		try{
			return new String(str.getBytes("iso-8859-1"), "gbk");
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ����ַ����Ƿ�������
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNum(String s){
		char c;
		int len = s.length();
		for(int i = 0; i < len; i++){
			c = s.charAt(i);
			if (c < '0' || c > '9')
				return false;
		}
		return true;
	}

	/**
	 * ����ַ������Ƿ�ȫ���Ǵ�Сд��ĸ
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isLetter(String s){
		char c;
		int len = s.trim().length();
		String t = s.trim();
		for(int i = 0; i < len; i++){
			c = t.charAt(i);
			// Out.pln((int)c);
			if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')))
				return false;
		}
		return true;
	}

	/**
	 * ����ٷֱ�
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String baiFenBi(String a, String b){
		if (null != a && 0 != a.trim().length() && null != b && 0 != b.trim().length()){
			double m = Double.parseDouble(a);
			double n = Double.parseDouble(b);
			if (0 == m)
				return "0%";
			if (m == n)
				return "100%";
			double r = m / n;
			return((Math.round(r * 100)) + "%");
		}
		return "0%";
	}

	/**
	 * @return ���ɳ���Ϊ21λ�����кţ�ǰ16λΪ��ǰʱ�䣺YYYYMMDDHHMMSS��
	 */
	public static final String generateKey(){
		return DateUtils.toStringByFormat(DateUtils.DateFormatFull) + generateKey(7, true);
	}

	/**
	 * ����Ψһ�����к�
	 * 
	 * @param size ���кų���
	 * @param num true��Ϊ�������кţ�false��Ϊ���ּ���ĸ
	 * @return Ψһ�����к�
	 */
	public synchronized static final String generateKey(int size, boolean num){
		if (0 == size)
			size = 21;
		String chars = "0123456789abcdef";
		if (num)
			chars = "0123456789";
		Random random = new Random();
		StringBuffer key = new StringBuffer();
		int index = 0;
		int j = chars.length() - 1;
		for(int i = 0; i < size; i++){
			index = random.nextInt(j) + 1;
			key.append(chars.substring(index, (index + 1)));
		}
		return key.toString();
	}

	/**
	 * @param length ��ų���
	 * @return �������к�
	 */
	public synchronized static String getTransactionID(int length){
		if (0 == length)
			length = 21;
		String sChars = "0123456789";
		// String sPswd = "";
		StringBuffer id = new StringBuffer();
		int iIndex = 0;
		// int iseq = 0;
		Random x = new Random();
		for(int i = 0; i < length; i++){
			iIndex = x.nextInt(9) + 1;
			// sPswd = sPswd + sChars.substring(iIndex, (iIndex + 1));
			id.append(sChars.substring(iIndex, (iIndex + 1)));
		}
		// if (iseq >= 999) {
		// iseq = 0;
		// } else {
		// iseq++;
		// }
		// Out.pln(iseq);
		// String sSeq = Integer.toString(iseq);
		// if (sSeq.length() == 1) {
		// sSeq = "00" + sSeq;
		// } else if (sSeq.length() == 2) {
		// sSeq = "0" + sSeq;
		// }
		// return sPswd + sSeq;
		return id.toString();
	}

	/**
	 * �ж�����
	 * 
	 * <pre>
	 * һ���������ֻ��1�������������������������������������������������� 2��3��5��7 ���������� 4��6��8��9 ���ǣ�
	 * ���߳�Ϊ�ϳ����������������۵�ɽ�������Ϊ���֣�һ�ֽ�������һ�ֽкϳ�������1����������Ҳ���Ǻ����������ĸ�
	 * ˹��Ψһ�ֽⶨ��˵���κ�һ������������д��һ��������˵Ļ��������г�2��ż���⣬��������������
	 * </pre>
	 * 
	 * @param n
	 * @return true, ������false, ������
	 */
	public static boolean isPrime(int n){
		for(int i = 2; i * i <= n; i++){
			if (n % i == 0)
				return false;
		}
		return true;
	}
}
