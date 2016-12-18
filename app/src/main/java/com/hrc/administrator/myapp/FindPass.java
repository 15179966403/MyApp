package com.hrc.administrator.myapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/11.
 */

public class FindPass extends Activity{
    private EditText answer,newpass,againpass;
    private TextView question;
    private Button ok,no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findpass_activity);
        answer=(EditText)findViewById(R.id.answer);
        question=(TextView)findViewById(R.id.question);
        newpass=(EditText)findViewById(R.id.newpass);
        againpass=(EditText)findViewById(R.id.againpass);
        ok=(Button)findViewById(R.id.ok);
        no=(Button)findViewById(R.id.no);
        question.setText("安全问题："+getIntent().getStringExtra("question"));
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer.getText().toString().equals(getIntent().getStringExtra("answer"))){
                    if(newpass.getText().toString().equals(againpass.getText().toString())){
                        ToastTool.SetMessage(FindPass.this,"新的密码修改成功");
                        MainActivity.actionStart(FindPass.this,newpass.getText().toString(),true);
                        finish();
                    }else{
                        ToastTool.SetMessage(FindPass.this,"请确保两次输入的密码一致");
                    }
                }else{
                    ToastTool.SetMessage(FindPass.this,"安全问题回答错误");
                    answer.setText("");
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

    public static void actionStart(Context context,String question,String answer){
        Intent intent=new Intent(context,FindPass.class);
        intent.putExtra("question",question);
        intent.putExtra("answer",answer);
        context.startActivity(intent);
    }
}
