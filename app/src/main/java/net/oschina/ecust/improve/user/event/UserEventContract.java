package net.oschina.ecust.improve.user.event;

import net.oschina.ecust.improve.base.BaseListPresenter;
import net.oschina.ecust.improve.base.BaseListView;
import net.oschina.ecust.improve.bean.SubBean;

/**
 * Created by haibin
 * on 2017/1/18.
 */

interface UserEventContract {

    interface EmptyView {
        void hideEmptyLayout();

        void showErrorLayout(int errorType);
    }

    interface View extends BaseListView<Presenter, SubBean> {

    }

    interface Presenter extends BaseListPresenter {

    }
}
