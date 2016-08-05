package com.example.yuanjc.myapplication1.view.mainFragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuanjc.myapplication1.adapter.DataAdapter;
import com.example.yuanjc.myapplication1.view.activity.DetailActivity;
import com.example.yuanjc.myapplication1.view.customView.ItemSelectPopWindow;
import com.example.yuanjc.myapplication1.R;
import com.example.yuanjc.myapplication1.view.customView.RefreshAndLoadListView;
import com.example.yuanjc.myapplication1.bean.Fund;
import com.example.yuanjc.myapplication1.presenter.AllFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by yuanjc on 2016/7/21.
 */
public class AllFragment extends Fragment implements View.OnClickListener,
        AllFragmentContract.IAllFragmentView {

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

    private DataAdapter adapter;

    private ItemSelectPopWindow itemSelectPopWindow;
    //单位净值排序，true为升序，false为降序,默认为降序
    private boolean netvalue=false;
    private boolean continus_netvalue=false;   //false表示目前没有连续点击，true表示目前是连续点击状态
    //item排序，true为升序，false为降序
    private boolean item=false;
    private boolean continus_item=false;   //false表示目前没有连续点击，true表示目前是连续点击状态
    //presenter
    private AllFragmentContract.IAllFragmentPresenter presenter;
    //要显示的数据类型
    private Fund.Type select= Fund.Type.QUANBU;
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
                    presenter.showSelctTypeData(select,AllFragmentPresenter.ValueType.WEEKVALUE,
                            AllFragmentPresenter.OrderType.DEBUFF_DESCEND);
                    break;
                case TWO:
                    clearSelect();
                    re4.setBackgroundResource(R.color.blue);
                    tv4.setTextColor(getResources().getColor(R.color.white));
                    tv4.setText("近一月");
                    tv22.setText("近一月");
                    iv21.setVisibility(View.GONE);
                    presenter.showSelctTypeData(select,AllFragmentPresenter.ValueType.MONTHVALUE,
                            AllFragmentPresenter.OrderType.DEBUFF_DESCEND);
                    break;
                case THREE:
                    clearSelect();
                    re4.setBackgroundResource(R.color.blue);
                    tv4.setTextColor(getResources().getColor(R.color.white));
                    tv4.setText("近三月");
                    tv22.setText("近三月");
                    iv21.setVisibility(View.GONE);
                    presenter.showSelctTypeData(select,AllFragmentPresenter.ValueType.THREEMONTHVALUE,
                            AllFragmentPresenter.OrderType.DEBUFF_DESCEND);
                    break;
                case FOUR:
                    clearSelect();
                    re4.setBackgroundResource(R.color.blue);
                    tv4.setTextColor(getResources().getColor(R.color.white));
                    tv4.setText("成立以来");
                    tv22.setText("成立以来");
                    iv21.setVisibility(View.GONE);
                    presenter.showSelctTypeData(select,AllFragmentPresenter.ValueType.ALLVALUE,
                            AllFragmentPresenter.OrderType.DEBUFF_DESCEND);
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
        presenter = new AllFragmentPresenter(this);
        presenter.setData();

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
                presenter.updateData(select);
                listView.hideHeaderView();
            }

            @Override
            public void onLoadingMore() {
                listView.hideFooterView();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("点击的position：",Integer.valueOf(position).toString());
//                Intent intent=new Intent(getActivity(),DetailActivity.class);
//                Bundle bundle=new Bundle();
                if(null!=getActivity()) {
                    presenter.goTo(select,position-1);
                }

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.re1:
                setSelect(1);
                presenter.showSelctTypeData(select,AllFragmentPresenter.ValueType.DAYVALUE,
                        AllFragmentPresenter.OrderType.DEBUFF_DESCEND);
                break;
            case R.id.re2:
                setSelect(2);
                presenter.showSelctTypeData(select,AllFragmentPresenter.ValueType.RECENTYEARVALUE,
                        AllFragmentPresenter.OrderType.DEBUFF_DESCEND);
                break;
            case R.id.re3:
                setSelect(3);
                presenter.showSelctTypeData(select,AllFragmentPresenter.ValueType.THISYEARVALUE,
                        AllFragmentPresenter.OrderType.DEBUFF_DESCEND);
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
    public void showSelectTypeView(Fund.Type t){
        select=t;
        presenter.changeSelectTypeData(select);
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
                continus_item=false;
                iv21.setVisibility(View.VISIBLE);
                iv22.setVisibility(View.GONE);
                if(continus_netvalue){
                    if(netvalue){
                        //递增状态
                        iv21.setImageResource(R.mipmap.arrow_top);
                        netvalue=false;
                        presenter.showSelctTypeData(select,null,
                                AllFragmentPresenter.OrderType.NETVALUE_ASCEND);
                    }else {
                        //递减状态
                        iv21.setImageResource(R.mipmap.arrow_bottom);
                        netvalue=true;
                        presenter.showSelctTypeData(select,null,
                                AllFragmentPresenter.OrderType.NETVALUE_DESCEND);
                    }
                }else {
                    //递减状态
                    iv21.setImageResource(R.mipmap.arrow_bottom);
                    netvalue=true;
                    presenter.showSelctTypeData(select,null,
                            AllFragmentPresenter.OrderType.NETVALUE_DESCEND);
                }
                continus_netvalue=true;
                break;
            case 6:
                continus_netvalue=false;
                iv21.setVisibility(View.GONE);
                iv22.setVisibility(View.VISIBLE);
                if(continus_item){
                    if(item){
                        //递增状态
                        iv22.setImageResource(R.mipmap.arrow_top);
                        item=false;
                        presenter.showSelctTypeData(select,null,
                                AllFragmentPresenter.OrderType.DEBUFF_ASCEND);
                    }else {
                        //递减状态
                        iv22.setImageResource(R.mipmap.arrow_bottom);
                        item=true;
                        presenter.showSelctTypeData(select,null,
                                AllFragmentPresenter.OrderType.DEBUFF_DESCEND);
                    }
                }else {
                    //递减状态
                    iv22.setImageResource(R.mipmap.arrow_bottom);
                    item=true;
                    presenter.showSelctTypeData(select,null,
                            AllFragmentPresenter.OrderType.DEBUFF_DESCEND);
                }
                continus_item=true;
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

        iv22.setVisibility(View.VISIBLE);
        iv22.setImageResource(R.mipmap.arrow_bottom);
        item=true;

        continus_netvalue=false;
        continus_item=true;
    }
    public void setType(Fund.Type t){
        select=t;
    }

    @Override
    public void iniListView(ArrayList<Fund> funds,ArrayList<Double> netValues
            ,ArrayList<Double> debuffs,int []order) {
        if(null!=getActivity()) {
            adapter = new DataAdapter(getActivity());
            adapter.setFunds(funds,netValues,debuffs,order);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void updateListView(ArrayList<Fund> funds,ArrayList<Double> netValues
            ,ArrayList<Double> debuffs,int []order) {
        if(adapter!=null){
            adapter.setFunds(funds,netValues,debuffs,order);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void showSelectTypeDataListView(ArrayList<Fund> funds,ArrayList<Double> netValues
            ,ArrayList<Double> debuffs,int []order) {
        if(adapter!=null){
            adapter.setFunds(funds,netValues,debuffs,order);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void goTo(String name, String id, String type, boolean AIP, boolean buy) {
        if(null==getActivity()) {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("id", id);
            bundle.putString("type", type);
            bundle.putBoolean("AIP", AIP);
            bundle.putBoolean("buy", buy);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    public final static int ONE=0X111;
    public final static int TWO=0X112;
    public final static int THREE=0X113;
    public final static int FOUR=0X114;

}
