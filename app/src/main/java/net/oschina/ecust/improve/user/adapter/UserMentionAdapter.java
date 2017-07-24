package net.oschina.ecust.improve.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.oschina.ecust.R;
import net.oschina.ecust.improve.base.adapter.BaseGeneralRecyclerAdapter;
import net.oschina.ecust.improve.bean.Mention;
import net.oschina.ecust.improve.bean.simple.Author;
import net.oschina.ecust.improve.bean.simple.Origin;
import net.oschina.ecust.improve.user.activities.OtherUserHomeActivity;
import net.oschina.ecust.improve.utils.parser.MentionParser;
import net.oschina.ecust.improve.widget.IdentityView;
import net.oschina.ecust.improve.widget.PortraitView;
import net.oschina.ecust.util.PlatfromUtil;
import net.oschina.ecust.util.StringUtils;
import net.oschina.ecust.widget.TweetTextView;

/**
 * Created by huanghaibin_dev
 * on 2016/8/16.
 */

public class UserMentionAdapter extends BaseGeneralRecyclerAdapter<Mention> {
    private OnUserFaceClickListener mListener;

    public UserMentionAdapter(Callback callback) {
        super(callback, ONLY_FOOTER);
        initListener();
    }

    private void initListener() {
        mListener = new UserMentionAdapter.OnUserFaceClickListener() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onClick(View v, int position) {
                Author author = getItem(position).getAuthor();
                if (author != null)
                    OtherUserHomeActivity.show(mCallBack.getContext(), author.getId());
            }
        };
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        MentionViewHolder holder = new UserMentionAdapter.MentionViewHolder(mInflater.inflate(R.layout.item_list_comment, parent, false));
        holder.iv_user_avatar.setTag(R.id.iv_face, holder);
        return holder;
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, Mention item, int position) {
        MentionViewHolder viewHolder = (MentionViewHolder) holder;
        Author author = item.getAuthor();
        viewHolder.identityView.setup(author);
        if (author != null) {
            viewHolder.iv_user_avatar.setup(author);
            viewHolder.tv_user_name.setText(author.getName());
        } else {
            viewHolder.iv_user_avatar.setup(0, "匿名用户", "");
            viewHolder.tv_user_name.setText("匿名用户");
        }
        viewHolder.iv_user_avatar.setOnClickListener(mListener);
        PlatfromUtil.setPlatFromString(viewHolder.tv_platform, item.getAppClient());
        viewHolder.tv_comment_count.setText(String.valueOf(item.getCommentCount()));
        viewHolder.tv_time.setText(StringUtils.formatSomeAgo(item.getPubDate()));
        viewHolder.tv_content.setText(MentionParser.getInstance().parse(mContext, item.getContent()));
        Origin origin = item.getOrigin();
        if (origin != null && !TextUtils.isEmpty(origin.getDesc())) {
            viewHolder.tv_origin.setVisibility(View.VISIBLE);
            viewHolder.tv_origin.setText(MentionParser.getInstance().parse(mContext, origin.getDesc()));
        } else {
            viewHolder.tv_origin.setVisibility(View.GONE);
        }

    }

    private static class MentionViewHolder extends RecyclerView.ViewHolder {
        IdentityView identityView;
        PortraitView iv_user_avatar;
        TextView tv_user_name, tv_time, tv_platform, tv_comment_count;
        TweetTextView tv_content, tv_origin;

        MentionViewHolder(View itemView) {
            super(itemView);
            identityView = (IdentityView) itemView.findViewById(R.id.identityView);
            iv_user_avatar = (PortraitView) itemView.findViewById(R.id.iv_user_avatar);
            tv_user_name = (TextView) itemView.findViewById(R.id.tv_user_name);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_content = (TweetTextView) itemView.findViewById(R.id.tv_content);
            tv_origin = (TweetTextView) itemView.findViewById(R.id.tv_origin);
            tv_platform = (TextView) itemView.findViewById(R.id.tv_platform);
            tv_comment_count = (TextView) itemView.findViewById(R.id.tv_comment_count);
        }
    }

    private abstract class OnUserFaceClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            MentionViewHolder holder = (MentionViewHolder) v.getTag(R.id.iv_face);
            onClick(v, holder.getAdapterPosition());
        }

        public abstract void onClick(View v, int position);
    }
}
