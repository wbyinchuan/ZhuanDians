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

import com.baronzhang.android.library.util.ViewFindUtils;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderListFragment extends Fragment {

    private static final String ARG_PARAM = "param";

    private String title;

    private final String[] mTitles = {
            "全部", "待付款", "待使用"
            , "待评价", "退款/售后"
    };
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    public MyOrderListFragment() {
    }

    public static MyOrderListFragment newInstance(String param1) {
        MyOrderListFragment fragment = new MyOrderListFragment();
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
        View view= inflater.inflate(R.layout.fragment_myorder_list, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {

        CommonTabLayout tabLayout = ViewFindUtils.find(view, R.id.sliding_tab);

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], R.drawable.ic_menu_camera, R.drawable.ic_menu_camera));
        }
        tabLayout.setTabData(mTabEntities);

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

    class MyAdapter extends RecyclerSwipeAdapter<RecyclerView.ViewHolder>{

        private static final int HEADER_TYPE = 0;
        private static final int NORMAL_TYPE = 1;

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view;
            view = getViewFromId(R.layout.item_orderlist_swipe, parent);
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

        @Override
        public int getSwipeLayoutResourceId(int position) {
            return R.id.swipe;
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

    class TabEntity implements CustomTabEntity {
        public String title;
        public int selectedIcon;
        public int unSelectedIcon;

        public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return selectedIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return unSelectedIcon;
        }
    }


}
