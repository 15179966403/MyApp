package com.hrc.administrator.myapp;

/**
 * Created by Administrator on 2016/9/26.
 */
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListViewAdapter extends ArrayAdapter<ListViewlei>{
    private int resourceid;

    public ListViewAdapter(Context context, int resource, List<ListViewlei> objects) {
        super(context, resource, objects);
        // TODO Auto-generated constructor stub
        resourceid=resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ListViewlei listViews=getItem(position);
        View view=LayoutInflater.from(getContext()).inflate(resourceid, null);
        TextView contexts=(TextView)view.findViewById(R.id.listview_contexts);
        TextView username=(TextView)view.findViewById(R.id.listview_username);
        TextView password=(TextView)view.findViewById(R.id.listview_password);
        contexts.setText(listViews.getContexts());
        username.setText(listViews.getUsername());
        password.setText(listViews.getPassword());
        return view;
    }
}
