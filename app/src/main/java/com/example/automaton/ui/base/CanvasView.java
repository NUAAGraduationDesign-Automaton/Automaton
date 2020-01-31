package com.example.automaton.ui.base;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.automaton.model.StateCircle;

import java.util.ArrayList;

public class CanvasView extends View {
    static int CIRCLE_RADIUS = 30;
    static int CIRCLE_MARGIN = 20;
    private Paint mPaint;
    private static Canvas mCanvas;
    private int MAX_XAXIS = this.getResources().getDisplayMetrics().widthPixels;
    private int MAX_YAXIS = 400;
    private int max_x = CIRCLE_MARGIN + CIRCLE_RADIUS;
    private int max_y = CIRCLE_MARGIN + CIRCLE_RADIUS;
    private float currentScale = 1;
    private ObjectAnimator bigAnimator;

    Context context;
    private ArrayList<StateCircle> stateCircles = new ArrayList<>();

    public CanvasView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setOnTouchListener(new OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    getBigAnimator().start();
                    return super.onDoubleTap(e);
                }
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;
        super.onDraw(canvas);
        canvas.scale(currentScale, currentScale);
        for (StateCircle stateCircle : stateCircles) {
            canvas.drawCircle(stateCircle.xPosition, stateCircle.yPosition, stateCircle.radius, mPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    public void drawStatesCircle(int count) {
        for (int i = 0; i < count; i++) {
            if (max_x + CIRCLE_RADIUS * 3 + CIRCLE_MARGIN > MAX_XAXIS) {
                max_x = CIRCLE_MARGIN + CIRCLE_RADIUS;
                max_y += CIRCLE_RADIUS * 2 + CIRCLE_MARGIN;
            } else {
                max_x += CIRCLE_RADIUS * 2 + CIRCLE_MARGIN;
            }
            stateCircles.add(new StateCircle(max_x, max_y, CIRCLE_RADIUS));
        }
        invalidate();
    }

    public ObjectAnimator getBigAnimator() {
        if (bigAnimator == null) {
            bigAnimator = ObjectAnimator.ofFloat(this, "currentScale", currentScale, 2);
        }
        bigAnimator.setFloatValues(currentScale, 2);
        return bigAnimator;
    }

    public void setCurrentScale(float currentScale) {
        this.currentScale = currentScale;
        invalidate();
    }
}