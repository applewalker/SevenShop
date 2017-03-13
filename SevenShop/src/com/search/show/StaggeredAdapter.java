package com.search.show;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Seven.Shop.R;
import com.example.android.bitmapfun.util.ImageFetcher;
import com.shop.entity.Goods;

public class StaggeredAdapter extends BaseAdapter {
    private LinkedList<Goods> mInfos;
    ImageFetcher mImageFetcher;

    public StaggeredAdapter(Context context, ImageFetcher f) {
        mInfos = new LinkedList<Goods>();
        mImageFetcher = f;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        Goods goodsInfo = mInfos.get(position);

        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(parent.getContext());
            convertView = layoutInflator.inflate(R.layout.infos_list, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.news_pic);
            holder.contentView = (TextView) convertView.findViewById(R.id.news_title);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.albid = (TextView) convertView.findViewById(R.id.albid);
            
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        int max=550;
        int min=400;
        Random random = new Random();
        int height = random.nextInt(max)%(max-min+1) + min;

        holder.imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
        		                             height/*(int) goodsInfo.getHeight()*/));

        holder.contentView.setText(goodsInfo.getGoods_name());
        mImageFetcher.loadImage(goodsInfo.getImage_path(), holder.imageView);
        holder.price.setText("￥"+goodsInfo.getMainprice());
        holder.albid.setText(goodsInfo.getGoods_id()+"");    //获取ID
        
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView contentView;
        TextView price;
        TextView timeView;
        TextView albid;      //商品ID
    }

    @Override
    public int getCount() {
        return mInfos.size();
    }

    @Override
    public Object getItem(int arg0) {
        return mInfos.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    public void addItemLast(List<Goods> datas) {
        mInfos.addAll(datas);
    }

    public void addItemTop(List<Goods> datas) {
        for (Goods info : datas) {
            mInfos.addFirst(info);
        }
    }
}
