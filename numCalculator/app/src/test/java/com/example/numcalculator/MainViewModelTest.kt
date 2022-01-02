package com.example.numcalculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.numcalculator.ext.getOrAwaitValue
import com.example.numcalculator.viewmodel.MainViewModel
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun `숫자 입력 테스트`() {
        val viewModel = MainViewModel()
        val num1 = "1"
        val num2 = "2"
        val num3 = "7"

        viewModel.addFormulaNum(num1)
        viewModel.addFormulaNum(num2)
        viewModel.addFormulaNum(num3)

        val result = viewModel.resultLiveData.getOrAwaitValue()
        assertEquals("127", result)
    }

    @Test
    fun `127이 입력되어있는 상태에서 +를 입력한다`() {
        val viewModel = MainViewModel()
        viewModel.addFormulaNum("1")
        viewModel.addFormulaNum("2")
        viewModel.addFormulaNum("7")
        val operation = "+"

        viewModel.addFormulaOperation(operation)

        val formula = viewModel.formulaLiveData.getOrAwaitValue()
        val operationResult = viewModel.operationLiveData.getOrAwaitValue()

        assertEquals("+", operationResult)
        assertEquals("127", formula)
    }

    @Test
    fun `127 + 인 상태에서 숫자를 입력한다`() {
        val viewModel = MainViewModel()
        viewModel.addFormulaNum("1")
        viewModel.addFormulaNum("2")
        viewModel.addFormulaNum("7")
        viewModel.addFormulaOperation("+")

        viewModel.addFormulaNum("2")
        viewModel.addFormulaNum("4")

        val result = viewModel.resultLiveData.getOrAwaitValue()
        assertEquals("24", result)
    }

    @Test
    fun `127 + 24에서 연산자를 입력한다`() {
        val viewModel = MainViewModel()
        viewModel.addFormulaNum("1")
        viewModel.addFormulaNum("2")
        viewModel.addFormulaNum("7")
        viewModel.addFormulaOperation("+")
        viewModel.addFormulaNum("2")
        viewModel.addFormulaNum("4")

        viewModel.addFormulaOperation("-")

        val operation = viewModel.operationLiveData.getOrAwaitValue()
        val formula = viewModel.formulaLiveData.getOrAwaitValue()
        val result = viewModel.resultLiveData.getOrAwaitValue()

        assertEquals("-", operation)
        assertEquals("127 + 24 = 151", formula)
        assertEquals("151", result)
    }

    @Test
    fun `127 + 24 = 151에서 숫자를 입력한다`() {
        val viewModel = MainViewModel()
        viewModel.addFormulaNum("1")
        viewModel.addFormulaNum("2")
        viewModel.addFormulaNum("7")
        viewModel.addFormulaOperation("+")
        viewModel.addFormulaNum("2")
        viewModel.addFormulaNum("4")
        viewModel.addFormulaOperation("-")

        viewModel.addFormulaNum("1")
        viewModel.addFormulaNum("2")

        val operation = viewModel.operationLiveData.getOrAwaitValue()
        val formula = viewModel.formulaLiveData.getOrAwaitValue()
        val result = viewModel.resultLiveData.getOrAwaitValue()

        assertEquals("-", operation)
        assertEquals("127 + 24 = 151", formula)
        assertEquals("12", result)
    }

    // TODO 여기부터 시작! 3번째 연산자 입력부터 문제가 생김
    @Test
    fun `127 + 24 - 12에서 연산자를 입력한다`() {
        val viewModel = MainViewModel()
        viewModel.addFormulaNum("1")
        viewModel.addFormulaNum("2")
        viewModel.addFormulaNum("7")
        viewModel.addFormulaOperation("+")
        viewModel.addFormulaNum("2")
        viewModel.addFormulaNum("4")
        viewModel.addFormulaOperation("-")
        viewModel.addFormulaNum("1")
        viewModel.addFormulaNum("2")
        viewModel.addFormulaOperation("*")

        val operation = viewModel.operationLiveData.getOrAwaitValue()
        val formula = viewModel.formulaLiveData.getOrAwaitValue()
        val result = viewModel.resultLiveData.getOrAwaitValue()

        assertEquals("*", operation)
        assertEquals("127 + 24 - 12 = 139", formula)
        assertEquals("139", result)
    }

    @Test
    fun `1 + 2에서 =를 입력헸을때`() {
        val viewModel = MainViewModel()
        viewModel.addFormulaNum("1")
        viewModel.addFormulaOperation("+")
        viewModel.addFormulaNum("2")

        viewModel.addFormulaResult()

        val operation = viewModel.operationLiveData.getOrAwaitValue()
        val formula = viewModel.formulaLiveData.getOrAwaitValue()
        val result = viewModel.resultLiveData.getOrAwaitValue()

        assertEquals("=", operation)
        assertEquals("1 + 2", formula)
        assertEquals("3", result)
    }

    @Test
    fun `현재 입력된 숫자 초기화`() {
        val viewModel = MainViewModel()
        viewModel.addFormulaNum("1")
        viewModel.addFormulaNum("2")
        viewModel.addFormulaNum("3")

        viewModel.clearFormula()

        val result = viewModel.resultLiveData.getOrAwaitValue()

        assertEquals("", result)
    }
}