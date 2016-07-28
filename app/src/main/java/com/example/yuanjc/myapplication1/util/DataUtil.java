package com.example.yuanjc.myapplication1.util;

import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by yuanjc on 2016/7/27.
 */
public class DataUtil {
    /**
     * 获取int型随机数
     * Math.random()是返回[0,1）之间的数
     * @param min 最小值
     * @param max 最大值
     * @return
     */
    public static final int getIntRandom(int min,int max){
        return (int)(min+Math.random()*(max-min+1));
    }
    public static final double randomNP(double d){
        if(getIntRandom(0,1)==1){
            return -d;
        }else {
            return d;
        }
    }

    /**
     * 获取升序序列
     * 计数排序算法
     * @param a
     * @param b
     * @param <T>
     */
    public static final <T extends Comparable<T>> void ascendingOrder
    (int []a,ArrayList<T> b){
        int length=b.size();
        int count;        //指向每次内循环的最小值的序数
        for(int i=0;i<length;i++){
            count=0;
            for(int j=0;j<length;j++){
                if(i!=j) {
                    if (b.get(i).compareTo(b.get(j)) > 0) {    //min>b.get(i)
                        count++;
                    } else if (b.get(i).compareTo(b.get(j)) == 0) {
                        if (i < j){
                            count++;
                        }
                    }
                }
            }
            a[count]=i;
        }
    }

    /**
     * 获取降序序列
     * 计数排序算法
     * @param a
     * @param b
     * @param <T>
     */
    public static final <T extends Comparable<T>> void descendingOrder
    (int []a, ArrayList<T> b){
        int length=b.size();
        int count;        //
        for(int i=0;i<length;i++){
            count=0;
            for(int j=0;j<length;j++){
                if(i!=j) {
                    if (b.get(i).compareTo(b.get(j)) < 0) {    //min>b.get(i)
                        count++;
                    } else if (b.get(i).compareTo(b.get(j)) == 0) {
                        if (i < j){
                            count++;
                        }
                    }
                }
            }
            a[count]=i;
        }
    }
}
