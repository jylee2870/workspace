package com.jylee.random;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.jylee.util.NormalizerUtils;



public class RandomAlpha {
    
    private char[] symbols;
    private char[] buf;
    
    private final Random random;
    private char fromCh;
    private char toCh;
    private int genCount = 1;
    
    public RandomAlpha() {
        this('a', 'z', 1);
    }
    
    public RandomAlpha(boolean upperCase, int genCount) {
        this.random = new Random();
        if(upperCase) {
            this.fromCh ='A';
            this.toCh ='Z';
        } else {
            this.fromCh ='a';
            this.toCh ='z';
        }
        this.genCount = genCount;
        init();
    }
    
    public RandomAlpha(char fromCh, char toCh, int genCount) {
        this.random = new Random();
        this.fromCh = fromCh;
        this.toCh = toCh;
        this.genCount = genCount;
        init();
    }
    
    /*
     * 다음처럼 사용한다. 
     * 대문자1개를 갖는 문자열 생성 : RandomAlphaNumeric("A ~ Z", 1);
     * 소문자2개를 갖는 문자열 생성 : RandomAlphaNumeric("a ~ z", 2);
     */
    public RandomAlpha(String rangeFormat, int genCount) {
        this.random = new Random();
        if(rangeFormat!=null && rangeFormat.contains("~")) {
            List<String> ranges = NormalizerUtils.split(true, 2, "~", rangeFormat);
            this.fromCh =ranges.get(0).charAt(0);
            this.toCh =ranges.get(1).charAt(0);
        }
        this.genCount = genCount;
        // System.out.println("rangeFormat : "+rangeFormat+" genCount:"+genCount);
        init();
    }
    
    private void init() {
        StringBuffer sbuf = new StringBuffer();
        for(char ch=fromCh; ch<=toCh; ++ch) {
            if(ch>=91 && ch<=96)
                continue;
            sbuf.append(ch);
        }
        symbols = sbuf.toString().toCharArray();
        buf = new char[genCount];
    }
    
    public String next() {
        for (int idx = 0; idx < genCount; ++idx) 
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }
    
    public String toString() {
        return next();
    }
}



