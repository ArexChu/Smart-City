package net.oschina.ecust.improve.main.tabs;

import android.support.v7.widget.GridLayoutManager;

import net.oschina.ecust.R;
import net.oschina.ecust.adapter.SmartCityAdapter;
import net.oschina.ecust.improve.base.adapter.BaseRecyclerAdapter;
import net.oschina.ecust.improve.base.fragments.BaseGeneralRecyclerFragment;
import net.oschina.ecust.improve.user.activities.UserMessageActivity;
import net.oschina.ecust.util.UIHelper;

import java.lang.reflect.Type;

/**
 * Created by thanatosx on 2016/11/7.
 */

public class SmartCommunityFragment extends BaseGeneralRecyclerFragment {
    private String[] itemNames = new String[]{
            "-家庭医生",
            "签约服务", "常规问诊", "定期上门",
            "慢病随访",
            "-延伸处方",
            "指定药房", "配送服务", "定点取药",
            "-爱心志愿",
            "六院爱心", "社区工作","党员义工",
            "-社区环境",
            "空气检测","天气预报","地理信息",
            "-绿色饮食",
            "营养推荐", "食品健康",
            "-在线健康",
            "在线咨询", "科普推广",
            "-免费站点",
            "健康小屋", "社区服务中心",
    };

    private int[] itemIcons = new int[]{
            R.drawable.family_physician,
            R.drawable.signing_service, R.drawable.regular_inquiry, R.drawable.indoor_service,
            R.drawable.follow_up_of_chronic_diseases,
            R.drawable.extended_prescription,
            R.drawable.designated_pharmacy, R.drawable.delivery_service_distribution_services, R.drawable.site_specific_drug_delivery,
            R.drawable.love_volunteer,
            R.drawable.love_social_worker, R.drawable.community_work, R.drawable.communist_party_membership_social_worker,
            R.drawable.community_environment,
            R.drawable.air_index, R.drawable.weather, R.drawable.geographic,
            R.drawable.green_diet,
            R.drawable.nutrition, R.drawable.food_health,
            R.drawable.online_health,
            R.drawable.online_consultation, R.drawable.science_popularization,
            R.drawable.free_site,
            R.drawable.health_house, R.drawable.community_service_center,
    };

    private int[] itemTypes = new int[]{
            1,
            0, 0, 0,
            0,
            1,
            0, 0, 0,
            1,
            0, 0, 0,
            1,
            0, 0, 0,
            1,
            0, 0,
            1,
            0, 0,
            1,
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
