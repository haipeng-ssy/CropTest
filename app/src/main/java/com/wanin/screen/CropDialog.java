package com.wanin.screen;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by wanin on 2017/9/27.
 */

public class CropDialog extends Dialog implements View.OnClickListener {


    private ImageView iv;
    private RelativeLayout mView;
    private String path = "/mnt/sdcard/TutorFiles/Picture/126.jpg";

    private Context mContext;
    CropCanvas cropCanvas;
    FloatObject floatObject = new FloatObject();
    BooleanObject B_hori = new BooleanObject();
    Bitmap bitmap;
    BitmapFactory.Options options = new BitmapFactory.Options();
    Button btnOk;
    CropUtils cropUtils;
    ShowDialog showDialog;


    public CropDialog(Context context) {
        super(context, R.style.theme_avchat_wait_dialog);
        mContext = context;
    }

    public CropDialog(Context context, int themeResId) {
        super(context, R.style.theme_avchat_wait_dialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_crop);
        mView = (RelativeLayout) findViewById(R.id.crop_bg);
        iv = (ImageView) findViewById(R.id.iv_dialog_crop);
        btnOk = (Button) findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);

        bitmap = BitmapFactory.decodeFile(path, options); // 初始化加载图片
        cropCanvas = new CropCanvas(mContext); // 初始化加载截图框
        mView.addView(cropCanvas);

        cropUtils = new CropUtils();
        iv.setImageBitmap(cropUtils.getBitmap(getContext(), bitmap, options, cropCanvas, floatObject, B_hori)); //显示图和截图框
        showDialog = new ShowDialog(mContext); //初始化加载截图后显示dialog
    }

    @Override
    public void onClick(View v) {
        showDialog.show();
        showDialog.setImage(cropUtils.cropBitmap(options, bitmap, cropCanvas));
        dismiss();
    }


}
