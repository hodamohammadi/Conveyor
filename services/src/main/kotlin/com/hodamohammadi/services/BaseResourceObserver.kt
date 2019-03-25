package com.hodamohammadi.services

import android.arch.lifecycle.Observer
import android.support.annotation.CallSuper

/**
 * Base class for service call observers.
 */
open class BaseResourceObserver<T>: Observer<Resource<T>> {
    override fun onChanged(resource: Resource<T>?) {
        if (resource != null) {
            when (resource.status) {
                Resource.Status.LOADING -> {
                    onLoading()
                }
                Resource.Status.SUCCESS -> {
                    onSuccess(resource.data)
                }
                Resource.Status.ERROR ->{
                    onError()
                }
            }
        }
    }

    @CallSuper
    open fun onLoading() {
        // TODO: implement loading indicator.
    }

    @CallSuper
    open fun onSuccess(data: T?) {
        // TODO: hide loading indicator.
    }

    @CallSuper
    open fun onError() {
        // TODO: hide loading indicator.
    }
}