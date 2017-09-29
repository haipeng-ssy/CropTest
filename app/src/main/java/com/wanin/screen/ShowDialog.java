package com.wanin.screen;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.HashMap;

/**
 * Created by wanin on 2017/9/27.
 */

public class ShowDialog extends Dialog {


    private ImageView iv;
    private Context mContext;


    public ShowDialog(Context context) {
        super(context, R.style.theme_avchat_wait_dialog);
        mContext = context;
    }

    public ShowDialog(Context context, int themeResId) {
        super(context, R.style.theme_avchat_wait_dialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_show);
        iv = (ImageView) findViewById(R.id.iv_dialog_crop);
    }

    public void setImage(Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
    }

}
