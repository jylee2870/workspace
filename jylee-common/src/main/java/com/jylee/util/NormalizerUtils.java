package com.jylee.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jylee.regex.CommonPatterns;
import com.jylee.util.DiffMatchPatcher.Diff;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.timeZone;
import com.maxmind.geoip.regionName;

/*
 * NormalizerUtils의 각 함수들은 문자열조작시 performance를 최우선으로 하여 작성한다. 
 * by lee,sangyub 2013.11.12
 */
public class NormalizerUtils {
	
	public enum CONFIG {
		trim, format, replace, substring, convert, split, keyvalue, branch, branchresult, result, rearrange, keep, copy, ignore, newstring, geoip, regex, makekeyvalue, meta, operator, none;
		
		public static CONFIG toCONFIG(String str) {
			try {
				return valueOf(str);
			} catch(Exception e) {
				return none;
			}
		}
	}
	
	public static final int REPLACE_ALL = 0;
	public static final int REPLACE_FIRST = 1;
	public static final int REPLACE_LAST = 2;
	public static final int REPLACE_RANGE = 3;
	
	public static final int SUBSTRING_INCLUDE = 0;
	public static final int SUBSTRING_EXCLUDE = 1;
	public static final int SUBSTRING_CHOOSE = 2;
	
	private static Log logger = LogFactory.getLog(NormalizerUtils.class);
	
	// BLAS Normalizer의 trim{...} configuration에 대응하는 함수이다.
	/*
	 * trim 옵션 : 이 flag를 설정하면 문자열의 맨 처음과 끝부분에 추가되어 있는 whitespace [ \t\n\x0B\f\r]를 제거한다. 
	 * squeeze 옵션 : 이 flag를 설정하면 문자열내의 공백(' ')이나 탭(\t)이 여러개 중복되어 나올경우 하나로만 인식한다. 
	 */
	public static String trim(boolean trim, boolean squeeze, String s) {
		if(squeeze) {
			char[] arr = s.toCharArray();
			int next = 1; // refers to the next character in the result.
	
			for (int i = 1; i < arr.length; i++) {
				arr[next] = arr[i];
				if (arr[next] != ' ' && arr[next] != '\t')
					next++;
				else if (arr[next - 1] != ' ' && arr[next - 1] != '\t')
					next++;
			}
			if(trim)
				return new String(arr, 0, next).trim();
			else
				return new String(arr, 0, next);
		} else {
			if(trim)
				return s.trim();
			else
				return s;
		}
	}
	
	/*
	 * 두 '문자열'(instr:입력문자열, convstr:변환문자열)내의 모든 문자들을 각각 비교하여 그 차이점을 아래와 같이 포맷화하여 문자열로 리턴한다. 
	 * (s:same 문자같음, a:append 문자추가됨, r:remove 문자삭제됨)
	 * EX : getDiffFormats("20131107 16:03:50.234Z", "2013/11/07 160350_234") --> ssssassasssssrssrssrasssr
	 *    : 문자열을 분해하여 비교하면 
	 *    : 처음 2013은 같음	------------> ssss
	 *    : / 문자가 추가됨	------------> a
	 *    : 11 같음 			------------> ss
	 *    : / 문자가 추가됨	------------> a
	 *    : 07 16 같음 		------------> sssss
	 *    : : 문자 삭제됨		------------> r 
	 *    : 03 같음			------------> ss
	 *    : : 문자 삭제됨		------------> r
	 *    : 50 같음			------------> ss
	 *    : . 문자 삭제됨		------------> r
	 *    : _ 문자 추가됨		------------> a
	 *    : 234 문자 같음		------------> sss
	 *    : Z 문자 삭제됨 		------------> r
	 */
	public static String getDiffFormats(String instr, String convstr ) {

		DiffMatchPatcher dmp = new DiffMatchPatcher();
		
 		List<Diff> ldf = dmp.diff_main(instr, convstr);
		
		StringBuffer format = new StringBuffer();
		// StringBuffer refstr = new StringBuffer();
		
		for(int idx=0;idx<ldf.size();idx++) {
			Diff df = ldf.get(idx);
			// System.out.println( df.operation.name()+" "+df.text);
			
			char[] arr = df.text.toCharArray();
			switch(df.operation) {
			case EQUAL:
				for(int i=0;i<arr.length;i++) {
					format.append('s');
				}
				break;
			case INSERT:
				for(int i=0;i<arr.length;i++) {
					format.append('a');
					// refstr.append(arr[i]);
				}
				break;
			case DELETE:
				for(int i=0;i<arr.length;i++) {
					format.append('r');
				}
				break;
			}
		}
		return format.toString();
	}
	
	/*
	 * 두 '문자열'(instr:입력문자열, convstr:변환문자열)내의 모든 문자들을 각각 비교하여 그 차이점중 추가되는 문자만 골라 문자열로 리턴한다. 
	 * getDiffFormats("20131107 16:03:50.234Z", "2013/11/07 160350_234") --> ssssassasssssrssrssrasssr
	 * 의 예제에서 'a'에 해당되는 문자들만을 골라 문자열로 리턴한다. 
	 * EX : getDiffCharacters("20131107 16:03:50.234Z", "2013/11/07 160350_234") --> "//_"
	 */
	public static String getDiffCharacters(String instr, String convstr ) {

		DiffMatchPatcher dmp = new DiffMatchPatcher();
		
 		List<Diff> ldf = dmp.diff_main(instr, convstr);
		
		StringBuffer chstr = new StringBuffer();
		
		for(int idx=0;idx<ldf.size();idx++) {
			Diff df = ldf.get(idx);
			
			char[] arr = df.text.toCharArray();
			switch(df.operation) {
			case EQUAL:
				break;
			case INSERT:
				for(int i=0;i<arr.length;i++) {
					chstr.append(arr[i]);
				}
				break;
			case DELETE:
				break;
			}
		}
		return chstr.toString();
	}
	
	// BLAS Normalizer의 format{...} configuration에 대응하는 함수이다. 
	/*
	 * getDiffFormats함수에서 구한 문자열비교 포맷(format)과 getDiffCharacters 함수에서 구한 추가 문자열을 가지고서
	 * 입력되는 문자열을 원하는 변환문자열로 변환하는 함수이다. 
	 * EX : format("20131107 16:03:50.234Z", "ssssassasssssrssrssrasssr", "//_") --> 2013/11/07 160350_234
	 *    : format("20131210 09:10:21.113Z", "ssssassasssssrssrssrasssr", "//_") --> 2013/12/10 091021_113
	 */
	public static String format(String str, /*String strlength, */String fmt, String chstr) throws Exception {
		StringBuffer outb = new StringBuffer();
		int stri = 0;
		int chstri = 0;
//		try {
//		if (str.length() != Integer.parseInt(strlength))
//			return outb.toString();
			for(int i=0;i<fmt.length();i++) {
				if(fmt.charAt(i)=='s') {
					outb.append(str.charAt(stri));
					stri++;
				} else if(fmt.charAt(i)=='a') {
					outb.append(chstr.charAt(chstri));
					chstri++;
				} else if(fmt.charAt(i)=='r') {
					stri++;
				}
			}
//		} catch(Exception e) {
//			logger.error("***** check... str:"+str+" fmt"+fmt+" chstr:"+chstr);
//			logger.error("***** error : "+e.getMessage(), e);
//		}
		return outb.toString();
	}
	
	// BLAS Normalizer의 replace{...} configuration에 대응하는 함수이다. 
	/*
	 * 원 문자열내에 발생하는 구문자열(old string:os)을 신문자열(new string)으로 변경하여 리턴하는 함수이다. 
	 * 다음과 같은 변경옵션이 있다. 
	 *   REPLACE_ALL: 원문자열내에서 모든 구문자열을 신문자열로 변경, 
	 *   REPLACE_FIRST: 원문자열내에서 최초 발생하는 구문자열만을 신문자열로 변경, 
	 *   REPLACE_LAST : 원문자열내에서 마지막으로 발생하는 구문자열만을 신문자열로 변경.
	 * EX : 원문자열 --> 192.168.100.130||FW||Lowest||20131001110020||Allow||Log||124.57.85.7 -> 178.100.144.118||Allow
	 *    : replace(NormalizerUtils.REPLACE_ALL, str, "||", "@")	--> 192.168.100.130@FW@Lowest@20131001110020@Allow@Log@124.57.85.7 -> 178.100.144.118@Allow
	 *    : replace(NormalizerUtils.REPLACE_FIRST, str, "||", "@")	--> 192.168.100.130@FW||Lowest||20131001110020||Allow||Log||124.57.85.7 -> 178.100.144.118||Allow
	 *    : replace(NormalizerUtils.REPLACE_LAST, str, "||", "@")	--> 192.168.100.130||FW||Lowest||20131001110020||Allow||Log||124.57.85.7 -> 178.100.144.118@Allow
	 */
	public static String replace(int option, String source, String os, String ns) {
	    if (source == null) {
	        return null;
	    }
	    int i = 0;
	    if(option == REPLACE_LAST)
	    	i = source.lastIndexOf(os);
	    else 
	    	i = source.indexOf(os, i);
	    // if ((i = source.indexOf(os, i)) >= 0) {
	    if (i >= 0) {
	        char[] sourceArray = source.toCharArray();
	        char[] nsArray = ns.toCharArray();
	        int oLength = os.length();
	        StringBuffer buf = new StringBuffer (sourceArray.length);
	        buf.append (sourceArray, 0, i).append(nsArray);
	        i += oLength;
	        int j = i;
	        
	        if(option == REPLACE_FIRST || option == REPLACE_LAST) {
	        	buf.append (sourceArray, j, sourceArray.length - j);
	        } else {
		        // Replace all remaining instances of oldString with newString.
		        while ((i = source.indexOf(os, i)) > 0) {
		            buf.append (sourceArray, j, i - j).append(nsArray);
		            i += oLength;
		            j = i;
		        }
		        buf.append (sourceArray, j, sourceArray.length - j);
	        }
	        source = buf.toString();
	        buf.setLength (0);
	    }
	    return source;
	}
	
	// BLAS Normalizer의 substring{...} configuration에 대응하는 함수이다. 
	/*
	 * 원 문자열내에서 접두사(문자열)과 접미사(문자열)내의 문자열을 리턴하는 함수이다. 
	 * 접두사(문자열)와 접미사(문자열)를 포함하는 지 여부에 따라 다음과 같은 옵션이 있다. 
	 *   SUBSTRING_INCLUDE : 접두사(문자열)와 접미사(문자열)를 포함하여 결과를 리턴함. 
	 *   SUBSTRING_EXCLUDE : 접두사(문자열)와 접미사(문자열)를 제외한후에 결과를 리턴함.
	 * 문자열내에 접두사(문자열)혹은 접미사(문자열)의 발생이 여러 건이 경우에 최초발생 혹은 최종발생유무도 고려한다. 
	 * EX : 원문자열 --> blas{{..cfgfirst..cfgfirst..}}blas{{~~cfgmedium~~cfgmedium~~}}blas{{||cfglast||cfglast||}}
	 * CASE1 : substring(NormalizerUtils.SUBSTRING_INCLUDE, true, true, cfgstr, "blas{", "}") 
	 *         해석 : 접두사접미사 포함, 접두사 최초발생, 접미사 최초발생 
	 *         --> blas{{..cfgfirst..cfgfirst..}
	 *       : substring(NormalizerUtils.SUBSTRING_INCLUDE, true, true, cfgstr, "blas{{", "}}")
	 *         해석 : 접두사접미사 포함, 접두사 최초발생, 접미사 최초발생
	 *         --> blas{{..cfgfirst..cfgfirst..}}
	 * CASE2 : substring(NormalizerUtils.SUBSTRING_INCLUDE, true, false, cfgstr, "blas{{", "}}")
	 *         해석 : 접두사접미사 포함, 접두사 최초발생, 접미사 최종발생 
	 *         --> blas{{..cfgfirst..cfgfirst..}}blas{{~~cfgmedium~~cfgmedium~~}}blas{{||cfglast||cfglast||}}
	 * CASE3 : substring(NormalizerUtils.SUBSTRING_INCLUDE, false, true, cfgstr, "blas{{", "}}")
	 *         해석 : 접두사접미사 포함, 접두사 최종발생, 접미사 최초발생
	 *         --> blas{{||cfglast||cfglast||}}
	 * CASE4 : substring(NormalizerUtils.SUBSTRING_INCLUDE, false, false, cfgstr, "blas{{", "}}")
	 *         해석 : 접두사접미사 포함, 접두사 최종발생, 접미사 최종발생
	 *         --> blas{{||cfglast||cfglast||}}
	 * CASE5 : substring(NormalizerUtils.SUBSTRING_EXCLUDE, true, true, cfgstr, "blas{{", "}}")
	 *         해석 : 접두사접미사 포함안함, 접두사 최초발생, 접미사 최초발생
	 *         --> ..cfgfirst..cfgfirst..
	 * CASE6 : substring(NormalizerUtils.SUBSTRING_EXCLUDE, true, false, cfgstr, "blas{{", "}}")
	 *         해석 : 접두사접미사 포함안함, 접두사 최초발생, 접미사 최종발생 
	 *         --> ..cfgfirst..cfgfirst..}}blas{{~~cfgmedium~~cfgmedium~~}}blas{{||cfglast||cfglast||
	 * CASE7 : substring(NormalizerUtils.SUBSTRING_EXCLUDE, false, true, cfgstr, "blas{{", "}}")
	 *         해석 : 접두사접미사 포함안함, 접두사 최종발생, 접미사 최초발생
	 *         --> ||cfglast||cfglast||
	 * CASE8 : substring(NormalizerUtils.SUBSTRING_EXCLUDE, false, false, cfgstr, "blas{{", "}}")
	 *         해석 : 접두사접미사 포함안함, 접두사 최종발생, 접미사 최종발생
	 *         --> ||cfglast||cfglast||
	 */
	public static String substring(int option, boolean isprefixfirst, boolean issuffixfirst, String org, String prefix, String suffix) {
		String rtn = null;
		int begin = -1;
		int end = -1;
		int inchk = -1;
		try {
			if(option == SUBSTRING_INCLUDE) {
				if(isprefixfirst)
					begin = org.indexOf(prefix);
				else
					begin = org.lastIndexOf(prefix);
				if(issuffixfirst)
					end = org.indexOf(suffix, begin+prefix.length())+suffix.length();
				else
					end = org.lastIndexOf(suffix)+suffix.length();
				if (begin>=0 && end>0 && end>begin) {
					rtn = org.substring(begin, end);
				}
			} else {
				inchk = org.indexOf(prefix);
				if(isprefixfirst) {
					begin = org.indexOf(prefix)+prefix.length();
					// begin = org.indexOf(prefix);
				} else { 
					begin = org.lastIndexOf(prefix)+prefix.length();
					// begin = org.lastIndexOf(prefix);
				}
				if(issuffixfirst)
					end = org.indexOf(suffix, begin);
				else
					end = org.lastIndexOf(suffix);
				
				// begin += prefix.length();
				if (inchk>=0 && end>0 && end>begin) {
					rtn = org.substring(begin, end);
				} else if (inchk>=0 && end>0 && end==begin){
					rtn = "";
				}
			}
			// System.out.println("begin:"+begin+" end:"+end);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return rtn;
	}
	
	// SUBSTRING_CHOOSE : nth번째 prefix출현 시점에서 suffix 출현시점 사이의 문자열을 반환.
	// String line = "<186>Nov 09 2014 23:59:58: %ASA-2-106006: Deny inbound UDP from 172.43.121.138/49154 to 172.23.121.19/161 on interface outside"
	// substring(SUBSTRING_CHOOSE, 2, line, "-", ":")
	// 2번째 발생한 '-'와 이후의 ':'사이의 106006을 반환. 
	public static String substring(int option, int nth, String org, String prefix, String suffix) {
		String rtn = null;
		int begin = -1;
		int end = -1;
		try {
			if(option == SUBSTRING_INCLUDE) {
				for(int i=0;i<nth;i++) {
					if(i==0)
						begin = org.indexOf(prefix, 0);
					else
						begin = org.indexOf(prefix, end);
					if(begin<0 ) {
						// System.out.println("begin:"+begin);
						return rtn;
					}
					end = org.indexOf(suffix, begin+prefix.length());
					// System.out.println("begin:"+begin+",end:"+end);
					if(begin<0 || end<0 )
						return rtn;
				}
				end += suffix.length();
				if (begin>=0 && end>0 && end>begin) {
					rtn = org.substring(begin, end);
				}
			} else if(option == SUBSTRING_EXCLUDE){
				for(int i=0;i<nth;i++) {
					if(i==0)
						begin = org.indexOf(prefix, 0);
					else
						begin = org.indexOf(prefix, end);
					if(begin<0 ) {
						// System.out.println("begin:"+begin);
						return rtn;
					}
					end = org.indexOf(suffix, begin+prefix.length());
					// System.out.println("begin:"+begin+",end:"+end);
					if(begin<0 || end<0 )
						return rtn;
				}
				begin += prefix.length();
				if (begin>=0 && end>0 && end>begin) {
					rtn = org.substring(begin, end);
				}
			} else {
				List<String> rtnList = Lists.newArrayList(Splitter.on(prefix).limit(nth+1).split(org));
				rtn = Lists.newArrayList(Splitter.on(suffix).limit(2).split(rtnList.get(nth))).get(0);
				return rtn;
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return rtn;
	}
	
	public static List<String> substring(int option, String org, String prefix, String suffix) {
		int i=0;
		List<String> rtn = null;
		int begin = -1;
		int end = -1;
		try {
			if(option == SUBSTRING_INCLUDE) {
				while(true) {
					if(i==0)
						begin = org.indexOf(prefix, 0);
					else
						begin = org.indexOf(prefix, end);
					if(begin<0 ) {
						// System.out.println("begin:"+begin);
						break;
					}
					end = org.indexOf(suffix, begin+prefix.length());
					// System.out.println("begin:"+begin+",end:"+end);
					if(begin<0 || end<0 )
						break;
					end += suffix.length();
					if (begin>=0 && end>0 && end>begin) {
						if(rtn==null)
							rtn = new ArrayList<String>();
						rtn.add( org.substring(begin, end) );
					}
					i++;
				}
			} else {
				while(true) {
					if(i==0)
						begin = org.indexOf(prefix, 0);
					else
						begin = org.indexOf(prefix, end+1);
					if(begin<0 ) {
						// System.out.println("begin:"+begin);
						break;
					}
					end = org.indexOf(suffix, begin+prefix.length());
					// System.out.println("begin:"+begin+",end:"+end);
					if(begin<0 || end<0 )
						break;
					begin += prefix.length();
					if (begin>=0 && end>0 && end>begin) {
						if(rtn==null)
							rtn = new ArrayList<String>();
						rtn.add( org.substring(begin, end) );
					}
					i++;
				}
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return rtn;
	}
	
	
	// BLAS Normalizer의 convert{...} configuration에 대응하는 함수이다. 
	/*
	 * 코드성 데이터는 MAP으로 작성하여 convert시에 performance를 고려하도록 한다. 
	 * ('if else'구문이나 'switch구문'은 코드성데이터(Key-Value쌍)가 많아지면 worst case의 경우 KEY값 비교시 성능이 떨어질 수 있다. 
	 * 하지만 MAP으로 관리하면 코드가 많아진다고 하더라도 동일한 응답결과를 보장할 수 있다. 
	 * 대소문자를 구분할 경우에는 일반적인 HashMap을 사용하도록 하고, 
	 * 대소문자를 구분할 필요가 없는 경우에는 TreeMap을 아래 예제처럼 사용하도록 한다. 
	 * // convert test : Case sensitive(대소문자 구분)
	 * Map<String,String> hmap = new HashMap<String,String>();
	 * hmap.put("TCP", "1");
	 * hmap.put("UDP", "2");
	 * hmap.put("ICMP", "3");
	 * 
	 * EX : convert(hmap, "TCP") --> 1
	 *    : convert(hmap, "UDP") --> 2
	 *    : convert(hmap, "ICMP") --> 3
	 * 
	 * // convert test : Case insensitive(대소문자 구분X)
	 * Map<String,String> tmap = new TreeMap<String,String>(String.CASE_INSENSITIVE_ORDER);
	 * tmap.put("TCP", "1");
	 * tmap.put("UDP", "2");
	 * tmap.put("ICMP", "3");
	 * 
	 * EX : convert(tmap, "Tcp") --> 1
	 *      convert(tmap, "tcp") --> 1
	 *      convert(tmap, "tcP") --> 1
	 *      convert(tmap, "udP") --> 2
	 *      convert(tmap, "IcmP") --> 3
	 */
	public static String convert(Map<String,String> map, String key) {
		return (String)map.get(key);
	}
	
	public static String convert(Map<String,String> map, String key, String defaultval) {
		String val = (String)map.get(key);
		if(val!=null)
			return val;
		logger.info(key + " is not mapped to anything");
		return defaultval;
	}
	
	// BLAS Normalizer의 구분자로 문자열을 쪼개는 split{...} configuration에 대응하는 함수이다. 
	/*
	 * 문자열을 특정 구분자(delimeter)로 쪼개어(split) 필드토큰 리스트로 작성하는 함수는 
	 * 구글의 guava 라이브러리를 사용하도록 한다. (performance 이슈때문임.)
	 * split이후 각 필드별로 trim을 할지여부와 split의 개수를 제한하는 기능을 제공한다. 
	 * EX : 원문자열 --> 192.168.100.130,FW, Lowest, 20131001110020, Allow,Log,124.57.85.7 -> 178.100.144.118 , Allow ,124.57.85.7, 10, 178.100.144.118, 21, TCP, 21/TCP
	 *    : split(true, 0, ',', msgstr)
	 *    : split(false, 0, ',', msgstr)
	 *    : split(true, 3, ',', msgstr)
	 *    : split(false, 3, ',', msgstr)
	 */
	public static List<String> split(boolean resulttrim, int limit, char delimeter, String line) {
		List<String> rtn = null;
		if(resulttrim) {
			if(limit>0)
				rtn = Lists.newArrayList( Splitter.on(delimeter).limit(limit).trimResults().split(line) );
			else 
				rtn = Lists.newArrayList( Splitter.on(delimeter).trimResults().split(line) );
		} else {
			if(limit>0)
				rtn = Lists.newArrayList( Splitter.on(delimeter).limit(limit).split(line) );
			else 
				rtn = Lists.newArrayList( Splitter.on(delimeter).split(line) );
		}
		return rtn;
	}
	
	public static List<String> split(boolean resulttrim, int limit, String delimeter, String line) {
		List<String> rtn = null;
		if(resulttrim) {
			if(limit>0)
				rtn = Lists.newArrayList( Splitter.on(delimeter).limit(limit).trimResults().split(line) );
			else 
				rtn = Lists.newArrayList( Splitter.on(delimeter).trimResults().split(line) );
		} else {
			if(limit>0)
				rtn = Lists.newArrayList( Splitter.on(delimeter).limit(limit).split(line) );
			else 
				rtn = Lists.newArrayList( Splitter.on(delimeter).split(line) );
		}
		return rtn;
	}
	
	public static List<String> splitOnPattern(boolean resulttrim, int limit, String pattern, String line) {
	    List<String> rtn = null;
        if(resulttrim) {
            if(limit>0)
                rtn = Lists.newArrayList( Splitter.onPattern(pattern).limit(limit).trimResults().split(line) );
            else 
                rtn = Lists.newArrayList( Splitter.onPattern(pattern).trimResults().split(line) );
        } else {
            if(limit>0)
                rtn = Lists.newArrayList( Splitter.onPattern(pattern).limit(limit).split(line) );
            else 
                rtn = Lists.newArrayList( Splitter.onPattern(pattern).split(line) );
        }
        return rtn;
	}
	
	// BLAS Normalizer의 원하는 길이만큼의 배열로 문자열을 쪼개는 split{...} configuration에 대응하는 함수이다. 
	/*
	 * 문자열을 특정 길이의 배열로 쪼개어(split) 필드토큰 리스트로 작성하는 함수는 구글의 guava 라이브러리를 변경하여 사용하도록 한다. (performance 이슈때문임.)
	 * EX : 원문자열(msgstr) --> abcdefghijklmnopqrstuvwxyz가나다라마바사1234567890
	 *    : split("26,7,10", msgstr) 
	 *        abcdefghijklmnopqrstuvwxyz
	 *        가나다라마바사
	 *        1234567890
	 *    : split("26,7", msgstr) 
	 *        abcdefghijklmnopqrstuvwxyz
	 *        가나다라마바사
	 *        1234567890
	 *    : split("26,6,100", msgstr) 
	 *        abcdefghijklmnopqrstuvwxyz
	 *        가나다라마바
	 *        사1234567890
	 *    : split("26,6", msgstr) 
	 *        abcdefghijklmnopqrstuvwxyz
	 *        가나다라마바
	 *        사1234567890
	 *    : split("26,6,4", msgstr) 
	 *        abcdefghijklmnopqrstuvwxyz
	 *        가나다라마바
	 *        사123
	 *        4567890
	 */
	public static List<String> split(String indexes, String line) {
		List<String> indexeslist = split(true, 0, ",",indexes);
		int indexesarr[] = null;
		if(indexeslist!=null) {
			indexesarr = new int[indexeslist.size()];
			for(int i=0; i<indexeslist.size();i++) {
				indexesarr[i] = Integer.parseInt(indexeslist.get(i));
			}
		}
		
		return split(indexesarr, line);
	}
	
	public static List<String> split(int indexes[], String line) {
		if(indexes!=null && line!=null)
			return Lists.newArrayList(com.jylee.util.Splitter.Length(indexes).split(line));
		return null;
	}
	
	
	public static Map codeFile2Map(String codefile, boolean casesensitive, String delimeter) {
		Map<String,String> codemap = null;
		try {
//			logger.info("***** codeFile2Map codefile:"+codefile);
//			logger.info("***** codeFile2Map codefile classLoader:"+CodeGenerator.class.getClassLoader());
//			// logger.info("***** codeFile2Map codefile URI:"+CodeGenerator.class.getClassLoader().getResource(codefile).toURI());
//			ClassLoader cl = CodeGenerator.class.getClassLoader();
//			URL[] urls = ((URLClassLoader)cl).getURLs();
//			for(URL url:urls) {
//				logger.info("***** classpath : "+url.getFile());
//			}
			
			// File codef = new File(CodeGenerator.class.getClassLoader().getResource(codefile).toURI());
			File codef = null;
			if(codefile.indexOf("/")>=0) 
				codef = new File(codefile);
			else
				codef = new File("/home/yeoksam/flume_ng/blum/libext/"+codefile);
			FileReader fr = new FileReader(codef);
			BufferedReader in = new BufferedReader(fr);
			
			String line = null;
			while ( (line = in.readLine())!=null) {
				if(line.startsWith("#") || (line.indexOf(delimeter)<0))
					continue;
				List<String> linesplit = split(false, 2, delimeter, line);
				if(codemap == null) {
					if(casesensitive)
						codemap = new HashMap<String,String>();
					else
						codemap = new TreeMap<String,String>(String.CASE_INSENSITIVE_ORDER);
				}
				codemap.put(linesplit.get(0), linesplit.get(1));
			}
			in.close();
			fr.close();
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		return codemap;
	}
	
	// BLAS Normalizer의 rearrange{...} configuration에 대응하는 함수이다. 
	/*
	 * getSelectedArrange 함수에서 구한 start, end 조합의 position정보를 통해 
	 * 입력 string 내의 표시 위치를 변경한다.  
	 * EX : rearrange("20131107 16:03:50.234Z", "6,7,4,5,0,3,9,10,12,13,15,16,18,20") --> 07112013160350234
	 *    이후에 format("07112013160350234", "ssassassssassassassasss","// ::.") --> 07/11/2013 16:03:50.234
	 *    위의 두 단계에 의해 위치가 변경된 포맷을 처리한다.
	 */
	public static String rearrange(String str, int[] fmt) {
		StringBuffer outb = new StringBuffer();
//		System.out.println(fmtLength);

		for (int i = 0; i < fmt.length; i++)
		{
//			System.out.println("656 : "+outb.toString()+"fmt["+i+"] : "+ fmt[i]+" ----string length : "+ str.length()+"\n");
			if (fmt[i] > str.length()-1)
				return outb.toString();
		}
		for(int i=0; i < fmt.length; i=i+2) {
			outb.append(str.substring(fmt[i], fmt[i+1]+1));
//			System.out.println(outb.toString()+"\n");
		}
		return outb.toString();
	}
	
	/*
	 * 두 '문자열'(instr:입력문자열, convstr:변환문자열)내의 모든 문자들을 각각 비교하여 동일한 문자의 idx를 뽑아낸다. 
	 * (s:same 문자같음, a:append 문자추가됨, r:remove 문자삭제됨)
	 * EX : getSelectedPosition("20131107 16:03:50.234Z", "2013") 
	 *    : 0,3 (0 : 시작 idx, 3: 끝 idx)
	 */
	public static String getSelectedPosition(String instr, String selectstr ) {
		StringBuffer outb = new StringBuffer();
		char[] instrChar = instr.toCharArray();
		char[] selectstrChar = selectstr.toCharArray();
		boolean stop = false;
		String Start = null;
		String End = null;
		for (int i = 0 ; i < instrChar.length; i++)
		{
			if (selectstrChar.length > (instrChar.length-i))
				break;
			
			if (instrChar[i] == selectstrChar[0])
			{
				for (int j = 0; j<selectstrChar.length; j++)
				{
					if (instrChar[i+j] != selectstrChar[j])
					{
						break;
					}
					if (j == selectstrChar.length-1)
					{
						Start = Integer.toString(i);
						End = Integer.toString(i+j);
						outb.append(Start);
						outb.append(",");
						outb.append(End);
						stop = true;
					}
				}
			}
		}
		if (stop)
			return outb.toString();
		else
			return "none";
	}
	
//	public static List<String> split(boolean resulttrim, int limit, String delimeter, String line) {
//		List<String> rtn = null;
//		if(resulttrim) {
//			if(limit>0)
//				rtn = Lists.newArrayList( Splitter.on(delimeter).limit(limit).trimResults().split(line) );
//			else 
//				rtn = Lists.newArrayList( Splitter.on(delimeter).trimResults().split(line) );
//		} else {
//			if(limit>0)
//				rtn = Lists.newArrayList( Splitter.on(delimeter).limit(limit).split(line) );
//			else 
//				rtn = Lists.newArrayList( Splitter.on(delimeter).split(line) );
//		}
//		return rtn;
//	}
	
	// BLAS Normalizer의 keyvalue{...} configuration에 대응하는 함수이다. 
	/*
	 * 문자열에서 키(key) 값(value)를 구분하여 Map 데이터를 작성하여 리턴하는 함수이다. 구글의 guava 라이브러리를 변경하여 사용한다. 
	 * (예외 처리등에 대하여 변경하였음. 구글에서는 key value가 쌍으로 제대로 있지않으면 에러를 리턴하고 종료하는데, 변경한 부분은 에러를 무시하고 계속처리하도록 처리함. )
	 * 문자열을 특정 구분자(split delimeter)로 쪼개고(split), trim을 수행한 후 쪼개어진 부분에 대하여  키값구분자(kvseparator)를 사용하여 
	 * 키(key) 값(value)을 나눈후 이를 Map에 저장하는 방식이다. 
	 * EX : 원문자열(str) --> date=2014/07/18 11:20:17,    name=홍길동,    e-mail=hongkildong@www.company.com,    phone=010-1234-5678,    department=연구소,    comment=this is hong. nice to see you all
	 *    : keyvalue(",", "=", true, str)
	 *      map에 저장되는 key:value 결과 --> 
	 *          date:2014/07/18 11:20:17
	 *          name:홍길동
	 *          e-mail:hongkildong@www.company.com
	 *          phone:010-1234-5678
	 *          department:연구소
	 *          comment:this is hong. nice to see you all
	 */
	public static Map<String, String> keyvalue(String splitdelimeter, String kvseparator, boolean trim, String s) {
		Map<String, String> kvmap = null;
		
		s = replaceBetweenQuotations( replaceBetweenQuotations(s, splitdelimeter, "^^"), kvseparator, "vv" );
		
		if(trim)
			kvmap = com.jylee.util.Splitter.on(splitdelimeter).trimResults().withKeyValueSeparator(kvseparator, splitdelimeter, "^^", kvseparator, "vv").split(s);
		else
			kvmap = com.jylee.util.Splitter.on(splitdelimeter).withKeyValueSeparator(kvseparator, splitdelimeter, "^^", kvseparator, "vv").split(s);
		
		return kvmap;
	}
	
	public static Map<String, String> keyvalue(String splitdelimeter, String kvseparator, String subss, String subse, boolean trim, String s) {
		Map<String, String> kvmap = null;
		
		s = replaceBetweens( replaceBetweens(s, subss, subse, splitdelimeter, "^^"), subss, subse, kvseparator, "vv" );
		
		if(trim)
			kvmap = com.jylee.util.Splitter.on(splitdelimeter).trimResults().withKeyValueSeparator(kvseparator, splitdelimeter, "^^", kvseparator, "vv").split(s);
		else
			kvmap = com.jylee.util.Splitter.on(splitdelimeter).withKeyValueSeparator(kvseparator, splitdelimeter, "^^", kvseparator, "vv").split(s);
		
		return kvmap;
	}
	
	// 위의 keyvalue 함수를 변형하여 원문자열에서 모든 키(key)값(value) 쌍을 다 구하는 것이 아니라, 
	// 원하는 key들에 대한 value만을 구할 때 사용하는 함수이다. 
	/*
	 * 다음 예제에서는 원문자열의 키값쌍중에서 키가 date, name, phone인 것에 대한 값만을 구하여 Map으로 저장한다. 
	 * EX : 원문자열(str) --> date=2014/07/18 11:20:17,    name=홍길동,    e-mail=hongkildong@www.company.com,    phone=010-1234-5678,    department=연구소,    comment=this is hong. nice to see you all
	 *    : keyvalue(",", "=", true, str, "date","name","phone")
	 *      map에 저장되는 key:value 결과 --> 
	 *          date:2014/07/18 11:20:17
	 *          name:홍길동
	 *          phone:010-1234-5678
	 *    : keyvalue(",", "=", true, str, "name","phone","comment","e-mail")
	 *      map에 저장되는 key:value 결과 --> 
	 *          name:홍길동
	 *          phone:010-1234-5678
	 *          comment:this is hong. nice to see you all
	 *          e-mail:hongkildong@www.company.com
	 */
	public static Map<String, String> keyvalue(String splitdelimeter, String kvseparator, boolean trim, String s, String...keys) {
		Map<String, String> kvmap = null;
		
		s = replaceBetweenQuotations( replaceBetweenQuotations(s, splitdelimeter, "^^"), kvseparator, "vv" );
		
		if(trim)
			kvmap = com.jylee.util.Splitter.on(splitdelimeter).trimResults().withKeyValueSeparator(kvseparator, splitdelimeter, "^^", kvseparator, "vv", keys).split(s);
		else
			kvmap = com.jylee.util.Splitter.on(splitdelimeter).withKeyValueSeparator(kvseparator, splitdelimeter, "^^", kvseparator, "vv", keys).split(s);
		
		return kvmap;
	}
	
	public static Map<String, String> keyvalue(String splitdelimeter, String kvseparator, String subss, String subse, boolean trim, String s, String...keys) {
		Map<String, String> kvmap = null;
		
		s = replaceBetweens( replaceBetweens(s, subss, subse, splitdelimeter, "^^"), subss, subse, kvseparator, "vv" );
		
		if(trim)
			kvmap = com.jylee.util.Splitter.on(splitdelimeter).trimResults().withKeyValueSeparator(kvseparator, splitdelimeter, "^^", kvseparator, "vv", keys).split(s);
		else
			kvmap = com.jylee.util.Splitter.on(splitdelimeter).withKeyValueSeparator(kvseparator, splitdelimeter, "^^", kvseparator, "vv", keys).split(s);
		
		return kvmap;
	}
	
	// 문자열내부에 따옴표(")로 둘러쌓인 서브문자열내의 문자들을 변경하고자 할때 사용한다.  
	/* 
	 * 예를들어, 원문자열에 다음과 같을 때, 
	 * 원문자열(str) : id=firewall time="2014-07-22 13:25:06" fw=(none) pri=7 rule=53 proto=53/udp src=192.168.100.80 dst=8.8.8.8 sent=309 rcvd=0 duration=0 msg="source interface = Internal"
	 * 따옴표(")로 둘러쌓인 서브문자열 --> "2014-07-22 13:25:06"과 "source interface = Internal"의 " "를 "^^"로 바꾸고 싶으면 다음처럼 하면 된다. 
	 *   : replaceBetweenQuotations(str, " ", "^^")
	 * 결과 : id=firewall time="2014-07-22^^13:25:06" fw=(none) pri=7 rule=53 proto=53/udp src=192.168.100.80 dst=8.8.8.8 sent=309 rcvd=0 duration=0 msg="source^^interface^^=^^Internal"
	 */
	public static String replaceBetweenQuotations(String source, String os, String ns) {
		if(source==null)
			return null;
		
		char[] sourceArray = source.toCharArray();
		char[] nsArray = ns.toCharArray();
		int oLength = os.length();
		StringBuffer buf = new StringBuffer();
		
	    int i=0, j=0, s=0, e=0;
	    s = source.indexOf("\"", s);
	    e = source.indexOf("\"", s+1);
	    
	    while( s>=0 && e>=0 && e>s) {
	    	i = source.indexOf(os, s);
	    	while(i>s && i<e) {
	    		// System.out.println("s:"+s+", i:"+i+", e:"+e);
	    		buf.append(sourceArray, j, i-j).append(nsArray);
	    		i += oLength;
	    		j=i;
	    		i = source.indexOf(os, j);
	    	}
	    	
	    	s = e+1;
	    	s = source.indexOf("\"", s);
	    	e = source.indexOf("\"", s+1);
	    }
	    buf.append (sourceArray, j, sourceArray.length - j);
	    
	    return buf.toString();
	}
	
	// 문자열내부에 시작문자(열)과 종료문자(열)로 둘러쌓인 서브문자열들 내의 문자들을 다른 문자로 변경하고자 할때 사용한다.  
	/* 
	 * 예를들어, 원문자열에 다음과 같을 때, 
	 * 원문자열(str) : id=firewall time=[2014-07-22 13:25:06] fw=(none) pri=7 rule=53 proto=53/udp src=192.168.100.80 dst=8.8.8.8 sent=309 rcvd=0 duration=0 msg=[source interface = Internal]
	 * 시작문자"["와 종료문자"]"로 둘러쌓인 서브문자열 --> [2014-07-22 13:25:06]과 [source interface = Internal]의 공백( )을 "^^"로 바꾸고 싶으면 다음처럼 하면 된다. 
	 *   : replaceBetweens(str, "[", "]", " ", "^^")
	 * 결과 : id=firewall time=[2014-07-22^^13:25:06] fw=(none) pri=7 rule=53 proto=53/udp src=192.168.100.80 dst=8.8.8.8 sent=309 rcvd=0 duration=0 msg=[source^^interface^^=^^Internal]
	 */
	public static String replaceBetweens(String source, String ss, String es, String os, String ns) {
		if(source==null)
			return null;
		
		char[] sourceArray = source.toCharArray();
		char[] nsArray = ns.toCharArray();
		int oLength = os.length();
		StringBuffer buf = new StringBuffer();
		
	    int i=0, j=0, s=0, e=0;
	    s = source.indexOf(ss, s);
	    e = source.indexOf(es, s+1);
	    
	    while( s>=0 && e>=0 && e>s) {
	    	i = source.indexOf(os, s);
	    	while(i>s && i<e) {
	    		// System.out.println("s:"+s+", i:"+i+", e:"+e);
	    		buf.append(sourceArray, j, i-j).append(nsArray);
	    		i += oLength;
	    		j=i;
	    		i = source.indexOf(os, j);
	    	}
	    	
	    	s = e+1;
	    	s = source.indexOf(ss, s);
	    	e = source.indexOf(es, s+1);
	    }
	    buf.append (sourceArray, j, sourceArray.length - j);
	    
	    return buf.toString();
	}
	
	// 문자열내부에 시작문자(열)과 종료문자(열)로 둘러쌓인 서브문자열들 전체를 다른 문자로 변경하고자 할때 사용한다.
	/* 
     * 예를들어, 원문자열에 다음과 같을 때, 
     * 원문자열(str) : 08:EB:74:07:FF:78||0||633||2016-03-31 22:check{0,1,2,3,4,5,6}:00||430||check{7,8,9,10}||UC1300X
     * 시작문자"check{"와 종료문자"}"로 둘러쌓인 서브문자열 --> check{0,1,2,3,4,5,6}과 check{7,8,9,10} 문자열들 모두를 "new string"로 바꾸고 싶으면 다음처럼 하면 된다. 
     *   : replaceBetweens(str, "check{", "}", "new string")
     * 결과 : 08:EB:74:07:FF:78||0||633||2016-03-31 22:new string:00||430||new string||UC1300X
     */
	public static String replaceBetweens(String source, String ss, String es, String ns) {
        if(source==null || ss==null || es==null)
            return null;
        
        char[] sourceArray = source.toCharArray();
        char[] nsArray = ns.toCharArray();
        StringBuffer buf = new StringBuffer();
        
        int i=0, s=0, e=0;
        s = source.indexOf(ss, s);
        e = source.indexOf(es, s+1);
        
        while( s>=0 && e>=0 && e>s) {
            // System.out.println("s:"+s+", i:"+i+", e:"+e);
            buf.append(sourceArray, i, s-i).append(nsArray);
            i = e+es.length();
            s = e+1;
            s = source.indexOf(ss, s);
            e = source.indexOf(es, s+1);
        }
        buf.append (sourceArray, i, sourceArray.length - i);
        
        return buf.toString();
    }
	
	// 문자열내부에 시작문자열과 종료문자열을 포함하는 부분을 제거한다. 
	/*
	 * EX : 원문자열(str) --> rf1c2{email}f1{phone}f0
	 *     removeBetween(str, "{", "}")의 결과 --> rf1c2f1f0
	 */
	public static String removeBetween(String source, String ss, String es) {
		if(source==null)
			return null;
		
		char[] sourceArray = source.toCharArray();
		StringBuffer buf = new StringBuffer();
		
	    int i=0, s=0, e=0;
	    s = source.indexOf(ss, s);
	    e = source.indexOf(es, s+1);
	    
	    while( s>=0 && e>=0 && e>s) {
	    	buf.append(sourceArray, i, s-i);
	    	i = e+es.length();
	    	s = e+es.length();
	    	s = source.indexOf(ss, s);
	    	e = source.indexOf(es, s+1);
	    }
	    buf.append (sourceArray, i, sourceArray.length - i);
	    
	    return buf.toString();
	}
	
	// BLAS Normalizer의 ignore{...} configuration에 대응하는 함수이다. 
	/* 
	 * 예를들어, 원문자열이 다음과 같을 때, 
	 * 원문자열(str) --> date=2014/07/18 11:20:17, name=홍길동, e-mail=hongkildong@www.company.com
	 * case1. ignore(true, str, "name|email|test")의 결과는 true이다. 
	 *   원문자열중에 "name"또는 "email" 또는 "test"중 하나라도 포함하면 ignore하라는 경우이므로 true가 된다. (포함하고 있으므로 ignore해야 함.)
	 * case2. ignore(true, str, "nname|email|test")의 결과는 false이다. 
	 *   원문자열중에 "nname"또는 "email" 또는 "test"중 하나라도 포함하면 ignore하라는 경우이므로 false가 된다. (포함하고 있지 않으므로 ignore하면 안됨.)
	 * case3. ignore(false, str, "name|email|test")의 결과는 false이다. 
	 *   원문자열중에 "name"또는 "email" 또는 "test"중 하나라도 포함하면 ignore하지 말라는 경우이므로 false가 된다. (포함하고 있으므로 ignore하면 안됨.)
	 * case2. ignore(false, str, "nname|email|test")의 결과는 true이다. 
	 *   원문자열중에 "nname"또는 "email" 또는 "test"중 하나라도 포함하면 ignore하지 말라는 경우인데 포함하고 있으므로 ignore해야하고 true가 된다. (포함하고 있지 않으므로 ignore해야 함.)
	 */
//	public static boolean ignore(boolean flag, String input, String...strs) {
//		String regex = String.format("\\b(%s)\\w*\\b", StringUtils.join(strs,"|"));
//		Pattern pattern = Pattern.compile(regex);
//		boolean matches = pattern.matcher(input).find();
//		
//		if(!flag)
//			return !matches;
//		return matches;
//	}
	
	public static boolean ignore(boolean flag, String input, String... items) {
        boolean rtn = false;
        
        for(int i =0; i < items.length; i++) {
            if(input.contains(items[i])) {
                rtn = true;
                break;
            }
        }
        if(!flag)
            return !rtn;
        return rtn;
    }
	
	
	// masking 처리 
	public static String masking(String maskfmt, String s) {
        List<String> fmtlist = split(false, 2, '|', maskfmt);
        if(fmtlist!=null && fmtlist.size()==2) {
            try {
                int idx = Integer.parseInt(fmtlist.get(0));
                return masking(idx, fmtlist.get(1), s);
            } catch(Exception e) {
                
            }
        }
        return s;
    }
    
	/*
	 * masking 처리 
	 * 원문자열 str : 12345678901234567890abcdefghijklmnopqrstuvwxys
	 * masking(5, "*", str) --> 12345*****************************************
	 * masking(5, "abc", str) --> 12345abcabcabcabcabcabcabcabcabcabcabcabcabcab
	 */
    public static String masking(int idx, String maskstr, String s) {
        if(s==null || s.length()<=idx)
            return s;
        char[] mask = maskstr.toCharArray();
        char[] arr = s.toCharArray();
        int j=0;
        for (int i = idx; i < arr.length; i++) {
            arr[i] = mask[j];
            j++;
            if(j>=mask.length)
                j=0;
        }
        return new String(arr, 0, arr.length);
    }
	
	/* 2015/08/11 YONGSANG
	 * 기능: 입력된 지리 정보에 대한 값을 리턴한다.
	 * 	입력은 ip타입이어야 한다. str --> 192.123.53.32
	 * 	geoip("isp location city", str, cl, rl, ispl) 여기서 사용자 입력 변수는 앞의 두개이며 cl, rl, ispl은 내부적으로 넘겨주는값
	 * 결과 : Seoul 
	 * */
	public static String geoip(String codename, String input, String IPversion, LookupService rl,LookupService ispl) {
		String geoIp = " ";
		System.out.println("geoip :" +codename +"---ipversion: "+IPversion+"---input: "+input);
		if (IPversion.equals("IPv4")) {
			if (codename.contains("isp") == true) {
				if (codename.contains("isp company name") == true) {
					String Org = ispl.getOrg(input);
					if (Org == null)
						return geoIp;
					geoIp = Org;
				}
			} else {
				if (codename.contains("company name") == true) {
					String Org = ispl.getOrg(input);
					if (Org == null)
						return geoIp;
						geoIp = Org;
				} else {
					Location loc = rl.getLocation(input);
					if (loc == null)
						return geoIp;
					if (codename.contains("location city") == true)
						geoIp = loc.city;
					else if (codename.contains("location countrycode") == true)
						geoIp = loc.countryCode;
					else if (codename.contains("location countryname") == true)
						geoIp = loc.countryName;
					else if (codename.contains("location postalcode") == true)
						geoIp = loc.postalCode;
					else if (codename.contains("location region") == true)
						geoIp = loc.region;
					else if (codename.contains("location region name") == true)
						geoIp = regionName.regionNameByCode(loc.countryCode,loc.region);
					else if (codename.contains("location areacode") == true)
						geoIp = Integer.toString(loc.area_code);
					else if (codename.contains("location dmacode") == true)
						geoIp = Integer.toString(loc.dma_code);
					else if (codename.contains("location metrocode") == true)
						geoIp = Integer.toString(loc.metro_code);
					else if (codename.contains("location latitude") == true){
						geoIp = "0.0";
						geoIp = Float.toString(loc.latitude);
					} else if (codename.contains("location longitude") == true){
						geoIp = "0.0";
						geoIp = Float.toString(loc.longitude);
					} else if (codename.contains("timezone") == true)
						geoIp = timeZone.timeZoneByCountryAndRegion(loc.countryCode, loc.region);
				}
			}
		}
		else if (IPversion.equals("IPv6")) {
			if (codename.contains("isp") == true) {
				if (codename.contains("isp company name") == true) {
					String Org = ispl.getOrgV6(input);
					if (Org == null)
						return geoIp;
					geoIp = Org;
				}
			} else {
				if (codename.contains("company name") == true) {
					String Org = ispl.getOrgV6(input);
					if (Org == null)
						return geoIp;
						geoIp = Org;
				} else {
					Location loc = rl.getLocationV6(input);
					if (loc == null)
						return geoIp;
					if (codename.contains("location city") == true)
						geoIp = loc.city;
					else if (codename.contains("location countrycode") == true)
						geoIp = loc.countryCode;
					else if (codename.contains("location countryname") == true)
						geoIp = loc.countryName;
					else if (codename.contains("location postalcode") == true)
						geoIp = loc.postalCode;
					else if (codename.contains("location region") == true)
						geoIp = loc.region;
					else if (codename.contains("location region name") == true)
						geoIp = regionName.regionNameByCode(loc.countryCode,loc.region);
					else if (codename.contains("location areacode") == true)
						geoIp = Integer.toString(loc.area_code);
					else if (codename.contains("location dmacode") == true)
						geoIp = Integer.toString(loc.dma_code);
					else if (codename.contains("location metrocode") == true)
						geoIp = Integer.toString(loc.metro_code);
					else if (codename.contains("location latitude") == true){
						geoIp = "0.0";
						geoIp = Float.toString(loc.latitude);
					} else if (codename.contains("location longitude") == true){
						geoIp = "0.0";
						geoIp = Float.toString(loc.longitude);
					} else if (codename.contains("timezone") == true)
						geoIp = timeZone.timeZoneByCountryAndRegion(loc.countryCode, loc.region);
				}
			}
		}
		return geoIp;
	}
	/* 2015/08/11 YONGSANG*/

	
	/* 2015/08/11 YONGSANG
	 * 기능 : 입력받은 regular expression을 str에서 부터 찾아서 값들을 delimeter로 구분한 string으로 리턴한다.
	 * 예제 : regex(encoded regular expression string, "," , "id=firewall,time=2014-07-22 13:25:06,fw=(none),proto=53/udp,src=192.168.100.80,dst=8.8.8.8,sent=309,duration=0,msg=source  tempinterface - Internal" )
	 * 결과 : 192.168.100.80,8.8.8.8
	 * */
	public static String regex(String encodeRegex, String delimeter, String input) {
		StringBuilder result = new StringBuilder();
		String regexpression = null;
		try {
			regexpression = new String(Base64.decode(encodeRegex, Base64.NO_WRAP), "UTF-8");
			//System.out.println(regexpression);
			List<String> matches = CommonPatterns.patternMatches("UserDefined", regexpression, input);
			int i = 1;
			for(String match:matches) {
				result.append(match);
				if (i < matches.size())
					result.append(delimeter);
				i++;
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result.toString();
	}
	/* 2015/08/11 YONGSANG*/

	
	/* 2015/08/12 YONGSANG
	 * 기능 : keyvalue쌍으로 구성되어진 string중 일부에서 key값이 존재하지 않는 등의 불완전한 상태일 때, 불완전한 field를 제거하거나 임의의 key를 삽입해 줌으로 
	 * 	keyvalue함수 사용시 유용하도록 해준다.
	 * 예제1 : str => id=firewall,time=2014-07-22 13:25:06,fw=(none), test7, 53, proto=53/udp,src=192.168.100.80,dst=8.8.8.8,sent=309,duration=0,msg=source  tempinterface - Internal
	 * 	 makekeyvalue(",", "=", false, str)
	 * 결과1 :  	id:firewall
				time:2014-07-22 13:25:06
				fw:(none)
				field0:test7
				field1:53
				proto:53/udp
				src:192.168.100.80
				dst:8.8.8.8
				sent:309
				field3:0
				duration:0
				msg:source  tempinterface - Internal    
				
				위의 입력에서 사용한 delimeter를 사용하여 하나의 string으로 출력한다.(아래 참고)
								
	 * 예제2 : str => id=firewall,time=2014-07-22 13:25:06,fw=(none), test7, 53,proto=53/udp,src=192.168.100.80,dst=8.8.8.8,sent=309,duration=0,msg=source  tempinterface - Internal
	 * 	 makekeyvalue(",", "=", true, str)
	 * 결과2 :  	id:firewall
				time:2014-07-22 13:25:06
				fw:(none)
				proto:53/udp
				src:192.168.100.80
				dst:8.8.8.8
				sent:309
				duration:0
				msg:source  tempinterface - Internal	
				
				위의 입력에서 사용한 delimeter를 사용하여 하나의 string으로 출력한다.
				==> id=firewall,time=2014-07-22 13:25:06,fw=(none),proto=53/udp,src=192.168.100.80,dst=8.8.8.8,sent=309,duration=0,msg=source  tempinterface - Internal
	 * */
	public static String makekeyvalue(String splitdelimeter, String keyvaluedelimeter, String subss, String subse, boolean drop, String input) {
		Map<String, String> kvmap = null;
		String temp = null;
		input = replaceBetweens( replaceBetweens(input, subss, subse, splitdelimeter, "^^"), subss, subse, keyvaluedelimeter, "vv" );
		input = input.replace("null", "temp__change");
		kvmap = com.jylee.util.Splitter.on(splitdelimeter).trimResults().withKeyValueSeparator(keyvaluedelimeter).split(input);
		int count = 0 , lastset = 0;
		StringBuilder gkv = new StringBuilder();
		
		for (String key : kvmap.keySet()) {
			if (kvmap.get(key).contains("null") == true) {
				if (drop == false) {
					temp = kvmap.get(key);
					for(int i =0 ; i < kvmap.get(key).split("null").length; i++) {
						temp = temp.replaceFirst("null", splitdelimeter+"field"+Integer.toString(count)+"=");
						count++;
					}
					temp = key+"="+temp;
//					System.out.println(temp);
				} else {
					temp = kvmap.get(key).split("null")[0];
					temp = key+"="+temp;
				}
				
			} else{ 
				temp = key+"="+kvmap.get(key);
			}
				
			gkv.append(temp);
			if (lastset < kvmap.keySet().size()-1)
				gkv.append(splitdelimeter);
			lastset++;
		}
//		System.out.println(kvmap.keySet().size());
//		System.out.println(gkv.toString());
		input = gkv.toString().replace("temp__change", "null");
		input = replaceBetweens( replaceBetweens(input, subss, subse, "^^",splitdelimeter), subss, subse, "vv" ,keyvaluedelimeter);
		
//		kvmap = keyvalue(splitdelimeter, keyvaluedelimeter, true, input);
//		for (String key: kvmap.keySet())
//			System.out.println(key+":"+kvmap.get(key));
		
		return input;
	}
	/* 2015/08/12 YONGSANG*/
	
	
	public static void main(String args[]) {
		String filename1 = /*"./GeoIP.dat";/*/"F:/geoip/GeoIP.dat";
		String filename2 = /*"./GeoLiteCity.dat";/*/"F:/geoip/GeoLiteCity.dat";
		String filename3 = /*"./GeoIPISP.dat";/*/"F:/geoip/GeoIPISP.dat";
		LookupService cl;
		try {
			cl = new LookupService(filename1,LookupService.GEOIP_MEMORY_CACHE);
			LookupService rl  = new LookupService(filename2,LookupService.GEOIP_MEMORY_CACHE);
			LookupService ispl  = new LookupService(filename3,LookupService.GEOIP_MEMORY_CACHE);

			String s = null,s1 = null,s2 = null,s3 = null,s4 = null,s5 = null,s6 = null,s7 = null,s8 = null,s9 = null,s10 = null,s11 = null,s12 = null,s13 = null,s14 = null,s15 = null,s16 = null
					,s17 = null,s18 = null,s19 = null,s20 = null,s21 = null,s22 = null,s23 = null,s24 = null,s25  = null;
			long count = 0;
			long today = System.currentTimeMillis(); // long 형의 현재시간
			long starttime = today;
			System.out.println(today);

//			System.out.println(s+"\n");
//			while(today <= starttime+30000){
			
			//198..245.61.123 : isp 문제있음. //211..241.225.206 :모두 오케
//				s = geoip("isp company name", "198.245.61.123","IPv4", rl, ispl);
//				s1 = geoip("isp location city", "198.245.61.123","IPv4", rl, ispl);
//				s2 = geoip("isp location countrycode", "198.245.61.123","IPv4", rl, ispl);
//				s3 = geoip("isp location countryname", "198.245.61.123","IPv4" , rl, ispl);
//				s4 = geoip("isp location postalcode", "198.245.61.123","IPv4" , rl, ispl);
//				s5 = geoip("isp location region", "198.245.61.123","IPv4" , rl, ispl);
//				s6 = geoip("isp location areacode", "198.245.61.123","IPv4" , rl, ispl);
//				s7 = geoip("isp location dmacode", "198.245.61.123","IPv4" , rl, ispl);
//				s8 = geoip("isp location metrocode", "198.245.61.123","IPv4" , rl, ispl);
				s9 = geoip("isp location latitude", "198.245.61.123","IPv4" , rl, ispl);
//				s10 = geoip("company name", "198.245.61.123" , rl, ispl);
//				s11 = geoip("location city", "198.245.61.123","IPv4" , rl, ispl);
//				s12 = geoip("location countrycode", "198.245.61.123","IPv4" , rl, ispl);
//				s13 = geoip("location countryname", "198.245.61.123","IPv4" , rl, ispl);
//				s14 = geoip("location postalcode", "198.245.61.123","IPv4" , rl, ispl);
//				s15 = geoip("location region", "198.245.61.123","IPv4" , rl, ispl);
//				s16 = geoip("location regionname", "198.245.61.123","IPv4" , rl, ispl);
//				s17 = geoip("location areacode", "198.245.61.123","IPv4" , rl, ispl);
//				s18 = geoip("location dmacode", "198.245.61.123","IPv4" , rl, ispl);
//				s19 = geoip("location metrocode", "198.245.61.123","IPv4" , rl, ispl);
//				s20 = geoip("location latitude", "192.168.80.7","IPv4" , rl, ispl);//192.168.80.7
//				s21 = geoip("location longitude", "198.245.61.123","IPv4" , rl, ispl);
//				s22 = geoip("timezone", "198.245.61.123","IPv4" , rl, ispl);
//				s23 = geoip("location countrycode", "198.245.61.123","IPv4" , rl, ispl);
//				s24 = geoip("location countrycode", "198.245.61.123","IPv4" , rl, ispl);
//				s25 = geoip("location countrycode", "198.245.61.123","IPv4" , rl, ispl);

//				System.out.println(s+"\n");
			
//				count++;
//				today = System.currentTimeMillis();
				
//			}
//			System.out.println(today);
//			
//			System.out.println(count);
//			cl.close();
//			rl.close();
//			ispl.close();
//			
//			System.out.println("1:"+s);
//			System.out.println("2:"+s1);
//			System.out.println("3:"+s2);
//			System.out.println("3_1:"+s3);
//			System.out.println("4:"+s4);
//			System.out.println("5:"+s5);
//			System.out.println("6:"+s6);
//			System.out.println("7:"+s7);
//			System.out.println("8:"+s8);
			System.out.println("9:"+s9);
//			System.out.println("10:"+s10);
//			System.out.println("11:"+s11);
//			System.out.println("12:"+s12);
//			System.out.println("13:"+s13);
//			System.out.println("14:"+s14);
//			System.out.println("15:"+s15);
//			System.out.println("16:"+s16);
//			System.out.println("17:"+s17);
//			System.out.println("18:"+s18);
//			System.out.println("19:"+s19);
//			System.out.println("20:"+s20);
//			System.out.println("21:"+s21);
//			System.out.println("22:"+s22);
//			System.out.println("23:"+s23);
//			System.out.println("24:"+s24);
//			System.out.println("25:"+s25);
//			
//			System.out.println("copy{0__1}".indexOf("__"));
/////////////////////////////////////////////////////////////////////////////////////////////////////////////			
//			Map<String, String> kvmap = null;
//			String temp = null;
//			boolean drop = false;
//			s = "firewall, time=2014-07-22 null 13:25:06, fw=(none), test7, 53, proto=53/udp, src=192.168.100.80, dst=8.8.8.8, sent=309, 0, duration=0, msg=source  nullinterface - Internal";
//			s = s.replace("null", "temp__change");
//			kvmap = /*keyvalue(",", "=", true, s);*/com.jylee.util.Splitter.on(",").trimResults().withKeyValueSeparator("=").split(s);
//			int count1 = 0 , lastset = 0;
//			StringBuilder gkv = new StringBuilder();
//			for (String key : kvmap.keySet()) {
//				System.out.println("-----"+key);
//				if (kvmap.get(key).contains("null") == true) {
//					if (drop == false) {
//						temp = kvmap.get(key);
//						for(int i =0 ; i < kvmap.get(key).split("null").length; i++) {
//							temp = temp.replaceFirst("null", ","+"field"+Integer.toString(count1)+"=");
//							count1++;
//						}
//						temp = key+"="+temp;
//						System.out.println(temp);
//					} else {
//						temp = kvmap.get(key).split("null")[0];
//						temp = key+"="+temp;
//					}
//					
//				} else{ 
//					temp = key+"="+kvmap.get(key);
//				}
//					
//				gkv.append(temp);
//				if (lastset < kvmap.keySet().size()-1)
//					gkv.append(",");
//				lastset++;
//			}
//			System.out.println(kvmap.keySet().size());
//			System.out.println(gkv.toString());
//			s = gkv.toString().replace("temp__change", "null");
//			kvmap = keyvalue(",", "=", true, s);
//			for (String key: kvmap.keySet())
//				System.out.println(key+":"+kvmap.get(key));
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//			
//		
//			s = "id=firewall, time=2014-07-22 null 13:25:06, fw=(none), test7, 53, proto=53/udp, src=192.168.100.80, dst=8.8.8.8, sent=309, 0, duration=0, msg=source  nullinterface - Internal";
//			
//			System.out.println(regex("KD88IVswLTldKSg/Oig/OjI1WzAtNV18MlswLTRdWzAtOV18WzAtMV0/WzAtOV17MSwyfSlbLl0oPzoyNVswLTVdfDJbMC00XVswLTldfFswLTFdP1swLTldezEsMn0pWy5dKD86MjVbMC01XXwyWzAtNF1bMC05XXxbMC0xXT9bMC05XXsxLDJ9KVsuXSg/OjI1WzAtNV18MlswLTRdWzAtOV18WzAtMV0/WzAtOV17MSwyfSkpKD8hWzAtOV0p",",",s));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}




