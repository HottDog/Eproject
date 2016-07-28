package com.example.yuanjc.myapplication1.model;

import com.example.yuanjc.myapplication1.DataAdapter;
import com.example.yuanjc.myapplication1.bean.Fund;
import com.example.yuanjc.myapplication1.util.DataUtil;
import com.example.yuanjc.myapplication1.view.AllFragmentContract;

import java.util.ArrayList;

/**
 * Created by yuanjc on 2016/7/25.
 */
public class AllFragmentModel implements AllFragmentContract.IAllFragmentModel {
    private ArrayList<Fund> funds;
    public AllFragmentModel(){
        funds=new ArrayList<Fund>();
        setData();
    }
    private void setData(){
        int id=10005;
        for(int i=0;i<10;i++){
            Fund fund=new Fund();
            if(DataUtil.getIntRandom(0,9)/2==0){
                fund.setLike(true);
            }else {
                fund.setLike(false);
            }
            if(DataUtil.getIntRandom(0,9)/2==0){
                fund.setAIP_isok(true);
            }else {
                fund.setAIP_isok(false);
            }
            if(DataUtil.getIntRandom(0,9)/2==0){
                fund.setBuy_isok(true);
            }else {
                fund.setBuy_isok(false);
            }
            fund.setId(Integer.valueOf(id+DataUtil.getIntRandom(2,30)).toString());
            fund.setName("易基金"+Integer.valueOf(i).toString());
            fund.setTime("2015-02-"+Integer.valueOf(DataUtil.getIntRandom(2,20)).toString());
            fund.setDebuff(DataUtil.randomNP(DataUtil.getIntRandom(1,20)*0.001),
                    DataUtil.randomNP(DataUtil.getIntRandom(1,20)*0.001),
                    DataUtil.randomNP(DataUtil.getIntRandom(1,20)*0.001),
                    DataUtil.randomNP(DataUtil.getIntRandom(1,20)*0.001),
                    DataUtil.randomNP(DataUtil.getIntRandom(1,20)*0.001),
                    DataUtil.randomNP(DataUtil.getIntRandom(1,20)*0.001),
                    DataUtil.randomNP(DataUtil.getIntRandom(1,20)*0.001));
            fund.setNetValue(DataUtil.randomNP(DataUtil.getIntRandom(1,100)*1.00+DataUtil.getIntRandom(1,100)*0.01),
                    DataUtil.randomNP(DataUtil.getIntRandom(1,100)*1.00+DataUtil.getIntRandom(1,100)*0.01),
                    DataUtil.randomNP(DataUtil.getIntRandom(1,100)*1.00+DataUtil.getIntRandom(1,100)*0.01),
                    DataUtil.randomNP(DataUtil.getIntRandom(1,100)*1.00+DataUtil.getIntRandom(1,100)*0.01),
                    DataUtil.randomNP(DataUtil.getIntRandom(1,100)*1.00+DataUtil.getIntRandom(1,100)*0.01),
                    DataUtil.randomNP(DataUtil.getIntRandom(1,100)*1.00+DataUtil.getIntRandom(1,100)*0.01),
                    DataUtil.randomNP(DataUtil.getIntRandom(1,100)*1.00+DataUtil.getIntRandom(1,100)*0.01));
            funds.add(fund);
        }
    }

    @Override
    public ArrayList<Fund> getFunds() {
        return funds;
    }
}
