package net.oschina.ecust.improve.git.detail;

import net.oschina.ecust.improve.base.BasePresenter;
import net.oschina.ecust.improve.base.BaseView;
import net.oschina.ecust.improve.git.bean.Project;

/**
 * Created by haibin
 * on 2017/3/9.
 */
interface ProjectDetailContract {

    interface EmptyView {
        void showGetDetailSuccess(int strId);

        void showGetDetailFailure(int strId);
    }

    @SuppressWarnings("unused")
    interface View extends BaseView<Presenter> {
        void showGetDetailSuccess(Project project, int strId);

        void showGetDetailFailure(int strId);

        void showGetCommentCountSuccess(int count);
    }

    interface Presenter extends BasePresenter {
        void getProjectDetail(long id);

        void getProjectDetail(String name, String pathWithNamespace);

        String getShareUrl();

        void getCommentCount(long id);
    }
}
