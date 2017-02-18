package net.angrycode.wehub.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.angrycode.wehub.R;

/**
 * Created by lancelot on 2016/11/14.
 */

public class GlideUtils {
    private GlideUtils() {

    }

    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).placeholder(R.mipmap.ic_github).crossFade().into(imageView);
    }
}
