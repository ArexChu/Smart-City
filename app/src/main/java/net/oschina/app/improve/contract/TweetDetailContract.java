package net.oschina.app.improve.contract;

import net.oschina.app.bean.Comment;
import net.oschina.app.bean.Tweet;
import net.oschina.app.bean.User;

/**
 * Created by thanatosx
 * on 16/5/28.
 */

public interface TweetDetailContract {

    interface Operator {

        Tweet getTweetDetail();

        void toReply(Comment comment);

        void onScroll();
    }

    interface CmnView {
        void onCommentSuccess(Comment comment);
    }

    interface ThumbupView {
        void onLikeSuccess(boolean isUp, User user);
    }

    interface AgencyView{
        void resetLikeCount(int count);

        void resetCmnCount(int count);
    }

}
