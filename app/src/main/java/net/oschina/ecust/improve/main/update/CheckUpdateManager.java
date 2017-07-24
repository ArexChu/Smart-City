package net.oschina.ecust.improve.main.update;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import net.oschina.ecust.AppContext;
import net.oschina.ecust.api.remote.OSChinaApi;
import net.oschina.ecust.improve.app.AppOperator;
import net.oschina.ecust.improve.bean.Version;
import net.oschina.ecust.improve.bean.base.ResultBean;
import net.oschina.ecust.improve.utils.DialogHelper;
import net.oschina.ecust.util.TDevice;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by haibin
 * on 2016/10/19.
 */
public class CheckUpdateManager {


    private ProgressDialog mWaitDialog;
    private Context mContext;
    private boolean mIsShowDialog;
    private RequestPermissions mCaller;

    public CheckUpdateManager(Context context, boolean showWaitingDialog) {
        this.mContext = context;
        mIsShowDialog = showWaitingDialog;
        if (mIsShowDialog) {
            mWaitDialog = DialogHelper.getProgressDialog(mContext);
            mWaitDialog.setMessage("正在检查中...");
            mWaitDialog.setCancelable(false);
            mWaitDialog.setCanceledOnTouchOutside(false);
        }
    }


    public void checkUpdate() {
        if (mIsShowDialog) {
            mWaitDialog.show();
        }
        OSChinaApi.checkUpdate(new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                if (mIsShowDialog) {
                    DialogHelper.getMessageDialog(mContext, "网络异常，无法获取新版本信息").show();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    ResultBean<List<Version>> bean = AppOperator.createGson()
                            .fromJson(responseString, new TypeToken<ResultBean<List<Version>>>() {
                            }.getType());
                    if (bean != null && bean.isSuccess()) {
                        List<Version> versions = bean.getResult();
                        if (versions.size() > 0) {
                            final Version version = versions.get(0);
                            int curVersionCode = TDevice.getVersionCode(AppContext
                                    .getInstance().getPackageName());
                            if (curVersionCode < Integer.parseInt(version.getCode())) {
                                UpdateActivity.show((Activity) mContext, version);
//                                AlertDialog.Builder dialog = DialogHelper.getConfirmDialog(mContext, version.getMessage(), new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        //mCaller.call(version);
//                                        if (!TDevice.isWifiOpen()) {
//                                            DialogHelper.getConfirmDialog(mContext, "当前非wifi环境，是否升级？", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    mCaller.call(version);
//                                                }
//                                            }).show();
//                                        } else {
//                                            mCaller.call(version);
//                                        }
//                                    }
//                                });
//                                dialog.setTitle("发现新版本");
//                                dialog.show();
                            } else {
                                if (mIsShowDialog) {
                                    DialogHelper.getMessageDialog(mContext, "已经是新版本了").show();
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (mIsShowDialog) {
                    mWaitDialog.dismiss();
                }
            }
        });
    }

    public void setCaller(RequestPermissions caller) {
        this.mCaller = caller;
    }

    public interface RequestPermissions {
        void call(Version version);
    }
}
