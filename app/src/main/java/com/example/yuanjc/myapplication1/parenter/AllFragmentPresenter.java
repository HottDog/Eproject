package com.example.yuanjc.myapplication1.parenter;

import android.content.Context;
import android.view.View;

import com.example.yuanjc.myapplication1.DataAdapter;
import com.example.yuanjc.myapplication1.model.AllFragmentModel;
import com.example.yuanjc.myapplication1.model.IAllFragmentModel;
import com.example.yuanjc.myapplication1.view.AllFragment;
import com.example.yuanjc.myapplication1.view.IAllFragmentView;

/**
 * Created by yuanjc on 2016/7/25.
 */
public class AllFragmentPresenter {
    private IAllFragmentView view;
    private IAllFragmentModel data;
    private DataAdapter adapter;
    public AllFragmentPresenter(IAllFragmentView view, Context context){
        this.view=view;
        data=new AllFragmentModel();
        adapter=new DataAdapter(context);
    }
    public void setData(){
        if(data.getFunds()!=null){
            adapter.setFunds(data.getFunds());
        }
        view.iniListView(adapter);
    }
    public void update(){
        if(data.getFunds()!=null){
            adapter.setFunds(data.getFunds());
        }
        view.updateListView(adapter);
    }

}
