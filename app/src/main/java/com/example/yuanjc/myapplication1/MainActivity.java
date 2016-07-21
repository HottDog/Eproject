package com.example.yuanjc.myapplication1;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{
    private RelativeLayout hot;
    private RelativeLayout all;
    private RelativeLayout yourself;

    private TextView hot_tv;
    private TextView all_tv;
    private TextView yourself_tv;

    private LinearLayout hot_select;
    private LinearLayout all_select;
    private LinearLayout yourself_select;
    //fragment
    HotFragment hotFragment;
    AllFragment allFragment;
    YourselfFragment yourselfFragment;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intView();
    }
    private void intView(){
        hot=(RelativeLayout)findViewById(R.id.hot);
        all=(RelativeLayout)findViewById(R.id.all);
        yourself=(RelativeLayout)findViewById(R.id.yourself);

        hot_tv=(TextView)findViewById(R.id.hot_tv);
        all_tv=(TextView)findViewById(R.id.all_tv);
        yourself_tv=(TextView)findViewById(R.id.yourself_tv);

        hot_select=(LinearLayout)findViewById(R.id.hot_select);
        all_select=(LinearLayout)findViewById(R.id.all_select);
        yourself_select=(LinearLayout)findViewById(R.id.yourself_select);
        //setting click event
        hot.setOnClickListener(this);
        all.setOnClickListener(this);
        yourself.setOnClickListener(this);

        //initial the view setting
        all_tv.setVisibility(View.GONE);
        hot_select.setVisibility(View.GONE);
        yourself_select.setVisibility(View.GONE);

        //initial the fragment
        fm=getFragmentManager();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hot:
                selectChange(1);
                break;
            case R.id.all:
                selectChange(2);
                break;
            case R.id.yourself:
                selectChange(3);
                break;
            default:
                break;
        }
    }
    private void selectChange(int index){
        clearSelect();
        switch (index){
            case 1:
                hot_tv.setVisibility(View.GONE);
                all_select.setVisibility(View.GONE);
                yourself_select.setVisibility(View.GONE);
                break;
            case 2:
                all_tv.setVisibility(View.GONE);
                hot_select.setVisibility(View.GONE);
                yourself_select.setVisibility(View.GONE);
                break;
            case 3:
                yourself_tv.setVisibility(View.GONE);
                all_select.setVisibility(View.GONE);
                hot_select.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
    private void clearSelect(){
        yourself.setVisibility(View.VISIBLE);
        all_tv.setVisibility(View.VISIBLE);
        hot_tv.setVisibility(View.VISIBLE);
        all_select.setVisibility(View.VISIBLE);
        hot_select.setVisibility(View.VISIBLE);
        yourself_select.setVisibility(View.VISIBLE);
    }
}
