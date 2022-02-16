package com.luis.test.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object ServiceGenerator {

    private const val BASE_URL = "https://randomuser.me"
    private const val CONNECTION_TIME_OUT = 30L
    private const val READ_TIME_OUT = 20L
    private const val WRITE_TIME_OUT = 25L

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)

    private lateinit var retrofit: Retrofit

    /**
     * @param service: ApiService class
     * @return Retrofit <T>: Return the retrofit builder with the regarding the service
     */
    fun <T> createService(service: Class<T>): T {
        builder.client(httpClient.build())
        retrofit = builder.build()

        return retrofit.create(service)
    }
}