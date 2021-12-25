package com.example.numcalculator.usecase

import com.example.numcalculator.CalToken
import com.example.numcalculator.calResult
import kotlin.math.floor
import kotlin.math.roundToInt

class CalUseCase: UseCase<String, Number>() {
    override fun run(parm: String): Number {

        val tokenList: List<CalToken> = parm.split(" ").map {
            CalToken(it)
        }

        return tokenList.calResult()
    }
}

enum class Operation(val operation: String, val calculate: (Double, Double) -> Double) {
    PLUS("+", {num1, num2 -> num1 + num2}),
    MINUS("-", {num1, num2 -> num1 - num2}),
    PRODUCT("*", {num1, num2 -> num1 * num2}),
    DIVIDE("/", {num1, num2 -> num1 / num2});

    companion object {
        fun find(operation: String): Operation {
            return values().find { it.operation == operation }
                ?: throw NullPointerException("$operation 연산자는 없습니다.")
        }
    }
}

fun Double.toRound(): Number {
    return if(mod(roundToInt().toDouble()) == 0.0) {
        roundToInt()
    } else {
        floor(this * 10000) / 10000
    }
}