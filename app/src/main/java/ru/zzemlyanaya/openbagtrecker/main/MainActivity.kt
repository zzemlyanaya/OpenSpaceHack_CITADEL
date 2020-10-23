package ru.zzemlyanaya.openbagtrecker.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.zzemlyanaya.openbagtrecker.R


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.containerMain)
        when(fragment!!.tag) {

        }
    }

    private fun onBackPressedDouble(){
       //TODO("on back pressed")
    }



}