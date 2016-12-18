package com.hrc.administrator.myapp;

/**
 * Created by Administrator on 2016/9/26.
 */
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddDataActivity extends Activity{
    private EditText username;
    private EditText password;
    private EditText contexts;
    private EditText tel;
    private EditText email;
    private Button ok;
    private Button no;
    private MyDatabase MyDataHelper=new MyDatabase(AddDataActivity.this, "Table.db", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata);
        username=(EditText)findViewById(R.id.add_data_username);
        password=(EditText)findViewById(R.id.add_data_password);
        contexts=(EditText)findViewById(R.id.add_data_contexts);
        tel=(EditText)findViewById(R.id.add_data_tel);
        email=(EditText)findViewById(R.id.add_data_email);
        ok=(Button)findViewById(R.id.add_data_ok);
        no=(Button)findViewById(R.id.add_data_no);
        no.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String scontexts=contexts.getText().toString();
                String susername=username.getText().toString();
                String spassword=password.getText().toString();
                String stel=tel.getText().toString();
                String semail=email.getText().toString();
                if(TextUtils.isEmpty(scontexts)){
                    scontexts="空";
                }
                if(TextUtils.isEmpty(susername)){
                    susername="空";
                }
                if(TextUtils.isEmpty(spassword)){
                    spassword="空";
                }
                if(TextUtils.isEmpty(stel)){
                    stel="空";
                }
                if(TextUtils.isEmpty(semail)){
                    semail="空";
                }
                SQLiteDatabase db=MyDataHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("contexts", scontexts);
                values.put("username", susername);
                values.put("password", spassword);
                values.put("tel", stel);
                values.put("email", semail);
                db.insert("database", null, values);
                ListViewActivity.actionStart(AddDataActivity.this);
                finish();
            }
        });
    }
    public static void actionStart(Context context){
        Intent intent=new Intent(context, AddDataActivity.class);
        context.startActivity(intent);
    }
}
