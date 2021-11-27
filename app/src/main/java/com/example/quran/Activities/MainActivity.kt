package com.example.quran.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quran.R
import com.example.quran.DataBase.ServiceRoom
import com.example.quran.Fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)

        /*history.setOnClickListener {

            val histRacineFragment = HistoricFragment()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment,  histRacineFragment)
                addToBackStack(null)
                commit()
            }
        }

        mark.setOnClickListener{
            val bookMarkFrag = BookMarkFragment()

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment,  bookMarkFrag)
                addToBackStack(null)
                commit()
            }

        }*/


        val souras=ServiceRoom.database.surahDao().getSurahs()
/*

        val racineFragment = RvRacineFragment(racines!!)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,  racineFragment)
            commit()
        }*/



            val RvSourahFragment = RvSourahFragment(souras!!)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment,  RvSourahFragment)
                commit()
            }
    }  }
