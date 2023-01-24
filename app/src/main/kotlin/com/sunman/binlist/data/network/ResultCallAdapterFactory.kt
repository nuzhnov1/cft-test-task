package com.sunman.binlist.data.network

import com.squareup.moshi.rawType
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java || returnType !is ParameterizedType) {
            return null
        }

        val upperBound = getParameterUpperBound(0, returnType)

        return if (upperBound.rawType == Result::class.java && upperBound is ParameterizedType) {
            object : CallAdapter<Any, Call<Result<*>>> {
                override fun responseType() = getParameterUpperBound(0, upperBound)

                override fun adapt(call: Call<Any>): Call<Result<*>> = ResultCall(call)
            }
        } else {
            null
        }
    }
}
