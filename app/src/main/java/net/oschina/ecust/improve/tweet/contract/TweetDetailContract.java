package net.oschina.ecust.improve.tweet.contract;

import net.oschina.ecust.bean.User;
import net.oschina.ecust.improve.bean.Tweet;
import net.oschina.ecust.improve.bean.simple.TweetComment;

/**
 * Created by thanatosx
 * on 16/5/28.
 */

public interface TweetDetailContract {

    interface Operator {

        Tweet getTweetDetail();

        void toReply(TweetComment comment);

        void onScroll();
    }

    interface ICmnView {
        void onCommentSuccess(TweetComment comment);
    }

    interface IThumbupView {
        void onLikeSuccess(boolean isUp, User user);
    }

    interface IAgencyView {
        void resetLikeCount(int count);

        void resetCmnCount(int count);
    }

}
