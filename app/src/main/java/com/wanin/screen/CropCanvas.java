package com.wanin.screen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanin on 2017/9/28.
 */

public class CropCanvas extends View {


    float origin_a, origin_b, origin_c, origin_d;
    float a, b, c, d;
    Canvas canvas;
    int direction;
    float dragRange = 2.0f;

    public CropCanvas(Context context) {
        super(context);
    }

    public CropCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CropCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    FloatObject f_ratio = null;
    BooleanObject hori;

    public void setValues(float a, float b, float c, float d, FloatObject f_ratio, BooleanObject hori) {

        this.hori = hori;
        this.f_ratio = f_ratio;

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;

        origin_a = a;
        origin_b = b;
        origin_c = c;
        origin_d = d;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth((float) 1.0);
        RectF rectF = new RectF(a, b, c, d);
//        canvas.drawColor(Color.RED);
        canvas.drawRect(rectF, paint);
    }

    public int leftDrag = 1, rightDrag = 2, topDrag = 3, bottomDrag = 4;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                directionDrag(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                translate(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                Log.d("tag", "a =" + a + "b =" + b + "c =" + c + "d =" + d);
                getPoints();
                invalidate();
                break;
        }
        return true;
    }

    public HashMap<String, Float> getPoints() {

        float x, y, p, q;
        HashMap<String, Float> map = new HashMap<String, Float>();
        if (hori.isHori()) {
            x = ((a - 25)) / f_ratio.getRatio();
            y = (b - origin_b) / f_ratio.getRatio();
            p = (c - 25) / f_ratio.getRatio();
            q = (d - origin_b) / f_ratio.getRatio();
        } else {
            x = ((a - origin_a)) / f_ratio.getRatio();
            y = (b - 25) / f_ratio.getRatio();
            p = (c - origin_a) / f_ratio.getRatio();
            q = (d - 25) / f_ratio.getRatio();
        }

        map.put("x", x);
        map.put("y", y);
        map.put("p", p);
        map.put("q", q);

        Log.d("tag", "x =" + x + "y =" + y + "p =" + p + "q=" + q);
        return map;
    }

    public void translate(float x, float y) {
        switch (direction) {
            case 0:
                directionDrag(x, y);
                break;
            case 1:
                if (x > origin_a && x < c) {
                    a = x;
                    invalidate();
                }
                break;
            case 2:
                if (x < origin_c && x > a) {
                    c = x;
                    invalidate();
                }
                break;
            case 3:
                if (y > origin_b && y < d) {
                    b = y;
                    invalidate();
                }
                break;
            case 4:
                Log.d("tag", "4");
                if (y < origin_d && y > b) {
                    d = y;
                    invalidate();
                }
                break;

        }
    }


    public void directionDrag(float x, float y) {
        direction = 0;
        if (Math.abs(x - a) < dragRange * 5) {
            direction = leftDrag;
            return;
        }
        if (Math.abs(x - c) < dragRange * 15) {
            direction = rightDrag;
            return;
        }
        if (Math.abs(y - b) < dragRange * 5) {
            direction = topDrag;
            return;
        }
        if (Math.abs(y - d) < dragRange * 15) {
            direction = bottomDrag;
            return;
        }
    }


}
