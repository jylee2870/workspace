package com.jylee.random;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jylee.util.CommonUtils;
import com.jylee.util.IPFinder;
import com.jylee.util.NormalizerUtils;


public class RandomIP extends RandomRange {
    // private static Log logger = LogFactory.getLog(RandomIP.class);
    
    private static final String IP_FULL = "full";
    
    private boolean unique = false;
    private long uniqueCnt = -1L;
    private Map<Long,Boolean> uniqueMap = null;
    
    // Constructor.
    public RandomIP() {
        this(false, "0.0.0.0", "255.255.255.255");
    }
    
    public RandomIP(String lowerIp, String upperIp) {
        this(false, lowerIp, upperIp);
    }
    
    public RandomIP(boolean unique, String lowerIp, String upperIp) {
        // this(lowerIp, upperIp);
        super();
        super.setLowerAndUpper(CommonUtils.ip2Number(lowerIp), CommonUtils.ip2Number(upperIp));
        
        if(!checkIPValid(lowerIp) || !checkIPValid(lowerIp)) 
            throw new IllegalArgumentException("IP is not valid.");
        
        this.unique = unique;
        if(unique) {
            uniqueCnt = super.getUpper().longValue() - super.getLower().longValue()+1L;
            uniqueMap = new HashMap<Long,Boolean>();
        }
    }
    
    public RandomIP(String rangeFormat) {
        this(false, rangeFormat);
    }
    
    public RandomIP(boolean unique, String rangeFormat) {
        super();
        if(rangeFormat!=null && rangeFormat.contains("~")) {
            List<String> ranges = NormalizerUtils.split(true, 2, "~", rangeFormat);
            super.setLowerAndUpper(CommonUtils.ip2Number(ranges.get(0)), CommonUtils.ip2Number(ranges.get(1)));
            if(!checkIPValid(ranges.get(0)) || !checkIPValid(ranges.get(1))) 
                throw new IllegalArgumentException("IP is not valid.");
        } else {
            super.setLowerAndUpper(CommonUtils.ip2Number(rangeFormat), CommonUtils.ip2Number(rangeFormat));
            if(!checkIPValid(rangeFormat) ) 
                throw new IllegalArgumentException("IP is not valid.");
        }
        
        this.unique = unique;
        if(unique) {
            uniqueCnt = super.getUpper().longValue() - super.getLower().longValue()+1L;
            uniqueMap = new HashMap<Long,Boolean>();
        }
    }
    
    
    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public long getUniqueCnt() {
        return uniqueCnt;
    }

    public Map<Long, Boolean> getUniqueMap() {
        return uniqueMap;
    }

    private boolean checkIPValid(String ip) {
        return IPFinder.isValidIP(ip);
    }
    
    // IP 생성하기. 
    public String ip() {
        // long ipLong = super.next().longValue();
        if(unique) {
            if(uniqueMap.size()>=uniqueCnt) 
                return IP_FULL;
                // throw new NotGeneratedException("Unique IP Address is full.. can't generate..!!");
            while(true) {
                long ipLong = super.next().longValue();
                if(uniqueMap.get(ipLong)==null) {
                    uniqueMap.put(ipLong, true);
                    return CommonUtils.number2Ip( ipLong);
                }
            }
        } else {
            return CommonUtils.number2Ip( super.next().longValue());
        }
    }
    
    public String toString() {
        String ip =null ;
//        try {
            ip = ip();
//        } catch (NotGeneratedException nge) {
//            logger.error("error ***** "+nge.getMessage(), nge);
//        }
        return ip;
    }
}


