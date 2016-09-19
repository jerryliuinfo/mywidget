package com.tcl.widget.demo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author crazychen
 * @version 1.0
 * @date 2015/12/2
 */
public class RadarView extends View {
    private int count = 6;                //数据个数  
    private float angle = (float) (Math.PI*2/count);     
    private float radius;                   //网格最大半径  
    private int centerX;                  //中心X  
    private int centerY;                  //中心Y  
    private String[] titles = {"a","b","c","d","e","f"};
    private double[] data = {100,60,60,60,100,50,10,20}; //各维度分值  
    private float maxValue = 100;             //数据最大值
    private Paint mainPaint;                //雷达区画笔  
    private Paint valuePaint;               //数据区画笔  
    private Paint textPaint;                //文本画笔      
    
    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    
    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    
    public RadarView(Context context) {
        super(context);
        init();
    }
    
    
    //初始化  
    private void init() {
        count = Math.min(data.length,titles.length);
       
        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setColor(Color.GRAY);
        mainPaint.setStyle(Paint.Style.STROKE);

        valuePaint = new Paint();
        valuePaint.setAntiAlias(true);
        valuePaint.setColor(Color.BLUE);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new Paint();
        textPaint.setTextSize(20);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h, w)/2*0.9f;
        centerX = w/2;
        centerY = h/2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(centerX, centerY);
        drawPolygon_v2(canvas);
        drawLines(canvas);
        drawText(canvas);
        /*
        drawRegion(canvas);*/
    }

    /**
     * 绘制正多边形
     */
    private void drawPolygon(Canvas canvas){
        Path path = new Path();
        //每个六边形之间的距离为r
        float r = radius / (count-1);
        for(int i= 1;i < count;i++){
            float curR = r * i;
            //清除Path中的内容
            path.reset();
            for(int j = 0;j < count; j++){
                if(j==0){
                    path.moveTo(centerX+ curR, centerY);
                }else{
                    float x = (float) (centerX + curR * Math.cos(angle*j));
                    float y = (float) (centerY + curR * Math.sin(angle*j));
                    path.lineTo(x,y);
                }
            }
            //将第一个点和最后一个点连接起来
            //path.close();
            canvas.drawPath(path, mainPaint);
        }
    }


    /**
     * 绘制正多边形
     */
    private void drawPolygon_v2(Canvas canvas){
        Path path = new Path();
        //每个六边形之间的距离为r
        float r = radius / (count-1);
        for(int i= 1;i < count;i++){
            //当前六边形的边长
            float currentR = r * i;
            //清除Path中的内容
            path.reset();
            for(int j = 0;j < count; j++){
                //设置每个六边形的起始点的位置
                if(j==0){
                    path.moveTo(currentR, 0);
                }else{
                    float x = (float) (currentR * Math.cos(angle*j));
                    float y = (float) (currentR * Math.sin(angle*j));
                    path.lineTo(x,y);
                }
            }
            //将第一个点和最后一个点连接起来
            path.close();
            canvas.drawPath(path, mainPaint);
        }
    }
    


    private void drawLines(Canvas canvas){
        Path path = new Path();
        for (int i = 0; i < count; i++){
            path.reset();
            float x = (float) (radius * Math.cos(angle * i));
            float y = (float) (radius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mainPaint);
        }
    }

    private void drawText(Canvas canvas){
        for (int i = 0; i < count; i++){
            float x = (float) (radius * Math.cos(angle * i));
            float y = (float) (radius * Math.sin(angle * i));
            canvas.drawText(titles[i], x, y , textPaint);
        }
    }


}  