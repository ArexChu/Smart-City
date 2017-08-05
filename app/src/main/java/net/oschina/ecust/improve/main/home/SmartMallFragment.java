package net.oschina.ecust.improve.main.home;

import android.support.v7.widget.GridLayoutManager;

import net.oschina.ecust.R;
import net.oschina.ecust.adapter.SmartCityAdapter;
import net.oschina.ecust.improve.base.adapter.BaseRecyclerAdapter;
import net.oschina.ecust.improve.base.fragments.BaseGeneralRecyclerFragment;
import net.oschina.ecust.improve.user.activities.UserMessageActivity;

import java.lang.reflect.Type;

/**
 * Created by thanatosx on 2016/11/7.
 */

public class SmartMallFragment extends BaseGeneralRecyclerFragment {
    private String[] itemNames = new String[]{
            "智慧家居", "陪伴机器人", "可佩戴产品",
            "辅助产品", "健康宝箱"
    };

    private int[] itemIcons = new int[]{
            R.drawable.smart_home, R.drawable.robot, R.drawable.wearable_products,
            R.drawable.ancillary_products, R.drawable.health_box,
    };

    private int[] itemTypes = new int[]{
            0, 0, 0,
            0, 0,
    };

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        return new SmartCityAdapter(getContext(),itemNames,itemIcons, itemTypes);
    }

    @Override
    protected Type getType() {
        return null;
    }

    @Override
    public void initData() {
        super.initData();
        mAdapter.setState(BaseRecyclerAdapter.STATE_HIDE, true);
        mRefreshLayout.setRefreshing(false);

        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (itemTypes[position] == 1) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void onItemClick(int position, long itemId) {
        super.onItemClick(position, itemId);
        switch (position) {
            case 0:
                UserMessageActivity.show(getActivity());
                break;
            default:
                break;
        }
    }

    @Override
    protected boolean isNeedEmptyView() {
        return false;
    }

    @Override
    public void onRefreshing() {
        mRefreshLayout.setEnabled(false);
    }

    @Override
    protected boolean isNeedCache() {
        return false;
    }
}
