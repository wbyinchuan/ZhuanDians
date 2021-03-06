package example.com.zhuandian;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baronzhang.android.library.activity.ToolBarBaseActivity;

public class EmailMsgActivity extends ToolBarBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_msg);

        EmailMsgFragment personMsgFragment = EmailMsgFragment.newInstance("个人信息");

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.hold, R.anim.hold, R.anim.slide_out_right)
                .replace(R.id.content_person_msg, personMsgFragment)
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
        textView.setText("我的消息");
    }
}
