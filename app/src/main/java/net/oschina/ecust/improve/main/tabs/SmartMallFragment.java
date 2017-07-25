package net.oschina.ecust.improve.main.tabs;

import android.support.v7.widget.GridLayoutManager;

import net.oschina.ecust.R;
import net.oschina.ecust.adapter.TopicTweetTestAdapter;
import net.oschina.ecust.improve.base.adapter.BaseRecyclerAdapter;
import net.oschina.ecust.improve.base.fragments.BaseGeneralRecyclerFragment;
import net.oschina.ecust.improve.user.collection.UserCollectionActivity;

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
            R.drawable.ic_category_t1, R.drawable.ic_category_t3, R.drawable.ic_category_t4,
            R.drawable.ic_category_t5, R.drawable.ic_category_t4,
    };

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        return new TopicTweetTestAdapter(getContext(),itemNames,itemIcons);
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
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    }

    @Override
    public void onItemClick(int position, long itemId) {
        super.onItemClick(position, itemId);
        switch (position) {
            case 0:
                //直播
                UserCollectionActivity.show(getActivity());
                break;
            case 1:
                //番剧
                break;
            case 2:
                //动画
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

    }

    @Override
    protected boolean isNeedCache() {
        return false;
    }
}
