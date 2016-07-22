package com.example.yuanjc.myapplication1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yuanjc on 2016/7/22.
 */
public class DataAdapter extends BaseAdapter {
    private LayoutInflater mInflate;
    private Context context;
    public DataAdapter(Context context) {
        // TODO Auto-generated constructor stub
        this.context=context;
        this.mInflate=LayoutInflater.from(context);
//		SetOptions();
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            convertView=mInflate.inflate(R.layout.data_item, null);
            holder=new ViewHolder();
            holder.tv11=(TextView)convertView.findViewById(R.id.tv11);
            holder.tv12=(TextView)convertView.findViewById(R.id.tv12);
            holder.tv21=(TextView)convertView.findViewById(R.id.tv21);
            holder.tv22=(TextView)convertView.findViewById(R.id.tv22);
            holder.tv3=(TextView)convertView.findViewById(R.id.tv3);
            holder.star=(ImageView)convertView.findViewById(R.id.star);
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
        }

        return convertView;
    }
    public final class ViewHolder {
        public ImageView star;
        public TextView tv11;
        public TextView tv21;
        public TextView tv12;
        public TextView tv22;
        public TextView tv3;


    }
}
