package com.example.yuanjc.myapplication1.model;

import com.example.yuanjc.myapplication1.bean.Fund;

import java.util.ArrayList;

/**
 * Created by yuanjc on 2016/7/25.
 */
public class AllFragmentModel implements IAllFragmentModel {
    private ArrayList<Fund> funds;
    public AllFragmentModel(){
        funds=new ArrayList<Fund>();
        setData();
    }
    private void setData(){
        int id=10005;
        double netValue=1.005;
        for(int i=0;i<10;i++){
            Fund fund=new Fund();
            if(i%3==0) {
                fund.setLike(true);
                fund.setState("发行中");
            }else {
                fund.setLike(false);
                fund.setState("--");
            }
            fund.setId(Integer.valueOf(id+i).toString());
            fund.setName("易基金");
            fund.setNetValue(Double.valueOf(netValue+i*0.001).toString());
            fund.setTime("2015-2-16");
            funds.add(fund);
        }
    }

    @Override
    public ArrayList<Fund> getFunds() {
        return funds;
    }
}
