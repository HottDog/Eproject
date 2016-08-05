package com.example.yuanjc.myapplication1;


import static com.example.yuanjc.myapplication1.util.DataUtil.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

//import static com.example.yuanjc.myapplication1.util.DataUtil.ascendingOrder;
import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class DataUnitTest {
    private static ArrayList<Double> d;
    int [] a;

    @BeforeClass
    public static void  iniAllData(){
        d=new ArrayList<Double>();
        d.add(0.953);
        d.add(90.349);
        d.add(34.892);
        d.add(-90.23);
        d.add(-0.983);
        d.add(-34.34);
        d.add(0.983);
        d.add(-23.34);
    }
    @Before
    public void iniEveryTestData(){
        a=new int [100];
    }
    @Test
    /**
     *测试是否获取到给的doule数组的升序序列排名
     * a数据用来存放double数据的升序序列
     *
     */
    public void AscendingOrderTest(){
        ascendingOrder(a,d);
        int []b=new int [100];
        b[0]=3;b[3]=4;b[6]=2;
        b[1]=5;b[4]=0;b[7]=1;
        b[2]=7;b[5]=6;
//        b={3,5,7,4,0,6,2,1};
        assertArrayEquals(b,a);
    }
    /**
     *测试是否获取到给的doule数组的降序序列排名
     * a数据用来存放double数据的降序序列
     *
     */
    @Test
    public void DescendingOrderTest(){
        descendingOrder(a,d);
        int []b=new int[100];
        b[0]=1;b[3]=0;b[6]=5;
        b[1]=2;b[4]=4;b[7]=3;
        b[2]=6;b[5]=7;
        assertArrayEquals(b,a);
    }
    @Test
    /**
     * 只测试获取到的随机数是否在设计的最小值和最大值中间
     * 理论是想要获取的随机int数据的大小不会大于200000，不会小于-200000
     * 所以只测获取到的随机int数据的范围是[-200000,200000]
     */
    public void GetIntRandomTest(){
//        int min=-100;
//        int max=100;
        int min=-200000;
        int max=200000;
        int b=getIntRandom(min,max);
        assert (min<=b&&b<=max);
    }
    @Test
    public void RandomNPTest(){
        double value=0.983;
        assert (value==randomNP(value)||value==(-randomNP(value)));
    }
}