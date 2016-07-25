package com.example.yuanjc.myapplication1.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuanjc.myapplication1.R;

/**
 * Created by yuanjc on 2016/7/21.
 */
public class YourselfFragment extends Fragment {
    //三个一般必须重载的方法
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View layout=inflater.inflate(R.layout.yourselffragment, container, false);
        return layout;
    }
}
