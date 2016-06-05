package com.cxyliuyu.www.cookingdiy_android.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by ly on 2016/6/4.
 */
public class BitmapCache implements ImageLoader.ImageCache{
    public LruCache<String,Bitmap>cache;
    public int max = 10 * 1024 * 1024;

    public BitmapCache(){
        cache = new LruCache<String,Bitmap>(max){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return cache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        cache.put(url,bitmap);
    }
    //图片缓存类
}
