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
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import java.util.HashMap;

public class ContentFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;
    private View SliderView;

    public ContentFragment() {
    }

    public static ContentFragment newInstance(String param1) {
        ContentFragment fragment = new ContentFragment();
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
        SliderView = getViewFromId(R.layout.slider_layout,container);

        View view= inflater.inflate(R.layout.fragment_content, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener((parent, view1, position, id) -> {});
        return view;
    }

    private View getViewFromId(@LayoutRes int layout, ViewGroup parent) {
        return LayoutInflater.from(getContext()).inflate(layout, parent, false);
    }

    class MyAdapter extends BaseRecyclerViewAdapter {

        private static final int HEADER_TYPE = 0;
        private static final int NORMAL_TYPE = 1;

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
                    initSlider(SliderView);
                    return new IViewHolder(new TextView(getContext()));
                case NORMAL_TYPE:
                    View item = getViewFromId(R.layout.item_youhui, parent);
                    return new IViewHolder(item);
                default:
                    throw new IllegalArgumentException("");
            }

        }

        private void initSlider(View view) {
            SliderLayout sliderLayout = (SliderLayout) view.findViewById(R.id.slider);
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
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(slider -> {
                            //SliderClick
                        });

                //add your extra information
                sliderView.bundle(new Bundle());
                sliderView.getBundle()
                        .putString("extra",key);

                sliderLayout.addSlider(sliderView);
            }
            sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
            sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            sliderLayout.setCustomAnimation(new DescriptionAnimation());
            sliderLayout.setDuration(4000);
            sliderLayout.addOnPageChangeListener(null);

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
        public IViewHolder(View itemView) {
            super(itemView);
        }
    }


}
