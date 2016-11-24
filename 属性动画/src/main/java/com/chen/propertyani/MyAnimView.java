package com.chen.propertyani;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class MyAnimView extends View {

    public static final float RADIUS = 50f;

    private Point currentPoint;

    private Paint mPaint;

    private String color;

    public MyAnimView(Context context) {
        super(context);
    }

    public MyAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint==null){
            currentPoint=new Point(RADIUS,RADIUS);
            drawCircle(canvas);
            startAnimation();
        }else {
            drawCircle(canvas);
        }

    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    private void startAnimation(){
        Point startPoint=new Point(RADIUS,RADIUS);
        Point endPoint=new Point(getWidth()-RADIUS,getHeight()-RADIUS);
        ValueAnimator anim=ValueAnimator.ofObject(new PointEvaluator(),startPoint,endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint=(Point)animation.getAnimatedValue();
                invalidate();
            }
        });

        ObjectAnimator anim2=ObjectAnimator.ofObject(this,"color",new ColorEvaluator(),"#0000FF","#FF0000");
        AnimatorSet animSet=new AnimatorSet();
        animSet.play(anim).with(anim2);
        animSet.setDuration(5000);
        animSet.setInterpolator(new BounceInterpolator());

        animSet.start();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }

}
