package com.bwie.recyclerview_demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bwie.recyclerview_demo.adapter.SecondAdapter;
import com.bwie.recyclerview_demo.bean.JsonBean;

import java.util.ArrayList;


public class SecondActivity extends Activity {


    private RecyclerView mRecyclerView2;
    private ArrayList<JsonBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        mList = (ArrayList<JsonBean.DataBean>) getIntent().getSerializableExtra("data");
        Log.e("--->",mList.size()+"");
        setAdapter();
    }

    private void setAdapter() {
        SecondAdapter adapter = new SecondAdapter(mList, this);
        mRecyclerView2.setAdapter(adapter);

    }

    private void initView() {
        mRecyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView2.setHasFixedSize(true);
    }
}
