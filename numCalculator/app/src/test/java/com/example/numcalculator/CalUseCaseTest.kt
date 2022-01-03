package com.example.numcalculator

import com.example.numcalculator.model.CalNumberData
import com.example.numcalculator.model.Operation
import com.example.numcalculator.usecase.CalUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * 계산 유즈케이스 테스트
 */
class CalUseCaseTest {

    @Test
    fun `1 + 1 = 2를 테스트하는 테스트케이스`() {
        val useCase = CalUseCase()
        val num1 = 1
        val num2 = 1
        val operationStr = "+"
        val operation = Operation.findOperation(operationStr)

        val result = useCase.invoke(CalNumberData(num1, num2, operation))

        assertEquals(2L, result)
    }

    @Test
    fun `3 * 3 = 9를 테스트하는 테스트케이스`() {
        val useCase = CalUseCase()
        val num1 = 3
        val num2 = 3
        val operationStr = "*"
        val operation = Operation.findOperation(operationStr)

        val result = useCase.invoke(CalNumberData(num1, num2, operation))

        assertEquals(9L, result)
    }

    @Test
    fun `100점32 - 3 = 97를 테스트하는 테스트케이스`() {
        val useCase = CalUseCase()
        val num1 = 100.32
        val num2 = 3
        val operationStr = "-"
        val operation = Operation.findOperation(operationStr)

        val result = useCase.invoke(CalNumberData(num1, num2, operation))

        assertEquals(97.32, result)
    }

    @Test
    fun `23 나누기 2 = 11점5를 테스트하는 테스트케이스`() {
        val useCase = CalUseCase()
        val num1 = 23
        val num2 = 2
        val operationStr = "/"
        val operation = Operation.findOperation(operationStr)

        val result = useCase.invoke(CalNumberData(num1, num2, operation))

        assertEquals(11.5, result)
    }

    @Test
    fun `23 % 2 = 1를 테스트하는 테스트케이스`() {
        val useCase = CalUseCase()
        val num1 = 23
        val num2 = 2
        val operationStr = "%"
        val operation = Operation.findOperation(operationStr)

        val result = useCase.invoke(CalNumberData(num1, num2, operation))

        assertEquals(1L, result)
    }

    @Test
    fun `1 - 100를 테스트하는 테스트케이스`() {
        val useCase = CalUseCase()
        val num1 = 1
        val num2 = 100
        val operationStr = "-"
        val operation = Operation.findOperation(operationStr)

        val result = useCase.invoke(CalNumberData(num1, num2, operation))

        assertEquals(-99L, result)
    }
}