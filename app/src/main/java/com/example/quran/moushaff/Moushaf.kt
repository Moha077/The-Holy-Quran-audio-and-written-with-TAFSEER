package com.example.quran.moushaff
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.quran.R.drawable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_moushaf.*
import android.graphics.drawable.Drawable

import android.R
import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageView
import kotlin.math.max
import kotlin.math.min
import kotlin.math.max
import kotlin.math.min
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
class Moushaf : AppCompatActivity() {



fun generate_list_pages_sourat(suratPage: Surat_page):ArrayList<IntroSlide>{

    val pages=ArrayList<IntroSlide>()

    for (i in suratPage.debut_Sourat_page..604){

        val uri = "@drawable/pic"+i.toString()


        val imageResource = resources.getIdentifier(uri, null, packageName)

        val intro  =IntroSlide(imageResource)
        pages!!.add(intro)
    }
    return pages


}


  val  sourat_pages = listOf(
      Surat_page(IdSourat= 1 , debut_Sourat_page=1,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=2,fin_sourat_page = 5),
      Surat_page(IdSourat= 2 , debut_Sourat_page=50,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=77,fin_sourat_page = 5),
      Surat_page(IdSourat= 3 , debut_Sourat_page=106,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=128,fin_sourat_page = 5),
      Surat_page(IdSourat= 4 , debut_Sourat_page=151,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=177,fin_sourat_page = 5),
      Surat_page(IdSourat= 5 , debut_Sourat_page=187,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=207,fin_sourat_page = 5),
      Surat_page(IdSourat= 6 , debut_Sourat_page=221,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=235,fin_sourat_page = 5),
      Surat_page(IdSourat= 7 , debut_Sourat_page=249,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=255,fin_sourat_page = 5),
      Surat_page(IdSourat= 8 , debut_Sourat_page=261,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=267,fin_sourat_page = 5),
      Surat_page(IdSourat= 9 , debut_Sourat_page=282,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=293,fin_sourat_page = 5),
      Surat_page(IdSourat= 10 , debut_Sourat_page=305,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=312,fin_sourat_page = 5),
      Surat_page(IdSourat= 11 , debut_Sourat_page=322,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=331,fin_sourat_page = 5),
      Surat_page(IdSourat= 12 , debut_Sourat_page=342,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=350,fin_sourat_page = 5),
      Surat_page(IdSourat= 13 , debut_Sourat_page=359,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=366,fin_sourat_page = 5),
      Surat_page(IdSourat= 14 , debut_Sourat_page=376,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=385,fin_sourat_page = 5),
      Surat_page(IdSourat= 15 , debut_Sourat_page=396,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=404,fin_sourat_page = 5),
      Surat_page(IdSourat= 16 , debut_Sourat_page=411,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=415,fin_sourat_page = 5),
      Surat_page(IdSourat= 17 , debut_Sourat_page=418,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=428,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=434,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=440,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=445,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=452,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=458,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=467,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=477,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=483,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=489,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=495,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=498,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=502,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=506,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=511,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=515,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=518,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=520,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=523,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=525,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=528,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=531,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=534,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=537,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=542,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=545,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=548,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=551,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=553,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=554,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=555,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=557,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=560,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=562,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=564,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=566,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=568,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=570,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=572,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=574,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=575,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=577,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=578,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=580,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=582,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=583,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=584,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=586,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=586,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=587,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=589,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=590,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=590,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=591,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=592,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=593,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=594,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=594,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=595,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=596,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=596,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=597,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=597,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=598,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=598,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=599,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=599,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=600,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=600,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=601,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=601,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=601,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=602,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=602,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=602,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=603,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=603,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=603,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=604,fin_sourat_page = 5),
      Surat_page(IdSourat= 1 , debut_Sourat_page=604,fin_sourat_page = 1),  Surat_page(IdSourat= 2 , debut_Sourat_page=604,fin_sourat_page = 5)


  )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.quran.R.layout.activity_moushaf)
        val intent = intent
        val sourat_id= intent.getIntExtra("id_surat",1)
        var introSliderAdapter = IntroSliderAdapter(
           generate_list_pages_sourat(sourat_pages.get(sourat_id-1))
        )
        introSliderViewPager.adapter = introSliderAdapter

        introSliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })





    }






}


