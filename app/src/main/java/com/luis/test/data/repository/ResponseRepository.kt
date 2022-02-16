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


    fun getNewRandomUserApi(): Response?{
        var data: Response? = null
        api.getNewRandomUser().enqueue( object: Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                data =
                    if( response.isSuccessful) {
                        response.body()
                    }
                    else
                    {
                        Log.e( TAG, "Error ->: ${response.errorBody().toString()}")
                        null
                    }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                data = null
                Log.e( TAG, "Error ->: $t")
            }

        })
        return data
    }
}