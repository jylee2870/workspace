package com.jylee.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/** 
* <ul>
*  <li>업무 그룹명 : BLAS </li>
*  <li>서브 업무명 : BLAS 공통유틸 - IP관련 유틸리티  </li>
*  <li>설  명 : 스트링내 IP어드레스 Finder 클래스 </li>
*  <li>작성자 : LEE SANG YUB</li>
*  <li>작성일 : 2012. 5. 29 </li>
*  <li>Copyright ⓒ DSNTECH All Right Reserved </li>
*  <li>========================================</li>
*  <li>변경자/변경일 : </li>
*  <li>변경사유/내역 : </li>
*  <li>========================================</li>
* </ul>
*/
public class IPFinder {
    private static final String IP_SEPERATOR = "(\\p{Space}|\\p{Punct})";

    private static final String IP_COMPONENT = "(1?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";

    private static final Pattern IP_PATTERN = Pattern.compile("(?<=(^|["
            + IP_SEPERATOR + "]))(" + IP_COMPONENT + "\\.){3}" + IP_COMPONENT
            + "(?=([" + IP_SEPERATOR + "]|$))");

    private List<String> ips;


    public IPFinder(String s) {
        ips = findIPAddresses(s);
    }

    /**
     * @return the list of IPs found in the String supplied during construction
     */
    public List<String> getIPs() {
        return ips;
    }

    /**
     * @return the list of unique IPs found in the String supplied during
     *         construction
     */
    public Set<String> getUniqueIPs() {
        return new TreeSet<String>(ips);
    }

    /**
     * Utility method to extract IP addresses, non unique
     * 
     * @param s
     */
    public static Set<String> findUniqueIPAddresses(String s) {
        return new TreeSet<String>(findIPAddresses(s));
    }

    /**
     * Utility method to extract IP addresses, non unique
     * 
     * @param s
     */
    public static boolean hasIPAddress(String s) {
        boolean has = false;
        Matcher m = IP_PATTERN.matcher(s);
        while (m.find()) {
            has = true;
            break;
        }
        return has;
    }
    
    public static List<String> findIPAddresses(String s) {
        List<String> ips = new ArrayList<String>();

        Matcher m = IP_PATTERN.matcher(s);
        while (m.find()) {
            String ip = m.group();
            ips.add(ip);
        }

        return ips;
    }
    
    public static String findOneIPAddresses(String s) {
        String ip = null;
        Matcher m = IP_PATTERN.matcher(s);
        if(m.find()) {
            ip = m.group();
        }
        return ip;
    }

    /**
     * Validates an IP address
     * 
     * @param ip
     * @return true if it's a valid IP address
     */
    public static boolean isValidIP(String ip) {
        return IP_PATTERN.matcher(ip).matches();
    }

}
