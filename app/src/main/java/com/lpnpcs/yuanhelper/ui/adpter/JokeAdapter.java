package com.lpnpcs.yuanhelper.ui.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lpnpcs.yuanhelper.R;
import com.lpnpcs.yuanhelper.data.entity.JokeEntity;
import com.lpnpcs.yuanhelper.util.ToolsUtil;

import java.util.List;


public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ItemViewHolder> {

    private List<JokeEntity> mData;
    private Context mContext;
    private int mMaxWidth;
    private int mMaxHeight;

    private OnItemClickListener mOnItemClickListener;

    public JokeAdapter(Context context) {
        this.mContext = context;
        mMaxWidth = ToolsUtil.getWidthInPx(mContext) - 10;
        mMaxHeight = ToolsUtil.getHeightInPx(mContext) - ToolsUtil.getStatusHeight(mContext) -
                ToolsUtil.dip2px(mContext, 96);
    }

    public void setmDate(List<JokeEntity> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public JokeAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item_joke, parent, false);
        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(JokeAdapter.ItemViewHolder holder, int position) {
        JokeEntity jokeEntity = mData.get(position);
        if(jokeEntity == null) {
            return;
        }
        holder.mTitle.setText(jokeEntity.getTitle());
        String content = jokeEntity.getContent();
        content = content.replace("<br/><br/>","<br/>");
        holder.mBody.setText(Html.fromHtml(content));
    }

    @Override
    public int getItemCount() {
        if(mData == null) {
            return 0;
        }
        return mData.size();
    }

    public JokeEntity getItem(int position) {
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
        public TextView mBody;

        public ItemViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.tvTitle);
            mBody = (TextView) v.findViewById(R.id.tvBody);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, this.getPosition());
            }
        }
    }

}
