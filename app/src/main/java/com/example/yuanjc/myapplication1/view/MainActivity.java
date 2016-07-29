package com.example.yuanjc.myapplication1.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuanjc.myapplication1.R;
import com.example.yuanjc.myapplication1.TypeSelectPopWindow;
import com.example.yuanjc.myapplication1.bean.Fund;

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

    private ImageView back;
    private ImageView sousuo;
    private ImageView suosou_hot;

    private TextView topic;
    private TextView type;
    //fragment
    HotFragment hotFragment;
    AllFragment allFragment;
    YourselfFragment yourselfFragment;
    Fragment mContent;
    FragmentManager fm;
    //选择的类型
    private Fund.Type select= Fund.Type.QUANBU;
    //popWIndows
    private TypeSelectPopWindow typeSelectPopWindow;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case TYPE:
                    Bundle bundle=msg.getData();
                    int value=bundle.getInt("type");
                    select= Fund.Type.getType(value);
                    Log.i("value",Fund.Type.getType(value).toString());
                    if(allFragment!=null){
                        Log.i("allFragment","不是空的");
                        allFragment.showSelectTypeView(select);
                        topic.setText(Fund.Type.getType(value).toString());
                    }
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intView(savedInstanceState);
    }

    private void intView(Bundle savedInstanceState){
        hot=(RelativeLayout)findViewById(R.id.hot);
        all=(RelativeLayout)findViewById(R.id.all);
        yourself=(RelativeLayout)findViewById(R.id.yourself);

        hot_tv=(TextView)findViewById(R.id.hot_tv);
        all_tv=(TextView)findViewById(R.id.all_tv);
        yourself_tv=(TextView)findViewById(R.id.yourself_tv);

        hot_select=(LinearLayout)findViewById(R.id.hot_select);
        all_select=(LinearLayout)findViewById(R.id.all_select);
        yourself_select=(LinearLayout)findViewById(R.id.yourself_select);

        back=(ImageView)findViewById(R.id.back);
        suosou_hot=(ImageView)findViewById(R.id.sousuo_hot);
        sousuo=(ImageView)findViewById(R.id.sousuo);

        topic=(TextView)findViewById(R.id.topic_tv);
        type=(TextView)findViewById(R.id.type_tv);
        //setting click event
        hot.setOnClickListener(this);
        all.setOnClickListener(this);
        yourself.setOnClickListener(this);

        //initial the view setting
        all_tv.setVisibility(View.GONE);
        hot_select.setVisibility(View.GONE);
        yourself_select.setVisibility(View.GONE);
        back.setOnClickListener(this);
        suosou_hot.setOnClickListener(this);
        sousuo.setOnClickListener(this);
        type.setOnClickListener(this);
        //initial the fragment
        fm=getFragmentManager();

        if (savedInstanceState == null) {
            defaultSet();
            if(null==allFragment){
                allFragment=new AllFragment();
                allFragment.setType(select);
            }
            fm.beginTransaction().add(R.id.content,
                    allFragment).commit();
            mContent=allFragment;
        }
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
            case R.id.back:
                finish();
                break;
            case R.id.sousuo_hot:
                break;
            case R.id.sousuo:
                if(mContent!=null&&mContent==allFragment){
                    Intent intent=new Intent(MainActivity.this,SousuoActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.type_tv:
                showTypeSelect();
                break;
            default:
                break;
        }
    }
    private void defaultSet(){
        suosou_hot.setVisibility(View.GONE);
    }
    /**
     * 控制页面切换
     * @param index
     */
    private void selectChange(int index){
        clearSelect();
        switch (index){
            case 1:
                topic.setText("热销");
                sousuo.setVisibility(View.GONE);
                type.setVisibility(View.GONE);

                hot_tv.setVisibility(View.GONE);
                all_select.setVisibility(View.GONE);
                yourself_select.setVisibility(View.GONE);
                if(null==hotFragment){
                    hotFragment=new HotFragment();
                }
                switchContent(mContent,hotFragment);
                break;
            case 2:
                topic.setText(select.toString());
                type.setText("类型");
                suosou_hot.setVisibility(View.GONE);

                all_tv.setVisibility(View.GONE);
                hot_select.setVisibility(View.GONE);
                yourself_select.setVisibility(View.GONE);
                if(null==allFragment){
                    allFragment=new AllFragment();
                    allFragment.setType(select);
                }
                switchContent(mContent,allFragment);
                break;
            case 3:
                topic.setText("自选");
                suosou_hot.setVisibility(View.GONE);
                type.setText("编辑");

                yourself_tv.setVisibility(View.GONE);
                all_select.setVisibility(View.GONE);
                hot_select.setVisibility(View.GONE);
                if(null==yourselfFragment){
                    yourselfFragment=new YourselfFragment();
                }
                switchContent(mContent,yourselfFragment);
                break;
            default:
                break;
        }
    }
    private void showTypeSelect(){
        if(mContent==allFragment&&mContent!=null){
            if(null==typeSelectPopWindow) {
                typeSelectPopWindow = new TypeSelectPopWindow(this, handler, select);
            }
            typeSelectPopWindow.setSelect(select);
            typeSelectPopWindow.showPopupWindow(type);
        }
    }
    private void clearSelect(){
        yourself_tv.setVisibility(View.VISIBLE);
        all_tv.setVisibility(View.VISIBLE);
        hot_tv.setVisibility(View.VISIBLE);
        all_select.setVisibility(View.VISIBLE);
        hot_select.setVisibility(View.VISIBLE);
        yourself_select.setVisibility(View.VISIBLE);

        sousuo.setVisibility(View.VISIBLE);
        suosou_hot.setVisibility(View.VISIBLE);
        type.setVisibility(View.VISIBLE);
    }

    /**
     * Fragment切换
     * @param from
     * @param to
     */
    private void switchContent(Fragment from, Fragment to) {
        if (mContent != to) {
            mContent = to;
            if(fm==null){
                fm=getFragmentManager();
            }
            FragmentTransaction transaction = fm.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.content, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }
    public static final int TYPE=0X212;

}
