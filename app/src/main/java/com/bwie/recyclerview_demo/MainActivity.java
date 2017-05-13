package com.bwie.recyclerview_demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bwie.recyclerview_demo.adapter.MyCheckBoxAdapter;
import com.bwie.recyclerview_demo.bean.JsonBean;
import com.bwie.recyclerview_demo.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private TextView all;
    private TextView NoAll;
    private MyCheckBoxAdapter mAdapter;
    private Button btnOk;
    private ArrayList<JsonBean.DataBean> mData ;
    private ArrayList<JsonBean.DataBean> list = new ArrayList<>();

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String s = (String) msg.obj;
                mData = new Gson().fromJson(s, JsonBean.class).getData();
                setRecyclerView();
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getDummyDatas();
    }

    public void getDummyDatas() {

        String uri = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17";

        OkHttpUtils.get(uri, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.d("XXX", string);
                Message message = new Message();
                message.what = 0;
                message.obj = string;
                mHandler.sendMessage(message);
            }
        });

    }


    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        all = (TextView) findViewById(R.id.all);

        NoAll = (TextView) findViewById(R.id.NoAll);


        //设置为线性布局方式
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);

        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);
    }

    private void setRecyclerView() {
        //创建并设置Adapter
        mAdapter = new MyCheckBoxAdapter(mData, MainActivity.this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MyCheckBoxAdapter.OnItemClickListener() {
            @Override
            public void setItemClickListener(View view, int position) {
                mAdapter.setCheckBoxSelect(position);//点击设置选中的项
            }

            @Override
            public boolean setItemLongClickListener(View view, int position) {
                mAdapter.setShowCheck();//长按事件显示CheckBox
                mAdapter.setCheckBoxSelect(position);//设置选中的项
                mAdapter.notifyDataSetChanged();//更新所有可见的Item

                return true;
            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<Integer, Boolean> map = mAdapter.getMap();
                for (int i = 0; i < map.size(); i++) {
                    map.put(i, true);//全选中
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
        NoAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<Integer, Boolean> map1 = mAdapter.getMap();
                for (int i = 0; i < map1.size(); i++) {
                    map1.put(i, false);//设置为全不选
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        Map<Integer, Boolean> m = mAdapter.getMap();
        for (int i = 0; i < m.size(); i++) {
            list.add(mData.get(i));
        }

        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        intent.putExtra("data",list);
        startActivity(intent);
    }
}

