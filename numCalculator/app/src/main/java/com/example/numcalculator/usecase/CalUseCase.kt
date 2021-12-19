package com.example.numcalculator.usecase

import kotlin.math.floor
import kotlin.math.roundToInt

class CalUseCase: UseCase<String, Number>() {
    override fun run(parm: String): Number {
        return parm.split(" ").let {
            Operation.find(it[1]).calculate(it[0], it[2]).toRound()
        }
    }
}

enum class Operation(val operation: String, val calculate: (String, String) -> Double) {
    PLUS("+", {num1, num2 -> num1.toDouble() + num2.toDouble()}),
    MINUS("-", {num1, num2 -> num1.toDouble() - num2.toDouble()}),
    PRODUCT("*", {num1, num2 -> num1.toDouble() * num2.toDouble()}),
    DIVIDE("/", {num1, num2 -> num1.toDouble() / num2.toDouble()});

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