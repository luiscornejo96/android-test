package com.luis.test.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.luis.test.R
import com.luis.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}