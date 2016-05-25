package com.lpnpcs.yuanhelper.ui.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lpnpcs.yuanhelper.R;
import com.lpnpcs.yuanhelper.data.entity.ImageEntity;
import com.lpnpcs.yuanhelper.util.ToolsUtil;

import java.util.List;

/**
 *
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ItemViewHolder> {

    private List<ImageEntity> mData;
    private Context mContext;
    private int mMaxWidth;
    private int mMaxHeight;

    private OnItemClickListener mOnItemClickListener;

    public ImageAdapter(Context context) {
        this.mContext = context;
        mMaxWidth = ToolsUtil.getWidthInPx(mContext) - 20;
        mMaxHeight = ToolsUtil.getHeightInPx(mContext) - ToolsUtil.getStatusHeight(mContext) -
                ToolsUtil.dip2px(mContext, 96);
    }

    public void setmDate(List<ImageEntity> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public ImageAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item_image, parent, false);
        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ItemViewHolder holder, int position) {
        ImageEntity imageEntity = mData.get(position);
        if (imageEntity == null) {
            return;
        }
        holder.mTitle.setText(imageEntity.getTitle());
        float scale = (float) imageEntity.getWidth() / (float) mMaxWidth;
        int height = (int) (imageEntity.getHeight() / scale);
        if (height > mMaxHeight) {
            height = mMaxHeight;
        }
        holder.mImage.setLayoutParams(new LinearLayout.LayoutParams(mMaxWidth, height));
        Glide.with(mContext)
                .load(imageEntity.getThumburl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    public ImageEntity getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitle;
        public ImageView mImage;

        public ItemViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.tvTitle);
            mImage = (ImageView) v.findViewById(R.id.ivImage);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, this.getPosition());
            }
        }
    }

}
