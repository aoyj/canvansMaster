package com.aoyj.learn.canvas_master.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by aoyuanjie on 2018/7/23.
 */

public class MyTextView extends View {
    private Paint mPaint;
    private String txt = "天下霸道之剑";
    private String decTxt = "基准点";
    int height;
    int width;
    int centerY;
    int centerX;

    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        //初始化画笔
        initPaint();
    }

    private void initPaint(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(50f);
        //测量文字的宽度
       // mPaint.measureText(txt);
    }

    /**
     * 绘制图片时需要确定BaseLine的位置，{@link Tools#getBaseLine(Paint)}
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        height = getMeasuredHeight();
        width = getMeasuredWidth();
        centerY = height /2;
        centerX = width /2;
        int baseLineY = centerY + (int)Tools.getBaseLine(mPaint);
        int baseLineX = centerX - (int)mPaint.measureText(txt) /2;
        drawTxt(canvas,baseLineX,baseLineY);
        drawCenterLine(canvas,baseLineX,baseLineY);
        drawBaseLineCircle(canvas,baseLineX,baseLineY);
        drawArrow(canvas,baseLineX,baseLineY);
    }

    private void drawTxt(Canvas canvas,int baseLineX,int baseLineY){
        mPaint.setColor(Color.BLUE);
        canvas.drawText(txt,baseLineX,baseLineY,mPaint);
    }

    private void drawCenterLine(Canvas canvas,int baseLineX,int baseLineY){
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(2);
        canvas.drawLine(0f,centerY,width,centerY,mPaint);
       // canvas.drawLine(baseLineX,0f,baseLineX,height,mPaint);
    }

    private void drawBaseLineCircle(Canvas canvas,int baseLineX,int baseLineY){
        float radius = 5;
        canvas.drawCircle(baseLineX,baseLineY,radius,mPaint);
    }

    private void drawArrow(Canvas canvas,int baseLineX,int baseLineY){
        int arrowOffset = 100;
        int smallLineOffset = 15;
        PointF arrowEnd = new PointF();
        arrowEnd.x = baseLineX + arrowOffset;
        arrowEnd.y = baseLineY + arrowOffset;
        canvas.drawLine(baseLineX,baseLineY,arrowEnd.x,arrowEnd.y,mPaint);

        PointF arrowRightLine = new PointF();
        arrowRightLine.x = arrowEnd.x;
        arrowRightLine.y = arrowEnd.y - smallLineOffset;
        canvas.drawLine(arrowEnd.x,arrowEnd.y,arrowRightLine.x,arrowRightLine.y,mPaint);

        PointF arrowLeftLine = new PointF();
        arrowLeftLine.x = arrowEnd.x - smallLineOffset;
        arrowLeftLine.y = arrowEnd.y;
        canvas.drawLine(arrowEnd.x,arrowEnd.y,arrowLeftLine.x,arrowLeftLine.y,mPaint);

        drawDec(canvas,arrowEnd.x,arrowEnd.y);
    }

    private void drawDec(Canvas canvas,float arrowEndX,float arrowEndY){
        int desTxtHeight = 30;

        mPaint.setTextSize(30);
        PointF decBaseLine = new PointF();
        decBaseLine.x = arrowEndX;
        float decCenterY = arrowEndY + desTxtHeight /2;
        decBaseLine.y = decCenterY + Tools.getBaseLine(mPaint);
        canvas.drawText(decTxt,decBaseLine.x,decBaseLine.y,mPaint);
    }
}
