package net.oschina.ecust.improve.feature;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import net.oschina.ecust.R;
import net.oschina.ecust.api.remote.OSChinaApi;
import net.oschina.ecust.improve.bean.base.ResultBean;
import net.oschina.ecust.improve.bean.shake.ShakeNews;
import net.oschina.ecust.util.StringUtils;
import net.oschina.ecust.util.TDevice;

import java.lang.reflect.Type;

import butterknife.Bind;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * 摇一摇新闻咨询等相关界面实现
 */
public class InquireFragment extends BaseSensorFragment<ShakeNews> implements View.OnClickListener{
    @Bind(R.id.inquire)
    ImageView mImageView;

    private ImageView mImgNews;
    private TextView mTxtNewsName, mTxtPubTime;

    String url = "http://172.20.220.48:8080/inquire";
    //    String url = "http://192.168.1.170:8080/inquire";
    public static long size;

    public static InquireFragment newInstance() {
        InquireFragment fragment = new InquireFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_inquire;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mShakeView = mInflater.inflate(R.layout.view_news, null);
        mImgNews = (ImageView) mShakeView.findViewById(R.id.iv_news);
        mTxtNewsName = (TextView) mShakeView.findViewById(R.id.tv_news_name);
        mTxtPubTime = (TextView) mShakeView.findViewById(R.id.tv_time);
        mDelayTime = 1;
        mCardView.setVisibility(View.GONE);

        size = AppointmentFragment.size;
        mTvState.setText(String.format("前面等候还有%d个病人，估计还要%d分钟", size, size*5));
    }

    @OnClick({R.id.inquire})

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.inquire:

                AsyncHttpClient client = new AsyncHttpClient();
                client.get(url, new TextHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, String res) {
                                // called when response HTTP status is "200 OK"

                                Gson gson = new Gson();
                                Greeting greeting = gson.fromJson(res, Greeting.class);
                                size = greeting.size;

                                mTvState.setText(String.format("前面等候还有%d个病人，估计还要%d分钟", size, size*5));
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                            }
                        }
                );
                break;
            default:
                break;
        }
    }

    @Override
    public void onShake() {
        if (!TDevice.hasInternet()) {
            Toast.makeText(mContext, "网络连接失败", Toast.LENGTH_SHORT).show();
            mLoading = false;
            return;
        }
        OSChinaApi.getShakeNews(mHandler);
    }

    @Override
    protected void initShakeView() {
        ShakeNews news = mBean.getResult();
        mCardView.setVisibility(View.VISIBLE);
        getImgLoader().load(news.getImg())
                .placeholder(R.mipmap.ic_split_graph)
                .into(mImgNews);
        mTxtNewsName.setText(news.getName());
        mTxtPubTime.setText(StringUtils.formatSomeAgo(news.getPubDate()));
    }

    @Override
    protected void onRequestStart() {
        super.onRequestStart();
        mTvState.setText("正在搜寻资讯");
    }

    @Override
    protected void onTimeProgress() {
        if (mContext != null) {
            if (mTimeHandler == null)
                mTimeHandler = new Handler();
            mLoadingView.setVisibility(View.GONE);
            //mTxtTime.setVisibility(View.VISIBLE);
            //mTxtTime.setText(String.format("%d秒后可再摇一次", mDelayTime));
            mTimeHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mTxtTime == null)
                        return;
                    mTxtTime.setVisibility(View.VISIBLE);
                    --mDelayTime;
                    if (mTxtTime == null)
                        return;
                    //mTxtTime.setText(String.format("%d秒后可再摇一次", mDelayTime));
                    if (mDelayTime > 0)
                        mTimeHandler.postDelayed(this, 1000);
                    else {
                        mTxtTime.setVisibility(View.INVISIBLE);
                        mTvState.setVisibility(View.VISIBLE);
                        mTvState.setText("摇一摇获取资讯");
                        mLoading = false;
                        mDelayTime = 1;
                    }
                }
            }, 1000);
        }
    }

    @Override
    protected void onFailure() {
        super.onFailure();
        mTvState.setText("很遗憾，你没有摇到资讯，请再试一次");
    }

    @Override
    protected Type getType() {
        return new TypeToken<ResultBean<ShakeNews>>() {
        }.getType();
    }
}
