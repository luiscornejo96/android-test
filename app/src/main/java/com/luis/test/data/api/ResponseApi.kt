package com.luis.test.data.api

import com.luis.test.data.model.Response
import retrofit2.Call
import retrofit2.http.GET

interface ResponseApi {

    /**
     * GET Method, get a new random user from API
     */
    @GET("/api/")
    fun getNewRandomUser(): Call<Response>
}