package com.wanin.screen;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    private CropDialog cropDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cropDialog = new CropDialog(this);
        Window dialogWindow = cropDialog.getWindow();
        WindowManager.LayoutParams attributes = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER | Gravity.CENTER);
        dialogWindow.setAttributes(attributes);

        cropDialog.show();
    }
}
