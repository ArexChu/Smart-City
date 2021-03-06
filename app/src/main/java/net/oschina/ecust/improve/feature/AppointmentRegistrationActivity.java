package net.oschina.ecust.improve.feature;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import net.oschina.ecust.R;
import net.oschina.ecust.improve.base.activities.BaseBackActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 摇一摇活动界面
 */
public class AppointmentRegistrationActivity extends BaseBackActivity implements View.OnClickListener {

    @Bind(R.id.ll_shake_present)
    LinearLayout mLayShakePresent;

    @Bind(R.id.ll_shake_news)
    LinearLayout mLayShakeNews;

    private AppointmentFragment mPresentFragment;
    private InquireFragment mNewsFragment;


    public static void show(Context context) {
        context.startActivity(new Intent(context, AppointmentRegistrationActivity.class));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_appointment_registration;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
//        mNewsFragment = InquireFragment.newInstance();
        mPresentFragment = AppointmentFragment.newInstance();
//        addFragment(R.id.fl_content, mNewsFragment);
        addFragment(R.id.fl_content, mPresentFragment);
    }

    @OnClick({R.id.ll_shake_present, R.id.ll_shake_news})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_shake_present) {
            addFragment(R.id.fl_content, mPresentFragment);
        } else if (v.getId() == R.id.ll_shake_news) {
            mNewsFragment = InquireFragment.newInstance();

//            replaceFragment(R.id.fl_content, mNewsFragment);
            addFragment(R.id.fl_content, mNewsFragment);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
