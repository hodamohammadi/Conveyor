package com.hodamohammadi.conveyor.utils

import android.widget.ImageView
import com.stfalcon.chatkit.commons.ImageLoader

/**
 * Common app util methods.
 */
class AppUtils {
    object CustomImageLoader : ImageLoader {
        override fun loadImage(imageView: ImageView, url: String?, payload: Any?) {
            // TODO: set image.
        }
    }
}