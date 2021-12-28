package com.example.numcalculator.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.numcalculator.usecase.CalUseCase

class MainViewModel: BaseViewModel() {

    private val useCase = CalUseCase()

    private val _formulaStrLivaData = MutableLiveData<String>()
    val formulaStrLiveData: LiveData<String>
        get() = _formulaStrLivaData

    private val _resultLiveData = MutableLiveData<String>()
    val resultLiveData: LiveData<String>
        get() = _resultLiveData

    fun addFormulaText(str: String) {
        _formulaStrLivaData.value = _formulaStrLivaData.value?.let {
            "$it $str"
        } ?: let { str }
    }

    fun formulaResult() {
        formulaStrLiveData.value?.let {
            _resultLiveData.value = useCase.run(it).toString()
            _formulaStrLivaData.value = _formulaStrLivaData.value + " = " + resultLiveData.value
        }
    }
}