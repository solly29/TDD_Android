package com.example.numcalculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.numcalculator.ext.getOrAwaitValue
import com.example.numcalculator.viewmodel.MainViewModel
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    // TODO +/-의 계산 로직을 추가해야됨

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

    @Test
    fun `=으로 계산 후 - 연산자 입력 테스트`() {
        val viewModel = MainViewModel()
        viewModel.addFormulaNum("1")
        viewModel.addFormulaOperation("+")
        viewModel.addFormulaNum("2")
        viewModel.addFormulaResult()

        viewModel.addFormulaOperation("-")

        val operation = viewModel.operationLiveData.getOrAwaitValue()
        val formula = viewModel.formulaLiveData.getOrAwaitValue()
        val result = viewModel.resultLiveData.getOrAwaitValue()

        assertEquals("-", operation)
        assertEquals("1 + 2 = 3", formula)
        assertEquals("3", result)
    }

    @Test
    fun `모든 계산식, 숫자 초기화`() {
        val viewModel = MainViewModel()
        viewModel.addFormulaNum("1")
        viewModel.addFormulaNum("2")
        viewModel.addFormulaNum("7")
        viewModel.addFormulaOperation("+")
        viewModel.addFormulaNum("2")
        viewModel.addFormulaNum("4")
        viewModel.addFormulaOperation("-")
        viewModel.addFormulaNum("1")
        viewModel.addFormulaNum("4")

        viewModel.clearFormula()
        viewModel.clearFormula()

        val operString = viewModel.operString.getOrAwaitValue()
        val operation = viewModel.operationLiveData.getOrAwaitValue()
        val formula = viewModel.formulaLiveData.getOrAwaitValue()
        val result = viewModel.resultLiveData.getOrAwaitValue()

        assertEquals("AC", operString)
        assertEquals("", operation)
        assertEquals("", formula)
        assertEquals("", result)
    }

    @Test
    fun `AC로 모든 계산식을 초기화 후 숫자, 연산자 입력 테스트`() {
        val viewModel = MainViewModel()
        viewModel.addFormulaNum("1")
        viewModel.addFormulaOperation("+")
        viewModel.addFormulaNum("1")
        viewModel.addFormulaResult()
        viewModel.clearFormula()

        viewModel.addFormulaNum("1")
        viewModel.addFormulaOperation("-")
        viewModel.addFormulaNum("2")
        viewModel.addFormulaResult()

        val operation = viewModel.operationLiveData.getOrAwaitValue()
        val formula = viewModel.formulaLiveData.getOrAwaitValue()
        val result = viewModel.resultLiveData.getOrAwaitValue()

        assertEquals("=", operation)
        assertEquals("1 - 2", formula)
        assertEquals("-1", result)
    }

    @Test
    fun `연산자 먼저 입력하는 테스트`() {
        val viewModel = MainViewModel()

        viewModel.addFormulaOperation("+")

        val operation = viewModel.operationLiveData.getOrAwaitValue()

        assertEquals("", operation)
    }

    @Test
    fun `음수 입력 테스트`() {
        val viewModel = MainViewModel()

        viewModel.addFormulaNum("1")
        viewModel.addMinus("+/-")
        viewModel.addFormulaOperation("+")
        viewModel.addFormulaNum("2")

        val operation = viewModel.operationLiveData.getOrAwaitValue()
        val result = viewModel.resultLiveData.getOrAwaitValue()
        val formula = viewModel.formulaLiveData.getOrAwaitValue()

        assertEquals("+", operation)
        assertEquals("2", result)
        assertEquals("-1", formula)
    }
}