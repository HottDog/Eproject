package com.example.yuanjc.myapplication1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by yuanjc on 2016/7/21.
 */
public class AllFragment extends Fragment implements View.OnClickListener{

    private RelativeLayout re1;
    private RelativeLayout re2;
    private RelativeLayout re3;
    private RelativeLayout re4;
    private LinearLayout re21;
    private LinearLayout re22;

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv21;
    private TextView tv22;

    private ImageView iv21;
    private ImageView iv22;
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
        View layout=inflater.inflate(R.layout.allfragment, container, false);
        initView(layout);
        return layout;
    }
    private void initView(View layout){
        re1=(RelativeLayout)layout.findViewById(R.id.re1);
        re2=(RelativeLayout)layout.findViewById(R.id.re2);
        re3=(RelativeLayout)layout.findViewById(R.id.re3);
        re4=(RelativeLayout)layout.findViewById(R.id.re4);

        re21=(LinearLayout)layout.findViewById(R.id.re21);
        re22=(LinearLayout)layout.findViewById(R.id.re22);

        tv1=(TextView)layout.findViewById(R.id.tv1);
        tv2=(TextView)layout.findViewById(R.id.tv2);
        tv3=(TextView)layout.findViewById(R.id.tv3);
        tv4=(TextView)layout.findViewById(R.id.tv4);
        tv21=(TextView)layout.findViewById(R.id.tv21);
        tv22=(TextView)layout.findViewById(R.id.tv22);

        iv21=(ImageView)layout.findViewById(R.id.iv21);
        iv22=(ImageView)layout.findViewById(R.id.iv22);
        //set the click event
        re1.setOnClickListener(this);
        re2.setOnClickListener(this);
        re3.setOnClickListener(this);
        re4.setOnClickListener(this);
        re21.setOnClickListener(this);
        re22.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.re1:
                break;
            case R.id.re2:
                break;
            case R.id.re3:
                break;
            case R.id.re4:
                break;
            case R.id.re21:
                break;
            case R.id.re22:
                break;
            default:
                break;
        }
    }
    private void setSelect(int index){
        switch (index){
            case 1:

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
                break;
        }
    }

}
