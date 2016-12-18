package com.hrc.administrator.myapp;

/**
 * Created by Administrator on 2016/9/26.
 */
import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        ActionGuanli.addActivity(this);
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        ActionGuanli.removeActivity(this);
    }
}
