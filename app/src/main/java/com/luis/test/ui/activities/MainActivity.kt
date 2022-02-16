package com.luis.test.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.luis.test.R
import com.luis.test.data.model.Response
import com.luis.test.databinding.ActivityMainBinding
import com.luis.test.helper.showCustomSnackBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView( this, R.layout.activity_main)
        viewModel = ViewModelProvider( this)[MainActivityVM::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.data.observe( this,{
            val name = "${it.results[0].name.title} ${it.results[0].name.first} ${it.results[0].name.last}"
            binding.lbBodyName.text = name
            val address = "${it.results[0].location.street.name} #${it.results[0].location.street.number}, ${it.results[0].location.state}, ${it.results[0].location.country}"
            binding.lbBodyAddress.text = address
        })
        viewModel.isLoading.observe( this, {
            binding.lpLoadingApi.visibility = if( it) View.VISIBLE else View.GONE
        })

        binding.imgReloadData.setOnClickListener {
            lifecycleScope.launch( Dispatchers.IO){
                withContext( Dispatchers.Main){
                    viewModel.setIsLoading( true)
                }
                val data: Response? = viewModel.getNewRandomUser()
                if( data != null) viewModel.setData(data)
                else baseContext.showCustomSnackBar( binding.root, "Error retreiving data from server, please try it again.")
                withContext( Dispatchers.Main){
                    viewModel.setIsLoading( false)
                }
            }
        }
    }

}