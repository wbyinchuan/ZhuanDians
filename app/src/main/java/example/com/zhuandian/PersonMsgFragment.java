package example.com.zhuandian;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baronzhang.android.library.adapter.BaseRecyclerViewAdapter;
import com.mikhaellopez.circularimageview.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonMsgFragment extends Fragment {

    private static final String ARG_PARAM = "param";

    private String title;

    public PersonMsgFragment() {
    }

    public static PersonMsgFragment newInstance(String param1) {
        PersonMsgFragment fragment = new PersonMsgFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_prsmsg, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener((parent, view1, position, id) -> {});
    }

    private View getViewFromId(@LayoutRes int layout, ViewGroup parent) {
        return LayoutInflater.from(getContext()).inflate(layout, parent, false);
    }

    class MyAdapter extends BaseRecyclerViewAdapter {

        private static final int HEADER_TYPE = 0;
        private static final int NORMAL_TYPE = 1;

        private String[] keys= {
                "头像","昵称","性别","出生年月","所在地区","","手机","微信","支付宝"
        };
        private String values[] = {
                "","尹川","男","1991-09-01","北京 海淀","","187******01","djksf","未绑定"
        };

        int drId = R.drawable.jiantou;

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view;

            if (viewType == 0) {
                view = getViewFromId(R.layout.item_prsmsg_hd, parent);
                return new HViewHolder(view);
            }
            if (viewType == 5) {
                view = getViewFromId(R.layout.item_prsmsg_pd, parent);
                return new RecyclerView.ViewHolder(view) {
                };
            }
            view = getViewFromId(R.layout.item_prsmsg, parent);
            return new IViewHolder(view);
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            if (position == 0) {
                HViewHolder hd = (HViewHolder) holder;
                hd.tv_key.setText(keys[position]);
                return;
            }

            if (position == 5) return;

            IViewHolder hd = (IViewHolder) holder;
            hd.tv_key.setText(keys[position]);
            hd.tv_msg.setText(values[position]);
            hd.line.setVisibility(View.VISIBLE);
            if (position == 6) {
                hd.line.setVisibility(View.INVISIBLE);
            }
//            if (position > 5) {
//                Drawable drawable = getContext().getResources().getDrawable(drId);
//                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                hd.tv_msg.setCompoundDrawables(null, null, drawable, null);
//            }
        }


        @Override
        public int getItemCount() {
            return keys.length;
        }

    }

    static class IViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_msg)
        TextView tv_msg;
        @BindView(R.id.tv_key)
        TextView tv_key;
        @BindView(R.id.line)
        View line;

        public IViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class HViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cv_touxiang)
        CircularImageView cv_touxiang;
        @BindView(R.id.tv_key)
        TextView tv_key;
        public HViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
