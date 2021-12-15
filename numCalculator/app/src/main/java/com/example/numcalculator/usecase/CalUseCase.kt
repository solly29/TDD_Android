package com.example.numcalculator.usecase

class CalUseCase: UseCase<String, Int>() {
    override fun run(parm: String): Int {
        val strList = parm.split("더하기")
        return strList[0].toInt() + strList[1].toInt()
    }
}