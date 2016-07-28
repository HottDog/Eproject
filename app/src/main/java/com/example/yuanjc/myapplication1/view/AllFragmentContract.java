package com.example.yuanjc.myapplication1.view;

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

    }
    interface IAllFragmentView {
        void iniListView(BaseAdapter adapter);
        void updateListView(BaseAdapter adapter);
        void showSelectTypeDataListView(BaseAdapter adapter);
    }
    interface IAllFragmentPresenter{
        void setData();
        void updateData();
        void showSelctTypeData(AllFragmentPresenter.ValueType valueType, AllFragmentPresenter.OrderType orderType);
    }
}
