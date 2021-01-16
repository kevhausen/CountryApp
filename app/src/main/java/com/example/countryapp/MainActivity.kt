package com.example.countryapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.countryapp.databinding.ActivityMainBinding
import com.example.countryapp.view.ListFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        supportFragmentManager.beginTransaction().add(R.id.frame_main, ListFragment()).commit()

        val intentFilter = IntentFilter("android.intent.action.AIRPLANE_MODE")

        val receiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.d("AirplaneMode", "Service state changed");
                val isAirplaneModeOn = intent!!.getBooleanExtra("state", false)
                if (isAirplaneModeOn) {
                    Toast.makeText(this@MainActivity, "airplane mode on", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "airplane mode off", Toast.LENGTH_SHORT).show()
                }
            }
        }
        this.registerReceiver(receiver, intentFilter)
    }


}