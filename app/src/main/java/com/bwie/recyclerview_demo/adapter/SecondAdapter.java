package com.bwie.recyclerview_demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.recyclerview_demo.R;
import com.bwie.recyclerview_demo.bean.JsonBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 用途：
 * author：王倩凤Administrator
 * date:2017/5/13.
 */

public class SecondAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final List<JsonBean.DataBean> list;
    private final Context context;
    private View mView;

    private static final int ITEM = 0;//条目1
    private static final int ITEM1 = 1;//条目1
    private static final int ITEM2 = 2;//条目1
    private static final int ITEM3 = 3;//条目1


    public SecondAdapter(List<JsonBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType==ITEM){
          view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
           return new SecondHolder(view);
       }else if (viewType==ITEM1){
          view = LayoutInflater.from(context).inflate(R.layout.item2_layout,parent,false);
           return new SecondHolder1(view);
       }else if (viewType==ITEM2){
           view = LayoutInflater.from(context).inflate(R.layout.item3_layout,parent,false);

           return new SecondHolder2(view);
       }else{
            view = LayoutInflater.from(context).inflate(R.layout.item4_layout,parent,false);

           return new SecondHolder3(view);

       }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        if (holder instanceof SecondHolder){
            SecondHolder holders = (SecondHolder) holder;
            holders.goods_name1.setText(list.get(position).getGoods_name());
            Picasso.with(context).load(list.get(position).getGoods_img()).into(holders.image1);
        }else if (holder instanceof SecondHolder1){
            SecondHolder1 holder1 = (SecondHolder1) holder;
            holder1.goods_name2.setText(list.get(position).getGoods_name());
            Picasso.with(context).load(list.get(position).getGoods_img()).into(holder1.image2);

        }else if (holder instanceof SecondHolder2){
            SecondHolder2 holder2 = (SecondHolder2) holder;
            holder2.goods_name3.setText(list.get(position).getGoods_name());
            Picasso.with(context).load(list.get(position).getGoods_img()).into(holder2.image31);
            Picasso.with(context).load(list.get(position).getGoods_img()).into(holder2.image32);
        }else if(holder instanceof SecondHolder3){
            SecondHolder3 holder3 = (SecondHolder3) holder;
            holder3.goods_name4.setText(list.get(position).getGoods_name());
        }
    }

    @Override
    public int getItemViewType(int position) {
        int count = position/4;
        switch (count){
            case 0:
                return ITEM;
            case 1:
                return ITEM1;
            case 2:
                return ITEM2;
            case 3:
                return ITEM3;
        }
        return ITEM3;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SecondHolder extends RecyclerView.ViewHolder{

        private final ImageView image1;
        private final TextView goods_name1;
        private View itemView;

        public SecondHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            image1 = (ImageView) itemView.findViewById(R.id.image1);
            goods_name1 = (TextView) itemView.findViewById(R.id.goods_name1);
        }
    }
    class SecondHolder1 extends RecyclerView.ViewHolder{

        private final ImageView image2;
        private final TextView goods_name2;
        private View itemView;

        public SecondHolder1(View itemView) {
            super(itemView);
            this.itemView = itemView;
            image2 = (ImageView) itemView.findViewById(R.id.image2);
            goods_name2 = (TextView) itemView.findViewById(R.id.goods_name2);
        }
    }
    class SecondHolder2 extends RecyclerView.ViewHolder{

        private final ImageView image31;
        private final ImageView image32;
        private final TextView goods_name3;
        private View itemView;

        public SecondHolder2(View itemView) {
            super(itemView);
            this.itemView = itemView;
            image32 = (ImageView) itemView.findViewById(R.id.image31);
            image31 = (ImageView) itemView.findViewById(R.id.image32);
            goods_name3 = (TextView) itemView.findViewById(R.id.goods_name3);
        }
    }
    class SecondHolder3 extends RecyclerView.ViewHolder{

        private final TextView goods_name4;
        private View itemView;

        public SecondHolder3(View itemView) {
            super(itemView);
            this.itemView = itemView;
            goods_name4 = (TextView) itemView.findViewById(R.id.goods_name4);
        }
    }
}
