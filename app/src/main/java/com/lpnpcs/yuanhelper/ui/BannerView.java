package com.lpnpcs.yuanhelper.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lpnpcs.yuanhelper.R;
import com.lpnpcs.yuanhelper.base.YuanApplication;
import com.lpnpcs.yuanhelper.data.entity.ZhiHuEntity;
import com.lpnpcs.yuanhelper.ui.activity.MainActivity;
import com.lpnpcs.yuanhelper.ui.activity.ZhiHuDetailActivity;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：deals with displaying the top banner
 */
public class BannerView implements Holder<ZhiHuEntity.TopStoriesBean>{
    private View view;
    @Override
    public View createView(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.card_item_big, null);
        return view;
    }
    @Override
    public void UpdateUI(final Context context, int position, final ZhiHuEntity.TopStoriesBean entity) {
        final ImageView imageView = (ImageView) view.findViewById(R.id.story_img);
        TextView textView = (TextView) view.findViewById(R.id.news_title);
        Glide.with(YuanApplication.context) .load(entity.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(imageView);
        textView.setText(entity.getTitle());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ZhiHuDetailActivity.class);
                intent.putExtra("id", entity.getId());
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((MainActivity) context,
                        imageView, context.getString(R.string.shared_img));
                ActivityCompat.startActivity((MainActivity) context, intent, optionsCompat.toBundle());
            }
        });

    }

}
