package net.oschina.ecust.improve.main.tabs;

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

public class CommunityInteractionFragment extends BaseGeneralRecyclerFragment {
    private String[] itemNames = new String[]{
            "-社群互动",
            "政策驿站", "中国上海", "上海市民政政策宣传",
            "-智慧科普",
            "达医晓护", "糖尿病", "健康中国",
            "-帮扶社区",
            "互助小组", "社区义工",
            "-儿童天地",
            "亲子乐园",
            "-居家养老",
            "居家照料", "养老服务",
            "-社区沙龙",
            "微中心", "生活", "摄影",
            "书画",
            "-小资书屋",
            "社区分图书馆",
    };

    private int[] itemIcons = new int[]{
            R.drawable.community_interaction,
            R.drawable.policy_inn, R.drawable.shanghai_china, R.drawable.public_policy_in_shanghai,
            R.drawable.intelligent_science,
            R.drawable.xiao_yi_da_hu, R.drawable.diabetes, R.drawable.health_china,
            R.drawable.helping_communities,
            R.drawable.each_help_group, R.drawable.community_social_worker,
            R.drawable.children_world,
            R.drawable.parental_paradise,
            R.drawable.community_nursing,
            R.drawable.community_care, R.drawable.elder_service,
            R.drawable.community_salon,
            R.drawable.micro_medical_center, R.drawable.livelihood, R.drawable.photography,
            R.drawable.calligraphy_and_painting,
            R.drawable.bookstore,
            R.drawable.community_sublibrary,
    };

    private int[] itemTypes = new int[]{
            1,
            0, 0, 0,
            1,
            0, 0, 0,
            1,
            0, 0,
            1,
            0,
            1,
            0, 0,
            1,
            0, 0, 0,
            0,
            1,
            0,
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
