package com.luis.test.ui.activities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luis.test.data.model.Response
import com.luis.test.data.repository.ResponseRepository

class MainActivityVM( app: Application): AndroidViewModel( app) {

    private val responseRepository: ResponseRepository by lazy { ResponseRepository() }

    private val _data = MutableLiveData<Response>()
    val data: LiveData<Response> = _data

    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isLoading: LiveData<Boolean> =  _isLoading

    /**
     * Get new user object from API
     * @param data: Response
     */
    fun setData( data: Response) {
        _data.value = data
    }

    /**
     * Get a new random user from call API
     */
    fun getNewRandomUser(): Response? = responseRepository.getNewRandomUserApi()

    /**
     * Set loading live data yo show/hide progress indicator
     * @param: isLoading: Boolean
     */
    fun setIsLoading( isLoading: Boolean){
        _isLoading.value = isLoading
    }
}