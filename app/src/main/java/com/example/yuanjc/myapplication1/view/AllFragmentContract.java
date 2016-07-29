package com.example.yuanjc.myapplication1.view;

import android.app.Activity;
import android.widget.BaseAdapter;

import com.example.yuanjc.myapplication1.bean.Fund;
import com.example.yuanjc.myapplication1.parenter.AllFragmentPresenter;

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
        void iniListView(BaseAdapter adapter);
        void updateListView(BaseAdapter adapter);
        void showSelectTypeDataListView(BaseAdapter adapter);

    }
    interface IAllFragmentPresenter{
        void setData();
        void updateData(Fund.Type type);
        void showSelctTypeData(Fund.Type type, AllFragmentPresenter.ValueType valueType, AllFragmentPresenter.OrderType orderType);
        void changeSelectTypeData(Fund.Type type);
        void goTo(Activity activity, Fund.Type t,int p);
    }
}
