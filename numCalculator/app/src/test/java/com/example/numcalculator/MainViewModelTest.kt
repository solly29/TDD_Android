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

    private val viewModel = MainViewModel()

    @Test
    fun `계산기 입력 테스트`() {
        /* Given */
        var text = "1"
        var text2 = "+"

        /* When */
        viewModel.addFormulaText(text)
        viewModel.addFormulaText(text2)

        /* Then */
        val result = viewModel.formulaStrLiveData.getOrAwaitValue()
        assertEquals(result, "1 +")
    }

    @Test
    fun `문자열 계산 테스트`() {
        val str = "1 + 2 + 3"

        viewModel.addFormulaText(str)
        viewModel.formulaResult()

        val result = viewModel.resultLiveData.getOrAwaitValue()
        assertEquals(result, "6")
    }
}