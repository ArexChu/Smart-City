package net.oschina.ecust.improve.feature;

import android.view.View;
import net.oschina.ecust.R;
import net.oschina.ecust.improve.base.fragments.BaseFragment;

/**
 * 摇一摇礼品相关实现
 */
public class ShakePresentFragment extends BaseFragment {

    public static ShakePresentFragment newInstance() {
        ShakePresentFragment fragment = new ShakePresentFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_appointment;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
    }
}
