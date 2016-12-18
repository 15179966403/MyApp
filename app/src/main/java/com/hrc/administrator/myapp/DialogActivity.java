package com.hrc.administrator.myapp;

/**
 * Created by Administrator on 2016/9/26.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class DialogActivity extends BaseActivity{
    private Button update;
    private Button select;
    private Button delete;
    private MyDatabase MyDataHelper=new MyDatabase(DialogActivity.this, "Table.db", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        update=(Button)findViewById(R.id.dialog_update);
        select=(Button)findViewById(R.id.dialog_select);
        delete=(Button)findViewById(R.id.dialog_delete);
        final Intent intent=getIntent();
        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Bundle bundle=new Bundle();
                bundle.putString("contexts", intent.getStringExtra("contexts"));
                bundle.putString("username", intent.getStringExtra("username"));
                bundle.putString("password", intent.getStringExtra("password"));
                bundle.putString("tel", intent.getStringExtra("tel"));
                bundle.putString("email", intent.getStringExtra("email"));
                UpdateActivity.actionStart(DialogActivity.this, bundle);
                finish();
            }
        });
        select.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Bundle bundle=new Bundle();
                bundle.putString("contexts", intent.getStringExtra("contexts"));
                bundle.putString("username", intent.getStringExtra("username"));
                bundle.putString("password", intent.getStringExtra("password"));
                bundle.putString("tel", intent.getStringExtra("tel"));
                bundle.putString("email", intent.getStringExtra("email"));
                SelectActivity.actionStart(DialogActivity.this,bundle);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SQLiteDatabase db=MyDataHelper.getWritableDatabase();
                String contexts=intent.getStringExtra("contexts");
				/*String username=intent.getStringExtra("username");
				String password=intent.getStringExtra("password");
				String tel=intent.getStringExtra("tel");
				String email=intent.getStringExtra("email");*/
                db.delete("database", "contexts=?", new String[]{contexts});
                ActionGuanli.finishAll();
                ListViewActivity.actionStart(DialogActivity.this);
                finish();
            }
        });
    }
    public static void actionStart(Context context,String contexts,String username,
                                   String password,String tel,String email){
        Intent intent=new Intent(context, DialogActivity.class);
        intent.putExtra("contexts", contexts);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        intent.putExtra("tel", tel);
        intent.putExtra("email", email);
        context.startActivity(intent);
    }
}
