package com.example.yuanjc.myapplication1.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuanjc.myapplication1.R;

public class DetailActivity extends Activity {
    private RelativeLayout buy_re;
    private RelativeLayout API_re;
    private TextView name_tv;
    private TextView id_tv;
    private TextView type_tv;

    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        iniView();
        iniData();
    }
    private void iniData(){
        Intent myIntent=getIntent();
        final Bundle myBundle=myIntent.getExtras();
        name_tv.setText(myBundle.getString("name"));
        id_tv.setText(myBundle.getString("id"));
        type_tv.setText(myBundle.getString("type"));
        if(myBundle.getBoolean("AIP")){
            API_re.setBackgroundResource(R.color.red);
        }else {
            API_re.setBackgroundResource(R.color.lightred);
        }
        if(myBundle.getBoolean("buy")){
            buy_re.setBackgroundResource(R.color.blue);
        }else {
            buy_re.setBackgroundResource(R.color.lightblue);
        }
        API_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myBundle.getBoolean("AIP")){
                    Toast.makeText(DetailActivity.this,"你点击了“定投”按钮",Toast.LENGTH_SHORT).show();
                }else {

                }
            }
        });
        buy_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myBundle.getBoolean("buy")){
                    Toast.makeText(DetailActivity.this,"你点击了“立即购买”按钮",Toast.LENGTH_SHORT).show();
                }else {

                }
            }
        });
    }
    private void iniView(){
        buy_re=(RelativeLayout)findViewById(R.id.buy);
        API_re=(RelativeLayout)findViewById(R.id.API);
        name_tv=(TextView)findViewById(R.id.name);
        id_tv=(TextView)findViewById(R.id.id);
        type_tv=(TextView)findViewById(R.id.type);
        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
