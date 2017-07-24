package net.oschina.ecust.improve.detail.v2;

import net.oschina.ecust.improve.base.BasePresenter;
import net.oschina.ecust.improve.base.BaseView;
import net.oschina.ecust.improve.bean.SubBean;
import net.oschina.ecust.improve.bean.comment.Comment;
import net.oschina.ecust.improve.detail.db.Behavior;

import java.util.List;

/**
 * Created by haibin
 * on 2016/11/30.
 */

interface DetailContract {

    interface EmptyView {
        void hideEmptyLayout();

        void showErrorLayout(int errorType);

        void showGetDetailSuccess(SubBean bean);

        void showFavReverseSuccess(boolean isFav, int favCount, int strId);

        void showCommentSuccess(Comment comment);

        void showCommentError(String message);

        void showUploadBehaviorsSuccess(int index, String time);

        void showShareCommentView(Comment comment);
    }

    interface View extends BaseView<Presenter> {
        void showGetDetailSuccess(SubBean bean);

        void showFavReverseSuccess(boolean isFav, int favCount, int strId);

        void showFavError();

        void showCommentSuccess(Comment comment);

        void showCommentError(String message);

        void showAddRelationSuccess(boolean isRelation, int strId);

        void showAddRelationError();

        void showScrollToTop();
    }

    interface Presenter extends BasePresenter {

        void getCache();

        void getDetail();//获得详情

        void favReverse();

        void addComment(
                long sourceId,
                int type,
                String content,
                long referId,
                long replyId,
                long reAuthorId
        );//添加评论

        void uploadBehaviors(List<Behavior> behaviors);

        void addUserRelation(long authorId);

        void scrollToTop();

        void shareComment(Comment comment);
    }
}
