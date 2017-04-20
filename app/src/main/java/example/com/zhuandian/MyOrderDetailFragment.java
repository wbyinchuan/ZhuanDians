package example.com.zhuandian;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyOrderDetailFragment extends Fragment {

    private static final String ARG_PARAM = "param";

    private String title;

    public MyOrderDetailFragment() {
    }

    public static MyOrderDetailFragment newInstance(String param1) {
        MyOrderDetailFragment fragment = new MyOrderDetailFragment();
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
        View view= inflater.inflate(R.layout.fragment_myorder_detail, container, false);

        return view;
    }

    private View getViewFromId(@LayoutRes int layout, ViewGroup parent) {
        return LayoutInflater.from(getContext()).inflate(layout, parent, false);
    }

}
