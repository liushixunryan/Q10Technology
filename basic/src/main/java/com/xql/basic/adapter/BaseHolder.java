package com.xql.basic.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xql.basic.application.BaseApplication;

public class BaseHolder extends RecyclerView.ViewHolder {
    View itemView;
    SparseArray<View> views;//存放itemview中的子view

    public BaseHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        views = new SparseArray<>();
    }

    //供adapter使用,返回holder
    public static <T extends BaseHolder> T getHolder(Context context, ViewGroup parent, int layoutId) {
        return (T) new BaseHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    //获取view
    public <T extends View> T getView(int id) {
        View view = views.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            views.put(id, view);
        }
        return (T) view;
    }

    public View getItemView() {
        return itemView;
    }

    //设置点击事件监听
    public BaseHolder setOnclickListioner(int viewId, View.OnClickListener onClickListener) {
        getView(viewId).setOnClickListener(onClickListener);
        return this;
    }

    public BaseHolder setText(int viewId, String descrp) {
        ((TextView) getView(viewId)).setText(descrp);
        return this;
    }

    public BaseHolder setText(int viewId, int resId) {
        ((TextView) getView(viewId)).setText(resId);
        return this;
    }

    public BaseHolder setImageView(int imagViewId, String url) {
        Glide.with(BaseApplication.getContext()).load(url).into((ImageView) getView(imagViewId));
        return this;
    }
    //    public BaseHolder setImageView(int imagViewId, String url) {
    //        GlideApp.with(AIApplication.mContext).load(url).error(R.drawable.ic_default).placeholder(R.drawable.ic_default)
    //                .transform(new GlideRoundTransform()).into((ImageView) getView(imagViewId));
    //        return this;
    //    }

    public BaseHolder setImageView(int imagViewId, int resId) {
        ((ImageView) getView(imagViewId)).setImageResource(resId);
        return this;
    }

    public BaseHolder setImageView(int imagViewId, Bitmap bitmap) {
        ((ImageView) getView(imagViewId)).setImageBitmap(bitmap);
        return this;
    }

}