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
        val calStr = "11 + 11"

        /* When */
        val result = calUseCase.invoke(calStr)

        /* Then */
//        verify(calUseCase).invoke(calStr)
        assertEquals(22, result)
    }


    @Test
    fun `문자열로 계산하는 빼기 테스트`() {
        /* Given */
        val calStr = "21 - 1"

        /* When */
        val result = calUseCase.invoke(calStr)

        /* Then */
//        verify(calUseCase).invoke(calStr)
        assertEquals(20, result)
    }

    @Test
    fun `문자열로 계산하는 나누기 테스트`() {
        /* Given */
        val calStr = "40 / 2"

        /* When */
        val result = calUseCase.invoke(calStr)

        /* Then */
//        verify(calUseCase).invoke(calStr)
        assertEquals(20, result)
    }

    @Test
    fun `문자열로 계산하는 곱하기 테스트`() {
        /* Given */
        val calStr = "30 * 5"

        /* When */
        val result = calUseCase.invoke(calStr)

        /* Then */
//        verify(calUseCase).invoke(calStr)
        assertEquals(150, result)
    }

    @Test
    fun `문자열로 계산하는 나머지 테스트`() {
        /* Given */
        val calStr = "31 % 5"

        /* When */
        val result = calUseCase.invoke(calStr)

        /* Then */
//        verify(calUseCase).invoke(calStr)
        assertEquals(1, result)
    }

    @Test
    fun `호환되지 않은 연산자 테스트`() {
        try {
            /* Given */
            val calStr = "3 테스트 5"

            /* When */
            val result = calUseCase.invoke(calStr)

            /* Then */
//            verify(calUseCase).invoke(calStr)
            assertEquals(15, result)
        } catch (e: NullPointerException) {
            assertEquals("테스트 연산자는 없습니다.", e.message)
        }
    }

    @Test
    fun `실수 계산 테스트`() {
        /* Given */
        val calStr = "3 / 7"

        /* When */
        val result = calUseCase.invoke(calStr)

        /* Then */
        assertEquals(0.4285, result)
    }

    @Test
    fun `여러개 연산자 테스트`() {
        /* Given */
        val calStr = "1 + 2 + 4"
        val calStr2 = "1 * 2 * 6 + 2"

        /* When */
        val result = calUseCase.invoke(calStr)
        val result2 = calUseCase.invoke(calStr2)

        /* Then */
        assertEquals(7, result)
        assertEquals(14, result2)
    }
}