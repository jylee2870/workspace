package com.jylee.util;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
* <ul>
*  <li>업무 그룹명 : BLAS </li>
*  <li>서브 업무명 : BLAS 공통유틸 - String관련 유틸리티  </li>
*  <li>설  명 : String 조작 관련 공통 유틸리티 클래스 </li>
*  <li>작성자 : LEE SANG YUB</li>
*  <li>작성일 : 2012. 5. 31 </li>
*  <li>Copyright ⓒ DSNTECH All Right Reserved </li>
*  <li>========================================</li>
*  <li>변경자/변경일 : </li>
*  <li>변경사유/내역 : </li>
*  <li>========================================</li>
* </ul>
*/
public class StrUtils {
    private static Log logger = LogFactory.getLog(StrUtils.class);

    // 공백(' ')이나 탭(\t)이 여러개 중복되어 나올경우 하나로만 인식시키기
    public static String Squeeze(String s) {
        char[] arr = s.toCharArray();
        int next = 1; // refers to the next character in the result.

        for (int i = 1; i < arr.length; i++) {
            arr[next] = arr[i];
            if (arr[next] != ' ' && arr[next] != '\t')
                next++;
            else if (arr[next - 1] != ' ' && arr[next - 1] != '\t')
                next++;
        }

        return new String(arr, 0, next);
    }
    
    public static String includeSubstring(String org, String prefix, String suffix) {
        String rtn = null;
        try {
            int begin = org.indexOf(prefix);
            int end = org.indexOf(suffix, begin)+1;
            if (begin>=0 && end>0 && end>begin) {
                rtn = org.substring(begin, end);
            }
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return rtn;
    }
    
    private static int includeSubstring(String org, String prefix, String suffix, int fromIndex, List<String> list) {
        String substr = null;
        int rtn = -1;
        try {
            int begin = org.indexOf(prefix, fromIndex);
            int end = org.indexOf(suffix, begin)+1;
            if (begin>=0 && end>0 && end>begin) {
                substr = org.substring(begin, end);
                list.add(substr);
                rtn = end;
            } 
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return rtn;
    }
    
    public static List<String> includeSubstringList(String org, String prefix, String suffix) {
        List<String> inlist = new ArrayList<String>();
        int fromIdx = 0;
        try {
            while(true) {
                int subIdx = includeSubstring(org, prefix, suffix, fromIdx, inlist);
                if(subIdx>=0) {
                    fromIdx = subIdx;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        if(inlist.size()>0)
            return inlist;
        else
            return null;
    }
    
    public static String excludeSubstring(String org, String prefix, String suffix) {
        String rtn = null;
        try {
            int chk = org.indexOf(prefix);
            int begin = org.indexOf(prefix)+prefix.length();
            int end = org.indexOf(suffix, begin);
            if (chk>=0 && end>0 && end>begin) {
                rtn = org.substring(begin, end);
            }
        } catch (Exception e) {
            
        }
        return rtn;
    }
    
    private static int excludeSubstring(String org, String prefix, String suffix, int fromIndex, List<String> list) {
        String substr = null;
        int rtn = -1;
        try {
            int chk = org.indexOf(prefix, fromIndex);
            int begin = org.indexOf(prefix, fromIndex)+prefix.length();
            int end = org.indexOf(suffix, begin);
            if (chk>=0 && end>0 && end>begin) {
                substr = org.substring(begin, end);
                list.add(substr);
                rtn = end;
                // logger.debug("substr:"+substr);
                // try { Thread.sleep(1000L); } catch(Exception e) {}
            } 
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return rtn;
    }
    
    public static List<String> excludeSubstringList(String org, String prefix, String suffix) {
        List<String> inlist = new ArrayList<String>();
        int fromIdx = 0;
        try {
            while(true) {
                int subIdx = excludeSubstring(org, prefix, suffix, fromIdx, inlist);
                // logger.debug("subIdx : "+subIdx);
                if(subIdx>=0) {
                    fromIdx = subIdx;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        if(inlist.size()>0)
            return inlist;
        else
            return null;
    }
    
    public static String excludeSuffix(String org, String suffix) {
        String rtn = null;
        try {
            int chk = org.lastIndexOf(suffix);
            if (chk!=-1) {
                rtn = org.substring(0, chk);
            }
        } catch (Exception e) {
            
        }
        return rtn;
    }
    
    public static String removeSpaces(String org) {
        return org.replaceAll("\\p{Space}", "");
    }
    
    public static String removeQuotations(String org) {
        return org.replaceAll("\"", "").replaceAll("'", "");
    }
    
    /**
     * 상수형식의 문자열에서 특정형식({0},{1},..)의 문자를 원하는 파라미터로 변환한다.  
     * formatStr : ex) "/site/collect_fwrk/{0}/{1}/idx" 
     *             {0},{1},... --> 0부터 시작해야한다. 
     * 사용법 
     *    getFormatMessage(formatStr, "node", "sn1.dsntech.com") 
     *    --> /site/collect_fwrk/node/sn1.dsntech.com/idx
     *    {0}는 "node"로 치환되고, {1}은 "sn1.dsntech.com"으로 치환된다. 
     */
    public static String getFormatMessage(String formatStr, Object...param ) {
        String compStr = MessageFormat.format(formatStr.replace("'", "''"), param);
        //logger.debug("==============================================");
        //logger.debug(" getFormatMessage :: "+ compStr);
        //logger.debug("==============================================");
        return compStr;
    }
    
    public static String getFormatMessage(String formatStr, List<String> strlist) {
        
        String compStr = null;
        if(strlist.size()>0) {
            compStr = MessageFormat.format(formatStr, strlist.toArray());
        }
        
        return compStr;
    }
    
    
    /**
     * 문자열중 "대문자"를 "_ + 소문자"로 치환하여 반환한다.<br>
     * ex) "appId" --> "app_id"
     * @param name 치환할 문자열
     * @return
     * @author soullovers
     */
    public static String changeLowerCase(String name) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(name.substring(0, 1).toLowerCase());
        stringBuffer.append(name.substring(1).replaceAll("([A-Z])", "_$1").toLowerCase());
        //logger.debug("changeLowerCase :: "+ name + "-->" + stringBuffer.toString());
        return stringBuffer.toString();
    }

    /**
     * 문자열중  "_ + 소문자"를 "대문자"로 치환하여 반환한다.<br>
     * ex) "app_id" --> "appId"
     * @param name 치환할 문자열
     * @return
     * @author soullovers
     */
    public static String changeUpperCase(String name) {
        StringBuffer propertyName = new StringBuffer();
        String  strArr[] = name.split("_");
        for (int i = 0; i < strArr.length; i++) {
            if(i==0){
                propertyName.append(strArr[i]);
            }else{
                propertyName.append(strArr[i].substring(0, 1).toUpperCase());
                propertyName.append(strArr[i].substring(1));
            }
            
        }
        //logger.debug("changeLowerCase :: "+ name + "-->" + propertyName.toString());
        return propertyName.toString();
    }
    
    public static String list2String(List<String> strs, String delimeter) {
        if(strs !=null && strs.size()>0) {
            StringBuffer strb = new StringBuffer();
            for(int i=0;i<strs.size();i++) {
                if(i>0)
                    strb.append(delimeter);
                strb.append(strs.get(i));
            }
            return strb.toString();
        } else {
            return null;
        }
    }
    
    public static String formatDir(String dir) {
        StringBuffer dirBuffer = new StringBuffer();
        boolean startwithslash = dir.startsWith("/");
        boolean endwithslash = dir.endsWith("/");
        int endslashidx = 0;
        if(endwithslash) {
            endslashidx = dir.lastIndexOf("/");
        }
        
        if(startwithslash) {
            if(endwithslash)
                dirBuffer.append(dir.substring(0, endslashidx));
            else
                dirBuffer.append(dir);
        } else {
            if(endwithslash) {
                dirBuffer.append("/");
                dirBuffer.append(dir.substring(0, endslashidx));
            } else {
                dirBuffer.append("/");
                dirBuffer.append(dir);
            }
        }
        return dirBuffer.toString();
    }
    
    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
    
    public static boolean containsItemFromList(String inputString, List<String> items) {
        for (int i=0; i<items.size(); i++) {
            if (inputString.contains(items.get(i))) {
                return true;
            }
        }
        return false;
    }

}
