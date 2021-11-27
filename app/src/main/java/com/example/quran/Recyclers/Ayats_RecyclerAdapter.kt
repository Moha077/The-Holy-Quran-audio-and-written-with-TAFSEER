package com.example.quran.Recyclers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.ApiControllers.Request.TafsirService
import com.example.quran.ApiControllers.Response.TafsirResponse
import com.example.quran.DataBase.AppDataBase
import com.example.quran.DataBase.ServiceRoom
import com.example.quran.Fragment.DetailVerset
import com.example.quran.Models.Mofasir
import com.example.quran.Models.Racine
import com.example.quran.Models.Surah
import com.example.quran.Models.Verset
import com.example.quran.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Ayats_RecyclerAdapter(context : Context, sura: Surah,val idtqfssir:Int): RecyclerView.Adapter<Ayats_RecyclerAdapter.ViewHolder>(){

    private var dataSet: List<Verset>? =  ServiceRoom.database.versetDao().getVersetsBySurah(sura.IdSourat)





    //private  var dataSet = racine.listVerset


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.SuraName)
        var ayah: TextView = itemView.findViewById(R.id.AyaTv)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewObj = LayoutInflater.from(parent.context).inflate(R.layout.aya_card, parent, false)

        return ViewHolder(viewObj)
    }


    override fun getItemCount(): Int {

        return dataSet?.size!!

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tafsirUrl = "http://api.quran-tafseer.com/"
        val retrofitTafsir = Retrofit.Builder().baseUrl(tafsirUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val tafsirApi = retrofitTafsir.create(TafsirService::class.java)
        holder.title.text = dataSet!![position].Text_AR

        val responseTafsir = tafsirApi.getTafsirByMofasirId(idtqfssir,dataSet!!.get(position).IdSourat.toInt(),dataSet!!.get(position).NumAya.toInt())
        responseTafsir.enqueue(object: Callback<TafsirResponse> {
            override fun onResponse(call: Call<TafsirResponse>, responseTafsir: Response<TafsirResponse>) {
                holder.ayah.text = responseTafsir.body()?.text
            }
            override fun onFailure(call: Call<TafsirResponse>, t: Throwable) {

                    }
                } )
            }




    }






