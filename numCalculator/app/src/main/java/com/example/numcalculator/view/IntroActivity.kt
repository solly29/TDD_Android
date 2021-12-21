package com.example.numcalculator.view

import android.content.Intent
import com.example.numcalculator.R
import com.example.numcalculator.databinding.ActivityIntroBinding
import com.example.numcalculator.viewmodel.BaseViewModel
import kotlinx.coroutines.*

class IntroActivity: BaseActivity<BaseViewModel, ActivityIntroBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_intro
    override val viewModel: BaseViewModel = BaseViewModel()
    private val coroutine = CoroutineScope(Dispatchers.Main)

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initEvent() {
        coroutine.launch {
            delay(2000)
            startActivity(
                Intent(this@IntroActivity, MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }
}