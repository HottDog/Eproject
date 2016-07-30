package com.example.yuanjc.myapplication1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuanjc.myapplication1.R;
import com.example.yuanjc.myapplication1.bean.Fund;

/**
 * Created by yuanjc on 2016/7/29.
 */
public class TypeDataAdapter extends BaseAdapter{
    private LayoutInflater mInflate;
    private Context context;
    private String []s;
    private Fund.Type select;
    public TypeDataAdapter(Context context,String []s){
        this.context=context;
        this.s=s;
        this.mInflate= LayoutInflater.from(context);
    }

    public void setSelect(Fund.Type select) {
        this.select = select;
    }

    @Override
    public int getCount() {
        return s.length;
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
        final ViewHolder holder;
        if(convertView==null)
        {
            convertView=mInflate.inflate(R.layout.type_item, null);
            holder=new ViewHolder();
            holder.name=(TextView)convertView.findViewById(R.id.name);
            holder.gou=(ImageView)convertView.findViewById(R.id.gou);
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.name.setText(s[position]);
        holder.gou.setVisibility(View.VISIBLE);
        if(position!=select.value()){
            holder.gou.setVisibility(View.GONE);
        }
        return convertView;
    }
    public final class ViewHolder {
        public ImageView gou;
        public TextView name;
    }
}
