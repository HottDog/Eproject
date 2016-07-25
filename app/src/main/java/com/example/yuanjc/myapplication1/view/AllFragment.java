package com.example.yuanjc.myapplication1.view;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuanjc.myapplication1.ItemSelectPopWindow;
import com.example.yuanjc.myapplication1.R;
import com.example.yuanjc.myapplication1.RefreshAndLoadListView;
import com.example.yuanjc.myapplication1.bean.Fund;
import com.example.yuanjc.myapplication1.parenter.AllFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by yuanjc on 2016/7/21.
 */
public class AllFragment extends Fragment implements View.OnClickListener,IAllFragmentView{

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

    private ImageView iv4;
    private ImageView iv21;
    private ImageView iv22;

    private RefreshAndLoadListView listView;

    private ItemSelectPopWindow itemSelectPopWindow;
    //单位净值排序，true为升序，false为降序,默认为降序
    private boolean netvalue=false;
    //item排序，true为升序，false为降序
    private boolean item=false;
    //presenter
    private AllFragmentPresenter presenter;
    //handler
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case ONE:
                    clearSelect();
                    re4.setBackgroundResource(R.color.blue);
                    tv4.setTextColor(getResources().getColor(R.color.white));
                    tv4.setText("近一周");
                    tv22.setText("近一周");
                    iv21.setVisibility(View.GONE);
                    break;
                case TWO:
                    clearSelect();
                    re4.setBackgroundResource(R.color.blue);
                    tv4.setTextColor(getResources().getColor(R.color.white));
                    tv4.setText("近一月");
                    tv22.setText("近一月");
                    iv21.setVisibility(View.GONE);
                    break;
                case THREE:
                    clearSelect();
                    re4.setBackgroundResource(R.color.blue);
                    tv4.setTextColor(getResources().getColor(R.color.white));
                    tv4.setText("近三月");
                    tv22.setText("近三月");
                    iv21.setVisibility(View.GONE);
                    break;
                case FOUR:
                    clearSelect();
                    re4.setBackgroundResource(R.color.blue);
                    tv4.setTextColor(getResources().getColor(R.color.white));
                    tv4.setText("成立以来");
                    tv22.setText("成立以来");
                    iv21.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    };
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

        iv4=(ImageView)layout.findViewById(R.id.iv4);
        iv21=(ImageView)layout.findViewById(R.id.iv21);
        iv22=(ImageView)layout.findViewById(R.id.iv22);

        listView=(RefreshAndLoadListView)layout.findViewById(R.id.listview);
        if(getActivity()!=null) {
            presenter = new AllFragmentPresenter(this, getActivity());
            presenter.setData();
        }
        //set the click event
        re1.setOnClickListener(this);
        re2.setOnClickListener(this);
        re3.setOnClickListener(this);
        re4.setOnClickListener(this);
        re21.setOnClickListener(this);
        re22.setOnClickListener(this);

        //popwindow
        if(null!=getActivity()) {
            if(itemSelectPopWindow==null) {
                itemSelectPopWindow = new ItemSelectPopWindow(getActivity(), handler);
            }
            itemSelectPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                      iv4.setImageResource(R.mipmap.arrow_bottom);
                }
            });
        }
        defaultSetting();
        //listview
        listView=(RefreshAndLoadListView)layout.findViewById(R.id.listview);
        listView.setOnRefreshListener(new RefreshAndLoadListView.OnRefreshListener() {
            @Override
            public void onDownPullRefresh() {
                presenter.update();
            }

            @Override
            public void onLoadingMore() {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.re1:
                setSelect(1);
                break;
            case R.id.re2:
                setSelect(2);
                break;
            case R.id.re3:
                setSelect(3);
                break;
            case R.id.re4:
                setSelect(4);
                break;
            case R.id.re21:
                setSelect(5);
                break;
            case R.id.re22:
                setSelect(6);
                break;
            default:
                break;
        }
    }
    private void defaultSetting(){
        clearSelect();
        setSelect(3);


    }
    private void setSelect(int index){
        switch (index){
            case 1:
                clearSelect();
                re1.setBackgroundResource(R.color.blue);
                tv1.setTextColor(getResources().getColor(R.color.white));
                tv22.setText("日涨跌");
                iv21.setVisibility(View.GONE);
                break;
            case 2:
                clearSelect();
                re2.setBackgroundResource(R.color.blue);
                tv2.setTextColor(getResources().getColor(R.color.white));
                tv22.setText("近一年");
                iv21.setVisibility(View.GONE);
                break;
            case 3:
                clearSelect();
                re3.setBackgroundResource(R.color.blue);
                tv3.setTextColor(getResources().getColor(R.color.white));
                tv22.setText("今年以来");
                iv21.setVisibility(View.GONE);
                break;
            case 4:
                if(null!=getActivity()) {
                    if(itemSelectPopWindow==null){
                        itemSelectPopWindow = new ItemSelectPopWindow(getActivity(), handler);
                    }
                    itemSelectPopWindow.showPopupWindow(re4);
                    iv4.setImageResource(R.mipmap.arrow_top);
                }
                break;
            case 5:
                iv21.setVisibility(View.VISIBLE);
                iv22.setVisibility(View.GONE);
                if(netvalue){
                    iv21.setImageResource(R.mipmap.arrow_top);
                    netvalue=false;
                }else {
                    iv21.setImageResource(R.mipmap.arrow_bottom);
                    netvalue=true;
                }
                break;
            case 6:
                iv21.setVisibility(View.GONE);
                iv22.setVisibility(View.VISIBLE);
                if(item){
                    iv22.setImageResource(R.mipmap.arrow_top);
                    item=false;
                }else {
                    iv22.setImageResource(R.mipmap.arrow_bottom);
                    item=true;
                }
                break;
            default:
                break;
        }
    }
    private void clearSelect(){
        re1.setBackgroundResource(R.color.white);
        re2.setBackgroundResource(R.color.white);
        re3.setBackgroundResource(R.color.white);
        re4.setBackgroundResource(R.color.white);

        tv1.setTextColor(getResources().getColor(R.color.hui));
        tv2.setTextColor(getResources().getColor(R.color.hui));
        tv3.setTextColor(getResources().getColor(R.color.hui));
        tv4.setTextColor(getResources().getColor(R.color.hui));

        tv21.setVisibility(View.VISIBLE);
        tv22.setVisibility(View.VISIBLE);
    }

    @Override
    public void iniListView(BaseAdapter adapter) {
        listView.setAdapter(adapter);
    }

    @Override
    public void updateListView(BaseAdapter adapter) {
        adapter.notifyDataSetChanged();
    }


    public final static int ONE=0X111;
    public final static int TWO=0X112;
    public final static int THREE=0X113;
    public final static int FOUR=0X114;

}
