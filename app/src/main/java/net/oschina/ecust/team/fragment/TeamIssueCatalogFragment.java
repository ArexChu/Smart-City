package net.oschina.ecust.team.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import net.oschina.ecust.api.remote.OSChinaTeamApi;
import net.oschina.ecust.base.BaseListFragment;
import net.oschina.ecust.bean.SimpleBackPage;
import net.oschina.ecust.improve.account.AccountHelper;
import net.oschina.ecust.team.adapter.TeamIssueCatalogAdapter;
import net.oschina.ecust.team.bean.Team;
import net.oschina.ecust.team.bean.TeamIssueCatalog;
import net.oschina.ecust.team.bean.TeamIssueCatalogList;
import net.oschina.ecust.team.bean.TeamProject;
import net.oschina.ecust.team.ui.TeamMainActivity;
import net.oschina.ecust.util.UIHelper;
import net.oschina.ecust.util.XmlUtils;

import java.io.InputStream;
import java.io.Serializable;

/**
 * 任务分组列表
 * <p>
 * TeamIssueCatalogFragment.java
 *
 * @author 火蚁(http://my.oschina.net/u/253900)
 * @data 2015-3-1 下午3:36:03
 */
public class TeamIssueCatalogFragment extends
        BaseListFragment<TeamIssueCatalog> {

    private Team mTeam;

    private TeamProject mTeamProject;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mTeam = (Team) args.getSerializable(TeamMainActivity.BUNDLE_KEY_TEAM);
            mTeamProject = (TeamProject) args.getSerializable(TeamMainActivity.BUNDLE_KEY_PROJECT);
        }
    }

    @Override
    protected TeamIssueCatalogAdapter getListAdapter() {
        // TODO Auto-generated method stub
        return new TeamIssueCatalogAdapter();
    }

    @Override
    protected TeamIssueCatalogList parseList(InputStream is)
            throws Exception {
        return XmlUtils.toBean(TeamIssueCatalogList.class, is);
    }

    @Override
    protected TeamIssueCatalogList readList(Serializable seri) {
        return (TeamIssueCatalogList) seri;
    }

    @Override
    protected void sendRequestData() {
        int uid = (int) AccountHelper.getUserId();
        int teamId = mTeam.getId();
        int projectId = mTeamProject.getGit().getId();
        String source = mTeamProject.getSource();
        OSChinaTeamApi.getTeamCatalogIssueList(uid, teamId, projectId, source, mHandler);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        TeamIssueCatalog teamIssueCatalog = mAdapter.getItem(position);
        if (teamIssueCatalog != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(TeamMainActivity.BUNDLE_KEY_TEAM, mTeam);
            bundle.putSerializable(TeamMainActivity.BUNDLE_KEY_PROJECT, mTeamProject);
            bundle.putSerializable(TeamMainActivity.BUNDLE_KEY_ISSUE_CATALOG, teamIssueCatalog);
            UIHelper.showSimpleBack(getActivity(), SimpleBackPage.TEAM_ISSUECATALOG_ISSUE_LIST, bundle);
        }
    }

    @Override
    protected String getCacheKeyPrefix() {
        // TODO Auto-generated method stub
        return "team_issue_catalog_list" + mTeam.getId() + "_" + mTeamProject.getGit().getId();
    }
}

