package com.example.numcalculator.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel: BaseViewModel() {

    private val _formulaStrLivaData = MutableLiveData<String>()
    val formulaStrLiveData: LiveData<String>
        get() = _formulaStrLivaData

    fun addFormulaText(str: String) {
        _formulaStrLivaData.value = _formulaStrLivaData.value?.let {
            "$it $str"
        } ?: let { str }
    }
}