package com.jylee.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.jylee.util.NormalizerUtils;

public class RandomInterval {
    private static final int RANGE_FULL = -2147483648;
    
    private String type;
    private final Random random;
    private Number lower;
    private Number upper;
    private String separator;
    
    public RandomInterval() {
        this.random = new Random();
    }
    
    public RandomInterval(String rangeFormat,String separator) {
        this.random = new Random();
        if(rangeFormat!=null && rangeFormat.contains("~")) {
            List<String> ranges = NormalizerUtils.split(true, 2, "~", rangeFormat);
            this.lower = Long.parseLong(ranges.get(0));
            this.upper = Long.parseLong(ranges.get(1));
            checkTypeAndValid();
        } else if (rangeFormat!=null && !rangeFormat.contains("~")) {
            this.lower = Long.parseLong(rangeFormat);
            this.upper = Long.parseLong(rangeFormat);
            checkTypeAndValid();
        }
        this.separator = separator;
    }
    
    public RandomInterval(Number lower, Number upper, String separator) {
        this.random = new Random();
        this.lower = lower;
        this.upper = upper;
        checkTypeAndValid();
        this.separator = separator;
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

    private Number next() {
        {
            if(type.equals("Double"))
                return lower.doubleValue() + (double)(random.nextDouble() * (subtractNumbers(upper, lower).doubleValue()));
            else if(type.equals("Float"))
                return lower.floatValue() + (float)(random.nextDouble() * (subtractNumbers(upper, lower).floatValue()));
            else if(type.equals("Long")) {
                return lower.longValue() + (long)(random.nextDouble() * (subtractNumbers(upper, lower).longValue()+1l));
            } else {
                return lower.intValue() + (int)(random.nextDouble() * (subtractNumbers(upper, lower).intValue()+1));
            }
        }
    }
    
    public String interval() {
        List<String> valList = new ArrayList<String>();
        long lval=-1, lower=-1, upper=-1;
        lval = next().longValue();
        lower = lval;
        upper = lval;
        lval = next().longValue();
        if(lval>lower)
            upper = lval;
        else
            lower = lval;
        for(long i=lower; i<=upper; i++)
            valList.add(""+i);
        return StringUtils.join(valList, separator);
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
        return interval();
    }
}
