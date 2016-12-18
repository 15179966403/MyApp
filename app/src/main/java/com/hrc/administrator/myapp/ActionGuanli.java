package com.hrc.administrator.myapp;

/**
 * Created by Administrator on 2016/9/26.
 */
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class ActionGuanli {
    public static List<Activity> lists=new ArrayList<Activity>();
    public static void addActivity(Activity activity){
        lists.add(activity);
    }
    public static void removeActivity(Activity activity){
        lists.remove(activity);
    }
    public static void finishAll(){
        for(Activity a:lists){
            if(!a.isFinishing()){
                a.finish();
            }
        }
    }
}
