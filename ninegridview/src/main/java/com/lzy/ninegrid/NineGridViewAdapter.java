package com.lzy.ninegrid;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

public abstract class NineGridViewAdapter<T> {

    protected Context context;
    private List<T> imageInfo;

    public NineGridViewAdapter(Context context, List<T> imageInfo) {
        this.context = context;
        this.imageInfo = imageInfo;
    }

    /**
     * 绑定更新视图
     * @param imageView   当前点击图片的控件
     * @param position    当前点击图片的的索引
     * @param imageInfo   当前的图片数据集
     */
    protected void onImageItemBind(ImageView imageView, int position, List<T> imageInfo) {
    }

    /**
     * 如果要实现图片点击的逻辑，重写此方法即可
     *
     * @param imageView    当前点击图片的控件
     * @param position     当前点击图片的的索引
     * @param imageInfo   当前的图片数据集
     */
    protected void onImageItemClick(ImageView imageView, int position, List<T> imageInfo) {
    }

    /**
     * 生成ImageView容器的方式，默认使用NineGridImageViewWrapper类，即点击图片后，图片会有蒙板效果
     * 如果需要自定义图片展示效果，重写此方法即可
     *
     * @param context 上下文
     * @return 生成的 ImageView
     */
    protected ImageView generateImageView(Context context) {
        NineGridViewWrapper imageView = new NineGridViewWrapper(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.drawable.ic_default_color);
        return imageView;
    }

    public List<T> getImageInfo() {
        return imageInfo;
    }

    public void setImageInfoList(List<T> imageInfo) {
        this.imageInfo = imageInfo;
    }
}