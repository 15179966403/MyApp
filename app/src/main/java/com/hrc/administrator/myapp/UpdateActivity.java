package com.hrc.administrator.myapp;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends BaseActivity{
    private MyDatabase myDatabase=new MyDatabase(UpdateActivity.this, "Table.db", null, 1);
    private Button ok;
    private Button no;
    private EditText password;
    private EditText tel;
    private EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent=getIntent();
        final Bundle bundle=intent.getExtras();
        ok=(Button) findViewById(R.id.update_ok);
        no=(Button)findViewById(R.id.update_no);
        password=(EditText)findViewById(R.id.update_password);
        tel=(EditText)findViewById(R.id.update_tel);
        email=(EditText)findViewById(R.id.update_email);
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
                SQLiteDatabase db=myDatabase.getWritableDatabase();
                ContentValues values=new ContentValues();
                String passwordString=password.getText().toString();
                String telString=tel.getText().toString();
                String emailString=email.getText().toString();
                if(TextUtils.isEmpty(telString)){
                    telString="空";
                }
                if(TextUtils.isEmpty(emailString)){
                    emailString="空";
                }
                values.put("password", passwordString);
                values.put("tel", telString);
                values.put("email", emailString);
                db.update("database", values, "contexts=? and username=?", new String[]{bundle.getString("contexts"),bundle.getString("username")});
                ActionGuanli.finishAll();
                ListViewActivity.actionStart(UpdateActivity.this);
                finish();
            }
        });
    }
    public static void actionStart(Context context,Bundle bundle){
        Intent intent=new Intent(context, UpdateActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
