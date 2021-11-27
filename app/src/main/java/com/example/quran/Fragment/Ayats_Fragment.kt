package com.example.quran.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.Recyclers.AyahRecyclerAdapter
import com.example.quran.Models.Racine
import com.example.quran.Models.Surah
import com.example.quran.R
import com.example.quran.Recyclers.Ayats_RecyclerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Ayats_Fragment(val surah: Surah,val idtqfssir:Int) : Fragment(R.layout.fragment_ayat_soura) {


        private var layoutManager: RecyclerView.LayoutManager? = null
        private var adapter: RecyclerView.Adapter<Ayats_RecyclerAdapter.ViewHolder>? = null

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            layoutManager = LinearLayoutManager(requireContext())
            var listTitle = view.findViewById<TextView>(R.id.listAyaTitle)
            listTitle.text = "تفسير آيات لسورة: " + surah.NomSourat


            val recyclerView = view?.findViewById<RecyclerView>(R.id.rv_aya)
            val arraySpinner: ArrayList<String> = ArrayList()

            for (i in 1..9) {
                arraySpinner.add("Number $i")
            }
          /*  val spinnerDialogFragment =
                .newInstance(
                    "Spinner Dialog", arraySpinnerModel,
                    object : OnSpinnerOKPressedListener {
                        override fun onItemSelect(data: SpinnerModel, selectedPosition: Int) {
                            Toast.makeText(applicationContext, data.text, Toast.LENGTH_LONG).show()
                        }

                    }, 0
                )  0
                )
*/


            recyclerView?.layoutManager = this.layoutManager

            adapter = Ayats_RecyclerAdapter(this.requireContext(),surah,idtqfssir)

            recyclerView?.adapter = this.adapter

        }

        private fun filter(text: String) {

        }


    }

