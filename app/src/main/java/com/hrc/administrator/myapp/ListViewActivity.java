package com.hrc.administrator.myapp;

/**
 * Created by Administrator on 2016/9/26.
 */
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.R.anim;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class ListViewActivity extends BaseActivity{
    private Button add;				//添加按钮
    private List<ListViewlei> list=new ArrayList<ListViewlei>();
    private MyDatabase MyDatabaseHelper=new MyDatabase(ListViewActivity.this, "Table.db", null, 1);
    private ListViewAdapter adapter;
    private ListView listView;
    //进行列表的初始化
    private void init(){
        SQLiteDatabase db=MyDatabaseHelper.getWritableDatabase();
        Cursor cursor=db.query("database", null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                String contexts=cursor.getString(cursor.getColumnIndex("contexts"));
                String username=cursor.getString(cursor.getColumnIndex("username"));
                String password=cursor.getString(cursor.getColumnIndex("password"));
                String tel=cursor.getString(cursor.getColumnIndex("tel"));
                String email=cursor.getString(cursor.getColumnIndex("email"));
                String shuzu[]={contexts,username,password,tel,email};
                for(String s:shuzu){
                    //如果为空或者空字符串，则统一为空
                    if(TextUtils.isEmpty(s)){
                        s="空";
                    }
                }
                ListViewlei lei=new ListViewlei(contexts, username, password, tel, email);
                list.add(lei);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        add=(Button)findViewById(R.id.list_add);
        //下拉列表的数据源
        init();
        adapter=new ListViewAdapter(ListViewActivity.this, R.layout.listview_less, list);
        //ListViewAdapter adapter=new ListViewAdapter(ListViewActivity.this, R.layout.listview_less, list);
        listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                AddDataActivity.actionStart(ListViewActivity.this);
                finish();
            }
        });
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                ListViewlei lei=list.get(position);
                DialogActivity.actionStart(ListViewActivity.this, lei.getContexts(), lei.getUsername(), lei.getPassword(), lei.getTel(), lei.getEmail());
            }
        });
    }
    public static void actionStart(Context context){
        Intent intent=new Intent(context,ListViewActivity.class);
        context.startActivity(intent);
    }
}
