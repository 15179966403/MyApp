package com.hrc.administrator.myapp;

/**
 * Created by Administrator on 2016/9/26.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SelectActivity extends BaseActivity{
    private Button back;
    private TextView username;
    private TextView password;
    private TextView contexts;
    private TextView tel;
    private TextView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        back=(Button)findViewById(R.id.select_back);
        contexts=(TextView)findViewById(R.id.select_contexts);
        username=(TextView) findViewById(R.id.select_username);
        password=(TextView) findViewById(R.id.select_password);
        tel=(TextView) findViewById(R.id.select_tel);
        email=(TextView) findViewById(R.id.select_email);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ListViewActivity.actionStart(SelectActivity.this);
                finish();
            }
        });
        contexts.setText("平台："+bundle.getString("contexts"));
        username.setText("账户："+bundle.getString("username"));
        password.setText("密码："+bundle.getString("password"));
        tel.setText("电话："+bundle.getString("tel"));
        email.setText("邮箱："+bundle.getString("email"));
    }
    public static void actionStart(Context context,Bundle bundle){
        Intent intent=new Intent(context, SelectActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
