package com.hrc.administrator.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/10.
 */

public class AlertDialogActivity extends Activity{
    private EditText user,pass,passagin,question,answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alertdialog);
        Button ok=(Button)findViewById(R.id.ok);
        Button no=(Button)findViewById(R.id.no);
        user=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.userpass);
        passagin=(EditText)findViewById(R.id.useragain);
        question=(EditText)findViewById(R.id.question);
        answer=(EditText)findViewById(R.id.answer);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(user.getText().toString())&&TextUtils.isEmpty(pass.getText().toString())&&
                        TextUtils.isEmpty(passagin.getText().toString())){
                    Toast.makeText(AlertDialogActivity.this,"不能填写空值",Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.getText().toString().equals(passagin.getText().toString())){
                        MainActivity.actionStart(AlertDialogActivity.this,user.getText().toString(),pass.getText().toString()
                                ,true,question.getText().toString(),answer.getText().toString());
                        finish();
                    }else{
                        Toast.makeText(AlertDialogActivity.this,"两次填写密码不同，重新输入",Toast.LENGTH_SHORT).show();
                        pass.setText("");
                        passagin.setText("");
                    }
                }
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
