package com.bwie.recyclerview_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private TextView all;
    private TextView NoAll;
    private MyCheckBoxAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public String[] getDummyDatas() {
        String[] datas = new String[50];
        for (int i = 0; i < 50; i++) {
            datas[i] = "HelloWorld!   " + i;
        }
        return datas;
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        all = (TextView) findViewById(R.id.all);

        NoAll = (TextView) findViewById(R.id.NoAll);


        //设置为线性布局方式
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);


        //创建并设置Adapter
        mAdapter = new MyCheckBoxAdapter(getDummyDatas(), MainActivity.this);
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
                    map.put(i,true);//全选中
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
        NoAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<Integer, Boolean> map1 = mAdapter.getMap();
                for (int i = 0; i < map1.size(); i++) {
                    map1.put(i,false);//设置为全不选
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }
}

