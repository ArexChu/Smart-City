package net.oschina.ecust.improve.user.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.google.gson.reflect.TypeToken;

import net.oschina.ecust.api.remote.OSChinaApi;
import net.oschina.ecust.improve.base.adapter.BaseRecyclerAdapter;
import net.oschina.ecust.improve.base.fragments.BaseRecyclerViewFragment;
import net.oschina.ecust.improve.bean.SubBean;
import net.oschina.ecust.improve.bean.base.PageBean;
import net.oschina.ecust.improve.bean.base.ResultBean;
import net.oschina.ecust.improve.detail.general.BlogDetailActivity;
import net.oschina.ecust.improve.main.subscription.BlogSubAdapter;

import java.lang.reflect.Type;

/**
 * created by thanatosx  on 2016/8/16.
 */
public class UserBlogFragment extends BaseRecyclerViewFragment<SubBean> {

    public static final String HISTORY_BLOG = "history_my_blog";
    public static final String BUNDLE_KEY_USER_ID = "BUNDLE_KEY_USER_ID";
    private long userId;

    public static Fragment instantiate(long uid) {
        Bundle bundle = new Bundle();
        bundle.putLong(BUNDLE_KEY_USER_ID, uid);
        Fragment fragment = new UserBlogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initBundle(Bundle bundle) {
        super.initBundle(bundle);
        userId = bundle.getLong(BUNDLE_KEY_USER_ID);
    }

    @Override
    protected void requestData() {
        super.requestData();
        String token = isRefreshing ? null : mBean.getNextPageToken();
        OSChinaApi.getSomeoneBlogs(token, userId, null, mHandler);
    }

    @Override
    protected BaseRecyclerAdapter<SubBean> getRecyclerAdapter() {
        return new BlogSubAdapter(getContext(), BaseRecyclerAdapter.ONLY_FOOTER);
    }

    @Override
    protected Type getType() {
        return new TypeToken<ResultBean<PageBean<SubBean>>>() {
        }.getType();
    }

    @Override
    protected boolean isNeedCache() {
        return false;
    }

    @Override
    protected boolean isNeedEmptyView() {
        return false;
    }

    @Override
    public void onItemClick(int position, long itemId) {
        SubBean blog = mAdapter.getItem(position);
        if (blog == null) return;
        BlogDetailActivity.show(getActivity(), blog.getId());
    }
}