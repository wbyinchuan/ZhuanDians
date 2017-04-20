package example.com.zhuandian;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.baronzhang.android.library.activity.ToolBarWithDrawerBaseActivity;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivityWithDrawer extends ToolBarWithDrawerBaseActivity {

    @BindView(R.id.gerenxinxi)
    RecyclerView mRecyclerView;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.tl_segment)
    SegmentTabLayout tabLayout;
    @BindView(R.id.bt_op_nav)
    ImageView bt_op_nav;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    String[] titles = {"优惠", "分享"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        ButterKnife.bind(this);
        initViews();
        initData();
    }

    private void initViews() {

        //初始化RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        DrawerListAdapter drawerListAdapter = new DrawerListAdapter(MainActivityWithDrawer.this);

        //初始化drawer
        mRecyclerView.setAdapter(drawerListAdapter);
        View header = LayoutInflater.from(this).inflate(R.layout.nav_header, mRecyclerView, false);
        drawerListAdapter.addHeader(header);
        drawerListAdapter.setOnItemClickListener(
                new DrawerListAdapter.DrawerListOnItemClickListener() {
                    @Override
                    public void onPersonImageClick(View view) {
                        gotoActivity(view, PersonMsgActivity.class);
                    }

                    @Override
                    public void onEmalImageClick(View view) {
                        gotoActivity(view,EmailMsgActivity.class);
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 1:
                                gotoActivity(view, MyOrderActivity.class);
                                break;
                            default:
                        }
                    }
                }
        );

        TextView nav_foot_left = initNavFootView(R.id.nav_foot_left, "设置", R.drawable.my_setting);
        TextView nav_foot_right = initNavFootView(R.id.nav_foot_right, "帮助", R.drawable.my_help);

        nav_foot_left.setOnClickListener((v) -> {
        });
        nav_foot_right.setOnClickListener((v) -> {
        });
    }

    private void gotoActivity(View view,  Class<?> cls) {
        view.postDelayed(() -> {
            Intent intent = new Intent(MainActivityWithDrawer.this, cls);
            startActivity(intent);
        },250);
        closeDrawer();
    }

    private TextView initNavFootView(@IdRes int id, String text, @DrawableRes int my_setting) {
        TextView nav_foot = (TextView) findViewById(id);
        nav_foot.setText(text);
        Drawable drawable = getResources().getDrawable(my_setting);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        nav_foot.setCompoundDrawables(drawable, null, null, null);
        return nav_foot;
    }

    private void initData() {

        bt_op_nav.setOnClickListener((v) -> openDrawer());

        tabLayout.setTabData(titles);
        //初始化Fragments数组
        mFragments.add(ContentYhFragment.newInstance(titles[0]));
        mFragments.add(ContentFxFragment.newInstance(titles[1]));

        //绑定ViewPager
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position, true);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        tabLayout.setCurrentTab(0);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        //绑定Toolbar的Layout文件
        View toolBarView = View.inflate(this, R.layout.main_toolbar, null);
        toolbarAddView(toolBarView, TOOLBAR_VIEW_MATCH);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
