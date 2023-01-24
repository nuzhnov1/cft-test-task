package com.sunman.binlist.data.network

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultCall<T>(private val delegate: Call<T>) : Call<Result<T?>> {
    override fun enqueue(delegateCallback: Callback<Result<T?>>) =
        delegate.enqueue(ResultCallback(this, delegateCallback))

    override fun execute(): Response<Result<T?>> = Response.success(
        Result.success(delegate.execute().body())
    )

    override fun isExecuted() = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled() = delegate.isCanceled

    override fun clone(): Call<Result<T?>> = ResultCall(delegate.clone())

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}
