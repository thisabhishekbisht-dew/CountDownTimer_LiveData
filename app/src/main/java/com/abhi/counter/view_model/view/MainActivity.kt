package com.abhi.counter.view_model.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.abhi.counter.view_model.MainActivityViewModel
import com.abhi.counter_livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // create instance of the ActivityMainBinding,
        // as we have only one layout activity_main.xml
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)


        /*first create instance of viewmodel class*/
        val mainViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        // binding.root returns the root layout,
        // which is activity_main.xml file itself
        setContentView(binding.root)

        /*   setContentView(R.layout.activity_main)*/


        /*call start countertimer method which is in our view model*/

        mainViewModel.startCounterTimer()
/*make restart button clickable false*/
        binding.btnStartAgain.isEnabled = false
        mainViewModel.getSeconds().observe(this)
        {
            /*set value of seconds to view */
            binding.tvCounter.text = it.toString()
        }


        /*check if the coundown timer is finished*/
        mainViewModel.getIsFinished().observe(this) {
            if (it) {
                binding.tvCounter.text = "Finished"
                /*make start buttton clickable false*/
                binding.btnStartAgain.isEnabled=true
            }
        }

        binding.btnStartAgain.setOnClickListener() {
            mainViewModel.stopCountDownTimerAndRestart()
            binding.tvCounter.text = ""
            // your code to perform when the user clicks on the button
            Toast.makeText(this@MainActivity, "Timer is restarted. Thanks", Toast.LENGTH_SHORT)
                .show()


        }

    }
}