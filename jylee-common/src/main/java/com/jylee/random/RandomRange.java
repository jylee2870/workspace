package com.jylee.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.jylee.util.NormalizerUtils;

public class RandomRange {
    private static final int RANGE_FULL = -2147483648;
    
    private boolean unique = false;
    private boolean truncate = false;
    private int truncateVal = -1;
    private long uniqueCnt = 0L;
    private Map<Number,Boolean> uniqueMap = null;

    private String type;
    private final Random random;
    private Number lower;
    private Number upper;
    
    public RandomRange() {
        this(false);
    }
    
    public RandomRange(boolean unique) {
        this.random = new Random();
        this.unique = unique;
    }
    
    public RandomRange(String rangeFormat) {
        this(false, rangeFormat);
    }
    
    public RandomRange(boolean unique, String rangeFormat) {
        this(unique, -1, rangeFormat);
//        this.random = new Random();
//        if(rangeFormat!=null && rangeFormat.contains("~")) {
//            List<String> ranges = NormalizerUtils.split(true, 2, "~", rangeFormat);
//            this.lower = Long.parseLong(ranges.get(0));
//            this.upper = Long.parseLong(ranges.get(1));
//            checkTypeAndValid();
//            this.unique = unique;
//            if(unique) {
//                uniqueCnt = upper.longValue() - lower.longValue()+1L;
//                uniqueMap = new HashMap<Number,Boolean>();
//            }
//        } else if (rangeFormat!=null && !rangeFormat.contains("~")) {
//            this.lower = Long.parseLong(rangeFormat);
//            this.upper = Long.parseLong(rangeFormat);
//            checkTypeAndValid();
//            this.unique = false;
//        }
    }
    
    public RandomRange(boolean unique, int truncateVal, String rangeFormat) {
        this.random = new Random();
        if(truncateVal>0) {
            truncate = true;
            this.truncateVal = truncateVal;
        }
        if(rangeFormat!=null && rangeFormat.contains("~")) {
            List<String> ranges = NormalizerUtils.split(true, 2, "~", rangeFormat);
            this.lower = Long.parseLong(ranges.get(0));
            this.upper = Long.parseLong(ranges.get(1));
            checkTypeAndValid();
            this.unique = unique;
            if(unique) {
                uniqueCnt = upper.longValue() - lower.longValue()+1L;
                uniqueMap = new HashMap<Number,Boolean>();
            }
        } else if (rangeFormat!=null && !rangeFormat.contains("~")) {
            this.lower = Long.parseLong(rangeFormat);
            this.upper = Long.parseLong(rangeFormat);
            checkTypeAndValid();
            this.unique = false;
        }
    }
    
    public RandomRange(Number lower, Number upper) {
        this(false, lower, upper);
    }
    
    public RandomRange(boolean unique, Number lower, Number upper) {
        this.random = new Random();
        this.lower = lower;
        this.upper = upper;
        checkTypeAndValid();
        this.unique = unique;
        if(unique) {
            if(type.equals("Long"))
                uniqueCnt = upper.longValue() - lower.longValue()+1L;
            if(type.equals("Integer"))
                uniqueCnt = upper.intValue() - lower.intValue()+1;
            uniqueMap = new HashMap<Number,Boolean>();
        }
    }
    
    public long getUniqueCnt() {
        return uniqueCnt;
    }

    public Number getLower() {
        return lower;
    }

    public Number getUpper() {
        return upper;
    }

    public void setLowerAndUpper(Number lower, Number upper) {
        this.lower = lower;
        this.upper = upper;
        checkTypeAndValid();
        if(unique) {
            if(type.equals("Long"))
                uniqueCnt = upper.longValue() - lower.longValue()+1L;
            if(type.equals("Integer"))
                uniqueCnt = upper.intValue() - lower.intValue()+1;
            uniqueMap = new HashMap<Number,Boolean>();
        }
    }
    
    private void checkTypeAndValid() {
        if(upper instanceof Double) {
            type = "Double";
            if(upper.doubleValue() <= lower.doubleValue()) {
                throw new NumberFormatException("Upper double Value is smaller than Lower double Value");
            }
        }
        else if(upper instanceof Float) {
            type = "Float";
            if(upper.floatValue() <= lower.floatValue()) {
                throw new NumberFormatException("Upper float Value is smaller than Lower float Value");
            }
        }
        else if(upper instanceof Long) {
            type = "Long";
            if(upper.longValue() < lower.longValue()) {
                throw new NumberFormatException("Upper long Value is smaller than Lower long Value");
            }
        }
        else if(upper instanceof Integer) {
            type = "Integer"; 
            if(upper.intValue() < lower.intValue()) {
                throw new NumberFormatException("Upper integer Value is smaller than Lower integer Value");
            }
        }
    }

    public Number next() {
        
        if(unique && (type.equals("Long") || type.equals("Integer")) ) {
            if(uniqueMap.size()>=uniqueCnt) 
                return RANGE_FULL;
                // throw new NotGeneratedException("Unique Number is full.. can't generate..!!");
            
            while(true) {
                if(type.equals("Long")) {
                    long longVal = lower.longValue() + (long)(random.nextDouble() * (subtractNumbers(upper, lower).longValue()+1l));
                    if(uniqueMap.get(longVal)==null) {
                        uniqueMap.put(longVal, true);
                        return longVal;
                    }
                } else if(type.equals("Integer")) {
                    int intVal = lower.intValue() + (int)(random.nextDouble() * (subtractNumbers(upper, lower).intValue()+1));
                    if(uniqueMap.get(intVal)==null) {
                        uniqueMap.put(intVal, true);
                        return intVal;
                    }
                }
            }
        } else {
            if(type.equals("Double"))
                return lower.doubleValue() + (double)(random.nextDouble() * (subtractNumbers(upper, lower).doubleValue()));
            else if(type.equals("Float"))
                return lower.floatValue() + (float)(random.nextDouble() * (subtractNumbers(upper, lower).floatValue()));
            else if(type.equals("Long")) {
                long genVal = lower.longValue() + (long)(random.nextDouble() * (subtractNumbers(upper, lower).longValue()+1l));
                if(truncate && (genVal>truncateVal)) 
                    return ((genVal/truncateVal)*truncateVal);
                return genVal;
                // return lower.longValue() + (long)(random.nextDouble() * (subtractNumbers(upper, lower).longValue()+1l));
            } else {
                int genVal = lower.intValue() + (int)(random.nextDouble() * (subtractNumbers(upper, lower).intValue()+1));
                if(truncate && (genVal>truncateVal)) 
                    return ((genVal/truncateVal)*truncateVal);
                return genVal;
                // return lower.intValue() + (int)(random.nextDouble() * (subtractNumbers(upper, lower).intValue()+1));
            }
        }
    }
    
    private static Number subtractNumbers(Number a, Number b) {
        if(a instanceof Double || b instanceof Double) {
            return new Double(a.doubleValue() - b.doubleValue());
        } else if(a instanceof Float || b instanceof Float) {
            return new Float(a.floatValue() - b.floatValue());
        } else if(a instanceof Long || b instanceof Long) {
            return new Long(a.longValue() - b.longValue());
        } else {
            return new Integer(a.intValue() - b.intValue());
        }
    }
    
    public String toString() {
        Number numVal =null ;
//        try {
            numVal = next();
//        } catch (NotGeneratedException nge) {
//            
//        }
        return numVal.toString();
    }
}
