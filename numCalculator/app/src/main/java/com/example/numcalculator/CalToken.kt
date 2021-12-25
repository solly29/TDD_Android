package com.example.numcalculator

import com.example.numcalculator.usecase.Operation
import com.example.numcalculator.usecase.toRound
import java.lang.Exception

class CalToken(var token: String) {

    var operation: Operation? = null
    private val list = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")

    init {
        operation = try {
            Operation.find(token)
        } catch (e: NullPointerException) {
            if(!list.contains(token)) {
                throw NullPointerException(e.message)
            }
            null
        }
    }
}

fun List<CalToken>.calResult(): Number {
    forEachIndexed { index, calToken ->
        calToken.operation?.let {
            get(index + 1).token = it.calculate(this[index - 1].token.toDouble(), get(index + 1).token.toDouble()).toString()
        }
    }

    return get(size - 1).token.toDouble().toRound()
}