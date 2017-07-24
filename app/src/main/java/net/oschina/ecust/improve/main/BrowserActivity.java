package net.oschina.ecust.improve.main;

import net.oschina.ecust.R;
import net.oschina.ecust.improve.base.activities.BaseActivity;
import net.oschina.ecust.improve.widget.OWebView;

import butterknife.Bind;

public class BrowserActivity extends BaseActivity {
    @Bind(R.id.webView)
    protected OWebView mWebView;

    @Override
    protected int getContentView() {
        return R.layout.activity_browser;
    }

    @Override
    public void onResume() {
        super.onResume();
        OWebView webView = mWebView;
        if (webView != null) {
            webView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        OWebView webView = mWebView;
        if (webView != null) {
            webView.onPause();
        }
    }

    @Override
    public void onDestroy() {
        OWebView view = mWebView;
        if (view != null) {
            mWebView = null;
            view.destroy();
        }
        super.onDestroy();
    }
}
