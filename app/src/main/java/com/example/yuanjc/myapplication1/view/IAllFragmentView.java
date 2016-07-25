package com.example.yuanjc.myapplication1.view;

import android.widget.BaseAdapter;

import com.example.yuanjc.myapplication1.bean.Fund;

import java.util.ArrayList;

/**
 * Created by yuanjc on 2016/7/25.
 */
public interface IAllFragmentView {
    void iniListView(BaseAdapter adapter);
    void updateListView(BaseAdapter adapter);
}
