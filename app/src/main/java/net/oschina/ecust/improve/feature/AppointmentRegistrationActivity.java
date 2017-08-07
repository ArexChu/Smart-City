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

    private ShakePresentFragment mPresentFragment;
    private ShakeNewsFragment mNewsFragment;

    private boolean mIsRegisterPresent;

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
        mNewsFragment = ShakeNewsFragment.newInstance();
        mPresentFragment = ShakePresentFragment.newInstance();
        addFragment(R.id.fl_content, mNewsFragment);
        addFragment(R.id.fl_content, mPresentFragment);
        setState(mLayShakePresent, true);
        mIsRegisterPresent = true;
    }

    @OnClick({R.id.ll_shake_present, R.id.ll_shake_news})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_shake_present) {
            addFragment(R.id.fl_content, mPresentFragment);
            mNewsFragment.unregisterSensor();
            setState(mLayShakeNews, false);
            setState(mLayShakePresent, true);
            mIsRegisterPresent = true;
        } else if (v.getId() == R.id.ll_shake_news) {
            addFragment(R.id.fl_content, mNewsFragment);
            mNewsFragment.registerSensor();
            setState(mLayShakePresent, false);
            setState(mLayShakeNews, true);
            mIsRegisterPresent = false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mNewsFragment.unregisterSensor();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mIsRegisterPresent) {
        } else {
            mNewsFragment.registerSensor();
        }
    }

    @Override
    public void finish() {
        super.finish();
        mNewsFragment.unregisterSensor();
    }

    private void setState(LinearLayout layout, boolean selected) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            layout.getChildAt(i).setSelected(selected);
        }
    }
}
