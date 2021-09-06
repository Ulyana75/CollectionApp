package com.ulyanaab.collectionsapp2.utilities

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import coil.load
import com.ulyanaab.collectionsapp2.R

fun ImageView.downloadAndSetPhoto(url: String, callback: () -> Unit = {}) {
    this.load(url) {
        placeholder(R.drawable.placeholder)
        listener(
            onSuccess = { _, _ ->
                callback()
            },
            onError = { _, _ ->
                callback()
            }
        )
    }
}

fun showToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_SHORT).show()
}

fun calculateImageSizeInPX(
    context: Context,
    spanCount: Int,
    offsetLength: Int,
    defaultWith: Int,
    defaultHeight: Int
): Pair<Int, Int> {
    val dpi = context.resources.displayMetrics.densityDpi
    val widthDp = convertToDP(context.resources.displayMetrics.widthPixels, dpi)
    val imgWidth = (widthDp - (spanCount + 1) * offsetLength) / spanCount
    val imgHeightInPx =
        convertToPX((imgWidth * defaultHeight / (defaultWith * 1.0)).toInt(), dpi)

    return Pair(
        convertToPX(imgWidth, dpi),
        imgHeightInPx
    )
}

fun convertToDP(value: Int, dpi: Int): Int {
    return (value / (dpi / 160.0)).toInt()
}

fun convertToPX(value: Int, dpi: Int): Int {
    return (value * (dpi / 160.0)).toInt()
}