package com.luis.test.data.repository

import android.util.Log
import com.luis.test.data.api.ResponseApi
import com.luis.test.data.model.Response
import com.luis.test.network.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback

class ResponseRepository {

    companion object{
        private const val TAG = "ResponseRepository"
    }

    private val api: ResponseApi by lazy { ServiceGenerator.createService( ResponseApi::class.java)}

    /**
     * Get a sync response from API and handle it
     */
    fun getNewRandomUserApi(): Response?{
       val data = api.getNewRandomUser()
        data.execute().body()?.let {
            Log.i( TAG, it.toString())
            return it
        }?: kotlin.run { return null }
    }
}