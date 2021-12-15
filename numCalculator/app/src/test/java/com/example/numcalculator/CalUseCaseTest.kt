package com.example.numcalculator

import com.example.numcalculator.usecase.CalUseCase
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

class CalUseCaseTest {

    private val calUseCase = CalUseCase()

    @Test
    fun `문자열로 계산하는 더하기 테스트`() {
        /* Given */
        val calStr = "1 더하기 1"

        /* When */
        val result = calUseCase.invoke(calStr)

        /* Then */
//        verify(calUseCase).invoke(calStr)
        assertEquals(2, result)
    }


    @Test
    fun `문자열로 계산하는 빼기 테스트`() {
        /* Given */
        val calStr = "2 빼기 1"

        /* When */
        val result = calUseCase.invoke(calStr)

        /* Then */
//        verify(calUseCase).invoke(calStr)
        assertEquals(1, result)
    }

    @Test
    fun `문자열로 계산하는 나누기 테스트`() {
        /* Given */
        val calStr = "4 나누기 2"

        /* When */
        val result = calUseCase.invoke(calStr)

        /* Then */
//        verify(calUseCase).invoke(calStr)
        assertEquals(2, result)
    }

    @Test
    fun `문자열로 계산하는 곱하기 테스트`() {
        /* Given */
        val calStr = "3 곱하기 5"

        /* When */
        val result = calUseCase.invoke(calStr)

        /* Then */
//        verify(calUseCase).invoke(calStr)
        assertEquals(15, result)
    }
}