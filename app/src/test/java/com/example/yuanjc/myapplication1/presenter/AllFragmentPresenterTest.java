package com.example.yuanjc.myapplication1.presenter;

import android.content.Context;

import com.example.yuanjc.myapplication1.bean.Fund;
import com.example.yuanjc.myapplication1.model.AllFragmentModel;
import com.example.yuanjc.myapplication1.util.DataUtil;
import com.example.yuanjc.myapplication1.view.mainFragment.AllFragmentContract;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by yuanjc on 2016/8/3.
 */
public class AllFragmentPresenterTest {
    @Mock
    public AllFragmentContract.IAllFragmentView iView;
    @Mock
    public AllFragmentContract.IAllFragmentModel iModel;


    public static ArrayList<Fund> funds;
    public int[] order;
    public ArrayList<Double> netValues;
    public ArrayList<Double> debuffs;
    public AllFragmentPresenter presenter;

    @Captor
    public ArgumentCaptor<String> captor;
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
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        presenter=new AllFragmentPresenter(iView);
        presenter.setModel(iModel);
        netValues=new ArrayList<>();
        debuffs=new ArrayList<>();
        order=new int[10000];
    }
    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSetData() throws Exception {
//        List<String> list=mock(List.class);
//        when(list.get(0)).thenReturn("nihao");
//        String result=list.get(0);
//        verify(list).get(0);
//        Assert.assertEquals(result,list.get(0));
        when(iModel.getFunds()).thenReturn(funds);
        when(iModel.getFunds(Fund.Type.QUANBU)).thenReturn(funds);
        netValues.clear();
        debuffs.clear();
        presenter.setData();
        for(int i=0;i<4;i++){
            netValues.add(funds.get(i).getNetValue().getThisYearValue());
            System.out.println(funds.get(i).getNetValue().getThisYearValue());
            debuffs.add(funds.get(i).getDebuff().getThisYearValue());
            System.out.println(funds.get(i).getDebuff().getThisYearValue());
        }
//        System.out.println(netValues.get(0).toString());
        DataUtil.descendingOrder(order,debuffs);

        verify(iView).iniListView(iModel.getFunds(),netValues,debuffs,order);

    }

    /**
     * updataData()单元测试
     * 测试用例1：TYPE.QUANBU,OrderType.NETVALUE_ASCEND,ValueType.DAYVALUE
     * @throws Exception
     */
    @Test
    public void testUpdateData1() throws Exception {
        when(iModel.getFunds(
                Fund.Type.HUNHE
        )).thenReturn(funds);
        presenter.setCurrentOrderType(AllFragmentPresenter.OrderType.NETVALUE_ASCEND);
        presenter.setCurrentValueType(AllFragmentPresenter.ValueType.DAYVALUE);
        netValues.clear();
        debuffs.clear();
        presenter.updateData(Fund.Type.HUNHE);
        for(int i=0;i<4;i++){
            netValues.add(funds.get(i).getNetValue().getDayValue());
            System.out.println(funds.get(i).getNetValue().getDayValue());
            debuffs.add(funds.get(i).getDebuff().getDayValue());
            System.out.println(funds.get(i).getDebuff().getDayValue());
        }
        DataUtil.ascendingOrder(order,netValues);
        verify(iView).updateListView(iModel.getFunds(Fund.Type.HUNHE),netValues,debuffs,order);
    }
    /**
     * updataData()单元测试
     * 测试用例2：TYPE.QUANBU,OrderType.DEBUFF_DESCEND,ValueType.MONTHVALUE
     * @throws Exception
     */
    @Test
    public void testUpdateData2() throws Exception {
        when(iModel.getFunds(
                Fund.Type.HUOBI
        )).thenReturn(funds);
        presenter.setCurrentOrderType(AllFragmentPresenter.OrderType.DEBUFF_DESCEND);
        presenter.setCurrentValueType(AllFragmentPresenter.ValueType.MONTHVALUE);
        netValues.clear();
        debuffs.clear();
        presenter.updateData(Fund.Type.HUOBI);
        for(int i=0;i<4;i++){
            netValues.add(funds.get(i).getNetValue().getMonthValue());
            System.out.println(funds.get(i).getNetValue().getMonthValue());
            debuffs.add(funds.get(i).getDebuff().getMonthValue());
            System.out.println(funds.get(i).getDebuff().getMonthValue());
        }
        DataUtil.descendingOrder(order,debuffs);
        verify(iView).updateListView(iModel.getFunds(Fund.Type.HUOBI),netValues,debuffs,order);
    }

    /**
     * updataData()单元测试
     * 测试用例3：TYPE.QUANBU,null(ValueType.THISYEARVALUE),null(OrderType.DEBUFF_DESCEND)
     * @throws Exception
     */
    @Test
    public void testUpdateData3() throws Exception {
        when(iModel.getFunds(
                Fund.Type.QUANBU
        )).thenReturn(funds);
        presenter.setCurrentOrderType(null);
        presenter.setCurrentValueType(null);
        netValues.clear();
        debuffs.clear();
        presenter.updateData(Fund.Type.QUANBU);
        for(int i=0;i<4;i++){
            netValues.add(funds.get(i).getNetValue().getThisYearValue());
            System.out.println(funds.get(i).getNetValue().getThisYearValue());
            debuffs.add(funds.get(i).getDebuff().getThisYearValue());
            System.out.println(funds.get(i).getDebuff().getThisYearValue());
        }
        DataUtil.descendingOrder(order,debuffs);
        verify(iView).updateListView(iModel.getFunds(Fund.Type.QUANBU),netValues,debuffs,order);
    }

    /**
     * showSelctTypeData()单元测试
     * 测试用例1：Fund.Type.QUANBU, AllFragmentPresenter.ValueType.ALLVALUE
     * , AllFragmentPresenter.OrderType.DEBUFF_ASCEND
     * @throws Exception
     */
    @Test
    public void testShowSelctTypeData1() throws Exception {
        when(iModel.getFunds(
                Fund.Type.QUANBU
        )).thenReturn(funds);
        netValues.clear();
        debuffs.clear();
        presenter.showSelctTypeData(Fund.Type.QUANBU, AllFragmentPresenter.ValueType.ALLVALUE
                , AllFragmentPresenter.OrderType.DEBUFF_ASCEND);
        for(int i=0;i<4;i++){
            netValues.add(funds.get(i).getNetValue().getAllValue());
            debuffs.add(funds.get(i).getDebuff().getAllValue());
        }
        DataUtil.ascendingOrder(order,debuffs);
        verify(iView).showSelectTypeDataListView(iModel.getFunds(Fund.Type.QUANBU)
                ,netValues,debuffs,order);
    }
    /**
     * showSelctTypeData()单元测试
     * 测试用例2：Fund.Type.QUANBU, null(currentValueType== AllFragmentPresenter.ValueType.ALLVALUE)
     * ,null(currentOrderType==AllFragmentPresenter.OrderType.DEBUFF_ASCEND)
     * @throws Exception
     */
    @Test
    public void testShowSelctTypeData2() throws Exception {
        when(iModel.getFunds(
                Fund.Type.QUANBU
        )).thenReturn(funds);
        netValues.clear();
        debuffs.clear();
        presenter.setCurrentValueType(AllFragmentPresenter.ValueType.ALLVALUE);
        presenter.setCurrentOrderType(AllFragmentPresenter.OrderType.DEBUFF_ASCEND);
        presenter.showSelctTypeData(Fund.Type.QUANBU, null, null);
        for(int i=0;i<4;i++){
            netValues.add(funds.get(i).getNetValue().getAllValue());
            debuffs.add(funds.get(i).getDebuff().getAllValue());
        }
        DataUtil.ascendingOrder(order,debuffs);
        verify(iView).showSelectTypeDataListView(iModel.getFunds(Fund.Type.QUANBU)
                ,netValues,debuffs,order);
    }

    /**
     * showSelctTypeData()单元测试
     * 测试用例3：Fund.Type.QUANBU, null(currentValueType== null,
     * 默认是ValueType.THISYEARVALUE)
     * ,null(currentOrderType==null，默认是OrderType.DEBUFF_DESCEND)
     * @throws Exception
     */
    @Test
    public void testShowSelctTypeData3() throws Exception {
        when(iModel.getFunds(
                Fund.Type.QUANBU
        )).thenReturn(funds);
        netValues.clear();
        debuffs.clear();
        presenter.setCurrentValueType(null);
        presenter.setCurrentOrderType(null);
        presenter.showSelctTypeData(Fund.Type.QUANBU, null, null);
        for(int i=0;i<4;i++){
            netValues.add(funds.get(i).getNetValue().getThisYearValue());
            debuffs.add(funds.get(i).getDebuff().getThisYearValue());
        }
        DataUtil.descendingOrder(order,debuffs);
        verify(iView).showSelectTypeDataListView(iModel.getFunds(Fund.Type.QUANBU)
                ,netValues,debuffs,order);
    }

    /**
     * 这个里面是直接调用同一个类里面的公有函数updateData(),
     * 测了updateData()就不用测这个函数了
     * @throws Exception
     */
    @Test
    public void testChangeSelectTypeData() throws Exception {

    }

    /**
     * goTo()单元测试
     * 测试用例：Fund.Type.QUANBU,0
     * @throws Exception
     */
    @Test
    public void testGoTo() throws Exception {
        when(iModel.getFunds(
                Fund.Type.HUOBI
        )).thenReturn(funds);
        order[0]=3;
        order[1]=0;
        order[2]=1;
        order[3]=2;
        presenter.setOrder(order);
        presenter.goTo(Fund.Type.HUOBI,0);
        verify(iView).goTo(funds.get(order[0]).getName()
                ,funds.get(order[0]).getId(),
                funds.get(order[0]).getType().toString(),
                funds.get(order[0]).isAIP_isok(),
                funds.get(order[0]).isBuy_isok());
    }
}