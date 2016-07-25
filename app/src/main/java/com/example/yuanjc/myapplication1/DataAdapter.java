package com.example.yuanjc.myapplication1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuanjc.myapplication1.bean.Fund;

import java.util.ArrayList;

/**
 * Created by yuanjc on 2016/7/22.
 */
public class DataAdapter extends BaseAdapter {
    private LayoutInflater mInflate;
    private Context context;
    private ArrayList<Fund> funds;
    public DataAdapter(Context context) {
        // TODO Auto-generated constructor stub
        this.context=context;
        this.mInflate=LayoutInflater.from(context);
        funds=new ArrayList<Fund>();
    }

    public void setFunds(ArrayList<Fund> funds) {
        this.funds = funds;
    }

    @Override
    public int getCount() {
        return funds.size();
    }

    @Override
    public Object getItem(int position) {
        return funds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
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
        holder.tv11.setText(funds.get(position).getName());
        holder.tv12.setText(funds.get(position).getNetValue());
        holder.tv3.setText(funds.get(position).getState());
        holder.tv21.setText(funds.get(position).getId());
        holder.tv22.setText(funds.get(position).getTime());
        if(funds.get(position).isLike()){
            holder.star.setImageResource(R.mipmap.star_select);
        }else{
            holder.star.setImageResource(R.mipmap.star);
        }
        holder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(funds.get(position).isLike()){
                    holder.star.setImageResource(R.mipmap.star);
                    funds.get(position).setLike(false);
                }else {
                    holder.star.setImageResource(R.mipmap.star_select);
                    funds.get(position).setLike(true);
                }
            }
        });
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
