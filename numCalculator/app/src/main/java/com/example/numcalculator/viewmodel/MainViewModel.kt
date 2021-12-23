package com.example.numcalculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel: BaseViewModel() {

    val formulaStr = StringBuffer()
//    val formulaStr: String get() = _formulaStr.toString()

    fun addFormulaText(str: String) {
        formulaStr.append(str)
    }
}