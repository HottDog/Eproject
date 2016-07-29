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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.yuanjc.myapplication1.bean.Fund;
import com.example.yuanjc.myapplication1.util.ViewUtil;
import com.example.yuanjc.myapplication1.view.MainActivity;

/**
 * Created by yuanjc on 2016/7/29.
 */
public class TypeSelectPopWindow extends PopupWindow {
    private View contentView;
    private Activity context;
    private Handler handler;
    //view component
    private ListView listView;
    private String types[]={"全部","债券型","货币型","股票型","QDII","短期理财","指数型","混合型"};
    private TypeDataAdapter adapter;

    //选择的类型，默认是全部
    private Fund.Type select;
    public TypeSelectPopWindow(Activity context, final Handler handler, Fund.Type selectType) {
        this.handler = handler;
        this.context = context;
        this.select=select;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.typeselect_popwindow, null);
        this.setContentView(contentView);
        this.setWidth(ViewUtil.Dp2Px(context, 100));
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
        listView = (ListView) contentView.findViewById(R.id.type_list);
        if (adapter == null) {
            adapter = new TypeDataAdapter(context, types);
        }
        if (selectType == null) {
            selectType = Fund.Type.QUANBU;
        }
        adapter.setSelect(selectType);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Message message = new Message();
                message.what= MainActivity.TYPE;
                Bundle bundle = new Bundle();
                bundle.putInt("type", position);
                message.setData(bundle);
                handler.sendMessage(message);
                dismiss();
            }
        });
    }
    public void setSelect(Fund.Type t){
        adapter.setSelect(t);
        adapter.notifyDataSetChanged();
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

}
