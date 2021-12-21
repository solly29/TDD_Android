package com.example.numcalculator.view

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.numcalculator.viewmodel.BaseViewModel

abstract class BaseActivity<V: BaseViewModel, B: ViewDataBinding>: AppCompatActivity() {

    abstract val layoutId: Int
    abstract val viewModel: V
    lateinit var binding: B

    abstract fun initStartView()

    abstract fun initDataBinding()

    abstract fun initEvent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this

        initStartView()

        initDataBinding()
        initEvent()
    }
}