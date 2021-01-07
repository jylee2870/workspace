package com.jylee.range;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.Serializable;

/**
 * <p><code>NumberRange</code> represents an inclusive range of 
 * {@link java.lang.Number} objects of the same type.</p>
 *
 * @author Apache Software Foundation
 * @author <a href="mailto:chrise@esha.com">Christopher Elkins</a>
 * @since 2.0 (previously in org.apache.commons.lang)
 * @version $Id: NumberRange.java 905636 2010-02-02 14:03:32Z niallp $
 */
public final class StringRange implements Serializable {
    
    /**
     * Required for serialization support.
     * 
     * @see java.io.Serializable
     */
    private static final long serialVersionUID = 71849363892711L;

    /**
     *  added by lee,sangyub 2016.02.03
     */
    private boolean hasMin = true;
    
    private boolean hasMax = true;
    
    /**
     * The minimum number in this range.
     */
    private final String min;
    /**
     * The maximum number in this range.
     */
    private final String max;
    
    /**
     * Cached output hashCode (class is immutable).
     */
    private transient int hashCode = 0;
    /**
     * Cached output toString (class is immutable).
     */
    private transient String toString = null;

    /**
     * <p>Constructs a new <code>NumberRange</code> using the specified
     * number as both the minimum and maximum in this range.</p>
     *
     * @param num the number to use for this range
     * @throws IllegalArgumentException if the number is <code>null</code>
     * @throws IllegalArgumentException if the number doesn't implement <code>Comparable</code>
     * @throws IllegalArgumentException if the number is <code>Double.NaN</code> or <code>Float.NaN</code>
     */
    public StringRange(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The string must not be null");
        }
        if (str instanceof Comparable == false) {
            throw new IllegalArgumentException("The string must implement Comparable");
        }
        
        this.hasMin = true;
        this.hasMax = true;
        this.min = str;
        this.max = str;
    }

    /**
     *  added by lee,sangyub 2016.02.03
     */
    public StringRange(String str1, String str2) {
        this(true, true, str1, str2);
    }
    
    /**
     * <p>Constructs a new <code>NumberRange</code> with the specified
     * minimum and maximum numbers (both inclusive).</p>
     * 
     * <p>The arguments may be passed in the order (min,max) or (max,min). The
     * {@link #getMinimumNumber()} and {@link #getMaximumNumber()} methods will return the
     * correct value.</p>
     * 
     * <p>This constructor is designed to be used with two <code>Number</code>
     * objects of the same type. If two objects of different types are passed in,
     * an exception is thrown.</p>
     *
     * @param num1  first number that defines the edge of the range, inclusive
     * @param num2  second number that defines the edge of the range, inclusive
     * @throws IllegalArgumentException if either number is <code>null</code>
     * @throws IllegalArgumentException if the numbers are of different types
     * @throws IllegalArgumentException if the numbers don't implement <code>Comparable</code>
     */
    /**
     *  modified by lee,sangyub 2016.02.03
     */
    public StringRange(boolean hasMin, boolean hasMax, String str1, String str2) {
        this.hasMin = hasMin;
        this.hasMax = hasMax;
        
        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException("The strings must not be null");
        }
        if (str1.getClass() != str2.getClass()) {
            throw new IllegalArgumentException("The strings must be of the same type");
        }
        if (str1 instanceof Comparable == false) {
            throw new IllegalArgumentException("The strings must implement Comparable");
        }
        
        int compare = ((Comparable) str1).compareTo(str2);
        if (compare == 0) {
            this.hasMin = true;
            this.hasMax = true;
            this.min = str1;
            this.max = str1;
        } else if (compare > 0) {
            this.min = str2;
            this.max = str1;
        } else {
            this.min = str1;
            this.max = str2;
        }
    }
    
    // Accessors
    //--------------------------------------------------------------------

    /**
     * <p>Returns the minimum number in this range.</p>
     *
     * @return the minimum number in this range
     */
    public String getMinimumString() {
        return min;
    }

    /**
     * <p>Returns the maximum number in this range.</p>
     *
     * @return the maximum number in this range
     */
    public String getMaximumString() {
        return max;
    }

    // Tests
    //--------------------------------------------------------------------
    
    /**
     * <p>Tests whether the specified <code>number</code> occurs within
     * this range.</p>
     * 
     * <p><code>null</code> is handled and returns <code>false</code>.</p>
     *
     * @param number  the number to test, may be <code>null</code>
     * @return <code>true</code> if the specified number occurs within this range
     * @throws IllegalArgumentException if the number is of a different type to the range
     */
    public boolean containsString(String str) {
        boolean minchk = false;
        boolean maxchk = false;
        
        if (str == null) {
            return false;
        }
        if (str.getClass() != min.getClass()) {
            throw new IllegalArgumentException("The string must be of the same type as the range numbers");
        }
        int compareMin = ((Comparable) min).compareTo(str);
        if(hasMin && compareMin<=0)
            minchk= true;
        else if( !hasMin && compareMin<0 )
            minchk= true;
        
        int compareMax = ((Comparable) max).compareTo(str);
        if(hasMax && compareMax>=0)
            maxchk= true;
        else if( !hasMax && compareMax>0 )
            maxchk= true;
        return minchk && maxchk;
        // return compareMin <= 0 && compareMax >= 0;
    }

    // Range tests
    //--------------------------------------------------------------------
    // use Range implementations

    // Basics
    //--------------------------------------------------------------------

    /**
     * <p>Compares this range to another object to test if they are equal.</p>.
     * 
     * <p>To be equal, the class, minimum and maximum must be equal.</p>
     *
     * @param obj the reference object with which to compare
     * @return <code>true</code> if this object is equal
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StringRange == false) {
            return false;
        }
        StringRange range = (StringRange) obj;
        return min.equals(range.min) && max.equals(range.max);
    }

    /**
     * <p>Gets a hashCode for the range.</p>
     *
     * @return a hash code value for this object
     */
    public int hashCode() {
        if (hashCode == 0) {
            hashCode = 17;
            hashCode = 37 * hashCode + getClass().hashCode();
            hashCode = 37 * hashCode + min.hashCode();
            hashCode = 37 * hashCode + max.hashCode();
        }
        return hashCode;
    }

    /**
     * <p>Gets the range as a <code>String</code>.</p>
     *
     * <p>The format of the String is 'Range[<i>min</i>,<i>max</i>]'.</p>
     *
     * @return the <code>String</code> representation of this range
     */
    public String toString() {
        if (toString == null) {
            StringBuffer buf = new StringBuffer(32);
            buf.append("Range[");
            buf.append(min);
            buf.append(',');
            buf.append(max);
            buf.append(']');
            toString = buf.toString();
        }
        return toString;
    }
    
    
    public static void main(String args[]) {
        try {
            
            StringRange sr1 = new StringRange("def","def");
            System.out.println(" NumberRange(def, def) contains def : "+sr1.containsString("def"));
            System.out.println(" NumberRange(def, def) contains defg : "+sr1.containsString("defg"));
            System.out.println("");
            StringRange sr2 = new StringRange("def","jkl");
            System.out.println(" NumberRange(def, jkl) contains abc : "+sr2.containsString("abc"));
            System.out.println(" NumberRange(def, jkl) contains abcZ1234 : "+sr2.containsString("abcZ1234"));
            System.out.println(" NumberRange(def, jkl) contains def : "+sr2.containsString("def"));
            System.out.println(" NumberRange(def, jkl) contains defg : "+sr2.containsString("defg"));
            System.out.println(" NumberRange(def, jkl) contains defgUUUU12134837 : "+sr2.containsString("defgUUUU12134837"));
            System.out.println(" NumberRange(def, jkl) contains ghi : "+sr2.containsString("ghi"));
            System.out.println(" NumberRange(def, jkl) contains jkl : "+sr2.containsString("jkl"));
            System.out.println(" NumberRange(def, jkl) contains jklm : "+sr2.containsString("jklm"));
            System.out.println(" NumberRange(def, jkl) contains mno : "+sr2.containsString("mno"));
            System.out.println("");
            StringRange sr3 = new StringRange(true, false, "def","jkl");
            System.out.println(" NumberRange(true, false, def, jkl) contains abc : "+sr3.containsString("abc"));
            System.out.println(" NumberRange(true, false, def, jkl) contains abcZ1234 : "+sr3.containsString("abcZ1234"));
            System.out.println(" NumberRange(true, false, def, jkl) contains def : "+sr3.containsString("def"));
            System.out.println(" NumberRange(true, false, def, jkl) contains defg : "+sr3.containsString("defg"));
            System.out.println(" NumberRange(true, false, def, jkl) contains defgUUUU12134837 : "+sr3.containsString("defgUUUU12134837"));
            System.out.println(" NumberRange(true, false, def, jkl) contains ghi : "+sr3.containsString("ghi"));
            System.out.println(" NumberRange(true, false, def, jkl) contains jkl : "+sr3.containsString("jkl"));
            System.out.println(" NumberRange(true, false, def, jkl) contains jklm : "+sr3.containsString("jklm"));
            System.out.println(" NumberRange(true, false, def, jkl) contains mno : "+sr3.containsString("mno"));
            System.out.println("");
            StringRange sr4 = new StringRange(true, false, "0123456", "jkl");
            System.out.println(" NumberRange(true, false, 0123456, jkl) contains abc1234 : "+sr4.containsString("abc1234"));
            System.out.println("");
            StringRange sr5 = new StringRange(true, false, "zzz", "CCC");
            System.out.println(" NumberRange(true, false, zzz, CCC) contains AAA1234 : "+sr4.containsString("AAA1234"));
            System.out.println(" NumberRange(true, false, zzz, CCC) contains kkk1234 : "+sr4.containsString("kkk1234"));
            
            
            
        } catch(Exception e) {
            
        }
    }
}

