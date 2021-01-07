package com.jylee.util;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.routines.DateValidator;

import com.jylee.data.IData;

/** 
* <ul>
*  <li>업무 그룹명 : BLAS </li>
*  <li>서브 업무명 : BLAS 공통유틸 </li>
*  <li>설  명 : BLAS 프로젝트 공통 유틸리티 클래스 : 날짜관련, 정규표현식 패턴, IP변환 등 </li>
*  <li>작성자 : LEE SANG YUB</li>
*  <li>작성일 : 2012. 5. 29 </li>
*  <li>Copyright ⓒ DSNTECH All Right Reserved </li>
*  <li>========================================</li>
*  <li>변경자/변경일 : </li>
*  <li>변경사유/내역 : </li>
*  <li>========================================</li>
* </ul>
*/
public class CommonUtils {
	static final Pattern APP_ID_PATTERN = Pattern.compile("^[a-z]{1,2}");
	static final Pattern MULTI_APP_ID_PATTERN = Pattern.compile("^[a-zA-Z]{1,2}");
	// 주 혹은 월 단위의 샤드가 추가되면서, 3가지 패턴의 날짜 포맷을 필터링해야 한다. - 2014.03.12. Dohyung Kim
	// 일단위 : 8자리(yyyyMMdd) 패턴 이름에 DAY가 포함.
	// 주단위 : 7자리(yyyyMMw) 패턴 이름에 WEEK가 포함.
	// 월단위 : 6자리(yyyyMM) 패턴 이름에 MONTH가 포함.
	static final Pattern SHARD_DAY_PATTERN = Pattern.compile("^[a-zA-Z]{1,2}\\d{8}[a-z]{1,2}\\d"); 
	static final Pattern SHARD_WEEK_PATTERN = Pattern.compile("^[a-zA-Z]{1,2}\\d{7}[a-z]{1,2}\\d");
	static final Pattern SHARD_MONTH_PATTERN = Pattern.compile("^[a-zA-Z]{1,2}\\d{6}[a-z]{1,2}\\d");
	static final Pattern SHARD_DAY_DATE_PATTERN = Pattern.compile("[1-2][0-9][0-9][0-9][0-1][0-9][0-3][0-9]");
	static final Pattern SHARD_WEEK_DATE_PATTERN = Pattern.compile("[1-2][0-9][0-9][0-9][0-1][0-9][1-6]");
	static final Pattern SHARD_MONTH_DATE_PATTERN = Pattern.compile("[1-2][0-9][0-9][0-9][0-1][0-9]");

	static final Pattern DATE_TIME_PATTERN = Pattern.compile("[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]\\p{Space}[0-2][0-9]:[0-5][0-9]:[0-5][0-9]");
	static final String IP_SEPERATOR = "(\\p{Space}|\\p{Punct})";
	static final String IP_COMPONENT = "(1?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";
	static final Pattern IP_PATTERN = Pattern.compile("(?<=(^|["+ IP_SEPERATOR + "]))(" + IP_COMPONENT + "\\.){3}" + IP_COMPONENT+ "(?=([" + IP_SEPERATOR + "]|$))");
	
	public static final String DATE_YEAR = "YEAR";
	public static final String DATE_MONTH = "MONTH";
	public static final String DATE_WEEK = "WEEK";
	public static final String DATE_DAY = "DAY";
	public static final String DATE_HOUR = "HOUR";
	public static final String DATE_MINUTE = "MINUTE";
	public static final String DATE_SECOND = "SECOND";
	
	static Log logger = LogFactory.getLog(CommonUtils.class);
	
	public static String number2Ip(long i) {
        return ((i >> 24 ) & 0xFF) + "." +
               ((i >> 16 ) & 0xFF) + "." +
               ((i >>  8 ) & 0xFF) + "." +
               ( i        & 0xFF);
    }
	
	public static Long ip2Number(String addr) {
        String[] addrArray = addr.split("\\.");
        long num = 0;
        int intval = 0;
        int power = 0;
        for (int i=0;i<addrArray.length;i++) {
            power = 3-i;
            try {
            	intval = Integer.parseInt(addrArray[i])%256;
            }
            catch (NumberFormatException ne){
            	intval = 0;
            }
            num += ((intval * Math.pow(256,power)));
        }
        // System.out.println("** val : "+ num);
        return num;
    }
	
	public static Long ip2Number(String addr, boolean cClass) {
        String[] addrArray = addr.split("\\.");
        long num = 0;
        int intval = 0;
        int power = 0;
        for (int i=0;i<addrArray.length;i++) {
            power = 3-i;
            try {
//            	if(cClass && i==0) {
//            		addrArray[i] = "0";
//            	}
//            	if(cClass && i==1) {
//            		addrArray[i] = "0";
//            	}
//            	if(cClass && i==2) {
//            		addrArray[i] = "0";
//            	}
            	if(cClass && i==3) {
            		addrArray[i] = "0";
            	}
            	intval = Integer.parseInt(addrArray[i])%256;
            }
            catch (NumberFormatException ne){
            	intval = 0;
            }
            num += ((intval * Math.pow(256,power)));
        }
        // System.out.println("** val : "+ num);
        return num;
    }
	
	public static String convQStr(String str) {
		String rtnStr = new String(str);
		// System.out.println("convQStr in : "+rtnStr);
		List<String> list = findSecFmtString(str);
		for(int i=0; i<list.size(); i++) {
			String ss = list.get(i);
			rtnStr = rtnStr.replaceAll(ss, ss.replaceAll(":","\\\\\\\\:"));
		}
		return rtnStr;
	}
	
	public static List<String> findSecFmtString(String s) {
		List<String> ips = new ArrayList<String>();
		
		Pattern p = Pattern.compile("[0-9][0-9]:[0-9][0-9]:[0-9][0-9]");
		Matcher m = p.matcher(s);
		while (m.find()) {
			String ip = m.group();
			ips.add(ip);
		}

		return ips;
	}
    
	public static String getHostName() {
		String hostname = null;
		try {
			java.net.InetAddress addr = java.net.InetAddress.getLocalHost();	    
		    // Get hostname
	        hostname = addr.getHostName();
		}catch (UnknownHostException e) {
		    logger.error("error ***** "+e.getMessage(), e);
			// e.printStackTrace();
		}
		return hostname;
	}
	
	public static String getHostIp() {
		String hostip = null;
		try {
			java.net.InetAddress addr = java.net.InetAddress.getLocalHost();
		    // Get IP Address
			hostip = addr.getHostAddress();
		}catch (UnknownHostException e) {
		    logger.error("error ***** "+e.getMessage(), e);
			// e.printStackTrace();
		}
		return hostip;
	}
	
	public static List<String> getHostNetworkInterfaceIps() {
		List<String> ips = new ArrayList<String>();
		
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetaddr = enumIpAddr.nextElement();
					
					if(IP_PATTERN.matcher(inetaddr.getHostAddress()).matches()) {
						ips.add(inetaddr.getHostAddress());
					}
				}
			}
		} catch (SocketException e) {
		    logger.error("error ***** "+e.getMessage(), e);
			// e.printStackTrace();
		}
		
		return ips;
	}
	
	public static String getDns2Ip(String dnsName) {
		String ipaddr = null;
		try {
			InetAddress ia = InetAddress.getByName(dnsName);
			ipaddr = ia.getHostAddress();
		} catch(UnknownHostException uhe) {
		    logger.error("error ***** "+uhe.getMessage(), uhe);
			// uhe.printStackTrace();
		}
		return ipaddr;
	}
	
	public static String getIp2Dns(String ipAddr) {
		String dns = null;
		try {
			InetAddress ia = InetAddress.getByName(ipAddr);
			dns = ia.getHostName();
		} catch(UnknownHostException uhe) {
		    logger.error("error ***** "+uhe.getMessage(), uhe);
			// uhe.printStackTrace();
		}
		return dns;
	}
	
	public static String getDateStr(long ltime, String format, boolean w3cflag) {
		Date dt = new Date(ltime);
		SimpleDateFormat fmt = new SimpleDateFormat(format);

		if(w3cflag)
			return fmt.format(dt).replace(" ", "T") + "Z";
		else
			return fmt.format(dt);
	}
	
	public static String getDateStr(Date dt, String format, boolean w3cflag) {
        // Date dt = new Date(ltime);
        SimpleDateFormat fmt = new SimpleDateFormat(format);

        if(w3cflag)
            return fmt.format(dt).replace(" ", "T") + "Z";
        else
            return fmt.format(dt);
    }
	
	public static String getDateStr(long ltime, String format, boolean w3cflag, Locale locale) {
		Date dt = new Date(ltime);
		SimpleDateFormat fmt = new SimpleDateFormat(format, locale);

		if(w3cflag)
			return fmt.format(dt).replace(" ", "T") + "Z";
		else
			return fmt.format(dt);
	}
	
	public static String getDateStr(Date dt, String format, boolean w3cflag, Locale locale) {
        // Date dt = new Date(ltime);
        SimpleDateFormat fmt = new SimpleDateFormat(format, locale);

        if(w3cflag)
            return fmt.format(dt).replace(" ", "T") + "Z";
        else
            return fmt.format(dt);
    }
	
	public static Date getDate(String dateStr, String format) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = null;
        
        try {
            simpleDateFormat = new SimpleDateFormat(format);
            date = simpleDateFormat.parse(dateStr);       
        } catch (Exception e) {
            logger.error("error ***** "+e.getMessage(), e);
            // e.printStackTrace();
        }
        return date;
    }
	
	public static Date getDate(String dateStr, String format, Locale locale) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = null;
        
        try {
            simpleDateFormat = new SimpleDateFormat(format, locale);
            date = simpleDateFormat.parse(dateStr);
        } catch (Exception e) {
            logger.error("error ***** "+e.getMessage(), e);
            // e.printStackTrace();
        }
        return date;
    }
	
	public static long getDateLong(String dateStr, String format) {
		Date date = null;
		long millies = 0L;
		SimpleDateFormat simpleDateFormat = null;
		
		try {
			simpleDateFormat = new SimpleDateFormat(format);
			date = simpleDateFormat.parse(dateStr);
			millies = date.getTime();			
		} catch (Exception e) {
		    logger.error("error ***** "+e.getMessage(), e);
			// e.printStackTrace();
		}
		return millies;
	}
	
	public static long getDateLong(String dateStr, String format, Locale locale) {
		Date date = null;
		long millies = 0L;
		SimpleDateFormat simpleDateFormat = null;
		
		try {
			simpleDateFormat = new SimpleDateFormat(format, locale);
			date = simpleDateFormat.parse(dateStr);
			millies = date.getTime();			
		} catch (Exception e) {
		    logger.error("error ***** "+e.getMessage(), e);
			// e.printStackTrace();
		}
		return millies;
	}
	
	public static int getSubDateIntval(String dateStr, String format) {
		int rtn = 0;
		try {
			long lval = getDateLong(dateStr, format);
			rtn = Integer.parseInt(String.valueOf(lval).substring(0, 10));
		} catch(Exception e) {
		    logger.error("error ***** "+e.getMessage(), e);
			// e.printStackTrace();
		}
		return rtn;
	}
	
	public static int getWeekOfMonth( long dateLong ) {
	    String yyyy = getDateStr(dateLong, "yyyy", false);
	    String MM = getDateStr(dateLong, "MM", false);
	    String dd = getDateStr(dateLong, "dd", false);
	    return getWeekOfMonth(yyyy, MM, dd);
//	    int weekofmonth = -1;
//	    Date baseDate = new Date( dateLong );
//        Calendar calendar = Calendar.getInstance();
//        try {
//            calendar.setTime(baseDate);
//            weekofmonth = calendar.get(Calendar.WEEK_OF_MONTH);
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//	    return weekofmonth;
	}
	
	public static int getWeekOfMonth(String yyyy, String MM, String dd) {
        
	    int weekofmonth = -1;
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.set( Integer.parseInt(yyyy), Integer.parseInt(MM)-1, Integer.parseInt(dd) );
            // calendar.setMinimalDaysInFirstWeek(1);
            weekofmonth = calendar.get(Calendar.WEEK_OF_MONTH);
        } catch (Exception e) {
            logger.error("error ***** "+e.getMessage(), e);
            // e.printStackTrace();
        }
        return weekofmonth;
    }
	
	public static String getLastDayOfMonth( long dateLong ) {
	    String yyyy = getDateStr(dateLong, "yyyy", false);
        String MM = getDateStr(dateLong, "MM", false);
        return getLastDayOfMonth(yyyy, MM);
	}
	
	public static String getLastDayOfMonth( String yyyy, String MM ) {
        
        String dayofmonth = null;
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.set( Integer.parseInt(yyyy), Integer.parseInt(MM)-1, 1 );
            dayofmonth = ""+calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            logger.error("error ***** "+e.getMessage(), e);
            // e.printStackTrace();
            return null;
        }
        return dayofmonth;
    }
	
	
	public static long getManipulateDateLong( long dateLong, String type, int addAmount) {
        Date resultDate = null;
        SimpleDateFormat simpleDateFormat = null;
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(new Date(dateLong));
            if (type.equals(DATE_YEAR))
                calendar.add(Calendar.YEAR, addAmount);
            else if (type.equals(DATE_MONTH))
                calendar.add(Calendar.MONTH, addAmount);
            else if (type.equals(DATE_WEEK))
                calendar.add(Calendar.WEEK_OF_MONTH, addAmount);
            else if (type.equals(DATE_DAY))
                calendar.add(Calendar.DATE, addAmount);
            else if (type.equals(DATE_HOUR))
                calendar.add(Calendar.HOUR, addAmount);
            else if (type.equals(DATE_MINUTE))
                calendar.add(Calendar.MINUTE, addAmount);
            else if (type.equals(DATE_SECOND))
                calendar.add(Calendar.SECOND, addAmount);
            resultDate = calendar.getTime();
        } catch (Exception e) {
            logger.error("error ***** "+e.getMessage(), e);
            // e.printStackTrace();
        }

        return resultDate.getTime();
    }
	
	/**
	 * 작성자 : lee,sangyub (2010.12.10) 날짜 스트링 조작함수 입력값 : 
	 * baseDateStr -> 'yyyyMMddHHmmss'형식의 날짜 표시 스트링 
	 * format -> yyyyMMddHHmmss 로 표시한 날짜표시 포맷 (ex : yyyyMMdd HH:mm:ss ) 
	 * type -> 년:YEAR, 월:MONTH, 일:DAY, 시간:HOUR, 분:MINUTE, 초:SECOND 
	 * addAmount -> 더하거나 빼는 년,월,일,시간,분,초의 양 
	 * 사용법 
	 * : 2010년10월01일12시10분15초 를 기준으로 하면 일년전 날짜 스트링을 구하려면 
	 * -> getDateManipulateStr("20101001121015","yyyyMMddHHmmss", DATE_YEAR, -1) 
	 * : 20091001121015 일년후 날짜 스트링을 구하려면 
	 * -> getDateManipulateStr("20101001121015", "yyyyMMddHHmmss", DATE_YEAR, 1) 
	 * : 20111001121015 한달전 날짜 스트링을 구하려면 
	 * -> getDateManipulateStr("20101001121015", "yyyyMMddHHmmss", DATE_MONTH, -1)
	 * : 20100901121015 한달후 날짜 스트링을 구하려면 
	 * -> getDateManipulateStr("20101001121015", "yyyyMMddHHmmss", DATE_MONTH, 1) 
	 * : 일, 시간, 분, 초도 마찬가지로 사용.
	 * 
	 */
	public static String getDateManipulateStr(String baseDateStr, String format, String type, int addAmount) {
		Date baseDate = null;
		Date resultDate = null;
		SimpleDateFormat simpleDateFormat = null;
		Calendar calendar = Calendar.getInstance();

		try {
			simpleDateFormat = new SimpleDateFormat(format);
			baseDate = simpleDateFormat.parse(baseDateStr);
			calendar.setTime(baseDate);
			if (type.equals(DATE_YEAR))
				calendar.add(Calendar.YEAR, addAmount);
			else if (type.equals(DATE_MONTH))
				calendar.add(Calendar.MONTH, addAmount);
			else if (type.equals(DATE_WEEK))
				calendar.add(Calendar.WEEK_OF_MONTH, addAmount);
			else if (type.equals(DATE_DAY))
				calendar.add(Calendar.DATE, addAmount);
			else if (type.equals(DATE_HOUR))
				calendar.add(Calendar.HOUR, addAmount);
			else if (type.equals(DATE_MINUTE))
				calendar.add(Calendar.MINUTE, addAmount);
			else if (type.equals(DATE_SECOND))
				calendar.add(Calendar.SECOND, addAmount);
			resultDate = calendar.getTime();
		} catch (Exception e) {
		    logger.error("error ***** "+e.getMessage(), e);
			// e.printStackTrace();
		}

		return simpleDateFormat.format(resultDate);
	}
	
	public static String getDateManipulateStr(long dateLong, String format, String type, int addAmount) {
        // Date baseDate = null;
        Date resultDate = null;
        SimpleDateFormat simpleDateFormat = null;
        Calendar calendar = Calendar.getInstance();

        try {
            simpleDateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
            // baseDate = simpleDateFormat.parse(baseDateStr);
            calendar.setTime(new Date(dateLong));
            if (type.equals(DATE_YEAR))
                calendar.add(Calendar.YEAR, addAmount);
            else if (type.equals(DATE_MONTH))
                calendar.add(Calendar.MONTH, addAmount);
            else if (type.equals(DATE_WEEK))
                calendar.add(Calendar.WEEK_OF_MONTH, addAmount);
            else if (type.equals(DATE_DAY))
                calendar.add(Calendar.DATE, addAmount);
            else if (type.equals(DATE_HOUR))
                calendar.add(Calendar.HOUR, addAmount);
            else if (type.equals(DATE_MINUTE))
                calendar.add(Calendar.MINUTE, addAmount);
            else if (type.equals(DATE_SECOND))
                calendar.add(Calendar.SECOND, addAmount);
            resultDate = calendar.getTime();
        } catch (Exception e) {
            logger.error("error ***** "+e.getMessage(), e);
            // e.printStackTrace();
        }

        return simpleDateFormat.format(resultDate);
    }
	
	
	public static String getDateManipulateStr(String baseDateStr, String format, String outformat, String type, int addAmount) {
		Date baseDate = null;
		Date resultDate = null;
		SimpleDateFormat simpleDateFormat = null;
		SimpleDateFormat outDateFormat = new SimpleDateFormat(outformat);
		Calendar calendar = Calendar.getInstance();

		try {
			simpleDateFormat = new SimpleDateFormat(format);
			baseDate = simpleDateFormat.parse(baseDateStr);
			calendar.setTime(baseDate);
			if (type.equals(DATE_YEAR))
				calendar.add(Calendar.YEAR, addAmount);
			else if (type.equals(DATE_MONTH))
				calendar.add(Calendar.MONTH, addAmount);
			else if (type.equals(DATE_WEEK))
				calendar.add(Calendar.WEEK_OF_MONTH, addAmount);
			else if (type.equals(DATE_DAY))
				calendar.add(Calendar.DATE, addAmount);
			else if (type.equals(DATE_HOUR))
				calendar.add(Calendar.HOUR, addAmount);
			else if (type.equals(DATE_MINUTE))
				calendar.add(Calendar.MINUTE, addAmount);
			else if (type.equals(DATE_SECOND))
				calendar.add(Calendar.SECOND, addAmount);
			resultDate = calendar.getTime();
		} catch (Exception e) {
		    logger.error("error ***** "+e.getMessage(), e);
			// e.printStackTrace();
		}
		return outDateFormat.format(resultDate);
		// return simpleDateFormat.format(resultDate);
	}
	
	public static String getDateManipulateStr(String baseDateStr, String format, String outformat, Locale locale) {
		Date baseDate = null;
		Date resultDate = null;
		SimpleDateFormat simpleDateFormat = null;
		SimpleDateFormat outDateFormat = new SimpleDateFormat(outformat, locale);
		Calendar calendar = Calendar.getInstance();

		try {
			simpleDateFormat = new SimpleDateFormat(format, locale);
			baseDate = simpleDateFormat.parse(baseDateStr);
			calendar.setTime(baseDate);
			resultDate = calendar.getTime();
		} catch (Exception e) {
		    logger.error("error ***** "+e.getMessage(), e);
			// e.printStackTrace();
		}
		return outDateFormat.format(resultDate);
		// return simpleDateFormat.format(resultDate);
	}
	

//	public static String getLastDayOfMonth(String baseDateStr, String format) {
//		Date baseDate = null;
//		SimpleDateFormat simpleDateFormat = null;
//		Calendar calendar = Calendar.getInstance();
//		
//		try {
//			simpleDateFormat = new SimpleDateFormat(format);
//			baseDate = simpleDateFormat.parse(baseDateStr);
//			calendar.setTime(baseDate);
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return ""+calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//	}
	
	
	// DAY_OF_MONTH
	public static String getDayOfMonthDateStr(String baseDateStr, String format, String rformat, int addMonth, int day) {
		Date baseDate = null;
		Date resultDate = null;
		SimpleDateFormat simpleDateFormat = null, rtnSimpleDateFormat=null;
		Calendar calendar = Calendar.getInstance();

		try {
			simpleDateFormat = new SimpleDateFormat(format);
			baseDate = simpleDateFormat.parse(baseDateStr);
			calendar.setTime(baseDate);
			if(day <= calendar.get(Calendar.DAY_OF_MONTH))
				calendar.add(Calendar.MONTH, addMonth+1);
			else 
				calendar.add(Calendar.MONTH, addMonth);
			calendar.set(Calendar.DAY_OF_MONTH, day);
			resultDate = calendar.getTime();
		} catch (Exception e) {
		    logger.error("error ***** "+e.getMessage(), e);
			// e.printStackTrace();
		}
		rtnSimpleDateFormat = new SimpleDateFormat(rformat);
		return rtnSimpleDateFormat.format(resultDate);
	}
	
	// WEEK_OF_MONTH
	// DAY_OF_WEEK
	public static String getDayOfWeekOfMonthDateStr(String baseDateStr, String format, String rformat, int addMonth, int nthWeek, int weekDay) {
		Date baseDate = null;
		Date resultDate = null;
		SimpleDateFormat simpleDateFormat = null, rtnSimpleDateFormat=null;
		Calendar calendar = Calendar.getInstance();

		try {
			simpleDateFormat = new SimpleDateFormat(format);
			baseDate = simpleDateFormat.parse(baseDateStr);
			calendar.setTime(baseDate);
			
//			System.out.println(baseDateStr+"는 "+calendar.get(calendar.YEAR)+"년도 "+(calendar.get(calendar.MONTH)+1)+"월의 "+calendar.get(calendar.WEEK_OF_MONTH)+"번째주의 "
//					+getDayOfWeek(calendar.get(calendar.DAY_OF_WEEK))+"입니다.");
			int baseNthWeek = calendar.get(calendar.WEEK_OF_MONTH);
			int baseWeekDay = calendar.get(calendar.DAY_OF_WEEK);
			if(baseNthWeek > nthWeek)
				calendar.add(Calendar.MONTH, addMonth+1);
			else if (baseNthWeek == nthWeek) {
				if(baseWeekDay >= weekDay)
					calendar.add(Calendar.MONTH, addMonth+1);
				else
					calendar.add(Calendar.MONTH, addMonth);
			} else 
				calendar.add(Calendar.MONTH, addMonth);
			
			int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			// System.out.println(calendar.get(calendar.YEAR)+"년도 "+(calendar.get(calendar.MONTH)+1)+"월의 lastDay : "+lastDay);
			
			boolean checked = false;
			int checkNthWeek = 0;
	        for(int i=1;i<=lastDay;i++) {
	            calendar.set(Calendar.DAY_OF_MONTH, i);
	            // System.out.println("i:"+i+" "+calendar.get(calendar.WEEK_OF_MONTH)+" "+calendar.get(calendar.DAY_OF_WEEK));
	            if(nthWeek == calendar.get(calendar.WEEK_OF_MONTH)) {
	            	checkNthWeek = nthWeek;
	            	checked = true;
	            	// System.out.println("break..."+calendar.get(calendar.WEEK_OF_MONTH)+" "+calendar.get(calendar.DAY_OF_WEEK));
	            	if(weekDay == calendar.get(calendar.DAY_OF_WEEK)) {
	            		// System.out.println("break..."+calendar.get(calendar.WEEK_OF_MONTH)+" "+calendar.get(calendar.DAY_OF_WEEK));
	            		break;
	            	}
	            }
	            if(checked && checkNthWeek!=nthWeek)
	            	break;
	        }
			
			resultDate = calendar.getTime();
		} catch (Exception e) {
		    logger.error("error ***** "+e.getMessage(), e);
			// e.printStackTrace();
		}
		rtnSimpleDateFormat = new SimpleDateFormat(rformat);
		return rtnSimpleDateFormat.format(resultDate);
	}
	
	//해당 날짜로 부터 요일 얻어옴
	public static String getDateDay(String date, String dateType) throws Exception {
	    String day = "" ;
	    SimpleDateFormat dateFormat = new SimpleDateFormat(dateType) ;
	    Date nDate = dateFormat.parse(date) ;
	    Calendar cal = Calendar.getInstance() ;
	    cal.setTime(nDate);
	    int dayNum = cal.get(Calendar.DAY_OF_WEEK) ;
	    switch(dayNum){
	        case 1:
	            day = "7";//"SUN";
	            break ;
	        case 2:
	            day = "1";//"MON";
	            break ;
	        case 3:
	            day = "2";//"TUE";
	            break ;
	        case 4:
	            day = "3";//"WED";
	            break ;
	        case 5:
	            day = "4";//"THU";
	            break ;
	        case 6:
	            day = "5";//"FRI";
	            break ;
	        case 7:
	            day = "6";//"SAT";
	            break ;
	    }
	    return day ;
	}
	
	public static long getDateIntervalLong(String sDateStr, String sformat, String eDateStr, String eformat) {
		Date sDate = null, eDate = null;
		long sMillies = 0L, eMillies = 0L;
		SimpleDateFormat simpleDateFormat = null;
		Calendar calendar = Calendar.getInstance();
		try {
			simpleDateFormat = new SimpleDateFormat(sformat);
			sDate = simpleDateFormat.parse(sDateStr);
			sMillies = sDate.getTime();
			
			simpleDateFormat = new SimpleDateFormat(eformat);
			eDate = simpleDateFormat.parse(eDateStr);
			eMillies = eDate.getTime();
		} catch (Exception e) {
		    logger.error("error ***** "+e.getMessage(), e);
			// e.printStackTrace();
		}
		return (eMillies - sMillies);
	}
	
	public static float getDocBoost(String dateStr, String dtFormat) {
		float boost = 1f;
		if(isValidDateTime(dateStr)) {
			// System.out.println("valid date string.. "+dateStr);
			// float interval = 100000 + getDateIntervalLong(dateStr.substring(0, 4), "yyyy", dateStr, "yyyy-MM-dd HH:mm:ss") / (1000 * 60) ;
			float interval = 100000 + getDateIntervalLong(dateStr.substring(0, 4), "yyyy", dateStr, dtFormat) / (1000 * 60) ;
			System.out.println("interval "+interval);
			boost = interval * (float)Math.pow(10, (Integer.parseInt(dateStr.substring(0, 4)) - 2012)%38);
		}
		return boost;
	}
	
	
	public static List<String> getSerialDateStr(String beginDateStr, String endDateStr, String format, String type, int addAmount) {
		List<String> dateArray = new ArrayList<String>();
		
		try {
			int i=0;
			if( Integer.parseInt(beginDateStr) > Integer.parseInt(endDateStr) )
				return dateArray;
			
			// dateArray = new ArrayList<String>();
			while(true) {
				String dateStr = getDateManipulateStr(beginDateStr, format, type, (i*addAmount));
				dateArray.add(dateStr);
				if(Integer.parseInt(dateStr) >= Integer.parseInt(endDateStr) ) {
					break;
				}
				i++;
			}
		} catch(Exception e) {
		    logger.error("error ***** "+e.getMessage(), e);
			// e.printStackTrace();
			return null;
		}	
		return dateArray;
	}
	
	/**
	 * DateTime(DT) Pattern maker 사용법 
	 * yyyy --> [1-2][0-9][0-9][0-9]
	 * MM ----> [0-1][0-9]
	 * dd ----> [0-3][0-9]
	 * HH ----> [0-2][0-9]
	 * mm ----> [0-5][0-9]
	 * ss ----> [0-5][0-9]
	 * 'space'-> \\p{Space}
	 */
	public static Pattern makeDTPattern(String dtFormat) {
		String pattern = new String(dtFormat);
		if(dtFormat.indexOf("yyyy")>=0)
			pattern = pattern.replaceAll("yyyy", "[1-2][0-9][0-9][0-9]");
		if(dtFormat.indexOf("MM")>=0)
			pattern = pattern.replaceAll("MM", "[0-1][0-9]");
		if(dtFormat.indexOf("dd")>=0)
			pattern = pattern.replaceAll("dd", "[0-3][0-9]");
		if(dtFormat.indexOf("HH")>=0)
			pattern = pattern.replaceAll("HH", "[0-2][0-9]");
		if(dtFormat.indexOf("mm")>=0)
			pattern = pattern.replaceAll("mm", "[0-5][0-9]");
		if(dtFormat.indexOf("ss")>=0)
			pattern = pattern.replaceAll("ss", "[0-5][0-9]");
		if(dtFormat.indexOf(" ")>=0)
			pattern = pattern.replaceAll(" ", "\\\\p{Space}");
		
		return Pattern.compile(pattern);
	}
	
	public static String findDTPatternStr(Pattern p, String s) {
		String matchedstr = null;

		Matcher m = p.matcher(s);
		if (m.find()) {
			matchedstr = m.group();
		}
		return matchedstr;
	}
	
	
	public static boolean isValidDateTime(String datetime) {
		Matcher m = DATE_TIME_PATTERN.matcher(datetime);
		return m.find();
	}
	
	public static boolean isValidIP(String ipstr) {
        Matcher m = IP_PATTERN.matcher(ipstr);
        return m.find();
    }
	
	public static boolean isValidPatternStr(Pattern p, String s) {
		Matcher m = p.matcher(s);
		return m.find();
	}
	
	public static String findDateTime(String s) {
		String dateTime = null;

		Matcher m = DATE_TIME_PATTERN.matcher(s);
		if (m.find()) {
			dateTime = m.group();
		}
		return dateTime;
	}
	
	public static int randomRange(int n1, int n2) {
		return (int) (Math.random() * (n2 - n1 + 1)) + n1;
	}
	
	public static String listToString(List<? extends Object> list, String delimeter) {
		String delim = "";
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < list.size(); i++) {
			sb.append(delim).append("" + list.get(i));
			delim = delimeter;
		}

		return sb.toString();
	}	
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	
	public static boolean isLong(String s) {
	    try { 
            Long.parseLong(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        return true;
	}
	
	public static boolean isFloat(String s) {
        try { 
            Float.parseFloat(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
	
	public static boolean isDouble(String s) {
        try { 
            Double.parseDouble(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
	
	public static Number getNumber(Object obj) {
	    Number number = null;
	    try {
	        if( obj instanceof Integer) {
	            number = ((Number) obj).intValue();
	        } else if (obj instanceof Long) {
	            number = ((Number) obj).longValue();
	        } else if (obj instanceof Float) {
	            number = ((Number) obj).floatValue();
	        } else if (obj instanceof Double) {
	            number = ((Number) obj).doubleValue();
	        } else if (obj instanceof Short) {
	            number = ((Number) obj).shortValue();
	        } else if (obj instanceof String) {
	            number = Long.parseLong((String)obj);
	        }
	    } catch(Exception e) {
	        logger.error("error ***** "+e.getMessage(), e);
	    }
	    return number;
	}
	
	/**
	 * compare == 0 : num1과 num2가 같음.
	 * compare > 0 : num1이 num2보다 큼.
	 * compare < 0 : num1이 num2보다 작음.
	 */
	public static int compareTo(Number num1, Number num2) {
	    if (num1 == null || num2 == null) {
            throw new IllegalArgumentException("The numbers must not be null");
        }
	    return ((Comparable) num1).compareTo(num2);
	}
	
	/**
	 * inData에 fieldName이 키로 들어있지 않은 경우 defaultValue를 반환하는 함수.
	 * @param fieldName
	 * @param inData
	 * @param defaultValue
	 * @return
	 */
	public static String getDefaultIfNotContain(String fieldName, IData inData, String defaultValue){
		String result = "";
		String[] keys = inData.getKeyNames();
		ArrayList<String> keyList = new ArrayList<String>();
		for(String key : keys){
			keyList.add(key);
		}
		if(keyList.contains(fieldName) && inData.getValueByKey(fieldName)!=null){
			result = inData.getValueByKey(fieldName);
		} else {
			result = defaultValue;
		}
		return result;
	}
	
	
	/**
	 * 입력받은 날짜와 날짜 포맷, 로케일 정보를 사용하여 원하는 로케일을 사용하는 날짜 포맷으로 변환한다.
	 * @param date : 변환할 날짜.
	 * @param fromDateFormat : 변환할 날짜의 날짜 포맷.
	 * @param fromLocale : 변환할 날짜의 Locale.
	 * @param toDateFormat : 변환될 날짜의 날짜 포맷.
	 * @param toLocale : 변환된 날짜의 Locale.
	 * @return
	 */
	public static String getDateFromDateLocaleFormat(String date, String fromDateFormat, Locale fromLocale, String toDateFormat, Locale toLocale){
		return getDateStr(getDateLong(date, fromDateFormat, fromLocale), toDateFormat, false, toLocale);
	}
	
	/**
	 * 입력받은 날짜와 insideChangeFormat를 사용하여 원하는 로케일을 사용하는 날짜 포맷으로 변환한다.
	 * @param insideChangeFormat : 변환할 날짜의 insideChangeFormat.
	 * @param date : 변환할 날짜.
	 * @param dateFormat : 변환될 날짜의 날짜 포맷.
	 * @param toLocale : 변환될 날짜의 locale.
	 * @return
	 */
	public static String getDateFromInsideChangeFormat(String date, String insideChangeFormat, String toDateFormat, String toLocaleStr){
		Locale fromLocale = null;
		String fromDateFormat = null;
		if(insideChangeFormat.startsWith("Locale")){
			List<String> pieces = NormalizerUtils.split(false, 2, "|", insideChangeFormat);
			fromLocale = getLocaleFromString(pieces.get(0));
			fromDateFormat = pieces.get(1);
		} else {
			fromLocale = Locale.ENGLISH;
			fromDateFormat = insideChangeFormat;
		}
		return getDateFromDateLocaleFormat(date, fromDateFormat, fromLocale, toDateFormat, getLocaleFromString(toLocaleStr));				
	}
	
	// string으로 받은 locale정보를 Locale로 반환.
	public static Locale getLocaleFromString(String str){
		Locale locale = null;
		if(str.equals("Locale.ENGLISH")){
			locale = Locale.ENGLISH;
		} else if(str.equals("Locale.KOREA")){
			locale = Locale.KOREA;
		} else if(str.equals("Locale.CHINA")){
			locale = Locale.CHINA;
		} else if(str.equals("Locale.JAPAN")){
			locale = Locale.JAPAN;
		} else if(str.equals("Locale.GERMANY")){
			locale = Locale.GERMANY;
		} else if(str.equals("Locale.FRANCE")){
			locale = Locale.FRANCE;
		} else if(str.equals("Locale.ITALY")){
			locale = Locale.ITALY;
		} else if(str.equals("Locale.TAIWAN")){
			locale = Locale.TAIWAN;
		} else if(str.equals("Locale.CANADA")){
			locale = Locale.CANADA;
		} else if(str.equals("Locale.UK")){
			locale = Locale.UK;
		}
		
		return locale;
	}
	
    public static boolean isDateValid(String dateData, String dateFormat) {
        if (dateData == null ) {
            return false;
        }
        DateValidator validator = DateValidator.getInstance();
        return validator.isValid(dateData, dateFormat);
    }
    
    
    public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                if(o1.getKey()==null)
                    return 1;
                if(o2.getKey()==null)
                    return -1;
                return (o1.getKey()).compareTo(o2.getKey());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                if(o1.getValue()==null)
                    return 1;
                if(o2.getValue()==null)
                    return -1;
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    
    
    public static double truncate(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = (long) value;
        return (double) tmp / factor;
    }
    
//    public static void main(String args[]) {
//        DateValidator validator = DateValidator.getInstance();
//        System.out.println(validator.isValid("20150721", "yyyyMMdd"));
//        
//    }
    
    /*
     * CPU BUSY WAIT - CPU를 무진장 사용하면서 WAIT하게 된다. 
     * 정확하긴해도 가능하면 사용해서는 안됨. 
     */
    public static void nanoWait(long nanoInterval) {
        long start = System.nanoTime();
        long end=0;
        do {
            end = System.nanoTime();
        } while(start + nanoInterval >= end);
    }
    
    public static void main(String args[]) {
        System.out.println((1234l/100)*100);
        
        long ctimel = System.currentTimeMillis();
        System.out.println(getDateStr(ctimel, "yyyyMMdd HHmmss", false));
        System.out.println(getDateManipulateStr(ctimel, "yyyyMMdd HHmmss", DATE_MINUTE, -2));
        
    }
}