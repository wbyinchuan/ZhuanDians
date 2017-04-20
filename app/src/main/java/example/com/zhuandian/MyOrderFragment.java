package example.com.zhuandian;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MyOrderFragment extends Fragment {

    private static final String ARG_PARAM = "param";

    private String title;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public MyOrderFragment() {
    }

    public static MyOrderFragment newInstance(String param1) {
        MyOrderFragment fragment = new MyOrderFragment();
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
        View view= getViewFromId(R.layout.fragment_myorder, container);

        MyOrderListFragment fragment = MyOrderListFragment.newInstance("");

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.hold, R.anim.hold, R.anim.slide_out_right)
                .replace(R.id.frame_myorder, fragment)
                .commit();
        return view;
    }

    private View getViewFromId(@LayoutRes int layout, ViewGroup parent) {
        return LayoutInflater.from(getContext()).inflate(layout, parent, false);
    }
}
