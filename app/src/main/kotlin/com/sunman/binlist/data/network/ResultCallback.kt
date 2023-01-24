package com.sunman.binlist.data.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class ResultCallback<T>(
    private val resultCall: ResultCall<T>,
    private val delegate: Callback<Result<T?>>
) : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) = when {
        response.isSuccessful || response.code() == 404 -> delegate.onResponse(
            resultCall,
            Response.success(Result.success(response.body()))
        )

        else -> delegate.onResponse(
            resultCall,
            Response.success(Result.failure(HttpException(response)))
        )
    }

    override fun onFailure(call: Call<T>, t: Throwable) = delegate.onResponse(
        resultCall,
        Response.success(Result.failure(t))
    )
}
