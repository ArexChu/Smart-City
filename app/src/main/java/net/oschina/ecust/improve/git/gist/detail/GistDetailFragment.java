package net.oschina.ecust.improve.git.gist.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.oschina.ecust.R;
import net.oschina.ecust.improve.base.fragments.BaseFragment;
import net.oschina.ecust.improve.dialog.ShareDialog;
import net.oschina.ecust.improve.git.bean.CodeDetail;
import net.oschina.ecust.improve.git.bean.Gist;
import net.oschina.ecust.improve.git.gist.comment.GistCommentActivity;
import net.oschina.ecust.improve.git.utils.MarkdownUtils;
import net.oschina.ecust.improve.git.utils.SourceEditor;
import net.oschina.ecust.util.HTMLUtil;
import net.oschina.ecust.util.StringUtils;

import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 代码片段详情
 * Created by haibin on 2017/5/10.
 */

public class GistDetailFragment extends BaseFragment implements GistDetailContract.View, View.OnClickListener {
    private SourceEditor mEditor;
    @Bind(R.id.webView)
    WebView mWebView;
    @Bind(R.id.tv_file_name)
    TextView mTextFileName;
    @Bind(R.id.tv_start_count)
    TextView mTexStartCount;
    @Bind(R.id.tv_fork_count)
    TextView mTextForkCount;
    @Bind(R.id.tv_description)
    TextView mTextDescription;
    @Bind(R.id.tv_language)
    TextView mTextLanguage;
    @Bind(R.id.tv_last_update)
    TextView mTextLastUpdate;
    @Bind(R.id.tv_comment_count)
    TextView mTextCommentCount;
    @Bind(R.id.ll_file_info)
    LinearLayout mLinearInfo;
    @Bind(R.id.ll_category)
    LinearLayout mLinearCategory;
    @Bind(R.id.line1)
    View mLine1;
    @Bind(R.id.line2)
    View mLine2;
    @Bind(R.id.ll_tool)
    LinearLayout mLinearTool;
    private Gist mGist;
    private ShareDialog mAlertDialog;
    static GistDetailFragment newInstance(Gist gist) {
        GistDetailFragment fragment = new GistDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("gist", gist);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gist_detail;
    }


    @Override
    protected void initBundle(Bundle bundle) {
        super.initBundle(bundle);
        mGist = (Gist) bundle.getSerializable("gist");
    }

    @SuppressWarnings("MalformedFormatString")
    @SuppressLint("DefaultLocale")
    private void init(Gist gist) {
        assert gist != null;
        mTextFileName.setText(String.format("%s / %s", gist.getOwner().getName(), gist.getName()));
        mTexStartCount.setText(String.valueOf(gist.getStartCounts()));
        mTextForkCount.setText(String.valueOf(gist.getForkCounts()));
        mTextDescription.setText(gist.getDescription());
        mTextLanguage.setText(gist.getLanguage());
        if (gist.getLastUpdateDate() != null) {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            mTextLastUpdate.setText(String.format("最后更新于%s", StringUtils.formatSomeAgo(dateFormat.format(gist.getLastUpdateDate()))));
        }
        mTextDescription.setVisibility(TextUtils.isEmpty(gist.getDescription()) ? View.GONE : View.VISIBLE);
        mTextLanguage.setVisibility(TextUtils.isEmpty(gist.getLanguage()) ? View.GONE : View.VISIBLE);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultFontSize(10);
        settings.setAllowContentAccess(true);
        mWebView.setWebChromeClient(new WebChromeClient() {
        });
        mEditor = new SourceEditor(mWebView);
        init(mGist);
    }

    @OnClick({R.id.ll_comment, R.id.ll_share})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_comment:
                GistCommentActivity.show(mContext, mGist);
                break;
            case R.id.ll_share:
                toShare();
                break;
        }
    }

    @Override
    public void setPresenter(GistDetailContract.Presenter presenter) {

    }

    @Override
    public void showNetworkError(int strId) {

    }

    @Override
    public void showGetDetailSuccess(Gist gist, int strId) {
        mGist = gist;
        init(gist);
        mEditor.setMarkdown(MarkdownUtils.isMarkdown(gist.getName()));
        CodeDetail detail = new CodeDetail();
        detail.setContent(gist.getContent());
        mEditor.setSource(gist.getName(), detail);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAlertDialog != null)
            mAlertDialog.dismiss();
    }

    @Override
    public void showGetCommentCountSuccess(int count) {
        mTextCommentCount.setText(String.format("评论（%s）", count));
    }

    @Override
    public void showLandscape() {
        mLinearInfo.setVisibility(View.GONE);
        mLinearCategory.setVisibility(View.GONE);
        mLinearTool.setVisibility(View.GONE);
        mLine1.setVisibility(View.GONE);
        mLine2.setVisibility(View.GONE);
        mTextDescription.setVisibility(View.GONE);
    }

    @Override
    public void showPortrait() {
        mLinearInfo.setVisibility(View.VISIBLE);
        mLinearCategory.setVisibility(View.VISIBLE);
        mLinearTool.setVisibility(View.VISIBLE);
        mLine1.setVisibility(View.VISIBLE);
        mLine2.setVisibility(View.VISIBLE);
        mTextDescription.setVisibility(View.VISIBLE);
    }

    private boolean toShare() {
        String content = mGist.getDescription().trim();
        if (content.length() > 55) {
            content = HTMLUtil.delHTMLTag(content);
            if (content.length() > 55)
                content = StringUtils.getSubString(0, 55, content);
        } else {
            content = HTMLUtil.delHTMLTag(content);
        }
        if (TextUtils.isEmpty(content))
            content = "";

        // 分享
        if (mAlertDialog == null) {
            mAlertDialog = new
                    ShareDialog(getActivity())
                    .title(mGist.getOwner().getName() + "/" + mGist.getName())
                    .content(content)
                    .url(mGist.getUrl())
                    .bitmapResID(R.mipmap.ic_git)
                    .with();
        }
        mAlertDialog.show();

        return true;
    }
}
