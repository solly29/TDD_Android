package com.example.numcalculator.ext

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import kotlin.math.round

/**
 * live 테스트 할 때 observer를 걸어주는 함수..?
 */
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            data = t
            println(data)
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)

    if(!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}

fun Number.toRound(): Number {
    val num: Number = toDouble() % 1
    return if(num == 0.0) toLong() else round(toDouble() * 1000) / 1000
}

fun String?.toTypeCasting(): Number? {
    return try {
        this?.toDouble()
    } catch (e: NumberFormatException) {
        Log.e("String.toTypeCasting", "${this}는 숫자가 아닙니다.")
        null
    } catch (e: Exception) {
        Log.e("String.toTypeCasting", "${this}는 숫자가 아닙니다.")
        null
    }
}