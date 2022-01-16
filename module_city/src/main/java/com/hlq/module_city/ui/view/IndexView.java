/*
 *
 *  * Copyright (C)  HuangLinqing, TravelPrevention Open Source Project
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.hlq.module_city.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.hlq.module_city.R;


/**
 * @author huanglinqing Copy From by Others
 * @desc 字母索引自定义view
 * * 1.把26个字母放入数组
 * * 2.在onMeasure计算每条的高itemHeight和宽itemWidth,
 * * 3.在onDraw和wordWidth,wordHeight,wordX,wordY
 * *
 * * 手指按下文字变色
 * * 1.重写onTouchEvent(),返回true,在down/move的过程中计算
 * * int touchIndex = Y / itemHeight; 强制绘制
 * *
 * * 2.在onDraw()方法对于的下标设置画笔变色
 * *
 * * 3.在up的时候
 * * touchIndex  = -1； //还原默认
 * * 强制绘制
 */
public class IndexView extends View {
    /**
     * 每条的宽和高
     */
    private int itemWidth;
    private int itemHeight;

    private Paint paint;  //画笔

    private String[] words = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};

    /**
     * 如果我们在布局文件使用该类，将会用这个构造方法实例该类，如果没有就会崩溃
     *
     * @param context
     * @param attrs
     */
    public IndexView(Context context, @Nullable AttributeSet attrs) {  //要实现带有两个参数值的构造方法
        super(context, attrs);
        paint = new Paint();
        paint.setTextSize(40);  //字母字体大小
        paint.setColor(Color.BLACK); //默认设置画笔是白色
        paint.setAntiAlias(true); //设置抗锯齿
    }

    /**
     * 测量方法
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemWidth = getMeasuredWidth();
        itemHeight = getMeasuredHeight() / words.length;  //words.length是26。除以26
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < words.length; i++) {
            if (touchIndex == i) {
                //设置为灰色(按下去的时候)
                paint.setColor(Color.GRAY);
            } else {  //没有-1走下面代码
                //设置为白色(松开)
                paint.setColor(getResources().getColor(R.color.black));
            }

            String word = words[i];//若取值是A

            Rect rect = new Rect();  //矩形
            //画笔
            paint.getTextBounds(word, 0, 1, rect);  // 0, 1指的是取一个字母

            //字母的高和宽
            int wordWidth = rect.width();
            int wordHeight = rect.height();

            //计算每个字母在视图上的坐标位置
            float wordX = itemWidth / 2 - wordWidth / 2;
            float wordY = itemHeight / 2 + wordHeight / 2 + i * itemHeight;  ///i * itemHeight是指往下绘制字母

            canvas.drawText(word, wordX, wordY, paint);
        }
    }

    private int touchIndex = -1; //默认字母的下标位置

    /**
     * 手指按下文字变色
     * 1.重写onTouchEvent(),返回true,在down/move的过程中计算
     * int touchIndex = Y / itemHeight; 强制绘制
     * <p>
     * 2.在onDraw()方法对于的下标设置画笔变色
     * <p>
     * 3.在up的时候
     * touchIndex  = -1； //还原默认
     * 强制绘制
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float Y = event.getY();
                int index = (int) (Y / itemHeight); //字母索引
                if (index != touchIndex) {
                    touchIndex = index;  //当前的索引位置
                    invalidate(); //强制绘制会导致OnDraw()方法执行
                    if (onIndexChangeListener != null && touchIndex < words.length && touchIndex >= 0) {
                        onIndexChangeListener.onIndexChange(words[touchIndex]);
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                touchIndex = -1;  //还原
                invalidate();
                if (onIndexChangeListener != null) {
                    onIndexChangeListener.uplift();
                }
                break;
        }
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("down下拉", getParent().getParent().getClass().getName());
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 字母下标索引变化的监听器（做接口）
     */
    public interface OnIndexChangeListener {

        /**
         * 当字母下标位置发生变化的时候回调
         *
         * @param word 字母（A~Z）
         */
        void onIndexChange(String word); //没有返回值

        /**
         * 抬起
         */
        void uplift();

    }

    private OnIndexChangeListener onIndexChangeListener;

    /**
     * Setter方法
     * 设置字母下标索引变化的监听
     *
     * @param onIndexChangeListener
     */
    public void setOnIndexChangeListener(OnIndexChangeListener onIndexChangeListener) {
        this.onIndexChangeListener = onIndexChangeListener;
    }
}
