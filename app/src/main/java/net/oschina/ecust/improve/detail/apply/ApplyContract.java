package net.oschina.ecust.improve.detail.apply;

import net.oschina.ecust.improve.base.BaseListPresenter;
import net.oschina.ecust.improve.base.BaseListView;
import net.oschina.ecust.improve.bean.ApplyUser;

/**
 * Created by haibin
 * on 2016/12/27.
 */

 interface ApplyContract {

    interface EmptyView {
        void showGetApplyUserSuccess();

        void showGetApplyUserError(String message);

        void showSearchError(String message);
    }

    interface View extends BaseListView<Presenter, ApplyUser> {
        void showAddRelationSuccess(int relation, int position);

        void showAddRelationError();
    }

    interface Presenter extends BaseListPresenter {
        void addRelation(long authorId, int position);
    }
}
