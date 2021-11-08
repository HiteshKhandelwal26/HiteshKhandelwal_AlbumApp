package com.demo.albums.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import com.google.android.material.snackbar.Snackbar

/*Setting up the Common class- to access the methods across the application*/
object Common {

    fun isNetworkConnected(context: Context) =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            getNetworkCapabilities(activeNetwork)?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } ?: false
        }

    fun getSnackBar(message: String?, duration: Int, view: View?): Snackbar {
        return Snackbar
            .make(view!!, message!!, duration)
    }
}
