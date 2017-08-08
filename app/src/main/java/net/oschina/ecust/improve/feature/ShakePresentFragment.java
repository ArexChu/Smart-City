package net.oschina.ecust.improve.feature;

import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import net.oschina.ecust.R;
import net.oschina.ecust.improve.base.fragments.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * 摇一摇礼品相关实现
 */
public class ShakePresentFragment extends BaseFragment implements View.OnClickListener{
    @Bind(R.id.booking)
    Button mButton;

    public static ShakePresentFragment newInstance() {
        ShakePresentFragment fragment = new ShakePresentFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_appointment;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
    }

    @OnClick({R.id.booking})

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.booking:

                AsyncHttpClient client = new AsyncHttpClient();
                client.get("http://172.20.190.27:8080/greeting", new TextHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, String res) {
                                // called when response HTTP status is "200 OK"

                                String str = "{\"id\":42,\"content\":\"Hello, World!\",\"size\":38}";
                                Gson gson = new Gson();
                                Greeting greeting = gson.fromJson(str, Greeting.class);
                                System.out.println(greeting);
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
}
