package com.xu.spider4j.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {
	public static final int YYYYMMDDHHMMSS = 0;
	public static final int YYYYMMDDHHMM = 1;
	public static final int YYYYMMDDHH = 2;
	public static final int YYYYMMDD = 3;
	public static final int HH = 4;
	public static final int HHMM = 5;
	public static final int yyyyMMdd = 6;
	public static final int yyyyMMddHHmm = 7;
	public static final int yyyyMMddTHHmmssZ = 8; // 2017-11-11T16:11:00Z
	public static final int yyyyMMddHH = 9;
	private static final String FORMAT[] = {
			"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH", "yyyy-MM-dd", "HH", "HH:mm", //5
			"yyyyMMdd", "yyyyMMddHHmm", "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyyMMddHH"  //9
	};

	public static String dateToString(Date date, int index) {

		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT[index]);
		return sdf.format(date);
	}

	/**
	 * 获取给定时间的前n天或者后n天 n为正向后移 n为负向前移
	 */
	public static String otherDay(String basicsDay, int n, int index) {
		Date date = stringToDate(basicsDay, index);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, n);
		date = calendar.getTime();
		return dateToString(date, index);
	}

	public static String otherDay(Date date, int n, int index) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, n);
		date = calendar.getTime();
		return dateToString(date, index);
	}

	public static Date stringToDate(String date, int index) {
		if (date == null) {
			return null;
		}
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT[index]);
		try {
			d = sdf.parse(date);
			return d;
		} catch (ParseException e) {

			e.printStackTrace();

		}

		return null;
	}

	public static String stringSubStr(String date, int index) {

		try {
			Date d = stringToDate(date, index);
			if (d != null) {
				return dateToString(d, index);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static int dateCompare(String date1, String date2, int index) {
		if (date1 == null || date2 == null) {
			return -1;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT[index]);
		try {
			if (sdf.parse(date1).getTime() == sdf.parse(date2).getTime()) {
				return 0;
			} else if (sdf.parse(date1).getTime() < sdf.parse(date2).getTime()) {
				return 1;
			} else {
				return 2;
			}
		} catch (ParseException e) {

			e.printStackTrace();

		}

		return -1;
	}

	public static int dayForWeek(String pTime) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	public static int currentForWeek() {

		Calendar c = Calendar.getInstance();
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	public static long timeToDate(long time) {

		try {
			String date = dateToString(new Date(time), YYYYMMDD);

			if (date != null) {
				Date d = stringToDate(date, YYYYMMDD);
				if (d != null) {
					return d.getTime();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	// String类型的时间转换成时间戳
	public static long stringToTime(String date, int index) {

		try {
			Date d = stringToDate(date, index);
			if (d != null) {
				return d.getTime();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;

	}

	public static String startReduceEndByHour(String start, String end) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			long startT = format.parse(start).getTime(); // 定义上机时间
			long endT = format.parse(end).getTime();
			long ss = (endT - startT) / (1000); // 共计秒数
			int hour = (int) ss / 60 / 60; // 共计分钟数
			return hour + "";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 定义下机时间
		return "0";
	}

	public static String startReduceEnd(String start, String end) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			long startT = format.parse(start).getTime(); // 定义上机时间
			long endT = format.parse(end).getTime();
			long ss = (endT - startT) / (1000); // 共计秒数
			int MM = (int) ss / 60; // 共计分钟数
			return MM + "";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 定义下机时间
		return "0";
	}

	public static long startHHReduceEnd(String start, String end) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
		try {
			long startT = format.parse(start).getTime(); // 定义上机时间
			long endT = format.parse(end).getTime();
			long ss = (endT - startT) / (1000); // 共计秒数
			int MM = (int) ss / 60; // 共计分钟数
			return MM;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 定义下机时间
		return 0;
	}

	public static int startEND(String start, String end) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			long startT = format.parse(start).getTime(); // 定义上机时间
			long endT = format.parse(end).getTime();
			long ss = (endT - startT) / (1000); // 共计秒数
			int MM = (int) ss / 60; // 共计分钟数
			return (int) MM;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 定义下机时间
		return 0;
	}

	public static String dateDayYMDHMS() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}

	public static String dateDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(new Date());
	}

	/**
	 * 前一个小时
	 */
	public static String beforeOneHourToNowDate(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		try {
			Calendar calendar = Calendar.getInstance();
			/* HOUR_OF_DAY 指示一天中的小时 */
			calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
			return df.format(df.parse(date).getTime() - 60 * 60 * 1000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return df.format(new Date());
	}

	/**
	 * 前2个小时
	 */
	public static String beforeOneHourToNowDate2(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		try {
			Calendar calendar = Calendar.getInstance();
			/* HOUR_OF_DAY 指示一天中的小时 */
			calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
			return df.format(df.parse(date).getTime() - 60 * 60 * 1000 * 2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return df.format(new Date());
	}

	/**
	 * 前3个小时
	 */
	public static String beforeOneHourToNowDate3(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		try {
			Calendar calendar = Calendar.getInstance();
			/* HOUR_OF_DAY 指示一天中的小时 */
			calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
			return df.format(df.parse(date).getTime() - 60 * 60 * 1000 * 3);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return df.format(new Date());
	}

	/**
	 * hou一个小时
	 */
	public static String beforeOneHourToHOUDate(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		try {
			Calendar calendar = Calendar.getInstance();
			/* HOUR_OF_DAY 指示一天中的小时 */
			calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
			return df.format(df.parse(date).getTime() + 60 * 60 * 1000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return df.format(new Date());
	}

	/**
	 * 日期转毫秒
	 */
	public static Long millionSeconds(String date, int f) {
		SimpleDateFormat df = new SimpleDateFormat(FORMAT[f]);
		long millionSeconds = 0;
		try {
			millionSeconds = df.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		} // 毫秒
		return millionSeconds;
	}

	/**
	 * @param day n	 获取前(后)n天的日期
	 */
	public static String intervalDay(String date, int day) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT[3]);
		Date thisDate = new Date();
		try {
			Calendar calendar = Calendar.getInstance(); // 得到日历
			calendar.setTime(sdf.parse(date));// 把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, day); // 设置
			thisDate = calendar.getTime(); // 得到前一天的时间
			return sdf.format(thisDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sdf.format(new Date());
	}


	/**
	 * 获取后一个小时
	 */
	public static String getOneHoursAfterTime(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT[YYYYMMDDHH]);
		try {
			Date dBefore = new Date();
			Calendar calendar = Calendar.getInstance(); // 得到日历
			calendar.setTime(sdf.parse(date));// 把当前时间赋给日历
			calendar.add(Calendar.HOUR_OF_DAY, +1); // 设置为前一天
			dBefore = calendar.getTime(); // 得到前一天的时间

			return sdf.format(dBefore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sdf.format(new Date());
	}

	public static String getOneHoursAfterTime(String date, int i) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT[YYYYMMDDHH]);
		try {
			Date dBefore = new Date();
			Calendar calendar = Calendar.getInstance(); // 得到日历
			calendar.setTime(sdf.parse(date));// 把当前时间赋给日历
			calendar.add(Calendar.HOUR_OF_DAY, i); // 设置为前一天
			dBefore = calendar.getTime(); // 得到前一天的时间

			return sdf.format(dBefore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sdf.format(new Date());
	}

	/**
	 * 两个时间之间的时间集合 格式自定义
	 */
	public static List<String> getDatesBetweenTwoDateByStyle(String sDate, String eDate, String dateStyle) {
		List<String> returnList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat(dateStyle);
		Date beginDate = null;
		Date endDate = null;
		try {
			beginDate = sdf.parse(sDate);
			sDate = sdf.format(beginDate);
			endDate = sdf.parse(eDate);
			eDate = sdf.format(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		returnList.add(sDate);// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				Date time = cal.getTime();
				returnList.add(sdf.format(time));
			} else {
				break;
			}
		}
		returnList.add(eDate);// 把结束时间加入集合
		return returnList;
	}

	//两个时间之间的天数     no.2-no.1     true取绝对值
	public static int getNumbersDifference(String firstDate, String sencondDate, boolean flag) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//跨年不会出现问题
		//如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 0
		Date fDate = null;
		Date oDate = null;
		try {
			fDate = sdf.parse(firstDate);
			oDate = sdf.parse(sencondDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long days = (oDate.getTime() - fDate.getTime()) / (1000 * 60 * 60 * 24);
		if (flag == true) {
			days = Math.abs(days);
		}
		return (int) days;
	}

	//格式转换  yyyy-MM-dd -> MM月dd日
	public static String getMMddDateStyle(String date) {
		return date.split("-")[1] + "月" + date.split("-")[2] + "日";
	}


	//格式转换  yyyy-MM-dd -> MM月dd日
	public static List<String> getMMddDateStyle(List<String> list) {
		List<String> returnList = new ArrayList<String>();
		for (String date : list) {
			String newDate = date.split("-")[1] + "月" + date.split("-")[2] + "日";
			returnList.add(newDate);
		}
		return returnList;
	}

	//得到当前月份
	public static String getThisMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String format2 = sdf.format(new Date());
		System.out.println(format2);
		return format2;
	}

	//日期格式转换       StrUtil 也有1份
	public static String getDayType(String dateString) {
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
		Date parseDate = null;
		try {
			parseDate = sdf0.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String format = sdf.format(parseDate);
		System.out.println(format);
		return format;
	}

	//获得 当前日期   格式  2017-07-05 00:00:00
	public static String getCurrentDay() {
		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String returnDate = sdf.format(currentDate);
		return returnDate;
	}

	//月份格式转换    2017-07 =>  2017_07_    方便Mapper的sql查询
	public static String monthTransType(String monthString) {
		String year = monthString.substring(0, monthString.indexOf('-'));
		String month = monthString.substring(monthString.indexOf('-') + 1);
		StringBuilder sb = new StringBuilder();
		sb.append(year).append('-').append(month).append('-');
		return sb.toString();
	}

	//格式转换
	public static String yyyyMMDDString(String dateString) {
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf0.parse(dateString);
			return sdf0.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 得到当前日期	14
	 */
	public static int getThisOneDayNumber() {
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf0.format(new Date());
		String lastNumber = strDate.substring(strDate.lastIndexOf('-') + 1, strDate.length());
		return Integer.parseInt(lastNumber);
	}

	//日期格式转换    2017-07-05   =>     2017-07-05 00:00:00
	public static String dateTransType(String dateString) {
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
		String returnDate = null;
		Date date = null;
		try {
			date = sdf0.parse(dateString);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			returnDate = sdf.format(date);
			System.out.println("returnDate --- " + returnDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}

	//获取1个月的时间
	public static List<String> getOneMonth(String month) {//2017-07
		String oneDate = month + "-01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date firstMonthdate = null;
		try {
			firstMonthdate = sdf.parse(oneDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<String> returnList = new ArrayList<String>();
		Date date = getMonthStart(firstMonthdate);
		Date monthEnd = getMonthEnd(firstMonthdate);
		while (!date.after(monthEnd)) {
			returnList.add(sdf.format(date));
			date = getNext(date);
		}
		return returnList;
	}

	//获取上半月的集合
	public static List<String> getFirstHalfMonth(String month) {
		List<String> returnList = new ArrayList<String>();
		List<String> oneMonth = getOneMonth(month);
		for (int i = 0; i < 15; i++) {
			returnList.add(oneMonth.get(i));
		}
		return returnList;
	}

	//获取下半月的集合
	public static List<String> getSecondHalfMonth(String month) {
		List<String> returnList = new ArrayList<String>();
		List<String> oneMonth = getOneMonth(month);
		for (int i = 15, size = oneMonth.size(); i < size; i++) {
			returnList.add(oneMonth.get(i));
		}
		return returnList;
	}

	//下一天
	private static Date getNext(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

	//月初
	private static Date getMonthStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (1 - index));
		return calendar.getTime();
	}

	//月末
	private static Date getMonthEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (-index));
		return calendar.getTime();
	}

}
