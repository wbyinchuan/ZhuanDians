package example.com.zhuandian;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baronzhang.android.library.adapter.BaseRecyclerViewAdapter;
import com.baronzhang.android.library.util.ViewFindUtils;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yinchuan on 2017/4/14.
 */

public class DrawerListAdapter extends BaseRecyclerViewAdapter {

    private final int HEADER_TYPE = 0;
    private final int NORMAL_TYPE = 1;
    private List<String> data = new ArrayList<>();

    private int[] drawables = {
            R.drawable.my_order,
            R.drawable.my_share,
            R.drawable.my_collection,
            R.drawable.my_bill,
            R.drawable.my_earn,
            R.drawable.invate_friends
    };

    private View header;

    private Context context;

    DrawerListAdapter(Context context) {
        this.context = context;
        String[] dt = {"我的订单",
                "我的分享", "我的收藏", "我的账单", "我的收益", "邀请好友"};
        Collections.addAll(data, dt);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER_TYPE:
                return new RecyclerView.ViewHolder(header) {
                };
            case NORMAL_TYPE:
                View view = LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.list_item, parent, false);
                return new ViewHolderNormal(view, this);
            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && header != null) {
            return HEADER_TYPE;
        }
        return NORMAL_TYPE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == HEADER_TYPE) {
            initDrawerHeader();
            return;
        }
        if (holder instanceof ViewHolderNormal) {
            int curpos = position - 1;
            TextView tv = ((ViewHolderNormal) holder).tv;
            tv.setText(data.get(curpos));
            Drawable drawable_left = context.getResources().getDrawable(drawables[curpos]);
            drawable_left.setBounds(0, 0, drawable_left.getMinimumWidth(), drawable_left.getMinimumHeight());
            tv.setCompoundDrawables(drawable_left, null, null, null);
        }
    }

    private void initDrawerHeader() {
        CircularImageView circularImageView = ViewFindUtils.find(header, R.id.circularImageView);
        circularImageView.setOnClickListener(v -> {
            if (onItemClickListener instanceof DrawerListOnItemClickListener) {
                ((DrawerListOnItemClickListener) onItemClickListener).
                        onPersonImageClick(v);
            }
        });

        ImageButton ibEmail = ViewFindUtils.find(header, R.id.iv_email);
        ibEmail.setOnClickListener(v -> {
            if (onItemClickListener instanceof DrawerListOnItemClickListener) {
                ((DrawerListOnItemClickListener) onItemClickListener).
                        onEmalImageClick(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (header != null) {
            return data.size() + 1;
        }
        return data.size();
    }

    public void addHeader(View headerView) {
        header = headerView;
        notifyItemInserted(0);
    }

    static class ViewHolderNormal extends RecyclerView.ViewHolder {
        @BindView(R.id.item)
        TextView tv;

        public ViewHolderNormal(View itemView, DrawerListAdapter adapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener((v) -> adapter.onItemHolderClick(this));
        }

    }

    interface DrawerListOnItemClickListener extends AdapterView.OnItemClickListener {
        void onPersonImageClick(View view);

        void onEmalImageClick(View view);
    }

}
