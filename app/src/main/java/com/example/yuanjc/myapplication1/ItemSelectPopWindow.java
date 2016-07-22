package com.example.yuanjc.myapplication1;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

/**
 * Created by yuanjc on 2016/7/22.
 */
public class ItemSelectPopWindow extends PopupWindow implements View.OnClickListener{
    private View contentView;
    private Activity context;
    private Handler handler;
    //view component
    private RelativeLayout re11;
    private RelativeLayout re12;
    private RelativeLayout re21;
    private RelativeLayout re22;
    public ItemSelectPopWindow(Activity context,Handler handler){
        this.handler=handler;
        this.context=context;
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView=inflater.inflate(R.layout.itemselect_popwindow,null);
        this.setContentView(contentView);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
//        ColorDrawable dw=new ColorDrawable(0000000000);
//        this.setBackgroundDrawable(dw);
//        this.setAnimationStyle(R.style.AnimationPreview);
        re11=(RelativeLayout)contentView.findViewById(R.id.re11);
        re12=(RelativeLayout)contentView.findViewById(R.id.re12);
        re21=(RelativeLayout)contentView.findViewById(R.id.re21);
        re22=(RelativeLayout)contentView.findViewById(R.id.re22);

        re11.setOnClickListener(this);
        re12.setOnClickListener(this);
        re21.setOnClickListener(this);
        re22.setOnClickListener(this);
    }
    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
//            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.re11:
                handler.sendEmptyMessage(AllFragment.ONE);
                break;
            case R.id.re12:
                handler.sendEmptyMessage(AllFragment.TWO);
                break;
            case R.id.re21:
                handler.sendEmptyMessage(AllFragment.THREE);
                break;
            case R.id.re22:
                handler.sendEmptyMessage(AllFragment.FOUR);
                break;
            default:
                break;
        }
    }
}
