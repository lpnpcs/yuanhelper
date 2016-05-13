package com.lpnpcs.yuanhelper.ui.adpter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lpnpcs.yuanhelper.R;
import com.lpnpcs.yuanhelper.data.entity.ZhiHuEntity;
import com.lpnpcs.yuanhelper.ui.BannerView;
import com.lpnpcs.yuanhelper.ui.OnListFragmentInteract;
import com.lpnpcs.yuanhelper.util.DateUtil;
import com.lpnpcs.yuanhelper.util.LogUtil;

import java.util.List;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：ZhuZhiList Adapter
 */
public class ZhiHuListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_BANNER = 0;
    private static final int TYPE_ITEM = 2;
    /**
     * footer is to show load more hint
     */
    private static final int TYPE_FOOTER = 3;
    public static int textGrey;
    public static int textDark;
    private List<ZhiHuEntity.StoriesBean> storiesBeans;
    private List<ZhiHuEntity.TopStoriesBean> topStoriesBeens;
    private ConvenientBanner<ZhiHuEntity.TopStoriesBean> banner;
    private OnListFragmentInteract mListener;

    public ZhiHuListAdapter(OnListFragmentInteract listener, ZhiHuEntity zhiHuEntity) {
        mListener = listener;
        storiesBeans = zhiHuEntity.getStories();
        topStoriesBeens = zhiHuEntity.getTop_stories();

    }

    public void setNews(ZhiHuEntity zhiHuEntity) {
        storiesBeans = zhiHuEntity.getStories();
        topStoriesBeens = zhiHuEntity.getTop_stories();

        LogUtil.e("lp",storiesBeans+"");
        LogUtil.e("lp",topStoriesBeens+"");
        notifyDataSetChanged();
        if (null != banner) {
            banner.notifyDataSetChanged();
        }
    }

    public void addNews(ZhiHuEntity zhiHuEntity) {
        storiesBeans.addAll(zhiHuEntity.getStories());
        notifyDataSetChanged();
    }

    public void clear() {
        banner.stopTurning();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_BANNER) {
            View view = inflater.inflate(R.layout.fragment_news_banner, parent, false);
            return new BannerViewHolder(view);

        } else if (viewType == TYPE_FOOTER) {
            View view = inflater.inflate(R.layout.footer_loading, parent, false);
            return new FooterViewHolder(view);

        } else {
            View view = inflater.inflate(R.layout.fragment_news_item, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        textGrey = ContextCompat.getColor(context, R.color.darker_gray);
        textDark = ContextCompat.getColor(context, android.support.design.R.color.abc_primary_text_material_light);
        if (holder instanceof ViewHolder) {
            final ViewHolder viewHolder = (ViewHolder) holder;
            if (position == 1) {
                viewHolder.header.setText(context.getString(R.string.hottest));
                viewHolder.header.setVisibility(View.VISIBLE);
                viewHolder.mItem.setVisibility(View.GONE);
                viewHolder.mItem.setClickable(false);
                return;
            } else {
                //position=0, 1 are occupied with banner, header
                viewHolder.zhihuStory = storiesBeans.get(position - 2);
                //type == 1 means this item is added by me, so it's a header to show date.
                if (viewHolder.zhihuStory.getType() == 1) {
                    String date = DateUtil.getDisplayDate(viewHolder.zhihuStory.getId() + "");
                    viewHolder.header.setText(date);
                    viewHolder.header.setVisibility(View.VISIBLE);
                    viewHolder.header.setClickable(false);
                    viewHolder.mItem.setVisibility(View.GONE);
                }else {
                    viewHolder.header.setVisibility(View.GONE);
                    viewHolder.mItem.setVisibility(View.VISIBLE);
                }
            }
            Glide.with(context).load(viewHolder.zhihuStory.getImages().get(0))
                    .diskCacheStrategy(DiskCacheStrategy.ALL).crossFade()
                    .into(viewHolder.mImage);
            viewHolder.mTitle.setTextColor(textDark);
            viewHolder.mTitle.setText(viewHolder.zhihuStory.getTitle());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onListFragmentInteraction(viewHolder);
                    }

                }
            });

        } else if (holder instanceof BannerViewHolder) {
            final BannerViewHolder itemHolder = (BannerViewHolder) holder;
            itemHolder.banner.setPages(new CBViewHolderCreator<BannerView>() {
                @Override
                public BannerView createHolder() {
                    return new BannerView();
                }
            }, topStoriesBeens);
            banner = itemHolder.banner;
        }

    }

    @Override
    public int getItemCount() {
        //items + banner + footer
        if (storiesBeans != null) {
            return storiesBeans.size() + 2;
        }else{
            return  0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == storiesBeans.size() + 1) {
            // position 0 is banner so
            // the footer appears the size+1 position
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View view) {
            super(view);
        }

    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        public final ConvenientBanner<ZhiHuEntity.TopStoriesBean> banner;

        public BannerViewHolder(View view) {
            super(view);
            banner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView header;
        public final ImageView mImage;
        public final TextView mTitle;
        public final View mItem;
        public ZhiHuEntity.StoriesBean zhihuStory;

        public ViewHolder(View view) {
            super(view);
            header = (TextView) view.findViewById(R.id.story_header);
            mImage = (ImageView) view.findViewById(R.id.story_img);
            mTitle = (TextView) view.findViewById(R.id.news_title);
            mItem = view.findViewById(R.id.news_item);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }

}
