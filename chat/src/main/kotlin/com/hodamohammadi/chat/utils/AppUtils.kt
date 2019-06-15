package com.hodamohammadi.chat.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.stfalcon.chatkit.commons.ImageLoader

/**
 * Common app util methods.
 */
class AppUtils {
    object CustomImageLoader : ImageLoader {
        override fun loadImage(imageView: ImageView, url: String?, payload: Any?) {
            Picasso.get().load(url).into(imageView);
        }
    }
}