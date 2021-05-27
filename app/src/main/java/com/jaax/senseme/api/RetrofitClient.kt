package com.jaax.senseme.api

import com.jaax.senseme.utils.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private val client by lazy {
        OkHttpClient
            .Builder()
            .connectTimeout( 1, TimeUnit.MINUTES )
            .readTimeout( 30, TimeUnit.SECONDS )
            .writeTimeout( 30, TimeUnit.SECONDS )
            .build()
    }

    private val retrofitClient by lazy {
        Retrofit
            .Builder()
            .baseUrl( Constantes.API_BASE_URL )
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
    }


    fun <T> getService( service: Class<T> ): T {
        return retrofitClient.create( service )
    }
}