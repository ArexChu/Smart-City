package net.oschina.ecust.improve.git.feature;

import android.content.Context;
import android.content.Intent;

import net.oschina.ecust.R;
import net.oschina.ecust.improve.base.activities.BaseBackActivity;

/**
 * Created by haibin
 * on 2017/3/9.
 */

public class FeatureActivity extends BaseBackActivity {

    public static void show(Context context) {
        context.startActivity(new Intent(context, FeatureActivity.class));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_feature;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        FeatureFragment fragment = FeatureFragment.newInstance();
        new FeaturePresenter(fragment);
        addFragment(R.id.fl_content, fragment);
    }
}
