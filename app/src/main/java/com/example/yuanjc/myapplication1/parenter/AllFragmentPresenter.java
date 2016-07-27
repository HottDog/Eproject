package com.example.yuanjc.myapplication1.parenter;

import android.content.Context;

import com.example.yuanjc.myapplication1.DataAdapter;
import com.example.yuanjc.myapplication1.model.AllFragmentModel;
import com.example.yuanjc.myapplication1.view.AllFragmentContract;

/**
 * Created by yuanjc on 2016/7/25.
 */
public class AllFragmentPresenter implements AllFragmentContract.IAllFragmentPresenter{
    private AllFragmentContract.IAllFragmentView view;
    private AllFragmentContract.IAllFragmentModel data;
    private DataAdapter adapter;
    public AllFragmentPresenter(AllFragmentContract.IAllFragmentView view, Context context){
        this.view=view;
        data=new AllFragmentModel();
        adapter=new DataAdapter(context);
    }
    @Override
    public void setData(){
        if(data.getFunds()!=null){
            adapter.setFunds(data.getFunds());
        }
        view.iniListView(adapter);
    }
    @Override
    public void updateData(){
        if(data.getFunds()!=null){
            adapter.setFunds(data.getFunds());
        }
        view.updateListView(adapter);
    }


}
