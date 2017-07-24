package net.oschina.ecust.viewpagerfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import net.oschina.ecust.R;
import net.oschina.ecust.adapter.ViewPageFragmentAdapter;
import net.oschina.ecust.base.BaseFragment;
import net.oschina.ecust.base.BaseViewPagerFragment;
import net.oschina.ecust.bean.SoftwareList;
import net.oschina.ecust.fragment.SoftwareCatalogListFragment;
import net.oschina.ecust.fragment.SoftwareListFragment;

public class OpenSoftwareFragment extends BaseViewPagerFragment {

    @Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {

        FrameLayout generalActionBar = (FrameLayout) mRoot.findViewById(R.id.general_actionbar);
        generalActionBar.setVisibility(View.GONE);

        String[] title = getResources().getStringArray(
                R.array.opensourcesoftware);
        adapter.addTab(title[0], "software_catalog",
                SoftwareCatalogListFragment.class, null);
        adapter.addTab(title[1], "software_recommend",
                SoftwareListFragment.class,
                getBundle(SoftwareList.CATALOG_RECOMMEND));
        adapter.addTab(title[2], "software_latest", SoftwareListFragment.class,
                getBundle(SoftwareList.CATALOG_TIME));
        adapter.addTab(title[3], "software_hot", SoftwareListFragment.class,
                getBundle(SoftwareList.CATALOG_VIEW));
        adapter.addTab(title[4], "software_china", SoftwareListFragment.class,
                getBundle(SoftwareList.CATALOG_LIST_CN));
    }

    private Bundle getBundle(String catalog) {
        Bundle bundle = new Bundle();
        bundle.putString(SoftwareListFragment.BUNDLE_SOFTWARE, catalog);
        return bundle;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public boolean onBackPressed() {
        BaseFragment fragment = (BaseFragment) mTabsAdapter.getItem(mViewPager
                .getCurrentItem());
        if (fragment instanceof SoftwareCatalogListFragment) {
            return fragment.onBackPressed();
        }
        return super.onBackPressed();
    }
}