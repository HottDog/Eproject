package com.example.yuanjc.myapplication1.model;

import com.example.yuanjc.myapplication1.bean.Fund;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by yuanjc on 2016/8/5.
 */
public class AllFragmentModelTest {
    public static ArrayList<Fund> funds;
    private ArrayList<Fund> typeFunds;
    public AllFragmentModel model;

    @BeforeClass
    public static void setUpOnce() throws Exception {
        funds=new ArrayList<>();
        //1组数据
        Fund fund=new Fund("易基金1", "00123", "2016-5-13",true,
                Fund.Type.HUNHE,true,true);
        fund.setNetValue(0.23,1.03,12.0,15.0,154.0,-40.1,-1.235);
        fund.setDebuff(9.23,-34.0,90.3,0.34,1.23,-23.12,56.23);
        funds.add(fund);
        //2组数据
        Fund fund1=new Fund("易基金2", "00124", "2016-5-13",true,
                Fund.Type.GUPIAO,false,true);
        fund1.setNetValue(0.56,-12.32,-15.02,15.0,154.0,40.1,-1.235);
        fund1.setDebuff(15.23,34.0,-90.3,56.34,1.23,23.12,-56.23);
        funds.add(fund1);
        //3组数据
        Fund fund2=new Fund("易基金3", "00125", "2016-5-13",false,
                Fund.Type.HUOBI,false,false);
        fund2.setNetValue(53.56,12.32,-15.02,1.50,14.0,-40.9,-1.235);
        fund2.setDebuff(-1.23,-34.0,-20.3,-56.34,11.23,83.12,0.235);
        funds.add(fund2);
        //4组数据
        Fund fund3=new Fund("易基金3", "00125", "2016-5-13",false,
                Fund.Type.HUOBI,false,true);
        fund3.setNetValue(23.56,-12.32,5.023,3.23,-14.0,60.23,23.02);
        fund3.setDebuff(-50.23,-34.0,20.365,-56.34,0.231,10.2,-0.235);
        funds.add(fund3);
//        adapter=new DataAdapter(context);
    }

    @Before
    public void setUp() throws Exception {
        model=new AllFragmentModel();
        typeFunds=new ArrayList<Fund>();
        model.setFunds(funds);
    }

    @Test
    public void testGetFunds() throws Exception {
        assertEquals(funds,model.getFunds());
    }

    /**
     * getFund()单元测试
     * 测试用例1：Fund.Type.HUOBI
     * @throws Exception
     */
    @Test
    public void testGetFunds1() throws Exception {
        typeFunds.clear();
        typeFunds.add(funds.get(2));
        typeFunds.add(funds.get(3));
        assertEquals(typeFunds,model.getFunds(Fund.Type.HUOBI));
    }

    /**
     * getFund()单元测试
     * 测试用例2：Fund.Type.HUNHE
     * @throws Exception
     */
    @Test
    public void testGetFunds2() throws Exception {
        typeFunds.clear();
        typeFunds.add(funds.get(0));
        assertEquals(typeFunds,model.getFunds(Fund.Type.HUNHE));
    }
}