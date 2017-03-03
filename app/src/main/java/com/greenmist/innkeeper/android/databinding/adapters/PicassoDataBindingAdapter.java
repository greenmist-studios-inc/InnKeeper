package com.greenmist.innkeeper.android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by geoff.powell on 2/18/17.
 */
public class PicassoDataBindingAdapter {

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        if (url == null) {
            imageView.setImageDrawable(null);
        } else {
            Picasso.with(imageView.getContext()).load(url).into(imageView);
        }
    }
}
