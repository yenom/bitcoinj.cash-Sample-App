package yenom.tech.bitcoinjcashsample.presentation.util

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.widget.ImageView

class DataBindingUtils {
    companion object {
        @JvmStatic
        @BindingAdapter("bind:imageBitmap")
        fun setImageBitmap(view: ImageView, bitmap: Bitmap?) {
            view.setImageBitmap(bitmap)
        }
    }
}