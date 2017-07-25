package net.oschina.ecust.improve.main.tabs;

import android.support.v7.widget.GridLayoutManager;

import net.oschina.ecust.R;
import net.oschina.ecust.adapter.TopicTweetTestAdapter;
import net.oschina.ecust.improve.base.adapter.BaseRecyclerAdapter;
import net.oschina.ecust.improve.base.fragments.BaseGeneralRecyclerFragment;
import net.oschina.ecust.improve.tweet.adapter.TopicTweetAdapter;
import net.oschina.ecust.improve.user.collection.UserCollectionActivity;

import java.lang.reflect.Type;

/**
 * Created by thanatosx on 2016/11/7.
 */

public class SmartHealthFragment extends BaseGeneralRecyclerFragment {
    private String[] itemNames = new String[]{
            "小慧在线", "预约挂号", "就诊记录",
            "检验报告", "用药明细", "健康档案",
            "住院病史", "费用明细", "健康评估",
            "中医辨识", "慢病管理", "远程医疗"
    };

    private int[] itemIcons = new int[]{
            R.drawable.ic_category_t1, R.drawable.ic_category_t3, R.drawable.ic_category_t4,
            R.drawable.ic_category_t5, R.drawable.ic_category_t4, R.drawable.ic_category_t11,
            R.drawable.ic_category_t13, R.drawable.ic_category_t15, R.drawable.ic_category_t17,
            R.drawable.ic_category_t19, R.drawable.ic_category_t20, R.drawable.ic_category_t21,
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
