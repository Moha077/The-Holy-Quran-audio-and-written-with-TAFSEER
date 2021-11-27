package com.example.quran.moushaff

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class Surat_page(
    val IdSourat: Int ,
    val debut_Sourat_page:Int,
    val fin_sourat_page:Int

)
