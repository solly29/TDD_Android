package com.example.numcalculator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numcalculator.R
import com.example.numcalculator.databinding.ActivityMainBinding
import com.example.numcalculator.viewmodel.MainViewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel = MainViewModel()

    override fun initStartView() {
        binding.viewmodel = viewModel
    }

    override fun initDataBinding() {

    }

    override fun initEvent() {

    }
}