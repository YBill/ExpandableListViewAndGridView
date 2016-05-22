package com.example.ywb.customviewforgridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bill on 2016/5/22.
 */
public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> myList = new ArrayList<>();

    public MyBaseExpandableListAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return 10;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return myList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder = null;
        if(convertView == null){
            convertView =  LayoutInflater.from(context).inflate(R.layout.layout_group_item, parent, false);
            holder = new GroupViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.tv_group_content);
            convertView.setTag(holder);
        }else{
            holder = (GroupViewHolder) convertView.getTag();
        }
        holder.textView.setText("Group " + groupPosition);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder = null;
        if(convertView == null){
            convertView =  LayoutInflater.from(context).inflate(R.layout.layout_child_item, parent, false);
            holder = new ChildViewHolder();
            holder.gridView = (MyGridView) convertView.findViewById(R.id.gridview);
            convertView.setTag(holder);
        }else{
            holder = (ChildViewHolder) convertView.getTag();
        }
        if(groupPosition == 2 || groupPosition == 4 || groupPosition == 5)
            holder.gridView.setNumColumns(3);
        MyGridViewAdapter adapter = new MyGridViewAdapter(context);
        holder.gridView.setAdapter(adapter);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class GroupViewHolder{
        TextView textView;
    }

    private class ChildViewHolder{
        MyGridView gridView;
    }

}
