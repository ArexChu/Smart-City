package net.oschina.ecust.improve.feature;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.oschina.ecust.R;
import net.oschina.ecust.improve.app.AppOperator;
import net.oschina.ecust.improve.base.activities.BaseBackActivity;
import net.oschina.ecust.improve.dialog.ShareDialog;
import net.oschina.ecust.improve.media.ImageGalleryActivity;
import net.oschina.ecust.improve.utils.DialogHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 邀请函
 * Created by haibin on 2017/4/11.
 */
public class InvitationActivity extends BaseBackActivity {
    private ShareDialog mShareDialog;
    private String mUrl;

    public static void show(Context context, String url) {
        Intent intent = new Intent(context, InvitationActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_quickbooking;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mShareDialog = new ShareDialog(this, -1);
        mUrl = getIntent().getStringExtra("url");
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mShareDialog != null) {
            mShareDialog.dismiss();
        }
    }
}
