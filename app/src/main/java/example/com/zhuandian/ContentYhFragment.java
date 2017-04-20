package example.com.zhuandian;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baronzhang.android.library.adapter.BaseRecyclerViewAdapter;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.yalantis.phoenix.AutoLoadOnScrollListener;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.HashMap;

public class ContentYhFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    public ContentYhFragment() {
    }

    public static ContentYhFragment newInstance(String param1) {
        ContentYhFragment fragment = new ContentYhFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
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

        PullToRefreshView mPullToRefreshView = (PullToRefreshView)view.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(() -> mPullToRefreshView.setRefreshing(false));

        AutoLoadOnScrollListener mAutoLoadListener = new AutoLoadOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                new Handler().postDelayed(() -> {
                    myAdapter.size += 5;
                    myAdapter.notifyItemInserted(myAdapter.getItemCount() - 1);
                    this.setLoading(false);
                }, 200);

            }
        };
        recyclerView.addOnScrollListener(mAutoLoadListener);
    }

    private View getViewFromId(@LayoutRes int layout, ViewGroup parent) {
        return LayoutInflater.from(getContext()).inflate(layout, parent, false);
    }

    class MyAdapter extends BaseRecyclerViewAdapter {

        private static final int HEADER_TYPE = 0;
        private static final int NORMAL_TYPE = 1;

        public int size = 10;

        @Override
        public int getItemViewType(int position) {
            switch (position) {
                case 0:
                    return HEADER_TYPE;
                default:
                    return NORMAL_TYPE;

            }
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            switch (viewType) {
                case HEADER_TYPE:
                    View slider= initSlider(parent);
                    return new IViewHolder(slider);
                case NORMAL_TYPE:
                    View item = getViewFromId(R.layout.item_youhui, parent);
                    TextView zhekou = (TextView) item.findViewById(R.id.tv_zhekou);
                    setZheKouText(zhekou);
                    TextView price = (TextView) item.findViewById(R.id.tv_price);
                    setPriceText(price);
                    return new IViewHolder(item);
                default:
                    throw new IllegalArgumentException("");
            }

        }

        private View initSlider(ViewGroup parent) {
            View slider = getViewFromId(R.layout.header_slider, parent);
            SliderLayout sliderLayout = (SliderLayout) slider.findViewById(R.id.slider);

            HashMap<String,Integer> file_maps = new HashMap<>();
            file_maps.put("Hannibal",R.drawable.banner);
            file_maps.put("Big Bang Theory",R.drawable.coupon_item_icon);
            file_maps.put("House of Cards",R.drawable.share_item);
            for (String key : file_maps.keySet()) {
                DefaultSliderView sliderView = new DefaultSliderView(getContext());
                // initialize a SliderLayout
                sliderView
                        .description(key)
                        .image(file_maps.get(key))
                        .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                        .setOnSliderClickListener(null);

                //add your extra information
                sliderView.bundle(new Bundle());
                sliderView.getBundle()
                        .putString("extra",key);

                sliderLayout.addSlider(sliderView);
            }
            sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);
            sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            sliderLayout.setPresentIndcatorShape(PagerIndicator.Shape.Rectangle);
            sliderLayout.setDuration(4000);
            sliderLayout.addOnPageChangeListener(null);
            return slider;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return size;
        }

    }

    static class IViewHolder extends RecyclerView.ViewHolder {
        public IViewHolder(View itemView) {
            super(itemView);
        }
    }

    private void setZheKouText(TextView textView) {

        String text = "10元抢<font color='#ff8800'>5.5折</font>折扣券";
        String text1 = "<font color='#ff8800'>";
        String text2 = "5.5折";
        String text3 = "</font>折扣券";

        StringBuilder sb = new StringBuilder(text);
        sb.append(text1);
        sb.append(text2);
        sb.append(text3);

        textView.setText(Html.fromHtml(text));

//        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setPriceText(TextView price) {
        price.setText(Html.fromHtml("<big><big>50</big></big>元"));
    }


}
