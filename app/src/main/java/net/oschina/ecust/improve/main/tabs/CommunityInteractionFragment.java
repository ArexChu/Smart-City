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

public class CommunityInteractionFragment extends BaseGeneralRecyclerFragment {
    private String[] itemNames = new String[]{
            "政策驿站", "智慧科普", "帮扶社区",
            "儿童天地", "居家养老", "社区健身",
            "社区沙龙", "社区微中心", "社区义工",
            "小资书屋", "达医晓护"
    };

    private int[] itemIcons = new int[]{
            R.drawable.ic_category_t1, R.drawable.ic_category_t3, R.drawable.ic_category_t4,
            R.drawable.ic_category_t5, R.drawable.ic_category_t4, R.drawable.ic_category_t11,
            R.drawable.ic_category_t13, R.drawable.ic_category_t15, R.drawable.ic_category_t17,
            R.drawable.ic_category_t19, R.drawable.ic_category_t20,
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
