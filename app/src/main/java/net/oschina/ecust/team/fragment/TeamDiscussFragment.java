package net.oschina.ecust.team.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import net.oschina.ecust.api.remote.OSChinaTeamApi;
import net.oschina.ecust.base.BaseListFragment;
import net.oschina.ecust.bean.ListEntity;
import net.oschina.ecust.team.adapter.TeamDiscussAdapter;
import net.oschina.ecust.team.bean.Team;
import net.oschina.ecust.team.bean.TeamDiscuss;
import net.oschina.ecust.team.bean.TeamDiscussList;
import net.oschina.ecust.team.ui.TeamMainActivity;
import net.oschina.ecust.util.StringUtils;
import net.oschina.ecust.util.UIHelper;
import net.oschina.ecust.util.XmlUtils;

import java.io.InputStream;
import java.io.Serializable;

/**
 * team讨论区列表界面
 *
 * @author fireant(http://my.oschina.net/u/253900)
 */
public class TeamDiscussFragment extends BaseListFragment<TeamDiscuss> {

    protected static final String TAG = TeamDiscussFragment.class
            .getSimpleName();
    private static final String CACHE_KEY_PREFIX = "team_discuss_list_";

    private Team mTeam;

    private int mTeamId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Team team = (Team) bundle
                    .getSerializable(TeamMainActivity.BUNDLE_KEY_TEAM);
            if (team != null) {
                mTeam = team;
                mTeamId = StringUtils.toInt(mTeam.getId());
            }
        }
    }

    @Override
    protected TeamDiscussAdapter getListAdapter() {
        return new TeamDiscussAdapter();
    }

    /**
     * 获取当前展示页面的缓存数据
     */
    @Override
    protected String getCacheKeyPrefix() {
        return CACHE_KEY_PREFIX + mTeamId + "_" + mCurrentPage;
    }

    @Override
    protected TeamDiscussList parseList(InputStream is)
            throws Exception {
        TeamDiscussList list = XmlUtils.toBean(
                TeamDiscussList.class, is);
        return list;
    }

    @Override
    protected ListEntity<TeamDiscuss> readList(Serializable seri) {
        return ((TeamDiscussList) seri);
    }

    @Override
    protected void sendRequestData() {
        OSChinaTeamApi.getTeamDiscussList("new", mTeamId, 0, mCurrentPage,
                mHandler);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        TeamDiscuss item = (TeamDiscuss) mAdapter.getItem(position);
        if (item != null) {
            UIHelper.showTeamDiscussDetail(getActivity(), mTeam, item);
        }

    }

}