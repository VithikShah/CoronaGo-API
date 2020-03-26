package com.example.coronago.utils

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.coronago.R
import com.example.coronago.utils.GlideApp


@BindingAdapter("app:image_url")
fun loadImage(view: ImageView, url: Uri?) {
    Log.v("inside load image", url.toString())
    if (url != null) {
        Glide.with(view)
            .load(url)
            .into(view)
    } else {
        view.setImageResource(R.drawable.ic_male_user_profile_picture)
    }
}