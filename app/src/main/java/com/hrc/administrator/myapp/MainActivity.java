package com.hrc.administrator.myapp;

/**
 * Created by Administrator on 2016/9/26.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends BaseActivity {
    private static String TAG="onCreate";
    private Button ok;
    private Button zhuce;
    private TextView misspass;
    private EditText userName;
    private EditText passWord;
    private CheckBox remember;
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;
    private boolean isZhuce;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent=this.getIntent();
        ok=(Button)findViewById(R.id.ok);
        userName=(EditText)findViewById(R.id.userName);
        passWord=(EditText)findViewById(R.id.passWord);
        remember=(CheckBox)findViewById(R.id.choose);
        zhuce=(Button)findViewById(R.id.zhuce);
        misspass=(TextView)findViewById(R.id.misspass);
        shared=PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemeber=shared.getBoolean("remeber", false);
        isZhuce=shared.getBoolean("is",false);
        Log.d(TAG,shared.getString("user","无"));
        Log.d(TAG,shared.getString("mima","无"));
        if((intent.getBooleanExtra("isnew",false))){
            editor=shared.edit();
            editor.putString("mima",intent.getStringExtra("newpass"));
            editor.commit();
            isRemeber=false;
        }
        if(true==(intent.getBooleanExtra("is",false))){
            isZhuce=true;
            editor=shared.edit();
            editor.putString("user",intent.getStringExtra("name"));
            editor.putString("mima",intent.getStringExtra("pass"));
            editor.putBoolean("is",true);
            editor.putString("question",intent.getStringExtra("question"));
            editor.putString("answer",intent.getStringExtra("answer"));
            Log.d(TAG,"设置的问题："+intent.getStringExtra("question"));
            Log.d(TAG,"问题的答案："+intent.getStringExtra("answer"));
            editor.commit();
            Log.d(TAG,shared.getString("user","无"));
            Log.d(TAG,shared.getString("mima","无"));
        }
        if(isRemeber){
            String username=shared.getString("username", "");
            String password=shared.getString("password", "");
            userName.setText(username);
            passWord.setText(password);
            remember.setChecked(true);
        }
        final boolean finalIsRemeber = isRemeber;
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String user=userName.getText().toString();
                String pass=passWord.getText().toString();
                String U=shared.getString("user","");
                String M=shared.getString("mima","");
                Log.d(TAG,"用户名："+U);
                Log.d(TAG,"密码："+M);
                if(U.equals(user)&&M.equals(pass)){
                    editor=shared.edit();
                    if(remember.isChecked()){
                        editor.putBoolean("remeber", true);
                        editor.putString("username", user);
                        editor.putString("password", pass);
                    }else {
                        editor.remove("remeber");
                        editor.remove("username");
                        editor.remove("password");
                    }
                    editor.commit();
                    ListViewActivity.actionStart(MainActivity.this);
                    finish();
                }else{
                    ToastTool.SetMessage(MainActivity.this,"用户名错误");
                    Log.d(TAG,"复选框的选择状态："+ finalIsRemeber);
                    Log.d(TAG,"填写的用户名："+user);
                    Log.d(TAG,"填写的密码："+pass);
                }
            }
        });
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isZhuce){
                    ToastTool.SetMessage(MainActivity.this,"你已经注册过，无需再次注册");
                }else{
                    Intent intent1=new Intent(MainActivity.this, AlertDialogActivity.class);
                    startActivity(intent1);
                    finish();
                }
            }
        });
        misspass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question=shared.getString("question","无");
                String answer=shared.getString("answer","无");
                Log.d(TAG,question);
                Log.d(TAG,answer);
                if(isZhuce){
                    FindPass.actionStart(MainActivity.this,question,answer);
                    finish();
                }else{
                    ToastTool.SetMessage(MainActivity.this,"请先注册");
                }
            }
        });
    }

    public static void actionStart(Context context,String name,String pass,Boolean is,String question,String answer){
        Intent intent=new Intent(context,MainActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("pass",pass);
        intent.putExtra("is",is);
        intent.putExtra("question",question);
        intent.putExtra("answer",answer);
        context.startActivity(intent);
    }

    public static void actionStart(Context context,String newpass,Boolean isnew){
        Intent intent=new Intent(context,MainActivity.class);
        intent.putExtra("newpass",newpass);
        intent.putExtra("isnew",isnew);
        context.startActivity(intent);
    }
}
