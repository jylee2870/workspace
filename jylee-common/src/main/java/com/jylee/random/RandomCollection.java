package com.jylee.random;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

/*
 * 가중치를 가진 아이템을 가중치에 의한 확률로 가져오고 싶을때 사용한다. 
 * EX : 아이템         가중치 
 *      'S Grade'      5
 *      'A Grade'      10
 *      'B Grade'      50
 *      'C Grade'      25
 *      'D Grade'      10
 */
public class RandomCollection<E> {
    private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
    private final Random random;
    private double querykey;
    private double total = 0;

    public RandomCollection() {
        this(new Random());
    }

    public RandomCollection(Random random) {
        this.random = random;
    }

    public void add(double weight, E result) {
        if (weight <= 0) return;
        total += weight;
        map.put(total, result);
    }

    public E next() {
        double value = random.nextDouble() * total;
        querykey = map.ceilingEntry(value).getKey();
        return map.ceilingEntry(value).getValue();
    }
    
    public double getQueryKey() {
        return querykey;
    }
    
    public void remove(double weight) {
        map.remove(weight);
        total -= weight;
    }
    
}


