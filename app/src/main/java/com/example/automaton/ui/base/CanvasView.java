package com.example.automaton.ui.base;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.example.automaton.model.StateCircle;

import java.util.ArrayList;

import automaton.automaton.Automaton;
import automaton.automaton.State;

public class CanvasView extends View {
    // Circle Props
    static int CIRCLE_RADIUS = 60;
    static int CIRCLE_MARGIN = 60;
    private int max_x = CIRCLE_MARGIN + CIRCLE_RADIUS;
    private int max_y = CIRCLE_MARGIN + CIRCLE_RADIUS;
    private int currentCount = 0;
    // Paints
    private Paint circlePaint;
    private Paint textPaint;
    // Constants
    private int MAX_XAXIS = this.getResources().getDisplayMetrics().widthPixels;
    private int MAX_YAXIS = 400;
    private float MIN_SCALE = 1;
    private float MAX_SCALE = 2;
    // Gestures Props
    private float oldScale = 1;
    private float currentScale = 1;
    private ObjectAnimator bigAnimator;
    private ObjectAnimator smallAnimator;
    private float canvasScaleOffsetX = 0;
    private float canvasScaleOffsetY = 0;
    private boolean isScaled = false;
    // Automaton
    private Automaton automaton = new Automaton();
    // Others
    Context context;
    private ArrayList<StateCircle> stateCircles = new ArrayList<>();

    public CanvasView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setTouchListener();
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(3);
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(48);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.scale(currentScale, currentScale, canvasScaleOffsetX, canvasScaleOffsetY);
        for (StateCircle stateCircle : stateCircles) {
            canvas.drawCircle(stateCircle.xPosition, stateCircle.yPosition, stateCircle.radius, circlePaint);
            String stateName = stateCircle.state.getName();
            float textX = stateCircle.xPosition - stateName.length() * 48 / 4;
            float textY = stateCircle.yPosition + 48 / 4;
            canvas.drawText(stateCircle.state.getName(), textX, textY, textPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void setTouchListener() {
        this.setOnTouchListener(new OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    if (isScaled) {
                        getSmallAnimator().start();
                    } else {
                        canvasScaleOffsetX = e.getX();
                        canvasScaleOffsetY = e.getY();
                        getBigAnimator().start();
                    }
                    isScaled = !isScaled;
                    return super.onDoubleTap(e);
                }

                @Override
                public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                    canvasScaleOffsetX += distanceX;
                    canvasScaleOffsetY += distanceY;
                    invalidate();
                    return super.onScroll(e1, e2, distanceX, distanceY);
                }
            });

            private ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.OnScaleGestureListener() {
                @Override
                public boolean onScale(ScaleGestureDetector detector) {
                    currentScale = oldScale * detector.getScaleFactor();
                    currentScale = Math.max(currentScale, MIN_SCALE);
                    currentScale = Math.min(currentScale, MAX_SCALE);
                    invalidate();
                    return false;
                }

                @Override
                public boolean onScaleBegin(ScaleGestureDetector detector) {
                    oldScale = currentScale;
                    return true;
                }

                @Override
                public void onScaleEnd(ScaleGestureDetector detector) {
                }
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                scaleGestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    public void drawStatesCircle(int count) {
        for (int i = 0; i < count; i++) {
            if (max_x + CIRCLE_RADIUS * 3 + CIRCLE_MARGIN > MAX_XAXIS) {
                max_x = CIRCLE_MARGIN + CIRCLE_RADIUS;
                max_y += CIRCLE_RADIUS * 2 + CIRCLE_MARGIN;
            } else {
                max_x += CIRCLE_RADIUS * 2 + CIRCLE_MARGIN;
            }
            State newState = new State(automaton);
            newState.setName("s" + (currentCount + i));
            stateCircles.add(new StateCircle(max_x, max_y, CIRCLE_RADIUS, newState));
        }
        currentCount = count;
        invalidate();
    }

    public ObjectAnimator getBigAnimator() {
        if (bigAnimator == null) {
            bigAnimator = ObjectAnimator.ofFloat(this, "currentScale", currentScale, 2);
        }
        bigAnimator.setFloatValues(currentScale, 2);
        return bigAnimator;
    }

    public ObjectAnimator getSmallAnimator() {
        if (smallAnimator == null) {
            smallAnimator = ObjectAnimator.ofFloat(this, "currentScale", currentScale, 1);
            smallAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) { }

                @Override
                public void onAnimationEnd(Animator animation) {
                    canvasScaleOffsetX = 0;
                    canvasScaleOffsetY = 0;
                }

                @Override
                public void onAnimationCancel(Animator animation) { }

                @Override
                public void onAnimationRepeat(Animator animation) { }
            });
        }
        smallAnimator.setFloatValues(currentScale, 1);
        return smallAnimator;
    }

    public void setCurrentScale(float currentScale) {
        this.currentScale = currentScale;
        invalidate();
    }
}