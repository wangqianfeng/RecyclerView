package com.bwie.recyclerview_demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.recyclerview_demo.R;

/**
 * 用途：
 * author：王倩凤Administrator
 * date:2017/5/11.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {
    private OnItemClickListener mOnItemClickListener = null;//声明这个接口的变量
    public String[] datas = null;
    public MyAdapter(String[] datas) {
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        //2、将创建的View注册点击事件
        view.setOnClickListener(this);

        return holder;
    }

    //调用接口的onItemClick()中的v.getTag()方法，这需要在onBindViewHolder()方法中设置和item的position
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(datas[position]);

        //4、将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

    @Override //3、将点击事件转移给外面的调用者：
    public void onClick(View view) {
        if (mOnItemClickListener != null){
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        private final CheckBox mCheckBox;

        public ViewHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
            mCheckBox = (CheckBox) view.findViewById(R.id.checkbox);
        }
    }
    //1、定义一个接口
    public interface OnItemClickListener{
        public void onItemClick(View view,int postion);
    }

    //5、暴露给外面的调用者，定义一个设置Listener的方法（）：
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;

    }


}
