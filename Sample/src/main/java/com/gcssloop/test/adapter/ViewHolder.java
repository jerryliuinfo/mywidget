/*
 * Copyright (c) 2016 GcsSloop
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * GitHub :  https://github.com/GcsSloop
 * Weibo  :  http://weibo,com/GcsSloop
 * Blog     :  http://www.gcssloop.com
 *
 * Last modify $2016-09-15 03:31:26
 */

package com.gcssloop.test.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewHolder {

    private SparseArray<View> mViews;
    private View mConvertView;

    private ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);  //setTag
    }

    /**
     * 获取ViewHolder的实例
     *
     * @param context     上下文
     * @param convertView 布局
     * @param parent      父布局
     * @param layoutId    布局ID
     * @param position    位置
     * @return ViewHolder实例
     */
    public static ViewHolder getInstance(Context context, View convertView, ViewGroup parent, int layoutId, int
            position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            return (ViewHolder) convertView.getTag();
        }
    }

    /**
     * 通过View的id来获取子View
     *
     * @param resId view的id
     * @param <T>   泛型
     * @return 子View
     */
    public <T extends View> T getView(int resId) {
        View view = mViews.get(resId);

        //如果该View没有缓存过，则查找View并缓存
        if (view == null) {
            view = mConvertView.findViewById(resId);
            mViews.put(resId, view);
        }

        return (T) view;
    }

    /**
     * 获取布局View
     *
     * @return 布局View
     */
    public View getConvertView() {
        return mConvertView;
    }
}