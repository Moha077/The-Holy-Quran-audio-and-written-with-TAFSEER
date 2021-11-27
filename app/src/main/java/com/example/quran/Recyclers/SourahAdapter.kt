package com.example.quran.Recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.Fragment.DetailSurah
import com.example.quran.Fragment.RvSourahFragment
import com.example.quran.Models.Racine
import com.example.quran.Models.Surah
import com.example.quran.R
import kotlin.collections.ArrayList

class SourahAdapter(var msg: List<Surah>):RecyclerView.Adapter<SourahAdapter.ViewHolder>() ,Filterable  {


    var racinesFilterList = ArrayList<Surah>()


    private  var dataSet= arrayListOf<Surah>()

    init {
        dataSet.addAll(msg)
        racinesFilterList = dataSet
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var id: TextView = itemView.findViewById(R.id.idRacine)
        var title: TextView = itemView.findViewById(R.id.racineTv)
        var len: TextView = itemView.findViewById(R.id.lenRacine)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewObj = LayoutInflater.from(parent.context).inflate(R.layout.racine_card, parent, false)
        return ViewHolder(viewObj)
    }


    override fun getItemCount(): Int {
        return racinesFilterList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = racinesFilterList[position].IdSourat.toString()
        holder.title.text = racinesFilterList[position].NomSourat
        holder.len.text = racinesFilterList[position].Location

        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val activity = v!!.context as AppCompatActivity
               /* if( getItemCount() != dataSet.size ){
                    ServiceRoom.database.historicRacineDao().insertHistRacine(HistoricRacine(racinesFilterList[position].idRacine))
                }**/


                val detailSurah = DetailSurah(racinesFilterList.get(position))

                activity.supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment,  detailSurah)
                    addToBackStack(null)
                    commit()
                }
            }
        }
        )
    }



    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    racinesFilterList = dataSet
                } else {
                    val resultList = ArrayList<Surah>()
                    for (row in dataSet) {
                        if (row.NomSourat.contains(charSearch)) {
                            resultList.add(row)
                        }
                    }
                    racinesFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = racinesFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                racinesFilterList = results?.values as ArrayList<Surah>
                notifyDataSetChanged()
            }

        }
    }

}