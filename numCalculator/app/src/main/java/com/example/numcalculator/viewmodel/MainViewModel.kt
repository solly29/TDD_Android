package com.example.numcalculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.numcalculator.ext.toRound
import com.example.numcalculator.ext.toTypeCasting
import com.example.numcalculator.model.CalNumberData
import com.example.numcalculator.model.Operation
import com.example.numcalculator.usecase.CalUseCase

class MainViewModel: BaseViewModel() {

    private val useCase = CalUseCase()

    private val _resultLiveData = MutableLiveData<String>()
    val resultLiveData: LiveData<String> get() = _resultLiveData

    private val _operationLiveData = MutableLiveData<String>()
    val operationLiveData: LiveData<String> get() = _operationLiveData

    private val _formulaLiveData = MutableLiveData<String>()
    val formulaLiveData: LiveData<String> get() = _formulaLiveData

    private var formulaResult: Number? = null

    private var isInputOper = false

    fun addFormulaNum(num: String) {
        _resultLiveData.value = if(isInputOper) {
            isInputOper = false
            num
        } else {
            (resultLiveData.value ?: "") + num
        }
    }

    fun addFormulaOperation(operation: String) {
        formulaResult?.let { num1 ->
            val calNumberData = resultLiveData.value.toTypeCasting()?.let { num2 ->
                val op = Operation.findOperation(operationLiveData.value ?: "")
                CalNumberData(num1, num2, op)
            }

            formulaResult = calNumberData?.let {
                useCase.invoke(it)
            } ?: return@let


            _formulaLiveData.value = "${formulaLiveData.value} ${operationLiveData.value} ${resultLiveData.value} = $formulaResult"
            _resultLiveData.value = formulaResult.toString()
        } ?: run{
            formulaResult = resultLiveData.value.toTypeCasting()
            _formulaLiveData.value = resultLiveData.value
        }
        _operationLiveData.value = operation
        isInputOper = true
    }
}