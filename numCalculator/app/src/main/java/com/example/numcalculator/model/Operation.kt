package com.example.numcalculator.model

import com.example.numcalculator.ext.toRound

enum class Operation(val operation: String, val calculate: (Number, Number) -> Number) {
    PLUS("+", { num1, num2 -> (num1.toDouble() + num2.toDouble()).toRound()}),
    MULTIPLY("*", { num1, num2 -> (num1.toDouble() * num2.toDouble()).toRound()}),
    MINUS("-", { num1, num2 -> (num1.toDouble() - num2.toDouble()).toRound()}),
    DIVIDE("/", { num1, num2 -> (num1.toDouble() / num2.toDouble()).toRound()}),
    REMAINDER("%", { num1, num2 -> (num1.toDouble() % num2.toDouble()).toRound()});

    companion object {
        fun findOperation(operation: String): Operation {
            return values().find { it.operation == operation } ?: throw NullPointerException("$operation 연산자가 없습니다.")
        }
    }
}