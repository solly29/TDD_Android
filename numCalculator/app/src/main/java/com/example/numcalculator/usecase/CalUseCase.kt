package com.example.numcalculator.usecase

import com.example.numcalculator.model.CalNumberData

class CalUseCase: UseCase<CalNumberData, Number>() {
    override fun run(parm: CalNumberData): Number {
        return parm.run {
            operation.calculate(number1, number2)
        }
    }
}