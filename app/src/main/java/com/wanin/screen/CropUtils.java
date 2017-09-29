package com.wanin.screen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.util.HashMap;

/**
 * Created by wanin on 2017/9/28.
 */

public class CropUtils {

    // 截图
    public Bitmap cropBitmap(BitmapFactory.Options options, Bitmap oBitmap, CropCanvas cropCanvas) {
        HashMap<String, Float> map = cropCanvas.getPoints();

        float x = map.get("x");
        float y = map.get("y");//<0?0:map.get("y");
        float p = map.get("p");//<0?0:map.get("x");
        float q = map.get("q");//<0?0:map.get("x");

        if (x < 0) x = 0;
        if (x > options.outWidth) x = options.outWidth - 5;

        if (y < 0) y = 0;
        if (y > options.outHeight) y = options.outHeight - 5;

        if (p < 0) p = 5;
        if (p > options.outWidth) p = options.outWidth;

        if (q < 0) q = 5;
        if (q > options.outHeight) q = options.outHeight;

        int width = (int) (p - x);
        int height = (int) (q - y);

        Bitmap bitmap = Bitmap.createBitmap(oBitmap, (int) x, (int) y, width, height);

        return bitmap;
    }

    //显示图和截图框
    public Bitmap getBitmap(Context mContext,
                            Bitmap bitmap,
                            BitmapFactory.Options options,
                            CropCanvas cropCanvas,
                            FloatObject f_ratio,
                            BooleanObject b_hori) {

        float a, b, c, d;

        int outWidth = options.outWidth;
        int outHeight = options.outHeight;

        float dialogWidth = DenistyUtils.dip2px(mContext, 850);
        float dialogHeight = DenistyUtils.dip2px(mContext, 650);


        float showWidth = 0f;
        float showHeight = 0f;

        if (outWidth >= outHeight) {

            showWidth = dialogWidth - 50;
            f_ratio.setRatio(showWidth / outWidth);
            showHeight = outHeight * (f_ratio.getRatio());
            b_hori.setHori(true);
            a = 0 + 25;
            b = ((dialogHeight - showHeight) / (float) 2);
            c = dialogWidth - 25;
            d = ((dialogHeight - showHeight) / (float) 2) + showHeight;
        } else {
            b_hori.setHori(false);
            showHeight = dialogHeight - 50;
            f_ratio.setRatio(showHeight / outHeight);
            showWidth = outWidth * (f_ratio.getRatio());
            a = (dialogWidth - showWidth) / (float) 2;
            b = 25;
            c = ((dialogWidth - showWidth) / (float) 2) + showWidth;
            d = dialogHeight - 25;
        }

        Matrix matrix = new Matrix();
        matrix.postScale(f_ratio.getRatio(), f_ratio.getRatio());

//        options.inJustDecodeBounds = false;
        Bitmap scaleBitmap = Bitmap.createBitmap(bitmap, 0, 0, outWidth, outHeight, matrix, true);

        cropCanvas.setValues(a, b, c, d, f_ratio, b_hori);
        cropCanvas.invalidate();

        return scaleBitmap;
//        return bitmap;
    }

}
