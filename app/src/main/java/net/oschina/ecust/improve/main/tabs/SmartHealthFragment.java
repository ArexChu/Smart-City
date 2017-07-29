package net.oschina.ecust.improve.main.tabs;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import net.oschina.ecust.R;
import net.oschina.ecust.adapter.SmartCityAdapter;
import net.oschina.ecust.improve.base.adapter.BaseRecyclerAdapter;
import net.oschina.ecust.improve.base.fragments.BaseGeneralRecyclerFragment;
import net.oschina.ecust.improve.user.activities.UserMessageActivity;
import net.oschina.ecust.util.UIHelper;

import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * Created by thanatosx on 2016/11/7.
 */

public class SmartHealthFragment extends BaseGeneralRecyclerFragment {
    private String[] itemNames = new String[]{
            "-预约挂号",
            "医院官网", "微医", "好大夫在线",
            "-健康档案",
            "体检信息", "微健康",
            "-健康评估",
            "风险评估", "疾病预测",
            "-慢病管理",
            "分级诊疗",
            "-远程医疗",
            "上海市第六人民医院图书馆", "徐汇云医院", "远程问询",
            "远程医疗", "远程会诊",
            "-其它",
            "小慧在线", "就诊记录", "检验报告",
            "用药明细", "住院病史", "费用明细",
            "中医辨识",
    };

    private int[] itemIcons = new int[]{
            R.drawable.appointment_registration,
            R.drawable.hospital_official_website, R.drawable.micro_hospital, R.drawable.good_doctor_online,
            R.drawable.health_document,
            R.drawable.physical_information, R.drawable.micro_health,
            R.drawable.health_assessment,
            R.drawable.risk_assessment, R.drawable.diseases_prediction,
            R.drawable.chronic_diseases_management,
            R.drawable.graded_diagnosis,
            R.drawable.telemedicine,
            R.drawable.sixth_hospital, R.drawable.xuhui_cloud_hospital, R.drawable.remote_consultation,
            R.drawable.telemedicine, R.drawable.remote_consultation2,
            R.drawable.expand,
            R.drawable.online_xiaohui, R.drawable.medical_records, R.drawable.inspection,
            R.drawable.medical_details, R.drawable.medical_history, R.drawable.cost_details,
            R.drawable.chinese_medicine,
    };

    private int[] itemTypes = new int[]{
            1,
            0, 0, 0,
            1,
            0, 0,
            1,
            0, 0,
            1,
            0,
            1,
            0, 0, 0,
            0, 0,
            1,
            0, 0, 0,
            0, 0, 0,
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
            case 1:
                UIHelper.openInternalBrowser(getActivity(), "http://www.6thhosp.com");
                break;
            case 2:
                UIHelper.openInternalBrowser(getActivity(), "http://www.guahao.com");
                break;
            case 3:
                UIHelper.openInternalBrowser(getActivity(), "http://www.haodf.com/");
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
