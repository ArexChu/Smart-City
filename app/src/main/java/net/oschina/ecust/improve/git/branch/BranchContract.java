package net.oschina.ecust.improve.git.branch;

import net.oschina.ecust.improve.base.BasePresenter;
import net.oschina.ecust.improve.base.BaseView;
import net.oschina.ecust.improve.git.bean.Branch;

import java.util.List;

/**
 * Created by haibin
 * on 2017/3/13.
 */

 interface BranchContract {

    interface View extends BaseView<Presenter> {
        void showGetBranchSuccess(List<Branch> branches);

        void showGetBranchFailure(int strId);
    }

    interface Presenter extends BasePresenter {
        void getBranches(long id);
    }
}
