package com.hrc.administrator.myapp;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/11.
 */

public class ToastTool {
    private static Toast mToast;
    private static Handler mHandler=new Handler();
    private static Runnable r=new Runnable() {
        @Override
        public void run() {
            mToast.cancel();
            mToast=null;
        }
    };
    public static void SetMessage(Context context, String message){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mHandler.removeCallbacks(r);
        if(mToast==null){
            mToast=Toast.makeText(context,message,Toast.LENGTH_SHORT);
        }
        mHandler.postDelayed(r,1000);
        mToast.show();
    }
}
