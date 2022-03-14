package com.t4zb.edvora.modelLayer.rest.service.request

import android.content.Context
import com.t4zb.edvora.modelLayer.network.cache.CacheClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    private var retrofit: Retrofit? = null

    fun buildRetrofit(context: Context): Retrofit? {

        val client = CacheClient.createCachedClient(context)
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl("https://assessment.api.vweb.app")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}
