package com.example.numcalculator.usecase

class CalUseCase: UseCase<String, Int>() {
    override fun run(parm: String): Int {
        return parm.split(" ").let {
            Operation.find(it[1]).calculate(it[0], it[2]).toInt()
        }
    }
}

enum class Operation(val operation: String, val calculate: (String, String) -> Number) {
    PLUS("더하기", {num1, num2 -> num1.toInt() + num2.toInt()}),
    MINUS("빼기", {num1, num2 -> num1.toInt() - num2.toInt()}),
    PRODUCT("곱하기", {num1, num2 -> num1.toInt() * num2.toInt()}),
    DIVIDE("나누기", {num1, num2 -> num1.toInt() / num2.toInt()});

    companion object {
        fun find(operation: String): Operation {
            return values().find { it.operation == operation }
                ?: throw NullPointerException("$operation 연산자는 없습니다.")
        }
    }
}