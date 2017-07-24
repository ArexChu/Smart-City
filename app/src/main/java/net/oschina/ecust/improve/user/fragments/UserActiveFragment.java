package net.oschina.ecust.improve.user.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.google.gson.reflect.TypeToken;

import net.oschina.ecust.api.remote.OSChinaApi;
import net.oschina.ecust.improve.base.adapter.BaseRecyclerAdapter;
import net.oschina.ecust.improve.base.fragments.BaseRecyclerViewFragment;
import net.oschina.ecust.improve.bean.Active;
import net.oschina.ecust.improve.bean.base.PageBean;
import net.oschina.ecust.improve.bean.base.ResultBean;
import net.oschina.ecust.improve.bean.simple.Origin;
import net.oschina.ecust.improve.detail.general.BlogDetailActivity;
import net.oschina.ecust.improve.detail.general.EventDetailActivity;
import net.oschina.ecust.improve.detail.general.NewsDetailActivity;
import net.oschina.ecust.improve.detail.general.QuestionDetailActivity;
import net.oschina.ecust.improve.detail.general.SoftwareDetailActivity;
import net.oschina.ecust.improve.tweet.activities.TweetDetailActivity;
import net.oschina.ecust.improve.user.adapter.UserActiveAdapter;
import net.oschina.ecust.util.UIHelper;

import java.lang.reflect.Type;


/**
 * 某用户的动态(讨论)列表
 * Created by thanatos on 16/8/16.
 */
public class UserActiveFragment extends BaseRecyclerViewFragment<Active> {

    public static final String BUNDLE_KEY_USER_ID = "BUNDLE_KEY_USER_ID";

    private long uid;

    public static Fragment instantiate(Long uid) {
        Bundle bundle = new Bundle();
        bundle.putLong(BUNDLE_KEY_USER_ID, uid);
        Fragment fragment = new UserActiveFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initBundle(Bundle bundle) {
        super.initBundle(bundle);
        uid = bundle.getLong(BUNDLE_KEY_USER_ID);
    }

    @Override
    protected BaseRecyclerAdapter<Active> getRecyclerAdapter() {
        return new UserActiveAdapter(getContext());
    }

    @Override
    protected Type getType() {
        return new TypeToken<ResultBean<PageBean<Active>>>() {
        }.getType();
    }

    @Override
    protected void requestData() {
        String token = isRefreshing ? null : mBean.getNextPageToken();
        OSChinaApi.getUserActives(uid, token, mHandler);
    }

    @Override
    public void onItemClick(int position, long itemId) {
        Origin origin = mAdapter.getItem(position).getOrigin();
        if (origin == null)
            return;
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
                // pass
        }
    }

    @Override
    protected boolean isNeedCache() {
        return false;
    }

    @Override
    protected boolean isNeedEmptyView() {
        return false;
    }
}
