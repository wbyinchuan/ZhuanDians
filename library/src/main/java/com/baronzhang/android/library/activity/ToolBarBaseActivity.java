package com.baronzhang.android.library.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.baronzhang.android.library.R;

public class ToolBarBaseActivity extends BaseActivity {

    public static final int TOOLBAR_VIEW_LEFT    = 0;
    public static final int TOOLBAR_VIEW_RIGHT   = 1;
    public static final int TOOLBAR_VIEW_MID     = 2;
    public static final int TOOLBAR_VIEW_MATCH   = 3;

    protected Toolbar toolbar;
    protected View toolbarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //状态栏透明方法2
        Window w = getWindow();
        w.requestFeature(Window.FEATURE_NO_TITLE);
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        // 这句很关键，注意是调用父类的方法
        super.setContentView(getLayoutID());
        initToolbar();
    }

    protected
    @LayoutRes
    int getLayoutID() {
        return R.layout.app_bar_main;
    }

    protected void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        //添加Toolbar layout
        View toolbarView = inflateView(R.layout.toolbar);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        toolbarView.setLayoutParams(layoutParams);
        toolbar.addView(toolbarView);

    }

    protected void toolbarAddView(@LayoutRes int layout, int gravity) {
        View leftView = inflateView(layout);
        toolbarAddView(leftView, gravity);
    }

    protected void toolbarAddView(View view,int gravity) {

        switch (gravity) {
            case TOOLBAR_VIEW_LEFT:
                addViewToGroup(view, R.id.view_group_left);
                break;
            case TOOLBAR_VIEW_RIGHT:
                addViewToGroup(view, R.id.view_group_right);
                break;
            case TOOLBAR_VIEW_MID:
                addViewToGroup(view, R.id.view_group_mid);
                break;
            case TOOLBAR_VIEW_MATCH:
                addViewToGroup(view, R.id.view_group_match);
                toolbarView = view;
                break;
            default:
                throw new IllegalArgumentException("gravity参数不合法");
        }
    }

    public View getToobarView() {
        return toolbarView;
    }

    private void addViewToGroup(View view, int id) {
        FrameLayout frameLayout = (FrameLayout) findViewById(id);
        frameLayout.addView(view);
    }


    @NonNull
    private ViewGroup.LayoutParams getLayoutParams() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
    }
    private View inflateView(@LayoutRes int layout) {
        return LayoutInflater.from(this).inflate(layout, toolbar, false);
    }


    @Override
    public void setContentView(int layoutId) {
        ViewStub content = (ViewStub) findViewById(R.id.content_stub);
        content.setLayoutResource(layoutId);
        content.inflate();
    }

    @Override
    public void setContentView(View view) {
//        rootLayout = (LinearLayout) findViewById(R.id.root_layout);
//        if (rootLayout == null) return;
//        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        initToolbar();
    }
}
