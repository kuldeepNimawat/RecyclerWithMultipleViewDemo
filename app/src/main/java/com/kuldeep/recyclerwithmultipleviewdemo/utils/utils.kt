package com.kuldeep.recyclerwithmultipleviewdemo.utils

import android.app.Activity
import android.net.Uri
import android.view.View
import com.facebook.drawee.view.SimpleDraweeView
import com.google.android.material.snackbar.Snackbar

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun SimpleDraweeView.loadImage(url: String){
    this.setImageURI(Uri.parse(url))
}

fun Activity.snackbar(msg: String, action :(()->Unit)?=null){
    Snackbar.make(
    findViewById(android.R.id.content),
    msg,
    Snackbar.LENGTH_LONG).also{
        it.setAction(
            "Ok"
        ){ action?.invoke() }
    }.show()
}