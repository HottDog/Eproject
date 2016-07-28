package com.example.yuanjc.myapplication1.parenter;

import android.content.Context;
import android.content.Intent;

import com.example.yuanjc.myapplication1.DataAdapter;
import com.example.yuanjc.myapplication1.bean.Fund;
import com.example.yuanjc.myapplication1.model.AllFragmentModel;
import com.example.yuanjc.myapplication1.util.DataUtil;
import com.example.yuanjc.myapplication1.view.AllFragmentContract;

import java.util.ArrayList;

/**
 * Created by yuanjc on 2016/7/25.
 */
public class AllFragmentPresenter implements AllFragmentContract.IAllFragmentPresenter{
    private AllFragmentContract.IAllFragmentView view;
    private AllFragmentContract.IAllFragmentModel data;
    private ArrayList<Integer> order;
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
        order=new ArrayList<Integer>();
    }
    @Override
    /**
     * 默认是设置今年以来,降序
     */
    public void setData(){
        if(data.getFunds()!=null&&data.getFunds().size()>0){
            //设置显示今年以来的数据
            currentValueType=ValueType.THISYEARVALUE;
            setValuse(currentValueType);
            //设置以debuff的降序显示
            currentOrderType=OrderType.DEBUFF_DESCEND;
            setOrder(currentOrderType);
            adapter.setFunds(data.getFunds(),netValues,debuffs,order);
        }
        view.iniListView(adapter);
    }
    @Override
    public void updateData(){
        if(data.getFunds()!=null&&data.getFunds().size()>0){
            //设置显示今年以来的数据
            currentValueType=ValueType.THISYEARVALUE;
            setValuse(currentValueType);
            //设置以debuff的降序显示
            currentOrderType=OrderType.DEBUFF_DESCEND;
            setOrder(currentOrderType);
            adapter.setFunds(data.getFunds(),netValues,debuffs,order);
        }
        view.updateListView(adapter);
    }
    private void setValuse(ValueType valueType){
        if(data.getFunds()!=null){
            netValues.clear();
            debuffs.clear();
            int length=data.getFunds().size();
            switch (valueType) {
                case THISYEARVALUE:
                    for (int i = 0; i < length; i++) {
                        netValues.add(data.getFunds().get(i).getNetValue().getThisYearValue());
                        debuffs.add(data.getFunds().get(i).getDebuff().getThisYearValue());
                    }
                    break;
                case DAYVALUE:
                    for (int i = 0; i < length; i++) {
                        netValues.add(data.getFunds().get(i).getNetValue().getDayValue());
                        debuffs.add(data.getFunds().get(i).getDebuff().getDayValue());
                    }
                    break;
                case ALLVALUE:
                    for (int i = 0; i < length; i++) {
                        netValues.add(data.getFunds().get(i).getNetValue().getAllValue());
                        debuffs.add(data.getFunds().get(i).getDebuff().getAllValue());
                    }
                    break;
                case RECENTYEARVALUE:
                    for (int i = 0; i < length; i++) {
                        netValues.add(data.getFunds().get(i).getNetValue().getRecentYearValue());
                        debuffs.add(data.getFunds().get(i).getDebuff().getRecentYearValue());
                    }
                    break;
                case THREEMONTHVALUE:
                    for (int i = 0; i < length; i++) {
                        netValues.add(data.getFunds().get(i).getNetValue().getThreeMonthValue());
                        debuffs.add(data.getFunds().get(i).getDebuff().getThreeMonthValue());
                    }
                    break;
                case WEEKVALUE:
                    for (int i = 0; i < length; i++) {
                        netValues.add(data.getFunds().get(i).getNetValue().getWeekValue());
                        debuffs.add(data.getFunds().get(i).getDebuff().getWeekValue());
                    }
                    break;
                case MONTHVALUE:
                    for (int i = 0; i < length; i++) {
                        netValues.add(data.getFunds().get(i).getNetValue().getMonthValue());
                        debuffs.add(data.getFunds().get(i).getDebuff().getMonthValue());
                    }
                    break;
                default:
                    break;

            }
        }
    }
    private void setOrder(OrderType orderType){
        if(data.getFunds()!=null&&data.getFunds().size()>0){
            order.clear();
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
    public enum ValueType{
        THISYEARVALUE(1),DAYVALUE(2),ALLVALUE(3),RECENTYEARVALUE(4),
        THREEMONTHVALUE(5),WEEKVALUE(6),MONTHVALUE(7);
        ValueType(int i){

        }
    }
    public enum OrderType{
        DEBUFF_DESCEND(1),DEBUFF_ASCEND(2),NETVALUE_DESCEND(3),NETVALUE_ASCEND(4);
        OrderType(int i){}
    }
}
