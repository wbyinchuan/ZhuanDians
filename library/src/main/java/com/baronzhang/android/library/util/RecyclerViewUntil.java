package com.baronzhang.android.library.util;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baronzhang.android.library.adapter.BaseRecyclerViewAdapter;

/**
 * Created by yinchuan on 2017/4/19.
 */

public class RecyclerViewUntil  {
    public static void initRecyclerView(
            Context context,
            RecyclerView recyclerView,
            BaseRecyclerViewAdapter adapter,
            View header) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
//        View header = LayoutInflater.from(context).inflate(headerId, recyclerView, false);
//        adapter.addHeader(header);
        recyclerView.setAdapter(adapter);

    }
}
