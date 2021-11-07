package com.demo.albums.utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import com.google.android.material.snackbar.Snackbar

/*Setting up the Common class- to access the methods across the application*/
object Common {
    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
    fun getSnackBar(message: String?, duration: Int, view: View?): Snackbar {
        return Snackbar
            .make(view!!, message!!, duration)
    }
}
