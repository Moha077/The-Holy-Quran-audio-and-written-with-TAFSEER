package com.example.quran.moushaff

import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlin.math.max
import kotlin.math.min
class IntroSliderAdapter(private val introSlides: List<IntroSlide>) :
    RecyclerView.Adapter<IntroSliderAdapter.IntroSlideViewHolder>(){
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f
    lateinit var holder: IntroSlideViewHolder


     class IntroSlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {

         var  imageIcon = view.findViewById<ImageView>(R.id.imageSlideIcon)

        fun bind(introSlide: IntroSlide) {
            imageIcon.setImageResource(introSlide.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewHolder {
        return IntroSlideViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.slider_item_container, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return introSlides.size
    }

    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) {
        holder.bind(introSlides[position])
        this.holder =holder
    //    scaleGestureDetector = ScaleGestureDetector(holder.itemView.context, ScaleListener())

    }
/*

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            scaleFactor *= scaleGestureDetector.scaleFactor
            scaleFactor = max(0.1f, min(scaleFactor, 10.0f))
            holder.imageIcon.scaleX = scaleFactor
            holder.imageIcon.scaleY = scaleFactor
            return true
        }
    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        scaleGestureDetector.onTouchEvent(p1)
        return  true

    }*/
}