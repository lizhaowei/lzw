package utils.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ��ʽ�����������ڡ�ʱ��<br>
 * <br>
 * <table width="400px">
 * <tr bgcolor="#ccccff">
 * <th align="left">��ʽ</th>
 * <th align="left">���</th>
 * </tr>
 * <tr bgcolor="#ccccff">
 * <td><code>yyyy-MM-dd</code></td>
 * <td>2007-11-07</td>
 * </tr>
 * <tr bgcolor="#ccccff">
 * <td>yyyy-MM-dd HH:mm:ss</td>
 * <td>2007-11-07 16:52:29</td>
 * </tr>
 * </tr>
 * <tr bgcolor="#ccccff">
 * <td>......</td>
 * <td>......</td>
 * </tr>
 * </table><br>
 * <br>
 * �����ַ�����ʽ�����ڻ��<code> java.util.Date </code>���� <br>
 * <br>
 * <table width="400px">
 * <tr bgcolor="#ccccff">
 * <td>stringDate</td>
 * <td>pattern</td>
 * </tr>
 * <tr bgcolor="#ccccff">
 * <td>2007-12-11</td>
 * <td>yyyy-MM-dd</td>
 * </tr>
 * <tr bgcolor="#ccccff">
 * <td>2007-12-12 15:30</td>
 * <td>yyyy-MM-dd HH:mm</td>
 * </tr>
 * <tr bgcolor="#ccccff">
 * <td>......</td>
 * <td>......</td>
 * </tr>
 * </table>
 * 
 * @author ����ΰ Create: 2007-11-07
 */
public class Dateutils{

	/** * ���ڸ�ʽ��yyyy��MM��dd�� HHʱmm��ss�� */
	public static final String DateCNFormat = "yyyy��MM��dd�� HHʱmm��ss��";
	/** * ���ڸ�ʽ��yyyy-MM-dd */
	public static final String DateDayFormat = "yyyy-MM-dd";
	/** * ���ڸ�ʽ��yyyyMMdd */
	public static final String DateDayFormat1 = "yyyyMMdd";
	/** * ���ڸ�ʽ��yyyy-MM-dd_HH-mm-ss */
	public static final String DateFileFormat = "yyyy-MM-dd_HH-mm-ss";
	/** * ���ڸ�ʽ��yyyyMMddHHmmss */
	public static final String DateFormatFull = "yyyyMMddHHmmss";
	/** * ���ڸ�ʽ��yyyy-MM-dd HH:mm:ss,SSS */
	public static final String DateMilliFormat = "yyyy-MM-dd HH:mm:ss,SSS";
	/** * ���ڸ�ʽ��yyyyMMddHHmmssSSS */
	public static final String DateMilliFormat1 = "yyyyMMddHHmmssSSS";
	/** * ���ڸ�ʽ��yyyy-MM-dd HH:mm */
	public static final String DateMinuteFormat = "yyyy-MM-dd HH:mm";
	/** * ���ڸ�ʽ��yyyy-MM-dd HH:mm:ss */
	public static final String DateSecondFormat = "yyyy-MM-dd HH:mm:ss";
	/** * ���ڸ�ʽ��EEE, dd MMM yyyy HH:mm:ss z */
	public static final String DateTimeGMT = "EEE, dd MMM yyyy HH:mm:ss z";
	/** * һ����ÿ���µ����� */
	private static final int[] DayArray = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * ��ȡָ���·ݵ�����
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDayOfMonth(int year, int month){
		if (month < 1 || month > 12)
			return -1;

		int retn = 0;
		if (2 == month){
			if (isLeapYear(year))
				retn = 29;
			else
				retn = DayArray[month - 1];

		}else
			retn = DayArray[month - 1];

		return retn;
	}

	/**
	 * ��ȡָ�����ڵ������·ݵĵ�һ��
	 * 
	 * @param c
	 * @return
	 */
	public static Calendar getFirstDayOfMonth(Calendar c){
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c;
	}

	/**
	 * ��ȡָ�����ڵ������·ݵĵ�һ��
	 * 
	 * @param date ָ�����ڡ�
	 * @return ָ�����ڵ������·ݵĵ�һ��
	 */
	public static Date getFirstDayOfMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return getFirstDayOfMonth(c).getTime();
	}

	/**
	 * ��ȡָ�����ڵ���һ���µĵ�һ��
	 * 
	 * @param cal ָ��������
	 * @return ָ�����ڵ���һ���µĵ�һ��
	 */
	public static Calendar getFirstDayOfNextMonth(Calendar cal){
		cal.setTime(getNextMonth(cal.getTime()));
		cal.setTime(getFirstDayOfMonth(cal.getTime()));
		return cal;
	}

	/**
	 * ��ȡָ�����ڵ���һ���µĵ�һ��
	 * 
	 * @param date ָ�����ڡ�
	 * @return ָ�����ڵ���һ���µĵ�һ��
	 */
	public static Date getFirstDayOfNextMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getFirstDayOfNextMonth(cal).getTime();
	}

	/**
	 * ��ȡָ�����ڵ���һ�����ڵĵ�һ��
	 * 
	 * @param cal ָ��������
	 * @return ָ�����ڵ���һ�����ڵĵ�һ��
	 */
	public static Calendar getFirstDayOfNextWeek(Calendar cal){
		cal.setTime(getNextWeek(cal.getTime()));
		cal.setTime(getFirstDayOfWeek(cal.getTime()));
		return cal;
	}

	/**
	 * ��ȡָ�����ڵ���һ�����ڵĵ�һ��
	 * 
	 * @param date ָ�����ڡ�
	 * @return ָ�����ڵ���һ�����ڵĵ�һ��
	 */
	public static Date getFirstDayOfNextWeek(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getFirstDayOfNextWeek(cal).getTime();
	}

	/**
	 * ��ȡָ�����ڵ���һ���µĵ�һ��
	 * 
	 * @param cal
	 * @return
	 */
	public static Calendar getFirstDayOfPreviousMonth(Calendar cal){
		cal.setTime(getPreviousMonth(cal.getTime()));
		cal.setTime(getFirstDayOfMonth(cal.getTime()));
		return cal;
	}

	/**
	 * ��ȡָ�����ڵ���һ���µĵ�һ��
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfPreviousMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getFirstDayOfPreviousMonth(cal).getTime();
	}

	/**
	 * ��ȡָ�����ڵ���һ�����ڵ�����һ
	 * 
	 * @param cal
	 * @return
	 */
	public static Calendar getFirstDayOfPreviousWeek(Calendar cal){
		cal.setTime(getPreviousWeek(cal.getTime()));
		cal.setTime(getFirstDayOfWeek(cal.getTime()));
		return cal;
	}

	/**
	 * ��ȡָ�����ڵ���һ�����ڵ�����һ
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfPreviousWeek(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getFirstDayOfPreviousWeek(cal).getTime();
	}

	/**
	 * ��ȡָ�����ڵ��������ڵĵ�һ��<br>
	 * 
	 * @param cal ָ��������
	 * @return ָ�����ڵ��������ڵĵ�һ��
	 */
	public static Calendar getFirstDayOfWeek(Calendar cal){
		switch(cal.get(Calendar.DAY_OF_WEEK)){
			case (Calendar.SUNDAY):
				cal.add(Calendar.DATE, -6);
				break;
			case (Calendar.MONDAY):
				cal.add(Calendar.DATE, 0);
				break;
			case (Calendar.TUESDAY):
				cal.add(Calendar.DATE, -1);
				break;
			case (Calendar.WEDNESDAY):
				cal.add(Calendar.DATE, -2);
				break;
			case (Calendar.THURSDAY):
				cal.add(Calendar.DATE, -3);
				break;
			case (Calendar.FRIDAY):
				cal.add(Calendar.DATE, -4);
				break;
			case (Calendar.SATURDAY):
				cal.add(Calendar.DATE, -5);
				break;
		}
		return cal;
	}

	/**
	 * ��ȡָ�����ڵ��������ڵĵ�һ��<br>
	 * 
	 * @param date ָ�����ڡ�
	 * @return ָ�����ڵ��������ڵĵ�һ��
	 */
	public static Date getFirstDayOfWeek(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getFirstDayOfWeek(cal).getTime();
	}

	/**
	 * ��ȡָ�����ڵ������·ݵ����һ��<br>
	 * <br>
	 * 1. ���date��1�£���Ϊ31��<br>
	 * 2. ���date��2�£���Ϊ28�գ����date�������2�£���Ϊ29��<br>
	 * 3. ���date��3�£���Ϊ31��<br>
	 * 4. ���date��4�£���Ϊ30��<br>
	 * 5. ���date��5�£���Ϊ31��<br>
	 * 6. ���date��6�£���Ϊ30��<br>
	 * 7. ���date��7�£���Ϊ31��<br>
	 * 8. ���date��8�£���Ϊ31��<br>
	 * 9. ���date��9�£���Ϊ30��<br>
	 * 10. ���date��10�£���Ϊ31��<br>
	 * 11. ���date��11�£���Ϊ30��<br>
	 * 12. ���date��12�£���Ϊ31��
	 * 
	 * @param cal ָ��������
	 * @return ָ�����ڵ������·ݵ����һ��
	 */
	public static Calendar getLastDayOfMonth(Calendar cal){
		switch(cal.get(Calendar.MONTH)){
			case 0:
				cal.set(Calendar.DAY_OF_MONTH, 31);
				break;
			case 1:
				cal.set(Calendar.DAY_OF_MONTH, 28);
				break;
			case 2:
				cal.set(Calendar.DAY_OF_MONTH, 31);
				break;
			case 3:
				cal.set(Calendar.DAY_OF_MONTH, 30);
				break;
			case 4:
				cal.set(Calendar.DAY_OF_MONTH, 31);
				break;
			case 5:
				cal.set(Calendar.DAY_OF_MONTH, 30);
				break;
			case 6:
				cal.set(Calendar.DAY_OF_MONTH, 31);
				break;
			case 7:
				cal.set(Calendar.DAY_OF_MONTH, 31);
				break;
			case 8:
				cal.set(Calendar.DAY_OF_MONTH, 30);
				break;
			case 9:
				cal.set(Calendar.DAY_OF_MONTH, 31);
				break;
			case 10:
				cal.set(Calendar.DAY_OF_MONTH, 30);
				break;
			case 11:
				cal.set(Calendar.DAY_OF_MONTH, 31);
				break;
		}
		// �������
		if ((cal.get(Calendar.MONTH) == Calendar.FEBRUARY) && (isLeapYear(cal.get(Calendar.YEAR)))){
			cal.set(Calendar.DAY_OF_MONTH, 29);
		}
		return cal;
	}

	/**
	 * ��ȡָ�����ڵ������·ݵ����һ��
	 * 
	 * @param date ָ�����ڡ�
	 * @return ָ�����ڵ������·ݵ����һ��
	 */
	public static Date getLastDayOfMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getLastDayOfMonth(cal).getTime();
	}

	/**
	 * ��ȡָ�����ڵ���һ���µ����һ��
	 * 
	 * @param cal ָ�����ڡ�
	 * @return ָ�����ڵ���һ���µ����һ��
	 */
	public static Calendar getLastDayOfNextMonth(Calendar cal){
		cal.setTime(getNextMonth(cal.getTime()));
		cal.setTime(getLastDayOfMonth(cal.getTime()));
		return cal;
	}

	/**
	 * ��ȡָ�����ڵ���һ���µ����һ��
	 * 
	 * @param date ָ�����ڡ�
	 * @return ָ�����ڵ���һ���µ����һ��
	 */
	public static Date getLastDayOfNextMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getLastDayOfNextMonth(cal).getTime();
	}

	/**
	 * ��ȡָ�����ڵ���һ�����ڵ����һ��
	 * 
	 * @param cal ָ�����ڡ�
	 * @return ָ�����ڵ���һ�����ڵ����һ��
	 */
	public static Calendar getLastDayOfNextWeek(Calendar cal){
		cal.setTime(getNextWeek(cal.getTime()));
		cal.setTime(getLastDayOfWeek(cal.getTime()));
		return cal;
	}

	/**
	 * ��ȡָ�����ڵ���һ�����ڵ����һ��
	 * 
	 * @param date ָ�����ڡ�
	 * @return ָ�����ڵ���һ�����ڵ����һ��
	 */
	public static Date getLastDayOfNextWeek(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getLastDayOfNextWeek(cal).getTime();
	}

	/**
	 * ��ȡָ�����ڵ���һ���µ����һ��
	 * 
	 * @param cal
	 * @return
	 */
	public static Calendar getLastDayOfPreviousMonth(Calendar cal){
		cal.setTime(getPreviousMonth(cal.getTime()));
		cal.setTime(getLastDayOfMonth(cal.getTime()));
		return cal;
	}

	/**
	 * ��ȡָ�����ڵ���һ���µ����һ��
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfPreviousMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getLastDayOfPreviousMonth(cal).getTime();
	}

	/**
	 * ��ȡָ�����ڵ���һ�����ڵ�������
	 * 
	 * @param cal
	 * @return
	 */
	public static Calendar getLastDayOfPreviousWeek(Calendar cal){
		cal.setTime(getPreviousWeek(cal.getTime()));
		cal.setTime(getLastDayOfWeek(cal.getTime()));
		return cal;
	}

	/**
	 * ��ȡָ�����ڵ���һ�����ڵ�������
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfPreviousWeek(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getLastDayOfPreviousWeek(cal).getTime();
	}

	/**
	 * ��ȡָ�����ڵ��������ڵ����һ��<br>
	 * 
	 * @param cal ָ��������
	 * @return ָ�����ڵ��������ڵ����һ��
	 */
	public static Calendar getLastDayOfWeek(Calendar cal){
		switch(cal.get(Calendar.DAY_OF_WEEK)){
			case (Calendar.SUNDAY):
				cal.add(Calendar.DATE, 0);
				break;
			case (Calendar.MONDAY):
				cal.add(Calendar.DATE, 6);
				break;
			case (Calendar.TUESDAY):
				cal.add(Calendar.DATE, 5);
				break;
			case (Calendar.WEDNESDAY):
				cal.add(Calendar.DATE, 4);
				break;
			case (Calendar.THURSDAY):
				cal.add(Calendar.DATE, 3);
				break;
			case (Calendar.FRIDAY):
				cal.add(Calendar.DATE, 2);
				break;
			case (Calendar.SATURDAY):
				cal.add(Calendar.DATE, 1);
				break;
		}
		return cal;
	}

	/**
	 * ��ȡָ�����ڵ��������ڵ����һ��
	 * 
	 * @param date ָ�����ڡ�
	 * @return ָ�����ڵ��������ڵ����һ��
	 */
	public static Date getLastDayOfWeek(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getLastDayOfWeek(cal).getTime();
	}

	/**
	 * ��ȡָ�����ڵ���һ��
	 * 
	 * @param cal ָ��������
	 * @return ָ�����ڵ���һ��
	 */
	public static Calendar getNextDay(Calendar cal){
		cal.add(Calendar.DATE, 1);
		return cal;
	}

	/**
	 * ��ȡָ�����ڵ���һ��
	 * 
	 * @param date ָ�����ڡ�
	 * @return ָ�����ڵ���һ��
	 */
	public static Date getNextDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getNextDay(cal).getTime();
	}

	/**
	 * ��ȡָ�����ڵ���һ����
	 * 
	 * @param cal ָ��������
	 * @return ָ�����ڵ���һ����
	 */
	public static Calendar getNextMonth(Calendar cal){
		cal.add(Calendar.MONTH, 1);
		return cal;
	}

	/**
	 * ��ȡָ�����ڵ���һ����
	 * 
	 * @param date ָ�����ڡ�
	 * @return ָ�����ڵ���һ����
	 */
	public static Date getNextMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getNextMonth(cal).getTime();
	}

	/**
	 * ��ȡָ�����ڵ���һ������
	 * 
	 * @param cal ָ��������
	 * @return ָ�����ڵ���һ������
	 */
	public static Calendar getNextWeek(Calendar cal){
		cal.add(Calendar.DATE, 7);
		return cal;
	}

	/**
	 * ��ȡָ�����ڵ���һ������
	 * 
	 * @param date ָ�����ڡ�
	 * @return ָ�����ڵ���һ������
	 */
	public static Date getNextWeek(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getNextWeek(cal).getTime();
	}

	/**
	 * ��ȡָ�����ڵĺ�һ��������<br>
	 * <br>
	 * 1.���date�������壬���3��<br>
	 * 2.���date�������������2��, �����1��
	 * 
	 * @param cal ָ��������
	 * @return ָ�����ڵĺ�һ��������
	 */
	public static Calendar getNextWorkingDay(Calendar cal){
		switch(cal.get(Calendar.DAY_OF_WEEK)){
			case Calendar.FRIDAY:
				cal.add(Calendar.DATE, 3);
				break;
			case Calendar.SATURDAY:
				cal.add(Calendar.DATE, 2);
				break;
			default:
				cal.add(Calendar.DATE, 1);
				break;
		}
		return cal;
	}

	/**
	 * ��ȡָ�����ڵĺ�һ��������
	 * 
	 * @param date ָ�����ڡ�
	 * @return ָ�����ڵĺ�һ��������
	 */
	public static Date getNextWorkingDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getNextWorkingDay(cal).getTime();
	}

	/**
	 * ��ȡָ�����ڵ���һ����
	 * 
	 * @param date
	 * @return
	 */
	public static Date getPreviousMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getPreviousMonth(cal).getTime();
	}

	/**
	 * ��ȡָ�����ڵ���һ����
	 * 
	 * @param cal
	 * @return
	 */
	public static Calendar getPreviousMonth(Calendar cal){
		cal.add(Calendar.MONTH, -1);
		return cal;
	}

	/**
	 * ��ȡָ�����ڵ�ǰһ��
	 * 
	 * @param cal
	 * @return
	 */
	public static Calendar getPreviousDay(Calendar cal){
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal;
	}

	/**
	 * ��ȡָ�����ڵ�ǰһ��
	 * 
	 * @param date
	 * @return
	 */
	public static Date getPreviousDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return getPreviousDay(c).getTime();
	}

	/**
	 * ��ȡָ�����ڵ�ǰһ������
	 * 
	 * @param cal
	 * @return
	 */
	public static Calendar getPreviousWeek(Calendar cal){
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		return cal;
	}

	/**
	 * ��ȡָ�����ڵ�ǰһ������
	 * 
	 * @param date
	 * @return
	 */
	public static Date getPreviousWeek(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getPreviousWeek(cal).getTime();
	}

	/**
	 * �õ�ָ�����ڵ�ǰһ��������
	 * 
	 * @param cal ָ��������
	 * @return ָ�����ڵ�ǰһ��������
	 */
	public static Calendar getPreviousWorkingDay(Calendar cal){
		switch(cal.get(Calendar.DAY_OF_WEEK)){
			case (Calendar.MONDAY):
				cal.add(Calendar.DATE, -3);
				break;
			case (Calendar.SUNDAY):
				cal.add(Calendar.DATE, -2);
				break;
			default:
				cal.add(Calendar.DATE, -1);
				break;
		}
		return cal;
	}

	/**
	 * �õ�ָ�����ڵ�ǰһ��������<br>
	 * <br>
	 * 1.���date�������գ����3��<br>
	 * 2.���date�������������2��, �����1��
	 * 
	 * @param date ָ�����ڡ�
	 * @return ָ�����ڵ�ǰһ��������
	 */
	public static Date getPreviousWorkingDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getPreviousWorkingDay(cal).getTime();
	}

	/**
	 * �ж�ָ�����ڵ�����Ƿ�������
	 * 
	 * @param cal ָ������
	 * @return �Ƿ�����
	 */
	public static boolean isLeapYear(Calendar cal){
		int year = cal.get(Calendar.YEAR);
		return isLeapYear(year);
	}

	/**
	 * �ж�ָ�����ڵ�����Ƿ�������
	 * 
	 * @param date ָ�����ڡ�
	 * @return �Ƿ�����
	 */
	public static boolean isLeapYear(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return isLeapYear(cal);
	}

	/**
	 * �ж�����<br>
	 * <br>
	 * 1. ��400����������<br>
	 * 2. �ܱ�4����ͬʱ���ܱ�100������������
	 * 
	 * @param year ָ������
	 * @return �Ƿ�����
	 */
	public static boolean isLeapYear(int year){
		int r = year % 400;
		if (0 == r)
			return true;

		r = year % 4;
		int i = year % 100;
		return((0 == r && 0 != i) ? true : false);
	}

	/**
	 * �����ַ�����ʽ�����ڻ��<code> java.util.Calendar </code>����
	 * 
	 * @param stringDate �ַ�����ʽ������
	 * @param pattern �����ڵ��ַ�����ʾ��ʽ
	 * @return
	 */
	public static Calendar parseCalendarByFormat(String stringDate, String pattern){
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDateByFormat(stringDate, pattern));
		return cal;
	}

	/**
	 * �����ַ�����ʽ�����ڻ��<code> java.util.Date </code>���� <br>
	 * <br>
	 * <table width="400px">
	 * <tr bgcolor="#ccccff">
	 * <td>stringDate</td>
	 * <td>pattern</td>
	 * </tr>
	 * <tr bgcolor="#ccccff">
	 * <td>2007-12-11</td>
	 * <td>yyyy-MM-dd</td>
	 * </tr>
	 * <tr bgcolor="#ccccff">
	 * <td>2007-12-12 15:30</td>
	 * <td>yyyy-MM-dd HH:mm</td>
	 * </tr>
	 * </table>
	 * 
	 * @param stringDate �ַ�����ʽ������
	 * @param pattern �����ڵ��ַ�����ʾ��ʽ
	 * @return
	 */
	public static Date parseDateByFormat(String stringDate, String pattern){
		Date date = null;
		sdf.applyPattern(pattern);
		try{
			date = sdf.parse(stringDate);
		}catch(ParseException e){
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * ��ʽ����ĵ�ǰ����
	 * 
	 * @param cal
	 * @param pattern
	 * @return
	 */
	public static String toStringByFormat(Calendar cal, String pattern){
		return toStringByFormat(cal.getTime(), pattern);
	}

	/**
	 * ��ʽ����ĵ�ǰ����
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String toStringByFormat(Date date, final String pattern){
		sdf.applyPattern(pattern);
		return sdf.format(date);
	}

	/**
	 * ��ʽ����ĵ�ǰ����<br>
	 * <br>
	 * <table>
	 * <tr bgcolor="#ccccff">
	 * <th align="left">��ʽ</th>
	 * <th align="left">���</th>
	 * </tr>
	 * <tr bgcolor="#ccccff">
	 * <td><code>yyyy-MM-dd</code></td>
	 * <td>2007-11-07</td>
	 * </tr>
	 * <tr bgcolor="#ccccff">
	 * <td>yyyy-MM-dd HH:mm:ss</td>
	 * <td>2007-11-07 16:52:29</td>
	 * </tr>
	 * </table>
	 * 
	 * @param pattern ���ڸ�ʽ
	 * @return ��ʽ����ĵ�ǰ����
	 */
	public static String toStringByFormat(final String pattern){
		return toStringByFormat(new Date(), pattern);
	}

	/**
	 * ��ʽ����ĵ�ǰ����
	 * 
	 * @param pattern ���ڸ�ʽ
	 * @param second ʱ���������<code> long </code>
	 * @return ��ʽ����ĵ�ǰ����
	 */
	public static String toStringByFormat(final String pattern, long second){
		return toStringByFormat(new Date(second), pattern);
	}

	/**
	 * ��ʽ����ĵ�ǰ����
	 * 
	 * @param pattern ���ڸ�ʽ
	 * @param second ʱ���������<code> String </code>
	 * @return ��ʽ����ĵ�ǰ����
	 */
	public static String toStringByFormat(final String pattern, String second){
		Date date = new Date(Long.parseLong(second));
		return toStringByFormat(date, pattern);
	}

	/**
	 * ����
	 */
	public static void main(String[] args){
		test();
	}

	private static void test(){
		String pattern = DateMilliFormat1; //"yyyyMMddHHmmssSSS";

		for(int i = 0; i < 10; i++){
			try{
				Thread.sleep(100);
			}catch(InterruptedException e){}
			System.out.println(toStringByFormat(new Date(), pattern));
		}
	}
}
