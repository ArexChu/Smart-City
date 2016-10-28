package net.oschina.app.improve.account.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import net.oschina.app.AppContext;
import net.oschina.app.R;
import net.oschina.app.api.remote.OSChinaApi;
import net.oschina.app.improve.account.AccountHelper;
import net.oschina.app.improve.account.bean.PhoneToken;
import net.oschina.app.improve.base.activities.BaseActivity;
import net.oschina.app.improve.bean.User;
import net.oschina.app.improve.bean.base.ResultBean;
import net.oschina.app.improve.main.MainActivity;
import net.oschina.app.util.TDevice;

import java.lang.reflect.Type;

import butterknife.Bind;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by fei
 * on 2016/10/14.
 * desc:
 */

public class RegisterStepTwoActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private static final String TAG = "RegisterStepTwoActivity";

    public static final String PHONETOKEN_KEY = "phoneToken";

    @Bind(R.id.ly_register_bar)
    LinearLayout mLlRegisterBar;

    @Bind(R.id.ll_register_two_username)
    LinearLayout mLlRegisterTwoUsername;
    @Bind(R.id.et_register_username)
    EditText mEtRegisterUsername;
    @Bind(R.id.iv_register_username_del)
    ImageView mIvRegisterUsernameDel;
    @Bind(R.id.ll_register_two_pwd)
    LinearLayout mLlRegisterTwoPwd;
    @Bind(R.id.et_register_pwd_input)
    EditText mEtRegisterPwd;
    @Bind(R.id.tv_register_man)
    TextView mTvRegisterMan;
    @Bind(R.id.tv_register_female)
    TextView mTvRegisterFemale;
    @Bind(R.id.bt_register_submit)
    Button mBtRegisterSubmit;
    private PhoneToken mPhoneToken;
    private TextHttpResponseHandler mHandler = new TextHttpResponseHandler() {
        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, String responseString) {


            Type type = new TypeToken<ResultBean<User>>() {
            }.getType();
            ResultBean<User> resultBean = AppContext.createGson().fromJson(responseString, type);

            if (resultBean.isSuccess()) {
                User user = resultBean.getResult();
                AccountHelper.login(user, headers);
                // Kill and skip
                Intent intent = new Intent(RegisterStepTwoActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            } else {
                int code = resultBean.getCode();
                switch (code) {
                    case 216:
                        //phoneToken 已经失效
                        break;
                    case 217:
                        mLlRegisterTwoUsername.setBackgroundResource(R.drawable.bg_login_input_error);
                        break;
                    case 218:
                        break;
                    case 219:
                        mLlRegisterTwoPwd.setBackgroundResource(R.drawable.bg_login_input_error);
                        break;
                    default:
                        break;
                }

                AppContext.showToast(resultBean.getMessage(), Toast.LENGTH_SHORT);
            }

        }
    };

    /**
     * show register step two activity
     *
     * @param context context
     */
    public static void show(Context context, PhoneToken phoneToken) {
        Intent intent = new Intent(context, RegisterStepTwoActivity.class);
        intent.putExtra(PHONETOKEN_KEY, phoneToken);
        context.startActivity(intent);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main_register_step_two;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        TextView tvLabel = (TextView) mLlRegisterBar.findViewById(R.id.tv_navigation_label);
        tvLabel.setText(R.string.login_register_hint);

        mEtRegisterUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.length();
                if (length > 0) {
                    mIvRegisterUsernameDel.setVisibility(View.VISIBLE);
                } else {
                    mIvRegisterUsernameDel.setVisibility(View.INVISIBLE);
                }

                if (length > 12) {
                    AppContext.showToast(getResources().getString(R.string.register_username_error), Toast.LENGTH_SHORT);
                    mLlRegisterTwoUsername.setBackgroundResource(R.drawable.bg_login_input_error);
                } else {
                    mLlRegisterTwoUsername.setBackgroundResource(R.drawable.bg_login_input_ok);
                }

            }
        });
        mEtRegisterUsername.setOnFocusChangeListener(this);
        mEtRegisterPwd.setOnFocusChangeListener(this);
        mEtRegisterPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.length();
                if (length < 6) {
                    mLlRegisterTwoPwd.setBackgroundResource(R.drawable.bg_login_input_error);
                } else {
                    mLlRegisterTwoPwd.setBackgroundResource(R.drawable.bg_login_input_ok);
                }

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();
        mPhoneToken = (PhoneToken) intent.getSerializableExtra(PHONETOKEN_KEY);
        Log.e(TAG, "initData: ------------>" + mPhoneToken.toString());

    }

    @SuppressWarnings("deprecation")
    @OnClick({R.id.ib_navigation_back, R.id.iv_register_username_del, R.id.tv_register_man,
            R.id.tv_register_female, R.id.bt_register_submit})
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ib_navigation_back:
                finish();
                break;
            case R.id.iv_register_username_del:
                mEtRegisterUsername.setText(null);
                break;
            case R.id.tv_register_man:
                if (mTvRegisterMan.getTag() != null) {
                    Drawable left = getResources().getDrawable(R.mipmap.btn_gender_male_normal);
                    mTvRegisterMan.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                    mTvRegisterMan.setTag(null);
                } else {
                    Drawable left = getResources().getDrawable(R.mipmap.btn_gender_male_actived);
                    mTvRegisterMan.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                    mTvRegisterMan.setTag(false);
                    Drawable female = getResources().getDrawable(R.mipmap.btn_gender_female_normal);
                    mTvRegisterFemale.setCompoundDrawablesWithIntrinsicBounds(female, null, null, null);
                    mTvRegisterFemale.setTag(null);
                }

                break;
            case R.id.tv_register_female:
                if (mTvRegisterFemale.getTag() != null) {
                    Drawable left = getResources().getDrawable(R.mipmap.btn_gender_female_normal);
                    mTvRegisterFemale.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                    mTvRegisterFemale.setTag(null);
                } else {
                    Drawable left = getResources().getDrawable(R.mipmap.btn_gender_female_actived);
                    mTvRegisterFemale.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                    mTvRegisterFemale.setTag(true);

                    Drawable men = getResources().getDrawable(R.mipmap.btn_gender_male_normal);
                    mTvRegisterMan.setCompoundDrawablesWithIntrinsicBounds(men, null, null, null);
                    mTvRegisterMan.setTag(null);
                }


                break;
            case R.id.bt_register_submit:

                if (TDevice.hasInternet()) {

                    String username = mEtRegisterUsername.getText().toString().trim();
                    String pwd = mEtRegisterPwd.getText().toString().trim();

                    if (TextUtils.isEmpty(username) && TextUtils.isEmpty(pwd)) {
                        AppContext.showToast(getString(R.string.hint_pwd_null));
                        return;
                    } else {

                        int gender = 0;

                        Object isMan = mTvRegisterMan.getTag();
                        if (isMan != null) {
                            gender = 1;
                        }

                        Object isFemale = mTvRegisterFemale.getTag();
                        if (isFemale != null) {
                            gender = 2;
                        }

                        String appToken = "123";//Verifier.getPrivateToken(getApplication());

                        OSChinaApi.register(username, pwd, gender, mPhoneToken.getToken(), appToken, mHandler);
                    }
                } else {
                    AppContext.showToast(getResources().getString(R.string.tip_network_error));
                }


                break;
            default:
                break;
        }

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        int id = v.getId();
        switch (id) {
            case R.id.et_register_username:
                if (hasFocus) {
                    mLlRegisterTwoUsername.setActivated(true);
                    mLlRegisterTwoPwd.setActivated(false);
                }
                break;
            case R.id.et_register_pwd_input:
                if (hasFocus) {
                    mLlRegisterTwoPwd.setActivated(true);
                    mLlRegisterTwoUsername.setActivated(false);
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}