package example.com.zhuandian;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baronzhang.android.library.activity.ToolBarBaseActivity;

public class MyOrderActivity extends ToolBarBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_msg);

        MyOrderFragment myOrderFragment = MyOrderFragment.newInstance("我的订单");

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_person_msg, myOrderFragment)
                .commit();
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        View toolbar = LayoutInflater.from(this).inflate(R.layout.person_toolbar, null, false);
        toolbarAddView(toolbar, TOOLBAR_VIEW_MATCH);
        ImageButton button = (ImageButton) toolbar.findViewById(R.id.ib_back);
        button.setOnClickListener(v -> finish());
        TextView textView = (TextView) toolbar.findViewById(R.id.tv_right);
        textView.setVisibility(View.GONE);
        textView = (TextView) toolbar.findViewById(R.id.title);
        textView.setText("我的订单");
    }


}
