package com.jylee.random;

public enum TYPE {
    NUM, INTERVAL, SEQ, STR, CHAR, IP, TIME, MAP, NONE;
    
    public static TYPE toTYPE(String type) {
        try { return valueOf(type); } catch (Exception e) { return NONE; }
    }
}
