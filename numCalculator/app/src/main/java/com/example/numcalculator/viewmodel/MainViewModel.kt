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

    private val _operationLiveData = MutableLiveData<String>("")
    val operationLiveData: LiveData<String> get() = _operationLiveData

    private val _formulaLiveData = MutableLiveData<String>()
    val formulaLiveData: LiveData<String> get() = _formulaLiveData

    private val _operString = MutableLiveData("AC")
    val operString: LiveData<String> get() = _operString

    private var formulaResult: Number? = null

    private var formula: String = ""

    private var isInputOper = false

    fun addFormulaNum(num: String) {
        _operString.value = "C"
        _resultLiveData.value = if(isInputOper) {
            isInputOper = false
            num
        } else {
            (resultLiveData.value ?: "") + num
        }
    }

    fun addFormulaOperation(operation: String) {
        _operString.value = "C"
        formulaResult?.let { num1 ->
            if(operationLiveData.value != "=") {
                val calNumberData = resultLiveData.value.toTypeCasting()?.let { num2 ->
                    val op = Operation.findOperation(operationLiveData.value ?: "")
                    CalNumberData(num1, num2, op)
                }

                formulaResult = calNumberData?.let {
                    useCase.invoke(it)
                } ?: return@let

                formula += " ${operationLiveData.value} ${resultLiveData.value}"
            }

            _formulaLiveData.value = "$formula = $formulaResult"
            _resultLiveData.value = formulaResult.toString()
        } ?: run{
            if(resultLiveData.value == null || resultLiveData.value == "")
                return
            formulaResult = resultLiveData.value.toTypeCasting()
            formula = resultLiveData.value ?: ""
            _formulaLiveData.value = resultLiveData.value
        }
        _operationLiveData.value = operation
        isInputOper = true
    }

    fun addFormulaResult() {
        _operString.value = "AC"
        if(operationLiveData.value != "=" && operationLiveData.value != null && operationLiveData.value != "") {
            formulaResult?.let {
                val calNumberData = resultLiveData.value.toTypeCasting()?.let { num2 ->
                    val op = Operation.findOperation(operationLiveData.value ?: "")
                    CalNumberData(it , num2, op)
                }

                formulaResult = calNumberData?.let {
                    useCase.invoke(it)
                } ?: return@let
            }

            formula = "$formula ${operationLiveData.value} ${resultLiveData.value}"
            _formulaLiveData.value = formula
            _operationLiveData.value = "="
            _resultLiveData.value = formulaResult.toString()
        }
    }

    fun clearFormula() {
        if(operString.value == "AC") {
            _operationLiveData.value = ""
            _formulaLiveData.value = ""
            _resultLiveData.value = ""
            formulaResult = null
            formula = ""
        } else if(operString.value == "C") {
            _operString.value = "AC"
            _resultLiveData.value = ""
        }
    }
}