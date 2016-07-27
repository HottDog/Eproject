package com.example.yuanjc.myapplication1.util;

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
}
