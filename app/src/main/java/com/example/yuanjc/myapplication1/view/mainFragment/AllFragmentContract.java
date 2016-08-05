package com.example.yuanjc.myapplication1.view.mainFragment;

import android.app.Activity;
import android.widget.BaseAdapter;

import com.example.yuanjc.myapplication1.bean.Fund;
import com.example.yuanjc.myapplication1.presenter.AllFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by yuanjc on 2016/7/27.
 */
public interface AllFragmentContract {
    interface IAllFragmentModel {
        ArrayList<Fund> getFunds();

        ArrayList<Fund> getFunds(Fund.Type t);
    }
    interface IAllFragmentView {
        void iniListView(ArrayList<Fund> funds,ArrayList<Double> netValues
                ,ArrayList<Double> debuffs,int []order);
        void updateListView(ArrayList<Fund> funds,ArrayList<Double> netValues
                ,ArrayList<Double> debuffs,int []order);
        void showSelectTypeDataListView(ArrayList<Fund> funds,ArrayList<Double> netValues
                ,ArrayList<Double> debuffs,int []order);
        void goTo(String name,String id,String type,boolean AIP,boolean buy);

    }
    interface IAllFragmentPresenter{
        void setData();
        void updateData(Fund.Type type);
        void showSelctTypeData(Fund.Type type, AllFragmentPresenter.ValueType valueType, AllFragmentPresenter.OrderType orderType);
        void changeSelectTypeData(Fund.Type type);
        void goTo( Fund.Type t,int p);
    }
}
