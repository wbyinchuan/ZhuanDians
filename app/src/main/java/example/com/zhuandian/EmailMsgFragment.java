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

public class EmailMsgFragment extends Fragment {

    private static final String ARG_PARAM = "param";

    private String title;

    public EmailMsgFragment() {
    }

    public static EmailMsgFragment newInstance(String param1) {
        EmailMsgFragment fragment = new EmailMsgFragment();
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
        View view= inflater.inflate(R.layout.fragment_content, container, false);
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

        int drId = R.drawable.jiantou;

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view;
            view = getViewFromId(R.layout.item_emmsg, parent);
            return new RecyclerView.ViewHolder(view) {
            };
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        }


        @Override
        public int getItemCount() {
            return 10;
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
