package net.oschina.ecust.improve.user.collection;

import android.content.Context;

import net.oschina.ecust.improve.base.BaseListPresenter;
import net.oschina.ecust.improve.base.BaseListView;
import net.oschina.ecust.improve.bean.Collection;

/**
 * Created by haibin
 * on 2016/12/30.
 */

 interface UserCollectionContract {

    interface View extends BaseListView<Presenter, Collection> {
        void showGetFavSuccess(int position);
    }

    interface Presenter extends BaseListPresenter {
        void getCache(Context context);

        void getFavReverse(Collection collection, int position);
    }
}
