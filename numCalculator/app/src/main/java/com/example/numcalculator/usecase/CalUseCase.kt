package com.example.numcalculator.usecase

class CalUseCase: UseCase<String, Int>() {
    override fun run(parm: String): Int {
        return parm.split(" ").let {
            when(it[1]) {
                "더하기" -> {
                    it[0].toInt() + it[2].toInt()
                }
                "빼기" -> {
                    it[0].toInt() - it[2].toInt()
                }
                "곱하기" -> {
                    it[0].toInt() * it[2].toInt()
                }
                "나누기" -> {
                    it[0].toInt() / it[2].toInt()
                }
                else -> throw Error("연산자 오류")
            }
        }
    }
}