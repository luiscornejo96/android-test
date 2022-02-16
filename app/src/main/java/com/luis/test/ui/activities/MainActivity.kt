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
import com.luis.test.helper.fadeIn
import com.luis.test.helper.fadeOut
import com.luis.test.helper.showCustomSnackBar
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityVM

    companion object{
        private const val DELAY_TIME = 1000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView( this, R.layout.activity_main)
        viewModel = ViewModelProvider( this)[MainActivityVM::class.java]

        // To update the new data automatically with data binding //
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Observe the object to set the regarding labels //
        viewModel.data.observe( this) {
            val name =
                "${it.results[0].name.title} ${it.results[0].name.first} ${it.results[0].name.last}"
            binding.lbBodyName.text = name
            val address =
                "${it.results[0].location.street.name} #${it.results[0].location.street.number}, ${it.results[0].location.state}, ${it.results[0].location.country}"
            binding.lbBodyAddress.text = address
            Picasso.get().load( it.results[0].picture.large).into( binding.imgProfile)
        }

        viewModel.isLoading.observe( this) {
            binding.lpLoadingApi.visibility = if (it) View.VISIBLE else View.GONE
        }

        binding.imgReloadData.setOnClickListener {
            getDataFromApi()
        }
        getDataFromApi( true)

    }

    /**
     * Make the logic to show/get data request from server
     * @param isFirst: Boolean -> the first call needs to be immediately.
     */
    private fun getDataFromApi( isFirst: Boolean = false){
        lifecycleScope.launch( Dispatchers.IO){
            if( !isFirst) delay( DELAY_TIME)
            withContext( Dispatchers.Main){
                viewModel.setIsLoading( true)
                binding.llData.startAnimation( baseContext.fadeOut)
                binding.llData.visibility = View.GONE
            }
            val data: Response? = viewModel.getNewRandomUser()
            // To appreciate the progress bar indicator //
            if( data != null) {
                withContext( Dispatchers.Main){
                    viewModel.setData(data)
                    baseContext.showCustomSnackBar( binding.root, "Information was requested successfully.")
                }
            }
            else baseContext.showCustomSnackBar( binding.root, "Error retrieving data from server, please try it again.")
            withContext( Dispatchers.Main){
                viewModel.setIsLoading( false)
                binding.llData.visibility = View.VISIBLE
                binding.llData.startAnimation( baseContext.fadeIn)
            }
        }
    }

}