package com.baronzhang.android.library.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.AdapterView;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com ==>> baronzhang.com)
 *         16/4/15
 */
public abstract class BaseRecyclerViewAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T>{

    protected AdapterView.OnItemClickListener onItemClickListener;
  //  protected View header;

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {

        this.onItemClickListener = onItemClickListener;
    }

    protected void onItemHolderClick(RecyclerView.ViewHolder itemHolder) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        } else {
            throw new IllegalStateException("Please call setOnItemClickListener method set the click event listeners");
        }
    }

//    public void addHeader(View headerView) {
//        header = headerView;
//        notifyItemInserted(0);
//    }

}
