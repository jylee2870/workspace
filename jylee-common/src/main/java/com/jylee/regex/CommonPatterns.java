package com.jylee.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommonPatterns {
	
	static final String APPID_STR = "^[a-z]{1,2}";
	static final Pattern APPID_PATTERN = Pattern.compile(APPID_STR);
	static final String USERNAME_STR = "[a-zA-Z0-9._-]+";
	static final Pattern USERNAME_PATTERN = Pattern.compile(USERNAME_STR);
	static final String USER_STR = USERNAME_STR;
	static final Pattern USER_PATTERN = Pattern.compile(USER_STR);
	static final String INT_STR = "(?:[+-]?(?:[0-9]+))";
	static final Pattern INT_PATTERN = Pattern.compile(INT_STR);
	static final String BASE10NUM_STR = "(?<![0-9.+-])(?>[+-]?(?:(?:[0-9]+(?:\\.[0-9]+)?)|(?:\\.[0-9]+)))";
	static final Pattern BASE10NUM_PATTERN = Pattern.compile(BASE10NUM_STR);
	static final String NUMBER_STR = "(?:"+BASE10NUM_STR+")";
	static final Pattern NUMBER_PATTERN = Pattern.compile(NUMBER_STR);
	static final String BASE16NUM_STR = "(?<![0-9A-Fa-f])(?:[+-]?(?:0x)?(?:[0-9A-Fa-f]+))";
	static final Pattern BASE16NUM_PATTERN = Pattern.compile(BASE16NUM_STR);
	static final String BASE16FLOAT_STR = "\\b(?<![0-9A-Fa-f.])(?:[+-]?(?:0x)?(?:(?:[0-9A-Fa-f]+(?:\\.[0-9A-Fa-f]*)?)|(?:\\.[0-9A-Fa-f]+)))\\b";
	static final Pattern BASE16FLOAT_PATTERN = Pattern.compile(BASE16FLOAT_STR);
	static final String POSINT_STR = "\\b(?:[1-9][0-9]*)\\b";
	static final Pattern POSINT_PATTERN = Pattern.compile(POSINT_STR);
	static final String NONNEGINT_STR = "\\b(?:[0-9]+)\\b";
	static final Pattern NONNEGINT_PATTERN = Pattern.compile(NONNEGINT_STR);
	static final String WORD_STR = "\\b[\\w|가-힣]+\\b";
	static final Pattern WORD_PATTERN = Pattern.compile(WORD_STR);
	static final String NOTSPACE_STR = "\\S+";
	static final Pattern NOTSPACE_PATTERN = Pattern.compile(NOTSPACE_STR);
	static final String SPACE_STR = "\\s*";
	static final Pattern SPACE_PATTERN = Pattern.compile(SPACE_STR);
	static final String DATA_STR = ".*?";
	static final Pattern DATA_PATTERN = Pattern.compile(DATA_STR);
	static final String GREEDYDATA_STR = ".*";
	static final Pattern GREEDYDATA_PATTERN = Pattern.compile(GREEDYDATA_STR);
	static final String ANYDATA_STR = ".+";
	static final Pattern ANYDATA_PATTERN = Pattern.compile(ANYDATA_STR);
	static final String QUOTEDSTRING_STR = "(?>(?<!\\\\)(?>\"(?>\\\\.|[^\\\\\"]+)+\"|\"\"|(?>'(?>\\\\.|[^\\\\']+)+')|''|(?>`(?>\\\\.|[^\\\\`]+)+`)|``))";
	static final Pattern QUOTEDSTRING_PATTERN = Pattern.compile(QUOTEDSTRING_STR);
	static final String UUID_STR = "[A-Fa-f0-9]{8}-(?:[A-Fa-f0-9]{4}-){3}[A-Fa-f0-9]{12}";
	static final Pattern UUID_PATTERN = Pattern.compile(UUID_STR);
	
	// NETWORKING
	static final String CISCOMAC_STR = "(?:(?:[A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2})";
	static final Pattern CISCOMAC_PATTERN = Pattern.compile(CISCOMAC_STR);
	static final String WINDOWSMAC_STR = "(?:(?:[A-Fa-f0-9]{2}-){5}[A-Fa-f0-9]{2})";
	static final Pattern WINDOWSMAC_PATTERN = Pattern.compile(WINDOWSMAC_STR);
	static final String COMMONMAC_STR = "(?:(?:[A-Fa-f0-9]{2}-){5}[A-Fa-f0-9]{2})";
	static final Pattern COMMONMAC_PATTERN = Pattern.compile(COMMONMAC_STR);
	static final String MAC_STR = "(?:"+CISCOMAC_STR+"|"+WINDOWSMAC_STR+"|"+COMMONMAC_STR+")";
	static final Pattern MAC_PATTERN = Pattern.compile(MAC_STR);
	static final String IP_STR = "(?<![0-9])(?:(?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})[.](?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})[.](?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})[.](?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2}))(?![0-9])";
	static final Pattern IP_PATTERN = Pattern.compile(IP_STR);
	
	   public static void main(String args[]) throws Exception {
	        
	        System.out.println(NUMBER_STR);
	    }
	
	
	public enum PATTERN {
		APPID,USERNAME,USER,INT,BASE10NUM,NUMBER,BASE16NUM,BASE16FLOAT,POSINT,NONNEGINT,WORD,NOTSPACE,DATA,GREEDYDATA,ANYDATA,QUOTEDSTRING,UUID
		,CISCOMAC,WINDOWSMAC,COMMONMAC,MAC,IP;
		
		public static PATTERN toPATTERN(String str) {
			try {
				return valueOf(str);
			} catch(Exception e) {
				return ANYDATA;
			}
		}
	}
	
	public static Pattern getPattern(String pattern) {
		switch (PATTERN.toPATTERN(pattern)) {
		case APPID:
			return APPID_PATTERN;
		case USERNAME:
			return USERNAME_PATTERN;
		case USER:
			return USER_PATTERN;
		case INT:
			return INT_PATTERN;
		case BASE10NUM:
			return BASE10NUM_PATTERN;
		case NUMBER:
			return NUMBER_PATTERN;
		case BASE16NUM:
			return BASE16NUM_PATTERN;
		case BASE16FLOAT:
			return BASE16FLOAT_PATTERN;
		case POSINT:
			return POSINT_PATTERN;
		case NONNEGINT:
			return NONNEGINT_PATTERN;
		case WORD:
			return WORD_PATTERN;
		case NOTSPACE:
			return NOTSPACE_PATTERN;
		case DATA:
			return DATA_PATTERN;
		case GREEDYDATA:
			return GREEDYDATA_PATTERN;
		case ANYDATA:
			return ANYDATA_PATTERN;
		case QUOTEDSTRING:
			return QUOTEDSTRING_PATTERN;
		case UUID:
			return UUID_PATTERN;
		case CISCOMAC:
			return CISCOMAC_PATTERN;
		case WINDOWSMAC:
			return WINDOWSMAC_PATTERN;
		case COMMONMAC:
			return COMMONMAC_PATTERN;
		case MAC:
			return MAC_PATTERN;
		case IP:
			return IP_PATTERN;
		}
		return null;
	}
	
	
	public static List<String> patternMatches(String patternName, String definedPattern, String str) {
		List<String> rtnlist = null;
		Pattern pattern = null;
		
		if(patternName.equals("UserDefined")) {
		    pattern = Pattern.compile(definedPattern);
		} else {
		    pattern = getPattern(patternName);
		}
		
		Matcher m = pattern.matcher(str);
		while (m.find()) {
			if(rtnlist==null)
				rtnlist = new ArrayList<String>();
			rtnlist.add(m.group());
		}
		return rtnlist;
		
	}
	
	public static boolean hasPatterns(String patternName, String definedPattern,  String str) {
        boolean rtn = false;
        Pattern pattern = null;
        
        if(patternName.equals("UserDefined")) {
            pattern = Pattern.compile(definedPattern);
        } else {
            pattern = getPattern(patternName);
        }
        Matcher m = pattern.matcher(str);
        while (m.find()) {
            return true;
        }
        return rtn;
        
    }
	
//	public static void main(String args[]) throws Exception {
//		
//		String s = "this 'is' test \"문자열\" 입니다. 192.168.100.32 hahahaha. 210.112.32.1 00:25:90:EB:A4:76 0C-8B-FD-C0-99-5A sn1.dsntech.com www.naver.com 111.222.212.23 10.19.1.1 ";
//		List<String> matches = patternMatches("MAC", null, s);
//		for(String match:matches) {
//			System.out.println(match);
//		}
//		System.out.println(hasPatterns("IP", null, s));
//	}
	
}


