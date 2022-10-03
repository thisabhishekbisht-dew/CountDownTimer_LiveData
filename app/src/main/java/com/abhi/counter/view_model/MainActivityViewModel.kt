package com.abhi.counter.view_model

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    /*variable for seconds*/
    private val seconds = MutableLiveData<Int>()

    /*variable for isFinished*/
    private val isFinished = MutableLiveData<Boolean>()

    val timer = object : CountDownTimer(20000, 100) {
        override fun onTick(millis: Long) {
            /*set value for seconds here*/
            val time = millis / 1000
            seconds.value = time.toInt()
        }

        override fun onFinish() {
            /*update boolean isFinsihed here*/
            isFinished.value = true
        }

    }

    /*method for getting int value for seconds */
    fun getSeconds(): LiveData<Int> {
        return seconds
    }

    /*method for getting boolean value for isFinished*/
    fun getIsFinished(): LiveData<Boolean> {
        return isFinished
    }

    fun stopCountDownTimerAndRestart() {
        timer.cancel()
        timer.start()
    }

    /*function for starting counter */
    fun startCounterTimer() {
        timer.start()
    }
}