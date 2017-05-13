package com.bwie.recyclerview_demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.recyclerview_demo.bean.JsonBean;
import com.bwie.recyclerview_demo.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用途：
 * author：王倩凤Administrator
 * date:2017/5/12.
 */

public class MyCheckBoxAdapter extends RecyclerView.Adapter<MyCheckBoxAdapter.MyHolders> implements View.OnClickListener, View.OnLongClickListener {

    private final Context context;
    private final List<JsonBean.DataBean> list;
    private boolean flag = false;
    private OnItemClickListener mOnItemClickListener;//接口
    private Map<Integer,Boolean> mMap = new HashMap<>();//存储checkbox勾选框状态的集合
    private MyHolders mHolders;
    private Animation mAnimation;

    public MyCheckBoxAdapter(List<JsonBean.DataBean> list, Context context) {
        this.context = context;
        this.list = list;
        setMap();//初始化checkBox的状态
    }

    @Override
    public MyHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        mHolders = new MyHolders(view);

        view.setOnClickListener(this);//设置点击事件
        view.setOnLongClickListener(this);//设置点击事件

        return mHolders;
    }

    @Override
    public void onBindViewHolder(MyHolders holder, final int position) {
        holder.mTextView.setText(list.get(position).getGoods_name());
        Picasso.with(context)
                .load(list.get(position).getGoods_img())
                .into(holder.mImageView);

        //长按的时候
        if (flag){
            mHolders.mCheckBox.setVisibility(View.VISIBLE);
        }else {
            mHolders.mCheckBox.setVisibility(View.INVISIBLE);
        }
        //设置checkBox的动画
        mAnimation = AnimationUtils.loadAnimation(context, R.anim.check_anim);
        //设置显示动画
        if (flag)
            mHolders.mCheckBox.startAnimation(mAnimation);//

            mHolders.itemView.setTag(position);//设置tag
            //设置CheckBox的监听事件
            mHolders.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    mMap.put(position,b);//使用map保存当前CheckBox的状态
                }
            });
            //设置CheckBox的状态
            if (mMap.get(position)== null){
                mMap.put(position,false);
            }
            mHolders.mCheckBox.setChecked(mMap.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolders extends RecyclerView.ViewHolder{
        private View itemView;

        private final TextView mTextView;
        private final CheckBox mCheckBox;
        private final ImageView mImageView;

        public MyHolders(View itemView) {
            super(itemView);
            this.itemView = itemView;
            mImageView = (ImageView) itemView.findViewById(R.id.image);
            mTextView = (TextView) itemView.findViewById(R.id.goods_name);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }

    public void setMap(){
        for (int i = 0; i <list.size() ; i++) {
            mMap.put(i,false);//初始化第一次全部设置为不显示
        }
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener!= null){
            mOnItemClickListener.setItemClickListener(view,(Integer)view.getTag());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        setMap();//长按的时候清空状态，不管选中与不选中
        return mOnItemClickListener != null &&mOnItemClickListener.setItemLongClickListener(view,(int)view.getTag());
    }

    //定义CheckBox的监听接口
    public interface OnItemClickListener{
        void setItemClickListener(View view,int position);//点击监听

        boolean setItemLongClickListener(View view,int position);//长按监听
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    //设置是否显示CheckBox
    public void setShowCheck(){
        flag = !flag;
    }
    //设置checkBox选中
    public void setCheckBoxSelect(int position){
        if (mMap.get(position)){
            mMap.put(position,false);//把当前点击的checkbox的状态设置为相反的
        }else {
            mMap.put(position,true);
        }
        notifyItemChanged(position);//更新指定位置的Item
    }

    //返回map集合
    public Map<Integer,Boolean> getMap(){
        return  mMap;
    }

}
