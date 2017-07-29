package net.oschina.ecust.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.oschina.ecust.R;
import net.oschina.ecust.improve.base.adapter.BaseRecyclerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by thanatosx on 2016/11/7.
 */

public class SmartCityAdapter extends BaseRecyclerAdapter {

    private String[] itemNames = new String[]{};

    private int[] itemIcons = new int[]{};

    private int[] itemTypes = new int[]{};

    public SmartCityAdapter(Context context, String[] itemNames, int[] itemIcons, int[] itemTypes) {
        super(context, NEITHER);
        this.itemNames = itemNames;
        this.itemIcons = itemIcons;
        this.itemTypes = itemTypes;
        for (int i = 0; i < itemNames.length; i++) {
            addItem("");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (itemTypes[position] == 1) {
            return 1;
        } else {
            return 0;
        }
    }



    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        if (type == 1) {
            return new TitleHolder(LayoutInflater.from(mContext).inflate(
                    R.layout.item_home_title, parent, false));
        } else {
            return new ItemHolder(LayoutInflater.from(mContext).inflate(
                    R.layout.item_home_region, parent, false));
        }
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder h, Object item, int position) {
        if (itemTypes[position] == 1) {
            TitleHolder titleHolder = (TitleHolder) h;
            titleHolder.mTitleIcon.setImageResource(itemIcons[position]);
            titleHolder.mTitleText.setText(itemNames[position]);
        } else {
            ItemHolder itemHolder = (ItemHolder) h;
            itemHolder.mItemIcon.setImageResource(itemIcons[position]);
            itemHolder.mItemText.setText(itemNames[position]);
        }
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_icon)
        ImageView mItemIcon;
        @Bind(R.id.item_title)
        TextView mItemText;

        public ItemHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class TitleHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_title_type)
        ImageView mTitleIcon;
        @Bind(R.id.tv_title_type)
        TextView mTitleText;

        public TitleHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
