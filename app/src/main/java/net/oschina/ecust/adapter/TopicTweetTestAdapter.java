package net.oschina.ecust.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.oschina.ecust.R;
import net.oschina.ecust.improve.base.adapter.BaseRecyclerAdapter;
import net.oschina.ecust.improve.tweet.activities.TopicTweetActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by thanatosx on 2016/11/7.
 */

public class TopicTweetTestAdapter extends BaseRecyclerAdapter {

    private String[] itemNames = new String[]{};

    private int[] itemIcons = new int[]{};

    public TopicTweetTestAdapter(Context context, String[] itemNames, int[] itemIcons) {
        super(context, NEITHER);
        this.itemNames = itemNames;
        this.itemIcons = itemIcons;
        for (int i = 0; i < itemNames.length; i++) {
            addItem("");
        }
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(
                R.layout.item_home_region, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder h, Object item, int position) {
        ViewHolder holder = (ViewHolder) h;
        holder.mItemIcon.setImageResource(itemIcons[position]);
        holder.mItemText.setText(itemNames[position]);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_icon)
        ImageView mItemIcon;
        @Bind(R.id.item_title)
        TextView mItemText;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
