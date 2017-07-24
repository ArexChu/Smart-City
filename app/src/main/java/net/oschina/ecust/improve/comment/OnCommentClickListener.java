package net.oschina.ecust.improve.comment;

import android.view.View;

import net.oschina.ecust.improve.bean.comment.Comment;

/**
 * Created by JuQiu
 * on 16/6/21.
 */

public interface OnCommentClickListener {
    void onClick(View view, Comment comment);
}
