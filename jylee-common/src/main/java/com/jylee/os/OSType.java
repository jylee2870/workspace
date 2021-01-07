package com.jylee.os;

public enum OSType {
    
    windows, linux, solaris, mac, generic;
    
    public static OSType toOSType(String str) {
        try {
            return valueOf(str);
        } catch(Exception e) {
            return generic;
        }
    }
}

