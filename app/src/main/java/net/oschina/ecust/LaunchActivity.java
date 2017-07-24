package net.oschina.ecust;

import android.content.Intent;
import android.text.TextUtils;

import net.oschina.ecust.improve.account.AccountHelper;
import net.oschina.ecust.improve.app.AppOperator;
import net.oschina.ecust.improve.base.activities.BaseActivity;
import net.oschina.ecust.improve.bean.User;
import net.oschina.ecust.improve.main.MainActivity;
import net.oschina.ecust.improve.main.tabs.DynamicTabFragment;

/**
 * 应用启动界面
 */
public class LaunchActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        return R.layout.app_start;
    }

    @Override
    protected void initData() {
        super.initData();
        // 在这里我们检测是否是新版本安装，如果是则进行老版本数据迁移工作
        // 该工作可能消耗大量时间所以放在自线程中执行
        AppOperator.runOnThread(new Runnable() {
            @Override
            public void run() {
                doMerge();
            }
        });
    }

    private void doMerge() {
        // 判断是否是新版本
        if (Setting.checkIsNewVersion(this)) {
            // Cookie迁移
            String cookie = OSCApplication.getInstance().getProperty("cookie");
            if (!TextUtils.isEmpty(cookie)) {
                OSCApplication.getInstance().removeProperty("cookie");
                User user = AccountHelper.getUser();
                user.setCookie(cookie);
                AccountHelper.updateUserCache(user);
                OSCApplication.reInit();
            }
        }

        // 栏目相关数据合并操作
        DynamicTabFragment.initTabPickerManager();

        // Delay...
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 完成后进行跳转操作
        redirectTo();
    }

    private void redirectTo() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
