package com.axxesscodingchallenge.utils

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.axxesscodingchallenge.R
import com.squareup.picasso.Picasso

infix fun ImageView.loadUrl(url: String) {
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.ic_baseline_search_24)
        /*.centerCrop()
        .fit()*/
        .into(this)
}
fun <T> AppCompatActivity.createIntent(className: Class<T>, bundle: Bundle? = null) {
    val intent = Intent(this, className)
    bundle?.let {
        intent.putExtras(it)
    }
    startActivity(intent)
}

fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

/**
 * Manage Visibility of TextInputLayout.
 * @param view :- according to view this function set Visibily of TextInputLayout.
 */
infix fun View.setVisible(visibility: Int) {
    this.visibility = visibility
}

