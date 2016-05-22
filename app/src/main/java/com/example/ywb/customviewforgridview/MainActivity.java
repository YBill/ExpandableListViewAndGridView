package com.example.ywb.customviewforgridview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

/**
 * Created by Bill on 2016/5/22.
 */
public class MainActivity extends AppCompatActivity {

    private MyExpandableListView expandableListView;
    private MyBaseExpandableListAdapter adapter;
    private PullToRefreshScrollView pullToRefreshScrollView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pullToRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.pull_to_refresh);
        pullToRefreshScrollView.setFocusableInTouchMode(true);
        pullToRefreshScrollView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshScrollView.onRefreshComplete();
                    }
                }, 3000);
            }
        });
        expandableListView = (MyExpandableListView)findViewById(R.id.expandablelistview);
        adapter = new MyBaseExpandableListAdapter(this);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        for (int i = 0; i < adapter.getGroupCount(); i++){
            expandableListView.expandGroup(i);
        }


    }

}
