package net.oschina.ecust.improve.user.fragments;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import net.oschina.ecust.api.remote.OSChinaApi;
import net.oschina.ecust.improve.base.adapter.BaseRecyclerAdapter;
import net.oschina.ecust.improve.base.fragments.BaseRecyclerViewFragment;
import net.oschina.ecust.improve.bean.Mention;
import net.oschina.ecust.improve.bean.base.PageBean;
import net.oschina.ecust.improve.bean.base.ResultBean;
import net.oschina.ecust.improve.bean.simple.Origin;
import net.oschina.ecust.improve.detail.general.BlogDetailActivity;
import net.oschina.ecust.improve.detail.general.EventDetailActivity;
import net.oschina.ecust.improve.detail.general.NewsDetailActivity;
import net.oschina.ecust.improve.detail.general.QuestionDetailActivity;
import net.oschina.ecust.improve.detail.general.SoftwareDetailActivity;
import net.oschina.ecust.improve.tweet.activities.TweetDetailActivity;
import net.oschina.ecust.improve.user.activities.UserMessageActivity;
import net.oschina.ecust.improve.user.adapter.UserMentionAdapter;
import net.oschina.ecust.improve.widget.SimplexToast;
import net.oschina.ecust.util.UIHelper;

import java.lang.reflect.Type;

/**
 * Created by huanghaibin_dev
 * on 2016/8/16.
 */

public class UserMentionFragment extends BaseRecyclerViewFragment<Mention> {

    private UserMessageActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null && context instanceof UserMessageActivity) {
            activity = (UserMessageActivity) context;
        }
    }

    @Override
    protected void onRequestSuccess(int code) {
        super.onRequestSuccess(code);
        if (activity != null && isRefreshing) activity.onRequestSuccess(0);
    }

    @Override
    protected void requestData() {
        super.requestData();
        OSChinaApi.getMsgMentionList(isRefreshing ? null : mBean.getNextPageToken(), mHandler);
    }

    @Override
    public void onItemClick(int position, long itemId) {
        Mention mention = mAdapter.getItem(position);
        if (mention == null) return;
        Origin origin = mention.getOrigin();
        if (origin == null) return;
        switch (origin.getType()) {
            case Origin.ORIGIN_TYPE_LINK:
                UIHelper.showUrlRedirect(getContext(), origin.getHref());
                break;
            case Origin.ORIGIN_TYPE_SOFTWARE:
                SoftwareDetailActivity.show(getContext(), origin.getId());
                break;
            case Origin.ORIGIN_TYPE_DISCUSS:
                QuestionDetailActivity.show(getContext(), origin.getId());
                break;
            case Origin.ORIGIN_TYPE_BLOG:
                BlogDetailActivity.show(getContext(), origin.getId());
                break;
            case Origin.ORIGIN_TYPE_TRANSLATION:
                NewsDetailActivity.show(getContext(), origin.getId());
                break;
            case Origin.ORIGIN_TYPE_ACTIVE:
                EventDetailActivity.show(getContext(), origin.getId());
                break;
            case Origin.ORIGIN_TYPE_NEWS:
                NewsDetailActivity.show(getContext(), origin.getId());
                break;
            case Origin.ORIGIN_TYPE_TWEETS:
                TweetDetailActivity.show(getContext(), origin.getId());
                break;
            default:
                SimplexToast.show(getContext(), "不支持该类型");
        }
    }

    @Override
    protected BaseRecyclerAdapter<Mention> getRecyclerAdapter() {
        return new UserMentionAdapter(this);
    }

    @Override
    protected Type getType() {
        return new TypeToken<ResultBean<PageBean<Mention>>>() {
        }.getType();
    }
}
