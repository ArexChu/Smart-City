package net.oschina.ecust.improve.feature;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
public class AppointmentFragment extends BaseFragment implements View.OnClickListener{
    @Bind(R.id.booking)
    Button mButton;

    String url = "http://172.20.220.48:8080/greeting";
//    String url = "http://192.168.1.170:8080/greeting";
    public static long size;

    public static AppointmentFragment newInstance() {
        AppointmentFragment fragment = new AppointmentFragment();
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
                client.get(url, new TextHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, String res) {
                                // called when response HTTP status is "200 OK"

                                Gson gson = new Gson();
                                Greeting greeting = gson.fromJson(res, Greeting.class);
                                size = greeting.size;
                                Log.e("arex", String.valueOf(size));

                                Toast.makeText(getActivity(), "预约成功!",
                                        Toast.LENGTH_LONG).show();


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
