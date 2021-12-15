package com.example.numcalculator.usecase

abstract class UseCase<PARM, TYPE> {

    abstract fun run(parm: PARM): TYPE

    operator fun invoke(parm: PARM): TYPE {
        return run(parm)
    }
}