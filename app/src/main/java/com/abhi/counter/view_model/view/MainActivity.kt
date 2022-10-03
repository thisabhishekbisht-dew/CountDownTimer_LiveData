package com.abhi.counter.view_model.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.abhi.counter_livedata.databinding.ActivityMainBinding
import com.abhi.counter.view_model.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // create instance of the ActivityMainBinding,
        // as we have only one layout activity_main.xml
        var binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)


        /*first create instance of viewmodel class*/
        val mainViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        // binding.root returns the root layout,
        // which is activity_main.xml file itself
        setContentView(binding.root)

        /*   setContentView(R.layout.activity_main)*/


        /*call start countertimer method which is in our view model*/

        mainViewModel.startCounterTimer()


        mainViewModel.getSeconds().observe(this)
        {
            /*set value of seconds to view */
            binding.tvCounter.text = it.toString()
        }


        /*check if the coundown timer is finished*/
        mainViewModel.getIsFinished().observe(this){
            if(it){
                binding.tvCounter.text="Finished"
            }
        }

        binding.btnStartAgain.setOnClickListener(){
            mainViewModel.stopCountDownTimerAndRestart()
            Log.e("Abu"," Abu")
            binding.tvCounter.text=""
            // your code to perform when the user clicks on the button
            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()


        }

    }
}