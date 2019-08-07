package com.furkanaskin.app.podpocket.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created by Furkan on 2019-07-20
 */

inline fun <T : Any> LiveData<T>.observeWith(
    lifecycleOwner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
) {
    observe(
        lifecycleOwner,
        Observer {
            it ?: return@Observer
            onChanged.invoke(it)
        }
    )
}