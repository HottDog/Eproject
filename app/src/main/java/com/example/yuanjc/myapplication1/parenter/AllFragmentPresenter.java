package com.example.yuanjc.myapplication1.parenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.yuanjc.myapplication1.DataAdapter;
import com.example.yuanjc.myapplication1.bean.Fund;
import com.example.yuanjc.myapplication1.model.AllFragmentModel;
import com.example.yuanjc.myapplication1.util.DataUtil;
import com.example.yuanjc.myapplication1.view.AllFragmentContract;
import com.example.yuanjc.myapplication1.view.DetailActivity;

import java.util.ArrayList;

/**
 * Created by yuanjc on 2016/7/25.
 */
public class AllFragmentPresenter implements AllFragmentContract.IAllFragmentPresenter{
    private AllFragmentContract.IAllFragmentView view;
    private AllFragmentContract.IAllFragmentModel data;
    private int[] order;
    private ArrayList<Double> netValues;
    private ArrayList<Double> debuffs;
    private DataAdapter adapter;
    //目前显示的数据类型
    private ValueType currentValueType;
    //目前显示的顺序
    private OrderType currentOrderType;
    public AllFragmentPresenter(AllFragmentContract.IAllFragmentView view, Context context){
        this.view=view;
        data=new AllFragmentModel();
        adapter=new DataAdapter(context);
        netValues=new ArrayList<>();
        debuffs=new ArrayList<>();
        order=new int[10000];
    }
    @Override
    /**
     * 默认是设置今年以来,降序,全部
     */
    public void setData(){
        if(data.getFunds()!=null&&data.getFunds().size()>0){
            //设置显示今年以来的数据
            currentValueType=ValueType.THISYEARVALUE;
            setValuse(Fund.Type.QUANBU,currentValueType);
            //设置以debuff的降序显示
            currentOrderType=OrderType.DEBUFF_DESCEND;
            setOrder(Fund.Type.QUANBU,currentOrderType);
            adapter.setFunds(data.getFunds(),netValues,debuffs,order);
        }
        view.iniListView(adapter);
    }
    @Override
    public void updateData(Fund.Type type){
        if(data.getFunds(type)!=null&&data.getFunds(type).size()>0){
            //设置显示今年以来的数据
            if(currentValueType==null){
                currentValueType=ValueType.THISYEARVALUE;
            }
            setValuse(type,currentValueType);
            //设置以debuff的降序显示
            if(currentOrderType==null){
                currentOrderType=OrderType.DEBUFF_DESCEND;
            }
            setOrder(type,currentOrderType);
            adapter.setFunds(data.getFunds(type),netValues,debuffs,order);
        }else {
            adapter.setFunds(data.getFunds(type));
        }
        view.updateListView(adapter);
    }

    @Override
    public void showSelctTypeData(Fund.Type type, ValueType valueType, OrderType orderType) {
        if(data.getFunds(type)!=null&&data.getFunds(type).size()>0){
            //设置显示今年以来的数据
            if(valueType!=null) {
                currentValueType = valueType;
            }
            setValuse(type,currentValueType);
            //设置以debuff的降序显示
            currentOrderType=orderType;
            setOrder(type,currentOrderType);
            adapter.setFunds(data.getFunds(type),netValues,debuffs,order);
        }
        view.showSelectTypeDataListView(adapter);
    }

    @Override
    public void changeSelectTypeData(Fund.Type type) {
        updateData(type);
//        view.showSelectTypeDataListView(adapter);
    }

    @Override
    public void goTo(Activity activity, Fund.Type t,int p) {
        if(data.getFunds(t)!=null&&data.getFunds(t).size()>0) {
            Intent intent = new Intent(activity, DetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("name",data.getFunds(t).get(order[p]).getName() );
            bundle.putString("id",data.getFunds(t).get(order[p]).getId());
            bundle.putString("type",data.getFunds(t).get(order[p]).getType().toString());
            bundle.putBoolean("AIP",data.getFunds(t).get(order[p]).isAIP_isok());
            bundle.putBoolean("buy",data.getFunds(t).get(order[p]).isBuy_isok());
            intent.putExtras(bundle);
            activity.startActivity(intent);
        }
    }

    private void setValuse(Fund.Type type,ValueType valueType){
        if(data.getFunds(type)!=null){
            netValues.clear();
            debuffs.clear();
            int length=data.getFunds(type).size();
            switch (valueType) {
                case THISYEARVALUE:
                    for (int i = 0; i < length; i++) {
                        netValues.add(data.getFunds(type).get(i).getNetValue().getThisYearValue());
                        debuffs.add(data.getFunds(type).get(i).getDebuff().getThisYearValue());
                    }
                    break;
                case DAYVALUE:
                    for (int i = 0; i < length; i++) {
                        netValues.add(data.getFunds(type).get(i).getNetValue().getDayValue());
                        debuffs.add(data.getFunds(type).get(i).getDebuff().getDayValue());
                    }
                    break;
                case ALLVALUE:
                    for (int i = 0; i < length; i++) {
                        netValues.add(data.getFunds(type).get(i).getNetValue().getAllValue());
                        debuffs.add(data.getFunds(type).get(i).getDebuff().getAllValue());
                    }
                    break;
                case RECENTYEARVALUE:
                    for (int i = 0; i < length; i++) {
                        netValues.add(data.getFunds(type).get(i).getNetValue().getRecentYearValue());
                        debuffs.add(data.getFunds(type).get(i).getDebuff().getRecentYearValue());
                    }
                    break;
                case THREEMONTHVALUE:
                    for (int i = 0; i < length; i++) {
                        netValues.add(data.getFunds(type).get(i).getNetValue().getThreeMonthValue());
                        debuffs.add(data.getFunds(type).get(i).getDebuff().getThreeMonthValue());
                    }
                    break;
                case WEEKVALUE:
                    for (int i = 0; i < length; i++) {
                        netValues.add(data.getFunds(type).get(i).getNetValue().getWeekValue());
                        debuffs.add(data.getFunds(type).get(i).getDebuff().getWeekValue());
                    }
                    break;
                case MONTHVALUE:
                    for (int i = 0; i < length; i++) {
                        netValues.add(data.getFunds(type).get(i).getNetValue().getMonthValue());
                        debuffs.add(data.getFunds(type).get(i).getDebuff().getMonthValue());
                    }
                    break;
                default:
                    break;

            }
        }
    }
    private void setOrder(Fund.Type type, OrderType orderType){
        if(data.getFunds(type)!=null&&data.getFunds(type).size()>0){
            switch (orderType){
                case DEBUFF_ASCEND:
                    DataUtil.ascendingOrder(order,debuffs);
                    break;
                case DEBUFF_DESCEND:
                    DataUtil.descendingOrder(order,debuffs);
                    break;
                case NETVALUE_ASCEND:
                    DataUtil.ascendingOrder(order,netValues);
                    break;
                case NETVALUE_DESCEND:
                    DataUtil.descendingOrder(order,netValues);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 显示的数据类型
     */
    public enum ValueType{
        THISYEARVALUE(1),DAYVALUE(2),ALLVALUE(3),RECENTYEARVALUE(4),
        THREEMONTHVALUE(5),WEEKVALUE(6),MONTHVALUE(7);
        ValueType(int i){

        }
    }

    /**
     * 排序的方式
     */
    public enum OrderType{
        DEBUFF_DESCEND(1),DEBUFF_ASCEND(2),NETVALUE_DESCEND(3),NETVALUE_ASCEND(4);
        OrderType(int i){}
    }
}
