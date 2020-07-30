package com.g3.kiwi.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImageFromURL(url: String?) {
    Glide.with(this).load(url).into(this)
}